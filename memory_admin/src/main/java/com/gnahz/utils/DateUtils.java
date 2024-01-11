package com.gnahz.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

/**
 * @Author 张伟洁
 * Date:2024-01-11-9:07
 * @create 忆项目(小白)
 */
public class DateUtils {

    public static DateTime StringTransformDate(String CharacterString){
        DateTime dateTime = DateUtil.parse(CharacterString);
        return dateTime;
    }

}
