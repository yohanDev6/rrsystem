package br.com.yohandevmeia.rrsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.yohandevmeia.rrsystem.repositories.ClientRepository;
import br.com.yohandevmeia.rrsystem.services.ClientService;

@Configuration
public class Services {

    @Autowired
    private ClientRepository clientRepository;

    @Bean
    public ClientService clientService() {
        return new ClientService(clientRepository);
    }
}
