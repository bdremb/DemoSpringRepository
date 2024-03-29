package com.example.spring.demospringrest.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Builder
@Entity(name = "clients")
@FieldNameConstants
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long id;

    @Column(name = "client_name")
    private String name;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        if(orders == null) {
            orders = new ArrayList<>();
        }
        orders.add(order);
    }

    public void removeOrder(Long orderId) {
        orders = orders.stream().filter(order -> !order.getId().equals(orderId)).collect(Collectors.toList());
    }

    //add Builder.Default instead this method
//    public List<Order> getOrders() {
//        if(orders == null) {
//            orders = new ArrayList<>();
//        }
//        return orders;
//    }
}
