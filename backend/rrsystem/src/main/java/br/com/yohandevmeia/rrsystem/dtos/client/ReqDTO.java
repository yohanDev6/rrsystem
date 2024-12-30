package br.com.yohandevmeia.rrsystem.dtos.client;

import br.com.yohandevmeia.rrsystem.models.ClientModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ReqDTO(
	long id,
	
    @NotNull(message = "Name is required")
    @Size(max = 128, message = "Max name size is 128")
    String name,

    @Email(message = "Email must be valid")
    @NotNull(message = "Email is required")
    @Size(max = 128, message = "Max email size is 128")
    String email,

    @NotNull(message = "Password is required")
    @Size(min = 8, max = 128, message = "Password size must be > 8 and < 128 characters")
    String password,

    String phone
) {
    
    public ClientModel convertDTOToOClientModel() {
        ClientModel client = new ClientModel();
        if(id > 0) {
        	client.setId(id);
        }
        client.setName(name);
        client.setEmail(email);
        client.setPassword(password);
        client.setPhone(phone);
        client.setActive(false);
        return client;
    }
}
