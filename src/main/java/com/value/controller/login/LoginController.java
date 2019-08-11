package com.value.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    /**
     * 用户登入界面
     * @return
     */
    @RequestMapping
    public String login() {
        return "login";
    }

    /**
     * 登入主页
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * 404
     * @return
     */
    @RequestMapping("/404")
    public String notFoundPage() {
        return "404";
    }

    /**
     * 403
     * @return
     */
    @RequestMapping("/403")
    public String accessError() {
        return "403";
    }

    /**
     * 500
     * @return
     */
    @RequestMapping("500")
    public String internalError() {
        return "500";
    }
}
