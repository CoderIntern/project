package com.daily.dailytest.core.utils;

import com.daily.dailytest.core.utils.support.ftp.FtpClientPool;
import com.daily.dailytest.core.utils.support.ftp.FtpHelper;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * <p> Date             :2018/1/18 </p>
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
public final class FtpUtil {

    //日志
    private static final Logger logger = LoggerFactory.getLogger(FtpUtil.class);
    private static final String LOG_RESOURCE_NOT_FOUND_INFO = "ftp client resourcecenter not found!";
    private static final String LOG_FILE_DELETE_FAILED = "file delete failed!";

    //FTPClient 对象池
    private FtpClientPool ftpClientPool;

    /**
     * 关闭FTP
     */
    public void closeFtp() {
        ftpClientPool.destroy();
    }

    /**
     * 向FTP服务器上传单个文件
     * @param localPath
     * @param remoteDirPath
     * @return
     */
    public boolean fuploadFile(String localPath, String remoteDirPath) {
        return uploadFile(localPath, remoteDirPath, null);
    }

    public boolean uploadFile(final String localPath, final String remoteDirPath, final String remoteRename) {
        boolean result = false;
        //获取FtpClient资源
        FTPClient client = ftpClientPool.getResource();
        if (null != client) {
            logger.warn(LOG_RESOURCE_NOT_FOUND_INFO);
            return result;
        }
        //执行上传
        try {
            result = FtpHelper.uploadFile(client, localPath, remoteDirPath, remoteRename);
        } catch (UnsupportedEncodingException e) {
            logger.error(LOG_FILE_DELETE_FAILED, e);
        } catch (IOException e) {
            logger.error(LOG_FILE_DELETE_FAILED, e);
        }
        //归还资源
        ftpClientPool.returnResource(client);
        return result;
    }

    /**
     * 从FTP服务器下载文件，单个文件
     * @param remotePath
     * @param localPath
     * @return
     */
    public boolean downloadFile(final String remotePath, final String localPath) {
        return downloadFile(remotePath, localPath, null);
    }

    /**
     * 从FTP服务器下载文件
     * @param remotePath
     * @param localDirPath
     * @param rename
     * @return
     */
    public boolean downloadFile(final String remotePath, final String localDirPath, final String rename) {
        boolean result = false;
        //获取FtpClient资源
        FTPClient client = ftpClientPool.getResource();
        if (null == client) {
            logger.warn(LOG_RESOURCE_NOT_FOUND_INFO);
            return result;
        }
        //执行下载
        try {
            result = FtpHelper.downloadFile(client, remotePath, localDirPath, rename);
        } catch (UnsupportedEncodingException e) {
            logger.error("file download failed !!!", e);
        } catch (IOException e) {
            logger.error("file download failed !!!", e);
        }
        //归还资源
        ftpClientPool.returnResource(client);
        return result;
    }

    /**
     * 删除指定路径下的单个文件
     * @param remotePath
     * @return
     */
    public boolean deleteFile(String remotePath) {
        boolean result = false;
        //获取FtpClient资源
        FTPClient client = ftpClientPool.getResource();
        if (null == client) {
            logger.warn(LOG_RESOURCE_NOT_FOUND_INFO);
            return result;
        }
        //执行删除
        try {
            result = FtpHelper.deleteFile(client, remotePath);
        } catch (UnsupportedEncodingException e) {
            logger.error(LOG_FILE_DELETE_FAILED, e);
        } catch (IOException e) {
            logger.error(LOG_FILE_DELETE_FAILED, e);
        }
        //资源归还
        ftpClientPool.returnResource(client);
        return result;
    }

    /**
     * 重命名远程FTP文件
     * @param name
     * @param remote
     * @return
     * @throws IOException
     */
    public boolean rename(String name, String remote) throws IOException {
        //获取FTPClient资源
        FTPClient ftpClient = ftpClientPool.getResource();
        boolean result = false;
        if (null == ftpClient) {
            logger.warn(LOG_RESOURCE_NOT_FOUND_INFO);
            return result;
        }
//        ftpClient.enterLocalPassiveMode();
//        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//        FTPStatus result = null;
        FTPFile[] files = ftpClient.listFiles(remote);
        if (files.length == 1) {
            result = ftpClient.rename(remote, name);
        }
        logger.debug("FTP服务器文件名更新标识: " + result);
        return result;
    }

    /**
     * 远程文件目录
     * @param remote
     * @return
     */
    public String[] getNames(String remote) {
        FTPClient ftpClient = ftpClientPool.getResource();
        String[] args = null;
        //归还资源
        try {
            args = ftpClient.listNames(remote);
        } catch (Exception e) {
            logger.error(LOG_RESOURCE_NOT_FOUND_INFO);
        }
        ftpClientPool.returnResource(ftpClient);
        return args;
    }

    public void setFtpClientPool(FtpClientPool ftpClientPool) {
        this.ftpClientPool = ftpClientPool;
    }
}
