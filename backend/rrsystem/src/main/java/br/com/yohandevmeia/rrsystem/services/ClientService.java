package br.com.yohandevmeia.rrsystem.services;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import br.com.yohandevmeia.rrsystem.models.ClientModel;
import br.com.yohandevmeia.rrsystem.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

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
        
        client.setActive(false);
        client.setPassword(BCrypt.hashpw(client.getPassword(), BCrypt.gensalt()));
        clientRepository.save(client);
    }

    @Transactional(readOnly = true)
    public ClientModel getClientById(long id) {
        verifyId(id);
        return clientRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Client with ID " + id + " not found"));
    }

    @Transactional(readOnly = true)
    public ClientModel getClientByEmail(String email) {
    	verifyEmail(email, "Invalid email:" + email + " to find a client");
        return clientRepository.findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException("Client with email " + email + " not found"));
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
        verifyEmail(clientUpdated.getEmail(), "Invalid data to update the client: email is null");
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
