package com.daily.dailytest.core.utils;

import com.alibaba.fastjson.JSON;
import com.daily.dailytest.core.constant.HttpConstant;
import com.daily.dailytest.core.utils.support.http.HttpClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

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
public class HttpUtil {

    //日志
    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * POST -> JSON 提交请求
     * @param url
     * @return
     */
    public static String postJson(String url) {
        return HttpClient.postJson(url, new HashMap<>());
    }

    /**
     * POST -> JSON 提交氢气
     * @param url
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T postJson(String url, Class<T> clazz) {
        String result = HttpClient.postJson(url, new HashMap<>());
        T object = null;
        try {
            object = clazz.newInstance();
            if (!StringUtils.isBlank(result)) {
                object = JSON.parseObject(result, clazz);
            }
        } catch (InstantiationException e) {
            logger.error("InstantiationException error!", e);
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException error!", e);
        }
        return object;
    }

    /**
     * POST -> JSON 提交请求
     * @param url
     * @param jsonParam
     * @return
     */
    public static String postJson(String url, Map<String, String> jsonParam) {
        return HttpClient.postJson(url, JSON.toJSONString(jsonParam));
    }

    /**
     * POST -> JSON 提交请求
     * @param url
     * @param jsonParam
     * @return
     */
    public static String postJson(String url, String jsonParam) {
        return HttpClient.postJson(url, jsonParam, HttpConstant.UTF8);
    }

    /**
     * POST -> JSON 提交请求
     * @param url
     * @param jsonParam
     * @param charset
     * @return
     */
    public static String postJson(String url, String jsonParam, String charset) {
        return HttpClient.postJson(url, jsonParam, charset);
    }

    /**
     * POST -> 提交xml数据
     * @param url
     * @param xml
     * @return
     */
    public static String postXml(String url, String xml) {
        return HttpClient.post(url, xml, HttpConstant.UTF8, MediaType.TEXT_XML_VALUE);
    }

    /**
     * POST 表单提交
     * @param url
     * @param params
     * @return
     */
    public static String post(String url, Map<String, String> params) {
        return HttpClient.post(url, params, HttpConstant.UTF8);
    }

    /**
     * POST 表单提交
     * @param url
     * @param params
     * @param charset
     * @return
     */
    public static String post(String url, Map<String, String> params, String charset) {
        return HttpClient.post(url, params, charset);
    }

    /**
     * GET 提交
     * @param url
     * @return
     */
    public static String get(String url) {
        return HttpClient.get(url, HttpConstant.UTF8);
    }

    /**
     * GET 提交
     * @param url
     * @param charset
     * @return
     */
    public static String get(String url, String charset) {
        return HttpClient.get(url, charset);
    }

    /**
     * GET 提交
     * @param url
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getJSON(String url, Class<T> clazz) {
        String result = HttpClient.get(url);
        T object = null;
        try {
            object = clazz.newInstance();
            if (!StringUtils.isBlank(result)) {
                object = JSON.parseObject(result, clazz);
            }
        } catch (InstantiationException e) {
            logger.error("InstantiationException error!", e);
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException error!", e);
        } catch (Exception e) {
            logger.error("Exception error!", e);
        }
        return object;
    }
}
