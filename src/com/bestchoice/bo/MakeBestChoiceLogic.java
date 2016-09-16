package com.bestchoice.bo;

import java.util.*;

import org.apache.catalina.tribes.membership.McastService;

import com.bestchoice.model.PriceReview;
import com.bestchoice.model.Products;
import com.bestchoice.optimization.MckpAtmostOne;
import com.bestchoice.util.Loadlist;

public class MakeBestChoiceLogic {

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
				val[i][j] = (int) arr.get(j).getReview();
				wt[i][j] = (int) arr.get(j).getAmount();
			}
			i++;
		}

		MckpAtmostOne mckp = new MckpAtmostOne();
		mckp.getMckpValues(budgetAmt, wt, val, n, marr);
		System.out.println("Hi");
	}

}
