package com.flypiggyyoyoyo.im.authenticationservice.service;

import com.flypiggyyoyoyo.im.authenticationservice.data.user.register.RegisterRequest;
import com.flypiggyyoyoyo.im.authenticationservice.data.user.register.RegisterResponse;
import com.flypiggyyoyoyo.im.authenticationservice.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author flypiggy
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2025-03-12 23:59:59
*/
public interface UserService extends IService<User> {
    RegisterResponse register(RegisterRequest request);
}
