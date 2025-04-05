package org.example.miniprojectspring;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@OpenAPIDefinition(
        info = @Info(
                title = "Gamified Habit Tracker API",
                version = "1.0",
                description = "API documentation for the Gamified Habit Tracker application"
        )
)

@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        in = SecuritySchemeIn.HEADER
)
@SpringBootApplication
public class MiniProjectSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniProjectSpringApplication.class, args);
    }

}
