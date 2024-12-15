package br.com.yohandevmeia.rrsystem.dtos.admin;

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
        this(admin.getId(), admin.getAdminId(), admin.getEmail());
    }
}
