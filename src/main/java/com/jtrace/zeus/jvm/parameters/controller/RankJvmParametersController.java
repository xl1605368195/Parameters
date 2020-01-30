package com.jtrace.zeus.jvm.parameters.controller;

import com.jtrace.zeus.jvm.parameters.data.ParametersRankRegister;
import com.jtrace.zeus.jvm.parameters.parameters.JavaInfoEntity;
import com.jtrace.zeus.jvm.parameters.parameters.ParametersRankEntity;
import com.jtrace.zeus.jvm.parameters.service.JavaInfoService;
import com.jtrace.zeus.jvm.parameters.service.ParametersRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @author xule05
 * @date 2020/1/30 下午1:43
 */
@Controller
@RequestMapping(value = "/rank")
public class RankJvmParametersController {

    @Autowired
    private ParametersRankService parametersRankService;

    @Autowired
    private JavaInfoService javaInfoService;

    @RequestMapping(value = "/add")
    public ResponseEntity complementeJvmParameters() {
        List<JavaInfoEntity> allJavaInfo = javaInfoService.getAll();
        for (JavaInfoEntity javaInfoEntity : allJavaInfo) {
            String javaInfo = javaInfoEntity.getJavaInfo();
            System.out.println(javaInfoEntity.getHostname());
            for (Map.Entry<String, ParametersRankEntity> entry : ParametersRankRegister.map.entrySet()) {
                String key = entry.getKey();
                if(javaInfo.contains(key)){
                    ParametersRankEntity mapValue = entry.getValue();
                    mapValue.setUseOffLine(mapValue.getUseOffLine()+1);
                }
            }
        }
        parametersRankService.addOne(ParametersRankRegister.map);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
//
//    @RequestMapping(value = "/info")
//    public ResponseEntity complementeJvmParameters(@RequestParam("hostname") String hostname,@RequestParam("javaInfo") String javaInfo) {
//
//        if (list == null || list.size() == 0) {
//            return new ResponseEntity<>("模糊查询参数[" + parameter + "]不存在!", HttpStatus.OK);
//        }
//        return new ResponseEntity<>(list, HttpStatus.OK);
//    }
}
