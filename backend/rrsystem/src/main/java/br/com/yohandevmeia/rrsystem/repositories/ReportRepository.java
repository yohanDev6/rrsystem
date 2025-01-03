package br.com.yohandevmeia.rrsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.yohandevmeia.rrsystem.models.ReportModel;

@Repository
public interface ReportRepository extends JpaRepository<ReportModel, Long>{

}
