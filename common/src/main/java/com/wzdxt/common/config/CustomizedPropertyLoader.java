package com.wzdxt.common.config;

import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.io.IOException;
import java.util.List;

/**
 * Created by wzdxt on 2018/6/29.
 */
@Configuration
public class CustomizedPropertyLoader implements ApplicationListener<ApplicationEnvironmentPreparedEvent>, Ordered {
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment = event.getEnvironment();
        String location = environment.getProperty("local.config.path");
        String resolvedLocation = environment.resolveRequiredPlaceholders(location);
        try {
            List<PropertySourceLoader> propertySourceLoaders = SpringFactoriesLoader.loadFactories(
                    PropertySourceLoader.class, getClass().getClassLoader());
            for (PropertySourceLoader loader : propertySourceLoaders) {
                for (String ext : loader.getFileExtensions()) {
                    if (location.endsWith(ext)) {
                        MutablePropertySources propertySources = environment.getPropertySources();
                        Resource resource = new FileSystemResource(resolvedLocation);
                        List<PropertySource<?>> load = loader.load(resource.toString(), resource);
                        for (PropertySource<?> propertySource : load) {
                            propertySources.addLast(propertySource);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public int getOrder() {
        return ConfigFileApplicationListener.DEFAULT_ORDER + 10;
    }

}
