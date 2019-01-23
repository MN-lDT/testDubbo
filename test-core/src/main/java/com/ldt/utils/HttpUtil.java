package com.ldt.utils;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

    private static RestTemplate restTemplate = new RestTemplate();
    public static org.apache.log4j.Logger logger = Logger.getLogger(HttpUtil.class);
    /***
     * 利用spring的rest模板发送json格式到指定的url
     * 返回值json串
     */
    public static String sendJson(String url,String reqJsonStr){
        logger.info("发送的json内容为:"+reqJsonStr);
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
            headers.setConnection("close");
            HttpEntity<String> entity = new HttpEntity<>(reqJsonStr,headers);
            ResponseEntity<String> result = restTemplate.postForEntity(url,entity,String.class);
            logger.info("返回的内容为："+result.getBody());
            return result.getBody();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static  String httpURLConntJson(String URIP, String param) {
        StringBuffer sb = new StringBuffer();
        HttpURLConnection httpURLConnection=null;
        InputStream iin=null;
        try {
            URL url = new URL(URIP);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setConnectTimeout(10000*10);
            httpURLConnection.setReadTimeout(10000*10);
            httpURLConnection.setRequestProperty("content-type", "application/json;charset=UTF-8");
            httpURLConnection.getOutputStream().write(param.getBytes("utf-8"));
            httpURLConnection.getOutputStream().flush();
            httpURLConnection.getOutputStream().close();

            httpURLConnection.connect();
            iin = httpURLConnection.getInputStream();
            byte[] buffer = new byte[2048];
            int length = 0;

            while ((length = iin.read(buffer, 0, buffer.length)) != -1) {
                sb.append(new String(buffer, 0, length, "utf-8"));
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }finally{
            try{
                if(iin!=null){
                    iin.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return sb.toString();
    }
}
