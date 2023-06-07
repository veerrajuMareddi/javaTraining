// Car.java
package com.rgt.vehiclerental;

public class Car extends Vehicle {
    private int numberOfSeats;
    private boolean openRoof;

    public Car(String licensePlate, String make, String model, int numberOfSeats) {
        super(licensePlate, make, model,price(numberOfSeats));
        this.openRoof=true;
        this.numberOfSeats = numberOfSeats;
    }
    /**
	 * specifies the price according to its model
	 */
	private static int price(int totalSeats) {
		
		return totalSeats>4?100:70;
	}

}
