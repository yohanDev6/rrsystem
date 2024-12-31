package br.com.yohandevmeia.rrsystem.services;

import org.springframework.stereotype.Service;

import br.com.yohandevmeia.rrsystem.repositories.EquipmentRepository;

@Service
public class EquipmentService {

	private final EquipmentRepository equipmentRepository;
	
	public EquipmentService(EquipmentRepository equipmentRepository) {
		this.equipmentRepository = equipmentRepository;
	}
}
