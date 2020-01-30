package com.jtrace.zeus.jvm.parameters.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xule05
 * @date 2020/1/30 下午11:48
 */
@Controller
public class EvaluateController {

    /**
     *   参数打分
      */
    @RequestMapping(value = "/evaluate")
    public ResponseEntity complementeJvmParameters(@RequestParam("parameters") String parameters) {

        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
