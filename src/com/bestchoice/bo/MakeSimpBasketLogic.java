package com.bestchoice.bo;

import java.util.List;
import com.bestchoice.model.Products;
import com.bestchoice.util.Loadlist;

public class MakeSimpBasketLogic {

	private int NormFactor = 10;

	public void prepSimpleBasket(String prodName, int budgetAmt) {
		Loadlist load = new Loadlist();
		List<Products> list = load.fetchSingleProduct(prodName);
		StringBuilder sb = new StringBuilder(detemineResult(prodName, budgetAmt, list));

	}

	public StringBuilder detemineResult(String prodName, int budgetAmt, List<Products> list) {
		StringBuilder sb = new StringBuilder();
		int sz = list.size();
		int val[] = new int[sz];
		int wt[] = new int[sz];
		int i = 0;
		for (Products prod : list) {
			val[i] = (int) Math.round(prod.getPrice());
			wt[i] = (int) Math.round(prod.getReviewValue() * NormFactor);
			i++;
		}
		return sb;
	}

}
