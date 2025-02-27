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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.yohandevmeia.rrsystem.dtos.AuthDTO;
import br.com.yohandevmeia.rrsystem.models.ClientModel;
import br.com.yohandevmeia.rrsystem.models.RefreshToken;
import br.com.yohandevmeia.rrsystem.services.JwtService;
import br.com.yohandevmeia.rrsystem.services.RefreshTokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/")
public class AuthController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private RefreshTokenService refreshTokenService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody AuthDTO dto) {
	    var authToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
	    var auth = authManager.authenticate(authToken);

	    var client = (ClientModel) auth.getPrincipal();
	    List<String> roles = client.getAuthorities().stream()
	                               .map(GrantedAuthority::getAuthority)
	                               .collect(Collectors.toList());

	    String accessToken = jwtService.generateToken(client.getUsername(), roles);
	    RefreshToken refreshToken = refreshTokenService.createRefreshToken(client.getId());

	    Map<String, String> response = new HashMap<>();
	    response.put("accessToken", accessToken);
	    response.put("refreshToken", refreshToken.getToken());
	    response.put("clientId", String.valueOf(client.getId()));

	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> request) {
	    String refreshToken = request.get("refreshToken");

	    RefreshToken token = refreshTokenService.verifyToken(refreshToken);
	    var client = token.getClient();

	    List<String> roles = client.getAuthorities().stream()
	                               .map(GrantedAuthority::getAuthority)
	                               .collect(Collectors.toList());

	    String newAccessToken = jwtService.generateToken(client.getUsername(), roles);
	    
	    Map<String, String> response = new HashMap<>();
	    response.put("accessToken", newAccessToken);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
	    String jwtToken = token.replace("Bearer ", "");
	    refreshTokenService.revokeToken(jwtToken);
	    return new ResponseEntity<>("Logout successfully", HttpStatus.OK);
	}
}
