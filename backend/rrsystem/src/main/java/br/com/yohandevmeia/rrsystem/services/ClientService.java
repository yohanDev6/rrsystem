package br.com.yohandevmeia.rrsystem.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.yohandevmeia.rrsystem.models.ClientModel;
import br.com.yohandevmeia.rrsystem.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService extends GlobalValidationService {
    
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Transactional
    public void create(ClientModel client) {
        verifyObject(client, "Invalid data to create a client");
        
        if (clientRepository.existsByEmail(client.getEmail())) {
            throw new IllegalArgumentException("A client with the email " + client.getEmail() + " already exists.");
        }

        client.setActive(true);
        clientRepository.save(client);
    }
    
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return clientRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public ClientModel getClientById(long id) {
        verifyId(id);
        return clientRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Client with ID " + id + " not found"));
    }

    @Transactional(readOnly = true)
    public List<ClientModel> getAllClients() {
        List<ClientModel> clients = clientRepository.findAll();

        if (clients.isEmpty()) {
            throw new EntityNotFoundException("Clients not found");
        }

        return clients;
    }

    //This method does not update the password
    @Transactional
    public void update(ClientModel clientUpdated) {
        verifyObject(clientUpdated, "Invalid data to update the client: client to update is null");
        verifyId(clientUpdated.getId());
        verifyEmail(clientUpdated.getEmail());
        ClientModel existingClient = getClientById(clientUpdated.getId());
        existingClient.setName(clientUpdated.getName());
        existingClient.setEmail(clientUpdated.getEmail());
        existingClient.setActive(clientUpdated.isActive());
        clientRepository.save(existingClient);
    }

    @Transactional
    public void delete(long id) {
    	verifyId(id);
        ClientModel existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client not found with id: " + id));
        clientRepository.delete(existingClient);
    }
}
