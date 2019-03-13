package com.sys.common;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Aliyun {


    /**
     * APISTORE_GET
     * @param strUrl
     * @param param
     * @param appcode
     * @return
     */
    public static String APISTORE(String strUrl, String param, String appcode, String Method) {

        String returnStr = null; // 返回结果定义
        URL url = null;
        HttpURLConnection httpURLConnection = null;
        try {
            url = new URL(strUrl + "?" + param);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setRequestProperty("Authorization", "APPCODE " + appcode);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod(Method);
            httpURLConnection.setUseCaches(false); // 不用缓存
            httpURLConnection.connect();
            httpURLConnection.getInputStream();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader
                            (httpURLConnection.getInputStream(), "utf-8"));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            reader.close();
            returnStr = buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return returnStr;
    }


    // 发起请求,获取内容
    public static void main(String[] args) {
        //请正确填写appcode,如果填写错误阿里云会返回 400错误或403错误
        //appcode查看地址 https://market.console.aliyun.com/imageconsole/
        String appcode = "88f2ea464e1540ab8db0e20a80a7e0ad";
        //请求地址
        String url="http://v.apis.la/api/idcard/dcidcard";
        //请求参数
        String params = "realName=彭浩&cardNo=320323199609031031";

        //发送请求
        String result = APISTORE(url, params, appcode,"POST");
        //输出结果
        System.out.println(result);
        //JSON
        JSONObject object = JSONObject.fromObject(result);
        //输出状态码
        System.out.println(object.getInt("error_code")) ;
        //输出返回结果
        System.out.println(object.get("reason")) ;
    }









   /* *//**
     * return
     *
     * @param args
     *//*
    public static void main(String[] args) {
        String host = "https://idcardpro.market.alicloudapi.com";
        String path = "/idcard";
        String method = "POST";
        String appcode = "88f2ea464e1540ab8db0e20a80a7e0ad";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("cardNo", "320323199609031031");
        querys.put("realName", "彭浩");
        Map<String, String> bodys = new HashMap<String, String>();


        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}


