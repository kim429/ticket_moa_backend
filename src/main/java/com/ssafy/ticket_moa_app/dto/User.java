package com.ssafy.ticket_moa_app.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
    private String userId;       // 유저ID
    private String pass;         // 비밀번호
    private String name;         // 이름
    private String email;        // 이메일
    private String phone;        // 전화번호
    private String profile;      // 프로필사진
    private int points;          // 포인트
}
