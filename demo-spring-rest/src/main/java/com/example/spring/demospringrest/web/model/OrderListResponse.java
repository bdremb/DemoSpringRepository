package com.example.spring.demospringrest.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderListResponse {

    private List<OrderResponse> orders = new ArrayList<>();
}
