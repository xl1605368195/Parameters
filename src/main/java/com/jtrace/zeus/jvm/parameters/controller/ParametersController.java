package com.jtrace.zeus.jvm.parameters.controller;

import com.jtrace.zeus.jvm.parameters.parameters.JvmParameterEntity;
import com.jtrace.zeus.jvm.parameters.parameters.ParametersRegister;
import com.jtrace.zeus.jvm.parameters.service.ParametersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xule05
 * @date 2020/1/27 下午7:30
 */
@Controller
public class ParametersController {

    @Autowired
    private ParametersService parametersService;

    /**
     * 补全参数
     */
    @RequestMapping(value = "/complemente")
    public ResponseEntity complementeJvmParameters(@RequestParam("parameter")String parameter) {
        return ResponseEntity.ok(ParametersRegister.set);
    }

    /**
     * 查询含义
     */
    @RequestMapping(value = "/query")
    public ResponseEntity queryJvmParameters(@RequestParam("parameter")String parameter) {
        JvmParameterEntity parameterEntity = parametersService.getJvmParametersByName(parameter);
        return ResponseEntity.ok(parameterEntity);
    }
}
