Parking Lot Reservation System ---- 
A Spring Boot application for managing a parking lot with floors, slots, reservations, and parking rates.

Floor Management

 Easily add new floors, view existing ones, or remove floors when needed.

Slot Management

 Create parking slots for different types of vehicles and keep track of whether each slot is available (ACTIVE) or already taken (INACTIVE).


Reservations

 Allow vehicles to book parking slots while following important rules:


The start time must always come before the end time.


A reservation can last for a maximum of 24 hours.


Vehicle numbers must follow the format XX00XX0000 (for example, KA05MH1234).


If the time is not a whole hour, it is rounded up (e.g., 1.2 hours will be charged as 2 hours).

 The system also prevents two reservations from overlapping on the same slot or for the same vehicle.


Parking Rates

 Define hourly rates for each vehicle type, and the system will automatically calculate the total cost of a reservation.

 Tech Stack

- Java 17+
- Spring Boot 
- Spring Data JPA
- MySQL
- Hibernate
- Maven




