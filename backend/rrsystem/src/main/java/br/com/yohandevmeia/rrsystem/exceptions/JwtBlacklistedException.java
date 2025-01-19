package br.com.yohandevmeia.rrsystem.exceptions;

public class JwtBlacklistedException extends RuntimeException{
	public JwtBlacklistedException(String message) {
		super(message);
	}
}
