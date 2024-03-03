package com.example.spring.demospringrest.web.model;

import com.example.spring.demospringrest.validation.OrderFilterValid;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@OrderFilterValid
public class OrderFilter {

    private Integer pageSize;

    private Integer pageNumber;

    private String productName;

    private BigDecimal minCost;

    private BigDecimal maxCost;

    private Instant createBefore;

    private Instant updateBefore;

    private Long clientId;

}
