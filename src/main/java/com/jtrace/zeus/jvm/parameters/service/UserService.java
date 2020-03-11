package com.jtrace.zeus.jvm.parameters.service;

import com.jtrace.zeus.jvm.parameters.dao.GroupByDate;
import com.jtrace.zeus.jvm.parameters.mapper.UserMapper;
import com.jtrace.zeus.jvm.parameters.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    public List<GroupByDate> getDailyCount(int limitCount) {
        List<GroupByDate> groupByDates = userMapper.groupByDay(limitCount);
        return groupByDates;
    }
}
