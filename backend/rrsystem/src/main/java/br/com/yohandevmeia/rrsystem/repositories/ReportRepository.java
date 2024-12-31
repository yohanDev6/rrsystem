package br.com.yohandevmeia.rrsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.yohandevmeia.rrsystem.models.ReportModel;

public interface ReportRepository extends JpaRepository<ReportModel, Long>{

}
