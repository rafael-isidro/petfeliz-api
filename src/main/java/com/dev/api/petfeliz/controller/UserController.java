package com.dev.api.petfeliz.controller;

import com.dev.api.petfeliz.entity.user.UserEntity;
import com.dev.api.petfeliz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable Long id) {
        return userService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID " + id + " not found"));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public UserEntity updateUser(@PathVariable Long id, @RequestBody UserEntity user) {
        return userService.update(id, user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
