package br.com.yohandevmeia.rrsystem.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	
	@Column(name = "reservation_datetime", nullable = false)
	private LocalDateTime reservationDateTime;
	
	@Column(name = "status", nullable = false, length = 20)
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ManyToMany
	@JoinTable(
	    name = "report_reservation",
	    joinColumns = @JoinColumn(name = "reservation_id"),
	    inverseJoinColumns = @JoinColumn(name = "report_id")
	)
	private List<ReportModel> reports = new ArrayList<>();
	
	public ReservationModel() {
		
	}

	public ReservationModel(LocalDateTime reservationDateTime, Status status) {
		this.reservationDateTime = reservationDateTime;
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

	public LocalDateTime getReservationDateTime() {
		return reservationDateTime;
	}

	public void setReservationDateTime(LocalDateTime reservationDateTime) {
		this.reservationDateTime = reservationDateTime;
	}

	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public List<ReportModel> getReports() {
		return reports;
	}

	public void setReports(List<ReportModel> reports) {
		this.reports = reports;
	}
}