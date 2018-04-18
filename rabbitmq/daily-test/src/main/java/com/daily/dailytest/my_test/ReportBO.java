package com.daily.dailytest.my_test;

import java.io.Serializable;
import java.util.Calendar;

/**
 * <p> Date             :2018/3/6 </p>
 * <p> Module           : </p>
 * <p> Description      : </p>
 * <p> Remark           : </p>
 *
 * @author yangdejun
 * @version 1.0  <p>--------------------------------------------------------------</p> <p>修改历史</p> <p>    序号    日期    修改人    修改原因    </p> <p>    1                                     </p>
 */
public class ReportBO implements Serializable{

    /**
     * 蓝牙网管ID
     */
    private String gwId;

    /**
     * 蓝牙信标ID
     */
    private String beaconId;

    /**
     * 检测信号强度
     */
    private int beaconSignalStrength;

    private int rSSIReference;

    /**
     * 是否需要计算标识：0:不计算; 1:计算
     * 用于多点定位和单点定位区分
     */
    private int flag = 0;

    /**
     * 是否告警标识: 0:非告警; 1:告警
     */
    private int alarm = 0;

    private long timestamp = Calendar.getInstance().getTimeInMillis();

    /**
     * Gets gw id.
     *
     * @return the gw id
     */
    public String getGwId() {
        return gwId;
    }

    /**
     * Sets gw id.
     *
     * @param gwId the gw id
     */
    public void setGwId(String gwId) {
        this.gwId = gwId;
    }

    /**
     * Gets beacon id.
     *
     * @return the beacon id
     */
    public String getBeaconId() {
        return beaconId;
    }

    /**
     * Sets beacon id.
     *
     * @param beaconId the beacon id
     */
    public void setBeaconId(String beaconId) {
        this.beaconId = beaconId;
    }

    /**
     * Gets beacon signal strength.
     *
     * @return the beacon signal strength
     */
    public int getBeaconSignalStrength() {
        return beaconSignalStrength;
    }

    /**
     * Sets beacon signal strength.
     *
     * @param beaconSignalStrength the beacon signal strength
     */
    public void setBeaconSignalStrength(int beaconSignalStrength) {
        this.beaconSignalStrength = beaconSignalStrength;
    }

    /**
     * Gets flag.
     *
     * @return the flag
     */
    public int getFlag() {
        return flag;
    }

    /**
     * Sets flag.
     *
     * @param flag the flag
     */
    public void setFlag(int flag) {
        this.flag = flag;
    }

    /**
     * Gets alarm.
     *
     * @return the alarm
     */
    public int getAlarm() {
        return alarm;
    }

    /**
     * Sets alarm.
     *
     * @param alarm the alarm
     */
    public void setAlarm(int alarm) {
        this.alarm = alarm;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getrSSIReference() {
        return rSSIReference;
    }

    public void setrSSIReference(int rSSIReference) {
        this.rSSIReference = rSSIReference;
    }

    @Override
    public String toString() {
        return "ReportBO{" +
                "gwId='" + gwId + '\'' +
                ", beaconId='" + beaconId + '\'' +
                ", beaconSignalStrength=" + beaconSignalStrength +
                ", rSSIReference=" + rSSIReference +
                ", flag=" + flag +
                ", alarm=" + alarm +
                ", timestamp=" + timestamp +
                '}';
    }
}
