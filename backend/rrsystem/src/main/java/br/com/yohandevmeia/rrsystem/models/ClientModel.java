package br.com.yohandevmeia.rrsystem.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "client")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Email
    @Column(name = "email", unique = true, nullable = false, length = 128)
    private String email;

    @Column(name = "password", nullable = false, length = 128)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "active", nullable = false)
    private boolean active;

    public ClientModel(String name, String email, String phone, Boolean active) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.active = active;
    }
}
