package com.newbie.springsecurityjwt.repository;

import com.newbie.springsecurityjwt.model.developer.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer,Long> {
}
