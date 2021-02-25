package com.newbie.springsecurityjwt.rest;

import com.newbie.springsecurityjwt.dto.AuthenticationRequestDto;
import com.newbie.springsecurityjwt.dto.UserDto;
import com.newbie.springsecurityjwt.model.user.Status;
import com.newbie.springsecurityjwt.model.user.User;
import com.newbie.springsecurityjwt.security.jwt.JwtTokenProvider;
import com.newbie.springsecurityjwt.service.UserService;
import com.newbie.springsecurityjwt.service.twilio.SmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * REST controller for authentication requests (login, logout, register, etc.)
 */

@RestController
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationRestControllerV1 {


    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }
            String token = jwtTokenProvider.createToken(username, user.getRoles());
            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @GetMapping("confirmation")
    public ResponseEntity check(String sms){
        if (sms.equals(userService.confirmationUserBySms(sms)))
            return ResponseEntity.ok(HttpStatus.valueOf("User Status " + Status.ACTIVE));
        else {
            return ResponseEntity.ok(HttpStatus.valueOf("Wrong sms, register another one..."));
        }
    }

    @PostMapping("register")
    public ResponseEntity registerUser(@RequestBody UserDto userDto){
        User registerUser = userService.register(userDto.toUser());
        return ResponseEntity.ok(registerUser);
    }
}
