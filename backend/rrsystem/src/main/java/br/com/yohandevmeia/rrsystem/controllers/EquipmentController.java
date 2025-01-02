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

import br.com.yohandevmeia.rrsystem.dtos.EquipmentDTO;
import br.com.yohandevmeia.rrsystem.services.EquipmentService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("equipments")
public class EquipmentController {

	@Autowired
	private EquipmentService equipmentService;
	
	@PostMapping
	public ResponseEntity<String> createEquipment(@Valid @RequestBody EquipmentDTO dto) {
		equipmentService.create(dto.convertDTOToObject(), dto.roomId());
		return new ResponseEntity<>("Equipment created successfully", HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<?> getAllEquipments() {
		return new ResponseEntity<>(EquipmentDTO.convertAllEquipments(equipmentService.getAllEquipments()), HttpStatus.OK);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> getEquipmentById(@PathVariable long id) {
		return new ResponseEntity<>(new EquipmentDTO(equipmentService.getEquipmentById(id)), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<String> updateEquipment(@Valid @RequestBody EquipmentDTO dto) {
		equipmentService.update(dto.convertDTOToObject(), dto.roomId());
		return new ResponseEntity<>("Equipment updated successfully", HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEquipment(@PathVariable long id) {
		equipmentService.delete(id);
		return new ResponseEntity<>("Equipment deleted successfully", HttpStatus.OK);
	}
}
