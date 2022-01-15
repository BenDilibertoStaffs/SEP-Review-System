package com.sep.restarauntreview.domain.transformer;

import com.sep.restarauntreview.domain.Review;
import com.sep.restarauntreview.dto.ReviewDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class ReviewTransformer {

    public Review transform(ReviewDto reviewDto) {
        return Review.builder()
                .id(reviewDto.getId())
                .restarauntId(reviewDto.getRestaurantId())
                .cuisines(reviewDto.getCuisines())
                .quality(reviewDto.getQuality())
                .value(reviewDto.getValue())
                .build();
    }

    public ReviewDto transform(Review review) {
        return ReviewDto.builder()
                .id(review.getId())
                .restaurantId(review.getRestarauntId())
                .cuisines(review.getCuisines())
                .quality(review.getQuality())
                .value(review.getValue())
                .build();
    }

    public Collection<ReviewDto> transform(Collection<Review> reviews) {
        return reviews.stream().map(this::transform).collect(Collectors.toList());
    }
}
