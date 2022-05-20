package com.bootcamp.nttdata.ms.clientes.controller;

import com.bootcamp.nttdata.ms.clientes.service.ClientService;
import com.bootcamp.nttdata.ms.commons.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/all")
    public List<Client> getAllClients(){
        return clientService.findAll();
    }

    @GetMapping(value = "/findBy/{id}")
    public Client findById(@PathVariable String id){
        return clientService.findById(id);
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Client Client(@RequestBody Client client){
        return clientService.save(client);
    }

    @PutMapping(value = "/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Client updateClient(@PathVariable String id, @RequestBody Client client){
        Client clientUpdate = clientService.findById(id);

        clientUpdate.setFirstName(client.getFirstName());
        clientUpdate.setLastName(client.getLastName());
        clientUpdate.setDocumentNumber(client.getDocumentNumber());
        clientUpdate.setAge(client.getAge());
        clientUpdate.setType(client.getType());

        return clientService.save(clientUpdate);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable String id){
        clientService.deleteById(id);
    }
}
