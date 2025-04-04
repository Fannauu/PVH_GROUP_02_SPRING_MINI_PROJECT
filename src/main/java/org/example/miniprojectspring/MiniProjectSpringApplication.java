package org.example.miniprojectspring;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Gamified Habit Tracker API",
                version = "1.0",
                description = "API documentation for the Gamified Habit Tracker application\n"
        )
)

@SpringBootApplication
public class MiniProjectSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniProjectSpringApplication.class, args);
    }

}
