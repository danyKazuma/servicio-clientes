package com.bootcamp.nttdata.ms.clientes.controller;

import com.bootcamp.nttdata.ms.clientes.service.ClientService;
import com.bootcamp.nttdata.ms.commons.entity.Client;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class ClientController {

    private final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @Autowired
    private CircuitBreakerFactory cbFactory;

    @GetMapping(value = "/all")
    public List<Client> getAllClients(){
        return clientService.findAll();
    }

//    @GetMapping(value = "/findBy/{id}")
//    public Client findById(@PathVariable String id)throws InterruptedException{
//
//        return cbFactory.create("clientes")
//                .run(() -> {
//                    try {
//                        return clientService.findById(id);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                }, e -> metodoAlternativo(id, e));
//    }

    @GetMapping(value = "/findBy/{id}")
    @CircuitBreaker(name = "clients", fallbackMethod = "metodoAlternativo")
    public Client findById(@PathVariable String id){

        try {
            return clientService.findById(id);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Client metodoAlternativo(String id){

        Client client = new Client();

        client.setId(id);
        client.setFirstName("NombrePrueba");
        client.setLastName("ApellidoPrueba");
        client.setDocumentNumber("DNIPrueba");
        client.setAge("EdadPrueba");
        client.setType("TipoPrueba");

        return client;
    }

    public String metodoAlternativo2(){
        return "EL SERVICIO NO SE ENCUENTRA DISPONIBLE";
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Client Client(@RequestBody Client client){
        return clientService.save(client);
    }

    @PutMapping(value = "/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Client updateClient(@PathVariable String id, @RequestBody Client client) throws InterruptedException {
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
