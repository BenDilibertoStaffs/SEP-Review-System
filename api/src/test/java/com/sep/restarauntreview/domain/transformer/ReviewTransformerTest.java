package com.sep.restarauntreview.domain.transformer;

import com.sep.restarauntreview.domain.Review;
import com.sep.restarauntreview.dto.ReviewDto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class ReviewTransformerTest {

    private static final String ID = "REVIEW";
    private static final String RESTAURANT = "Borgir";
    private static final Collection<String> CUISINES = Arrays.asList("Indian", "British");
    private static final int QUALITY = 3;
    private static final int VALUE = 1;

    private final ReviewTransformer onTest = new ReviewTransformer();

    @Test
    public void transform_whenDtoGiven_returnDomainObject() {
        ReviewDto reviewDto = createDto();
        Review result = onTest.transform(reviewDto);

        assertThat(result.getId()).isEqualTo(reviewDto.getId());
        assertThat(result.getRestarauntId()).isEqualTo(reviewDto.getRestaurantId());
        assertThat(result.getCuisines()).isEqualTo(reviewDto.getCuisines());
        assertThat(result.getQuality()).isEqualTo(reviewDto.getQuality());
        assertThat(result.getValue()).isEqualTo(reviewDto.getValue());
    }

    @Test
    public void transform_whenDomainObjectGiven_returnDto() {
        Review review = createReview();
        ReviewDto result = onTest.transform(review);

        assertThat(result.getId()).isEqualTo(review.getId());
        assertThat(result.getRestaurantId()).isEqualTo(review.getRestarauntId());
        assertThat(result.getCuisines()).isEqualTo(review.getCuisines());
        assertThat(result.getQuality()).isEqualTo(review.getQuality());
        assertThat(result.getValue()).isEqualTo(review.getValue());
    }

    private Review createReview() {
        return Review.builder()
                .id(ID)
                .restarauntId(RESTAURANT)
                .cuisines(CUISINES)
                .quality(QUALITY)
                .value(VALUE)
                .build();
    }

    private ReviewDto createDto() {
        return ReviewDto.builder()
                .id(ID)
                .restaurantId(RESTAURANT)
                .cuisines(CUISINES)
                .quality(QUALITY)
                .value(VALUE)
                .build();
    }
}