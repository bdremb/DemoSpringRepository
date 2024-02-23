package com.example.spring.demospringrest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Long id;
    private String product;
    private BigDecimal cost;
    private Client client;
    private Instant createdAt;
    private Instant updatedAt;

}
