package br.com.yohandevmeia.rrsystem.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.yohandevmeia.rrsystem.models.ClientModel;

public interface ClientRepository extends JpaRepository<ClientModel, Long>{
    Optional<ClientModel> findByEmail(String email);
    boolean existsByEmail(String email);
}
