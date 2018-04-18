package com.daily.dailytest.core.base;

import com.daily.dailytest.core.constant.HttpConstant;
import com.daily.dailytest.core.utils.LangUtil;
import com.daily.dailytest.core.utils.support.paramchecker.ParamCheckerResult;
import com.daily.dailytest.core.utils.support.response.ResponseJsonResultBuilder;
import com.daily.dailytest.core.utils.support.response.bean.ResponseCode;
import com.daily.dailytest.core.utils.support.response.bean.ResponseVO;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

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
public class BaseController {

    @Autowired(required = false)
    private LangUtil langUtil;

    /**
     * 输出JSON，错误提示
     * @param paramCheckerResult
     * @return
     */
    protected ResponseEntity<String> responseResultJsonError(ParamCheckerResult paramCheckerResult) {
        String result = ResponseJsonResultBuilder.getInstance().buildResponsResult(paramCheckerResult.code(), paramCheckerResult.errParams()).toJson();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpConstant.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    /**
     * 输出JSON，错误提示
     * @param code
     * @return
     */
    protected ResponseEntity<String> responseResultJsonError(String code) {
        String result = ResponseJsonResultBuilder.getInstance().buildResponsResult(code).toJson();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpConstant.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    /**
     * 输出JSON,错误提示
     * @param code
     * @param errorParams
     * @return
     */
    public ResponseEntity<String> responseResultJsonError(String code, String[] errorParams) {
        String result = ResponseJsonResultBuilder.getInstance().buildResponsResult(code, errorParams).toJson();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpConstant.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    /**
     * 输出JSON，成功
     * @param data
     * @return
     */
    protected ResponseEntity<String> responseResultJsonSuccess(Object data) {
        String result = ResponseJsonResultBuilder.getInstance().buildResponsResult(ResponseCode.SUCCESS, data).toJson();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpConstant.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    /**
     * 输出JSON， 成功
     * @return
     */
    protected ResponseEntity<String> responseResultJsonSuccess() {
        String result = ResponseJsonResultBuilder.getInstance().buildResponsResult(ResponseCode.SUCCESS).toJson();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpConstant.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    /**
     * 输出JSON
     * @param responseVO
     * @return
     */
    protected ResponseEntity<String> responseResultJsonObject(ResponseVO responseVO) {
        String result = ResponseJsonResultBuilder.getInstance().setResponseVO(responseVO).toJson();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpConstant.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }

    /**
     * 获取输出的XML内容
     * @param nodeName
     * @return
     */
    protected ResponseEntity<String> responseResultXml(String nodeName) {
        return this.responseResultXml(nodeName, 0L, null);
    }

    /**
     * 获取输出的XML内容
     * @param nodeName
     * @param code
     * @return
     */
    protected ResponseEntity<String> responseResultXml(String nodeName, Long code) {
        return this.responseResultXml(nodeName, code, null);
    }

    /**
     * 获取输出XML内容
     * @param nodeName
     * @param code
     * @return
     */
    protected ResponseEntity<String> responseResultXml(String nodeName, String code) {
        return this.responseResultXml(nodeName, Long.parseLong(code), null);
    }

    /**
     * 获取输出的XML内容
     * @param nodeName
     * @param code
     * @param msg
     * @return
     */
    protected ResponseEntity<String> responseResultXml(String nodeName, Long code, String msg) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement(nodeName);
        Element resultCode = root.addElement("ResultCode");
        resultCode.addText(String.valueOf(code));
        //ResultDesc
        Element resultDesc = root.addElement("ResultDesc");
        if (StringUtils.isBlank(msg) && null != langUtil) {
            resultDesc.addText(langUtil.getCodeString(String.valueOf(code)));
        } else {
            resultDesc.addText(msg);
        }
        return new ResponseEntity<>(root.asXML(), HttpStatus.OK);
    }

}
