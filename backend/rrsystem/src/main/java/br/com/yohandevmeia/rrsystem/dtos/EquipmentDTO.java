package br.com.yohandevmeia.rrsystem.dtos;

import java.util.ArrayList;
import java.util.List;

import br.com.yohandevmeia.rrsystem.models.EquipmentModel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// Used to create, read or update an equipment
public record EquipmentDTO(
		long id,
		
		@NotNull
		@Size(min = 3)
		String name,
		long roomId

		) {

	public EquipmentDTO(EquipmentModel equipment) {
		this(equipment.getId(), equipment.getName(), equipment.getRoom().getId());
	}
	
	public EquipmentModel convertDTOToObject() {
		EquipmentModel equipment = new EquipmentModel();
		
		if(id > 0) {
			equipment.setId(id);
		}
		
		equipment.setName(name);
		return equipment;
	}
	
	public static ArrayList<EquipmentDTO> convertAllEquipments(List<EquipmentModel> equipments) {
		ArrayList<EquipmentDTO> dtos = new ArrayList<>();
		for (EquipmentModel equipment : equipments) {
			EquipmentDTO dto = new EquipmentDTO(equipment);
			dtos.add(dto);
		}
		return dtos;
	}
}
