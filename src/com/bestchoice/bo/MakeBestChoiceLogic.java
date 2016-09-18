package com.bestchoice.bo;

import java.util.*;

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
		LinkedHashMap<String, ArrayList<PriceReview>> hm = new LinkedHashMap<>();
		for (Products prods : list) {
			String prodName = prods.getProductName();
			if (!hm.containsKey(prodName)) {
				ArrayList<PriceReview> ar = new ArrayList<>();
				ar.add(new PriceReview(prods.getPrice(), prods.getReviewValue()));
				hm.put(prodName, ar);
			} else {
				ArrayList<PriceReview> ar = new ArrayList<>(hm.get(prodName));
				ar.add(new PriceReview(prods.getPrice(), prods.getReviewValue()));
				hm.put(prodName, ar);
			}
		}

		int n = hm.size(), i = 0, m;
		int wt[][] = new int[n][sz - n + 1];
		int val[][] = new int[n][sz - n + 1];
		int marr[] = new int[n];
		for (String s : hm.keySet()) {
			ArrayList<PriceReview> arr = new ArrayList<>(hm.get(s));
			m = arr.size();
			marr[i] = m;
			for (int j = 0; j < m; j++) {
				val[i][j] = (int) (arr.get(j).getReview() * NormFactor);
				wt[i][j] = (int) arr.get(j).getAmount();
			}
			i++;
		}

		MckpAtmostOne mckp = new MckpAtmostOne();
		MckpReturn mckpAtmostOne = mckp.getMckpValues(budgetAmt, wt, val, n, marr);
		MckpExactlyOne mckp1 = new MckpExactlyOne();
		MckpReturn mckpExactlyone = mckp1.getMckpValues(budgetAmt, wt, val, n, marr);
		System.out.println(mckpAtmostOne + " : " + mckpExactlyone);
	}

}