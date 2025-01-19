package br.com.yohandevmeia.rrsystem.exceptions;

public class JwtInvalidException extends RuntimeException{
	public JwtInvalidException(String message) {
		super(message);
	}
}
