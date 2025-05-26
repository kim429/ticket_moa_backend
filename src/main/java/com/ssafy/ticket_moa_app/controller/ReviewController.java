package com.ssafy.ticket_moa_app.controller;

import com.ssafy.ticket_moa_app.dto.Review;
import com.ssafy.ticket_moa_app.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    @Operation(summary = "새로운 기대평을 추가합니다.")
    public int addReview(@RequestBody Review review) {
        return reviewService.addReview(review);
    }

    @GetMapping
    @Operation(summary = "모든 기대평을 조회합니다.")
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/festival/{fesId}")
    @Operation(summary = "특정 공연의 기대평을 조회합니다.")
    public List<Review> getReviewsByFestivalId(@PathVariable("fesId") int fesId) {
        return reviewService.getReviewsByFestivalId(fesId);
    }

    @PutMapping
    @Operation(summary = "기대평을 수정합니다.")
    public int updateReview(@RequestBody Review review) {
        return reviewService.updateReview(review);
    }

    @DeleteMapping("/{reviewId}")
    @Operation(summary = "기대평을 삭제합니다.")
    public int deleteReview(@PathVariable("reviewId") int reviewId) {
        return reviewService.deleteReview(reviewId);
    }
}
