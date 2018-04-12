package io.arukas.controller;

import io.arukas.tools.SecurityUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController {

    @GetMapping(value = "ok")
    @PreAuthorize("hasRole('ADMIN')")
    public String ok() {
        String currentUser = SecurityUtils.currentUser();
        return "admin->" + currentUser;
    }

}

