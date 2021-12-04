package com.sep.restarauntreview.domain.transformer;

import com.sep.restarauntreview.domain.User;
import com.sep.restarauntreview.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserTransformer {

    public User transform(UserDto userDto) {
        return User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .build();
    }

    public UserDto transform(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}
