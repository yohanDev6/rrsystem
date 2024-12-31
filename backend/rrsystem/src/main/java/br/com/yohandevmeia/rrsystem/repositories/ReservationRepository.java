package br.com.yohandevmeia.rrsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.yohandevmeia.rrsystem.models.ReservationModel;

public interface ReservationRepository extends JpaRepository<ReservationModel, Long>{

}
