package com.example.basedomains.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String orderId;
    private String name;
    private int quantity;
    private double price;
}
