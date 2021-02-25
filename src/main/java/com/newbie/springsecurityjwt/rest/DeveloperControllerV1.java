package com.newbie.springsecurityjwt.rest;

import com.newbie.springsecurityjwt.dto.DeveloperDto;
import com.newbie.springsecurityjwt.dto.UserDto;
import com.newbie.springsecurityjwt.model.developer.Developer;
import com.newbie.springsecurityjwt.model.user.User;
import com.newbie.springsecurityjwt.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.newbie.springsecurityjwt.dto.DeveloperDto.developerDtoList;
import static com.newbie.springsecurityjwt.dto.DeveloperDto.fromDeveloper;

@RestController
@RequestMapping(value = "/api/v1/developer/")
public class DeveloperControllerV1 {

    private DeveloperService developerService;

    @Autowired
    public DeveloperControllerV1(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @GetMapping(value = "all")
    public ResponseEntity listDevelopers() {
        List<Developer> allDeveloper = developerService.getAll();
        if (allDeveloper.isEmpty()) {
            return new ResponseEntity<>("Some thing wrong with DB ?!", HttpStatus.NOT_FOUND);
        }
        List<DeveloperDto> developerDtoList = developerDtoList(allDeveloper);
        return new ResponseEntity(developerDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity getDeveloperById(@PathVariable(name = "id") Long id){
        Developer developer = developerService.findById(id);
        if(developer == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        DeveloperDto result = DeveloperDto.fromDeveloper(developer);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(value = "update/{id}")
    public ResponseEntity updateDeveloper(@PathVariable(name = "id") Long id, @RequestBody DeveloperDto developerDto){
        Developer developer = developerDto.toDeveloper();
        developerService.save(developer);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(value = "save")
    public ResponseEntity saveDeveloper(@RequestBody UserDto userDto, DeveloperDto developerDto){
        Developer developer = developerDto.toDeveloper();
        User user = userDto.toUser();
        developer.getAccount().setUser(user);
        developer = developerService.save(developer);
        DeveloperDto result = fromDeveloper(developer);
        return new ResponseEntity(result,HttpStatus.OK);
    }
}
