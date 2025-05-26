package com.ssafy.ticket_moa_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    private int resId;
    private String userId;
    private int fesId;
    private Date resDate;
    private int ticketCount;
    private int totalPrice;
    private List<Seat> seats;  // 다중 좌석을 리스트로 받음
}
