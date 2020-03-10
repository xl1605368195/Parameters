package com.jtrace.zeus.jvm.parameters.ip;

import com.jtrace.zeus.jvm.parameters.mapper.UserMapper;
import com.jtrace.zeus.jvm.parameters.user.User;
import com.jtrace.zeus.jvm.parameters.utils.IPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.jtrace.zeus.jvm.parameters.utils.IPUtils.getLoctionByIP;


@Configuration
@EnableScheduling
public class SaticScheduleTask {

    private final static Logger logger = LoggerFactory.getLogger(SaticScheduleTask.class);

    private static ConcurrentHashMap<String, Date> IPInfo = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<String, Date> OnlineInfo = new ConcurrentHashMap<>();

    @Autowired
    private UserMapper userMapper;

    /**
     * 每天23点执行一次
     */
    @Scheduled(cron = "0 0 23 * * ?")
    private void task1() {
        try {
            int number = IPInfo.size();
            logger.warn("累积访问用户量为:" + number);
            ArrayList<User> result = new ArrayList<>(number);
            // 整理获取用户数据:ip地址,归属地,登录时间
            for (Map.Entry<String, Date> entity : IPInfo.entrySet()) {
                Date time = entity.getValue();
                String ip = entity.getKey();
                String location = getLoctionByIP(ip);
                User user = new User(ip, time, location);
                result.add(user);
            }
            IPInfo.clear();
            logger.warn("累积访问用户量已经清零");
            // 批量插入数据库
            userMapper.batchInsert(result);
            logger.warn("批量插入数据成功");
        } catch (Exception e) {
            logger.warn("批量插入IP任务异常", e);
        }
    }

    /**
     * 每个小时执行一次
     */
    @Scheduled(cron = "0 0 0-23 * * ?")
    private void task2() {
        try {
        logger.warn("在线用户量为:" + OnlineInfo.size());
        OnlineInfo.clear();
        logger.warn("在线用户量已经清零");
        } catch (Exception e) {
            logger.warn("定时清除IP任务异常", e);
        }
    }

    public static void saveIP(HttpServletRequest request) {
        // 获取用户IP
        String ip = IPUtils.getIP(request);
        String location = getLoctionByIP(ip);
        Date time = new Date();
        // 在线用户(24小时统计一次)
        if (!IPInfo.containsKey(ip)) {
            IPInfo.put(ip, time);
            logger.info(location+" IP[" + ip + "]用户登录了,今日访问总人数[" + IPInfo.size() + "]");
        }
        // 在线用户(1小时统计一次)
        if (!OnlineInfo.containsKey(ip)) {
            OnlineInfo.put(ip, time);
            logger.info(location+" IP[" + ip + "]用户登录了,当前在线人数[" + IPInfo.size() + "]");
        }
        HttpSession session = request.getSession();
        session.setAttribute("total", IPInfo.size());
        session.setAttribute("online", OnlineInfo.size());
    }
}