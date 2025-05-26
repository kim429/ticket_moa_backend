package com.ssafy.ticket_moa_app.dao;

import com.ssafy.ticket_moa_app.dto.Festival;
import java.util.List;

public interface FestivalDao {
    Festival selectTopFestival();
    List<Festival> selectTodayFestivals();
    List<Festival> selectAllFestivals();
    Festival selectFestivalById(int id);
    // 공연 정보를 ID로 조회
    Festival selectById(int fesId);
}
