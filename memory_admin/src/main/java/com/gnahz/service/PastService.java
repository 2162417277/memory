package com.gnahz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gnahz.pojo.Past;

/**
 * @Author 张伟洁
 * Date:2024-01-04-18:40
 * @create 忆项目(小白)
 */
public interface PastService extends IService<Past> {
    /**
     * 查询所有回忆信（分页）
     * @param id
     * @param pageName
     * @param pageSize
     * @return
     */
    Page queryPast(Integer id, Integer pageName, Integer pageSize);

    /**
     * 给以前的自己一封信
     * @param past
     * @param userId
     * @return
     */
    Past PastInsert(Past past, Integer userId);

    /**
     * 用户添加好的单表单信息修改（可修改信息为[主题pastTheme,内容pastContent,视频/图片pastVideo,]）
     * @param past
     * @return
     */
    Past pastUpdate(Past past);
}
