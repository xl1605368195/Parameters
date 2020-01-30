package com.jtrace.zeus.jvm.parameters.service;

import com.jtrace.zeus.jvm.parameters.mapper.JavaInfoMapper;
import com.jtrace.zeus.jvm.parameters.parameters.JavaInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xule05
 * @date 2020/1/30 下午2:03
 */
@Service
public class JavaInfoService {
    @Autowired
    private JavaInfoMapper mapper;

    public int addOne(JavaInfoEntity javaInfoEntity) {
        return mapper.insertOne(javaInfoEntity);
    }

    public boolean isExitHostName(String hostname) {
        JavaInfoEntity javaInfoEntity = mapper.selectOneByName(hostname);
        return !(javaInfoEntity == null || "".equals(javaInfoEntity.getEnv()));
    }

    public List<JavaInfoEntity> getAll() {
        List<JavaInfoEntity> list = mapper.selectAll();
        return list;
    }
}
