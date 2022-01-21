package com.sep.restarauntreview.service;

import com.sep.restarauntreview.domain.Restaurant;
import com.sep.restarauntreview.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {

    private static final String RESTAURANT_NAME = "Borgir";

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private Restaurant restaurant;

    @InjectMocks
    private RestaurantService onTest;

    @Test
    public void getAll_whenRestaurantsReturned_returnListOfRestaurants() {
        mockAllRestaurants();

        Collection<Restaurant> result = onTest.getAll();
        assertThat(result).containsExactly(restaurant);
    }

    @Test
    public void getAll_whenNoRestaurantsReturned_returnEmptyList() {
        mockNoRestaurants();

        Collection<Restaurant> result = onTest.getAll();
        assertThat(result).isEmpty();
    }

    @Test
    public void getById_whenRepoReturnsRestaurant_returnRestaurant() {
        mockById();

        Restaurant result = onTest.getById(RESTAURANT_NAME);
        assertThat(result).isEqualTo(restaurant);
    }

    @Test
    public void getById_whenRepoReturnsNoRestaurant_returnNull() {
        Restaurant result = onTest.getById(RESTAURANT_NAME);
        assertThat(result).isNull();
    }

    private void mockAllRestaurants() {
        when(restaurantRepository.getAll()).thenReturn(Collections.singletonList(restaurant));
    }

    private void mockNoRestaurants() {
        when(restaurantRepository.getAll()).thenReturn(Collections.emptyList());
    }

    private void mockById() {
        when(restaurantRepository.getById(RESTAURANT_NAME)).thenReturn(restaurant);
    }
}