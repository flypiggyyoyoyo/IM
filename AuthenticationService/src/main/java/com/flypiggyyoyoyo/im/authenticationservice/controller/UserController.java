package com.flypiggyyoyoyo.im.authenticationservice.controller;


import com.flypiggyyoyoyo.im.authenticationservice.common.Result;
import com.flypiggyyoyoyo.im.authenticationservice.data.user.LoginCode.LoginCodeRequest;
import com.flypiggyyoyoyo.im.authenticationservice.data.user.LoginCode.LoginCodeResponse;
import com.flypiggyyoyoyo.im.authenticationservice.data.user.login.LoginRequest;
import com.flypiggyyoyoyo.im.authenticationservice.data.user.login.LoginResponse;
import com.flypiggyyoyoyo.im.authenticationservice.data.user.register.RegisterRequest;
import com.flypiggyyoyoyo.im.authenticationservice.data.user.register.RegisterResponse;
import com.flypiggyyoyoyo.im.authenticationservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        RegisterResponse response = userService.register(request);
        return Result.OK(response);
    }

    @PostMapping("/login")
    public Result<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = userService.login(request);
        return Result.OK(response);
    }

    @PostMapping("/loginCode")
    public Result<LoginCodeResponse> loginCode(@Valid @RequestBody LoginCodeRequest request) {
        LoginCodeResponse response = userService.loginCode(request);
        return Result.OK(response);
    }
}
