package com.ssafy.ticket_moa_app.dao;

import com.ssafy.ticket_moa_app.dto.Reservation;
import java.util.List;

public interface ReservationDao {
    List<Reservation> selectByUserId(String userId);  // 전체 예매 내역
    Reservation selectByResId(int resId);             // 단건 조회
    int confirmEntry(int resId);                      // 입장 확인 처리
    int countPastReservationsByUserId(String userId); // 과거 공연 예매 개수
}
