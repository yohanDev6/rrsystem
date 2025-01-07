package br.com.yohandevmeia.rrsystem.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.yohandevmeia.rrsystem.dtos.AuthDTO;
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
	public ResponseEntity<?> login(@Valid @RequestBody AuthDTO dto) {
	    var authToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
	    var auth = authManager.authenticate(authToken);

	    var client = (ClientModel) auth.getPrincipal();
	    List<String> roles = client.getAuthorities().stream()
	                               .map(GrantedAuthority::getAuthority)
	                               .collect(Collectors.toList());

	    String token = jwtService.generateToken(client.getUsername(), roles);

	    Map<String, String> response = new HashMap<>();
	    response.put("token", token);
	    
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
