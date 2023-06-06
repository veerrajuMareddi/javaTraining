package com.rgt.vehiclerental;
import java.time.LocalDateTime;
import java.util.UUID;

public class Rental {
    private String id;
    private Vehicle rentedVehicle;
    private Customer customer;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    int i=1234;
    public Rental(Vehicle rentedVehicle, Customer customer, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = "RGT_Rental"+(i++);
        this.rentedVehicle = rentedVehicle;
        this.customer = customer;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getId() {
        return id;
    }

    public Vehicle getRentedVehicle() {
        return rentedVehicle;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
