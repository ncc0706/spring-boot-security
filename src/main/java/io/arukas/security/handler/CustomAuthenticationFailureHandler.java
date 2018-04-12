package io.arukas.security.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登录认证失败处理器
 */
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {


//        response.setContentType("text/html;charset=UTF-8");
//        String errorMsg = null;
//        JSONObject jsonObject = new JSONObject();
//        if (exception instanceof CustomAuthenticationException) {
//            errorMsg = exception.getMessage();
//        } else {
//            LOGGER.error("登录异常，请求参数为[" + request + "]", exception);
//            errorMsg = "登录失败，服务器内部错误，请稍后再试...";
//        }
//        jsonObject.put("staut", false);
//        jsonObject.put("errorMsg", errorMsg);
//        response.getWriter().write(jsonObject.toString());
    }
}
