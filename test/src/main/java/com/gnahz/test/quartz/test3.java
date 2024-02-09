package com.gnahz.test.quartz;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @Author 张伟洁
 * Date:2024-02-03-20:37
 * @create 忆项目(小白)
 */
public class test3 {
    public static void main(String[] args) {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println("当前时间： " + now);

        // 添加五分钟
        LocalDateTime fiveMinutesLater = now.plus(4, ChronoUnit.MINUTES);
        System.out.println("五分钟后的时间： " + fiveMinutesLater);

        // 格式化时间为yyyy-MM-dd HH:mm:ss
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedTime = fiveMinutesLater.format(formatter);
        System.out.println("格式化后的时间： " + formattedTime);
    }
}
