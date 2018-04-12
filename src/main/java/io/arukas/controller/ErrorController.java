package io.arukas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("error")
public class ErrorController {

    @GetMapping(value = "/403")
    public String accesssDenied(Principal user, Model model) {
        if (user != null) {
            model.addAttribute("msg", "Hi 【 " + user.getName() + "】, you do not have permission to access this page!");
        } else {
            model.addAttribute("msg", "You don't have permission to access this page!");
        }
        return "error/403";
    }
}
