package com.bestchoice.model;

public class PriceReview {

	private double amount;
	private double review;

	public PriceReview(double amount, double review) {
		super();
		this.amount = amount;
		this.review = review;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @return the review
	 */
	public double getReview() {
		return review;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @param review
	 *            the review to set
	 */
	public void setReview(double review) {
		this.review = review;
	}

}
