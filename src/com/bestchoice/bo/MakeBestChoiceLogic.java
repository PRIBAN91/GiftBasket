package com.bestchoice.bo;

import java.util.*;

import com.bestchoice.model.PriceReview;
import com.bestchoice.model.Products;
import com.bestchoice.util.Loadlist;

public class MakeBestChoiceLogic {

	public void getBestChoiceList(List<String> desiredProducts, double budgetAmt) {
		Loadlist load = new Loadlist();
		List<Products> list = load.fetchProducts(desiredProducts);
		System.out.println(list);
		// MckpExactlyOne mckp = new MckpExactlyOne();
		// int n = list.size();

	}

	public void separateProducts(List<Products> list) {
		HashMap<String, ArrayList<PriceReview>> hm = new HashMap<>();
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

	}

}
