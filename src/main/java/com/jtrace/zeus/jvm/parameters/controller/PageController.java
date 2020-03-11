package com.jtrace.zeus.jvm.parameters.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

import static com.jtrace.zeus.jvm.parameters.ip.SaticScheduleTask.saveIP;

/**
 * @author xule05
 * @date 2019/6/8 下午4:33
 */
@Controller
public class PageController {

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

    @GetMapping("/weixin")
    public String pageWeixin() {
        return "/doc/weixin";
    }

    @GetMapping("/fangwen")
    public String pageFangwen() {
        return "/doc/fangwen";
    }

    @GetMapping("/faq")
    public String pageFaq() {
        return "/doc/faq";
    }

    @GetMapping("/examples")
    public String pageExamples() {
        return "/doc/examples";
    }

    @GetMapping("/introduction")
    public String pageIntroduction() {
        return "/doc/introduction";
    }

}
