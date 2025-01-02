package br.com.yohandevmeia.rrsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.yohandevmeia.rrsystem.dtos.client.ReqDTO;
import br.com.yohandevmeia.rrsystem.dtos.client.ResDTO;
import br.com.yohandevmeia.rrsystem.services.ClientService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("clients")
public class ClientController {
    
    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<String> createClient(@Valid @RequestBody ReqDTO dto) {    
        clientService.create(dto.convertDTOToObject());
        return new ResponseEntity<>("Client created successfully", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllClients() {
        return new ResponseEntity<>(ResDTO.convertAllClientsToDTO(clientService.getAllClients()), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getClientById(@PathVariable long id) {
        return new ResponseEntity<>(new ResDTO(clientService.getClientById(id)), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getClientById(@PathVariable String email) {
        return new ResponseEntity<>(new ResDTO(clientService.getClientByEmail(email)), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> updateClient(@Valid @RequestBody ReqDTO dto) {
        clientService.update(dto.convertDTOToObject());
        return new ResponseEntity<>("Client updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable long id) {
        clientService.delete(id);
        return new ResponseEntity<>("Client deleted successfully", HttpStatus.OK);
    }
}
