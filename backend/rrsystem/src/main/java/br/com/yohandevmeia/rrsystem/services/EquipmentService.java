package br.com.yohandevmeia.rrsystem.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.yohandevmeia.rrsystem.models.EquipmentModel;
import br.com.yohandevmeia.rrsystem.models.RoomModel;
import br.com.yohandevmeia.rrsystem.repositories.EquipmentRepository;
import br.com.yohandevmeia.rrsystem.repositories.RoomRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EquipmentService {

	private final EquipmentRepository equipmentRepository;
	private final RoomRepository roomRepository;
	
	public EquipmentService(EquipmentRepository equipmentRepository, RoomRepository roomRepository) {
		this.equipmentRepository = equipmentRepository;
		this.roomRepository = roomRepository;
	}
	
	@Transactional
	public void save(EquipmentModel equipment, long roomId) {
		if (equipment == null) {
			throw new IllegalArgumentException("Invalid data to create an equipment");
		}

		equipment.setRoom(getRoomToCreateOrUpdateEquipment(roomId));
		
		equipmentRepository.save(equipment);
	}
	
	@Transactional(readOnly = true)
	public EquipmentModel getEquipmentById(long id) {
		if (id <= 0) {
			throw new IllegalArgumentException("Invalid ID");
		}
		return equipmentRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Equipment with ID: "+ id +" not found"));
	}
	
	@Transactional(readOnly = true)
	public List<EquipmentModel> getAllEquipments() {
		return equipmentRepository.findAll();
	}
	
	@Transactional
	public void update(EquipmentModel equipmentUpdated, long newRoomId) {
		if (equipmentUpdated == null || equipmentUpdated.getId() <= 0) {
			throw new IllegalArgumentException("Invalid data to update the equipment");
		}
		
		EquipmentModel existingEquipment = getEquipmentById(equipmentUpdated.getId());
		
		existingEquipment.setName(equipmentUpdated.getName());
		
		if(existingEquipment.getRoom().getId() != newRoomId) {
			existingEquipment.setRoom(getRoomToCreateOrUpdateEquipment(newRoomId));
		}
		
		equipmentRepository.save(existingEquipment);
	}
	
	@Transactional
	public void delete(long id) {
		if (id <= 0) {
			throw new IllegalArgumentException("Invalid ID");
		}
		equipmentRepository.deleteById(id);
	}
	
	private RoomModel getRoomToCreateOrUpdateEquipment(long roomId) {
		return roomRepository.findById(roomId)
				.orElseThrow(() -> new EntityNotFoundException("Room not found"));
	}
}
