package com.bestchoice.model;

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
