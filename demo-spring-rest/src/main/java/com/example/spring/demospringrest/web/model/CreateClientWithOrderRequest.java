package com.example.spring.demospringrest.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateClientWithOrderRequest {

    private String name;

    private List<OrderRequest> orders;
}
