package com.gnahz.test.quartz;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @Author 张伟洁
 * Date:2024-02-01-15:11
 * @create 忆项目(小白)
 */
public class test1 {
    public static void main(String[] args) {
        // 创建一个Java日期对象2024-02-03 20:34:23
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.FEBRUARY, 3, 20, 42, 51);

        // 将Java日期对象转换为Cron表达式
        String cronExpression = convertDateToCron(calendar);
        System.out.println("Cron表达式： " + cronExpression);
    }

    public static String convertDateToCron(Calendar date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("ss mm HH dd MM ? yyyy");
        return dateFormat.format(date.getTime());
    }
}
