package com.value.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping
    public String login() {
        return "login";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/404")
    public String notFoundPage() {
        return "404";
    }

    @RequestMapping("/403")
    public String accessError() {
        return "403";
    }

    @RequestMapping("500")
    public String internalError() {
        return "500";
    }
}
