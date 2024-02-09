package com.gnahz.test.quartz;

/**
 * @Author 张伟洁
 * Date:2024-02-01-15:13
 * @create 忆项目(小白)
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CronToDate {
    public static void main(String[] args) {
        // Cron表达式
        String cronExpression = "00 30 10 01 01 ? 2022";

        // 将Cron表达式转换为Java日期对象
        try {
            Calendar date = convertCronToDate(cronExpression);
            System.out.println("Java日期对象：" + date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static Calendar convertCronToDate(String cronExpression) throws ParseException {
        // 解析Cron表达式的各个字段
        String[] fields = cronExpression.split(" ");
        int second = Integer.parseInt(fields[0]);
        int minute = Integer.parseInt(fields[1]);
        int hour = Integer.parseInt(fields[2]);
        int dayOfMonth = Integer.parseInt(fields[3]);
        int month = Integer.parseInt(fields[4]) - 1; // 月份从0开始计数
        int year = Integer.parseInt(fields[6]);

        // 创建一个Java日期对象
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth, hour, minute, second);

        return calendar;
    }
}
