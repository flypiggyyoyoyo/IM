package com.flypiggyyoyoyo.im.offlinedatastoreservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flypiggyyoyoyo.im.offlinedatastoreservice.model.UserSession;
import com.flypiggyyoyoyo.im.offlinedatastoreservice.service.UserSessionService;
import com.flypiggyyoyoyo.im.offlinedatastoreservice.mapper.UserSessionMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
* @author flypiggy
* @description 针对表【user_session】的数据库操作Service实现
* @createDate 2025-03-27 21:31:53
*/
@Service
public class UserSessionServiceImpl extends ServiceImpl<UserSessionMapper, UserSession>
    implements UserSessionService{

    @Override
    public Set<Long> findSessionIdByUserId(Long userId) {
        QueryWrapper<UserSession> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);

        List<UserSession> userSessions = this.baseMapper.selectList(queryWrapper);

        return userSessions.stream().map(UserSession::getSessionId).collect(Collectors.toSet());
    }
}




