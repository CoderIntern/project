package com.daily.dailytest.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * <p> Date             :2018/4/16 </p>
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
public class IPUtils {

    /**
     * 调用windows cmd 命令
     * @param cmd
     * @return
     * @throws Exception
     */
    public static String executeCmd(String cmd) throws Exception{
        Process process = Runtime.getRuntime().exec("cmd /c" + cmd);
        StringBuilder executeResult = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = br.readLine()) != null) {
            executeResult.append(line + "\n");
        }
        return executeResult.toString();
    }

    /**
     * 连接ADSL
     * @param adslTitle
     * @param adslName
     * @param adlsPwd
     * @return
     * @throws Exception
     */
    public static boolean connAdsl (String adslTitle, String adslName, String adlsPwd) throws Exception {
        String adslCmd = "rasdial " + adslTitle + " " + adslName + " " + adlsPwd;
        return executeCmd (adslCmd).indexOf ("已连接") > 0 ? true : false;
    }

    /**
     * 断开ADSL
     * @param adslTitle
     * @return
     * @throws Exception
     */
    public static boolean cutAdsl (String adslTitle) throws Exception {
        String adslCmd = "rasdial" + adslTitle + " /disconnect";
        return executeCmd(adslCmd).indexOf("没有连接") != -1 ? false : true;
    }

}
