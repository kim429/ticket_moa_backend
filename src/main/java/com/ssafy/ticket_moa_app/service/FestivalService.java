package com.ssafy.ticket_moa_app.service;

import com.ssafy.ticket_moa_app.dto.Festival;

import java.util.List;

public interface FestivalService {
    Festival getTopFestival();
    List<Festival> getTodayFestivals();
    List<Festival> getAllFestivals();
    Festival getFestivalById(int id);
}
