package com.gnahz.service;



import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gnahz.pojo.Grow;


/**
 * @Author 张伟洁
 * Date:2024-01-04-18:40
 * @create 忆项目(小白)
 */
public interface GrowService extends IService<Grow> {
    /**
     * 查询所有未来信（分页）
     * @param id
     * @param pageName
     * @param pageSize
     * @return
     */
    Page queryGrow(Integer id, Integer pageName, Integer pageSize);

}
