package com.example.userAggregationService.controller;

import com.example.userAggregationService.model.User;
import com.example.userAggregationService.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    @Operation(summary = "Retrieve a list of users from all DBs added in application.yml")
    public List<User> getUsers() {
        return userService.getAllUsersService();
    }
}