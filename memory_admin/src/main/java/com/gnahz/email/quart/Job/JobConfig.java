package com.gnahz.email.quart.Job;

import com.gnahz.email.quart.Job.Bean.MySqlJobBean;
import com.gnahz.email.quart.Job.Bean.QQJobBean;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 张伟洁
 * Date:2024-02-02-19:25
 * @create 忆项目(小白)
 */
@Configuration
public class JobConfig {

    @Bean
    public JobDetail springQQJobDetail(){
        return JobBuilder.newJob(QQJobBean.class)
                .withIdentity("springQQJobDetail")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger springQQJobTrigger(){
        return TriggerBuilder.newTrigger()
                .forJob("springQQJobDetail")
                //.startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0 * * * * ? *")) //不能写过期的时间(每分钟执行一次)
                .build();
    }



    @Bean
    public JobDetail springMysqlJobDetail(){
        return JobBuilder.newJob(MySqlJobBean.class)
                .withIdentity("springMysqlJobDetail")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger springMysqlJobTrigger(){
        return TriggerBuilder.newTrigger()
                .forJob("springMysqlJobDetail")
                //.startNow()    0 0 0 1 1/2 ?   40 15 8 * * ? *
                .withSchedule(CronScheduleBuilder.cronSchedule("40 20 22 * * ? *")) //不能写过期的时间(每两个月执行一次)
                .build();
    }
}
