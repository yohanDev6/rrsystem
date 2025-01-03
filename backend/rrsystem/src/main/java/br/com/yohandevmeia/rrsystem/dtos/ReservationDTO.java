package br.com.yohandevmeia.rrsystem.dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.yohandevmeia.rrsystem.models.ReservationModel;
import br.com.yohandevmeia.rrsystem.models.Status;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ReservationDTO(
		long id,
		@NotNull(message = "Reservation date is required")
	    @FutureOrPresent(message = "Reservation date cannot be in the past")
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	    LocalDateTime reservationDateTime,

	    @NotNull(message = "Status is required")
	    Status status,

	    @NotNull(message = "Client ID is required")
	    @Positive(message = "Client ID must be a positive number")
	    long clientId,

	    @NotNull(message = "Room ID is required")
	    @Positive(message = "Room ID must be a positive number")
	    long roomId
		) {

	public ReservationDTO(ReservationModel reservation) {
		this(
				reservation.getId(),
				reservation.getReservationDateTime(),
				reservation.getStatus(),
				reservation.getClient().getId(),
				reservation.getRoom().getId());
	}
	
	public ReservationModel convertDTOToObject() {
		ReservationModel reservation = new ReservationModel();
		if (id > 0) {
			reservation.setId(id);
		}
		reservation.setReservationDateTime(reservationDateTime);
		reservation.setStatus(status);
		return reservation;
	}
	
	public static ArrayList<ReservationDTO> convertAllToDTO(List<ReservationModel> reservations) {
		ArrayList<ReservationDTO> dtos = new ArrayList<>();
		for (ReservationModel reservation : reservations) {
			ReservationDTO dto = new ReservationDTO(reservation);
			dtos.add(dto);
		}
		return dtos;
	}
}
