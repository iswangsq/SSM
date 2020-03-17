package com.chuyue.config;

import com.chuyue.filter.AuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class FilterConfig extends WebMvcConfigurerAdapter {

    @Bean
    public AuthFilter authFilter() {
        return new AuthFilter();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器 对/login、请求不实行拦截
        registry.addInterceptor(authFilter()).addPathPatterns("/**").excludePathPatterns("/login").excludePathPatterns("/hello");
    }
}
