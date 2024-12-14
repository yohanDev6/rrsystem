package br.com.yohandevmeia.rrsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.yohandevmeia.rrsystem.models.ClientModel;

public interface ClientRepository extends JpaRepository<ClientModel, Long>{
    
}
