package br.com.yohandevmeia.rrsystem.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.yohandevmeia.rrsystem.models.ReportModel;
import br.com.yohandevmeia.rrsystem.models.ReservationModel;
import br.com.yohandevmeia.rrsystem.repositories.AdminRepository;
import br.com.yohandevmeia.rrsystem.repositories.ReportRepository;
import br.com.yohandevmeia.rrsystem.repositories.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ReportService extends GlobalValidationService{
	private final ReportRepository reportRepository;
	private final AdminRepository adminRepository;
	private final ReservationRepository reservationRepository;
	
	public ReportService(
			ReportRepository reportRepository,
			AdminRepository adminRepository,
			ReservationRepository reservationRepository) {
		this.reportRepository = reportRepository;
		this.adminRepository = adminRepository;
		this.reservationRepository = reservationRepository;
	}

	@Transactional
	public void create(ReportModel report, long adminId, List<Long> reservationIds) {
	    verifyObject(report, "Invalid data to create a report");
	    verifyId(adminId);

	    report.setAdmin(adminRepository.findById(adminId)
		        .orElseThrow(() -> new EntityNotFoundException("Admin not found")));
	    
	    if (reservationIds == null || reservationIds.isEmpty()) {
	        throw new IllegalArgumentException("A report must be associated with at least one reservation.");
	    }
	    
	    for(Long reservationId : reservationIds) {
	    	ReservationModel reservation = reservationRepository.findById(reservationId)
	    			.orElseThrow(() -> new EntityNotFoundException("Reservation with ID: " + reservationId + " not found"));
	    	report.addReservation(reservation);
	    }
	    
	    report.setGenerationDate(LocalDateTime.now());

	    reportRepository.save(report);
	}
	
	@Transactional(readOnly = true)
	public ReportModel getReportById(long id) {
		verifyId(id);
		return reportRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Report with ID: " + id + " not found"));
	}
	
	@Transactional(readOnly = true)
	public List<ReportModel> getAllReports() {
		List<ReportModel> reports = reportRepository.findAll();
		
		if (reports.isEmpty()) throw new EntityNotFoundException("Reports not found");
		
		return reports;
	}
	
	@Transactional
	public void update(ReportModel reportUpdated, List<Long> reservationIds, boolean addReservations) {
	    verifyId(reportUpdated.getId());

	    ReportModel existingReport = reportRepository.findById(reportUpdated.getId())
	    		.orElseThrow(() -> new EntityNotFoundException("Report with ID: " + reportUpdated.getId() + " not found"));

	    if (reportUpdated.getData() != null && !reportUpdated.getData().isBlank()) {
	        existingReport.setData(reportUpdated.getData());
	    }

	    if (reservationIds != null && !reservationIds.isEmpty()) {
	        List<ReservationModel> reservations = reservationRepository.findAllById(reservationIds);

	        if (reservations.size() != reservationIds.size()) {
	            throw new EntityNotFoundException("One or more reservations not found.");
	        }

	        if (addReservations) {
	            for(ReservationModel reservation : reservations) existingReport.addReservation(reservation);
	        } else {
	            for(ReservationModel reservation : reservations) existingReport.removeReservation(reservation);
	        }
	    }

	    reportRepository.save(existingReport);
	}
	
	@Transactional
	public void delete(long id) {
		verifyId(id);
		reportRepository.deleteById(id);
	}
}
