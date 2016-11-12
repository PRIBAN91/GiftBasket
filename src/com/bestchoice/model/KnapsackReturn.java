package com.bestchoice.model;

public class KnapsackReturn {

	private int picks[][];
	private int amountSpent;

	public KnapsackReturn(int[][] picks, int amountSpent) {
		super();
		this.picks = picks;
		this.amountSpent = amountSpent;
	}

	public int[][] getPicks() {
		return picks;
	}

	public int getAmountSpent() {
		return amountSpent;
	}

	@Override
	public String toString() {
		return "KnapsackReturn [ amountSpent=" + amountSpent + "]";
	}

}
