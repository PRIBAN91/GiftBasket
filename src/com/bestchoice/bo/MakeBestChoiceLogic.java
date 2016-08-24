package com.bestchoice.bo;

import java.util.*;
import com.bestchoice.model.Products;
import com.bestchoice.optimization.MckpExactlyOne;
import com.bestchoice.util.Loadlist;

public class MakeBestChoiceLogic {

	public void getBestChoiceList(List<String> desiredProducts, double budgetAmt) {
		Loadlist load = new Loadlist();
		List<Products> list = load.fetchProducts(desiredProducts);
		System.out.println(list);
		MckpExactlyOne mckp = new MckpExactlyOne();
		int n = list.size();

	}

}
