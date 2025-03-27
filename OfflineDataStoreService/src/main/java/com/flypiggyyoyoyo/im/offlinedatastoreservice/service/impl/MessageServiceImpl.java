package com.flypiggyyoyoyo.im.offlinedatastoreservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flypiggyyoyoyo.im.offlinedatastoreservice.common.TextMessage;
import com.flypiggyyoyoyo.im.offlinedatastoreservice.model.Message;
import com.flypiggyyoyoyo.im.offlinedatastoreservice.service.MessageService;
import com.flypiggyyoyoyo.im.offlinedatastoreservice.mapper.MessageMapper;
import org.springframework.stereotype.Service;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;

/**
* @author flypiggy
* @description 针对表【message】的数据库操作Service实现
* @createDate 2025-03-27 21:26:43
*/
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
    implements MessageService{

    public void saveOfflineMessage(String message) {
        TextMessage textMessage = JSONUtil.toBean(message, TextMessage.class);
        Message msg = new Message();
        BeanUtil.copyProperties(textMessage, msg);

        msg.setContent(textMessage.getBody().getContent());
        msg.setReplyId(textMessage.getBody().getReplyId());
        msg.setSenderId(textMessage.getSendUserId());

        int insert = this.baseMapper.insert(msg);

        if (insert <= 0) {
            throw new RuntimeException("保存离线消息失败");
        }
    }
}




