package com.ssafy.ticket_moa_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Festival {
    private int fesId;                // 공연ID
    private String title;             // 공연명
    private String hallName;          // 공연장
    private LocalDate fesDate;        // 공연날짜
    private LocalDateTime ticketTime; // 티켓팅시간
    private String posterImg;         // 포스터 이미지
    private String desImg;            // 설명 이미지
    private int price;                // 가격
    private int noti;          // 알람
}
