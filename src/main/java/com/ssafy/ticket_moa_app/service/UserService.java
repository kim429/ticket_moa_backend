package com.ssafy.ticket_moa_app.service;

import com.ssafy.ticket_moa_app.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
}
