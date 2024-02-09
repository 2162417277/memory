package com.gnahz.config.note;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author 张伟洁
 * Date:2024-02-04-13:53
 * @create 忆项目(小白)
 * 接口防刷自定义注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AccessLimit {
    //时间(单位秒)
    int seconds();
    //最大访问次数
    int maxCount();
    boolean needLogin() default true;

}
