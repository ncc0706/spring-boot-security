package io.arukas.security.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 自定义登录认证成功处理器
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        response.setContentType("text/html;charset=UTF-8");
//        JSONObject jsonObject = new JSONObject();
//        String targetUrl = request.getParameter("to");
//        if (StringUtils.isEmpty(targetUrl)) {
//            DefaultSavedRequest savedRequest = (DefaultSavedRequest) this.requestCache.getRequest(request, response);
//            if (savedRequest != null) {
//                targetUrl = savedRequest.getRequestURI() + "?" + savedRequest.getQueryString();
//            } else {
//                targetUrl = request.getContextPath() + "/index.action";
//            }
//        } else {
//            this.requestCache.removeRequest(request, response);
//        }
//        clearAuthenticationAttributes(request);
//        jsonObject.put("staut", true);
//        jsonObject.put("targetUrl", targetUrl);
//        response.getWriter().write(jsonObject.toString());

    }

    /**
     * 删除身份认证临时数据
     */
    protected final void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
