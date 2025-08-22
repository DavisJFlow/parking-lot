package com.parkinglot.service;

import java.time.LocalDateTime;
import java.util.List; 
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkinglot.model.Slot;
import com.parkinglot.model.Slot.VehicleStatus;
import com.parkinglot.repository.SlotRepository;

@Service
public class SlotServiceImpl implements SlotService {

    @Autowired
    private SlotRepository slotRepository;

    @Override
    public Slot saveSlot(Slot slot) {
        return slotRepository.save(slot);
    }

    @Override
    public Slot getSlotById(Long slotId) {
        Optional<Slot> slot = slotRepository.findById(slotId);
        return slot.orElse(null); 
    }

    @Override
    public List<Slot> getAllSlots() {
        return slotRepository.findAll();
    }

    @Override
    public List<Slot> getSlotsByFloorId(Long floorId) {
        return slotRepository.findByFloor_FloorId(floorId);
    }

    @Override
    public List<Slot> getActiveSlotsByType(String vehicleType) {
        return slotRepository.findByVehicleTypeAndVehicleStatus(vehicleType, VehicleStatus.ACTIVE);
    }

    @Override
    public void deleteSlot(Long slotId) {
        slotRepository.deleteById(slotId);
    }

	@Override
	public List<Slot> getAvailableSlots(LocalDateTime startTime, LocalDateTime endTime) {
		 return slotRepository.findAvailable(startTime, endTime);
	}
}