package com.flypiggyyoyoyo.im.offlinedatastoreservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flypiggyyoyoyo.im.offlinedatastoreservice.model.Session;
import com.flypiggyyoyoyo.im.offlinedatastoreservice.service.SessionService;
import com.flypiggyyoyoyo.im.offlinedatastoreservice.mapper.SessionMapper;
import org.springframework.stereotype.Service;

/**
* @author flypiggy
* @description 针对表【session(会话表)】的数据库操作Service实现
* @createDate 2025-03-27 21:29:13
*/
@Service
public class SessionServiceImpl extends ServiceImpl<SessionMapper, Session>
    implements SessionService{

}




