package com.ssafy.ticket_moa_app.service;

import com.ssafy.ticket_moa_app.dto.User;

public interface UserService {
    public int join(User user);
    public User login(String id, String pass);
    public boolean isUsedId(String id);
    public User selectUser(User user);
}
