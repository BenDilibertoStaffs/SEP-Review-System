package com.sep.restarauntreview.domain;

import lombok.*;

@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Getter
public class Restaurant {
    private final String id;
    private final String name;
}
