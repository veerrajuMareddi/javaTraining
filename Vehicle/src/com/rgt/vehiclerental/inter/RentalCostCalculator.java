// RentalCostCalculator.java
package com.rgt.vehiclerental.inter;

import java.math.BigDecimal;

import com.rgt.vehiclerental.Rental;

public interface RentalCostCalculator {
    BigDecimal calculateRentalCost(Rental rental);
}
