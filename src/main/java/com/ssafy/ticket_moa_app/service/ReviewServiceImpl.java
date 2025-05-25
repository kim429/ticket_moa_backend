package com.ssafy.ticket_moa_app.service;

import com.ssafy.ticket_moa_app.dao.ReviewDao;
import com.ssafy.ticket_moa_app.dto.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDao reviewDao;

    @Override
    public int addReview(Review review) {
        return reviewDao.insert(review);
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewDao.selectAll();
    }

    @Override
    public List<Review> getReviewsByFestivalId(int fesId) {
        return reviewDao.selectByFestivalId(fesId);
    }

    @Override
    public int updateReview(Review review) {
        return reviewDao.update(review);
    }

    @Override
    public int deleteReview(int reviewId) {
        return reviewDao.delete(reviewId);
    }
}
