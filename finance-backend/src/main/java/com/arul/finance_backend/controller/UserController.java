package com.arul.finance_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arul.finance_backend.dtos.UserDto;
import com.arul.finance_backend.model.User;
import com.arul.finance_backend.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/users")
public class UserController {
    

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<?> addUser(@Valid @RequestBody User user, @RequestParam Long userId) {
        userService.addUser(user, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

    
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId, @RequestParam Long currentUserId) {
        return ResponseEntity.ok(
            userService.getUser(userId, currentUserId)
        );
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser( @PathVariable Long id, @RequestBody User user,  @RequestParam Long userId) {

        return ResponseEntity.ok(userService.updateUser(id, user, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@Valid @PathVariable Long id,  @RequestParam Long userId){
        userService.deleteUser(id, userId);
        return ResponseEntity.ok("User Deleted Successfully");
    }
    

}
