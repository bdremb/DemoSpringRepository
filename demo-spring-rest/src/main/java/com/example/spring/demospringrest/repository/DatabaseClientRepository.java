package com.example.spring.demospringrest.repository;

import com.example.spring.demospringrest.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatabaseClientRepository extends JpaRepository<Client, Long> {
}
