package br.com.yohandevmeia.rrsystem.controllers;

import java.util.List;

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
import br.com.yohandevmeia.rrsystem.models.ClientModel;
import br.com.yohandevmeia.rrsystem.services.ClientService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("clients")
public class ClientController {
    
    @Autowired
    private ClientService clientService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> saveClient(@Valid @RequestBody ReqDTO dto) {
        ClientModel client = dto.convertDTOToOClientModel();
        clientService.save(client);
        return new ResponseEntity<>("Client created successfully", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllClients() {
        List<ClientModel> clients = clientService.getAllClients();
        return new ResponseEntity<>(ResDTO.convertAllClientsToDTO(clients), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getClientById(@PathVariable long id) {
        ClientModel client = clientService.getClientById(id);
        ResDTO dto = new ResDTO(client);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getClientById(@PathVariable String email) {
        ClientModel client = clientService.getClientByEmail(email);
        ResDTO dto = new ResDTO(client);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> updateClient(@Valid @RequestBody ReqDTO dto) {
        ClientModel client = dto.convertDTOToOClientModel();
        clientService.update(client);
        return new ResponseEntity<>("Client updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable long id) {
        clientService.delete(id);
        return new ResponseEntity<>("Client deleted successfully", HttpStatus.OK);
    }
}
