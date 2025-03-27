package com.flypiggyyoyoyo.im.offlinedatastoreservice.consumer;


import com.flypiggyyoyoyo.im.offlinedatastoreservice.constants.kafka.KafkaConstants;
import com.flypiggyyoyoyo.im.offlinedatastoreservice.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageConsumer {

    @Autowired
    private MessageService messageService;

    @KafkaListener(topics = KafkaConstants.topic, groupId = KafkaConstants.consumerGroupId)
    public void listen(String message) {
        messageService.saveOfflineMessage(message);
    }
}
