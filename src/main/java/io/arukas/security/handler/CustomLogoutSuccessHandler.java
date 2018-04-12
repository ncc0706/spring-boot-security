package io.arukas.security.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义退出登录处理器
 */
@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        if(logger.isDebugEnabled()){
            logger.debug("logout success.");
        }

//        response.setContentType("text/html;charset=UTF-8");
//        JSONObject jsonObject = new JSONObject();
//        String url = request.getParameter("to");
//        if (StringUtils.isEmpty(url)) {
//            url = request.getContextPath() + "/login.action?logout=true";
//        }
//        jsonObject.put("staut", true);
//        jsonObject.put("url", url);
//        response.getWriter().write(jsonObject.toString());
    }
}
