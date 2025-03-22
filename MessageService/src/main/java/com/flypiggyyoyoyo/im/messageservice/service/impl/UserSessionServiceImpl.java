package com.flypiggyyoyoyo.im.messageservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flypiggyyoyoyo.im.messageservice.model.UserSession;
import com.flypiggyyoyoyo.im.messageservice.service.UserSessionService;
import com.flypiggyyoyoyo.im.messageservice.mapper.UserSessionMapper;
import org.springframework.stereotype.Service;

/**
* @author flypiggy
* @description 针对表【user_session】的数据库操作Service实现
* @createDate 2025-03-22 21:17:12
*/
@Service
public class UserSessionServiceImpl extends ServiceImpl<UserSessionMapper, UserSession>
    implements UserSessionService{

}




