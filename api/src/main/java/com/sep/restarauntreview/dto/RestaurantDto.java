package com.sep.restarauntreview.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Getter
public class RestaurantDto {
    private final String id;
    private final String name;
}
