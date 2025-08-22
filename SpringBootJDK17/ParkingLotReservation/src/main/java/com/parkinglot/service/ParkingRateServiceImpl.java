package com.parkinglot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkinglot.model.ParkingRate;
import com.parkinglot.repository.ParkingRateRepository;

@Service
public class ParkingRateServiceImpl implements ParkingRateService {

	@Autowired
	private ParkingRateRepository parkingRateRepository;

	@Override
	public ParkingRate saveParkingRate(ParkingRate parkingRate) {
		return parkingRateRepository.save(parkingRate);
	}

	@Override
	public ParkingRate getParkingRateByVehicleType(String vehicleType) {
		Optional<ParkingRate> rate = parkingRateRepository.findById(vehicleType);
		return rate.orElse(null);
	}

	@Override
	public List<ParkingRate> getAllParkingRates() {
		return parkingRateRepository.findAll();
	}

	@Override
	public void deleteParkingRate(String vehicleType) {
		parkingRateRepository.deleteById(vehicleType);
	}

}
