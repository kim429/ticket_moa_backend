package com.ssafy.ticket_moa_app.service;

import com.ssafy.ticket_moa_app.dto.Reservation;
import java.util.List;

public interface ReservationService {
    List<Reservation> getReservationsByUser(String userId);
    Reservation getReservationById(int resId);
}
