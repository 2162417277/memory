package com.gnahz.common;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author 张伟洁
 * Date:2024-02-04-14:31
 * @create 忆项目(小白)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface RateLimiting {
    /**
     * 资源的key,唯一
     * 作用：不同的接口，不同的流量控制
     */
    String key() default "";

    /**
     * 最多的访问限制次数
     */
    double permitsPerSecond();

    /**
     * 获取令牌最大等待时间
     */
    long timeout();

    /**
     * 获取令牌最大等待时间,单位(例:分钟/秒/毫秒) 默认:毫秒
     * NANOSECONDS - 纳秒
     * MICROSECONDS - 微秒
     * MILLISECONDS - 毫秒
     * SECONDS - 秒
     * MINUTES - 分钟
     * HOURS - 小时
     * DAYS - 天
     */
    TimeUnit timeunit() default TimeUnit.MILLISECONDS;

    /**
     * 得不到令牌的提示语
     */
    String msg() default "系统繁忙,请稍后再试.";
}
