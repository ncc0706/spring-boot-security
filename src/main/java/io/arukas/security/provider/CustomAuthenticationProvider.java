package io.arukas.security.provider;

import ch.qos.logback.core.net.SyslogOutputStream;
import io.arukas.security.UserInfo;
import io.arukas.security.exception.CustomAuthenticationException;
import io.arukas.security.token.CustomAuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * 自定义认证服务提供者
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (logger.isDebugEnabled()) {
            logger.debug("...auth...");
        }

        CustomAuthenticationToken token = (CustomAuthenticationToken) authentication;
        UserInfo user = retrieveUser(token);
        preAuthenticationCheck(token, user);
        additionalAuthenticationCheck(token, user);
        postAuthenticationCheck(token, user);
        CustomAuthenticationToken result = new CustomAuthenticationToken(user);
        result.setDetails(authentication.getDetails());
        return result;

//        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        boolean assignableFrom = CustomAuthenticationToken.class.isAssignableFrom(authentication);
        System.out.println(assignableFrom);
        return assignableFrom;
//        return false;
    }

    /**
     * 检索用户
     */
    private UserInfo retrieveUser(CustomAuthenticationToken token) {
        //这里是进数据库根据账号查用户
        UserInfo user = null;
        // user = new UserInfoQueryImpl().username(token.getUsername(), false).uniqueResult();
        String username = token.getUsername();
        System.out.println("username: " + username);

        user = new UserInfo();

        return user;
    }

    /**
     * 前置的身份认证检查
     */
    private void preAuthenticationCheck(CustomAuthenticationToken token, UserInfo user) {
        if (user == null) {
            throw new CustomAuthenticationException("登录失败，帐号不存在");
        }
        if (!user.isEnabled()) {
            throw new CustomAuthenticationException("登录失败，您的帐号已被禁用");
        }
        if (!user.isAccountNonExpired()) {
            throw new CustomAuthenticationException("登录失败，您的帐号已过期");
        }
    }

    /**
     * 后置的身份认证检查
     */
    private void postAuthenticationCheck(CustomAuthenticationToken token, UserInfo user) {
        if (!user.isCredentialsNonExpired()) {
            throw new CustomAuthenticationException("登录失败，您的密码已过期");
        }
    }

    /**
     * 额外的身份认证检查
     */
    public void additionalAuthenticationCheck(CustomAuthenticationToken token, UserInfo user) {
//        if (!user.isRealPassword(token.getPassword())) {
//            throw new CustomAuthenticationException("帐号或密码错误");
//        }
    }

    /**
     * 保存登录日志
     */
//    public void saveLoginLog(CustomAuthenticationToken token, UserInfo user) {
//        LoginLog loginLog = new LoginLog();
//        loginLog.setIp(token.getLoginIp());
//        loginLog.setUser(user);
//        loginLog.saveOrUpdateIt();
//    }
}
