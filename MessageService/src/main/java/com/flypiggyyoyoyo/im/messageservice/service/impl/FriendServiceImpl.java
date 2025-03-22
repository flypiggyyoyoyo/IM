package com.flypiggyyoyoyo.im.messageservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flypiggyyoyoyo.im.messageservice.mapper.FriendMapper;
import com.flypiggyyoyoyo.im.messageservice.service.FriendService;
import com.flypiggyyoyoyo.im.messageservice.model.Friend;
import org.springframework.stereotype.Service;

/**
* @author flypiggy
* @description 针对表【friend(联系人表)】的数据库操作Service实现
* @createDate 2025-03-22 21:10:45
*/
@Service
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend>
    implements FriendService {

}




