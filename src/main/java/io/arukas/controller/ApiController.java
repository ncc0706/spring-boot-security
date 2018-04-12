package io.arukas.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ApiController {

//    https://www.yiibai.com/spring-security/spring-security-4-method-security-using-preauthorize-postauthorize-secured-el.html

    @GetMapping("u1")
    @PreAuthorize("hasRole('u1')")
//    @Secured("hasRole('u1')")
    public String user1(){
        return "u1";
    }

}
