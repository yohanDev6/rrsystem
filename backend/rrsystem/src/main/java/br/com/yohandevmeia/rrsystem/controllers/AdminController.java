package br.com.yohandevmeia.rrsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.yohandevmeia.rrsystem.dtos.admin.AdminDTO;
import br.com.yohandevmeia.rrsystem.services.AdminService;

@RestController
@RequestMapping("admins")
public class AdminController {
    
    @Autowired
    private AdminService adminService;

    @PostMapping
    public ResponseEntity<String> createAdmin(@RequestBody AdminDTO dto) {
        adminService.create(dto.userEmail());
        return new ResponseEntity<>("Admin created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable Long id) {
        return new ResponseEntity<>(new AdminDTO(adminService.getAdminById(id)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllAdmins() {
        return new ResponseEntity<>(AdminDTO.convertAllAdminModelToDTO(adminService.getAllAdmins()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Long id) {
        adminService.delete(id);
        return new ResponseEntity<>("Admin deleted successfully", HttpStatus.OK);
    }
}
