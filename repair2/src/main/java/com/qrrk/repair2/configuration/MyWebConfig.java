package com.qrrk.repair2.configuration;


import com.qrrk.repair2.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebConfig implements WebMvcConfigurer {

//    设置拦截路径
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/repair.html","/submitInfo","/repair","/gosend","/gologin","/login","/login.html","/css/**","/bootstrap/**","/fonts/**","/js/**","/picture/**");
    }

//    页面跳转
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
       registry.addViewController("/login.html").setViewName("login");
       registry.addViewController("/repair.html").setViewName("repair");

    }
}
