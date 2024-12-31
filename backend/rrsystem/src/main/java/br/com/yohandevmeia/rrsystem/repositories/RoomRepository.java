package br.com.yohandevmeia.rrsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.yohandevmeia.rrsystem.models.RoomModel;

public interface RoomRepository extends JpaRepository<RoomModel, Long>{

}
