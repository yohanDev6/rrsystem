package br.com.yohandevmeia.rrsystem.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.yohandevmeia.rrsystem.models.RoomModel;
import br.com.yohandevmeia.rrsystem.repositories.RoomRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class RoomService extends GlobalValidationService{
	
	private final RoomRepository roomRepository;
	
	public RoomService(RoomRepository roomRepository) {
		this.roomRepository = roomRepository;
	}
	
	@Transactional
	public void create(RoomModel room) {
		verifyObject(room, "Invalid data to create a room");
		roomRepository.save(room);
	}
	
	@Transactional(readOnly = true)
	public RoomModel getRoomById(long id) {
		verifyId(id);
		return roomRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Room with ID: " + id + "not found"));
	}
	
	@Transactional(readOnly = true)
	public List<RoomModel> getAllRooms() {
		return roomRepository.findAll();
	}
	
	@Transactional
	public void update(RoomModel roomUpdated) {
		verifyObject(roomUpdated, "Invalid data to update the room");
		
		RoomModel existingRoom = roomRepository.findById(roomUpdated.getId())
				.orElseThrow(() -> new EntityNotFoundException("Room not found"));
		
		existingRoom.setName(roomUpdated.getName());
		existingRoom.setCapacity(roomUpdated.getCapacity());
		existingRoom.setAvailability(roomUpdated.isAvailability());
		
		roomRepository.save(existingRoom);
	}
	
	@Transactional
	public void delete(long id) {
		verifyId(id);
		roomRepository.deleteById(id);
	}
}
