package br.com.yohandevmeia.rrsystem.dtos;

import java.util.List;

import br.com.yohandevmeia.rrsystem.models.ReportModel;
import jakarta.validation.constraints.NotNull;

public record ReportUpdateDTO(
		@NotNull(message = "Report Id is required")
		
		long id,
		String data,
		List<Long> reservationsId,
		
		@NotNull(message = "Operation type (add/remove) must be specified with boolean data")
		boolean addReservations) {

	public ReportModel convertDTOToObject() {
		ReportModel report = new ReportModel();
		report.setId(id);
		report.setData(data);
		return report;
	}
}
