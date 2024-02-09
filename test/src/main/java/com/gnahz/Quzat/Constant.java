package com.gnahz.Quzat;

/**
 * @Author 张伟洁
 * Date:2024-02-01-16:43
 * @create 忆项目(小白)
 */
public final class Constant {

    private Constant() {
    }

    // 定时任务运行状态：运行
    public static final Integer STATUS_RUNNING = 1;

    // 定时任务运行状态：停止
    public static final Integer STATUS_NOT_RUNNING = 2;

    // 定时任务是否有状态：有（默认为1，弃用）
    public static final Integer CONCURRENT_IS = 1;

    // 定时任务是否有状态：无（默认为1，弃用）
    public static final Integer CONCURRENT_NOT = 0;
}