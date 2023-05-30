package net.javaguides.springbootkafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {
    @Value("${spring.kafka.topic.name}")
    private String topicName;
    @Value("${spring.kafka.topic.json.name}")
    private String topicJsonName;
    @Bean
    public KafkaAdmin.NewTopics createTopic(){
        return new KafkaAdmin.NewTopics(
                TopicBuilder.name(topicName)
//                .partitions(10)
//                .replicas(3)
                        .build(),
                TopicBuilder.name(topicJsonName)
//                .partitions(10)
//                .replicas(3)
                        .build()
        );

    }



}
