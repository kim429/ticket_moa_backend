package com.ssafy.ticket_moa_app.service;

import com.ssafy.ticket_moa_app.dao.NotiFestDao;
import com.ssafy.ticket_moa_app.dto.NotiFest;
import com.ssafy.ticket_moa_app.dto.Festival;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotiFestServiceImpl implements NotiFestService {

    @Autowired
    private NotiFestDao dao;

    @Override
    public boolean toggleNoti(NotiFest notiFest) {
        if (dao.exists(notiFest)) {
            dao.deleteNotiFest(notiFest);
            dao.decrementFestivalNoti(notiFest.getFesId());
            return false;
        } else {
            dao.insertNotiFest(notiFest);
            dao.incrementFestivalNoti(notiFest.getFesId());
            return true;
        }
    }

    @Override
    public List<Festival> getUserNotiList(String id) {
        return dao.selectByUserId(id);
    }

    @Override
    @Cacheable(value = "notiUserCount", key = "#fesId") // 🔥 캐싱
    public int getNotiUserCount(int fesId) {
        return dao.countNotiUsersByFestival(fesId);
    }

    @Override
    @CacheEvict(value = "notiUserCount", key = "#fesId") // 🔥 캐시 무효화
    public void addFestivalNoti(String userId, int fesId) {
        dao.insertNoti(new NotiFest(userId, fesId));
    }

    @Override
    public boolean isFestivalNotified(String id, int fesId) {
        return dao.isFestivalNotified(id, fesId);
    }
    @Override
    public boolean hasAnyNotifiedFestival(String id) {
        return dao.hasAnyNotifiedFestival(id);
    }


}
