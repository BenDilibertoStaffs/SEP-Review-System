package com.sep.restarauntreview.controller;

import com.sep.restarauntreview.domain.transformer.UserTransformer;
import com.sep.restarauntreview.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    private static final String USERNAME = "PerryThePlatypus";
    private static final String USERNAME_2 = "DrDoofenshmirtz";

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController onTest;

    @Test
    public void getAllUsernames_whenServiceReturnsListOfUsernames_thenUsernamesInResponse() {
        Collection<String> usernames = Arrays.asList(USERNAME, USERNAME_2);
        when(userService.getAllUsernames()).thenReturn(usernames);

        ResponseEntity<Collection<String>> response = onTest.getAllUsernames();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(usernames);
    }
}