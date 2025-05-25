package com.ssafy.ticket_moa_app.dao;

import com.ssafy.ticket_moa_app.dto.Review;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReviewDao {
    int insert(Review review);
    List<Review> selectAll();
    List<Review> selectByFestivalId(@Param("fesId") int fesId);
    int update(Review review);
    int delete(@Param("reviewId") int reviewId);
}
