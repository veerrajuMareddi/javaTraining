package com.rgt.vehiclerental;

public class Bicycle extends Vehicle {

	public Bicycle(String make, String model, String isGeared) {
		super(make, model, price(isGeared));
	}

	/**
	 * specifies the price according to its model
	 */
	private static int price(String value) {
		return (value.equalsIgnoreCase("yes")) ? 20 : 10;
	}
}
