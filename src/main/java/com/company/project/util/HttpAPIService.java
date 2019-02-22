package com.company.project.util;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class HttpAPIService {

    @Autowired
    private CloseableHttpClient httpClient;

    @Autowired
    private RequestConfig config;

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpAPIService.class);
    /**
     * 不带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     *
     * @param url
     * @return
     */
    public JSONObject doGet(String url) {
        // 声明 http get 请求
        HttpGet httpGet = new HttpGet(url);

        // 装载配置信息
        httpGet.setConfig(config);

        try {
            // 发起请求
            CloseableHttpResponse response = this.httpClient.execute(httpGet);
            // 判断状态码是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                // 返回响应体的内容
                return JSON.parseObject(EntityUtils.toString(response.getEntity(), "UTF-8"));
            }
        } catch (Exception e) {
            LOGGER.error("HTTP-GET-ERROR:" + e.getMessage());
        }
        return null;
    }

    /**
     * 带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     *
     * @param url
     * @return
     */
    public JSONObject doGet(String url, Map<String, Object> map) {
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            if (map != null) {
                // 遍历map,拼接请求参数
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
                }
            }
            // 调用不带参数的get请求
            return this.doGet(uriBuilder.build().toString());
        } catch (Exception e) {
            LOGGER.error("HTTP-GET-ERROR:" + e.getMessage());
        }
        return null;
    }

    /**
     * 带参数的post请求
     *
     * @param url
     * @param data
     * @return
     */
    public JSONObject doPost(String url, JSONObject data) {
        try {
            // 声明httpPost请求
            HttpPost httpPost = new HttpPost(url);
            // 加入配置信息
            httpPost.setConfig(config);

            StringEntity entity = new StringEntity(data.toJSONString());
            entity.setContentEncoding("utf-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            // 发起请求
            CloseableHttpResponse response = this.httpClient.execute(httpPost);
            // 判断状态码是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                // 返回响应体的内容
                return JSONObject.parseObject(EntityUtils.toString(response.getEntity()));
            }
        } catch (Exception e) {
            LOGGER.error("HTTP-POST-ERROR:" + e.getMessage());
        }

        return null;
    }

    /**
     * 不带参数post请求
     *
     * @param url
     * @return
     */
    public JSONObject doPost(String url) {
        return this.doPost(url, null);
    }
}