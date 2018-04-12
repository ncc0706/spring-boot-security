package io.arukas.security.filter;

import io.arukas.security.exception.CustomAuthenticationException;
import io.arukas.security.token.CustomAuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义认证过滤器
 */
public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SessionRegistry sessionRegistry;

    /**
     * 构造方法，设置登录URL
     *
     * @param defaultFilterProcessesUrl
     */
    public CustomAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    public CustomAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        try {


            CustomAuthenticationToken token = new CustomAuthenticationToken();
            token.setUsername(request.getParameter("username"));
            token.setPassword(request.getParameter("password"));
            token.setLoginIp(getRequestIp(request));
            token.setDetails(authenticationDetailsSource.buildDetails(request));

            //用户名密码验证通过后,注册session
//            sessionRegistry.registerNewSession(request.getSession().getId(),authRequest.getPrincipal());

            return this.getAuthenticationManager().authenticate(token);
        } catch (CustomAuthenticationException e) {
            throw e;
        } catch (Exception e) {
            logger.error("登录过程异常，请求参数为[" + request + "]", e);
            throw new CustomAuthenticationException("登录失败，服务器内部错误，请稍后再试...");
        }
    }

    /**
     * 获取请求客户端真实IP
     */
    public String getRequestIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
