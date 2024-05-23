package com.gnahz.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gnahz.common.event.GrowReqEvent;
import com.gnahz.config.MyThreadLocal.UserAndPsVoContext;
import com.gnahz.dao.GrowDao;
import com.gnahz.dao.UserDao;
import com.gnahz.email.service.impl.NetEaseServiceImpl;
import com.gnahz.mapper.GrowMapper;
import com.gnahz.mapper.UserMapper;
import com.gnahz.pojo.Grow;
import com.gnahz.service.GrowService;
import com.gnahz.service.RedisService;
import com.gnahz.utils.TimeStampUtils;
import com.gnahz.vo.req.GrowReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author 张伟洁
 * Date:2024-01-04-18:38
 * @create 忆项目(小白)
 */
@Service
public class GrowServiceImpl implements GrowService {

    @Autowired
    RedisService redisService;
    @Autowired
    private GrowDao growDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


   /**
     * 查询所有未来信（分页）
     * @param id
     * @param pageName
     * @param pageSize
     * @return
     */
    @Override
    public Page queryGrow(Integer id, Integer pageName, Integer pageSize) {
        //创建一个Page对象，使用传入的pageName和pageSize作为参数进行初始化
        Page page = new Page(pageName,pageSize);
        //使用queryWrapper.lambda()方法来构建查询条件
        Wrapper<Grow> grows = growDao.queryGrow(id);
        //调用this.page(page, queryWrapper)方法，将page和queryWrapper作为参数传入，执行查询操作并返回分页结果
        return growDao.page(page,grows);
    }

    /**
     * 写给未来的信
     * @param growReq
     * @return
     */
    @Transactional
    @Override
    public Boolean GrowInsert(GrowReq growReq) {
        // 获取当前时间
        Calendar calendar = Calendar.getInstance();
        // 在当前时间上加一个月
        calendar.add(Calendar.MONTH,1);
        Date MonThTime = calendar.getTime();
        // 格式化日期输出
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String TwoTime = sdf.format(MonThTime);
        Long TwoMonths = TimeStampUtils.TimeStamp(TwoTime);
        String UserTime = sdf.format(growReq.getGrowNewTime());
        Long UserMonths = TimeStampUtils.TimeStamp(UserTime);
        if(TwoMonths > UserMonths){
            return null;
        }
        //获取当前时间
        Date date = DateUtil.date();
        //将变量id的值设置为NewGrow对象的growUserId属性
        String username = UserAndPsVoContext.get();
        Integer userId = userDao.findByUsername(username);
        //插入操作TwoTime
        boolean growInsert = growDao.growInsert(growReq, userId, date);
        applicationEventPublisher.publishEvent(new GrowReqEvent(this,growReq));
        return growInsert ? growInsert : null;
    }

    /** .
     * 根据条件修改信息（可修改[主题,内容,视频\图片,发送时间,邮件地址,手机号,写者姓名,发送者姓名]）
     * 有瑕疵后期补救（原因：前端如果什么数据都没有填写就提交数据也会刷新表单时间，我的办法在前端做逻辑判断后端就不需要写了）
     * @param grow
     * @return
     */
    @Override
    public void growUpdate(Grow grow) {
        //时间判断待完成（创建时间必须小于发送时间）可以用时间戳比较
        //获取当前时间
        DateTime date = DateUtil.date();
        growDao.growUpdate(grow,date);
    }

    /**
     * 查询数据库里面所有的发送时间
     * @return
     */
    @Override
    public void InsertMysqlNewDate() {
        ArrayList<String> stringArrayList = new ArrayList<>();
        List<String> insertDateAll = growDao.InsertDateAll();
        for (String insetAll : insertDateAll) {
            stringArrayList.add(insetAll);
        }
        Object time = redisService.get("Time");
        if(time!=null){
            redisService.del("Time");
        }
        redisService.set("Time",stringArrayList);
        //获取当前年份和月份
        YearMonth yearMonth = YearMonth.now();
        //获取当前月份的总天数
        int daysInMonth = yearMonth.lengthOfMonth();
        //月份 * 24小时 * 60分钟 * 60秒 = 结果存入redis设置过期时间(动态设定)
        long times = daysInMonth * 60 * 60 * 24;
        redisService.expire("Time",times);//过期时间为一个月
    }

}
