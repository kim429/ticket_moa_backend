package com.ssafy.ticket_moa_app.controller;

import com.ssafy.ticket_moa_app.dto.Reservation;
import com.ssafy.ticket_moa_app.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Reservation> getReservationsByUser(@PathVariable String userId) {
        return service.getReservationsByUser(userId);
    }

    @GetMapping("/{resId}")
    @Operation(summary = "예매 상세 내역(단건) 조회")
    public Reservation getReservationById(@PathVariable int resId) {
        return service.getReservationById(resId);
    }

    @PutMapping("/confirm/{resId}")
    @Operation(summary = "입장 확인 true/false 반환 (기본이 false)")
    public ResponseEntity<?> confirmEntry(@PathVariable int resId) {
        service.confirmEntry(resId);
        return ResponseEntity.ok("입장 확인 완료");
    }

}
