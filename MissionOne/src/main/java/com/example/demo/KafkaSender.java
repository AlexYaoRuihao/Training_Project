package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class KafkaSender {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    public KafkaSender(){};

    /**
     * 发送消息到kafka,主题为test
     */
    public void sendTest(String msg){
        kafkaTemplate.send("test",msg);
    }
}