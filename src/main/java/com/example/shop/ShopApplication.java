package com.example.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ShopApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ShopApplication.class);
        application.setAddCommandLineProperties(false);
        SpringApplication.run(ShopApplication.class, args);
    }

}
