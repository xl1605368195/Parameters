package com.jtrace.zeus.jvm.parameters.controller;

import com.jtrace.zeus.jvm.parameters.parameters.JvmParameterEntity;
import com.jtrace.zeus.jvm.parameters.service.ParametersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author xule05
 * @date 2020/1/27 下午7:30
 */
@Controller
public class SearchJvmParametersController {

    private final static Logger logger = LoggerFactory.getLogger(SearchJvmParametersController.class);

    @Autowired
    private ParametersService parametersService;

    /**
     * 自动补全参数
     */
    @RequestMapping(value = "/complemente")
    public ResponseEntity complementeJvmParameters(@RequestParam("parameter") String parameter) {
        parameter = parameter.trim();
        List<String> list = parametersService.matchName(parameter);
        if (list == null || list.size() == 0) {
            logger.warn("模糊查询参数[" + parameter + "]不存在!", HttpStatus.OK);
            return new ResponseEntity<>("模糊查询参数[" + parameter + "]不存在!", HttpStatus.OK);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 查询含义
     */
    @RequestMapping(value = "/query")
    public ResponseEntity queryJvmParameters(@RequestParam("parameter") String parameter) {
        parameter = parameter.trim();
        JvmParameterEntity parameterEntity = parametersService.getJvmParametersByName(parameter);
        if (parameterEntity == null || "".equals(parameterEntity.getName())) {
            logger.warn("查询参数[" + parameter + "]不存在!", HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>("查询参数[" + parameter + "]不存在!", HttpStatus.BAD_REQUEST);
        }
        logger.info("parameterEntity:" + parameterEntity.toString());
        return new ResponseEntity<>(parameterEntity, HttpStatus.OK);
    }

    /**
     * 增加
     */
    @RequestMapping(value = "/add")
    public ResponseEntity addJvmParameters() {
        int count = parametersService.addOne();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
