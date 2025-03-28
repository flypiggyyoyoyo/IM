package com.flypiggyyoyoyo.im.momentservice.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.User;
import com.flypiggyyoyoyo.im.momentservice.service.UserService;
import com.flypiggyyoyoyo.im.momentservice.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author flypiggy
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2025-03-28 22:52:50
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




