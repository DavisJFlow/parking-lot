package com.parkinglot.service;

import java.util.List;

import com.parkinglot.model.Floor;

public interface FloorService {

	Floor saveFloor(Floor floor);

	Floor getFloorById(Long floorId);

	List<Floor> getAllFloors();

	void deleteFloor(Long floorId);

}
