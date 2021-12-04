package com.sep.restarauntreview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class UserDto {
    private final String username;
    private final String password;
}
