package br.com.yohandevmeia.rrsystem.services;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import br.com.yohandevmeia.rrsystem.models.ClientModel;
import br.com.yohandevmeia.rrsystem.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {
    
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // Save the client
    @Transactional
    public void save(ClientModel client) {
        if (client == null) {
            throw new IllegalArgumentException("Invalid data to create");
        }
        client.setPassword(BCrypt.hashpw(client.getPassword(), BCrypt.gensalt()));
        clientRepository.save(client);
    }

    @Transactional(readOnly = true)
    public ClientModel getClientById(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid ID");
        }

        return clientRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Client with ID " + id + " not found"));
    }

    @Transactional(readOnly = true)
    public ClientModel getClientByEmail(String email) {
        if (email.equals("") || email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Invalid email");
        }

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
        if (clientUpdated == null || clientUpdated.getId() <= 0) {
            throw new IllegalArgumentException("Invalid data to update");
        }

        ClientModel existingClient = getClientById(clientUpdated.getId());

        if (clientUpdated.getName() != null && !clientUpdated.getName().isEmpty()) {
            existingClient.setName(clientUpdated.getName());
        }
        if (clientUpdated.getEmail() != null && !clientUpdated.getEmail().isEmpty()) {
            existingClient.setEmail(clientUpdated.getEmail());
        }
        if (clientUpdated.getPhone() != null && !clientUpdated.getPhone().isEmpty()) {
            existingClient.setPhone(clientUpdated.getPhone());
        }

        clientRepository.save(existingClient);
    }

    @Transactional
    public void delete(long id) {
        ClientModel existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client not found with id: " + id));
        clientRepository.delete(existingClient);
    }
}
