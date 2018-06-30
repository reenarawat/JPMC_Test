package com.jpmc.assessment.dto;

/**
 * @author Reena
 * 
 * This will contain all the currency that the application will deal
 */
public enum Currency {

	SGP("SGP"), AED("AED"), SAR("SAR");

	private final String val;

	private Currency(String val) {
		this.val = val;
	}

	@Override
	public String toString() {
		return val;
	}

}
