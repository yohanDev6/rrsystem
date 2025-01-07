package br.com.yohandevmeia.rrsystem.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.yohandevmeia.rrsystem.models.ClientModel;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, Long>{
    Optional<ClientModel> findByEmail(String email);
    boolean existsByEmail(String email);
}
