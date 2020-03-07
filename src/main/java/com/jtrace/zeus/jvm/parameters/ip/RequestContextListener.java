package com.jtrace.zeus.jvm.parameters.ip;

import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Component
public class RequestContextListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        ServletRequest servletRequest = sre.getServletRequest();
        //获取ip,存入ServletContext
        String ip = servletRequest.getRemoteAddr();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //获取session
        HttpSession session = request.getSession();
        session.setAttribute("ip", ip);
        ServletContext servletContext = sre.getServletContext();
        Map<String, String> userIp = (Map<String, String>) servletContext.getAttribute("userIp");
        userIp.put(ip, new Date().toString());
        Map<String, Set<HttpSession>> userMap = (Map<String, Set<HttpSession>>) servletContext.getAttribute("userMap");
        Set<HttpSession> sessionList = userMap.containsKey(ip) ? userMap.get(ip) : new HashSet<>();
        if (!sessionList.contains(session)) {
            sessionList.add(session);
        }
        userMap.put(ip, sessionList);
    }
}