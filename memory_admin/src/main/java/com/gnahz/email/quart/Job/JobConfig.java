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
                //.startNow()0 * * * * ? *
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
                //.startNow()    0 0 0 1 1/1 ?   40 15 8 * * ? *
                .withSchedule(CronScheduleBuilder.cronSchedule("3 0 0 1 1/1 ?")) //不能写过期的时间(每一个月执行一次)
                .build();
    }





    @Bean
    public JobDetail springStartOnceMysqlJobDetail(){
        return JobBuilder.newJob(MySqlJobBean.class)
                .withIdentity("springStartOnceMysqlJobDetail")
                .storeDurably()
                .build();
    }
    @Bean
    public Trigger springStartOnceMysqlJobTrigger(){
        return TriggerBuilder.newTrigger()
                .forJob("springStartOnceMysqlJobDetail")
                .startNow() // 立即触发
                .build();
    }
}
