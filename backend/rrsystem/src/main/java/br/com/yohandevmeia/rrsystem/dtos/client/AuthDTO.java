package br.com.yohandevmeia.rrsystem.dtos.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record AuthDTO(
		@Email(message = "Email must be valid")
		@NotNull(message = "Email is required")
		String email,
		
		@NotNull(message = "Password is required")
		String password) {
	
}
