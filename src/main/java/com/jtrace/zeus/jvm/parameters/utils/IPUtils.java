package com.jtrace.zeus.jvm.parameters.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xule05
 * @date 2020/3/8 下午10:56
 */
public class IPUtils {

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
}
