package com.bestchoice.model;

/*
 * Model Class for returning MCKP calculated values
 */

public class MckpReturn {

	private String picks[][];
	private int amountSpent;

	public MckpReturn(String[][] picks, int amountSpent) {
		super();
		this.picks = picks;
		this.amountSpent = amountSpent;
	}

	public String[][] getPicks() {
		return picks;
	}

	public int getAmountSpent() {
		return amountSpent;
	}

	@Override
	public String toString() {
		return "MckpReturn [ amountSpent=" + amountSpent + "]";
	}

}
