package br.com.yohandevmeia.rrsystem.services;

import org.springframework.stereotype.Service;

import br.com.yohandevmeia.rrsystem.repositories.ReportRepository;

@Service
public class ReportService {
	private final ReportRepository reportRepository;
	
	public ReportService(ReportRepository reportRepository) {
		this.reportRepository = reportRepository;
	}
}
