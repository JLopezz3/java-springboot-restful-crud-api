package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RestClient {

    private static final String GET_ALL_USERS_API = "http://localhost:8080/api/users";
    private static final String GET_USER_BY_ID_API = "http://localhost:8080/api/users/{id}";
    private static final String CREATE_USER_API = "http://localhost:8080/api/users";
    private static final String UPDATE_USER_API = "http://localhost:8080/api/users/{id}";
    private static final String DELETE_USER_API = "http://localhost:8080/api/users/{id}";

    static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        callGetAllUsersAPI();
        callGetAllUsersAPI();
        callCreateUserAPI();
        callUpdateUserAPI();
        callDeleteUserAPI();
    }

    private static void callGetAllUsersAPI(){

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> result = restTemplate.exchange(GET_ALL_USERS_API, HttpMethod.GET, entity, String.class);
        System.out.println(result);
    }

    private static void callGetUserByIdAPI(){

        Map<String, Integer> param = new HashMap<>();
        param.put("id", 1);

        User user = restTemplate.getForObject(GET_USER_BY_ID_API, User.class, param);
        System.out.println(user.getFirstName());
        System.out.println(user.getLastName());
        System.out.println(user.getEmail());
    }

    private static void callCreateUserAPI(){

        User user = new User("Jerel", "Lopez", "jerel@gmail.com");
        ResponseEntity<User> user2 = restTemplate.postForEntity(CREATE_USER_API, user, User.class);
        System.out.println(user2);
    }

    private static void callUpdateUserAPI(){

        Map<String, Integer> param = new HashMap<>();
        param.put("id", 1);
        User updateUser = new User("Jer", "Lo", "jer@gmail.com");
        restTemplate.put(UPDATE_USER_API, updateUser, param);
    }

    private static void callDeleteUserAPI(){
        Map<String, Integer> param = new HashMap<>();
        param.put("id", 1);
        restTemplate.delete(DELETE_USER_API, param);

    }
}
