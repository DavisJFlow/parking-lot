package com.parkinglot.service;

import java.util.List;

import com.parkinglot.model.ParkingRate;

public interface ParkingRateService {

	ParkingRate saveParkingRate(ParkingRate parkingRate);

	ParkingRate getParkingRateByVehicleType(String vehicleType);

	List<ParkingRate> getAllParkingRates();

	void deleteParkingRate(String vehicleType);

}
