package com.taotao.config;

import com.taotao.component.LoginHandlerInterceptor;
import com.taotao.component.MylocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * @ClassName MyMvcConfig
 * @Description
 * 使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
 * @Author tyq
 * @Date 2020/4/30 11:02
 * @Version V1.0
 **/
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 浏览器发送请求/taotao来到success
        registry.addViewController("/taotao").setViewName("success");
    }

    // 所有的WebMvcConfigurerAdapter都会起作用；使用@Bean将组件注册到容器中
    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter () {
        WebMvcConfigurerAdapter webMvcConfigurerAdapter = new WebMvcConfigurerAdapter(){
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");

            }
            // 注册登录拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                // SpringBoot已经做好了静态资源映射 （*.css , *.js）
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/"
                        ,"/index.html","/user/login","classpath:/static/asserts/css/**","classpath:/static/asserts/js" +
                                "/**","classpath:/static/asserts/img/**");
            }

            // 静态资源
//            @Override
//            public void addResourceHandlers(ResourceHandlerRegistry registry) {
//                // 静态资源
//                registry.addResourceHandler("/**").addResourceLocations("classpath:/static");
//                // webjar文件
//                registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
//            }

        };

        return webMvcConfigurerAdapter;
    }

    @Bean
    public LocaleResolver localeResolver () {
        return new MylocaleResolver();
    }
}
