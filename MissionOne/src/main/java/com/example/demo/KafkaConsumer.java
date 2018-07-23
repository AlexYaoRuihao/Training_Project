
package com.example.demo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.*;
import kafka.javaapi.consumer.ConsumerConnector;
@Service
public class KafkaConsumer {
    private ConsumerConnector consumer;

    /**
     * 监听test主题,有消息就读取
     */
    /*@KafkaListener(topics = {"test"})
    public void consumer(String message){
        System.out.println("Kafka监听端信息: "+ message);
    }*/


    //LinkedList Implementation
    @KafkaListener(topics = {"test"})
    public void consumer(String message) {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add(message);
        System.out.println("Kafka监听端信息: " + linkedList.poll());
    }
}
