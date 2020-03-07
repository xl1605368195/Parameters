package com.jtrace.zeus.jvm.parameters.ip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@Configuration
@EnableScheduling
public class SaticScheduleTask {

    private final static Logger logger = LoggerFactory.getLogger(SaticScheduleTask.class);

    // 每天23点执行一次
    @Scheduled(cron = "0 0 23 * * ?")
    private void configureTasks() {
        logger.warn("访问用户量为:" + ServletContextListener.userIp.size());
        ServletContextListener.userIp.clear();
    }
}