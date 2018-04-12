package io.arukas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("")
    public String index() {
        if(logger.isDebugEnabled()){
            logger.debug("...index page...");
        }
        return "index";
    }

}
