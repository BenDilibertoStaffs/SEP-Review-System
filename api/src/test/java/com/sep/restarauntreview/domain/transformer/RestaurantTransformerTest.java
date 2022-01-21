package com.sep.restarauntreview.domain.transformer;

import com.sep.restarauntreview.domain.Restaurant;
import com.sep.restarauntreview.dto.RestaurantDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTransformerTest {

    private static final String ID = "PP";
    private static final String NAME = "PerrysPizza";

    private final RestaurantTransformer onTest = new RestaurantTransformer();

    @Test
    public void transform_whenDtoGiven_returnDomainObject() {
        RestaurantDto restaurantDto = createRestaurantDto();
        Restaurant result = onTest.transform(restaurantDto);

        assertThat(result.getId()).isEqualTo(restaurantDto.getId());
        assertThat(result.getName()).isEqualTo(restaurantDto.getName());
    }

    @Test
    public void transform_whenDomainObjectGiven_returnDto() {
        Restaurant restaurant = createRestaurant();
        RestaurantDto result = onTest.transform(restaurant);

        assertThat(result.getId()).isEqualTo(restaurant.getId());
        assertThat(result.getName()).isEqualTo(restaurant.getName());
    }

    private Restaurant createRestaurant() {
        return Restaurant.builder().id(ID).name(NAME).build();
    }

    private RestaurantDto createRestaurantDto() {
        return RestaurantDto.builder().id(ID).name(NAME).build();
    }
}