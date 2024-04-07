package com.ruanzerah.skyio.domain.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumerService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.addresses.from}")
    private String fromAddress;

    @Value("${spring.mail.addresses.replyTo}")
    private String replyToAddress;
    public EmailConsumerService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @KafkaListener(topics = "email-topic", groupId = "email-consumer-group")
    public void consume(EmailContent emailMessage) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(emailMessage.getTo());
        mailMessage.setFrom(emailMessage.getFrom());
        mailMessage.setReplyTo(emailMessage.getReplyTo());
        mailMessage.setSubject(emailMessage.getSubject());
        mailMessage.setText(emailMessage.getText());
        javaMailSender.send(mailMessage);

        System.out.println("Email sent successfully to: " + emailMessage.getTo());
    }
}
