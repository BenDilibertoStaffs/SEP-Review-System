package com.sep.restarauntreview.dto;

import lombok.*;

import java.util.Collection;

@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Getter
public class ReviewDto {
    private final String id;
    private final String restaurantId;
    private final Collection<String> cuisines;
    private final int quality;
    private final int value;
}
