package com.ssafy.ticket_moa_app.controller;

import com.ssafy.ticket_moa_app.dto.Reservation;
import com.ssafy.ticket_moa_app.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService service;

    @GetMapping("/user/{userId}")
    @Operation(summary = "사용자의 전체 예매 내역 조회")
    public List<Reservation> getReservationsByUser(@PathVariable("userId") String userId) {
        return service.getReservationsByUser(userId);
    }

    @GetMapping("/{resId}")
    @Operation(summary = "예매 상세 내역(단건) 조회")
    public Reservation getReservationById(@PathVariable("resId") int resId) {
        return service.getReservationById(resId);
    }

    @PutMapping("/confirm/{resId}")
    @Operation(summary = "입장 확인 true/false 반환 (기본이 false) ")
    public ResponseEntity<String> confirmEntry(@PathVariable("resId") int resId) {
        boolean isConfirmed = service.confirmEntry(resId);  // 입장 확인 상태 반환
        if (isConfirmed) {
            return ResponseEntity.ok("입장 확인 완료: true");
        } else {
            return ResponseEntity.ok("입장 확인 완료: false");
        }
    }

    @GetMapping("/user/{userId}/past-count")
    @Operation(summary = "사용자의 과거 공연 예매 개수 조회")
    public int countPastReservations(@PathVariable String userId) {
        return service.countPastReservationsByUserId(userId);
    }

    @PostMapping("/payment")
    @Operation(summary = "선택된 좌석에 대해 결제 처리 및 예매 정보 추가")
    public ResponseEntity<String> processPayment(@RequestBody Reservation reservationDTO) {
        // 결제 후 예매 정보 추가 (total_price 자동 계산)
        boolean isSuccess = service.addReservation(reservationDTO);
        if (isSuccess) {
            return ResponseEntity.ok("결제 완료 및 예매 정보 추가 성공");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("결제 실패");
    }

    // 사용자가 예매한 공연 목록 조회
    @GetMapping("/user/{userId}/list")
    @Operation(summary = "사용자가 예매한 공연 목록 조회")
    public List<Reservation> getUserReservations(@PathVariable("userId") String userId) {
        return service.getReservationsByUser(userId);
    }

    // 예매 상세 내역 조회 (좌석 포함)
    @GetMapping("/user/{userId}/reservation/{resId}")
    @Operation(summary = "예매 상세 내역 조회 (좌석 포함)")
    public Reservation getReservationDetails(@PathVariable("userId") String userId, @PathVariable("resId") int resId) {
        return service.getReservationDetails(resId);
    }
}

