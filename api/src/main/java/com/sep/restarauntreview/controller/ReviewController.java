package com.sep.restarauntreview.controller;

import com.sep.restarauntreview.domain.transformer.ReviewTransformer;
import com.sep.restarauntreview.dto.ReviewDto;
import com.sep.restarauntreview.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@AllArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewTransformer reviewTransformer;

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Collection<ReviewDto>> getAllForRestaurant(@PathVariable("restaurantId") String restaurantId) {
        return ResponseEntity.ok(reviewTransformer.transform(reviewService.getAllForRestaurant(restaurantId)));
    }

    @PostMapping("/")
    public ResponseEntity<String> save(@RequestBody ReviewDto reviewDto) {
        try {
            reviewService.save(reviewTransformer.transform(reviewDto));
            return ResponseEntity.ok(String.format("Created review: %s", reviewDto));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(String.format("Failed to create review: %s", reviewDto.toString()));
        }
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<String> deleteForRestaurant(@PathVariable("restaurantId") String restaurantId) {
        try {
            reviewService.deleteReviewsForRestaurant(restaurantId);
            return ResponseEntity.ok(String.format("Successfully deleted reviews for restaurant with id %s", restaurantId));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(String.format("Failed to delete reviews for restaurant with id %s. Exception was: %s", restaurantId, e.getMessage()));
        }
    }
}
