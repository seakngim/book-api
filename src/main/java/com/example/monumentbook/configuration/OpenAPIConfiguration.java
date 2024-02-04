package com.example.monumentbook.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer",
        in = SecuritySchemeIn.HEADER
)
@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
//                        name = "boeun mno",
//                        email = "boeurnmnor@gmil.com"
                ),
                title = "Leaving Management System",
                version = "1.0",
                description = "test api report system",
                license = @License(
                        name = "Ai.",
                        url = "https://www.ai.edu.kh/"
                ),
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                  //       description = "Local Environment",
                  //       url = "http://localhost:4141"
                ),
                  @Server(
                 //       description = "PROD Environment",
                 //       url = ""
                  )
        }
)
public class OpenAPIConfiguration {
}
