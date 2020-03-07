package com.jtrace.zeus.jvm.parameters.ip;

import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ServletContextListener implements javax.servlet.ServletContextListener {
    // session
    public static ConcurrentHashMap<String, Set<HttpSession>> userMap = new ConcurrentHashMap<>();
    // ip
    public static ConcurrentHashMap<String, String> userIp = new ConcurrentHashMap<>();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("userMap", userMap);
        sce.getServletContext().setAttribute("userIp", userIp);
    }
}
