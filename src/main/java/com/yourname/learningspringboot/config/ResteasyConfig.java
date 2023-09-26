package com.yourname.learningspringboot.config;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.springframework.context.annotation.Configuration;

@Configuration
@ApplicationPath("/")
public class ResteasyConfig extends Application {
}
