package com.bestchoice.optimization;

import com.bestchoice.model.KnapsackReturn;

public class Knapsack {

	public KnapsackReturn knapSack(int W, int wt[], int val[], int n) {
		int i, w;
		int K[][] = new int[n + 1][W + 1];

		int picks[][] = new int[n + 1][W + 1], tmp;

		for (i = 1; i <= n; i++) {
			for (w = 1; w <= W; w++) {
				if (wt[i - 1] <= w) {
					tmp = val[i - 1] + K[i - 1][w - wt[i - 1]];
					K[i][w] = Math.max(tmp, K[i - 1][w]);
					if (tmp > K[i - 1][w])
						picks[i][w] = 1;
					else
						picks[i][w] = -1;
				} else {
					picks[i][w] = -1;
					K[i][w] = K[i - 1][w];
				}
			}
		}

		int amountTobeSpent = K[n][W];
		for (i = 0; i <= W; i++)
			if (K[n][i] == amountTobeSpent)
				break;

		KnapsackReturn kpReturn = new KnapsackReturn(picks, i);

		return kpReturn;
	}

}
