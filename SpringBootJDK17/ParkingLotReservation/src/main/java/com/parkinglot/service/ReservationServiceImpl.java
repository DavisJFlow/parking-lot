package com.parkinglot.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkinglot.model.Reservation;
import com.parkinglot.model.Slot;
import com.parkinglot.model.ParkingRate;
import com.parkinglot.repository.ReservationRepository;
import com.parkinglot.repository.SlotRepository;
import com.parkinglot.repository.ParkingRateRepository;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private SlotRepository slotRepository;

	@Autowired
	private ParkingRateRepository parkingRateRepository;

	@Override
	public Reservation saveReservation(Reservation reservation) {
		return reservationRepository.save(reservation);
	}

	@Override
	public Reservation getReservationById(Long reservationId) {
		Optional<Reservation> reservation = reservationRepository.findById(reservationId);
		return reservation.orElse(null);
	}

	@Override
	public List<Reservation> getAllReservations() {
		return reservationRepository.findAll();
	}

	@Override
	public List<Reservation> getReservationsByVehicleNumber(String vehicleNumber) {
		return reservationRepository.findByVehicleNumber(vehicleNumber);
	}

	@Override
	public List<Reservation> getActiveReservations(LocalDateTime now) {
		return reservationRepository.findByEndTimeAfter(now);
	}

	@Override
	public List<Reservation> getReservationsBySlotId(Long slotId) {
		return reservationRepository.findBySlot_SlotsId(slotId);
	}

	@Override
	public void deleteReservation(Long reservationId) {
		reservationRepository.deleteById(reservationId);
	}

	public Reservation bookReservation(Long slotId, String vehicleNumber, LocalDateTime startTime,
			LocalDateTime endTime) {
		if (startTime.isAfter(endTime) || startTime.isEqual(endTime)) {
			throw new RuntimeException("Start time must be before end time");
		}

		long hours = java.time.Duration.between(startTime, endTime).toHours();
		long minutes = java.time.Duration.between(startTime, endTime).toMinutesPart();
		if (hours >= 24) {
			throw new RuntimeException("Reservation duration cannot exceed 24 hours");
		}
		if (minutes > 0) {
			hours += 1;
		}

		if (!vehicleNumber.matches("^[A-Z]{2}\\d{2}[A-Z]{2}\\d{4}$")) {
			throw new RuntimeException("Vehicle number must match format XX00XX0000 (e.g., KA05MH1234)");
		}

		Slot slot = slotRepository.findById(slotId).orElseThrow(() -> new RuntimeException("Slot not found"));
		if (slot.getVehicleStatus() == Slot.VehicleStatus.INACTIVE) {
			throw new RuntimeException("Slot is not available");
		}

		boolean slotOverlap = reservationRepository.existsOverlapForSlot(slotId, startTime, endTime);
		if (slotOverlap) {
			throw new RuntimeException("Slot already reserved for this time");
		}

		boolean vehicleOverlap = reservationRepository.existsOverlapForVehicle(vehicleNumber, startTime, endTime);
		if (vehicleOverlap) {
			throw new RuntimeException("Vehicle already has an active reservation");
		}

		ParkingRate rate = parkingRateRepository.findByVehicleTypeIgnoreCase(slot.getVehicleType())
				.orElseThrow(() -> new RuntimeException("Rate not defined for this vehicle type"));
		BigDecimal amount = rate.getHourlyRate().multiply(BigDecimal.valueOf(hours));

		Reservation reservation = new Reservation();
		reservation.setSlot(slot);
		reservation.setVehicleNumber(vehicleNumber);
		reservation.setStartTime(startTime);
		reservation.setEndTime(endTime);
		reservation.setTotalHours(String.valueOf(hours));
		reservation.setAmount(amount);

		Reservation saved = reservationRepository.save(reservation);

		slot.setVehicleStatus(Slot.VehicleStatus.INACTIVE);
		slotRepository.save(slot);

		return saved;
	}
}