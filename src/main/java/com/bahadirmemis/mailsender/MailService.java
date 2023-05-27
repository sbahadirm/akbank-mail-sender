package com.bahadirmemis.mailsender;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    public boolean sendMail(MailSendRequestDto requestDto){

        String to = requestDto.getEmailAddress();
        String subject = requestDto.getSubject();
        String body = requestDto.getBody();

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        try {
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }

        javaMailSender.send(mimeMessage);

        return true;
    }
}
