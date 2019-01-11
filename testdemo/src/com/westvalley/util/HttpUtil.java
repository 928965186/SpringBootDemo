package com.westvalley.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
	/**
     * Do POST request
     * @param url
     * @param parameterMap
     * @return
     * @throws Exception 
     */
    public static String doPost(String urlTem,String obj) throws Exception {
        URL localURL = new URL(urlTem);
        
        HttpURLConnection conn = (HttpURLConnection) localURL.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection)conn;
        
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Accept-Charset", "UTF-8");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(obj.length()));
        
        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;
        
        try {
            outputStream = httpURLConnection.getOutputStream();
            outputStreamWriter = new OutputStreamWriter(outputStream);
            outputStreamWriter.write(obj.toString());
            outputStreamWriter.flush();
            //响应失败
            if (httpURLConnection.getResponseCode() >= 300) {
                throw new Exception("钉钉连接异常" + httpURLConnection.getResponseCode());
            }
            //接收响应�?
            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
            reader = new BufferedReader(inputStreamReader);
            
            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }
            
        }catch (Exception e) {
		} finally {
            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }
            
            if (outputStream != null) {
                outputStream.close();
            }
            
            if (reader != null) {
                reader.close();
            }
            
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return resultBuffer.toString();
    }
	
	
	/**
     * Do GET request
     * @param urlTem
     * @return
     * @throws Exception
     * @throws IOException
     */
    public static String doGet(String urlTem) throws Exception {
    	// 创建url资源
        URL url = new URL(urlTem);
        // 建立http连接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection)conn;
        
        httpURLConnection.setRequestProperty("Accept-Charset", "UTF-8");
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;
        //响应失败
        if (httpURLConnection.getResponseCode() >= 300) {
            throw new Exception("钉钉请求url失败�?" + httpURLConnection.getResponseCode());
        }
        try {
            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
            reader = new BufferedReader(inputStreamReader);
            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (inputStreamReader != null) {
                inputStreamReader.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return resultBuffer.toString();
    }
}
