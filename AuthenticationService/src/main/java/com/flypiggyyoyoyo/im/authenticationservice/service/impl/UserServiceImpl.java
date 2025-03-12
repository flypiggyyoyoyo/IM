package com.flypiggyyoyoyo.im.authenticationservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flypiggyyoyoyo.im.authenticationservice.model.User;
import com.flypiggyyoyoyo.im.authenticationservice.service.UserService;
import com.flypiggyyoyoyo.im.authenticationservice.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author flypiggy
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2025-03-12 23:59:59
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




