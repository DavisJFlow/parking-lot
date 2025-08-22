package com.parkinglot.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parkinglot.model.Slot;
import com.parkinglot.service.SlotService;

@RestController
@RequestMapping("/api/slots")
public class SlotController {

	@Autowired
	private SlotService slotService;

	@PostMapping
	public Slot addSlot(@RequestBody Slot slot) {
		return slotService.saveSlot(slot);
	}

	@GetMapping("/{id}")
	public Slot getSlot(@PathVariable Long id) {
		return slotService.getSlotById(id);
	}

	@GetMapping
	public List<Slot> getAllSlots() {
		return slotService.getAllSlots();
	}

	@GetMapping("/floor/{floorId}")
	public List<Slot> getSlotsByFloor(@PathVariable Long floorId) {
		return slotService.getSlotsByFloorId(floorId);
	}

	@GetMapping("/active/{vehicleType}")
	public List<Slot> getActiveSlotsByType(@PathVariable String vehicleType) {
		return slotService.getActiveSlotsByType(vehicleType);
	}

	@DeleteMapping("/{id}")
	public void deleteSlot(@PathVariable Long id) {
		slotService.deleteSlot(id);
	}

	@GetMapping("/availability")
	public List<Slot> getAvailability(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {

		if (!startTime.isBefore(endTime)) {
			throw new RuntimeException("startTime must be before endTime");
		}
		return slotService.getAvailableSlots(startTime, endTime);
	}
}