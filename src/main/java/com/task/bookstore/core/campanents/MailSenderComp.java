package com.task.bookstore.core.campanents;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailSenderComp {

    private final JavaMailSender javaMailSender;

    public void sendMail(String mail, String bookName, String authorName){

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("randomislerucun123@gmail.com");
        simpleMailMessage.setTo(mail);
        simpleMailMessage.setText(authorName + " post "+ bookName +" book");
        simpleMailMessage.setSubject("Book Store");
        javaMailSender.send(simpleMailMessage);
    }
}
