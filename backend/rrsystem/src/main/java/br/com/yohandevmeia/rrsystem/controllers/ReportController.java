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

import br.com.yohandevmeia.rrsystem.dtos.ReportReadDTO;
import br.com.yohandevmeia.rrsystem.dtos.ReportSaveDTO;
import br.com.yohandevmeia.rrsystem.dtos.ReportUpdateDTO;
import br.com.yohandevmeia.rrsystem.services.ReportService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/reports")
public class ReportController {

	@Autowired
	private ReportService reportService;
	
	@PostMapping
	public ResponseEntity<String> createReport(@Valid @RequestBody ReportSaveDTO dto) {
		reportService.create(dto.convertDTOToObject(), dto.adminId(), dto.reservationsId());
		return new ResponseEntity<>("Report created successfully", HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<?> getAllReports() {
		return new ResponseEntity<>(ReportReadDTO.convertAllToDTO(reportService.getAllReports()), HttpStatus.OK);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> getReportById(@PathVariable long id) {
		return new ResponseEntity<>(new ReportReadDTO(reportService.getReportById(id)), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<String> updateReport(@Valid @RequestBody ReportUpdateDTO dto) {
		reportService.update(dto.convertDTOToObject(), dto.reservationsId(), dto.addReservations());
		return new ResponseEntity<>("Report updated successfully", HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteReport(@PathVariable long id) {
		reportService.delete(id);
		return new ResponseEntity<>("Report deleted successfully", HttpStatus.OK);
	}
}
