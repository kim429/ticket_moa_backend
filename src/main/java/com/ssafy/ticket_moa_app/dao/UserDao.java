package com.ssafy.ticket_moa_app.dao;

import com.ssafy.ticket_moa_app.dto.User;

public interface UserDao {

    // 사용자 정보 추가
    int insert(User user);

    // 사용자 point 정보 수정
    int updatePoint(User user);

    // 사용자 정보 조회
    User selectById(String userid);

    //사용자 정보 조회
    User selectByUser(User user);
    User login(String userId, String pass);
}