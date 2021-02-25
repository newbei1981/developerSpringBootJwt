package com.newbie.springsecurityjwt.repository;

import com.newbie.springsecurityjwt.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String name);
    User findByPhoneNumber(String phoneNumber);
    User findBySms(String sms);
}
