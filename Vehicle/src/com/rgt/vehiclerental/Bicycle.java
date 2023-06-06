// Bicycle.java
package com.rgt.vehiclerental;

public class Bicycle extends Vehicle {
	

	public Bicycle(String make, String model, String isGeared) {
		super(make,model, price(isGeared));
	}

	
private static int price(String value)
{
	return (value.equalsIgnoreCase("yes"))?20:10;
}
}
