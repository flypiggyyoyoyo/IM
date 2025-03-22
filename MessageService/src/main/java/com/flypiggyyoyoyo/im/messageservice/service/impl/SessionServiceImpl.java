package com.flypiggyyoyoyo.im.messageservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flypiggyyoyoyo.im.messageservice.model.Session;
import com.flypiggyyoyoyo.im.messageservice.service.SessionService;
import com.flypiggyyoyoyo.im.messageservice.mapper.SessionMapper;
import org.springframework.stereotype.Service;

/**
* @author flypiggy
* @description 针对表【session(会话表)】的数据库操作Service实现
* @createDate 2025-03-22 21:15:33
*/
@Service
public class SessionServiceImpl extends ServiceImpl<SessionMapper, Session>
    implements SessionService{

}




