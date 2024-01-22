package com.gnahz.config.component;

import cn.hutool.json.JSONUtil;
import com.gnahz.api.CommonResult;
import com.gnahz.api.ResultCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author 张伟洁
 * Date:2024-01-22-8:41
 * @create 忆项目(小白)
 * 没有权限访问时的响应处理类
 */
public class ResltFulAccesDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        //响应403没有相关权限
        response.getWriter().println( JSONUtil.parse(CommonResult.failed(ResultCode.FORBIDDEN)));
        //释放内存
        response.getWriter().flush();
    }
}
