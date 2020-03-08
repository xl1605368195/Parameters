package com.jtrace.zeus.jvm.parameters.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author xule05
 * @date 2019/6/8 下午4:33
 */
@Controller
public class PageController {

    @GetMapping(value = {"/index", "/"})
    public String pageIndex() {
        // 首页
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
