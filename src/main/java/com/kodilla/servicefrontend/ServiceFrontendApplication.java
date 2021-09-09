package com.kodilla.servicefrontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"com.kodilla.servicefrontend"})
public class ServiceFrontendApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ServiceFrontendApplication.class, args);
    }
}
