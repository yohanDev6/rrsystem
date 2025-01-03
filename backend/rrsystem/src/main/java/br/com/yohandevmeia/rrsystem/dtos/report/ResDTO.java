package br.com.yohandevmeia.rrsystem.dtos.report;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.yohandevmeia.rrsystem.dtos.ReservationDTO;
import br.com.yohandevmeia.rrsystem.models.ReportModel;

public record ResDTO(
		long id,
		long adminId,
		LocalDateTime generationDate,
		String data,
		List<ReservationDTO> reservations) {

	public ResDTO(ReportModel report) {
		this(
				report.getId(),
				report.getAdmin().getId(),
				report.getGenerationDate(),
				report.getData(),
				ReservationDTO.convertAllToDTO(report.getReservations()));
	}
	
	public static ArrayList<ResDTO> convertAllToDTO(List<ReportModel> reports) {
		ArrayList<ResDTO> dtos = new ArrayList<>();
		for(ReportModel report : reports) {
			ResDTO dto = new ResDTO(report);
			dtos.add(dto);
		}
		return dtos;
	}
}
