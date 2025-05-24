package com.ssafy.ticket_moa_app.service;

import com.ssafy.ticket_moa_app.dao.UserDao;
import com.ssafy.ticket_moa_app.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao dao;

    @Override
    public int join(User user) {
        return dao.insert(user);
    }

    @Override
    public User login(String id, String pass) {
        return dao.login(id, pass);
    }

    @Override
    public boolean isUsedId(String id) {
        return dao.selectById(id) != null;
    }


    @Override
    public User selectUser(User user) {
        return dao.selectByUser(user);
    }
}
