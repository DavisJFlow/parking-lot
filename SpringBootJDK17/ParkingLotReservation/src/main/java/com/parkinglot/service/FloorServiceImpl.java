package com.parkinglot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkinglot.model.Floor;
import com.parkinglot.repository.FloorRepository;

@Service
public class FloorServiceImpl implements FloorService {

	@Autowired
	private FloorRepository floorRepository;

	@Override
	public Floor saveFloor(Floor floor) {
		return floorRepository.save(floor);

	}

	@Override
	public Floor getFloorById(Long floorId) {
		Optional<Floor> floor = floorRepository.findById(floorId);
		return floor.orElse(null);
	}

	@Override
	public List<Floor> getAllFloors() {
		return floorRepository.findAll();
	}

	@Override
	public void deleteFloor(Long floorId) {

	}

}
