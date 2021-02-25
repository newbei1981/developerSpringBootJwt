package com.newbie.springsecurityjwt.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.newbie.springsecurityjwt.model.user.User;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Service
public class UserDto {

    private Long id;
    private String username;
    private String phoneNumber;

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPhoneNumber(phoneNumber);
        return user;
    }

    public static UserDto fromUser(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPhoneNumber(user.getPhoneNumber());
        return userDto;
    }
}
