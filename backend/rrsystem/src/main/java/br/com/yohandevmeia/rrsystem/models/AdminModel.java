package br.com.yohandevmeia.rrsystem.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "administrator")
public class AdminModel extends ClientModel{

    @Column(name = "admin_id", unique = true)
    private long adminId;

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }
}
