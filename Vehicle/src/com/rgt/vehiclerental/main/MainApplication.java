package com.rgt.vehiclerental.main;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.rgt.vehiclerental.Bicycle;
import com.rgt.vehiclerental.Car;
import com.rgt.vehiclerental.Customer;
import com.rgt.vehiclerental.Motorcycle;
import com.rgt.vehiclerental.Rental;
import com.rgt.vehiclerental.Vehicle;
import com.rgt.vehiclerental.service.RentalService;

public class MainApplication {
	public static RentalService rentalService = new RentalService();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean exit = false;

		while (!exit) {
			System.out.println("Vehicle Rental System");
			System.out.println("1. Add Vehicle");
			System.out.println("2. List Available Vehicles");
			System.out.println("3. Rent a Vehicle");
			System.out.println("4. Calculate Rental Cost");
			System.out.println("5. Return Vehicle");
			System.out.println("6. Exit");

			System.out.print("Enter your choice: ");

			int choice = 0;
			boolean input = false;
			while (!input) {
				try {
					choice = Integer.parseInt(scanner.next());
					input = true;
				} catch (NumberFormatException e) {
					System.out.println("Please Enter valid Input :");
				}
			}

			switch (choice) {
			case 1:
				addVehicle(scanner);
				break;
			case 2:
				listAvailableVehicles();
				break;
			case 3:
				rentVehicle(scanner);
				break;
			case 4:
				calculateRentalCost(scanner);
				break;
			case 5:
				returnVehicle(scanner);
				break;
			case 6:
				exit = true;
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}

			System.out.println();
		}
	}

	private static void addVehicle(Scanner scanner) {
		System.out.println("Enter the type of vehicle : ");
		System.out.println("1: Car");
		System.out.println("2 :MotorBike");
		System.out.println("3 :Bicycle");
		int type = scanner.nextInt();

		Vehicle vehicle;
		switch (type) {
		case 1:
			System.out.print("Enter the make: ");
			String make = scanner.next();
			System.out.print("Enter the model: ");
			String model = scanner.next();
			System.out.print("Enter the license plate: ");
			String licensePlate = scanner.next();
			System.out.print("Enter the number of seats: ");
			int seats = scanner.nextInt();
			vehicle = new Car(licensePlate, make, model, seats);
			break;
		case 2:
			System.out.print("Enter the make: ");
			make = scanner.next();
			System.out.print("Enter the model: ");
			model = scanner.next();
			System.out.print("Enter the license plate: ");
			licensePlate = scanner.next();
			System.out.print("Is it electric? (true/false): ");
			boolean isElectric = Boolean.parseBoolean(scanner.next());
			vehicle = new Motorcycle(licensePlate, make, model, isElectric);
			break;
		case 3:
			System.out.print("Enter the make: ");
			make = scanner.next();
			System.out.print("Enter the model: ");
			model = scanner.next();
			System.out.print("Is it Geared? (yes/no): ");
			String isGeared = scanner.next();
			vehicle = new Bicycle(make, model, isGeared);
			break;
		default:
			System.out.println("Invalid vehicle type. Vehicle not added.");
			return;
		}

		rentalService.addVehicle(vehicle);
		System.out.println("Vehicle added successfully.");
	}

	private static void listAvailableVehicles() {
		List<Vehicle> availableVehicles = rentalService.getAvailableVehicles();
		if (availableVehicles.isEmpty()) {
			System.out.println("No vehicles available for rent.");
		} else {
			System.out.println("Available Vehicles:");
			for (Vehicle vehicle : availableVehicles) {
				System.out.println("License Plate: " + vehicle.getLicensePlate());
				System.out.println("Make: " + vehicle.getMake());
				System.out.println("Model: " + vehicle.getModel());
				System.out.println();
			}
		}
	}

	private static void rentVehicle(Scanner scanner) {
		System.out.print("Enter your first name: ");
		String firstName = scanner.next();

		System.out.print("Enter your last name: ");
		String lastName = scanner.next();

		System.out.print("Enter your ID: ");
		String id = scanner.next();

		System.out.print("Enter the license plate of the vehicle you want to rent: ");
		String licensePlate = scanner.next();

		System.out.print("Enter the start date and time (yyyy-MM-dd HH:mm): ");
		String startDateTimeStr = scanner.next();
		startDateTimeStr=startDateTimeStr+" "+ "00:00:00";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startTime = LocalDateTime.parse(startDateTimeStr, formatter);

		System.out.print("Enter the end date and time (yyyy-MM-dd HH:mm): ");
		String endDateTimeStr = scanner.next();
		endDateTimeStr=endDateTimeStr+" "+ "00:00:00";
        LocalDateTime endTime = LocalDateTime.parse(endDateTimeStr, formatter);

		Customer customer = new Customer(firstName, lastName, id);
		Vehicle selectedVehicle = rentalService.getVehicleByLicensePlate(licensePlate);

		if (selectedVehicle != null) {
			Rental rental = rentalService.rentVehicle(customer, selectedVehicle, startTime, endTime);

			if (rental != null) {
				System.out.println("Rental successful!");
			} else {
				System.out.println("The selected vehicle is not available for rent.");
			}
		} else {
			System.out.println("Invalid license plate. Please try again.");
		}
	}

	private static void calculateRentalCost(Scanner scanner) {
		System.out.print("Enter the rental ID: ");
		String rentalId = scanner.next();

		Rental rental = rentalService.getRentalById(rentalId);
		if (rental != null) {
			BigDecimal rentalCost = rentalService.calculateRentalCost(rental);
			System.out.println("RgtRental amount: ₹" + rentalCost+"/-");
		} else {
			System.out.println("Invalid rental ID.");
		}
	}

	private static void returnVehicle(Scanner scanner) {
		System.out.print("Enter the rental ID: ");
		String rentalId = scanner.next();

		Rental rental = rentalService.getRentalById(rentalId);
		if (rental != null) {
			boolean success = rentalService.returnVehicle(rental);
			if (success) {
				System.out.println("Vehicle returned successfully.");
			} else {
				System.out.println("Failed to return the vehicle.");
			}
		} else {
			System.out.println("Invalid rental ID.");
		}
	}
}
