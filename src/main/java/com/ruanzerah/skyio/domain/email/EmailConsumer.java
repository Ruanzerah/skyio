package com.ruanzerah.skyio.domain.email;

import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static com.ruanzerah.skyio.domain.email.EmailProducer.EMAIL_TOPIC;

@Service
@AllArgsConstructor
public class EmailConsumer {

    private final JavaMailSender mailSender;
    private static final String EMAIL = System.getenv("SMTP_USER");

    @KafkaListener(topics = EMAIL_TOPIC, groupId = "email-consumer-group")
    public void consume(Email email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(EMAIL);
        mailMessage.setTo(email.to());
        mailMessage.setSubject(email.subject());
        mailMessage.setText(email.text());
        mailSender.send(mailMessage);
    }
}
