package com.tpg.holidays.context;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan(basePackages = {"com.tpg.holidays.controllers"})
public class WebApplicationConfig extends WebMvcConfigurerAdapter {
}
