package com.sep.restarauntreview.service;

import com.sep.restarauntreview.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthService onTest;

    @Test
    public void areCredentialsValid_whenRepoReturnsTrue_thenReturnTrue() {
        when(userRepository.areCredentialsValidForUser(any())).thenReturn(true);

        assertThat(onTest.areCredentialsValid(any())).isTrue();
    }

    @Test
    public void areCredentialsValid_whenRepoReturnsFalse_thenReturnFalse() {
        when(userRepository.areCredentialsValidForUser(any())).thenReturn(false);

        assertThat(onTest.areCredentialsValid(any())).isFalse();
    }

}