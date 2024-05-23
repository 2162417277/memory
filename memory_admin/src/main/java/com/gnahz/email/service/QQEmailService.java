package com.gnahz.email.service;

/**
 * @Author 张伟洁
 * Date:2024-01-28-17:51
 * @create 忆项目(小白)
 */
public interface QQEmailService {

    /**
     * 可上传邮件带html样式的还有图片
     */
    void sendCommonEmail(String subjects,String htmlS,String mail);

    /**
     * 邮箱是html形式
     */
    void sendCommonEmaill(String format1);

    /**
     * 测试方法
     */
    boolean EmailTest();

}
