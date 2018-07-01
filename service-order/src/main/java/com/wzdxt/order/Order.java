package com.wzdxt.order;

import com.wzdxt.common.config.CustomizedPropertyLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by wzdxt on 2018/6/23.
 */
@SpringBootApplication
@EnableAutoConfiguration
@Slf4j
public class Order implements WebMvcConfigurer {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Order.class).listeners(new CustomizedPropertyLoader()).run();
    }
}
