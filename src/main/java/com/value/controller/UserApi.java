package com.value.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserApi {

    @GetMapping(value = {"/get"})
    public Object getUserByValue(@RequestParam(required = false) String username) {
        return username;
    }
}
