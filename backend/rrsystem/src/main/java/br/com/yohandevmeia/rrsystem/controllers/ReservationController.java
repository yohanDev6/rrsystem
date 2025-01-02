package br.com.yohandevmeia.rrsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.yohandevmeia.rrsystem.dtos.ReservationDTO;
import br.com.yohandevmeia.rrsystem.services.ReservationService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("reservations")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	
	@PostMapping
	public ResponseEntity<String> createReservation(@Valid @RequestBody ReservationDTO dto){
		reservationService.create(dto.convertDTOToObject(), dto.clientId(), dto.roomId());
		return new ResponseEntity<>("Reservation created successfully", HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<?> getAllReservations() {
		return new ResponseEntity<>(ReservationDTO.convertAllToDTO(reservationService.getAllReservations()), HttpStatus.OK);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> getReservationById(@PathVariable long id) {
		return new ResponseEntity<>(new ReservationDTO(reservationService.getReservationById(id)), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<String> updateReservation(@Valid @RequestBody ReservationDTO dto) {
		reservationService.update(dto.convertDTOToObject(), dto.roomId());
		return new ResponseEntity<>("Reservation updated successfully", HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteReservation(@PathVariable long id) {
		reservationService.delete(id);
		return new ResponseEntity<>("Reservation deleted successfully", HttpStatus.OK);
	}
}
