package com.gnahz.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author 张伟洁
 * Date:2024-01-11-9:40
 * @create 忆项目(小白)
 */
public class TimeStampUtils {

    /**
     * 转换为时间戳（以秒为单位）
     * @param time
     * @return
     */
    public static Long TimeStamp(String time){
        Long beginUseTime = null;
        try {
          //  String bookBeginusetime = "20160-03-17 15:55";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            beginUseTime = sdf.parse(time).getTime()/1000;
            //Long beginUseTime1 = sdf.parse(bookBeginusetime).getTime();
            //Long beginUseTime2 = sdf.parse(bookBeginusetime).getTime()/1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return beginUseTime;
    }

    public static String DateStamp(Long Stamp){
        Date date = new Date(Stamp * 1000); // 将时间戳转换为Date对象

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); // 设置日期格式
        String formattedDate = sdf.format(date); // 将Date对象转换为格式化的字符串
        return formattedDate;
    }


}
