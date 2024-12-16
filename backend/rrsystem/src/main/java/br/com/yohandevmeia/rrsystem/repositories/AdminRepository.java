package br.com.yohandevmeia.rrsystem.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.yohandevmeia.rrsystem.models.AdminModel;
import br.com.yohandevmeia.rrsystem.models.ClientModel;

public interface AdminRepository extends JpaRepository<AdminModel, Long>{
    Optional<AdminModel> findByClient(ClientModel client);
}
