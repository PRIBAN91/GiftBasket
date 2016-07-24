package com.bestchoice.bo;

import java.util.*;
import com.bestchoice.model.Products;
import com.bestchoice.util.Loadlist;

public class MakeBestChoiceLogic {

	public void getBestChoiceList(List<String> desiredProducts) {
		Loadlist load = new Loadlist();
		List<Products> list = load.fetchProducts(desiredProducts);
		System.out.println(list);

	}

}
