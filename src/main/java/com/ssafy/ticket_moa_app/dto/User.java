package com.ssafy.ticket_moa_app.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor //아무것도 없는 생성자
@AllArgsConstructor // 필드 다 하는 생성자
// 우리가 게터세터 할 필요 없어서 속도가 빠르다
public class User {
    private int id;             // 유저 ID
    private String email;       // 이메일
    private String pass;        // 비밀번호
    private String name;        // 이름
    private String profile;     // 프로필 사진
    private String phone;       // 전화번호
    private int points;         // 포인트

}
