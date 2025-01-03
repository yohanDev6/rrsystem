package br.com.yohandevmeia.rrsystem.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.yohandevmeia.rrsystem.models.EquipmentModel;
import br.com.yohandevmeia.rrsystem.repositories.EquipmentRepository;
import br.com.yohandevmeia.rrsystem.repositories.RoomRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EquipmentService extends GlobalValidationService{

	private final EquipmentRepository equipmentRepository;
	private final RoomRepository roomRepository;
	
	public EquipmentService(EquipmentRepository equipmentRepository, RoomRepository roomRepository) {
		this.equipmentRepository = equipmentRepository;
		this.roomRepository = roomRepository;
	}
	
	@Transactional
	public void create(EquipmentModel equipment, long roomId) {
		verifyObject(equipment, "Invalid data to create an equipment");
		verifyId(roomId);
		equipment.setRoom(roomRepository.findById(roomId)
				.orElseThrow(() -> new EntityNotFoundException("Room not found")));
		equipmentRepository.save(equipment);
	}
	
	@Transactional(readOnly = true)
	public EquipmentModel getEquipmentById(long id) {
		verifyId(id);
		return equipmentRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Equipment with ID: "+ id +" not found"));
	}
	
	@Transactional(readOnly = true)
	public List<EquipmentModel> getAllEquipments() {
		return equipmentRepository.findAll();
	}
	
	@Transactional
	public void update(EquipmentModel equipmentUpdated, long newRoomId) {
		verifyObject(equipmentUpdated, "Invalid data to update the equipment");

		EquipmentModel existingEquipment = getEquipmentById(equipmentUpdated.getId());
		existingEquipment.setName(equipmentUpdated.getName());
		
		if(existingEquipment.getRoom().getId() != newRoomId) {
			verifyId(newRoomId);
			existingEquipment.setRoom(roomRepository.findById(newRoomId)
					.orElseThrow(() -> new EntityNotFoundException("Room not found")));
		}
		
		equipmentRepository.save(existingEquipment);
	}
	
	@Transactional
	public void delete(long id) {
		verifyId(id);
		equipmentRepository.deleteById(id);
	}
}