package com.gnahz.test.quartz;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author 张伟洁
 * Date:2024-02-03-21:41
 * @create 忆项目(小白)
 */
public class test4 {
    private static List<String> list = new ArrayList<>();

    public static void main(String[] args) {
        // 第一次赋值
        assignValues();

        // 设置定时器，每隔一段时间执行一次清空集合操作
        Timer timer = new Timer();
        timer.schedule(new ClearTask(), 0, 5000); // 每隔5秒执行一次
    }

    public static void assignValues() {
        if (list.isEmpty()) {
            list.add("A");
            list.add("B");
            list.add("C");
            System.out.println("集合已赋值：" + list);
        } else {
            System.out.println("集合已有数据：" + list);
        }
    }

    static class ClearTask extends TimerTask {
        @Override
        public void run() {
            list.clear();
            System.out.println("集合已清空");
            assignValues();
        }
    }
}
