package com.flypiggyyoyoyo.im.offlinedatastoreservice.service;

import com.flypiggyyoyoyo.im.offlinedatastoreservice.model.UserSession;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
* @author flypiggy
* @description 针对表【user_session】的数据库操作Service
* @createDate 2025-03-27 21:31:53
*/
public interface UserSessionService extends IService<UserSession> {
    Set<Long> findSessionIdByUserId(Long userId);
}
