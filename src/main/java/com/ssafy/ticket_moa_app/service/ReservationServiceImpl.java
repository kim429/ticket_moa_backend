package com.ssafy.ticket_moa_app.service;

import com.ssafy.ticket_moa_app.dao.FestivalDao;
import com.ssafy.ticket_moa_app.dao.ReservationDao;
import com.ssafy.ticket_moa_app.dto.Festival;
import com.ssafy.ticket_moa_app.dto.Reservation;
import com.ssafy.ticket_moa_app.dto.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    private FestivalDao festivalDao;

    // 사용자가 예매한 공연 목록 조회
    @Override
    public List<Reservation> getReservationsByUser(String userId) {
        // 예매 목록 조회
        List<Reservation> reservations = reservationDao.selectByUserId(userId);
        for (Reservation reservation : reservations) {
            // 예매된 각 공연에 대한 정보(예: 공연명, 날짜, 장소) 포함
            Festival festival = reservation.getFestival();
            // 필요한 경우 추가적인 로직을 여기에 추가
        }
        return reservations;
    }

    @Override
    public Reservation getReservationById(int resId) {
        return reservationDao.selectByResId(resId);
    }

    @Override
    public boolean confirmEntry(int resId) {
        // 입장 확인 처리
        int rowsAffected = reservationDao.confirmEntry(resId);  // 입장 확인 처리
        if (rowsAffected > 0) {
            // 입장 확인이 성공적으로 처리되었으면, 해당 예매 정보의 used 상태를 조회하여 반환
            Reservation reservation = reservationDao.selectByResId(resId);
            return reservation.isUsed();  // used 상태 반환
        }
        return false;  // 예매 ID가 유효하지 않으면 false 반환
    }

    @Override
    public int countPastReservationsByUserId(String userId) {
        return reservationDao.countPastReservationsByUserId(userId);
    }

    @Override
    public boolean addReservation(Reservation reservationDTO) {
        // 공연 정보 조회
        Festival festival = festivalDao.selectById(reservationDTO.getFesId());
        if (festival == null) {
            return false; // 공연 정보가 없으면 실패
        }

        // 총 가격 계산
        int totalPrice = reservationDTO.getTicketCount() * festival.getPrice();
        reservationDTO.setTotalPrice(totalPrice);

        // 예매 정보 추가
        int rowsAffected = reservationDao.insertReservation(reservationDTO);
        if (rowsAffected > 0) {
            int resId = reservationDTO.getResId();

            // 좌석 추가 시 fesId도 함께 넘겨줘야 함
            if (reservationDTO.getSeats() != null && !reservationDTO.getSeats().isEmpty()) {
                // Map으로 fesId와 seats를 전달
                Map<String, Object> params = new HashMap<>();
                params.put("resId", resId);
                params.put("fesId", reservationDTO.getFesId());
                params.put("seats", reservationDTO.getSeats());

                // 좌석 정보 추가
                return reservationDao.insertReservationSeats(params) > 0;
            }
        }
        return false;
    }



    @Override
    public Reservation getReservationDetails(int resId) {
        // 예매 정보 조회
        Reservation reservation = reservationDao.selectByResId(resId);
        if (reservation != null) {
            // 예매된 좌석 정보 포함
            List<Seat> seats = reservationDao.selectSeatsByResId(resId);
            reservation.setSeats(seats); // 예매된 좌석 정보를 담기
        }
        return reservation;
    }

}
