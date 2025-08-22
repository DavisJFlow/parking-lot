package com.parkinglot.service;

import java.time.LocalDateTime; 
import java.util.List;

import com.parkinglot.model.Slot;

public interface SlotService {

	Slot saveSlot(Slot slot);

	Slot getSlotById(Long slotId);

	List<Slot> getAllSlots();

	List<Slot> getSlotsByFloorId(Long floorId);

	List<Slot> getActiveSlotsByType(String vehicleType);
	
	List<Slot> getAvailableSlots(LocalDateTime startTime, LocalDateTime endTime);

	void deleteSlot(Long slotId);

}
