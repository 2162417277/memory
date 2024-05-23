package com.gnahz.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gnahz.api.CommonPage;
import com.gnahz.config.MyThreadLocal.UserAndPsVoContext;
import com.gnahz.dao.PastDao;
import com.gnahz.dao.UserDao;
import com.gnahz.mapper.PastMapper;
import com.gnahz.mapper.UserMapper;
import com.gnahz.pojo.Past;
import com.gnahz.service.PastService;
import com.gnahz.vo.req.PastReq;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author 张伟洁
 * Date:2024-01-04-18:39
 * @create 忆项目(小白)
 */
@Service
public class PastServiceImpl implements PastService {


    @Autowired
    private PastDao pastDao;
    @Autowired
    private UserDao userDao;

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
        Wrapper<Past> pasts = pastDao.queryPast(id);
        //调用this.page(page, queryWrapper)方法，将page和queryWrapper作为参数传入，执行查询操作并返回分页结果
        return pastDao.page(page,pasts);
    }

    /**
     * 给以前的自己一封信
     * @param pastReq
     * @return
     */
    @Transactional
    @Override
    public void PastInsert(PastReq pastReq) {
        //获取当前时间
        DateTime date = DateUtil.date();
        //将变量id的值设置为NewPast对象的PastUserId属性
        String username = UserAndPsVoContext.get();
        Integer userId = userDao.findByUsername(username);
        pastDao.PastInsert(pastReq,date,userId);
    }

    /**
     * 用户添加好的单表单信息修改（可修改信息为[主题pastTheme,内容pastContent,视频/图片pastVideo,]）
     * 有瑕疵后期补救（原因：前端如果什么数据都没有填写就提交数据也会刷新表单时间，我的办法在前端做逻辑判断后端就不需要写了）
     * @param past
     * @return
     */
    @Override
    public void pastUpdate(Past past) {
        //获取当前系统的时间
        DateTime date = DateUtil.date();
        //添加到setPastOldTime为表单创建时间
        pastDao.pastUpdate(past,date);
    }
}
