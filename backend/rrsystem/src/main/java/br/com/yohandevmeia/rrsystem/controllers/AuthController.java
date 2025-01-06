package br.com.yohandevmeia.rrsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.yohandevmeia.rrsystem.dtos.client.AuthDTO;
import br.com.yohandevmeia.rrsystem.models.ClientModel;
import br.com.yohandevmeia.rrsystem.services.JwtService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtService jwtService;
	
	@PostMapping
    public ResponseEntity<String> login(@Valid @RequestBody AuthDTO dto) {
		var authToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var auth = authManager.authenticate(authToken);

        var client = (ClientModel) auth.getPrincipal();
        String token = jwtService.generateToken(client.getUsername());

        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
