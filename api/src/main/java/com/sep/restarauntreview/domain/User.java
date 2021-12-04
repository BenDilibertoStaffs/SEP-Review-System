package com.sep.restarauntreview.domain;

import lombok.*;

@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Getter
public class User {
    private final String username;
    private final String password;
}