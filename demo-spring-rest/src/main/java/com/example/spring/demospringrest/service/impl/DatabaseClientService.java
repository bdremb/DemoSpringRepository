package com.example.spring.demospringrest.service.impl;

import com.example.spring.demospringrest.aop.Loggable;
import com.example.spring.demospringrest.exception.EntityNotFoundException;
import com.example.spring.demospringrest.model.Client;
import com.example.spring.demospringrest.model.Order;
import com.example.spring.demospringrest.repository.DatabaseClientRepository;
import com.example.spring.demospringrest.repository.DatabaseOrderRepository;
import com.example.spring.demospringrest.service.ClientService;
import com.example.spring.demospringrest.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DatabaseClientService implements ClientService {

    private final DatabaseClientRepository clientRepository;

    private final DatabaseOrderRepository orderRepository;

    @Override
    @Loggable
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Клиент с ID={0} не найден", id)));
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client update(Client client) {
        Client existedClient = findById(client.getId());
        BeanUtils.copyNonNullProperties(client, existedClient);
        return clientRepository.save(existedClient);
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    @Transactional
    @Loggable
    public Client saveWithOrders(Client client, List<Order> orders) {
        Client savedClient = clientRepository.save(client);
        for (Order order : orders) {
            order.setClient(savedClient);
            Order savedOrder = orderRepository.save(order);
            savedClient.addOrder(savedOrder);
        }
        return savedClient;
    }

}
