package com.ssafy.ticket_moa_app.dao;

import com.ssafy.ticket_moa_app.dto.NotiFest;
import com.ssafy.ticket_moa_app.dto.Festival;

import java.util.List;

public interface NotiFestDao {
    int insertNotiFest(NotiFest notiFest);            // 알림 추가
    int deleteNotiFest(NotiFest notiFest);            // 알림 제거
    List<Festival> selectByUserId(String id);         // 사용자가 알림 설정한 공연 목록
    boolean exists(NotiFest notiFest);                // 알림 설정 여부 확인
    int incrementFestivalNoti(int fesId);             // 공연 noti +1
    int decrementFestivalNoti(int fesId);             // 공연 noti -1
    int countNotiUsersByFestival(int fesId);

}
