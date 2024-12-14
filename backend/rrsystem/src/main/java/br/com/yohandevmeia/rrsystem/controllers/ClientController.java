package br.com.yohandevmeia.rrsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.yohandevmeia.rrsystem.dtos.client.ReqDTO;
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
}
