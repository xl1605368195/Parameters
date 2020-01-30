package com.jtrace.zeus.jvm.parameters.service;

import com.jtrace.zeus.jvm.parameters.mapper.ParametersRankMapper;
import com.jtrace.zeus.jvm.parameters.parameters.ParametersRankEntity;
import com.jtrace.zeus.jvm.parameters.data.ParametersRankRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author xule05
 * @date 2020/1/30 下午1:17
 */
@Service
public class ParametersRankService {
    @Autowired
    private ParametersRankMapper mapper;

    public int addOne(HashMap<String, ParametersRankEntity> map) {
        // 增加
        int count = 0;
        for (ParametersRankEntity value : map.values()) {
            mapper.insertOne(value);
            count++;
        }
        return count;
    }

}
