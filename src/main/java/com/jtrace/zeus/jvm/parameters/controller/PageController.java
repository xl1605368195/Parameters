package com.jtrace.zeus.jvm.parameters.controller;

import com.jtrace.zeus.jvm.parameters.mapper.UserMapper;
import com.jtrace.zeus.jvm.parameters.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Date;

import static com.jtrace.zeus.jvm.parameters.ip.SaticScheduleTask.saveIP;

/**
 * @author xule05
 * @date 2019/6/8 下午4:33
 */
@Controller
public class PageController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping(value = {"/index", "/"})
    public String pageIndex(HttpServletRequest request) {
        saveIP(request);
        return "index";
    }

    @GetMapping("/zhenduan")
    public String zhenduan() {
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

    @GetMapping("/introduction")
    public String pageIntroduction() {
        return "/doc/introduction";
    }

    @GetMapping("/examples")
    public String pageExamples() {
        return "/doc/examples";
    }

    @GetMapping("/faq")
    public String pageFaq() {
        return "/doc/faq";
    }

    @GetMapping("/join")
    public String pageJoin() {
        return "/doc/join";
    }
}
