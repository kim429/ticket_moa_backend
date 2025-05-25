package com.ssafy.ticket_moa_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    private String seatRow; // 알파벳 좌석 열 (A~Z)
    private int seatCol;    // 좌석 행 (숫자)
}
