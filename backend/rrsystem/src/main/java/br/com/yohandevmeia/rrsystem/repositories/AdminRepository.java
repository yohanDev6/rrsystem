package br.com.yohandevmeia.rrsystem.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.yohandevmeia.rrsystem.models.AdminModel;

public interface AdminRepository extends JpaRepository<AdminModel, Long>{
    
}
