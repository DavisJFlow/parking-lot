package com.parkinglot.service;

import java.time.LocalDateTime;
import java.util.List;

import com.parkinglot.model.Reservation;

public interface ReservationService {

	Reservation saveReservation(Reservation reservation);

	Reservation getReservationById(Long reservationId);

	List<Reservation> getAllReservations();

	List<Reservation> getReservationsByVehicleNumber(String vehicleNumber);

	List<Reservation> getActiveReservations(LocalDateTime now);

	List<Reservation> getReservationsBySlotId(Long slotId);

	void deleteReservation(Long reservationId);

	Reservation bookReservation(Long slotId, String vehicleNumber, LocalDateTime startTime, LocalDateTime endTime);
}