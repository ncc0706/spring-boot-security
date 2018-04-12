package io.arukas.security.token;

import io.arukas.security.UserInfo;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public class CustomAuthenticationToken extends AbstractAuthenticationToken {

    /**
     * Principal
     */
    private Object principal;

    /**
     * 帐号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 登录IP
     */
    private String loginIp;

    /**
     * 构造方法，未通过登录认证
     */
    public CustomAuthenticationToken() {
        super(null);
        this.principal = null;
        super.setAuthenticated(false);
    }

    /**
     * 构造方法，已经通过登录认证
     */
    public CustomAuthenticationToken(UserInfo user) {
        super(user.getAuthorities());
        this.principal = user;
        super.setAuthenticated(true);
    }

    /**
     * 获取帐号
     *
     * @return username 帐号
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置帐号
     *
     * @param username 帐号
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取登录IP
     *
     * @return loginIp 登录IP
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * 设置登录IP
     *
     * @param loginIp 登录IP
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

}
