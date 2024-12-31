package br.com.yohandevmeia.rrsystem.services;

import org.springframework.stereotype.Service;

import br.com.yohandevmeia.rrsystem.repositories.ReservationRepository;

@Service
public class ReservationService {
	
	private final ReservationRepository reservationRepository;
	
	public ReservationService(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}
}
