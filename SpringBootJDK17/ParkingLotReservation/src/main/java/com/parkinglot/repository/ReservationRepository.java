package com.parkinglot.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.parkinglot.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	List<Reservation> findByVehicleNumber(String vehicleNumber);

	List<Reservation> findByEndTimeAfter(LocalDateTime now);

	List<Reservation> findBySlot_SlotsId(Long slotsId);

	@Query("SELECT COUNT(r) > 0 FROM Reservation r " + "WHERE r.slot.slotsId = :slotId " + "AND r.startTime < :endTime "
			+ "AND r.endTime > :startTime")
	boolean existsOverlapForSlot(@Param("slotId") Long slotId, @Param("startTime") LocalDateTime startTime,
			@Param("endTime") LocalDateTime endTime);

	@Query("SELECT COUNT(r) > 0 FROM Reservation r " + "WHERE r.vehicleNumber = :vehicleNumber "
			+ "AND r.startTime < :endTime " + "AND r.endTime > :startTime")
	boolean existsOverlapForVehicle(@Param("vehicleNumber") String vehicleNumber,
			@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
}