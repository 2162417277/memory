package com.gnahz.email.quart.config;

/**
 * @Author 张伟洁
 * Date:2024-02-01-20:04
 * @create 忆项目(小白)
 */

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  cron工具
 * @Author: yjw 2021-12-17
 * @return:
 */
public class CronUtil {


    /**
     *  获取cron
     * @Author: yjw 2021-12-17
     * @return:
     */
    public static String getCron(Date date){
        String dateformat = "ss mm HH dd MM ? yyyy";
        return formatDateByPattern(date, dateformat);
    }

    /**
     *  日期转cron
     * @Author: yjw 2021-12-17
     * @return:
     */
    public static String formatDateByPattern(Date date, String dateformat){
        SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
        String timeStr = null;
        if (date != null){
            timeStr = sdf.format(date);
        }
        return timeStr;
    }

    public static void main(String[] args) {
        String a = getCron(new Date());
        System.err.println("cron:" + a);
    }
}
