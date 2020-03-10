package com.jtrace.zeus.jvm.parameters.service;

import com.jtrace.zeus.jvm.parameters.mapper.UserMapper;
import com.jtrace.zeus.jvm.parameters.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xule05
 * @date 2020/3/10 上午11:04
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public int addOne(User user) {
        return userMapper.insertOne(user);
    }
}
