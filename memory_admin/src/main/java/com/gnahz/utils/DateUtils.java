package com.gnahz.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author 张伟洁
 * Date:2024-01-11-9:07
 * @create 忆项目(小白)
 */
public class DateUtils {

    /**
     * 解析日期类型
     * @param CharacterString
     * @return
     */
    public static DateTime StringTransformDate(String CharacterString){
        DateTime dateTime = DateUtil.parse(CharacterString);
        return dateTime;
    }

    public static LocalDateTime DataLocalDateTime(String dateTimeStr){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, dateTimeFormatter);
         return dateTime;
    }

}
