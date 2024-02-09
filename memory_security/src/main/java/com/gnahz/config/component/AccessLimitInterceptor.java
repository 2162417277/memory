package com.gnahz.config.component;

import cn.hutool.json.JSONUtil;
import com.gnahz.api.CommonResult;
import com.gnahz.api.ResultCode;
import com.gnahz.config.note.AccessLimit;
import com.gnahz.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author 张伟洁
 * Date:2024-02-04-13:58
 * @create 忆项目(小白)
 */

public class AccessLimitInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            AccessLimit accessLimit = handlerMethod.getMethodAnnotation(AccessLimit.class);
            if(accessLimit == null){
                return true;
            }
            int seconds = accessLimit.seconds();
            int maxCount = accessLimit.maxCount();
            boolean needLogin = accessLimit.needLogin();
            if(needLogin){
                //判断是否登录
            }
            //获取http://ip:8080/url
            String ip = request.getRemoteAddr();
            String key = ip+request.getServletPath();
            Integer count = (Integer) redisService.get(key);
            //首次进入
            if(count == null || -1 == count){
                redisService.set(key,1);
                //设置过期时间
                redisService.expire(key,1);
                return true;
            }
            //如果访问次数<最大次数,则做加1操作
            if(count < maxCount){
                redisService.incr(key,1);
                return true;
            }

            //此时访问次数大于大于最大次数
            if(count >= maxCount){
                System.out.println("count===:"+count);
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println( JSONUtil.parse(CommonResult.failed(ResultCode.FREQUENT)));
                return false;
            }

        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
