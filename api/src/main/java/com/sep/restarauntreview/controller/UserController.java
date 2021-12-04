package com.sep.restarauntreview.controller;

import com.sep.restarauntreview.domain.transformer.UserTransformer;
import com.sep.restarauntreview.dto.UserDto;
import com.sep.restarauntreview.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@AllArgsConstructor
@RequestMapping("/api/user")
public record UserController(UserService userService,
                             UserTransformer userTransformer) {

    @GetMapping("/")
    public ResponseEntity<Collection<String>> getAllUsernames() {
        return ResponseEntity.ok(userService.getAllUsernames());
    }

    @PostMapping("/")
    public ResponseEntity<String> saveUser(@RequestBody UserDto userDto) {
        userService.save(userTransformer.transform(userDto));
        return ResponseEntity.ok("Yeet");
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteUserByUsername(@PathVariable("username") String username) {
        try {
            userService.deleteByUsername(username);
            return ResponseEntity.ok(String.format("Successfully deleted user with username %s", username));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(String.format("Failed to delete user with username %s. Exception was: %s", username, e.getMessage()));
        }
    }
}