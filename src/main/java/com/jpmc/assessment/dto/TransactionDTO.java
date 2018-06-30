package com.jpmc.assessment.dto;

import java.util.Date;

/**
 * @author Reena
 * 
 * This bean will hold all information about a transaction
 */
public class TransactionDTO {

	private String entity;
	private char transactionType;
	private float agreedFx;
	private Currency currency;
	private Date instructionDate;
	private Date settlementDate;
	private int units;
	private double pricePerUnit;

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public char getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(char transactionType) {
		this.transactionType = transactionType;
	}

	public float getAgreedFx() {
		return agreedFx;
	}

	public void setAgreedFx(float agreedFx) {
		this.agreedFx = agreedFx;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Date getInstructionDate() {
		return instructionDate;
	}

	public void setInstructionDate(Date instructionDate) {
		this.instructionDate = instructionDate;
	}

	public Date getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	@Override
	public String toString() {
		return "TransactionDTO [entity=" + entity + ", transactionType=" + transactionType + ", agreedFx=" + agreedFx
				+ ", currency=" + currency + ", instructionDate=" + instructionDate + ", settlementDate="
				+ settlementDate + ", units=" + units + ", pricePerUnit=" + pricePerUnit + "]";
	}

}