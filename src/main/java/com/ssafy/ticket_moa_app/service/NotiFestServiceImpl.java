package com.ssafy.ticket_moa_app.service;

import com.ssafy.ticket_moa_app.dao.NotiFestDao;
import com.ssafy.ticket_moa_app.dto.NotiFest;
import com.ssafy.ticket_moa_app.dto.Festival;
import org.springframework.beans.factory.annotation.Autowired;
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
    public int getNotiUserCount(int fesId) {
        return dao.countNotiUsersByFestival(fesId);
    }

}
