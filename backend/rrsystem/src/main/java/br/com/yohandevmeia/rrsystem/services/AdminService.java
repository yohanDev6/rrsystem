package br.com.yohandevmeia.rrsystem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.yohandevmeia.rrsystem.models.AdminModel;
import br.com.yohandevmeia.rrsystem.models.ClientModel;
import br.com.yohandevmeia.rrsystem.repositories.AdminRepository;
import br.com.yohandevmeia.rrsystem.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService extends GlobalValidationService{
    
    private final AdminRepository adminRepository;
    private final ClientRepository clientRepository;

    public AdminService(AdminRepository adminRepository, ClientRepository clientRepository) {
        this.adminRepository = adminRepository;
        this.clientRepository = clientRepository;
    }

    @Transactional
    public void create(long userId) {
    	verifyId(userId);
        Optional<ClientModel> optionalClient = clientRepository.findById(userId);
        verifyObject(optionalClient.get(), "Client with ID: " + userId + " not found");

        ClientModel client = optionalClient.get();

        if (!client.isActive()) {
            throw new IllegalArgumentException("This client is not valid to become an admin");
        }

        Optional<AdminModel> existingAdmin = adminRepository.findByClient(client);
        if (existingAdmin.isPresent()) {
            throw new IllegalArgumentException("This client is already an admin");
        }

        AdminModel admin = new AdminModel();
        admin.setClient(client);
        adminRepository.save(admin);
    }

    @Transactional(readOnly = true)
    public AdminModel getAdminById(Long id) {
    	verifyId(id);
        return adminRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Admin not found with id " + id));
    }

    @Transactional(readOnly = true)
    public List<AdminModel> getAllAdmins() {
        List<AdminModel> admins = adminRepository.findAll();

        if (admins.isEmpty()) {
            throw new EntityNotFoundException("Admins not found");
        }

        return adminRepository.findAll();
    }

    @Transactional
    public void delete(Long id) {
    	verifyId(id);
        AdminModel existingAdmin = adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Admin not found with id: " + id));
        adminRepository.delete(existingAdmin);
    }
}
