package br.com.yohandevmeia.rrsystem.dtos;

import java.util.ArrayList;
import java.util.List;

import br.com.yohandevmeia.rrsystem.models.AdminModel;

public record AdminDTO(
    long id,
    long userId
) {
    public AdminDTO(AdminModel admin) {
        this(admin.getId(), admin.getClient().getId());
    }
    
    public static ArrayList<AdminDTO> convertAllAdminModelToDTO(List<AdminModel> admins) {
    	ArrayList<AdminDTO> dtos = new ArrayList<>();
    	
    	for (AdminModel admin : admins) {
    		dtos.add(new AdminDTO(admin));
    	}
    	
    	return dtos;
    }
}
