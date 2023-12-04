package com.example.monumentbook;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Leaving Management System", version = "1.0", description = "LMS API Mobile - Developing by PVH Class IOS - Group 1 - Generation 11st"))
public class MonumentBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonumentBookApplication.class, args);
    }

}
