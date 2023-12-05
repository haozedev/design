package com.house.design.book.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpMethod;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

/**
 * @author haoze
 * @create 2023/12/5 12:24
 * @description
 */
public class HttpClientUtils {

    public static JSONObject execute(String url, HttpMethod httpMethod){
        HttpRequestBase http = null;

        try {
            HttpClient client = HttpClients.createDefault();
            if (httpMethod == HttpMethod.GET){
                http = new HttpGet(url);
            }else {
                http = new HttpPost(url);
            }
            HttpEntity entity = client.execute(http).getEntity();
            return JSONObject.parseObject(EntityUtils.toString(entity));
        }catch (IOException e){
            throw new RuntimeException("");
        }finally {
            http.releaseConnection();
        }
    }

}