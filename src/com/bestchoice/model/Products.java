package com.bestchoice.model;

import javax.persistence.*;

/**
 * @author PRITAM. Created for Hibernate ORM mapping with table.
 *
 */
@Entity
@Table(name = "product_list")
public class Products {

	@Id
	@Column(name = "SequenceNum")
	private int sequenceNum;
	@Column(name = "ProductName")
	private String productName;
	@Column(name = "SubProductName")
	private String subProductName;
	@Column(name = "Price")
	private double price;
	@Column(name = "ReviewValue")
	private double reviewValue;
	@Column(name = "ReviewTotal")
	private double reviewTotal;
	@Column(name = "ReviewCount")
	private long reviewCount;

	/**
	 * @return the sequenceNum
	 */
	public int getSequenceNum() {
		return sequenceNum;
	}

	/**
	 * @param sequenceNum
	 *            the sequenceNum to set
	 */
	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName
	 *            the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the subProductName
	 */
	public String getSubProductName() {
		return subProductName;
	}

	/**
	 * @param subProductName
	 *            the subProductName to set
	 */
	public void setSubProductName(String subProductName) {
		this.subProductName = subProductName;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the reviewValue
	 */
	public double getReviewValue() {
		return reviewValue;
	}

	/**
	 * @param reviewValue
	 *            the reviewValue to set
	 */
	public void setReviewValue(double reviewValue) {
		this.reviewValue = reviewValue;
	}

	/**
	 * @return the reviewTotal
	 */
	public double getReviewTotal() {
		return reviewTotal;
	}

	/**
	 * @param reviewTotal
	 *            the reviewTotal to set
	 */
	public void setReviewTotal(double reviewTotal) {
		this.reviewTotal = reviewTotal;
	}

	/**
	 * @return the reviewCount
	 */
	public long getReviewCount() {
		return reviewCount;
	}

	/**
	 * @param reviewCount
	 *            the reviewCount to set
	 */
	public void setReviewCount(long reviewCount) {
		this.reviewCount = reviewCount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + sequenceNum;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Products other = (Products) obj;
		if (sequenceNum != other.sequenceNum)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Products [sequenceNum=" + sequenceNum + ", productName=" + productName + ", subProductName="
				+ subProductName + ", price=" + price + ", reviewValue=" + reviewValue + ", reviewTotal=" + reviewTotal
				+ ", reviewCount=" + reviewCount + "]";
	}

}
