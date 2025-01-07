package br.com.yohandevmeia.rrsystem.dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.yohandevmeia.rrsystem.models.ReportModel;

public record ReportReadDTO(
		long id,
		long adminId,
		LocalDateTime generationDate,
		String data,
		List<ReservationDTO> reservations) {

	public ReportReadDTO(ReportModel report) {
		this(
				report.getId(),
				report.getAdmin().getId(),
				report.getGenerationDate(),
				report.getData(),
				ReservationDTO.convertAllToDTO(report.getReservations()));
	}
	
	public static ArrayList<ReportReadDTO> convertAllToDTO(List<ReportModel> reports) {
		ArrayList<ReportReadDTO> dtos = new ArrayList<>();
		for(ReportModel report : reports) {
			ReportReadDTO dto = new ReportReadDTO(report);
			dtos.add(dto);
		}
		return dtos;
	}
}
