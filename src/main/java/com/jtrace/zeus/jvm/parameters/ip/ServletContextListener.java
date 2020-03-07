package com.jtrace.zeus.jvm.parameters.ip;

import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ServletContextListener implements javax.servlet.ServletContextListener {
    private ConcurrentHashMap<String, List<HttpSession>> userMap = new ConcurrentHashMap<>();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("userMap", userMap);
    }
}
