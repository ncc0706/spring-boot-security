package io.arukas.controller;

import io.arukas.security.provider.CustomAuthenticationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("account")
public class AccountController {

    private final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

//    @Autowired
//    private AuthenticationManager authenticationManager;

    @GetMapping(value = "signin")
    public String signin() {
        if (logger.isDebugEnabled()) {
            logger.debug("......signin......");
        }
        // 注意返回的路径不要在account前面添加/，否则会执行两遍, 又可以了....无语
        return "/account/signin";
    }


    @PostMapping(value = "signin")
    public String signin(String username, String password, HttpServletRequest request) {

        if (logger.isDebugEnabled()) {
            logger.debug("username: {}", username);
            logger.debug("password: {}", password);
        }

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = customAuthenticationProvider.authenticate(authRequest);
        HttpSession session = request.getSession();

        // 这个非常重要，否则验证后将无法登陆
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

        return "redirect:/";
    }


    @GetMapping(value = "signout")
    public void signout() {

    }

}
