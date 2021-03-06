package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.interceptor.ItemAccessInterceptor;

@Configuration
public class ItemAccessConfig implements WebMvcConfigurer {
	
	@Autowired
    private ItemAccessInterceptor Interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(Interceptor)
          .addPathPatterns("/item/**");
    }
}
