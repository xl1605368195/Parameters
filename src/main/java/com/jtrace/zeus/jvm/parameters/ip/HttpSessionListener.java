package com.jtrace.zeus.jvm.parameters.ip;

import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Component
public class HttpSessionListener implements javax.servlet.http.HttpSessionListener {
    /**
     * session失效后执行
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        String ip = (String) session.getAttribute("ip");
        ServletContext servletContext = session.getServletContext();
        //获取userMap
        Map<String, Set<HttpSession>> userMap = (Map<String, Set<HttpSession>>) servletContext.getAttribute("userMap");
        Set<HttpSession> sessions = userMap.get(ip);
        sessions.remove(session);
        //如果sessionList的长度为0,说明没有以这个ip访问的用户已经下线,从user,map中移出ip。
        if (sessions.size() == 0) {
            userMap.remove(ip);
        } else {
            userMap.put(ip, sessions);
        }
    }
}