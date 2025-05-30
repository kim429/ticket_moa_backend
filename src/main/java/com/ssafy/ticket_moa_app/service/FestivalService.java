package com.ssafy.ticket_moa_app.service;

import com.ssafy.ticket_moa_app.dto.Festival;

import java.util.List;

public interface FestivalService {
    Festival getTopFestival();
    List<Festival> getTodayFestivals();
    List<Festival> getAllFestivals();
    Festival getFestivalById(int id);
    int countTodayFestivals(); // 오늘 공연 수 반환
    List<Festival> getTodayNotiFestivalsByUser(String userId);

}
