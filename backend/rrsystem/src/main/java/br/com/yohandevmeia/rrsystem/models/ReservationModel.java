package br.com.yohandevmeia.rrsystem.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservation")
public class ReservationModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	private ClientModel client;
	
	@ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
	private RoomModel room;
	
	@Column(name = "reservation_date", nullable = false)
	private LocalDate reservationDate;
	
	@Column(name = "reservation_time", nullable = false)
	private LocalDateTime reservationTime;
	
	@Column(name = "status", nullable = false, length = 20)
	private String status;
	
	@ManyToMany
	@JoinTable(
	    name = "report_reservation",
	    joinColumns = @JoinColumn(name = "reservation_id"),
	    inverseJoinColumns = @JoinColumn(name = "report_id")
	)
	private List<ReportModel> reports = new ArrayList<>();
	
	public ReservationModel() {
		
	}

	public ReservationModel(LocalDate reservationDate, LocalDateTime reservationTime, String status) {
		super();
		this.reservationDate = reservationDate;
		this.reservationTime = reservationTime;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ClientModel getClient() {
		return client;
	}

	public void setClient(ClientModel client) {
		this.client = client;
	}

	public RoomModel getRoom() {
		return room;
	}

	public void setRoom(RoomModel room) {
		this.room = room;
	}

	public LocalDate getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(LocalDate reservationDate) {
		this.reservationDate = reservationDate;
	}

	public LocalDateTime getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(LocalDateTime reservationTime) {
		this.reservationTime = reservationTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ReportModel> getReports() {
		return reports;
	}

	public void setReports(List<ReportModel> reports) {
		this.reports = reports;
	}
}