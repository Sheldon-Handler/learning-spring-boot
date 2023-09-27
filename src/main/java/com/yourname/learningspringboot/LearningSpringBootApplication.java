package com.yourname.learningspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

@SpringBootApplication
public class LearningSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningSpringBootApplication.class, args);
    }
}
