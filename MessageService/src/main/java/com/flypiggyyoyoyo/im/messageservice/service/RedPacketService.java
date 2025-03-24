package com.flypiggyyoyoyo.im.messageservice.service;

import com.flypiggyyoyoyo.im.messageservice.data.sendRedPackage.SendRedPacketRequest;
import com.flypiggyyoyoyo.im.messageservice.data.sendRedPackage.SendRedPacketResponse;
import com.flypiggyyoyoyo.im.messageservice.model.RedPacket;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author flypiggy
* @description 针对表【red_packet(红包主表)】的数据库操作Service
* @createDate 2025-03-24 16:30:00
*/
public interface RedPacketService extends IService<RedPacket> {
    /**
     * 发送红包
     * @param request
     * @return
     * @throws Exception
     */
    SendRedPacketResponse sendRedPacket(SendRedPacketRequest request) throws Exception;

    /**
     * 红包过期处理
     *
     * @param redPacketId 红包Id
     */
    void handleExpiredRedPacket(Long redPacketId);
}
