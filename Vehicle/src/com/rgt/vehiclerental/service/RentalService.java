package com.rgt.vehiclerental.service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.rgt.vehiclerental.Customer;
import com.rgt.vehiclerental.Rental;
import com.rgt.vehiclerental.Vehicle;
import com.rgt.vehiclerental.inter.RentalCostCalculator;

public class RentalService implements RentalCostCalculator {
	private List<Vehicle> vehicles = new ArrayList<>();
	private List<Rental> rentals = new ArrayList<>();
	private List<Customer> customers = new ArrayList<>();

	public void addVehicle(Vehicle vehicle) {
		vehicles.add(vehicle);
	}

	public void removeVehicle(Vehicle vehicle) {

		vehicles.remove(vehicle);
	}

	/**
	 * fetches available vehicles if availability is true
	 */
	public List<Vehicle> getAvailableVehicles() {
		List<Vehicle> availableVehicles = new ArrayList<>();
		for (Vehicle vehicle : vehicles) {
			if (vehicle.isAvailable()) {
				availableVehicles.add(vehicle);
			}
		}
		return availableVehicles;
	}

	/**
	 * fetches Vehicle through NumberPlate or VehicleId
	 */
	public Vehicle getVehicleByLicensePlate(String licensePlate) {
		for (Vehicle vehicle : vehicles) {
			if (vehicle.getLicensePlate().equals(licensePlate)
					|| vehicle.getVehicleId().equalsIgnoreCase(licensePlate)) {
				return vehicle;
			}
		}
		return null;
	}

	/**
	 * fetches Rental by rentalId
	 */
	public Rental getRentalById(String rentalId) {
		for (Rental rental : rentals) {
			if (rental.getId().equals(rentalId)) {
				return rental;
			}
		}
		return null;
	}

	public Rental rentVehicle(Customer customer, Vehicle vehicle, LocalDateTime startTime, LocalDateTime endTime) {
		if (vehicle.isAvailable()) {
			Rental rental = new Rental(vehicle, customer, startTime, endTime);
			if (!customers.contains(customer))
				customers.add(customer);
			rentals.add(rental);
			System.out.println("Generated rentalId :" + rental.getId());
			vehicle.setAvailable(false);
			return rental;
		} else {
			return null;
		}
	}

	/**
	 * Overridden from interface
	 * 
	 * calculates cost based on the price of vehicle and Number of Days which
	 * vehicle is rented
	 */
	@Override
	public BigDecimal calculateRentalCost(Rental rental) {
		Duration duration = Duration.between(rental.getStartTime(), rental.getEndTime());
		long days = duration.toDays();
		BigDecimal hourlyRate = BigDecimal.valueOf(rental.getRentedVehicle().getRentalPricePerday());
		return hourlyRate.multiply(BigDecimal.valueOf(days));
	}

	public boolean returnVehicle(Rental rental) {
		if (rentals.contains(rental)) {
			rentals.remove(rental);
			rental.getRentedVehicle().setAvailable(true);
			return true;
		} else {
			return false;
		}
	}

	public List<Rental> getAllRentals() {

		return rentals;

	}

	public Customer getCustomerByName(String name) {

		for (Customer customer : customers) {
			customer.getFirstName().equalsIgnoreCase(name);
			return customer;
		}
		return null;

	}

	public List<Customer> getAllCustomers() {
		return customers;
	}

	public Customer addCustomer(Customer customer) {
		if (!customers.contains(customer)) {
			customers.add(customer);
			return customer;
		}
		return null;
	}

}
