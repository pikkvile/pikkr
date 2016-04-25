package com.pikkvile.pikkr.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CommonConfig extends WebMvcConfigurerAdapter {

    @Value("${pikkr.files-path}")
    String filesPath;

    @Value("${pikkr.files-url-base}")
    String filesUrlBase;

    @Bean
    static public PropertyPlaceholderConfigurer placeHolderConfigurer() {
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();

        propertyPlaceholderConfigurer.setLocations(
                new ClassPathResource("application.properties"),
                new FileSystemResource("/opt/java/pikkr/application.properties"));

        propertyPlaceholderConfigurer.setIgnoreResourceNotFound(true);
        return propertyPlaceholderConfigurer;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(filesUrlBase + "**").addResourceLocations("file:///" + filesPath);
    }
}
