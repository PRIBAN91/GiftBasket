package com.bestchoice.model;

public class PriceReview {

	double amount;
	double review;

	public PriceReview(double amount, double review) {
		super();
		this.amount = amount;
		this.review = review;
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
