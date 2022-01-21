package com.sep.restarauntreview.service;

import com.sep.restarauntreview.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private static final String USERNAME = "Terry";
    private static final String USERNAME_2 = "Tony";

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService onTest;

    @Test
    public void getAllUsernames_whenRepoReturnsUsernames_thenReturnUsernames() {
        mockUsernames(USERNAME, USERNAME_2);

        Collection<String> result = onTest.getAllUsernames();
        assertThat(result).isEqualTo(Arrays.asList(USERNAME, USERNAME_2));
    }

    @Test
    public void getAllUsernames_whenRepoReturnsNoUsernames_thenReturnEmptyList() {
        mockUsernames();

        Collection<String> result = onTest.getAllUsernames();
        assertThat(result).isEmpty();
    }

    private void mockUsernames(String... usernames) {
        when(userRepository.getAllUsernames()).thenReturn(Arrays.asList(usernames));
    }
}