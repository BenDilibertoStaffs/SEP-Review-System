package com.sep.restarauntreview.controller;

import com.sep.restarauntreview.domain.transformer.UserTransformer;
import com.sep.restarauntreview.dto.UserDto;
import com.sep.restarauntreview.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserTransformer userTransformer;

    @PostMapping("/")
    public ResponseEntity<String> verifyUserCredentials(@RequestBody UserDto userDto) {
        if (authService.areCredentialsValid(userTransformer.transform(userDto))) {
            return ResponseEntity.ok(String.format("Authenticated user with username %s", userDto.getUsername()));
        } else {
            return ResponseEntity.internalServerError().body("Cannot authorise user");
        }
    }
}
