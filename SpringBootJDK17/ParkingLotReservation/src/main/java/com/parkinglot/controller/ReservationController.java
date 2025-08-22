package com.parkinglot.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parkinglot.dto.BookingRequest;
import com.parkinglot.model.Reservation;
import com.parkinglot.service.ReservationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;

	@PostMapping
	public Reservation addReservation(@RequestBody Reservation reservation) {
		return reservationService.saveReservation(reservation);
	}

	@GetMapping("/{id}")
	public Reservation getReservation(@PathVariable Long id) {
		return reservationService.getReservationById(id);
	}

	@GetMapping
	public List<Reservation> getAllReservations() {
		return reservationService.getAllReservations();
	}

	@GetMapping("/vehicle/{vehicleNumber}")
	public List<Reservation> getReservationsByVehicle(@PathVariable String vehicleNumber) {
		return reservationService.getReservationsByVehicleNumber(vehicleNumber);
	}

	@GetMapping("/active")
	public List<Reservation> getActiveReservations() {
		return reservationService.getActiveReservations(LocalDateTime.now());
	}

	@GetMapping("/slot/{slotId}")
	public List<Reservation> getReservationsBySlot(@PathVariable Long slotId) {
		return reservationService.getReservationsBySlotId(slotId);
	}

	@DeleteMapping("/{id}")
	public void deleteReservation(@PathVariable Long id) {
		reservationService.deleteReservation(id);
	}

	@PostMapping("/book")
	public Reservation bookJson(@RequestBody Map<String, String> req) {
		Long slotId = Long.valueOf(req.get("slotId"));
		String vehicleNumber = req.get("vehicleNumber");
		LocalDateTime startTime = LocalDateTime.parse(req.get("startTime")); 
		LocalDateTime endTime = LocalDateTime.parse(req.get("endTime"));

		return reservationService.bookReservation(slotId, vehicleNumber, startTime, endTime);

	}

	@PostMapping("/book-validated")
	public Reservation bookValidated(@Valid @RequestBody BookingRequest req) {
		return reservationService.bookReservation(req.getSlotId(), req.getVehicleNumber(), req.getStartTime(),
				req.getEndTime());
	}

}