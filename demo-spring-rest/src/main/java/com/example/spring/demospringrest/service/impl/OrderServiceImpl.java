package com.example.spring.demospringrest.service.impl;


import com.example.spring.demospringrest.exception.EntityNotFoundException;
import com.example.spring.demospringrest.model.Order;
import com.example.spring.demospringrest.repository.OrderRepository;
import com.example.spring.demospringrest.service.OrderService;
import com.example.spring.demospringrest.web.model.OrderFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<Order> filterBy(OrderFilter filter) {
       throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Заказ с id={0} не найден", id)));
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        checkForUpdate(order.getId());
        return orderRepository.update(order);
    }

    @Override
    public void deleteById(Long id) {
        Order currentOrder = findById(id);
        currentOrder.getClient().removeOrder(id);
        orderRepository.deleteById(id);
    }

    @Override
    public void deleteByIdIn(List<Long> ids) {
        orderRepository.deleteByIdIn(ids);
    }

}
