package com.daily.dailytest.core.utils.support.response.bean;

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
public class ResponseCode {

    /**返回码，成功*/
    public static final String SUCCESS = "0";

    /**缺少请求参数*/
    public static final String ERROR_MISS_PARAM = "011";

    /**
     * 校验文件上传过大
     */
    public static final String ERROR_FILE_MAX_SIZE = "025";

    /**
     * 校验后缀名不正确
     */
    public static final String ERROR_FILE_SUFFIX_NAME = "026";

    /**
     * 校验数字类型
     */
    public static final String ERROR_NOT_NUMBER = "021";

    /**
     * 校验IP地址
     */
    public static final String ERROR_NOT_IP = "022";

    /**
     * 校验日期参数格式
     */
    public static final String ERROR_DATE_FORMAT = "022";

    /**
     * 校验参数长度
     */
    public static final String ERROR_LENGTH_MAX = "023";
}
