package com.rgt.vehiclerental;

import com.rgt.vehiclerental.inter.VehicleOperations;

public abstract class Vehicle implements VehicleOperations {
	private String vehicleId;
	private String licensePlate;
	private String make;
	private String model;
	private int rentalPricePerday;
	private boolean isAvailable;
	/**
	 * for Generating vehicleId Dynamically
	 */
	static int count = 1000;

	public Vehicle(String licensePlate, String make, String model, int rentalPricePerday) {
		this.vehicleId = "RGT" + count++;
		this.licensePlate = licensePlate;
		this.make = make;
		this.model = model;
		this.rentalPricePerday = rentalPricePerday;
		this.isAvailable = true;
	}

	/**
	 * Constructor for vehicles not having NumberPlate
	 */
	public Vehicle(String make, String model, int price) {
		this.vehicleId = "RGT" + count++;
		this.licensePlate = "NULL";
		this.make = make;
		this.model = model;
		this.rentalPricePerday = price;
		this.isAvailable = true;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
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
