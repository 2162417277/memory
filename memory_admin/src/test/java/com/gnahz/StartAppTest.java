package com.gnahz;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gnahz.ai.dao.ChatGpt;
import com.gnahz.api.CommonPage;
import com.gnahz.api.CommonResult;
import com.gnahz.dao.GrowDao;
import com.gnahz.dao.PastDao;
import com.gnahz.email.service.impl.NetEaseServiceImpl;
import com.gnahz.email.service.impl.QQEmailServiceImpl;
import com.gnahz.mapper.GrowMapper;
import com.gnahz.mapper.PastMapper;
import com.gnahz.mapper.UserMapper;
import com.gnahz.pojo.Grow;
import com.gnahz.pojo.Past;
import com.gnahz.pojo.User;
import com.gnahz.pojo.dto.OssPolicyResult;
import com.gnahz.service.*;
import com.gnahz.service.impl.*;
import com.gnahz.utils.DateUtils;
import com.gnahz.utils.JwtTokenUtil;
import com.gnahz.utils.TimeStampUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * @Author 张伟洁
 * Date:2024-01-07-12:47
 * @create 忆项目(小白)
 */
@SpringBootTest(classes = {StartApp.class})//测试方法
@Slf4j//日志
public class StartAppTest {


    @Autowired
    private GrowDao growDao;

    @Test
    void test20(){

    }

    @Autowired
    private RedisService redisService;

    @Test
    void redisTest(){
        ArrayList<String> times = (ArrayList<String>) redisService.get("Time");
        for (String time: times) {
            System.out.println(time);
        }
    }

    @Autowired
    private ChatGpt chatGpt;

    @Test
    void test23(){
        System.out.println(chatGpt.getAppid());
    }





    @Test
    public void test8(){
        String date = "2024-2-8 06:09";
        Long timeStamp = TimeStampUtils.TimeStamp(date);
        System.out.println(timeStamp);
    }

    @Test
    public void test9(){
        long timestamp = 1707343740;
        String stamp = TimeStampUtils.DateStamp(timestamp);
        System.out.println(stamp);
//        //long timestamp = System.currentTimeMillis(); // 获取当前时间戳
//        //1707316626193
//        Date date = new Date(timestamp * 1000); // 将时间戳转换为Date对象
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); // 设置日期格式
//        String formattedDate = sdf.format(date); // 将Date对象转换为格式化的字符串
//
//        System.out.println("时间戳：" + timestamp);
//        System.out.println("日期：" + formattedDate);
    }


    @Test
    public void test16(){
        // 获取当前时间
        Calendar calendar = Calendar.getInstance();
        // 在当前时间上加一个月
        calendar.add(Calendar.MONTH,1);
        Date MonThTime = calendar.getTime();
        // 格式化日期输出
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String TwoTime = sdf.format(MonThTime);
        Long TwoMonths = TimeStampUtils.TimeStamp(TwoTime);
        //String UserTime = sdf.format(grow.getGrowNewTime());
        String UserTime = "2024-3-16 12:22";
        Long UserMonths = TimeStampUtils.TimeStamp(UserTime);
        if(TwoMonths > UserMonths){
            System.out.println("11111");
        }else {
            System.out.println("222222");
        }
    }

    @Test
    public void test11(){
        String date = "2024-02-07 22:59"; //2024-04-07 22:51
        String formatDate = "2024-07-07 12:59";
        String time = "2024-09-07 12:59";
        Long stamp = TimeStampUtils.TimeStamp(date);
        Long currentDates = TimeStampUtils.TimeStamp(formatDate);
        Long PageDate = TimeStampUtils.TimeStamp(time);
        System.out.println("stamp"+stamp);
        System.out.println("currentDates"+currentDates);//5184000
        System.out.println("PageDate"+PageDate);
        System.out.println(currentDates - stamp);
        if(currentDates - PageDate == stamp){
            System.out.println("111");
        }else {
            System.out.println("22222");
        }
    }


    @Test
    public void test15() throws ParseException {
        String date = "Tue Feb 20 00:00:00 CST 2024";
        //Fri Sep 20 00:00:00 CST 2024
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        System.out.println(sdf1.parse(date));

        Calendar calendar = Calendar.getInstance();
        //当前时间
        Date currentDate = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        System.out.println(sdf.format(currentDate));
    }

    @Test
    public void test13(){
        Calendar calendar = Calendar.getInstance();
        //当前时间
        Date currentDate = calendar.getTime();
        calendar.add(Calendar.MONTH, 2);
        //两个月后的时间
        Date newDate = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateNew1 = "2024-03-16 17:18";
        String dateNew11 = "2024-01-16 17:18";
        String dateNew2 = "2024-06-16 17:18";
        String format1 = sdf.format(currentDate);
        String format2 = sdf.format(newDate);
        System.out.println("当前的日期：" + format1);
        System.out.println("加两个月后的日期：" + format2);
        Long stamp1 = TimeStampUtils.TimeStamp(dateNew1);//"2024-03-16 17:18"
        Long stamp2 = TimeStampUtils.TimeStamp(dateNew2);//"2024-06-16 17:18"
        Long stamp3 = TimeStampUtils.TimeStamp(format1);
        Long stamp4 = TimeStampUtils.TimeStamp(format2);//"2024-04-16 17:18"
        Long stamp5 = TimeStampUtils.TimeStamp(dateNew11);
        if(stamp4 > stamp1){//"2024-04-16 17:18">"2024-03-16 17:18"
            System.out.println("加2");//
        }else {
            System.out.println("减2");
        }
        if(stamp4 > stamp2){//"2024-04-16 17:18">"2024-06-16 17:18"
            System.out.println("加3");
        }else {
            System.out.println("减3");//
        }
        if(stamp3 > stamp5){
            System.out.println("111");//
        }else {
            System.out.println("22222");
        }
        if(stamp3 > stamp1){
            System.out.println("加1");
        }else {
            System.out.println("减1");//
        }


    }



    @Test
    public void test12(){
        String dateNew1 = "2024-03-16 17:18";
        String substring = dateNew1.substring(5);
        System.out.println(substring);
        Long aLong = TimeStampUtils.TimeMinuteStamp(substring);
        System.out.println(aLong);
    }

    @Test
    public void test10(){
        // 获取当前时间
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        String dateNew1 = "2024-03-16 17:18";
        String substring = dateNew1.substring(5);
        System.out.println(substring);
        Long aLong = TimeStampUtils.TimeMinuteStamp(substring);

        String dateNew2 = "2024-06-16 17:18";
        String substring2 = dateNew2.substring(5);
        System.out.println(substring2);
        Long aLong2 = TimeStampUtils.TimeMinuteStamp(substring2);
        // 在当前时间上加两个月
        calendar.add(Calendar.MONTH, 2);
        Date newDate = calendar.getTime();

        /**
         * 当前日期：2024-02-16 17:18
         * 加两个月后的日期：2024-04-16 17:18
         * 相减:5184000
         */
        // 格式化日期输出
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String format = sdf.format(currentDate);
        String format1 = sdf.format(newDate);
        String substring1 = format.substring(5);
        String substring3 = format1.substring(5);
        Long stamp1 = TimeStampUtils.TimeMinuteStamp(substring1);
        System.out.println("当前时间日期时间戳:"+stamp1);
        Long stamp2 = TimeStampUtils.TimeMinuteStamp(substring3);
        System.out.println("当前时间日期:"+substring1);
        System.out.println("当前时间日期两个月后的时间戳:"+stamp2);
        System.out.println("当前时间日期两个月后的:"+substring3);
        System.out.println("当前日期：" + format);
        System.out.println("当前日期：" + TimeStampUtils.TimeStamp(format));
        System.out.println("加两个月后的日期：" + format1);
        System.out.println("加两个月后的日期：" + TimeStampUtils.TimeStamp(format1));
        System.out.println("时间戳1："+ aLong);
        System.out.println("时间戳2："+ aLong2);
        long date = TimeStampUtils.TimeStamp(format1) - TimeStampUtils.TimeStamp(format);
        long l2 = stamp2 - aLong;
        long l3 = stamp2  - aLong2;
        long l = date - aLong;
        long l1 = date - aLong2;
        System.out.println("相减:"+date);
        System.out.println("结果:"+l);
        System.out.println("结果2"+l1);
        System.out.println(l2);
        System.out.println(l3);
        if(l2 > stamp1){
            System.out.println("1111");
        }else {
            System.out.println("22222");
        }
        if(l3 > stamp1){
            System.out.println("1111");
        }else {
            System.out.println("22222");
        }

    }

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

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RedisServiceImpl redisServiceImpl;

    @Autowired
    private UserCacheServiceImpl userCacheService;

    @Autowired
    private QQEmailServiceImpl QQEmailService;

    @Autowired
    private GrowServiceImpl growServiceImpl;


    @Test
    public void test3(){
        QueryWrapper<Grow> queryWrapper = new QueryWrapper<>();
        String date = "2024-07-30 16:02:15";
        queryWrapper.lambda().eq(Grow::getGrowNewTime,date);
        int result = growMapper.delete(queryWrapper);
        System.out.println(result > 0 ? "删除成功！" : "删除失败！");
        System.out.println("受影响的行数为：" + result);
    }

    @Test
    public void insert(){
        QueryWrapper<Grow> queryWrapper = new QueryWrapper<>();
        String date = "2024-07-30 16:02:15";
        queryWrapper.lambda().eq(Grow::getGrowNewTime,date);
        List<Grow> grows = growMapper.selectList(queryWrapper);
        grows.forEach(System.out::println);
    }

    @Test
    public void test112(){
        YearMonth yearMonth = YearMonth.now();
        int daysInMonth = yearMonth.lengthOfMonth();
        long times = daysInMonth * 60 * 60 * 24;
        System.out.println(daysInMonth);
        System.out.println(times);
    }


    @Test
    public void QQEmail() throws ParseException {
        QueryWrapper<Grow> queryWrapper = new QueryWrapper<>();
        int id= 3;
        String date = "2024-02-3 20:18:08";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse  = simpleDateFormat.parse(date);
        System.out.println(parse);
        queryWrapper.lambda().eq(Grow::getGrowNewTime,parse);
        List<Grow> grows = growMapper.selectList(queryWrapper);

        String growTheme = grows.get(0).getGrowTheme();
        String growContent = grows.get(0).getGrowContent();
        String growMail = grows.get(0).getGrowMail();
        QQEmailService.sendCommonEmail(growTheme,growContent,growMail);
        System.out.println(growTheme);
        grows.forEach(System.out::println);
    }

    /**
     * cron
     */
    @Test
    public void cron(){
        //2024-02-10 06:20:08
        String cron = "00 15 16 01 02 ? 2024";
        String time = growMapper.OldTime(cron);
        System.out.println(time);
    }


    @Test
    public void rediss(){
        User user = userCacheService.getUser("memory");
        System.out.println(user);
    }


    @Test
    public void test6(){
        String format1 = "2024-02-04 10:15:00";
        QueryWrapper<Grow> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Grow::getGrowNewTime,format1);
        List<Grow> grows = growMapper.selectList(queryWrapper);
        for (int i = 0; i < grows.size(); i++) {
            System.out.println(grows.get(i).getGrowTheme());
        }
        String growTheme = grows.get(0).getGrowTheme();
        System.out.println(growTheme);
        grows.forEach(System.out::println);
    }

    @Autowired
    CommonAjaxController commonAjaxController;

    @Autowired
    QQEmailServiceImpl qqEmailService;
    @Autowired
    NetEaseServiceImpl netEaseService;


    @Test
    public void subTest(){
        String time = "2024-02-04 20:49:00";
        String[] split = time.split(" ");
        System.out.println(split[0]);
        String substring = split[0].substring(0, 4);//[)
        System.out.println(substring);

        // 获取当前时间的Calendar对象
        Calendar calendar = Calendar.getInstance();

        // 在当前年份上加上100年
        calendar.add(Calendar.YEAR, 100);

        // 获取加100年后的年份
        int year = calendar.get(Calendar.YEAR);

        // 输出结果
        System.out.println("当前时间加100年后的年份是：" + year);

    }

    /**
     * 获取当前时间类型yyyy-MM-dd HH:mm:ss
     */
    @Test
    public void testTime(){
        // 获取当前时间的Calendar对象
        Calendar calendar = Calendar.getInstance();

        // 创建SimpleDateFormat对象，指定日期时间格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 将Calendar对象转换为指定格式的字符串
        String dateStr = sdf.format(calendar.getTime());
        System.out.println(dateStr);
    }


    @Test
    public void test123(){
        String name = "李四";
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUserName,name);
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }

    @Test
    public void QQEmailTest(){
        //2099年时空法》
        //String time = "2022-11-09";
        // 获取当前时间的Calendar对象
        Calendar calendar = Calendar.getInstance();

        // 创建SimpleDateFormat对象，指定日期时间格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 将Calendar对象转换为指定格式的字符串
        String time = sdf.format(calendar.getTime());
        String title = "致青春，至未来";
        String userEmail = "2162417277@qq.com";
        netEaseService.CurrentMail(time,title,userEmail);
    }

    @Value("${spring.mail.username}")
    private String username;

    @Test
    public void Test1Emial(){
       // commonAjaxController.commonEmail();
        System.out.println(username);
    }





    /**
     * 测试邮件
     */
    @Test
    public void TestEmail(){
        boolean result = QQEmailService.EmailTest();
        System.out.println(result == true ? 1 : 2);
    }

    @Test
    public void admin(){
        User username = userService.getAdminByUsername("李四");
        System.out.println(username);
    }
    /**
     * token
     */
    @Test
    public void jwt(){
        //
        //Bearer eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyX25hbWUiOiLmnY7lm5siLCJjcmVhdGVkIjoxNzA1OTI4MzExMDA5LCJleHAiOjE3MDYwMTQ3MTF9.y_PsymS9jx0qYCjYSVJE6T-XapyCu8c4DuSzZmALk7Im8N-lFa8ZABl-ELUTzR7mMbaa19UYXkCIaO-NYZcOfA
        String nameStr = jwtTokenUtil.generateUserNameStr("朱先生");
        System.out.println(nameStr);
//        String username = "李四";
//        String username = "admin";
//
//        String str = jwtTokenUtil.generateUserNameStr(username);
//        System.out.println(str);
    }

    @Test
    public void redisEs(){
        redisServiceImpl.set("name","admin");
        String name = (String) redisServiceImpl.get("name");
        System.out.println(name);
    }

    @Test
    public void eq(){
        String name1 = "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsImNyZWF0ZWQiOjE3MDU5MTEzNzc5NDcsImV4cCI6MTcwNTk5Nzc3N30.b4Z_n7t8eDOM1IP7Z2gCkRG6t2HmhbYSmg_GGrJ5JDZe1n6mZ5zmpRq8Le9Cc5S0q4Oe04VuwpN2DiDJduspRA";
        String name2 = "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyX25hbWUiOiLmnY7lm5siLCJjcmVhdGVkIjoxNzA1OTExMjcyMzQ4LCJleHAiOjE3MDU5OTc2NzJ9.q42hxfJOvNr5vH9fLez-JS4jjw4x44PDx4bfpHSW9G3NfJ4Pay0RRGhGTz9LYNbs0ACpe0Tpeb2Jo4Eo7D5-zw";
        if(name1 == name2){
            System.out.println("1");
        }else {
            System.out.println("2");
        }
    }

    @Test
    public void md5AndUser(){
        User user = new User();
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        String username = "李绅";
        String password = "qwert";
        String encodePassword = BCrypt.hashpw(password);
        wrapper.lambda().eq(User::getUserName,username).set(User::getPassword,encodePassword);
        int result = userMapper.update(user, wrapper);
        System.out.println(result > 0 ? "修改成功！" : "修改失败！");
        System.out.println("受影响的行数为：" + result);
       /* QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUserName,"李四");
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);*/

    }


    @Test
    public void md5(){
        //将密码进行加密操作
//        String encodePassword = BCrypt.hashpw(umsMember.getPassword());
//        umsMember.setPassword(encodePassword);
//        baseMapper.insert(umsMember);
//        return umsMember;
        String username = "雄安塔";
        String password = "1234567";
        String encodePassword = BCrypt.hashpw(password);
        Date date = DateUtil.date();
        User user = new User();
        user.setUserName(username);
        user.setPassword(encodePassword);
        user.setUserDate(date);
        user.setUserLogic(0);
        userMapper.insert(user);
    }

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
        //User userInsert = userService.UserInsert(user);
        //System.out.println("userInsert:"+userInsert);
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
        String password = "1234567";
        //String password = userMapper.selectPasswordByName(userName);
        //HashMap<String, String> stringStringHashMap = userService.selectPasswordByName(userName, password);
        //System.out.println(stringStringHashMap);
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
       /* Grow insert = growService.GrowInsert(grow);
        System.out.println("result:"+insert);*/
    }

    @Autowired
    PastDao pastDao;


    @Test
    public void testDatePast(){
        Wrapper<Past> pasts = pastDao.queryPast(9);
        System.out.println(pasts);
    }


    @Test
    public void PastInsert(){
        Past past = new Past();
        past.setPastTheme("敬死去的自己");
        past.setPastContent("亲爱的过去的自己，我相信你会变得越来越好。请记住，无论你走到哪里，我都会一直支持你、关心你。加油！");
        past.setPastVideo(null);
        //Past insert = pastService.PastInsert(past, 1);
        //System.out.println("result:"+insert);
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
       // Past update = pastService.pastUpdate(past);
        //System.out.println("result:"+update);
    }

    @Test
    public void growUpdate(){
        Grow grow = new Grow();
        grow.setGrowId(1);
        //Grow update = growService.growUpdate(grow);
        //System.out.println("result:"+update);
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
