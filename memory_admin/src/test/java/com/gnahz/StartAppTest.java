package com.gnahz;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gnahz.mapper.UserMapper;
import com.gnahz.pojo.User;
import com.gnahz.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author 张伟洁
 * Date:2024-01-07-12:47
 * @create 忆项目(小白)
 */
@SpringBootTest(classes = {StartApp.class})//测试方法
@Slf4j//日志
public class StartAppTest {

    @Test
    public void tset(){
        //System.out.println("你好");
        //查找中文乱码
       // System.out.println(System.getProperties());

//当前时间
        Date date2 = DateUtil.date(Calendar.getInstance());
//当前时间
        Date date3 = DateUtil.date(System.currentTimeMillis());
        System.out.println(date2);
        System.out.println(date3);
    }

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;


    @Test
    void test(){
        String data = "1111";
        List<String> list = new ArrayList<>();
        list.add("111");
        list.add("222");
        list.add("333");
        list.add("444");
        list.add("555");

        if (hasDuplicate(data, list)) {
            System.out.println("列表中存在重复数据");
        } else {
            System.out.println("列表中不存在重复数据");
        }
    }

    public static boolean hasDuplicate(String data, List<String> list) {
        for (String item : list) {
            if (item == data) {
                return true;
            }
        }
        return false;
    }




    @Test
    public void insertUser(){
        User user = new User();
        String userName = "1111";
        String password = "1111";
        user.setUserName(userName);
        user.setPassword(password);
        User userInsert = userService.UserInsert(user);
        System.out.println("userInsert:"+userInsert);
    }

    /**
     * 测试查询user表所有数据
     */
    @Test
    public void testSelectList(){
        //通过条件构造器查询一个list集合，若没有条件，则可以设置null为参数
        List<User> users = userMapper.selectList(null);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        users.forEach(System.out::print);
    }

    @Test
    void testNull(){
        List<String> collect = userMapper.selectList(null).stream().map(User::getUserName).collect(Collectors.toList());
        for (int i = 0; i < collect.size(); i++) {
            System.out.println(collect.get(i));
        }
        /*for (String list:collect){
            System.out.println(list);
        }*/
        //collect.forEach(System.out::print);
    }

    /**
     * 测试根据id查询用户数据
     */
    @Test
    public void testSelectById(){
        User user = userMapper.selectById(1L);
        //userMapper.insert()
        System.out.println(user);
    }

    /**
     * 根据用户userName查询用户密码
     */
    @Test
    public void testUserNameAndPassword(){
        String userName = "李四";
        String password = "123456";
        //String password = userMapper.selectPasswordByName(userName);
        HashMap<String, String> stringStringHashMap = userService.selectPasswordByName(userName, password);
        System.out.println(stringStringHashMap);
    }
}
