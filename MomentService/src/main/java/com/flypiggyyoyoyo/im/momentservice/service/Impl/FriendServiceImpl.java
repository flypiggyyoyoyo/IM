package com.flypiggyyoyoyo.im.momentservice.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.Friend;
import com.flypiggyyoyoyo.im.momentservice.service.FriendService;
import com.flypiggyyoyoyo.im.momentservice.mapper.FriendMapper;
import org.springframework.stereotype.Service;

/**
* @author flypiggy
* @description 针对表【friend(联系人表)】的数据库操作Service实现
* @createDate 2025-03-28 22:52:03
*/
@Service
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend>
    implements FriendService{

}




