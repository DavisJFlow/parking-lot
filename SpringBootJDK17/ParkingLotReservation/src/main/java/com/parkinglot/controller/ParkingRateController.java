package com.parkinglot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.parkinglot.model.ParkingRate;
import com.parkinglot.service.ParkingRateService;

@RestController
@RequestMapping("/api/rates")
public class ParkingRateController {

    @Autowired
    private ParkingRateService parkingRateService;

    @PostMapping
    public ParkingRate addRate(@RequestBody ParkingRate parkingRate) {
        return parkingRateService.saveParkingRate(parkingRate);
    }

    @GetMapping("/{vehicleType}")
    public ParkingRate getRateByType(@PathVariable String vehicleType) {
        return parkingRateService.getParkingRateByVehicleType(vehicleType);
    }

    @GetMapping
    public List<ParkingRate> getAllRates() {
        return parkingRateService.getAllParkingRates();
    }

    @DeleteMapping("/{vehicleType}")
    public void deleteRate(@PathVariable String vehicleType) {
        parkingRateService.deleteParkingRate(vehicleType);
    }
}