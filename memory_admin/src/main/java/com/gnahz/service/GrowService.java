package com.gnahz.service;



import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gnahz.pojo.Grow;
import com.gnahz.vo.req.GrowReq;


/**
 * @Author 张伟洁
 * Date:2024-01-04-18:40
 * @create 忆项目(小白)
 */
public interface GrowService{
    /**
     * 查询所有未来信（分页）
     * @param id
     * @param pageName
     * @param pageSize
     * @return
     */
    Page queryGrow(Integer id, Integer pageName, Integer pageSize);


    /**
     * 写给未来的信
     * @param growReq
     * @return
     */
    Boolean GrowInsert(GrowReq growReq);

    /**
     * 根据条件修改信息（可修改[主题,内容,视频\图片,发送时间,邮件地址,手机号,写者姓名,发送者姓名]）
     * @param grow
     * @return
     */
    void growUpdate(Grow grow);


    /**
     * 查询数据库里面所有的发送时间
     * @return
     */
    void InsertMysqlNewDate();
}
