package br.com.yohandevmeia.rrsystem.exceptions;

public class JwtMissingException extends RuntimeException{
	public JwtMissingException(String message) {
		super(message);
	}
}
