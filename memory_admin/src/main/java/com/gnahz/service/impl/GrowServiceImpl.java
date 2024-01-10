package com.gnahz.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gnahz.mapper.GrowMapper;
import com.gnahz.pojo.Grow;
import com.gnahz.service.GrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 张伟洁
 * Date:2024-01-04-18:38
 * @create 忆项目(小白)
 */
@Service
public class GrowServiceImpl extends ServiceImpl<GrowMapper, Grow> implements GrowService {

    @Autowired
    GrowMapper growMapper;

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




}
