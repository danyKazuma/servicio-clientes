package com.bootcamp.nttdata.ms.clientes.service.impl;

import com.bootcamp.nttdata.ms.clientes.repository.ClientRepository;
import com.bootcamp.nttdata.ms.clientes.service.ClientService;
import com.bootcamp.nttdata.ms.commons.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ClientServiceImpl implements ClientService {

  @Autowired
  private ClientRepository clientRepository;

  @Override
  public List<Client> findAll() {
    return clientRepository.findAll();
  }

  @Override
  public Client findById(String id) throws InterruptedException {
    if(id.equals("22222")) {
      throw new IllegalStateException("Cliente no encontrado");
    }

    if(id.equals("11111")) {
      TimeUnit.SECONDS.sleep(5L);
    }

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
