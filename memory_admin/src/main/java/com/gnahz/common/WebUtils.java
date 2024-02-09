package com.gnahz.common;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author 张伟洁
 * Date:2024-02-04-15:04
 * @create 忆项目(小白)
 */
public class WebUtils {
    public static void writeJson(HttpServletResponse response, Object object) {
        try {
            // 设置响应内容类型为JSON
            response.setContentType("text/html;charset=utf-8");
            // 将对象转换为JSON字符串
            String json = new Gson().toJson(object);
            // 将JSON字符串写入响应输出流
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

