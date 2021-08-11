package com.netease.nlp.module;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

public abstract class NlpBase {
    private String serverHost = "http://dm.netease.com/nlp";//域名
    private static int connTimeOut = 5000;
    protected String requestMethod = "GET";
    protected String actionMethod = "";

    public String getUrl() {
        return this.serverHost + this.actionMethod;
    }

    public String sendRequest(String url, Map<String, Object> requestParams, String requestMethod) {
        String result = "";
        BufferedReader in = null;
        URLConnection connection = null;

        try {
            URL realUrl = new URL(url);
            if (url.toLowerCase().startsWith("https")) {
                HttpsURLConnection httpsConn = (HttpsURLConnection) realUrl.openConnection();

                httpsConn.setHostnameVerifier(new HostnameVerifier() {
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
                connection = httpsConn;
            } else {
                connection = realUrl.openConnection();
            }

            StringBuffer strBuf = new StringBuffer();
            if (requestMethod.equals("POST")) {
                ((HttpURLConnection) connection).setRequestMethod("POST");
                // 设置链接主机超时时间
                connection.setConnectTimeout(connTimeOut);
                connection.setDoOutput(true);
                connection.setDoInput(true);
                
                OutputStream out = new DataOutputStream(connection.getOutputStream());
                if (requestParams != null && !requestParams.isEmpty()) {
                    for (Entry<String, Object> entry : requestParams.entrySet()) {
                        try {
                            strBuf.append(entry.getKey()).append("=")
                                    .append(URLEncoder.encode((String) entry.getValue(), "UTF-8")).append("&");

                        } catch (UnsupportedEncodingException e) {
                            result =
                                "{\"code\":1,\"body\":\"sdk 报错！\",\"message\":\"BusinessException "
                                        + e.toString() + "\"}";
                        }
                    }
                }
                strBuf.deleteCharAt(strBuf.length() - 1);

                // 获得上传信息的字节大小及长度
                byte[] mydata = strBuf.toString().getBytes();

                out.write(mydata);
                out.flush();
                out.close();
            }

            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            
//        } catch (FileNotFoundException e) {
//            result =
//                "{\"code\":1,\"body\":\"HTTP 地址有误！\",\"message\":\"请检查访问的地址: "
//                        + e.getMessage() + "\"}";
        }catch (Exception e) {
            result =
                    "{\"code\":1,\"body\":\"sdk 报错！\",\"message\":\"BusinessException "
                            + e.toString() + "\"}";
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                result =
                        "{\"code\":1,\"body\":\"sdk 报错！\",\"message\":\"BusinessException "
                                + e.toString() + "\"}";
            }
        }
        return result;
    }
}
