package com.flypiggyyoyoyo.im.offlinedatastoreservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flypiggyyoyoyo.im.offlinedatastoreservice.model.User;
import com.flypiggyyoyoyo.im.offlinedatastoreservice.service.UserService;
import com.flypiggyyoyoyo.im.offlinedatastoreservice.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author flypiggy
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2025-03-27 21:31:01
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




