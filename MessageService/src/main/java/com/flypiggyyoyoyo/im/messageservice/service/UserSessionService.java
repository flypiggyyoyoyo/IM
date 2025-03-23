package com.flypiggyyoyoyo.im.messageservice.service;

import com.flypiggyyoyoyo.im.messageservice.model.UserSession;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author flypiggy
* @description 针对表【user_session】的数据库操作Service
* @createDate 2025-03-22 21:17:12
*/
public interface UserSessionService extends IService<UserSession> {
    /**
     * 通过sessionId得到群聊内的所有用户id
     * @param sessionId
     * @return
     */
    List<Long> getUserIdsBySessionId(Long sessionId);
}
