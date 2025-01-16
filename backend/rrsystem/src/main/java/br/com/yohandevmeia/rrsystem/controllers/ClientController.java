package br.com.yohandevmeia.rrsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.yohandevmeia.rrsystem.dtos.ClientReadDTO;
import br.com.yohandevmeia.rrsystem.dtos.ClientSaveDTO;
import br.com.yohandevmeia.rrsystem.models.ClientModel;
import br.com.yohandevmeia.rrsystem.services.ClientService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientController {
    
    @Autowired
    private ClientService clientService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @PostMapping
    public ResponseEntity<String> createClient(@Valid @RequestBody ClientSaveDTO dto) {
    	ClientModel newClient = dto.convertDTOToObject();
    	newClient.setPassword(passwordEncoder.encode(newClient.getPassword()));
        clientService.create(newClient);
        return new ResponseEntity<>("Client created successfully", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllClients() {
        return new ResponseEntity<>(ClientReadDTO.convertAllClientsToDTO(clientService.getAllClients()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable long id) {
        return new ResponseEntity<>(new ClientReadDTO(clientService.getClientById(id)), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> updateClient(@Valid @RequestBody ClientSaveDTO dto) {
        clientService.update(dto.convertDTOToObject());
        return new ResponseEntity<>("Client updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable long id) {
        clientService.delete(id);
        return new ResponseEntity<>("Client deleted successfully", HttpStatus.OK);
    }
}
