package com.example.spring.demospringrest.web.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpsertOrderRequest {

    private Long clientId;
    private String product;
    private BigDecimal cost;

}
