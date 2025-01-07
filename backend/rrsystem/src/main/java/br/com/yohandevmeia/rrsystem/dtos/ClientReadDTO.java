package br.com.yohandevmeia.rrsystem.dtos;

import java.util.ArrayList;
import java.util.List;

import br.com.yohandevmeia.rrsystem.models.ClientModel;

public record ClientReadDTO (
    long id,
    String name,
    String email,
    boolean isActive
) {
    
    public ClientReadDTO(ClientModel client) {
        this(client.getId(), client.getName(), client.getEmail(), client.isActive());
    }

    public static ArrayList<ClientReadDTO> convertAllClientsToDTO(List<ClientModel> clients) {
        ArrayList<ClientReadDTO> dtos = new ArrayList<>();
        for(ClientModel client : clients) {
            dtos.add(new ClientReadDTO(client));
        }
        return dtos;
    }
}
