package com.gabuanii.student.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@ComponentScan({
        "com.gabuanii.student.controller",
        "com.gabuanii.student.service"
})
@EnableJpaRepositories("com.gabuanii.student.repository")
@EntityScan("com.gabuanii.student.entity")
@EnableFeignClients("com.gabuanii.student.feign")
@EnableEurekaClient
public class StudentApplication {

    @Value("${address.service.url}")
    private String addressServiceUrl;

    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class, args);
    }

    @Bean
    public WebClient webClient(){
        WebClient webClient = WebClient.builder()
                .baseUrl(addressServiceUrl)
                .build();
        return webClient;
    }

}
