package com.value.controller.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
* @Description  用户数据来源
* @Author bxf
* @Date   2019/8/11
*/
@RestController
@RequestMapping("/api/user")
public class UserApi {

    /**
     * admin 权限才可访问
     * @param username
     * @return
     */
    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(value = {"/get"})
    public Object getUserByValue(@RequestParam(required = false) String username) {
        return username;
    }

    /**
     * 任务角色都可访问
     * @return
     */
    @GetMapping("/value")
    public Object getUserByAll() {
        return "zhangsan";
    }
}
