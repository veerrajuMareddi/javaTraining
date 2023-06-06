package com.rgt.vehiclerental;

import com.rgt.vehiclerental.inter.VehicleOperations;

public abstract class Vehicle implements VehicleOperations {

	private String licensePlate;
    private String make;
	private String model;
    private int rentalPricePerday;
    private boolean isAvailable;


    public Vehicle(String licensePlate, String make, String model ,int rentalPricePerday) {
        this.licensePlate = licensePlate;
        this.make = make;
        this.model = model;
        this.rentalPricePerday=rentalPricePerday;
        this.isAvailable = true;
    }
    public Vehicle(String make, String model, int price) {
    	this.licensePlate = "-";
        this.make = make;
        this.model = model;
        this.rentalPricePerday=price;
        this.isAvailable = true;
	}
	public int getRentalPricePerday() {
		return rentalPricePerday;
	}
	public void setRentalPricePerday(int rentalPricePerday) {
		this.rentalPricePerday = rentalPricePerday;
	}

    public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

    @Override
    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
