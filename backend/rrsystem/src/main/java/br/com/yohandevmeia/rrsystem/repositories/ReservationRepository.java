package br.com.yohandevmeia.rrsystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.yohandevmeia.rrsystem.models.ReservationModel;

public interface ReservationRepository extends JpaRepository<ReservationModel, Long>{
	List<ReservationModel> findAllById(Iterable<Long> ids);
}
