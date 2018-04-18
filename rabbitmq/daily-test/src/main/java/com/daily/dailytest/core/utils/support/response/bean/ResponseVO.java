package com.daily.dailytest.core.utils.support.response.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * <p> Date             :2018/1/17 </p>
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
public class ResponseVO {

    //响应码，默认成功
    private String code = ResponseCode.SUCCESS;

    //用于替换errCode的描述信息中的变量
    private String[] errParams = {};

    //返回数据，可以是任意对象，如，JsonObject、JsonArray、String等
    private Object data = "";

    public ResponseVO() {

    }

    public ResponseVO(Object data) {
        this.data = data;
    }

    private ResponseVO(String code, String[] errParams) {
        this.code = code;
        this.errParams = errParams;
    }

    private ResponseVO(String code, Object data) {
        this.code = code;
        this.data = data;
    }

    /**
     * 构建错误响应
     * @param code
     * @return
     */
    public static ResponseVO fail(String code) {
        return new ResponseVO(code, new String[]{});
    }

    /**
     * 构建错误响应，带格式化参数
     * @param code
     * @param errParams
     * @return
     */
    public static ResponseVO fail(String code, String[] errParams) {
        return new ResponseVO(code, errParams);
    }

    /**
     * 构建成功响应
     * @param data
     * @return
     */
    public static ResponseVO success(Object data) {
        return new ResponseVO(data);
    }

    /**
     * 构建错误响应并携带参数
     * @param code
     * @param data
     * @return
     */
    public static ResponseVO fail(String code, Object data) {
        return new ResponseVO(code, data);
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String[] getErrParams() {
        return errParams;
    }

    public void setErrParams(String[] errParams) {
        if (null == errParams || 0 >= errParams.length) {
            return;
        }
        this.errParams = errParams;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        if (null == data) {
            return;
        }
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return "0".equals(this.code);
    }
}
