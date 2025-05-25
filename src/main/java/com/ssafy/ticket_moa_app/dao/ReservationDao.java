package com.ssafy.ticket_moa_app.dao;

import com.ssafy.ticket_moa_app.dto.Reservation;
import java.util.List;

public interface ReservationDao {
    List<Reservation> selectByUserId(String userId);  // 전체 예매 내역
    Reservation selectByResId(int resId);             // 단건 조회
}
