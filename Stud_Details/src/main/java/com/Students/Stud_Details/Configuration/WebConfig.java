package com.Students.Stud_Details.Configuration;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
@org.springframework.context.annotation.Configuration
@EnableWebMvc
public class WebConfig {
	
	public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
}
