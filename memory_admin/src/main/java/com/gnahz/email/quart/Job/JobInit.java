package com.gnahz.email.quart.Job;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author 张伟洁
 * Date:2024-02-02-19:25
 * @create 忆项目(小白)
 */
//@Component
public class JobInit {
  /*  @Autowired
    public Scheduler scheduler;

    @PostConstruct
    public void initJOb() throws SchedulerException {
        JobDetail build = JobBuilder.newJob(QQJobBean.class)
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .startNow()
                .build();

        scheduler.scheduleJob(build,trigger);
    }*/
}
