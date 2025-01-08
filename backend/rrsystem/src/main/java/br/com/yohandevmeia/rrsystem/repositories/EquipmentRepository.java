package br.com.yohandevmeia.rrsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.yohandevmeia.rrsystem.models.EquipmentModel;

@Repository
public interface EquipmentRepository extends JpaRepository<EquipmentModel, Long>{

	long countByRoomId(long roomId);
}
