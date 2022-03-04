package com.autotest.uitils;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.*;

/**
 * @ClassName HttpUtils
 * @Description TODO
 * @Author tyx
 * @Date 2022/3/2 15:04
 */
public class HttpUtils {
    public static Logger logger=Logger.getLogger(HttpUtils.class);

    public static Map<String, Object> getDefaultHeaders() {
        Map<String, Object> headers = new HashMap<>();
        return headers;
    }

    public static String jsonToKeyValues(String json) {
        //json参数转为key1=value1&key2=value2这种form表单的数据
        Map map = JSONObject.parseObject(json, Map.class);
        Set<String> keySets = map.keySet();
        String params = "";
        for (String key : keySets) {
            params += key + "=" + map.get(key) + "&";
        }
        params = params.substring(0, (params.length() - 1));
        return params;
    }
    /*
    get请求
    参数 url:
    headers:请求头
     */
    public static String doGet(String url, Map<String, Object> headers) {
        return given().headers(headers).get(url).asString();
    }

    public static String doPost(String url, Map<String, Object> headers, String params) {
        try {
            JSONObject.parseObject(params);
        } catch (Exception e) {
            logger.info(e);
            logger.info("错误，fastjson解析错误！");
        }
        return given().headers(headers).body(params).post(url).asString();
    }

    public static String doPut(String url, Map<String, Object> headers, String params) {
        try {
            JSONObject.parseObject(params);
        } catch (Exception e) {
            logger.info(e);
            logger.info("错误，fastjson解析错误！");
        }
        return given().headers(headers).body(params).put(url).asString();
    }

    public static String doPatch(String url, Map<String, Object> headers, String params) {
        try {
            JSONObject.parseObject(params);
        } catch (Exception e) {
            logger.info(e);
            logger.info("错误，fastjson解析错误！");
        }
        return given().headers(headers).body(params).patch(url).asString();
    }

    public static String doDelete(String url, Map<String, Object> headers) {
        return given().headers(headers).delete(url).asString();
    }
}
