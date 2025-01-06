package br.com.yohandevmeia.rrsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.yohandevmeia.rrsystem.models.ClientModel;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, Long>{
    UserDetails findByEmail(String email);
    boolean existsByEmail(String email);
}
