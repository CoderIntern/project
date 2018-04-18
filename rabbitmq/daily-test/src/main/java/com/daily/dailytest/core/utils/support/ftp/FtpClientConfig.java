package com.daily.dailytest.core.utils.support.ftp;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

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
public class FtpClientConfig extends GenericObjectPoolConfig {

    //FTP地址
    private String host;

    //FTP端口
    private int port;

    //FTP登录账号
    private String account;

    //FTP登录密码
    private String password;

    //是否时主动模式 ACTIVE-主动模式，Passive-被动模式
    private boolean isActiveMode = false;

    //缓冲区大小
    private int bufferSize = 10240;

    //默认连接超时时间/连接超时时间 默认30s
    private int connectionTimeout = 30000;

    //数据传输超时时间 默认60s
    private int dataTimeout = 60000;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActiveMode() {
        return isActiveMode;
    }

    public void setActiveMode(boolean activeMode) {
        isActiveMode = activeMode;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getDataTimeout() {
        return dataTimeout;
    }

    public void setDataTimeout(int dataTimeout) {
        this.dataTimeout = dataTimeout;
    }

    @Override
    public String toString() {
        return "FtpClientConfig{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", isActiveMode=" + isActiveMode +
                ", bufferSize=" + bufferSize +
                ", connectionTimeout=" + connectionTimeout +
                ", dataTimeout=" + dataTimeout +
                '}';
    }
}
