package com.jtrace.zeus.jvm.parameters.controller;

import com.jtrace.zeus.jvm.parameters.parameters.JavaInfoEntity;
import com.jtrace.zeus.jvm.parameters.service.JavaInfoService;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xule05
 * @date 2020/1/30 下午2:04
 */
@Controller
@RequestMapping("/java")
public class JavaInfoController {

    @Autowired
    private JavaInfoService javaInfoService;

    /**
     * 增加javainfo信息
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity modifyJvmParameters(@RequestParam("hostname") String hostname, @RequestParam("javaInfo") String javaInfo) {
        if (StringUtils.isNullOrEmpty(hostname) || StringUtils.isNullOrEmpty(javaInfo)) {
            return ResponseEntity.ok("hostname is null");
        }
        if (StringUtils.isNullOrEmpty(javaInfo)) {
            return ResponseEntity.ok("javaInfo is null");
        }
        if (javaInfoService.isExitHostName(hostname)) {
            return ResponseEntity.ok("数据重复了");
        }
        String env = hostname.contains("test") ? "test" : "prod";
        JavaInfoEntity javaInfoEntity = new JavaInfoEntity(hostname, javaInfo, env);
        int flag = javaInfoService.addOne(javaInfoEntity);
        if (flag == 0) {
            return ResponseEntity.ok("新增失败");
        }
        return ResponseEntity.ok("新增成功");
    }
}
