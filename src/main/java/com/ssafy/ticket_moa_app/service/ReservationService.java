package com.ssafy.ticket_moa_app.service;

import com.ssafy.ticket_moa_app.dto.Reservation;
import java.util.List;

public interface ReservationService {
    List<Reservation> getReservationsByUser(String userId);
    Reservation getReservationById(int resId);
    boolean confirmEntry(int resId);
    int countPastReservationsByUserId(String userId);
    boolean addReservation(Reservation reservationDTO);

    // 예매 상세 내역 조회 (좌석 포함)
    Reservation getReservationDetails(int resId);
}
