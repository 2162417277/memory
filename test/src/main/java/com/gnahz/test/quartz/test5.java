package com.gnahz.test.quartz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author 张伟洁
 * Date:2024-02-04-10:41
 * @create 忆项目(小白)
 */
public class test5 {
    public static void main(String[] args) {
        String input = "2024-02-04 10:37:00";
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat outputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

        try {
            Date date = inputFormat.parse(input);
            String output = outputFormat.format(date);
            System.out.println(output);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
