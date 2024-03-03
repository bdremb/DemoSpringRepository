package com.example.spring.demospringrest.repository;

import com.example.spring.demospringrest.model.Client;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DatabaseClientRepository extends JpaRepository<Client, Long> {

    @Override
    @EntityGraph(attributePaths = {"orders"})
    List<Client> findAll();
}
