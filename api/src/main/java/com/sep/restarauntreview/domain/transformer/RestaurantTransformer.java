package com.sep.restarauntreview.domain.transformer;

import com.sep.restarauntreview.domain.Restaurant;
import com.sep.restarauntreview.dto.RestaurantDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class RestaurantTransformer {

    public Restaurant transform(RestaurantDto restaurantDto) {
        return Restaurant.builder()
                .id(restaurantDto.getId())
                .name(restaurantDto.getName())
                .build();
    }

    public RestaurantDto transform(Restaurant restaurant) {
        return RestaurantDto.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .build();
    }

    public Collection<RestaurantDto> transform(Collection<Restaurant> restaurants) {
        return restaurants.stream().map(this::transform).collect(Collectors.toList());
    }
}
