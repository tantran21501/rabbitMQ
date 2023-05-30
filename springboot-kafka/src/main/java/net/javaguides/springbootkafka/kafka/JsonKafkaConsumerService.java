package net.javaguides.springbootkafka.kafka;

import net.javaguides.springbootkafka.payload.User;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JsonKafkaConsumerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaConsumerService.class);

    @KafkaListener(topics = "${spring.kafka.topic.json.name}",groupId = "${spring.kafka.consumer.group-id}")
    public void consume(User user){
        LOGGER.info(String.format("Received message -> %s" , user.toString()));
    }

}
