package br.com.yohandevmeia.rrsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.yohandevmeia.rrsystem.models.RoomModel;

@Repository
public interface RoomRepository extends JpaRepository<RoomModel, Long>{

}
