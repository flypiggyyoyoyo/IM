package com.flypiggyyoyoyo.im.realtimecommunicationservice.service;

import com.flypiggyyoyoyo.im.realtimecommunicationservice.data.ReceiveMessage.ReceiveMessageRequest;
import com.flypiggyyoyoyo.im.realtimecommunicationservice.data.ReceiveMessage.ReceiveMessageResponse;

public interface RcvMsgServer {
    ReceiveMessageResponse receiveMessage(ReceiveMessageRequest request);
}
