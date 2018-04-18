package com.daily.dailytest.core.utils.support.ftp;

import org.apache.commons.net.ftp.FTPClient;

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
public class FtpClientPool extends AbstractFtpClientPool<FTPClient> {

    public FtpClientPool(FtpClientFactory factory) {
        super(factory.getFtpClientConfig(), factory);
    }
}
