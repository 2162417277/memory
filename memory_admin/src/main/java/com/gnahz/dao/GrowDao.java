package com.gnahz.dao;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gnahz.mapper.GrowMapper;
import com.gnahz.mapper.UserMapper;
import com.gnahz.pojo.Grow;
import com.gnahz.pojo.domain.enums.YesOrNoEnums;
import com.gnahz.utils.DateUtils;
import com.gnahz.vo.req.GrowReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author 张伟洁
 * Date:2024-03-17-13:34
 * @create 忆项目(小白)
 */
@Service
public class GrowDao extends ServiceImpl<GrowMapper, Grow> {


    @Autowired
    private GrowMapper growMapper;
    @Autowired
    private UserMapper userMapper;

    public List<String> InsertDateAll(){
        return growMapper.InsertDateAll();
    }

    public Integer findByUsername(String userName){
        return userMapper.findByUsername(userName);
    }


    public boolean growInsert(GrowReq growReq, Integer userId, Date date) {
        Grow growInsert = new Grow();
        growInsert.setGrowNewTime(growReq.getGrowNewTime());
        growInsert.setGrowTheme(growReq.getGrowTheme());
        growInsert.setGrowContent(growReq.getGrowContent());
        growInsert.setGrowMail(growReq.getGrowMail());
        growInsert.setReadName(growReq.getReadName());
        growInsert.setGrowUserId(userId);
        growInsert.setGrowOldTime(date);
        int result = growMapper.insert(growInsert);
        return result > 0 ? true : false;
    }

    /**
     *  根据条件修改信息（可修改[主题,内容,视频\图片,发送时间,邮件地址,手机号,写者姓名,发送者姓名]）
     * 有瑕疵后期补救（原因：前端如果什么数据都没有填写就提交数据也会刷新表单时间，我的办法在前端做逻辑判断后端就不需要写了）
     * @param SetGrow
     * @param date
     */
    public void growUpdate(Grow SetGrow, DateTime date) {
        lambdaUpdate()
                .set(Grow::getGrowId,SetGrow.getGrowId())//id
                .set(Grow::getGrowTheme, SetGrow.getGrowTheme())//主题
                .set(Grow::getGrowContent,SetGrow.getGrowContent())//内容
                .set(Grow::getGrowVideo,SetGrow.getGrowVideo())//视频\图片
                .set(Grow::getGrowOldTime,date)//创建时间
                .set(Grow::getGrowNewTime,SetGrow.getGrowNewTime())//发送时间
                .set(Grow::getGrowMail,SetGrow.getGrowMail())//邮件地址
                .set(Grow::getGrowTelephone,SetGrow.getGrowTelephone())//手机号
                .set(Grow::getWriteName,SetGrow.getWriteName())//写者姓名
                .set(Grow::getReadName,SetGrow.getReadName())//发送者姓名
                .update();

    }


    public Wrapper<Grow> queryGrow(Integer id) {
        QueryWrapper<Grow> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                //.eq(Grow::getGrowUserId, id)表示查询条件为Grow表中的growUserId字段等于传入的id
                .eq(Grow::getGrowUserId,id)
                //升序排序
                .orderByAsc(Grow::getGrowOldTime);
        return queryWrapper;
    }

    public List<Grow> selectList(QueryWrapper<Grow> queryWrapper) {
        return growMapper.selectList(queryWrapper);
    }

    public void deleteMailTime(String format) {
        QueryWrapper<Grow> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(Grow::getGrowNewTime,format);
        growMapper.delete(queryWrapper);
    }

}
