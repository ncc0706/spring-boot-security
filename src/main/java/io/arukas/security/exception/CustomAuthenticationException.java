package io.arukas.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 自定义认证异常
 */
public class CustomAuthenticationException extends AuthenticationException {

    public CustomAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public CustomAuthenticationException(String msg) {
        super(msg);
    }
}
