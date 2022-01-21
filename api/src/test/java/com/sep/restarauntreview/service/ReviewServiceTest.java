package com.sep.restarauntreview.service;

import com.sep.restarauntreview.domain.Review;
import com.sep.restarauntreview.repository.ReviewRepository;
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
public class ReviewServiceTest {

    public static final String RESTAURANT_NAME = "Borgir";

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private Review review;

    @InjectMocks
    private ReviewService onTest;

    @Test
    public void getAllForRestaurant_whenServiceReturnsReview_thenReturnListOfReview() {
        mockReviewForRestaurant();

        Collection<Review> result = onTest.getAllForRestaurant(RESTAURANT_NAME);
        assertThat(result).containsExactly(review);
    }

    @Test
    public void getAllForRestaurant_whenServiceReturnsNothing_thenReturnEmptyList() {
        mockEmptyReturn();

        Collection<Review> result = onTest.getAllForRestaurant(RESTAURANT_NAME);
        assertThat(result).isEmpty();
    }

    private void mockReviewForRestaurant() {
        when(reviewRepository.getAllForRestaurant(RESTAURANT_NAME)).thenReturn(Collections.singletonList(review));
    }

    private void mockEmptyReturn() {
        when(reviewRepository.getAllForRestaurant(RESTAURANT_NAME)).thenReturn(Collections.emptyList());
    }
}