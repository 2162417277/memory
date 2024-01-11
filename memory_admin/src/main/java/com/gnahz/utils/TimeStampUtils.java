package com.gnahz.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @Author 张伟洁
 * Date:2024-01-11-9:40
 * @create 忆项目(小白)
 */
public class TimeStampUtils {

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


}
