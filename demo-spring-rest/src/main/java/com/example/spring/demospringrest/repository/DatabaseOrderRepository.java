package com.example.spring.demospringrest.repository;

import com.example.spring.demospringrest.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DatabaseOrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    Page<Order> findAllByProduct(String product, Pageable pageable);


    //example
    @Query("SELECT o FROM com.example.spring.demospringrest.model.Order o WHERE o.product = :productName")
    List<Order> getByProduct(String productName);

    //example with SQL
    @Query(value = "SELECT * FROM orders o WHERE o.product = :productName", nativeQuery = true)
    List<Order> getByProductName(String productName);
}
