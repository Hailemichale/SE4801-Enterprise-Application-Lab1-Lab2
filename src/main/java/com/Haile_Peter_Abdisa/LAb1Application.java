package com.Haile_Peter_Abdisa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
    title       = "Product Service API",
    version     = "1.0.0",
    description = "RESTful Product Catalogue — Lab 2"
))
public class LAb1Application {

    public static void main(String[] args) {
        SpringApplication.run(LAb1Application.class, args);
    }

}
