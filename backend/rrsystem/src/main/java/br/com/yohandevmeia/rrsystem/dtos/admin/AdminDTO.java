package br.com.yohandevmeia.rrsystem.dtos.admin;

import java.util.ArrayList;
import java.util.List;

import br.com.yohandevmeia.rrsystem.models.AdminModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record AdminDTO(
    long id,
    long adminId,
    @Email(message = "Invalid email")
    @NotNull(message = "Email is required")
    String userEmail
) {
    public AdminDTO(AdminModel admin) {
        this(admin.getId(), admin.getAdminId(), admin.getClient().getEmail());
    }
    
    public static ArrayList<AdminDTO> convertAllAdminModelToDTO(List<AdminModel> admins) {
    	ArrayList<AdminDTO> dtos = new ArrayList<>();
    	
    	for (AdminModel admin : admins) {
    		dtos.add(new AdminDTO(admin));
    	}
    	
    	return dtos;
    }
}
