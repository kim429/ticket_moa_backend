package com.ssafy.ticket_moa_app.service;

import com.ssafy.ticket_moa_app.dto.NotiFest;
import com.ssafy.ticket_moa_app.dto.Festival;

import java.util.List;

public interface NotiFestService {
    boolean toggleNoti(NotiFest notiFest);     // noti 추가 or 삭제 (true: 추가됨, false: 삭제됨)
    List<Festival> getUserNotiList(String id); // 유저의 알림 공연 목록
    int getNotiUserCount(int fesId);
    void addFestivalNoti(String userId, int fesId);
    boolean isFestivalNotified(String id, int fesId);
    boolean hasAnyNotifiedFestival(String id);
}
