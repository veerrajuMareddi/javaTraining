// Motorcycle.java
package com.rgt.vehiclerental;

public class Motorcycle extends Vehicle {
    private boolean hasSideCar;

    public boolean isHasSideCar() {
		return hasSideCar;
	}

	public void setHasSideCar(boolean hasSideCar) {
		this.hasSideCar = hasSideCar;
	}

	public Motorcycle(String licensePlate, String make, String model, boolean hasSideCar) {
        super(licensePlate, make, model,50);
        this.hasSideCar = hasSideCar;
    }

}
