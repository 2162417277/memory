package com.gnahz.config;


import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author 张伟洁
 * Date:2024-01-04-19:31
 * @create 忆项目(小白)
 * swagger的配置文件
 * http://localhost:端口号/swagger-ui.html
 * http://localhost:9999/swagger-ui.html
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * 创建API应用
     * apiInfo()增强api相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例，用来控制哪些接口暴露给Swagger来展现
     * 指定扫描的包路径来定义指定要建立的API的目录
     * @return
     */
    @Bean
    public Docket coreApiConfig(){
        /*指定文档类型为Swagger 2.0*/
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(adminApiInfo())
                /*将API分组为名为"adminApi"的组*/
                .groupName("adminApi")
                .select()
                /*选择了所有以"/gnahz/"开头的路径*/
                .paths(Predicates.and(PathSelectors.regex("/admin/.*")))
                .build();
    }

    private ApiInfo adminApiInfo(){
        return new ApiInfoBuilder()
                .title("忆系统---api文档")
                .description("忆系统接口描述")
                .version("1.0")
                .contact(new Contact("张伟洁","https://dhc.pythonanywhere.com/","2162417277@qq.com"))
                .build();
    }
}
