package com.ssafy.ticket_moa_app.service;

import com.ssafy.ticket_moa_app.dao.FestivalDao;
import com.ssafy.ticket_moa_app.dto.Festival;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FestivalServiceImpl implements FestivalService {

    @Autowired
    private FestivalDao dao;

    @Override
    public Festival getTopFestival() {
        return dao.selectTopFestival();
    }

    @Override
    public List<Festival> getTodayFestivals() {
        return dao.selectTodayFestivals();
    }

    @Override
    public List<Festival> getAllFestivals() {
        return dao.selectAllFestivals();
    }

    @Override
    public Festival getFestivalById(int id) {
        return dao.selectFestivalById(id);
    }

    @Override
    public int countTodayFestivals() {
        return dao.countTodayFestivals();
    }

    @Override
    public List<Festival> getTodayNotiFestivalsByUser(String userId) {
        return dao.selectTodayNotiFestivalsByUser(userId);
    }
}
