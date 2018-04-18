package com.daily.dailytest.core.utils.support.http;

import com.alibaba.fastjson.JSON;
import com.daily.dailytest.core.constant.HttpConstant;
import com.daily.dailytest.core.utils.HttpUtil;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

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
public abstract class HttpClient {

    private static final String STR_EXCEPTION = "the exception is";

    //日志
    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    private static final String LOGGER_SEND_POST_FAILED = "send post request is failed.";
    private static final String LOGGER_SEND_POST_FORMAT = "http send url:{}, params: {}";

    /**
     * POST -> JSON 提交请求
     * @param url
     * @return
     */
    public static String postJson(String url) {
        return postJson(url, new HashMap<>());
    }

    /**
     * POST -> JSON 提交请求
     * @param url
     * @param jsonParam
     * @return
     */
    public static String postJson(String url, Map<String, String> jsonParam) {
        return postJson(url, JSON.toJSONString(jsonParam));
    }

    /**
     * POST -> JSON 提交请求
     * @param url
     * @param jsonParam
     * @return
     */
    public static String postJson(String url, String jsonParam) {
        return postJson(url, jsonParam, HttpConstant.UTF8);
    }

    /**
     * POST -> JSON 提交请求
     * @param url
     * @param jsonParam
     * @param charset
     * @return
     */
    public static String postJson(String url, String jsonParam, String charset) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            String sid = null;
            if (RequestContextHolder.getRequestAttributes() != null) {
                sid = RequestContextHolder.currentRequestAttributes().getSessionId();
            }
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty(HttpHeaders.ACCEPT, MediaType.ALL_VALUE);
            conn.setRequestProperty(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE + ";charset=" + charset);
            conn.setRequestProperty(HttpHeaders.CONNECTION, "Keep-Alive");
            conn.setRequestProperty(HttpHeaders.USER_AGENT, "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1");
            if (null != sid && 0 < sid.length()) {
                conn.setRequestProperty("Cookie", String.format("%s=%s", HttpConstant.SESSION_ID_NAME, sid));
            }
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            logger.info(LOGGER_SEND_POST_FORMAT, url, jsonParam);
            out.print(jsonParam);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.error(LOGGER_SEND_POST_FAILED, e);
        } finally {
            try {
                if (null != out) {
                    out.close();
                }
                if (null != null) {
                    in.close();
                }
            } catch (IOException ex) {
                logger.error("close post stream is failed." + STR_EXCEPTION, ex);
            }
        }
        return result;
    }

    /**
     * POST 提交xml数据
     * @param url
     * @param xml
     * @return
     */
    public static String postXml(String url, String xml) {
        return post(url, xml, HttpConstant.UTF8, MediaType.TEXT_XML_VALUE);
    }

    /**
     * POST 提交请求
     *
     * @param url
     * @param param
     * @param charset
     * @param contentType
     * @return
     */
    public static String post(String url, String param, String charset, String contentType) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty(HttpHeaders.ACCEPT, MediaType.ALL_VALUE);
            conn.setRequestProperty(HttpHeaders.CONTENT_TYPE, contentType + ";charset=" + charset);
            conn.setRequestProperty(HttpHeaders.CONNECTION, "Keep-Alive");
            conn.setRequestProperty(HttpHeaders.USER_AGENT, "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            logger.info(LOGGER_SEND_POST_FORMAT, url, param);
            out.print(param);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.error(LOGGER_SEND_POST_FAILED, e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                logger.error("close post stream is ailed." + STR_EXCEPTION, ex);
            }
        }
        return result;
    }

    /**
     * POST 表单提交
     * @param url
     * @param params
     * @return
     */
    public static String post(String url, Map<String, String> params) {
        return post(url, params, HttpConstant.UTF8);
    }

    /**
     * POST 表单提交
     *
     * @param url
     * @param params
     * @param charset
     * @return
     */
    public static String post(String url, Map<String, String> params, String charset) {
        String result = "";
        try {
            HttpClientBuilder httpClientBuilder = HttpClients.custom();
            List<BasicNameValuePair> list = new ArrayList<>();
            logger.info(LOGGER_SEND_POST_FORMAT, url, params);
            Set<Map.Entry<String, String>> entrySet = params.entrySet();
            for (Map.Entry<String, String> e : entrySet) {
                String name = e.getKey();
                String value = e.getValue();
                BasicNameValuePair pair = new BasicNameValuePair(name, value);
                list.add(pair);
            }
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(30000)
                    .setConnectionRequestTimeout(30000)
                    .setConnectionRequestTimeout(30000)
                    .build();
            HttpUriRequest postMethod = RequestBuilder.post().setUri(url)
                    .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=" + charset)
                    .addParameters(list.toArray(new BasicNameValuePair[params.size()]))
                    .setConfig(requestConfig).build();
            HttpResponse response = httpClientBuilder.build().execute(postMethod);
            result = EntityUtils.toString(response.getEntity(), charset);
        } catch (IOException e) {
            logger.error(LOGGER_SEND_POST_FAILED + STR_EXCEPTION, e);
        }
        return result;
    }

    /**
     * GET 提交
     * @param url
     * @return
     */
    public static String get(String url) {
        return get(url, HttpConstant.UTF8);
    }

    /**
     * GET 提交
     * @param url
     * @param charset
     * @return
     */
    public static String get(String url, String charset) {
        String result = "";
        try {
            HttpClientBuilder httpClientBuilder = HttpClients.custom();
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(30000)
                    .setConnectionRequestTimeout(30000)
                    .setConnectionRequestTimeout(30000).build();
            HttpUriRequest getMethod = RequestBuilder.get().setUri(url).setConfig(requestConfig).build();
            HttpResponse response = httpClientBuilder.build().execute(getMethod);
            result = EntityUtils.toString(response.getEntity(), charset);
        } catch (IOException e) {
            logger.error("send get request is failed." + STR_EXCEPTION, e);
        }
        return result;
    }
}
