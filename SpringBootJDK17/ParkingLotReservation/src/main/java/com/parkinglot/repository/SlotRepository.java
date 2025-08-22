package com.parkinglot.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.parkinglot.model.Slot;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {

	List<Slot> findByFloor_FloorId(Long floorId);

	List<Slot> findByVehicleTypeAndVehicleStatus(String vehicleType, Slot.VehicleStatus status);

	@Query("""
			    select s from Slot s
			    where s.vehicleStatus = com.parkinglot.model.Slot.VehicleStatus.ACTIVE
			      and not exists (
			        select r from Reservation r
			        where r.slot = s
			          and r.startTime < :endTime
			          and r.endTime   > :startTime
			      )
			""")
	List<Slot> findAvailable(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
}