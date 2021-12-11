package com.sep.restarauntreview.controller;

import com.sep.restarauntreview.domain.User;
import com.sep.restarauntreview.domain.transformer.UserTransformer;
import com.sep.restarauntreview.dto.UserDto;
import com.sep.restarauntreview.service.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    private static final String USERNAME = "PerryThePlatypus";
    private static final String PASSWORD = "DoofenshmirtzStinks";

    @Mock
    private User user;

    @Mock
    private AuthService authService;

    @Mock
    private UserTransformer userTransformer;

    @InjectMocks
    private AuthController onTest;

    @Test
    public void verifyUserCredentials_whenCredentialsAreValid_thenReturnOkResponse() {
        mockCredentialValidity(true);

        ResponseEntity<String> result = onTest.verifyUserCredentials(createUserDto());
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void verifyUserCredentials_whenCredentialsAreInvalid_thenReturn500Response() {
        mockCredentialValidity(false);

        ResponseEntity<String> result = onTest.verifyUserCredentials(createUserDto());
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void mockCredentialValidity(boolean valid) {
        when(userTransformer.transform(createUserDto())).thenReturn(user);
        when(authService.areCredentialsValid(user)).thenReturn(valid);
    }

    private UserDto createUserDto() {
        return UserDto.builder().username(USERNAME).password(PASSWORD).build();
    }
}