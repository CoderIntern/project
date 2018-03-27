package rabbitmqdemo.rabbbittest.entity;

import java.io.Serializable;

/**
 * <p> Date             :2018/3/6 </p>
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
public class ReportBO implements Serializable{

    /**
     * serialVersionUID: 串行化版本统一标识符
     * imei : 867717036767623
     * csq : 16
     * cellId : 8E52
     * stationId : 1043
     * gps : 48,48
     * warehouse : BLE4.0
     * deviceId : 8
     * device : 70D544D4D5E5
     * status : 41
     * type : 0
     */
    private static final long serialVersionUID = 4778592299868451581L;
    private String imei;
    private String csq;
    private String cellId;
    private String stationId;
    private String gps;
    private String warehouse;
    private int deviceId;
    private String device;
    private String status;
    private int type;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getCsq() {
        return csq;
    }

    public void setCsq(String csq) {
        this.csq = csq;
    }

    public String getCellId() {
        return cellId;
    }

    public void setCellId(String cellId) {
        this.cellId = cellId;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ReportBO{" +
                "imei='" + imei + '\'' +
                ", csq='" + csq + '\'' +
                ", cellId='" + cellId + '\'' +
                ", stationId='" + stationId + '\'' +
                ", gps='" + gps + '\'' +
                ", warehouse='" + warehouse + '\'' +
                ", deviceId=" + deviceId +
                ", device='" + device + '\'' +
                ", status='" + status + '\'' +
                ", type=" + type +
                '}';
    }
}
