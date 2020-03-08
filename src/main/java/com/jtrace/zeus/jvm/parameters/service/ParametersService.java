package com.jtrace.zeus.jvm.parameters.service;

import com.jtrace.zeus.jvm.parameters.mapper.JvmParametersMapper;
import com.jtrace.zeus.jvm.parameters.parameters.JvmParameterEntity;
import com.jtrace.zeus.jvm.parameters.data.ParametersRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xule05
 * @date 2020/1/27 下午7:31
 */
@Service
public class ParametersService {

    @Autowired
    private JvmParametersMapper mapper;

    // 精确匹配
    public JvmParameterEntity getJvmParametersByName(String name) {
        JvmParameterEntity jvmParameterEntity = mapper.selectOneByName(name);
        return jvmParameterEntity;
    }

    // 模糊匹配
    public List<String> matchName(String name) {
        List<String> list = mapper.matchByName(name);
        return list;
    }

    // 模糊匹配+含义
    public List<String> matchNameWithMeaning(String name) {
        List<JvmParameterEntity> list = mapper.matchByNameWithMeaning(name);
        List<String> result = new ArrayList<>(list.size());
        StringBuilder stringBuilder;
        for (JvmParameterEntity tmp : list) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(tmp.getName());
            stringBuilder.append("(");
            stringBuilder.append(tmp.getHanyi());
            stringBuilder.append(")");
            result.add(stringBuilder.toString());
        }
        return result;
    }

    // 增加
    public int addOne() {
        int count = 0;
        for (JvmParameterEntity obj : ParametersRegister.set) {
            mapper.insertOne(obj);
            count++;
        }
        return count;
    }
}
