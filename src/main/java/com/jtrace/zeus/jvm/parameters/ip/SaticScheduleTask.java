package com.jtrace.zeus.jvm.parameters.ip;

import com.jtrace.zeus.jvm.parameters.controller.PageController;
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
    private void task1() {
        logger.warn("累积访问用户量为:" + PageController.IPInfo.size());
        PageController.IPInfo.clear();
        logger.warn("累积访问用户量已经清零");
    }

    // 每个小时执行一次
    @Scheduled(cron = "0 0 0-23 * * ?")
    private void task2() {
        logger.warn("在线用户量为:" + PageController.OnlineInfo.size());
        PageController.OnlineInfo.clear();
        logger.warn("在线用户量已经清零");
    }
}