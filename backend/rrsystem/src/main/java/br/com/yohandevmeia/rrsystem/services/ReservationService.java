package br.com.yohandevmeia.rrsystem.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.yohandevmeia.rrsystem.models.ReservationModel;
import br.com.yohandevmeia.rrsystem.repositories.ClientRepository;
import br.com.yohandevmeia.rrsystem.repositories.ReservationRepository;
import br.com.yohandevmeia.rrsystem.repositories.RoomRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ReservationService extends GlobalValidationService{
	
	private final ReservationRepository reservationRepository;
	private final ClientRepository clientRepository;
	private final RoomRepository roomRepository;
	
	public ReservationService(ReservationRepository reservationRepository,
			ClientRepository clientRepository,
			RoomRepository roomRepository) {
		this.reservationRepository = reservationRepository;
		this.clientRepository = clientRepository;
		this.roomRepository = roomRepository;
	}
	
	@Transactional
	public void create(ReservationModel reservation, long clientId, long roomId) {
		verifyObject(reservation, "Invalid data to create a reservation");
		verifyId(clientId);
		verifyId(roomId);
		reservation.setClient(clientRepository.findById(clientId)
				.orElseThrow(() -> new EntityNotFoundException("Client not found")));
		reservation.setRoom(roomRepository.findById(roomId)
				.orElseThrow(() -> new EntityNotFoundException("Room not found")));
		reservationRepository.save(reservation);
	}
	
	@Transactional(readOnly = true)
	public ReservationModel getReservationById(long id) {
		verifyId(id);
		return reservationRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Reservation with ID: " + id + " not found"));
	}
	
	@Transactional(readOnly = true)
	public List<ReservationModel> getAllReservations(){
		return reservationRepository.findAll();
	}
	
	@Transactional
	public void update(ReservationModel reservUpdated, long roomId) {
		verifyObject(reservUpdated, "Invalid data to update a reservation");
		
		ReservationModel existingReserv = getReservationById(reservUpdated.getId());
		
		if (existingReserv.getRoom().getId() != roomId) {
			verifyId(roomId);
			existingReserv.setRoom(roomRepository.findById(roomId)
					.orElseThrow(() -> new EntityNotFoundException("Room not found")));
		}
		
		existingReserv.setReservationDateTime(reservUpdated.getReservationDateTime());
		existingReserv.setStatus(reservUpdated.getStatus());
		reservationRepository.save(existingReserv);
	}
	
	@Transactional
	public void delete(long id) {
		verifyId(id);
		reservationRepository.deleteById(id);
	}
}
