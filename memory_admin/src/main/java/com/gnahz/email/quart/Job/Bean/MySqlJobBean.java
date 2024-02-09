package com.gnahz.email.quart.Job.Bean;


import com.gnahz.service.impl.GrowServiceImpl;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author 张伟洁
 * Date:2024-02-03-20:58
 * @create 忆项目(小白)
 */
public class MySqlJobBean extends QuartzJobBean {

    @Autowired
    GrowServiceImpl growServiceImpl;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = format.format(date);
        System.out.println("现在时间为:"+format1);
        System.out.println("数据库查询开始任务");
        growServiceImpl.InsertMysqlNewDate();
    }
}
