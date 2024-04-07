package com.ruanzerah.skyio.domain.mail;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailProducerService {

    private static final String TOPIC = "email-topic";
    private final KafkaTemplate<String, EmailContent> kafkaTemplate;

    public EmailProducerService(KafkaTemplate<String, EmailContent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEmail(EmailContent emailMessage) {
        kafkaTemplate.send(TOPIC, emailMessage);
    }
}

