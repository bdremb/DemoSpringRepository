package com.example.spring.demospringrest.service.impl;

import com.example.spring.demospringrest.exception.EntityNotFoundException;
import com.example.spring.demospringrest.model.Client;
import com.example.spring.demospringrest.model.Order;
import com.example.spring.demospringrest.repository.ClientRepository;
import com.example.spring.demospringrest.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Клиент с id={0} не найден", id)));
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client update(Client client) {
        return clientRepository.update(client);
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Client saveWithOrders(Client client, List<Order> orders) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
