package com.bestchoice.optimization;

import java.util.Arrays;
import com.bestchoice.model.MckpReturn;

public class MckpExactlyOne {

	public MckpReturn getMckpValues(int W, int wt1[][], int val1[][], int n, int m[]) {
		int i, w;
		int K[][] = new int[n + 1][W + 1], pos, maxi, temp, mini = Integer.MAX_VALUE, j = 1, maxPos;
		String picks[][] = new String[n + 1][W + 1];

		for (i = 1; i <= n; i++) {
			// Picking up data from val[][] and wt[][]
			int val[] = Arrays.copyOf(val1[i - 1], m[i - 1]);
			int wt[] = Arrays.copyOf(wt1[i - 1], m[i - 1]);
			for (w = j; w <= W; w++) {
				temp = 0;
				pos = 0;
				maxi = 0;
				maxPos = 0;
				// For every element of m[i-1] category
				while (pos < m[i - 1]) {
					// If it doesn't go inside this block ever, it means we
					// won't be able to select anything from this particular
					// bag, for present weight w
					if (wt[pos] <= w) {
						// Check for other than 1st row
						if (i > 1 && K[i - 1][w - wt[pos]] != 0) {
							temp = val[pos] + K[i - 1][w - wt[pos]];
							if (temp > maxi) {
								maxi = temp;
								maxPos = pos;
							}
						} // Check for 1st row
						else if (i == 1) {
							temp = val[pos] + K[i - 1][w - wt[pos]];
							if (temp > maxi) {
								maxi = temp;
								maxPos = pos;
							}
						}
					}
					mini = Math.min(mini, wt[pos]); // To select the minimum
													// that can be selected from
													// the present category
					pos++;
				}
				// Modification from normal Knapsack
				if (maxi == 0) {
					// K[i][w] = K[i - 1][w];
					K[i][w] = 0;
					picks[i][w] = "-1";
				} else {
					K[i][w] = maxi;
					picks[i][w] = i - 1 + "," + maxPos;
				}
			}
			if (mini != Integer.MAX_VALUE)
				j += mini;
			mini = Integer.MAX_VALUE;
		}

		int maxVal = K[n][W];
		for (i = 0; i <= W; i++)
			if (K[n][i] == maxVal)
				break;

		MckpReturn res = new MckpReturn(picks, i);

		return res;
	}

}