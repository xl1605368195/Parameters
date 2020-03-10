package com.jtrace.zeus.jvm.parameters.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.jtrace.zeus.jvm.parameters.user.IPInfoEntity;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * @author xule05
 * @date 2020/3/8 下午10:56
 */
public class IPUtils {

    private final static Logger logger = LoggerFactory.getLogger(IPUtils.class);

    public static String getIP(HttpServletRequest request) {
        //获取用户登录ip;
        String loginIp = request.getHeader("X-Forwarded-For");
        if (loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) {
            loginIp = request.getHeader("X-Real-IP");
        }
        if (loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) {
            loginIp = request.getHeader("Proxy-Client-IP");
        }
        if (loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) {
            loginIp = request.getHeader("WL-Proxy-Client-IP");
        }
        if (loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) {
            loginIp = request.getHeader("HTTP_CLIENT_IP");
        }
        if (loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) {
            loginIp = request.getRemoteAddr();
        }
        return loginIp;
    }

    public static String getLoctionByIP(String ip) {
        String location = "";
        // 创建客户端
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //创建一个get请求
        HttpGet httpget = new HttpGet("http://whois.pconline.com.cn/ipJson.jsp?ip=" + ip + "&json=true");
        CloseableHttpResponse response = null;
        try {
            //发送请求并获取返回结果
            response = httpclient.execute(httpget);
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                HttpEntity httpEntity = response.getEntity();
                String result = EntityUtils.toString(httpEntity, "UTF-8");
                IPInfoEntity ipInfoEntity = JSON.parseObject(result, IPInfoEntity.class);
                return ipInfoEntity.getPro()+" "+ipInfoEntity.getCity();
            }
        } catch (IOException e) {
            logger.warn("发送请求出现异常", e);
        } finally {
            try {
                if (response != null) {
                    response.close(); //释放连接
                }
            } catch (Exception e) {
                logger.warn("释放连接出现异常", e);
            }
            try {
                if (httpclient != null) {
                    httpclient.close();//关闭客户端
                }
            } catch (Exception e) {
                logger.warn("关闭客户端出现异常", e);
            }
        }
        return location;
    }
}
