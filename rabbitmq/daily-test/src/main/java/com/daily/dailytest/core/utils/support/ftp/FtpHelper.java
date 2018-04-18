package com.daily.dailytest.core.utils.support.ftp;

import com.daily.dailytest.core.constant.HttpConstant;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * <p> Date             :2018/1/19 </p>
 * <p> Module           : </p>
 * <p> Description      : </p>
 * <p> Remark           : </p>
 *
 * @author yangdejun
 * @version 1.0
 * <p>--------------------------------------------------------------</p>
 * <p>修改历史</p>
 * <p>    序号    日期    修改人    修改原因    </p>
 * <p>    1                                     </p>
 */
public class FtpHelper {

    //日志
    private static final Logger logger = LoggerFactory.getLogger(FtpHelper.class);
    private static final String LOG_REMOTE_FILE_NOT_FOUND = "remote file [{}] not found!!!";
    //文件编码
    private static final String ENCODING = System.getProperty("file encoding");

    /**
     * 向FTP服务器上传单个文件
     *
     * @param client
     * @param localPath
     * @param remoteDirPath
     * @param remoteRename
     * @return
     */
    public static boolean uploadFile(FTPClient client, final String localPath, final String remoteDirPath, final String remoteRename) throws IOException {
        boolean result = false;
        //远程路径整理
        StringBuilder remotePathBuilder = new StringBuilder(remoteDirPath);
        if (StringUtils.isBlank(remoteRename)) {
            remotePathBuilder.append(localPath.substring(localPath.lastIndexOf('/') + 1));
        } else {
            remotePathBuilder.append(remoteRename);
        }
        final String remotePath = remotePathBuilder.toString();
        logger.debug("ftp [remotePath ====> {}, localPath ===> {}]", remotePath, localPath);
        //获取本地文件信息
        File localFile = new File(localPath);
        if (!localFile.exists()) {
            logger.warn("local file [{}] not found!!!", localPath);
            return result;
        }
        long localSize = localFile.length();
        //获取远程文件信息
        //创建远程目录/切换到该目录
        createDir(client, remoteDirPath);
        //获取远程文件
        FTPFile[] remoteFiles = null;
        try {
            remoteFiles = client.listFiles(new String(remotePath.getBytes(ENCODING), HttpConstant.ISO_8859_1));
        } catch (IOException e) {
            logger.error(LOG_REMOTE_FILE_NOT_FOUND, e);
            throw e;
        }
        //完成上传动作(上传，续传)
        //远程文件大小
        long remoteSize = 0L;
        //远程文件已存在
        if (remoteFiles.length == 1) {
            //远程文件大小
            remoteSize = remoteFiles[0].getSize();
            //文件已经传完不需要再继续上传
            if (localFile.length() == remoteSize) {
                logger.warn("file [{}] extis !!!", remotePath);
                return true;
            }
            //远程文件文件大小 > 本地文件文件大小
            if (remoteSize > localFile.length()) {
                logger.warn("remote file size > local file sezi");
                return result;
            }
            client.setRestartOffset(remoteSize);
        }
        try (
                final OutputStream out = client.appendFileStream(new String(remotePath.getBytes(ENCODING), HttpConstant.ISO_8859_1));
                final InputStream in = new FileInputStream(localFile)
        ) {
            in.skip(remoteSize);
            logger.info("file [{}] start upload!!!", localPath);
            //开始写入文件，并显示进度
            writeFileAndProcess(out, in, localSize, (percentage) ->
                    logger.info("file [{}] start upload, process [{}]!!!", localPath, percentage), remoteSize);
            logger.info("file [{}] upload success ~~~", localPath);
            in.close();
            out.flush();
            out.close();
            //TODO YANGDEJUN 等FTP Server 返回226 Transfer complete
            client.completePendingCommand();
            result = true;
        } catch (Exception e) {
            logger.info("file [{}] upload failed!!!", localPath);
        }
        return result;
    }

    /**
     * 从FTP服务器下载文件
     * @param client
     * @param remotePath
     * @param localDirPath
     * @param rename
     * @return
     */
    public static boolean downloadFile(FTPClient client, final String remotePath, final String localDirPath, final String rename) throws IOException {
        boolean result = false;
        StringBuilder localPathBuilder = new StringBuilder(localDirPath);
        //整合本地路径
        if (StringUtils.isBlank(rename)) {
            localPathBuilder.append(remotePath.substring(remotePath.lastIndexOf('/') + 1));
        } else {
            localPathBuilder.append(rename);
        }
        final String localPath = localPathBuilder.toString();
        logger.debug("ftp remotePath ===> [{}], localPath ===> [{}]", remotePath, localPath);
        //获取远程文件信息
        //远程目录
        String remoteDir = remotePath.substring(0, remotePath.lastIndexOf('/') + 1);
        //切换到远程目录
        if (!client.changeWorkingDirectory(new String(remoteDir.getBytes(ENCODING), HttpConstant.ISO_8859_1))) {
            logger.warn("ftp dir [{}] change failed !!!", client.getReplyString());
            return result;
        }
        logger.debug("ftp dir [{}] change successfuly ~~~", client.getReplyString());
        FTPFile[] remoteFiles = null;
        try {
            remoteFiles = client.listFiles(new String(remotePath.getBytes(ENCODING), HttpConstant.ISO_8859_1));
        } catch (IOException e) {
            logger.error(LOG_REMOTE_FILE_NOT_FOUND, e);
            throw e;
        }
        if (remoteFiles.length != 1) {
            logger.info(LOG_REMOTE_FILE_NOT_FOUND, remotePath);
            return result;
        }
        //远程文件大小
        long remoteSize = remoteFiles[0].getSize();
        //断点续传下载/直接下载
        //true-断点续传，false-重新下载
        boolean append = false;
        //本地文件大小
        long localSize = 0L;
        //读取本地文件
        File localFile = new File(localPath);
        if (localFile.exists()) {
            localSize = localFile.length();
            //文件已经存在不需要再次
            if (localSize == remoteSize) {
                logger.warn("file [{}] extis !!!", remotePath);
                return true;
            }
            //判断本地文件大小是否大于远程文件大小
            if (localSize > remoteSize) {
                logger.warn("local file size > remote file size !!!");
                return result;
            }
            append = true;
            client.setRestartOffset(localSize);
        }
        //目录是否存在，不存在创建
        File localDir = new File(localDirPath);
        if (!localDir.exists()) {
            localDir.mkdirs();
        }
        try (
                final OutputStream out = new FileOutputStream(localFile, append);
                final InputStream in = client.retrieveFileStream(new String(remotePath.getBytes(ENCODING), HttpConstant.ISO_8859_1))
        ) {
            logger.info("remote file start download ...");
            //开始写入文件，并显示进度
            writeFileAndProcess(out, in, remoteSize, (percentage) ->
                    logger.info("[{}] download, process [{}] ...", localPath, percentage), localSize);
            logger.info("remote file download successfuly");
            in.close();
            out.flush();
            out.close();
            //TODO yangdejun 等FTP Server 返回226 Transfer complete
            client.completePendingCommand();
            result = true;
        } catch (Exception e) {
            logger.info("file [{}] download failed !!!", localPath);
        }
        return result;
    }

    /**
     * 删除指定路径下的单个文件
     * @param client
     * @param remotePath
     * @return
     * @throws IOException
     */
    public static boolean deleteFile(FTPClient client, String remotePath) throws IOException {
        //删除文件
        if (!client.deleteFile(new String(remotePath.getBytes(ENCODING), HttpConstant.ISO_8859_1))) {
            logger.warn("remote file [{}] delete failed !!!", client.getReplyString());
            return false;
        }
        logger.info("remote file [{}] delete succssfuly ~~~", remotePath);
        return true;
    }

    /**
     * FTP创建远程目录，并且切换到该目录下
     * @param client
     * @param remotePath
     * @return
     * @throws IOException
     */
    private static boolean createDir(FTPClient client, final String remotePath) throws IOException {
        String path = remotePath;
        //是否是文件
        if (0 < remotePath.indexOf('.', 0)) {
            path = remotePath.substring(0, remotePath.lastIndexOf('/') + 1);
        }
        //尝试切入目录
        if (client.changeWorkingDirectory(path)) {
            return true;
        }
        String[] directories = path.split("/");
        StringBuilder dirBuilder = new StringBuilder();
        String dirPath = "/";
        boolean result = true;
        for (String dir : directories) {
            if (StringUtils.isBlank(dir)) {
                dirBuilder.append("/");
                continue;
            }
            dirBuilder.append(dir);
            dirBuilder.append("/");
            dirPath = dirBuilder.toString();
            if (client.changeWorkingDirectory(dirPath)) {
                continue;
            }
            if (!client.makeDirectory(dirPath)) {
                logger.warn("ftp create remoteDir [{}] error !!!", client.getReplyString());
                result = false;
                break;
            }
            logger.debug("ftp create remoteDir [{}]", dirPath);
        }
        client.changeWorkingDirectory(dirPath);
        logger.info("ftp change directory [{}]", dirPath);
        return result;
    }

    /**
     * 写入文件和进度条
     * @param out
     * @param in
     * @param endFileSize
     * @param process
     * @param beginFileSize
     * @throws IOException
     */
    private static void writeFileAndProcess(OutputStream out, InputStream in, long endFileSize, Process process, final long beginFileSize) throws IOException {
        byte[] bytes = new byte[1024];
        long step = endFileSize / 100;
        long percentage = 0;
        long localSize = beginFileSize;
        int c;
        while ((c = in.read(bytes)) != -1) {
            out.write(bytes, 0, c);
            localSize += c;
            //一次性读完
            if (0 >= step) {
                process.listen(100);
                break;
            }
            //进度比率
            if (localSize / step != percentage) {
                percentage = localSize / step;
                if (percentage > 100) {
                    percentage = 100;
                }
                process.listen(percentage);
            }
            if (percentage >= 100) {
                break;
            }
        }
    }

    private interface Process {
        //监听回调百分比
        void listen(final long percentage);
    }

}
