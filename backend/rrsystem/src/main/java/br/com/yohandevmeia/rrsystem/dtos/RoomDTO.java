package br.com.yohandevmeia.rrsystem.dtos;

import java.util.ArrayList;
import java.util.List;

import br.com.yohandevmeia.rrsystem.models.RoomModel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RoomDTO(
		long id,
		
		@NotNull(message = "Name is required")
		@Size(message = "Name min size is 3", min = 3)
		String name,
		
		@NotNull(message = "Capacity is required")
		int capacity,
		
		@NotNull(message = "Availability is required")
		boolean availability
		) {

	public RoomDTO(RoomModel room) {
		this(room.getId(), room.getName(), room.getCapacity(), room.isAvailability());
	}
	
	public RoomModel convertDTOToObject() {
		RoomModel room = new RoomModel();
		if(id > 0) {
			room.setId(id);
		}
		room.setName(name);
		room.setCapacity(capacity);
		room.setAvailability(availability);
		return room;
	}
	
	public static ArrayList<RoomDTO> convertAllToDTO(List<RoomModel> rooms) {
		ArrayList<RoomDTO> dtos = new ArrayList<>();
		for (RoomModel room : rooms) {
			RoomDTO dto = new RoomDTO(room);
			dtos.add(dto);
		}
		return dtos;
	}
}
