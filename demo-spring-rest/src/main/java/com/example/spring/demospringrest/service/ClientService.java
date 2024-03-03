package com.example.spring.demospringrest.service;

import com.example.spring.demospringrest.model.Client;
import com.example.spring.demospringrest.model.Order;

import java.util.List;

public interface ClientService {

    List<Client> findAll();

    Client findById(Long id);

    Client save(Client client);

    Client update(Client client);

    void deleteById(Long id);

    Client saveWithOrders(Client client, List<Order> orders);
}
