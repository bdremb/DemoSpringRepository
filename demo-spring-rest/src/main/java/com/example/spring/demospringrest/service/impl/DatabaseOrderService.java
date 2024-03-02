package com.example.spring.demospringrest.service.impl;

import com.example.spring.demospringrest.exception.EntityNotFoundException;
import com.example.spring.demospringrest.model.Client;
import com.example.spring.demospringrest.model.Order;
import com.example.spring.demospringrest.repository.DatabaseOrderRepository;
import com.example.spring.demospringrest.repository.OrderSpecification;
import com.example.spring.demospringrest.service.ClientService;
import com.example.spring.demospringrest.service.OrderService;
import com.example.spring.demospringrest.utils.BeanUtils;
import com.example.spring.demospringrest.web.model.OrderFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DatabaseOrderService implements OrderService {

    private final DatabaseOrderRepository orderRepository;

    private final ClientService databaseClientService;


    @Override
    public List<Order> filterBy(OrderFilter filter) {
        return orderRepository.findAll(
                OrderSpecification.withFilter(filter),
                PageRequest.of(filter.getPageNumber(), filter.getPageSize())
        ).getContent();
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Order ID={0} not found", id)));
    }

    @Override
    public Order save(Order order) {
        Client client = databaseClientService.findById(order.getClient().getId());
        order.setClient(client);
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        checkForUpdate(order.getId());
        Client client = databaseClientService.findById(order.getClient().getId());
        Order existedOrder = findById(order.getId());

        BeanUtils.copyNonNullProperties(order, existedOrder);
        existedOrder.setClient(client);

        return orderRepository.save(existedOrder);
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void deleteByIdIn(List<Long> ids) {
        orderRepository.deleteAllById(ids);
    }
}
