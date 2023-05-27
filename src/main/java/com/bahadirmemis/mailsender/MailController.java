package com.bahadirmemis.mailsender;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1/mails")
@RequiredArgsConstructor
public class MailController {

  private final MailService mailService;

  @GetMapping("/default-mail-address")
  public String getMailAddress() {
    return "sbahadirm@gmail.com";
  }

  @PostMapping
  public boolean sendMail(@RequestBody MailSendRequestDto mailSendRequestDto){

    System.out.println(mailSendRequestDto);

    return mailService.sendMail(mailSendRequestDto);

//    for (int i = 0; i < 10; i++){
//
//      mailSendRequestDto.setBody(mailSendRequestDto.getBody() + "-" + i);
//
//      mailService.sendMail(mailSendRequestDto);
//    }

  }

}
