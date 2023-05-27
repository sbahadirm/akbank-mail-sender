package com.bahadirmemis.mailsender.kafka;

import com.bahadirmemis.mailsender.MailSendRequestDto;
import com.bahadirmemis.mailsender.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaListenerService {

    private final MailService mailService;

    @KafkaListener(
            topics = "${akbank.kafka.topic}",
            groupId = "${akbank.kafka.group-id}"
    )
    public void listen(@Payload MailSendRequestDto requestDto){

        boolean isSuccess = mailService.sendMail(requestDto);

        if (isSuccess){
            log.info("Message received by mail-sender app. status: {} , params: {}" , isSuccess, requestDto);
        } else {
            log.error("Message could not read by mail-sender app. status: {} , params: {}" , isSuccess, requestDto);
        }

    }
}
