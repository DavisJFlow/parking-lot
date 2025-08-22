package com.parkinglot.controller;

import java.util.List;   

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.parkinglot.model.Floor;
import com.parkinglot.service.FloorService;

@RestController
@RequestMapping("/api/floors")
public class FloorController {

    @Autowired
    private FloorService floorService;

    @PostMapping
    public Floor addFloor(@RequestBody Floor floor) {
    	System.out.println("Received floor: " + floor.getFloorName());
        return floorService.saveFloor(floor);
    }

    @GetMapping("/{id}")
    public Floor getFloor(@PathVariable Long id) {
        return floorService.getFloorById(id);
    }

    @GetMapping
    public List<Floor> getAllFloors() {
        return floorService.getAllFloors();
    }

    @DeleteMapping("/{id}")
    public void deleteFloor(@PathVariable Long id) {
        floorService.deleteFloor(id);
    }
}