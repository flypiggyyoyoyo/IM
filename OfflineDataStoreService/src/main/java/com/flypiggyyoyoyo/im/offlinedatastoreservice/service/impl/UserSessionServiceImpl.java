package com.flypiggyyoyoyo.im.offlinedatastoreservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flypiggyyoyoyo.im.offlinedatastoreservice.model.UserSession;
import com.flypiggyyoyoyo.im.offlinedatastoreservice.service.UserSessionService;
import com.flypiggyyoyoyo.im.offlinedatastoreservice.mapper.UserSessionMapper;
import org.springframework.stereotype.Service;

/**
* @author flypiggy
* @description 针对表【user_session】的数据库操作Service实现
* @createDate 2025-03-27 21:31:53
*/
@Service
public class UserSessionServiceImpl extends ServiceImpl<UserSessionMapper, UserSession>
    implements UserSessionService{

}




