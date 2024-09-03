package com.example.bookfourth;

public class Payment {
   
	private String ID;
	private double Amount;
	public Payment(String iD, double amount) {
		super();
		ID = iD;
		Amount = amount;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public double getAmount() {
		return Amount;
	}
	public void setAmount(double amount) {
		Amount = amount;
	}
	@Override
	public String toString() {
		return "Payment [ID=" + ID + ", Amount=" + Amount + "]";
	}
	 
	
}
