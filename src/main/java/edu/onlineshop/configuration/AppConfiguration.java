package edu.onlineshop.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan("edu.onlineshop.dal")
@ImportResource("classpath:applicationContext.xml")
public class AppConfiguration {
}