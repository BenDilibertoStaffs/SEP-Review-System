package com.sep.restarauntreview.service;

import com.sep.restarauntreview.domain.Review;
import com.sep.restarauntreview.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class ReviewService {

    public final ReviewRepository reviewRepository;

    public Collection<Review> getAllForRestaurant(String restaurant) {
        return reviewRepository.getAllForRestaurant(restaurant);
    }

    public void save(Review review) {
        reviewRepository.save(review);
    }

    public void deleteReviewsForRestaurant(String restaurant) {
        reviewRepository.deleteReviewsByRestaurant(restaurant);
    }
}
