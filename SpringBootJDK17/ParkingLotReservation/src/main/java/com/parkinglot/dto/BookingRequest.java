package com.parkinglot.dto;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

public class BookingRequest {

    @NotNull(message = "slotId is required")
    private Long slotId;

    @NotBlank(message = "vehicleNumber is required")
    @Pattern(
        regexp = "^[A-Z]{2}\\d{2}[A-Z]{2}\\d{4}$",
        message = "vehicleNumber must match format XX00XX0000 (e.g., KA05MH1234)"
    )
    private String vehicleNumber;

    @NotNull(message = "startTime is required")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startTime;

    @NotNull(message = "endTime is required")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endTime;

    public Long getSlotId() { return slotId; }
    public void setSlotId(Long slotId) { this.slotId = slotId; }

    public String getVehicleNumber() { return vehicleNumber; }
    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
}