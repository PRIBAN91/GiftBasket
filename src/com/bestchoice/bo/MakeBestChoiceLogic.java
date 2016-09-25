package com.bestchoice.bo;

import java.util.*;
import java.util.Map.Entry;
import com.bestchoice.model.MckpReturn;
import com.bestchoice.model.PriceReview;
import com.bestchoice.model.Products;
import com.bestchoice.optimization.MckpAtmostOne;
import com.bestchoice.optimization.MckpExactlyOne;
import com.bestchoice.util.Loadlist;

public class MakeBestChoiceLogic {

	private int NormFactor = 10;

	public void getBestChoiceList(List<String> desiredProducts, int budgetAmt) {
		Loadlist load = new Loadlist();
		List<Products> list = load.fetchProducts(desiredProducts);
		separateProducts(list, budgetAmt);
		System.out.println(list);
		// MckpExactlyOne mckp = new MckpExactlyOne();
		// int n = list.size();

	}

	public void separateProducts(List<Products> list, int budgetAmt) {
		int sz = list.size();
		LinkedHashMap<String, ArrayList<PriceReview>> lhm = new LinkedHashMap<>();
		for (Products prods : list) {
			String prodName = prods.getProductName();
			if (!lhm.containsKey(prodName)) {
				ArrayList<PriceReview> ar = new ArrayList<>();
				ar.add(new PriceReview(prods.getPrice(), prods.getReviewValue(), prods.getSubProductName()));
				lhm.put(prodName, ar);
			} else {
				ArrayList<PriceReview> ar = new ArrayList<>(lhm.get(prodName));
				ar.add(new PriceReview(prods.getPrice(), prods.getReviewValue(), prods.getSubProductName()));
				lhm.put(prodName, ar);
			}
		}

		int n = lhm.size(), i = 0, m;
		int wt[][] = new int[n][sz - n + 1];
		int val[][] = new int[n][sz - n + 1];
		int marr[] = new int[n];
		for (String s : lhm.keySet()) {
			ArrayList<PriceReview> arr = new ArrayList<>(lhm.get(s));
			m = arr.size();
			marr[i] = m;
			for (int j = 0; j < m; j++) {
				val[i][j] = (int) (arr.get(j).getReview() * NormFactor);
				wt[i][j] = (int) Math.round(arr.get(j).getAmount());
			}
			i++;
		}

		MckpExactlyOne mckp1 = new MckpExactlyOne();
		MckpReturn mckpExactlyone = mckp1.getMckpValues(budgetAmt, wt, val, n, marr);
		String picks[][] = mckpExactlyone.getPicks();
		int item = n, size = budgetAmt;
		ArrayList<String> pickList = new ArrayList<>();
		if (picks[n][budgetAmt].equals("-1")) {
			MckpAtmostOne mckp = new MckpAtmostOne();
			MckpReturn mckpAtmostOne = mckp.getMckpValues(budgetAmt, wt, val, n, marr);
			picks = mckpAtmostOne.getPicks();
			if (!picks[n][budgetAmt].equals("-1")) {
				while (item > 0) {
					if (!picks[item][size].equals("-1")) {
						int row = Integer.valueOf(picks[item][size].split(",")[0]);
						int column = Integer.valueOf(picks[item][size].split(",")[1]);
						pickList.add(picks[item][size]);
						System.out.println(picks[item][size]);
						item--;
						size -= wt[row][column];
					} else {
						item--;
					}
				}
				System.out.println("Amount to be spent : " + mckpAtmostOne.getAmountSpent());
			} else {
				System.out.println("Your r cheap dude! Live a little, will ya!!!");
			}
		} else {
			while (item > 0) {
				if (picks[item][size] != null && !picks[item][size].equals("-1")) {
					int row = Integer.valueOf(picks[item][size].split(",")[0]);
					int column = Integer.valueOf(picks[item][size].split(",")[1]);
					pickList.add(picks[item][size]);
					System.out.println(picks[item][size]);
					item--;
					size -= wt[row][column];
				} else {
					break;
				}
			}
			System.out.println("Amount to be spent : " + mckpExactlyone.getAmountSpent());
		}
		Collections.reverse(pickList);

		i = 0;
		int row = 0, col = 0;
		Iterator<Entry<String, ArrayList<PriceReview>>> it = lhm.entrySet().iterator();
		for (String s : pickList) {
			String sarr[] = s.split(",");
			row = Integer.parseInt(sarr[0]);
			col = Integer.parseInt(sarr[1]);
			while (i < row) {
				System.out.println(it.next().getKey() + "  ::  Nothing to be selected");
				i++;
			}
			Map.Entry<String, ArrayList<PriceReview>> entry = (Map.Entry<String, ArrayList<PriceReview>>) it.next();
			System.out.println(entry.getKey() + "  ::  " + entry.getValue().get(col).getProdName());
			i++;
		}
	}

}
