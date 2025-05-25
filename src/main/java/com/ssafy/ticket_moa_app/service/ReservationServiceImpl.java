package com.ssafy.ticket_moa_app.service;

import com.ssafy.ticket_moa_app.dao.ReservationDao;
import com.ssafy.ticket_moa_app.dto.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationDao dao;

    @Override
    public List<Reservation> getReservationsByUser(String userId) {
        return dao.selectByUserId(userId);
    }

    @Override
    public Reservation getReservationById(int resId) {
        return dao.selectByResId(resId);
    }
}
