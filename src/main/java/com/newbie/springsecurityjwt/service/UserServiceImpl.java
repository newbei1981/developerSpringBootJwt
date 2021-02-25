package com.newbie.springsecurityjwt.service;

import com.newbie.springsecurityjwt.model.user.Role;
import com.newbie.springsecurityjwt.model.user.Status;
import com.newbie.springsecurityjwt.model.user.User;
import com.newbie.springsecurityjwt.repository.RoleRepository;
import com.newbie.springsecurityjwt.repository.UserRepository;
import com.newbie.springsecurityjwt.service.twilio.SmsSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final SmsSender smsSender;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder, SmsSender smsSender) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.smsSender = smsSender;
    }
    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.APPROVAL_REQUIRED);
        user.setSms(smsSender.sendSms(user.getPhoneNumber()));
        User registeredUser = userRepository.save(user);
        log.info("IN register - user: {} successfully registered, but confirmation by sms required", registeredUser);
        return registeredUser;
    }

    User registerRoleModerator(User user){
        Role roleUser = roleRepository.findByName("ROLE_MODERATOR");
        List<Role> userRoles = user.getRoles();
        userRoles.add(roleUser);
        return user;
    }

    @Override
    public boolean confirmationUserBySms(String sms){
        User chekUserRepository = userRepository.findBySms(sms);
        if (chekUserRepository !=null) {
            chekUserRepository.setStatus(Status.ACTIVE);
            userRepository.save(chekUserRepository);
            return true;
        } else return false;
    }

    @Override
    public User update(User user){
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);
        if (result == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }
        log.info("IN findById - user: {} found by id: {}", result);
        return result;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted");
    }

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        User result = userRepository.findByPhoneNumber(phoneNumber);
        return result;
    }
}
