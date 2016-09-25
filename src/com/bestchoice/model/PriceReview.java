package com.bestchoice.model;

public class PriceReview {

	private double amount;
	private double review;
	private String prodName;

	public PriceReview(double amount, double review, String prodName) {
		super();
		this.amount = amount;
		this.review = review;
		this.prodName = prodName;
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

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

}
