package com.flypiggyyoyoyo.im.authenticationservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.flypiggyyoyoyo.im.authenticationservice.data.user.LoginCode.LoginCodeRequest;
import com.flypiggyyoyoyo.im.authenticationservice.data.user.LoginCode.LoginCodeResponse;
import com.flypiggyyoyoyo.im.authenticationservice.data.user.login.LoginRequest;
import com.flypiggyyoyoyo.im.authenticationservice.data.user.login.LoginResponse;
import com.flypiggyyoyoyo.im.authenticationservice.data.user.register.RegisterRequest;
import com.flypiggyyoyoyo.im.authenticationservice.data.user.register.RegisterResponse;
import com.flypiggyyoyoyo.im.authenticationservice.data.user.updateAvatar.UpdateAvatarRequest;
import com.flypiggyyoyoyo.im.authenticationservice.data.user.updateAvatar.UpdateAvatarResponse;
import com.flypiggyyoyoyo.im.authenticationservice.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author flypiggy
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2025-03-12 23:59:59
*/
public interface UserService extends IService<User> {
    default User getOnly(QueryWrapper<User> wrapper, boolean throwEx){

        // 在查询条件的末尾添加 "limit 1" 语句，确保查询结果最多只有一条记录
        wrapper.last("limit 1");

        return this.getOne(wrapper, throwEx);
    }

    RegisterResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest requset);
    LoginCodeResponse loginCode(LoginCodeRequest requset);
    UpdateAvatarResponse updateAvatar(String id, UpdateAvatarRequest request);
}
