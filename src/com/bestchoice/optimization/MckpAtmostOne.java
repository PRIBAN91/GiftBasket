package com.bestchoice.optimization;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MckpAtmostOne {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter out = new PrintWriter(new BufferedOutputStream(
			System.out));

	public static int mckp(int W, int wt1[][], int val1[][], int n, int m[]) {
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
		while (item > 1) {
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

		return K[n][W];
	}

	public static void main(String[] args) throws Exception {

		System.out.println("Enter  : ");

		StringTokenizer st = new StringTokenizer(br.readLine()), st1;

		int n = Integer.parseInt(st.nextToken()), W = Integer.parseInt(st
				.nextToken());

		int val[][] = new int[n][5];
		int wt[][] = new int[n][5];
		int m[] = new int[n];

		for (int i = 0; i < n; i++) {
			m[i] = Integer.parseInt(br.readLine());
			for (int j = 0; j < m[i]; j++) {
				st1 = new StringTokenizer(br.readLine());
				wt[i][j] = Integer.parseInt(st1.nextToken());
				val[i][j] = Integer.parseInt(st1.nextToken());
			}
		}

		int maxItems = mckp(W, wt, val, n, m);
		if (maxItems == 0)
			System.out.println("-1");
		else {
			System.out.println(maxItems);
		}
	}
}
