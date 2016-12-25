package com.bestchoice.optimization;

import java.util.Arrays;
import com.bestchoice.model.MckpReturn;

public class MckpAtmostOne {

	public MckpReturn getMckpValues(int W, int wt1[][], int val1[][], int n, int m[]) {
		int i, w;
		int K[][] = new int[n + 1][W + 1], pos, maxi, temp, maxPos;
		String picks[][] = new String[n + 1][W + 1];

		for (i = 1; i <= n; i++) {
			int val[] = Arrays.copyOf(val1[i - 1], m[i - 1]);
			int wt[] = Arrays.copyOf(wt1[i - 1], m[i - 1]);
			for (w = 1; w <= W; w++) {
				temp = 0;
				pos = 0;
				maxi = 0;
				maxPos = 0;
				while (pos < m[i - 1]) {
					if (wt[pos] <= w) {
						temp = val[pos] + K[i - 1][w - wt[pos]];
						if (temp > maxi) {
							maxi = temp;
							maxPos = pos;
						}
					}
					pos++;
				}
				if (K[i - 1][w] > maxi || maxi == 0) {
					K[i][w] = K[i - 1][w];
					picks[i][w] = "-1";
				} else {
					K[i][w] = maxi;
					picks[i][w] = i - 1 + "," + maxPos;
				}
			}
		}

		int maxVal = K[n][W];
		for (i = 0; i <= W; i++)
			if (K[n][i] == maxVal)
				break;

		MckpReturn res = new MckpReturn(picks, i);

		return res;
	}

}
