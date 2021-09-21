package com.tsin.vueblog.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tsin.vueblog.common.dto.UserDto;
import com.tsin.vueblog.common.lang.Result;
import com.tsin.vueblog.entity.User;
import com.tsin.vueblog.service.UserService;
import com.tsin.vueblog.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author tsin
 * @since 2021/9/13-21:47
 */
@RestController
public class AccountController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;

    @CrossOrigin
    @RequestMapping("login")
    public Result login(@Validated @RequestBody UserDto userDto, HttpServletResponse response) {
        User user = userService.getOne(new QueryWrapper<User>().eq("username", userDto.getUsername()));
        Assert.notNull(user, "用户名不存在");

        if (!user.getPassword().equals(SecureUtil.md5(userDto.getPassword()))) {
            return Result.fail("密码错误");
        }
        //生成jwt
        String jwt = jwtUtils.generateToken(user.getId());

        response.setHeader("Authorization",jwt);
        response.setHeader("Access-control-Expose-Headers","Authorization");
        return Result.success(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail()).map()
        );

    }
    // 退出
    @GetMapping("/logout")
    @RequiresAuthentication
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.success(null);
    }

}
