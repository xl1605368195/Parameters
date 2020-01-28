package com.jtrace.zeus.jvm.parameters.controller;

import com.jtrace.zeus.jvm.parameters.parameters.JvmParameterEntity;
import com.jtrace.zeus.jvm.parameters.service.ParametersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xule05
 * @date 2020/1/28 上午9:34
 */
public class ModifyJvmParametersController {

    @Autowired
    private ParametersService parametersService;

    /**
     * 修改参数
     */
    @RequestMapping(value = "/modify")
    public ResponseEntity modifyJvmParameters(@RequestParam("parameter")String parameter) {
        JvmParameterEntity parameterEntity = parametersService.getJvmParametersByName(parameter);
        return ResponseEntity.ok(parameterEntity);
    }

    /**
     * 删除参数
     */
    @RequestMapping(value = "/delete")
    public ResponseEntity deleteJvmParameters(@RequestParam("parameter")String parameter) {
        JvmParameterEntity parameterEntity = parametersService.getJvmParametersByName(parameter);
        return ResponseEntity.ok(parameterEntity);
    }

    /**
     * 增加参数
     */
    @RequestMapping(value = "/add")
    public ResponseEntity addJvmParameters(@RequestParam("parameter")String parameter) {
        JvmParameterEntity parameterEntity = parametersService.getJvmParametersByName(parameter);
        return ResponseEntity.ok(parameterEntity);
    }
}
