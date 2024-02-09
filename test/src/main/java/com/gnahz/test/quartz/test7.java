package com.gnahz.test.quartz;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 张伟洁
 * Date:2024-02-04-11:05
 * @create 忆项目(小白)
 */
public class test7 {
    public static void main(String[] args) {
        List<Integer> myList = new ArrayList<>();
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);
        myList.add(2);
        myList.add(6);
        myList.add(7);
        myList.add(8);
        myList.add(9);
        myList.add(4);
        myList.add(10);

        Integer name = 2;

        for (Integer integer : myList) {
            if(integer.equals(name)){
                System.out.println(integer);
            }
        }
    }


}
