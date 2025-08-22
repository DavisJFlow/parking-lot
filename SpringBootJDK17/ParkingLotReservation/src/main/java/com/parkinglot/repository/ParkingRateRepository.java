package com.parkinglot.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.parkinglot.model.ParkingRate;

@Repository
public interface ParkingRateRepository extends JpaRepository<ParkingRate, String> {

	Optional<ParkingRate> findByVehicleTypeIgnoreCase(String vehicleType);
}