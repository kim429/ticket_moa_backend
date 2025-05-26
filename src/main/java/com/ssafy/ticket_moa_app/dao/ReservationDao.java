package com.ssafy.ticket_moa_app.dao;

import com.ssafy.ticket_moa_app.dto.Reservation;
import com.ssafy.ticket_moa_app.dto.Seat;

import java.util.List;
import java.util.Map;

public interface ReservationDao {
    // 사용자 예매 목록 조회
    List<Reservation> selectByUserId(String userId);  // 전체 예매 내역
    Reservation selectByResId(int resId);             // 단건 조회
    int confirmEntry(int resId);                      // 입장 확인 처리
    int countPastReservationsByUserId(String userId); // 과거 공연 예매 개수

    // 예매 정보 추가
    int insertReservation(Reservation reservationDTO);

    // 좌석 정보 추가 (Map으로 변경하여 fesId와 seats를 전달)
    int insertReservationSeats(Map<String, Object> params);

    // 예매 좌석 정보 조회
    List<Seat> selectSeatsByResId(int resId);         // 해당 예매에 대한 좌석 정보


}
