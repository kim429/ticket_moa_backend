package com.ssafy.ticket_moa_app.service;

import com.ssafy.ticket_moa_app.dto.Review;

import java.util.List;

public interface ReviewService {
    int addReview(Review review);
    List<Review> getAllReviews();
    List<Review> getReviewsByFestivalId(int fesId);
    int updateReview(Review review);
    int deleteReview(int reviewId);
}
