package com.tpg.holidays.context;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.tpg.holidays.controllers"})
@Import(SessionConfig.class)
public class WebApplicationConfig extends WebMvcConfigurerAdapter {
}
