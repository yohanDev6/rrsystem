package br.com.yohandevmeia.rrsystem.services;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import br.com.yohandevmeia.rrsystem.models.ClientModel;
import br.com.yohandevmeia.rrsystem.repositories.ClientRepository;

@Service
public class ClientService {
    
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // Save the client
    public void save(ClientModel client) {
        client.setPassword(BCrypt.hashpw(client.getPassword(), BCrypt.gensalt()));
        clientRepository.save(client);
    }
}
