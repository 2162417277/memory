package com.gnahz.config.MyThreadLocal;



/**
 * @Author 张伟洁
 * Date:2024-01-27-22:18
 * @create 忆项目(小白)
 */
public class UserAndPsVoContext {
    //从token里面获取用户名
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void set(String username) {
        threadLocal.set(username);
    }

    public static String get() {
        return threadLocal.get();
    }

    public static void remove(){
        threadLocal.remove();
    }
}
