package com.example.monumentbook;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Monument_Book System", version = "1.0", description = "Monument_book - Developing by BOEURN MNOR AGA Institute - Group 5 - Generation 15st"))
public class MonumentBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonumentBookApplication.class, args);
    }

}
