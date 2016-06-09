package com.bestchoice.bo;

import java.util.*;
import com.bestchoice.model.Products;
import com.bestchoice.util.Loadlist;

public class SeparationLogic {

	public List<String> extractProductList() {
		List<Products> productList = new ArrayList<>();
		Loadlist load = new Loadlist();
		productList = load.loadWordList();
		List<String> prodNameList = separateProducts(productList);
		return prodNameList;
	}

	public List<String> separateProducts(List<Products> productList) {
		Set<String> prodSet = new TreeSet<>();
		for (Products product : productList) {
			prodSet.add(product.getProductName());
		}
		List<String> prodList = new ArrayList<>(prodSet);
		return prodList;
	}

}
