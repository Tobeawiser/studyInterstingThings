package com.example.lovestory.util.test.sendrequest;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;

public class SendRequestPressureTest {


    public static void main(String[] args) throws Exception {
        //1. 确定首页的url:
        String indexUrl = "http://192.168.43.167:8081/api/gf-admin/gf/index/login";
        //2. 发送请求, 获取数据
        //2.1 获取httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2.2 创建请求方式对象
        HttpPost httpPost = new HttpPost(indexUrl);
        //2.3 封装请求参数, 和请求头
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUserId("odis");
        loginInfo.setPassword("047427ca382ba3f663dc277fe5c0438751420deaae9b9b7ddbbaa200390416939878e389ad40f19dcc2b7e283225b1ff163690050331a425b0e88e6cf8a92663913bc028d3bd38b799e2b3e739bf5c471e1f16aa178aef57168f28edda0345746e7f6f8e36c681");
        HttpEntity entity = new StringEntity(JSONObject.toJSONString(loginInfo));
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
        httpPost.setHeader("Cookie", "Authorization=bearerd963152a-116b-4c6c-8354-19d21635d40f; JSESSIONID=042A36D37A3E611F8B5C99A8A74BD971");

        // 设计一个头:  referer  防掉链
        //httpPost.setHeader("Referer", "http://localhost:8081/#/login");
        //2.4 发送请求, 获取响应的对象
        CloseableHttpResponse response = httpClient.execute(httpPost);
        System.out.println(response.getStatusLine());
        HttpEntity entity1 = response.getEntity();
        String result = EntityUtils.toString(entity1, "utf-8");
        System.out.println(result);


        //进行导入
        String reportImport = "http://192.168.43.167:8081/api/report/import/upload";
        HttpPost httpPost2 = new HttpPost(reportImport);
        FileEntity fileEntity = new FileEntity(new File("C:\\Users\\86131\\Desktop\\报告导入\\006304_中加颐鑫纯债债券型证券投资基金2020年第三季度报告 - 副本 - 副本 (5).doc"));
        httpPost2.setEntity(fileEntity);

        httpPost2.setHeader("Content-Type", "multipart/form-data");
        httpPost2.setHeader("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhcHBJZCI6eyJmdW5jUGtnIjoiY29tLmhleC5vZGlzIiwiYXBwSWQiOiJvZGlzIiwib3JnVHlwZSI6ImhyIiwicHVibGljS2V5IjoiMDRhYmIyZTRlYTU5MzdiMDM4YTA2NmQ3ZWVlNGEyNTgzYWJmOGIxOTVlMzBlODIyMDQ2ZDYxMjVhNDY0NDEzNjY3MjE0YWQ1OWQ0MDk5YjViYWYzMWY1YjQ3OGU2OWFmZjExMGQ0NDg2NjQ1YWZiOTJiYWJmYjJjZWNiN2ZmZDRlNiIsInB3ZFJ1bGUiOiIiLCJwd2RQcm9tcHQiOiIiLCJwd2RFeHBpcmVEYXlzIjowLCJlcnJvckNvdW50Ijo1LCJhbGxvd011bHRpIjp0cnVlLCJjYXB0Y2hhQ29kZUNvdW50Ijo0fSwiY3J0VHMiOjE2NDY5ODkwOTksInVzZXJJZCI6Im9kaXMifQ.5R-KWKli0g_IIjhyGY0BH2s5C8XPUffLR8zcfknVPbI");


    }

}
