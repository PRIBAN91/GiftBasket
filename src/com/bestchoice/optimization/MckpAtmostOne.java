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
				if (K[i - 1][w] > maxi) {
					K[i][w] = K[i - 1][w];
					picks[i][w] = "-1";
				} else {
					K[i][w] = maxi;
					picks[i][w] = i - 1 + "," + maxPos;
				}
			}
		}

		int item = n, size = W;
		while (item > 0) {
			if (!picks[item][size].equals("-1")) {
				int row = Integer.valueOf(picks[item][size].split(",")[0]);
				int column = Integer.valueOf(picks[item][size].split(",")[1]);
				System.out.println(picks[item][size]);
				item--;
				size -= wt1[row][column];
			} else {
				item--;
			}
		}

		MckpReturn res = new MckpReturn(picks, K[n][W]);

		return res;
	}

}
