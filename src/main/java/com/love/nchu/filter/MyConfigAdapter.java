package com.love.nchu.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootConfiguration
public class MyConfigAdapter extends WebMvcConfigurerAdapter {

    @Autowired
    MyInterceptor myInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/productCenter",
                                    "/product/**",
                                    "/products/**",
                                    "/projects/**",
                                    "/news/**",
                                    "/test/**",
                                    "/ForgetOrChangePassword/**",
                                    "/updatePassword",
                                    "/password/**",
                                    "/registry/**",
                                    "/papers/**",
                                    "/people/**",
                                    "/js/**",
                                    "/img/**",
                                    "/image/**",
                                    "/login",
                                    "/login_valid",
                                    "/login_success",
                                    "log_out",
                                    "/index",
                                    "/");
    }
}
