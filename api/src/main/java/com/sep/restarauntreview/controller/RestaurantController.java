package com.sep.restarauntreview.controller;

import com.sep.restarauntreview.domain.transformer.RestaurantTransformer;
import com.sep.restarauntreview.dto.RestaurantDto;
import com.sep.restarauntreview.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@AllArgsConstructor
@RequestMapping("/api/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantTransformer restaurantTransformer;

    @GetMapping("/")
    public ResponseEntity<Collection<RestaurantDto>> getAll() {
        return ResponseEntity.ok(restaurantTransformer.transform(restaurantService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(restaurantTransformer.transform(restaurantService.getById(id)));
    }

    @PostMapping("/")
    public ResponseEntity<String> save(@RequestBody RestaurantDto restaurantDto) {
        try {
            restaurantService.save(restaurantTransformer.transform(restaurantDto));
            return ResponseEntity.ok(String.format("Created restaurant: %s", restaurantDto));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(String.format("Failed to create restaurant: %s", restaurantDto.toString()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") String id) {
        try {
            restaurantService.deleteById(id);
            return ResponseEntity.ok(String.format("Successfully deleted restaurant with id %s", id));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(String.format("Failed to delete restaurant with id %s. Exception was: %s", id, e.getMessage()));
        }
    }
}
