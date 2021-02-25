package com.newbie.springsecurityjwt.service;

import com.newbie.springsecurityjwt.model.developer.Developer;

import java.util.List;

public interface DeveloperService {

    Developer findById(Long id);

    Developer save(Developer developer);

    List<Developer> getAll();

    void delete(Developer developer);

    void update(Developer developer);
}
