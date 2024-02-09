package com.gnahz.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gnahz.config.MyThreadLocal.UserAndPsVoContext;
import com.gnahz.email.service.impl.QQEmailServiceImpl;
import com.gnahz.mapper.GrowMapper;
import com.gnahz.mapper.UserMapper;
import com.gnahz.pojo.Grow;
import com.gnahz.pojo.User;
import com.gnahz.service.GrowService;
import com.gnahz.utils.TimeStampUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class GrowServiceImpl extends ServiceImpl<GrowMapper, Grow> implements GrowService {

    @Autowired
    GrowMapper growMapper;
    @Autowired
    UserMapper userMapper;


    public String getCron() {
        return growMapper.getCron();
    }

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
        QueryWrapper<Grow> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                //.eq(Grow::getGrowUserId, id)表示查询条件为Grow表中的growUserId字段等于传入的id
                .eq(Grow::getGrowUserId,id)
                //升序排序
                .orderByAsc(Grow::getGrowOldTime);
        //调用this.page(page, queryWrapper)方法，将page和queryWrapper作为参数传入，执行查询操作并返回分页结果
        return this.page(page,queryWrapper);
    }

    /**
     * 写给未来的信
     * @param grow
     * @return
     */
    @Transactional
    @Override
    public Grow GrowInsert(Grow grow) {
        //创建了一个新的Grow对象，并将其引用赋值给变量NewGrow
        Grow NewGrow = new Grow();

        // 获取当前时间
//        Calendar calendar = Calendar.getInstance();
//
//        // 在当前时间上加两个月
//        calendar.add(Calendar.MONTH, 2);
//        Date newDate = calendar.getTime();
//
//        // 格式化日期输出
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String formatDate = sdf.format(newDate);
        //获取当前时间
        Date date = DateUtil.date();
//        Long stamp = TimeStampUtils.TimeStamp(date.toString());
//        Long currentDates = TimeStampUtils.TimeStamp(formatDate);
//        Long PageDate = TimeStampUtils.TimeStamp(grow.getGrowNewTime().toString());
       // if()
        //将grow对象的所有属性值复制到NewGrow对象中。这意味着如果grow对象有某个属性，那么NewGrow对象也会有相同的属性，并且它们的值是一样的
        BeanUtils.copyProperties(grow,NewGrow);

        //将变量id的值设置为NewGrow对象的growUserId属性
        String username = UserAndPsVoContext.get();
        Integer userId = userMapper.findByUsername(username);
        NewGrow.setGrowUserId(userId);

        //将变量date的值设置为NewGrow对象的growOldTime属性
        NewGrow.setGrowOldTime(date);
        //0代表未删除
        NewGrow.setGrowLogic(0);
        //插入操作
        growMapper.insert(NewGrow);
        return NewGrow;
    }

    /** .
     * 根据条件修改信息（可修改[主题,内容,视频\图片,发送时间,邮件地址,手机号,写者姓名,发送者姓名]）
     * 有瑕疵后期补救（原因：前端如果什么数据都没有填写就提交数据也会刷新表单时间，我的办法在前端做逻辑判断后端就不需要写了）
     * @param grow
     * @return
     */
    @Override
    public Grow growUpdate(Grow grow) {
        //时间判断待完成（创建时间必须小于发送时间）可以用时间戳比较
        //获取当前时间
        DateTime date = DateUtil.date();
        //添加到setGrowOldTime为表单创建时间
        grow.setGrowOldTime(date);
        growMapper.GrowUpdate(grow.getGrowId(),//id
                grow.getGrowTheme(),//主题
                grow.getGrowContent(),//内容
                grow.getGrowVideo(),//视频\图片
                grow.getGrowOldTime(),//创建时间
                grow.getGrowNewTime(),//发送时间
                grow.getGrowMail(),//邮件地址
                grow.getGrowTelephone(),//手机号
                grow.getWriteName(),//写者姓名
                grow.getReadName());//发送者姓名
        return grow;
    }

    /**
     * 查询数据库里面所有的发送时间
     * @return
     */
    @Override
    public List<String> InsertMysqlNewDate() {
        ArrayList<String> stringArrayList = new ArrayList<>();
        List<String> insertDateAll = growMapper.InsertDateAll();
        for (String insetAll : insertDateAll) {
            stringArrayList.add(insetAll);
        }
        return stringArrayList;
    }

}
