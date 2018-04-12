package io.arukas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private SessionRegistry sessionRegistry;

    /**
     * 获取所有在线用户
     *
     * @return
     */
    @GetMapping(value = "users/online-user")
    public List<Object> onlineUser() {
        List<Object> principals = sessionRegistry.getAllPrincipals();
        return principals;
    }
}
