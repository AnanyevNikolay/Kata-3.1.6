package com.example.resttemplate.service;

import com.example.resttemplate.model.User;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class RestTemplateOperations {

    private RestTemplate restTemplate;
    private String url = "http://94.198.50.185:7081/api/users";

    public RestTemplateOperations(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void allOperationsMethod() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List> responseEntity = getUsersList(httpEntity);
        System.out.println(httpHeaders.get("Cookie"));
        httpHeaders.set("Cookie", String.join(";", Objects.requireNonNull(responseEntity.getHeaders().get("Set-Cookie"))));

        User newUser = new User();
        newUser.setId(3L);
        newUser.setName("James");
        newUser.setLastName("Brown");
        newUser.setAge((byte) 30);
        httpEntity = new HttpEntity<>(newUser, httpHeaders);
        String part1 = addUser(httpEntity);

        newUser.setName("Thomas");
        newUser.setLastName("Shelby");
        httpEntity = new HttpEntity<>(newUser, httpHeaders);
        String part2 = updateUser(httpEntity);

        String part3= deleteUser(httpEntity, newUser);

        System.out.println(part1 + part2 + part3);

    }

    public ResponseEntity<List> getUsersList(HttpEntity<Object> httpEntity) {
        ResponseEntity<List> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, List.class);
        HttpHeaders responseHeaders = responseEntity.getHeaders();
        return responseEntity;
    }

    public String addUser(HttpEntity<Object> httpEntity) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        String userDetails = responseEntity.getBody();
        return userDetails;
    }

    public String updateUser(HttpEntity<Object> httpEntity) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, String.class);
        String userDetails = responseEntity.getBody();
        return userDetails;
    }

    public String deleteUser(HttpEntity<Object> httpEntity, User user) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(url + "/" + user.getId(), HttpMethod.DELETE, httpEntity, String.class);
        String userDetails = responseEntity.getBody();
        return userDetails;
    }



}
