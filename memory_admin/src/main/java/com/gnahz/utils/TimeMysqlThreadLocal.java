package com.gnahz.utils;

import java.util.ArrayList;

/**
 * @Author 张伟洁
 * Date:2024-02-17-20:38
 * @create 忆项目(小白)
 */
public class TimeMysqlThreadLocal {
    private static ThreadLocal<ArrayList<String>> TimeThreadLocal = new ThreadLocal<>();

    public static ThreadLocal<ArrayList<String>> get() {
        return TimeThreadLocal;
    }

    public static void set(ArrayList<String> timeThreadLocal) {
        TimeThreadLocal.set(timeThreadLocal);
    }

    public static void remove(){
        TimeThreadLocal.remove();
    }
}
