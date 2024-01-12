package com.gnahz.service;

import com.gnahz.pojo.dto.OssCallbackResult;
import com.gnahz.pojo.dto.OssPolicyResult;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO  oss上传管理Service
 * @Author 张伟洁
 * Date:2024-01-12-10:07
 * @create 忆项目(小白)
 */
public interface OssService {

    /**
     * oss上传策略生成
     * @return
     */
    OssPolicyResult policy();

    /**
     * oss上传成功回调
     * @param request
     * @return
     */
    OssCallbackResult callback(HttpServletRequest request);
}

