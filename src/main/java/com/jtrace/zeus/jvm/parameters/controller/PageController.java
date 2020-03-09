package com.jtrace.zeus.jvm.parameters.controller;

import com.jtrace.zeus.jvm.parameters.utils.IPUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xule05
 * @date 2019/6/8 下午4:33
 */
@Controller
public class PageController {
    private final static Logger logger = LoggerFactory.getLogger(PageController.class);

    public static ConcurrentHashMap<String, String> IPInfo = new ConcurrentHashMap<>();

    public static ConcurrentHashMap<String, String> OnlineInfo = new ConcurrentHashMap<>();

    @GetMapping(value = {"/index", "/"})
    public String pageIndex(HttpServletRequest request) {
        // 获取用户IP
        String ip = IPUtils.getIP(request);
        String time = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        // 在线用户(24小时统计一次)
        if (!IPInfo.containsKey(ip)) {
            IPInfo.put(ip, time);
            logger.info(time + " IP[" + ip + "]用户登录了,今日访问总人数[" + IPInfo.size() + "]");
        }
        // 在线用户(1小时统计一次)
        if (!OnlineInfo.containsKey(ip)) {
            OnlineInfo.put(ip, time);
            logger.info(time + " IP[" + ip + "]用户登录了,当前在线人数[" + IPInfo.size() + "]");
        }
        HttpSession session = request.getSession();
        session.setAttribute("total", IPInfo.size());
        session.setAttribute("online", OnlineInfo.size());
        return "index";
    }

    @GetMapping("/zhenduan")
    public String zhenduan() {
        // 诊断
        return "zhenduan";
    }

    @GetMapping("/dingzhi")
    public String dingzhi() {
        // 定制
        return "dingzhi";
    }

    @GetMapping("/remen")
    public String remen() {
        // 热门
        return "remen";
    }
}
