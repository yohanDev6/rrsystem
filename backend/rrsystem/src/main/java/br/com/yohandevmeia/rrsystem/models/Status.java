package br.com.yohandevmeia.rrsystem.models;

public enum Status {
	RESERVED, 	// reservada
	CANCELED,	// cancelada
	POSTPONED,	// adiada
	MODIFIED,	// modificada
	HAPPENING,	// acontecendo
	FINISHED;	// terminada
	
	public static String statusToString(String status) {
	    if (status == null || status.isBlank()) {
	        throw new IllegalArgumentException("Status string cannot be null or blank");
	    }
	    
	    try {
	        Status statusEnum = Status.valueOf(status.trim().toUpperCase());
	        return statusEnum.name();
	    } catch (IllegalArgumentException e) {
	        throw new IllegalArgumentException("Invalid status string: " + status, e);
	    }
	}
}
