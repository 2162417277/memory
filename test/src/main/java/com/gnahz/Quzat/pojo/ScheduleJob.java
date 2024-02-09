package com.gnahz.Quzat.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Author 张伟洁
 * Date:2024-02-01-16:39
 * @create 忆项目(小白)
 */
@Data
@TableName("SCHEDULE_JOB")
public class ScheduleJob {

    /**
     * 序号
     */
    @TableId(value = "ID", type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 任务名称
     */
    @TableField("JOB_NAME")
    private String jobName;

    /**
     * 任务分组
     */
    @TableField("JOB_GROUP")
    private String jobGroup;

    /**
     * 任务状态 是否启动任务,2：失效；1：有效
     */
    @TableField("JOB_STATUS")
    private Integer jobStatus;

    /**
     * cron表达式,推荐使用6域的
     */
    @TableField("CRON_EXPRESSION")
    private String cronExpression;

    /**
     * 描述
     */
    @TableField("DESCRIPTION")
    private String description;

    /**
     * 任务执行时调用哪个类的方法 包名+类名，全路径
     */
    @TableField("BEAN_CLASS")
    private String beanClass;

    /**
     * 任务是否有状态
     */
    @TableField("IS_CONCURRENT")
    private Integer isConcurrent;

    /**
     * spring bean 对应定时任务的类名，首字母小写
     */
    @TableField("SPRING_ID")
    private String springId;

    /**
     * 任务调用的方法名
     */
    @TableField("method_name")
    private String methodName;

    @TableField("CREATE_TIME")
    private Date createTime;

    @TableField("UPDATE_TIME")
    private Date updateTime;
}
