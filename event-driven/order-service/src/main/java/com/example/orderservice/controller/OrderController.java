package com.example.orderservice.controller;

import com.example.basedomains.dto.Order;
import com.example.basedomains.dto.OrderEvent;
import com.example.orderservice.kafka.OrderProducerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
    private OrderProducerService orderProducerService;

    public OrderController(OrderProducerService orderProducerService) {
        this.orderProducerService = orderProducerService;
    }
    @PostMapping("/orders")
    public String  placeOrder(@RequestBody Order order){
        order.setOrderId(UUID.randomUUID().toString());

        OrderEvent event = new OrderEvent();
        event.setStatus("PENDING");
        event.setMessage("order status is in pending state");
        event.setOrder(order);

        orderProducerService.sendMessage(event);
        return "Order placed successfully...";
    }
}
