package br.com.yohandevmeia.rrsystem.dtos.client;

import java.util.ArrayList;
import java.util.List;

import br.com.yohandevmeia.rrsystem.models.ClientModel;

public record ResDTO (
    long id,
    String name,
    String email,
    String phone,
    boolean isActive
) {
    
    public ResDTO(ClientModel client) {
        this(client.getId(), client.getName(), client.getEmail(), client.getPhone(), client.isActive());
    }

    public static ArrayList<ResDTO> convertAllClientsToDTO(List<ClientModel> clients) {
        ArrayList<ResDTO> dtos = new ArrayList<>();
        for(ClientModel client : clients) {
            dtos.add(new ResDTO(client));
        }
        return dtos;
    }
}
