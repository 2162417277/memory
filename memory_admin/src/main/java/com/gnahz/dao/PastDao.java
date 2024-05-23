package com.gnahz.dao;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gnahz.mapper.PastMapper;
import com.gnahz.pojo.Grow;
import com.gnahz.pojo.Past;
import com.gnahz.vo.req.PastReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 张伟洁
 * Date:2024-03-17-13:33
 * @create 忆项目(小白)
 */
@Service
public class PastDao extends ServiceImpl<PastMapper, Past> {

    @Autowired
    private PastMapper pastMapper;

    /**
     * 给过去写信
     * @param pastReq
     * @param date
     * @param userId
     */
    public void PastInsert(PastReq pastReq, DateTime date, Integer userId) {
        Past pastInsert = new Past();
        pastInsert.setPastTheme(pastReq.getPastTheme());
        pastInsert.setPastContent(pastReq.getPastContent());
        pastInsert.setPastOldTime(date);
        pastInsert.setPastUserId(userId);
        pastMapper.insert(pastInsert);
    }

    /**
     *  用户添加好的单表单信息修改（可修改信息为[主题pastTheme,内容pastContent,视频/图片pastVideo,]）
     *      * 有瑕疵后期补救（原因：前端如果什么数据都没有填写就提交数据也会刷新表单时间，我的办法在前端做逻辑判断后端就不需要写了）
     * @param SetPast
     * @param date
     */
    public void pastUpdate(Past SetPast, DateTime date) {
        lambdaUpdate()
                .set(Past::getPastUserId,SetPast.getPastUserId())//表单id
                .set(Past::getPastTheme,SetPast.getPastTheme())//表单主题
                .set(Past::getPastContent,SetPast.getPastContent())//表单内容
                .set(Past::getPastVideo,SetPast.getPastVideo())//表单视频或图片
                .set(Past::getPastOldTime,date)//表单日期
                .update();
    }

    public Wrapper<Past> queryPast(Integer id) {
        QueryWrapper<Past> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                //.eq(Grow::getGrowUserId, id)表示查询条件为Grow表中的growUserId字段等于传入的id
                .eq(Past::getPastUserId,id)
                //升序排序
                .orderByAsc(Past::getPastOldTime);
        return queryWrapper;
    }
}
