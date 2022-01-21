package com.sep.restarauntreview.domain.transformer;

import com.sep.restarauntreview.domain.User;
import com.sep.restarauntreview.dto.UserDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTransformerTest {

    private static final String USERNAME = "PerryThePlatypus";
    private static final String PASSWORD = "Platypus4Eva";

    private final UserTransformer onTest = new UserTransformer();

    @Test
    public void transform_whenDtoGiven_returnDomainObject() {
        UserDto userDto = createDto();
        User result = onTest.transform(userDto);

        assertThat(result.getUsername()).isEqualTo(userDto.getUsername());
        assertThat(result.getPassword()).isEqualTo(userDto.getPassword());
    }

    @Test
    public void transform_whenDomainObjectGiven_returnDto() {
        User user = createUser();
        UserDto result = onTest.transform(user);

        assertThat(result.getUsername()).isEqualTo(user.getUsername());
        assertThat(result.getPassword()).isEqualTo(user.getPassword());
    }

    private User createUser() {
        return User.builder()
                .username(USERNAME)
                .password(PASSWORD)
                .build();
    }

    private UserDto createDto() {
        return UserDto.builder()
                .username(USERNAME)
                .password(PASSWORD)
                .build();
    }
}