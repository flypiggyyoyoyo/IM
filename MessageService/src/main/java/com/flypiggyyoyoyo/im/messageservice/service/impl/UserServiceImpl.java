package com.flypiggyyoyoyo.im.messageservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flypiggyyoyoyo.im.messageservice.model.User;
import com.flypiggyyoyoyo.im.messageservice.service.UserService;
import com.flypiggyyoyoyo.im.messageservice.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author flypiggy
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2025-03-22 21:16:25
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




