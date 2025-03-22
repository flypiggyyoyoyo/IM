package com.flypiggyyoyoyo.im.messageservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flypiggyyoyoyo.im.messageservice.model.Message;
import com.flypiggyyoyoyo.im.messageservice.service.MessageService;
import com.flypiggyyoyoyo.im.messageservice.mapper.MessageMapper;
import org.springframework.stereotype.Service;

/**
* @author flypiggy
* @description 针对表【message】的数据库操作Service实现
* @createDate 2025-03-22 21:13:40
*/
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
    implements MessageService{

}




