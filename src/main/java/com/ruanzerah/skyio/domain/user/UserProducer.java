//package com.ruanzerah.skyio.domain.user;
//
//import lombok.AllArgsConstructor;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@AllArgsConstructor
//@Service
//public class UserProducer {
//    private final KafkaTemplate kafkaTemplate;
//
//    public void create(UserDTO dto) {
//        kafkaTemplate.send("user_creation", dto);
//    }
//}
