package com.jtrace.zeus.jvm.parameters.controller;

import com.jtrace.zeus.jvm.parameters.rules.CheckoutResult;
import com.jtrace.zeus.jvm.parameters.service.ParametersEvaluateService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author xule05
 * @date 2020/1/30 下午11:48
 */
@Controller
public class EvaluateController {

    @Autowired
    private ParametersEvaluateService evaluateService;

    /**
     * 参数打分
     */
    @PostMapping(value = "/evaluate")
    public ResponseEntity complementeJvmParameters(@RequestParam("parameters") String parameters, @RequestParam("jdkVersion") String jdkVersion,@RequestParam("totalMem") int totalMem) {
        if (StringUtils.isBlank(parameters)) {
            return ResponseEntity.ok("parameters is null.");
        }
        if (StringUtils.isBlank(jdkVersion)) {
            return ResponseEntity.ok("jdkVersion is null.");
        }
        // 参数分解
        HashMap<String, String> parametersValuesMap = evaluateService.splitParameters(parameters);
        // 参数检查
        List<CheckoutResult> results = evaluateService.checkParameters(parametersValuesMap, jdkVersion,totalMem);
        for (CheckoutResult checkoutResult : results) {
            System.out.println(checkoutResult.toString());
        }
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
