package com.gnahz.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gnahz.mapper.PastMapper;
import com.gnahz.pojo.Past;
import com.gnahz.service.PastService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author 张伟洁
 * Date:2024-01-04-18:39
 * @create 忆项目(小白)
 */
@Service
public class PastServiceImpl extends ServiceImpl<PastMapper, Past> implements PastService {


    @Autowired
    PastMapper pastMapper;

    /**
     * 查询所有回忆信（分页）
     * @param id
     * @param pageName
     * @param pageSize
     * @return
     */
    @Override
    public Page queryPast(Integer id, Integer pageName, Integer pageSize) {
        //创建一个Page对象，使用传入的pageName和pageSize作为参数进行初始化
        Page page = new Page<>(pageName,pageSize);
        //使用queryWrapper.lambda()方法来构建查询条件
        QueryWrapper<Past> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                //.eq(Grow::getGrowUserId, id)表示查询条件为Grow表中的growUserId字段等于传入的id
                .eq(Past::getPastUserId,id)
                //升序排序
                .orderByAsc(Past::getPastOldTime);
        //调用this.page(page, queryWrapper)方法，将page和queryWrapper作为参数传入，执行查询操作并返回分页结果
        return this.page(page,queryWrapper);
    }

    /**
     * 给以前的自己一封信
     * @param past
     * @param userId
     * @return
     */
    @Override
    public Past PastInsert(Past past, Integer userId) {
        //创建一个新的Past对象
        Past NewPast = new Past();
        //将past对象的所有属性值复制到NewPast对象中
        BeanUtils.copyProperties(past,NewPast);
        //获取当前时间
        DateTime date = DateUtil.date();
        //将当前时间设置到PastOldTime中
        NewPast.setPastOldTime(date);
        //将变量id的值设置为NewPast对象的PastUserId属性
        NewPast.setPastUserId(userId);
        //初始化为0 表示未删除
        NewPast.setPastLogic(0);
        //添加到数据库
        pastMapper.insert(NewPast);
        //返回NewPast实体类
        return NewPast;
    }

    /**
     * 用户添加好的单表单信息修改（可修改信息为[主题pastTheme,内容pastContent,视频/图片pastVideo,]）
     * 有瑕疵后期补救（原因：前端如果什么数据都没有填写就提交数据也会刷新表单时间，我的办法在前端做逻辑判断后端就不需要写了）
     * @param past
     * @return
     */
    @Override
    public Past pastUpdate(Past past) {
        //获取当前系统的时间
        DateTime date = DateUtil.date();
        //添加到setPastOldTime为表单创建时间
        past.setPastOldTime(date);
        pastMapper.PastUpdate(past.getPastUserId(),//表单id
                            past.getPastTheme(),//表单主题
                            past.getPastContent(),//表单内容
                            past.getPastVideo(),//表单视频或图片
                            past.getPastOldTime());//表单日期
        return past;
    }
}
