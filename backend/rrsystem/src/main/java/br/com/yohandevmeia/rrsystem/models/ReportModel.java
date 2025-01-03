package br.com.yohandevmeia.rrsystem.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "report")
public class ReportModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "generation_date", nullable = false)
	private LocalDateTime generationDate;
	
	@Column(name = "data", nullable = false)
	private String data;
	
	@ManyToOne
	@JoinColumn(name = "admin_id", nullable = false)
	private AdminModel admin;
	
	@ManyToMany
	@JoinTable(
	    name = "report_reservation",
	    joinColumns = @JoinColumn(name = "report_id"),
	    inverseJoinColumns = @JoinColumn(name = "reservation_id")
	)
	private List<ReservationModel> reservations = new ArrayList<>();
	
	public ReportModel() {
		
	}
	
	public ReportModel(LocalDateTime generationDate, String data) {
		this.generationDate = generationDate;
		this.data = data;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getGenerationDate() {
		return generationDate;
	}

	public void setGenerationDate(LocalDateTime generationDate) {
		this.generationDate = generationDate;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public AdminModel getAdmin() {
		return admin;
	}

	public void setAdmin(AdminModel admin) {
		this.admin = admin;
	}

	public List<ReservationModel> getReservations() {
		return reservations;
	}

	public void setReservations(List<ReservationModel> reservations) {
		this.reservations = reservations;
	}

	public void addReservation(ReservationModel reservation) {
		if (!reservations.contains(reservation)) {
			this.reservations.add(reservation);
			reservation.getReports().add(this);
		}
	}

	public void removeReservation(ReservationModel reservation) {
		if (reservations.contains(reservation)) {
			this.reservations.remove(reservation);
			reservation.getReports().remove(this);
		}
	}
}
