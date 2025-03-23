package com.flypiggyyoyoyo.im.messageservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flypiggyyoyoyo.im.messageservice.model.UserSession;
import com.flypiggyyoyoyo.im.messageservice.service.UserSessionService;
import com.flypiggyyoyoyo.im.messageservice.mapper.UserSessionMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author flypiggy
* @description 针对表【user_session】的数据库操作Service实现
* @createDate 2025-03-22 21:17:12
*/
@Service
public class UserSessionServiceImpl extends ServiceImpl<UserSessionMapper, UserSession>
    implements UserSessionService{

    @Override
    public List<Long> getUserIdsBySessionId(Long sessionId) {
        List<UserSession> userSessionList = this.lambdaQuery().eq(UserSession::getSessionId, sessionId).list();
        return userSessionList.stream().map(UserSession::getUserId).collect(Collectors.toList());
    }
}




