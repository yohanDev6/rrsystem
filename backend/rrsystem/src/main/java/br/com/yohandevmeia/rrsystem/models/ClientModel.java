package br.com.yohandevmeia.rrsystem.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "client")
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

    @Column(name = "active", nullable = false)
    private boolean active;
    
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservationModel> reservations = new ArrayList<>();
    
    public ClientModel() {
    	
    }

    public ClientModel(String name, String email, Boolean active) {
        this.name = name;
        this.email = email;
        this.active = active;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<ReservationModel> getReservations() {
		return reservations;
	}

	public void setReservations(List<ReservationModel> reservations) {
		this.reservations = reservations;
	}
}
