package com.example.spring.demospringrest.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class Order {

    private Long id;
    private String product;
    private BigDecimal cost;
    private Client client;
    private Instant createdAt;
    private Instant updatedAt;

}
