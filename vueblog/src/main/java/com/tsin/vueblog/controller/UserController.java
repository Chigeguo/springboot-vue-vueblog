package com.tsin.vueblog.controller;


import com.tsin.vueblog.common.lang.Result;
import com.tsin.vueblog.entity.User;
import com.tsin.vueblog.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author tsin
 * @since 2021-09-12
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequiresAuthentication
    @RequestMapping("/index")
    public Result index() {
        User byId = userService.getById(1L);
        return Result.success(byId);
    }

    @RequestMapping("/save")
    public Result save(@Validated @RequestBody User user) {

        return Result.success(user);
    }
}
