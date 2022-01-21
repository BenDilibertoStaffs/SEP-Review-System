package com.sep.restarauntreview.controller;

import com.sep.restarauntreview.domain.Restaurant;
import com.sep.restarauntreview.domain.transformer.RestaurantTransformer;
import com.sep.restarauntreview.dto.RestaurantDto;
import com.sep.restarauntreview.service.RestaurantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestaurantControllerTest {

    private static final String RESTAURANT = "Borgirs";
    private static final String RESTAURANT_2 = "Kebop";

    @Mock
    private RestaurantService restaurantService;

    @Mock
    private RestaurantTransformer restaurantTransformer;

    @InjectMocks
    private RestaurantController onTest;

    @Test
    public void getAll_whenServiceReturnsRestaurants_thenRestaurantDtosInResponse() {
        mockAllRestaurants(RESTAURANT, RESTAURANT_2);

        ResponseEntity<Collection<RestaurantDto>> result = onTest.getAll();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(result.getBody()).stream().allMatch(restaurantDto -> Arrays.asList(RESTAURANT, RESTAURANT_2).contains(restaurantDto.getName()))).isTrue();
    }

    @Test
    public void getAll_whenServiceReturnsEmptyList_thenEmptyResponse() {
        mockAllRestaurants();

        ResponseEntity<Collection<RestaurantDto>> result = onTest.getAll();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(result.getBody()).isEmpty()).isTrue();
    }

    @Test
    public void getById_whenServiceReturnsRestaurant_thenRestaurantInResponse() {
        mockById(RESTAURANT);

        ResponseEntity<RestaurantDto> result = onTest.getById(RESTAURANT);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(result.getBody()).getName()).isEqualTo(RESTAURANT);
    }

    @Test
    public void getById_whenServiceReturnsNull_thenEmptyResponse() {
        mockNoRestaurantForId(RESTAURANT);

        ResponseEntity<RestaurantDto> result = onTest.getById(RESTAURANT);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(result.getBody()).getName()).isNull();
    }

    @Test
    public void save_whenServiceDoesNotError_thenReturnSuccess() {
        mockSave(RESTAURANT);

        ResponseEntity<String> result = onTest.save(createDto(RESTAURANT));
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    private void mockAllRestaurants(String... restaurantNames) {
        Collection<Restaurant> restaurants = Stream.of(restaurantNames).map(name -> Restaurant.builder().name(name).build()).collect(Collectors.toList());
        when(restaurantService.getAll()).thenReturn(restaurants);
        mockListTransformation(restaurants.toArray(Restaurant[]::new));
    }

    private void mockById(String restaurantName) {
        Restaurant restaurant = Restaurant.builder().name(restaurantName).build();
        when(restaurantService.getById(eq(restaurantName))).thenReturn(restaurant);
        mockTransformation(restaurant);
    }

    private void mockSave(String restaurantName) {
        mockToDomain(restaurantName);
    }

    private void mockNoRestaurantForId(String restaurantName) {
        Restaurant restaurant = Restaurant.builder().build();
        when(restaurantService.getById(eq(restaurantName))).thenReturn(restaurant);
        mockTransformation(restaurant);
    }

    private void mockListTransformation(Restaurant... restaurants) {
        Collection<RestaurantDto> restaurantDtos = Stream.of(restaurants).map(restaurant -> createDto(restaurant.getName())).collect(Collectors.toList());
        when(restaurantTransformer.transform(Arrays.asList(restaurants))).thenReturn(restaurantDtos);
    }

    private void mockTransformation(Restaurant restaurant) {
        when(restaurantTransformer.transform(restaurant)).thenReturn(createDto(restaurant.getName()));
    }

    private void mockToDomain(String restaurantName) {
        when(restaurantTransformer.transform(createDto(restaurantName))).thenReturn(Restaurant.builder().name(restaurantName).build());
    }

    private RestaurantDto createDto(String restaurantName) {
        return RestaurantDto.builder().name(restaurantName).build();
    }
}