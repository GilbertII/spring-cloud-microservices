package com.gabuanii.address.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({
        "com.gabuanii.address.controller",
        "com.gabuanii.address.service",
        "com.gabuanii.address.execption"
})
@EntityScan("com.gabuanii.address.entity")
@EnableJpaRepositories("com.gabuanii.address.repository")
@EnableEurekaClient
public class AddressApplication {

    public static void main(String[] args) {
        SpringApplication.run(AddressApplication.class, args);
    }

}
