package com.sep.restarauntreview.domain;

import lombok.*;

import java.util.Collection;

@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Getter
public class Review {
    private final String id;
    private final String restarauntId;
    private final Collection<String> cuisines;
    private final int quality;
    private final int value;
}
