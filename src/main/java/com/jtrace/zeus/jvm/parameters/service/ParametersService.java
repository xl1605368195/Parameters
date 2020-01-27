package com.jtrace.zeus.jvm.parameters.service;

import com.jtrace.zeus.jvm.parameters.mapper.JvmParametersMapper;
import com.jtrace.zeus.jvm.parameters.parameters.JvmParameterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xule05
 * @date 2020/1/27 下午7:31
 */
@Service
public class ParametersService {

    @Autowired
    private JvmParametersMapper mapper;

    public JvmParameterEntity getJvmParametersByName(String name) {
        JvmParameterEntity jvmParameterEntity = mapper.selectOneByName(name);
        return jvmParameterEntity;
    }
}
