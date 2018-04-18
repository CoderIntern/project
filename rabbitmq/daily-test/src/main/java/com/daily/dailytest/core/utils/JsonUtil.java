package com.daily.dailytest.core.utils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
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
public class JsonUtil {

    //日志
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private JsonUtil() {

    }

    /**
     * JSON 转 MAP
     * @param json
     * @return
     */
    public static Map jsonToMap(String json) {
        Map map = JSONObject.parseObject(json);
        if (null == map || map.isEmpty()) {
            return new HashMap<>();
        }
        return map;
    }

    /**
     * MAP 转JSON
     * @param map
     * @return
     */
    public static String mapToJson(Map map) {
        StringWriter stringWriter = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(stringWriter, map);
        } catch (IOException e) {
            logger.error("ObjectMapper conversion failed", e);
        } finally {
            try {
                stringWriter.close();
            } catch (IOException e) {
                logger.error("ObjectMapper close failed", e);
            }
        }
        return stringWriter.toString();
    }
}
