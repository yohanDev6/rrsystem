package br.com.yohandevmeia.rrsystem.dtos;

import java.util.List;

import br.com.yohandevmeia.rrsystem.models.ReportModel;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ReportSaveDTO(
		@NotNull(message = "Data is required")
		String data,
		
		@NotNull(message = "Admin id is required")
		long adminId,
		
		@NotEmpty(message = "At least one reservation ID is required")
		List<Long> reservationsId
		) {

	public ReportModel convertDTOToObject() {
		ReportModel report = new ReportModel();
		report.setData(data);
		return report;
	}
}
