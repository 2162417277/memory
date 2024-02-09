package com.gnahz.email.quart.Job.Bean;

import com.gnahz.email.service.impl.QQEmailServiceImpl;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author 张伟洁
 * Date:2024-02-02-15:35
 * @create 忆项目(小白)
 */
public class QQJobBean extends QuartzJobBean {
//obs
    @Autowired
    private QQEmailServiceImpl qqEmailService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = format.format(date);
        System.out.println("现在时间为:"+format1);
        System.out.println("QQJobBean开始任务");
        qqEmailService.sendCommonEmaill(format1);
    }
}
