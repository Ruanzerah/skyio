package com.ruanzerah.skyio.domain.email;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailProducer {

    public static final String EMAIL_TOPIC = "email-topic";
    private final KafkaTemplate<String, Email> kafkaTemplate;

    public void send(Email email) {
        kafkaTemplate.send(EMAIL_TOPIC, email);
    }
}
