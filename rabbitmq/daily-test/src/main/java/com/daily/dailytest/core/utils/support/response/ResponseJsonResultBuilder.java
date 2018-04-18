package com.daily.dailytest.core.utils.support.response;

import com.daily.dailytest.core.utils.support.response.bean.ResponseVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class ResponseJsonResultBuilder {

    //返回数据
    private ResponseVO responseVO;

    //日志
    private static final Logger logger = LoggerFactory.getLogger(ResponseJsonResultBuilder.class);

    private ResponseJsonResultBuilder() {

    }

    private static ResponseJsonResultBuilder instance;

    public static ResponseJsonResultBuilder getInstance() {
        instance = new ResponseJsonResultBuilder();
        return instance;
    }

    /**
     * 返回对象序列化
     * @return
     */
    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        String result = null;
        try {
            result = mapper.writeValueAsString(this.responseVO);
        } catch (JsonProcessingException e) {
            logger.error("Json To String Error!", e);
        } finally {
            this.responseVO = null;
        }
        return result;
    }

    /**
     * 手动设置
     * @param data
     * @return
     */
    public ResponseJsonResultBuilder setResponseVO(ResponseVO data) {
        this.responseVO = data;
        return instance;
    }

    /**
     * 构建输出结果对象
     * @param code
     * @return
     */
    public ResponseJsonResultBuilder buildResponsResult(String code) {
        return this.buildResponsResult(code, null, null);
    }

    /**
     * 构建输出结果对象
     * @param code
     * @param data
     * @return
     */
    public ResponseJsonResultBuilder buildResponsResult(String code, Object data) {
        return this.buildResponsResult(code, null, data);
    }

    /**
     * 构建输出结果对象
     * @param code
     * @param errParams
     * @return
     */
    public ResponseJsonResultBuilder buildResponsResult(String code, String[] errParams) {
        return this.buildResponsResult(code, errParams, null);
    }

    public ResponseJsonResultBuilder buildResponsResult(String code, String[] errParams, Object data) {
        responseVO = new ResponseVO();
        responseVO.setCode(code);
        responseVO.setData(data);
        responseVO.setErrParams(errParams);
        return instance;
    }
}
