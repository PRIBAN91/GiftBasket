package com.bestchoice.bo;

import java.util.List;
import com.bestchoice.model.Products;
import com.bestchoice.util.Loadlist;

public class MandatoryBasketLogic {
	
	private int NormFactor = 10;

	public String getBestChoiceList(List<String> mandatoryItems, List<String> optionalItems,int budgetAmt) {
		Loadlist load = new Loadlist();
		List<Products> list = load.fetchProducts(mandatoryItems);
		
		String result = "";//separateProducts(list, budgetAmt);
		return result;
	}

}
