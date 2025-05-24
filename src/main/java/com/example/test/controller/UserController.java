package com.example.test.controller;

import com.example.test.dto.req.CreateUser;
import com.example.test.dto.res.UserResponse;
import com.example.test.entity.User;
import com.example.test.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<UserResponse> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public UserResponse createUser(@RequestBody CreateUser createUser) {
        return userService.saveUser(createUser);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody User updateUser) {
        return userService.updateUser(id, updateUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
