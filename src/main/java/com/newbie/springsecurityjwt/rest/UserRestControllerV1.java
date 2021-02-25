package com.newbie.springsecurityjwt.rest;

import com.newbie.springsecurityjwt.dto.UserDto;
import com.newbie.springsecurityjwt.model.user.User;
import com.newbie.springsecurityjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * REST controller user connected request.
 */

@RestController
@RequestMapping(value = "/api/v1/user/")
public class UserRestControllerV1 {

    private final UserService userService;

    @Autowired
    public UserRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id){
        User user = userService.findById(id);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        UserDto result = UserDto.fromUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id){
        userService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping ("update")
    public ResponseEntity updateUser(@RequestBody UserDto userDto){
        User user = userDto.toUser();
        userService.update(user);
        return new ResponseEntity(userDto, HttpStatus.OK);
    }
}
