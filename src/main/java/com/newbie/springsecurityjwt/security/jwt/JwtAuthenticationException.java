package com.newbie.springsecurityjwt.security.jwt;

import org.springframework.security.core.AuthenticationException;

/**
 * Authentication exception for JwtAppDemo application.
 */

public class JwtAuthenticationException extends AuthenticationException {

    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}
