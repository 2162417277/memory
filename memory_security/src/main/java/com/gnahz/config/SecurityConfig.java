package com.gnahz.config;

import com.gnahz.config.component.AccessLimitInterceptor;
import com.gnahz.config.component.JwtAuthenticationFilter;
import com.gnahz.config.component.ResfulAathenticationEntryPoint;
import com.gnahz.config.component.ResltFulAccesDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author 张伟洁
 * Date:2024-01-22-8:23
 * @create 忆项目(小白)
 */
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 权限配置 白名单 jwt
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //白名单进行放行
        AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry = http.authorizeHttpRequests();
        //循环白名单进行放行
//        for (String url : secureIgnoreUrlsConfig().getUrls()) {
//            registry.antMatchers(url).permitAll();
//        }HttpMethod.OPTIONS
        /**
         * "/admin/public/user/login","/admin/grow/queryGrow/**","/js/**","/css/**","/img/**","/**.html","/swagger-ui.html","/doc.html"
         */
        //允许跨域请求OPTIONS CORS
        registry.antMatchers("/admin/public/user/login","/sendCommonEmail","/admin/public/user/insert","/admin/grow/queryGrow/**","/js/**","/css/**","/img/**","/**.html","/swagger-ui.html","/doc.html").permitAll();
        //其他任何请求都需要身份认证
        registry
                //任何请求都需要认证
                .anyRequest()
                //都需要认证
                .authenticated()
                //关闭csrf跨站请求伪造：因为现在使用jwt来实现认证
                .and()
                .csrf()
                .disable()
                //禁止session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //自定义权限拒绝处理类
                .and()
                .exceptionHandling()
                //没有权限访问时的处理类
                .accessDeniedHandler(resltFulAccesDeniedHandler())
                //没有登录的处理类
                .authenticationEntryPoint(resfulAathenticationEntryPoint())
                .and()
                //加入jwt认证过滤器
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    public SecureIgnoreUrlsConfig secureIgnoreUrlsConfig(){
        return new SecureIgnoreUrlsConfig();
    }

    @Bean
    public ResltFulAccesDeniedHandler resltFulAccesDeniedHandler(){
        return new ResltFulAccesDeniedHandler();
    }

    @Bean
    public ResfulAathenticationEntryPoint resfulAathenticationEntryPoint(){
        return new ResfulAathenticationEntryPoint();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }

    @Bean
    public AccessLimitInterceptor accessLimitInterceptor(){
        return new AccessLimitInterceptor();
    }
}
