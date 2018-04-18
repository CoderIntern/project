package com.daily.dailytest.core.utils.support.ftp;

import com.daily.dailytest.core.constant.HttpConstant;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

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
public class FtpClientFactory implements PooledObjectFactory<FTPClient> {

    //日子
    private static final Logger logger = LoggerFactory.getLogger(FtpClientFactory.class);

    //FtpClient位置
    private FtpClientConfig ftpClientConfig;

    public FtpClientFactory(FtpClientConfig ftpClientConfig) {
        this.ftpClientConfig = ftpClientConfig;
    }

    /**
     * 工厂创建一个FTPClient对象
     * @return
     * @throws Exception
     */
    @Override
    public PooledObject<FTPClient> makeObject() throws Exception {
        FTPClient ftpClient = new FTPClient();
        ftpClient.setControlEncoding(HttpConstant.GB2312);
        //超时时间(防止FTP僵死)
        ftpClient.setConnectTimeout(ftpClientConfig.getConnectionTimeout());
        ftpClient.setDefaultTimeout(ftpClientConfig.getConnectionTimeout());
        ftpClient.setDataTimeout(ftpClient.getDefaultTimeout());
        ftpClient.setControlKeepAliveTimeout(ftpClientConfig.getConnectionTimeout());
        //开始连接
        ftpClient.connect(ftpClientConfig.getHost(), ftpClient.getPassivePort());
        //登录失败
        if (!ftpClient.login(ftpClientConfig.getAccount(), ftpClientConfig.getPassword())) {
            logger.warn(ftpClient.getReplyString());
            throw new IOException("530 Authentication failed.");
        }
        //主动模式
        if (ftpClientConfig.isActiveMode()) {
            ftpClient.enterLocalActiveMode();
        } else {
            //被动模式
            ftpClient.enterLocalPassiveMode();
        }
        logger.debug(ftpClient.getReplyString());
        //设置缓冲区大小
        ftpClient.setBufferSize(ftpClientConfig.getBufferSize());
        logger.debug("makeObject");
        return new DefaultPooledObject<>(ftpClient);
    }

    /**
     * 销毁
     * @param pooledObject
     * @throws Exception
     */
    @Override
    public void destroyObject(PooledObject<FTPClient> pooledObject) throws Exception {
        logger.debug("destroyObject");
        FTPClient client = pooledObject.getObject();
        if (!client.isConnected()) {
            return;
        }
        try {
            client.disconnect();
        } catch (Exception e) {
            logger.error("ftp client disconnect error!!!", e);
            throw e;
        }
    }

    /**
     * 验证该对象是否可用
     * @param pooledObject
     * @return
     */
    @Override
    public boolean validateObject(PooledObject<FTPClient> pooledObject) {
        logger.debug("validateObject");
        FTPClient client = pooledObject.getObject();
        if (!client.isConnected()) {
            return false;
        }
        try {
            return client.sendNoOp();
        } catch (IOException e) {
            logger.warn("Failed to validate client: ", e);
            return false;
        }
    }

    @Override
    public void activateObject(PooledObject<FTPClient> pooledObject) throws Exception {
        logger.debug("activateObject");
    }

    @Override
    public void passivateObject(PooledObject<FTPClient> pooledObject) throws Exception {
        logger.debug("passivateObject");
    }

    public FtpClientConfig getFtpClientConfig() {
        return ftpClientConfig;
    }

    public void setFtpClientConfig(FtpClientConfig ftpClientConfig) {
        this.ftpClientConfig = ftpClientConfig;
    }
}
