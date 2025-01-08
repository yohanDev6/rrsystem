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

import br.com.yohandevmeia.rrsystem.dtos.RoomDTO;
import br.com.yohandevmeia.rrsystem.services.RoomService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/rooms")
public class RoomController {

	@Autowired
	private RoomService roomService;

	@PostMapping
	public ResponseEntity<String> createRoom(@Valid @RequestBody RoomDTO dto) {
		roomService.create(dto.convertDTOToObject());
		return new ResponseEntity<>("Room created successfully", HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<?> getAllRooms() {
		return new ResponseEntity<>(RoomDTO.convertAllToDTO(roomService.getAllRooms()), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getRoomById(@PathVariable long id) {
		return new ResponseEntity<>(new RoomDTO(roomService.getRoomById(id)), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<String> updateRoom(@Valid @RequestBody RoomDTO dto) {
		roomService.update(dto.convertDTOToObject());
		return new ResponseEntity<>("Room updated successfully", HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteRoom(@PathVariable long id) {
		roomService.delete(id);
		return new ResponseEntity<>("Room deleted successfully", HttpStatus.OK);
	}
}
