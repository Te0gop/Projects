package service;

import entity.Client;
import org.springframework.stereotype.Service;
import repository.ClientRepository;

import java.util.List;
@Service
public class ClientService {

    private ClientRepository repo;
    public Client getClientById(Integer id) {
        return repo.findById(id).orElseThrow();
    }
    public List<Client> getAllClients() {
        return repo.findAll();
    }
    public Client addClient(Client client) {
        return repo.save(client);
    }

    public Client updateClient(Integer id, Client client) {
        repo.findById(id).orElseThrow(IllegalStateException::new);

        Client updatedClient = repo.getById(id);
        updatedClient.setName(client.getName());
        updatedClient.setTransactionId(client.getTransactionId());
        updatedClient.setIsRefunded(client.getIsRefunded());

        return repo.save(updatedClient);
    }

    public void deleteClientById(Integer id) {
        repo.findById(id).orElseThrow(IllegalStateException::new);

        repo.deleteById(id);
    }
}
