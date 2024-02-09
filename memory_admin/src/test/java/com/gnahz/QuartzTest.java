package com.gnahz;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

/**
 * @Author 张伟洁
 * Date:2024-02-02-14:38
 * @create 忆项目(小白)
 */
@SpringBootTest(classes = {StartApp.class})//测试方法
@Slf4j//日志
public class QuartzTest {








    @Test
    public void test(){
        try {
            Scheduler defaultScheduler = StdSchedulerFactory.getDefaultScheduler();
            defaultScheduler.start();

//            JobDetail jobDetail = JobBuilder.newJob(QQJob.class)
//                    .usingJobData("jobDetail-key-1", "jobDetail-value-1")
//                    .withIdentity("job1", "group1")
//                    .build();

            /**
             * 优先级高
             */
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1", "group1")
                    .usingJobData("trigger-key-1","trigger-value-1")
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatSecondlyForever(10))
                    .build();

            //defaultScheduler.scheduleJob(jobDetail,trigger);

            TimeUnit.SECONDS.sleep(3000);
            defaultScheduler.shutdown();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {

        try {
            Scheduler defaultScheduler = StdSchedulerFactory.getDefaultScheduler();
            defaultScheduler.start();

//            JobDetail jobDetail = JobBuilder.newJob(QQJob.class)
//                    .usingJobData("jobDetail-key-1", "jobDetail-value-1")
//                    .withIdentity("job1", "group1")
//                    .build();

            /**
             * 优先级高
             */
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1", "group1")
                    .usingJobData("trigger-key-1","trigger-value-1")
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatSecondlyForever(10))
                    .build();

            //defaultScheduler.scheduleJob(jobDetail,trigger);

            TimeUnit.SECONDS.sleep(3000);
            defaultScheduler.shutdown();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
