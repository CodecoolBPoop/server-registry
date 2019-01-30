package com.codecool.shoppingcartservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ShoppingcartServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingcartServiceApplication.class, args);
    }

}

