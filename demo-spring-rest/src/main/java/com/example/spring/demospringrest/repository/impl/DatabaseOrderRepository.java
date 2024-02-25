package com.example.spring.demospringrest.repository.impl;

import com.example.spring.demospringrest.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatabaseOrderRepository extends JpaRepository<Order, Long> {
}
