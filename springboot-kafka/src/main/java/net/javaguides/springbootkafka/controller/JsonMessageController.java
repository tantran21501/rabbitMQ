package net.javaguides.springbootkafka.controller;

import net.javaguides.springbootkafka.kafka.JsonKafkaProducerService;
import net.javaguides.springbootkafka.payload.User;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/kafka")
public class JsonMessageController {
    private JsonKafkaProducerService jsonKafkaProducerService;

    public JsonMessageController(JsonKafkaProducerService jsonKafkaProducerService) {
        this.jsonKafkaProducerService = jsonKafkaProducerService;
    }
    @PostMapping("/publish")
    public ResponseEntity<String>  publish(@RequestBody User user){
        jsonKafkaProducerService.publish(user);
        return ResponseEntity.ok("JSON Message sent to kafka topic");
    }
}
