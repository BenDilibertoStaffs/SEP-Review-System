package com.sep.restarauntreview.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Getter
public class UserDto {
    private final String username;
    private final String password;
}
