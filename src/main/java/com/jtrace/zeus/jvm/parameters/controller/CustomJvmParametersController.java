package com.jtrace.zeus.jvm.parameters.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xule05
 * @date 2020/2/16 下午5:34
 */
@Controller
public class CustomJvmParametersController {

    // 自定义参数
    @PostMapping(value = "/custom")
    public ResponseEntity customJvmParameters(@RequestParam("parameters") String parameters, @RequestParam("jdkVersion") String jdkVersion, @RequestParam("totalMem") int totalMem) {
        return null;
    }
}
