package br.com.yohandevmeia.rrsystem.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.yohandevmeia.rrsystem.models.AdminModel;
import br.com.yohandevmeia.rrsystem.models.ClientModel;

@Repository
public interface AdminRepository extends JpaRepository<AdminModel, Long>{
    Optional<AdminModel> findByClient(ClientModel client);
}
