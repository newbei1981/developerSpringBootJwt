package com.newbie.springsecurityjwt.service;

import com.newbie.springsecurityjwt.model.user.User;

import java.util.List;

public interface UserService {

    User register(User user);

    User update(User user);

    User findByPhoneNumber(String phoneNumber);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void delete(Long id);

    boolean confirmationUserBySms(String sms);
}
