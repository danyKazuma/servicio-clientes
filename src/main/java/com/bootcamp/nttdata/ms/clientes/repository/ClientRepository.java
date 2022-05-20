package com.bootcamp.nttdata.ms.clientes.repository;

import com.bootcamp.nttdata.ms.commons.entity.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ClientRepository extends MongoRepository<Client, String> {
}
