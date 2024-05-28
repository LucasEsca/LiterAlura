package com.alura.literalura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.alura.literalura")
public class LiteraluraApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }
}