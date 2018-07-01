package com.wzdxt.product;

import com.wzdxt.common.config.CustomizedPropertyLoader;
import lombok.extern.slf4j.Slf4j;
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
public class Product implements WebMvcConfigurer {
    public static void main(String[] args) {
        new SpringApplicationBuilder(Product.class).listeners(new CustomizedPropertyLoader()).run();
    }
}
