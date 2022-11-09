package com.example.resttemplate;

import com.example.resttemplate.service.RestTemplateOperations;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestTemplateApplication {

    public static void main(String[] args) {
        RestTemplateOperations restTemplateOperations = new RestTemplateOperations(new RestTemplate());
        restTemplateOperations.allOperationsMethod();
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
