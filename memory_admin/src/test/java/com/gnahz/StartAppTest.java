package com.gnahz;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.gnahz.api.CommonPage;
import com.gnahz.api.CommonResult;
import com.gnahz.mapper.GrowMapper;
import com.gnahz.mapper.PastMapper;
import com.gnahz.mapper.UserMapper;
import com.gnahz.pojo.Grow;
import com.gnahz.pojo.Past;
import com.gnahz.pojo.User;
import com.gnahz.pojo.dto.OssPolicyResult;
import com.gnahz.service.GrowService;
import com.gnahz.service.OssService;
import com.gnahz.service.PastService;
import com.gnahz.service.UserService;
import com.gnahz.service.impl.OssServiceImpl;
import com.gnahz.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertNotNull;

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

    @Autowired
    GrowService growService;

    @Autowired
    GrowMapper growMapper;

    @Autowired
    PastMapper pastMapper;

    @Autowired
    PastService pastService;

    @Autowired
    OssService ossService;

    @Autowired
    private RedisTemplate redisTemplate;
    
    @Test
    public void redis(){
        redisTemplate.opsForValue().set("name","张伟洁");
        String  name = (String) redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }


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

    /**
     * SELECT * FROM `grow` WHERE grow_user_id = 1;
     */
    @Test
    public void queryGrow1(){
        Integer id = 2;
        QueryWrapper<Grow> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Grow::getGrowUserId,id);
        List<Grow> grows = growMapper.selectList(wrapper);
        grows.forEach(System.out::println);
        if(grows.size() == 0){
            System.out.println("111");
        }else {
            System.out.println("2222");
        }
    }

    @Test
    public void queryGrow2(){
//        Page page = pastService.queryPast(1, 1, 3);
        Page page = growService.queryGrow(1, 1, 3);
        CommonResult<CommonPage> success = CommonResult.success(CommonPage.restPage(page));
        System.out.println(success);
    }

    @Test
    public void GrowInsert(){
        Grow grow = new Grow();
        String dateTime = "2025-01-01 21:33:32";
        DateTime date = DateUtils.StringTransformDate(dateTime);
        grow.setGrowTheme("未来的哭泣");
        grow.setGrowContent("我希望你能够看到这封信的时候，你已经成为了我们曾经梦想中的那个自己。我希望你过上了幸福、健康、充实的生活，追求着自己的梦想，享受着每一天的美好");
        grow.setGrowVideo(null);
        grow.setGrowNewTime(date);
        grow.setGrowMail("2162417277@qq.com");
        grow.setGrowTelephone("13856939334");
        grow.setWriteName("筱勇");
        grow.setReadName("猪心");
        Grow insert = growService.GrowInsert(grow,1);
        System.out.println("result:"+insert);
    }

    @Test
    public void PastInsert(){
        Past past = new Past();
        past.setPastTheme("敬死去的自己");
        past.setPastContent("亲爱的过去的自己，我相信你会变得越来越好。请记住，无论你走到哪里，我都会一直支持你、关心你。加油！");
        past.setPastVideo(null);
        Past insert = pastService.PastInsert(past, 1);
        System.out.println("result:"+insert);
    }


    /**
     * Past(pastId=1,
     * pastTheme=回忆,
     * pastContent=任时光流逝在芳华中，用回忆的方式来抹去属于悲伤的印记,
     * pastVideo=null,
     * pastOldTime=Mon Jan 01 20:57:25 CST 2024,
     * pastUserId=1,
     * pastLogic=0)
     */
    @Test
    public void pastUpdate(){
        Past past = new Past();
        past.setPastUserId(1);
        past.setPastTheme("某天");
        past.setPastContent("未来模式");
        Past update = pastService.pastUpdate(past);
        System.out.println("result:"+update);
    }

    @Test
    public void growUpdate(){
        Grow grow = new Grow();
        grow.setGrowId(1);
        Grow update = growService.growUpdate(grow);
        System.out.println("result:"+update);
    }

    @Test
    public void past(){
        Integer id = 1;
        QueryWrapper<Past> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Past::getPastId,id);
        Past past = pastMapper.selectOne(queryWrapper);
        System.out.println(past);
    }


    @Test
    public void testDate(){
//        String dateString = "2024-01-01 21:33:32";
//        DateTime dateTime = DateUtils.StringTransformDate(dateString);
        DateTime date = DateUtil.date();
        System.out.println(date);
    }

    @Test
    public void OssTest(){
        String url = "D:\\develo\\memory\\memory_admin\\src\\main\\resources\\333.jpg";
        OssPolicyResult result = ossService.policy();
        assertNotNull(result);
        assertNotNull(result.getAccessKeyId());
        assertNotNull(result.getPolicy());
        assertNotNull(result.getSignature());
        assertNotNull(result.getDir());
        assertNotNull(result.getHost());
    }

    @Autowired
    private OssServiceImpl ossServices;

    public void uploadImage() {

    }
}
