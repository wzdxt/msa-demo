package com.wzdxt.front;

import com.wzdxt.common.config.CustomizedPropertyLoader;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by wzdxt on 2018/6/23.
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableZuulProxy
@PropertySource({"application.yml"})
@Import(CustomizedPropertyLoader.class)
public class Front implements WebMvcConfigurer {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Front.class).listeners(new CustomizedPropertyLoader()).run();
    }
}
