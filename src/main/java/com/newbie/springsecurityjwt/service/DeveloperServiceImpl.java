package com.newbie.springsecurityjwt.service;

import com.newbie.springsecurityjwt.model.developer.Developer;
import com.newbie.springsecurityjwt.model.user.User;
import com.newbie.springsecurityjwt.repository.DeveloperRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DeveloperServiceImpl implements DeveloperService{

    private final DeveloperRepository developerRepository;
    private final UserServiceImpl userService;

    @Autowired
    public DeveloperServiceImpl(DeveloperRepository developerRepository,UserServiceImpl userService){
        this.developerRepository = developerRepository;
        this.userService = userService;
    }

    @Override
    public Developer save(Developer developer) {
        User user = developer.getAccount().getUser();
        user = userService.registerRoleModerator(user);
        return developerRepository.save(developer);
    }

    @Override
    public List<Developer> getAll() {
        return developerRepository.findAll();
    }

    @Override
    public void update(Developer developer) {
        developerRepository.save(developer);
    }

    @Override
    public void delete(Developer developer){
        developerRepository.delete(developer);
    }

    @Override
    public Developer findById(Long id) {
        Developer result = developerRepository.findById(id).orElse(null);
        if (result == null) {
            log.warn("IN findById - no Developer found by id: {}", id);
            return null;
        }
        log.info("IN findById - Developer: {} found by id: {}", result);
        return result;
    }
}
