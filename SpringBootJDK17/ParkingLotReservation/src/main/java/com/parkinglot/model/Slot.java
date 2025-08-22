package com.parkinglot.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "slots")
public class Slot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "slots_id")
	private Long slotsId;

	@ManyToOne
	@JoinColumn(name = "floor_id")
	private Floor floor;

	@Column(name = "slot_number")
	private String slotNumber;

	@Column(name = "vehicle_type")
	private String vehicleType;

	@Enumerated(EnumType.STRING)
	@Column(name = "vehicle_status")
	private VehicleStatus vehicleStatus;

	@Column(name = "date")
	private LocalDateTime date;

	@Column(name = "time")
	private LocalDateTime time;

	public enum VehicleStatus {
		ACTIVE, INACTIVE
	}

	public Long getSlotsId() {
		return slotsId;
	}

	public void setSlotsId(Long slotsId) {
		this.slotsId = slotsId;
	}

	public Floor getFloor() {
		return floor;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	public String getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(String slotNumber) {
		this.slotNumber = slotNumber;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public VehicleStatus getVehicleStatus() {
		return vehicleStatus;
	}

	public void setVehicleStatus(VehicleStatus vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}
}