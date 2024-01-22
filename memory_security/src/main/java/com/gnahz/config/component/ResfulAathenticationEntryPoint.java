package com.gnahz.config.component;

import cn.hutool.json.JSONUtil;
import com.gnahz.api.CommonResult;
import com.gnahz.api.ResultCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author 张伟洁
 * Date:2024-01-22-8:58
 * @create 忆项目(小白)
 * 没有登录响应
 */
public class ResfulAathenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        //响应401没有相关权限
        response.getWriter().println( JSONUtil.parse(CommonResult.failed(ResultCode.UNAUTHORIZED)));
        //释放内存
        response.getWriter().flush();
    }
}
