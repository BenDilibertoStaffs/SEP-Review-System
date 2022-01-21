package com.sep.restarauntreview.service;

import com.sep.restarauntreview.domain.Restaurant;
import com.sep.restarauntreview.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public Collection<Restaurant> getAll() {
        return restaurantRepository.getAll();
    }

    public Restaurant getById(String id) {
        return restaurantRepository.getById(id);
    }

    public void save(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    public void deleteById(String id) {
        restaurantRepository.deleteById(id);
    }
}
