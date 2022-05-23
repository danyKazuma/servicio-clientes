package com.bootcamp.nttdata.ms.clientes.service;

import com.bootcamp.nttdata.ms.commons.entity.Client;

import java.util.List;

public interface ClientService{

    public List<Client> findAll();

    public Client findById(String id) throws InterruptedException;

    public Client save(Client client);

    public void deleteById(String id);
}
