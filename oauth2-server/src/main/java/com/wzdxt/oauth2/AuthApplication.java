package com.wzdxt.oauth2;

import com.wzdxt.common.config.CustomizedPropertyLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Created by wzdxt on 2018/6/24.
 */
@SpringBootApplication
@EnableAutoConfiguration
@Slf4j
public class AuthApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AuthApplication.class).listeners(new CustomizedPropertyLoader()).run();
    }
}
