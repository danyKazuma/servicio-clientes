package com.bootcamp.nttdata.ms.clientes.service.impl;

import com.bootcamp.nttdata.ms.clientes.repository.ClientRepository;
import com.bootcamp.nttdata.ms.clientes.service.ClientService;
import com.bootcamp.nttdata.ms.commons.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

  @Autowired
  private ClientRepository clientRepository;

  @Override
  public List<Client> findAll() {
    return clientRepository.findAll();
  }

  @Override
  public Client findById(String id) {
    return clientRepository.findById(id).orElse(null);
  }

  @Override
  public Client save(Client client) {
    return clientRepository.save(client);
  }

  @Override
  public void deleteById(String id) {
    clientRepository.deleteById(id);
  }
}
