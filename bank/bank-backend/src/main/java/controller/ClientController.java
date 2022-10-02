package controller;

import entity.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ClientService;

import java.util.List;

@RestController
public class ClientController {

    private ClientService clientService;

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable("id") Integer id) {
        return clientService.getClientById(id);
    }

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        return new ResponseEntity<>(clientService.addClient(client), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Client> updateClient(@PathVariable("id") Integer id, @RequestBody Client client) {
        return new ResponseEntity<>(clientService.updateClient(id, client), HttpStatus.OK);
    }

    @DeleteMapping
    public void deleteClientById(@PathVariable("id") Integer id) {
        clientService.deleteClientById(id);
    }
}
