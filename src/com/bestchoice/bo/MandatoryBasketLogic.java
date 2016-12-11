package com.bestchoice.bo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.bestchoice.model.MckpReturn;
import com.bestchoice.model.PriceReview;
import com.bestchoice.model.Products;
import com.bestchoice.optimization.MckpAtmostOne;
import com.bestchoice.optimization.MckpExactlyOne;
import com.bestchoice.util.Loadlist;

public class MandatoryBasketLogic {

	private int NormFactor = 10;

	public String getBestChoiceList(List<String> mandatoryItems, List<String> optionalItems, int budgetAmt) {
		Loadlist load = new Loadlist();
		List<Products> mandatoryList = load.fetchProducts(mandatoryItems);
		List<Products> optionalList = load.fetchProducts(optionalItems);
		String result = separateProducts(mandatoryList, optionalList, budgetAmt);
		return result;
	}

	public String separateProducts(List<Products> mandatoryList, List<Products> optionalList, int budgetAmt) {
		int sz = mandatoryList.size();
		LinkedHashMap<String, ArrayList<PriceReview>> lhm = new LinkedHashMap<>();
		for (Products prods : mandatoryList) {
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
				val[i][j] = (int) Math.round(arr.get(j).getReview() * NormFactor);
				wt[i][j] = (int) Math.round(arr.get(j).getAmount());
			}
			i++;
		}
		StringBuilder sb = new StringBuilder(determineResult(budgetAmt, marr, wt, val, n, lhm, optionalList));
		return sb.toString();
	}

	public StringBuilder determineResult(int budgetAmt, int marr[], int wt[][], int val[][], int n,
			LinkedHashMap<String, ArrayList<PriceReview>> lhm, List<Products> optionalList) {
		StringBuilder sb = new StringBuilder();
		MckpExactlyOne mckp1 = new MckpExactlyOne();
		MckpReturn mckpExactlyone = mckp1.getMckpValues(budgetAmt, wt, val, n, marr);
		String picks[][] = mckpExactlyone.getPicks();
		int item = n, size = budgetAmt;
		if (picks[item][size] == null || picks[n][budgetAmt].equals("-1")) {
			sb.append("<br><br> Sorry, we could not determine your Mandatory Basket for you. "
					+ "<br><br>The budget seems to be very low. <br><br>"
					+ " Please increase the budget accordingly for us to prepare a Mandatory Basket.");
		} else {
			List<String> pickList = determinePickList(picks, wt, item, size);
			sb.append("<br> Congratulations! Your Mandatory Basket is ready. <br> <br>");
			sb.append("<br><br><br> Items to be selected from Madatory List are : <br><br>");
			prepareOutput(pickList, lhm, sb);
			int mandatoryBudget = mckpExactlyone.getAmountSpent(), optionalBudget = budgetAmt - mandatoryBudget;
			LinkedHashMap<String, ArrayList<PriceReview>> lhm1 = new LinkedHashMap<>();
			for (Products prods : optionalList) {
				String prodName = prods.getProductName();
				if (!lhm1.containsKey(prodName)) {
					ArrayList<PriceReview> ar = new ArrayList<>();
					ar.add(new PriceReview(prods.getPrice(), prods.getReviewValue(), prods.getSubProductName()));
					lhm1.put(prodName, ar);
				} else {
					ArrayList<PriceReview> ar = new ArrayList<>(lhm1.get(prodName));
					ar.add(new PriceReview(prods.getPrice(), prods.getReviewValue(), prods.getSubProductName()));
					lhm1.put(prodName, ar);
				}
			}
			n = lhm1.size();
			int i = 0, m, sz = optionalList.size();
			wt = new int[n][sz - n + 1];
			val = new int[n][sz - n + 1];
			marr = new int[n];
			for (String s : lhm1.keySet()) {
				ArrayList<PriceReview> arr = new ArrayList<>(lhm1.get(s));
				m = arr.size();
				marr[i] = m;
				for (int j = 0; j < m; j++) {
					val[i][j] = (int) Math.round(arr.get(j).getReview() * NormFactor);
					wt[i][j] = (int) Math.round(arr.get(j).getAmount());
				}
				i++;
			}
			sb.append("<br><br><br> In Optional List : <br><br>");
			determineOptionalList(optionalBudget, marr, wt, val, n, lhm1, sb);
		}
		return sb;

	}

	public void determineOptionalList(int budgetAmt, int marr[], int wt[][], int val[][], int n,
			LinkedHashMap<String, ArrayList<PriceReview>> lhm, StringBuilder sb) {
		MckpExactlyOne mckp1 = new MckpExactlyOne();
		MckpReturn mckpExactlyone = mckp1.getMckpValues(budgetAmt, wt, val, n, marr);
		String picks[][] = mckpExactlyone.getPicks();
		int item = n, size = budgetAmt;
		List<String> pickList = new ArrayList<>();
		if (picks[item][size] == null || picks[n][budgetAmt].equals("-1")) {
			MckpAtmostOne mckp = new MckpAtmostOne();
			MckpReturn mckpAtmostOne = mckp.getMckpValues(budgetAmt, wt, val, n, marr);
			picks = mckpAtmostOne.getPicks();
			if (mckpAtmostOne.getAmountSpent() != 0) {
				while (item > 0 && size > 0) {
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
				sb.append("Not all the items could be selected. Please have a look.<br>");
			} else {
				sb.append("Sorry, nothing could be selected. Very little or no amount was ");
				sb.append("left after creating mandatory basket.<br>");
			}
		} else {
			pickList = determinePickList(picks, wt, item, size);
		}
		prepareOutput(pickList, lhm, sb);
	}

	public List<String> determinePickList(String picks[][], int wt[][], int item, int size) {
		List<String> pickList = new ArrayList<>();
		while (item > 0 && size > 0) {
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
		return pickList;
	}

	public void prepareOutput(List<String> pickList, LinkedHashMap<String, ArrayList<PriceReview>> lhm,
			StringBuilder sb) {
		Collections.reverse(pickList);
		int i = 0, row = 0, col = 0;
		Iterator<Entry<String, ArrayList<PriceReview>>> it = lhm.entrySet().iterator();
		for (String s : pickList) {
			String sarr[] = s.split(",");
			row = Integer.parseInt(sarr[0]);
			col = Integer.parseInt(sarr[1]);
			while (i < row) {
				sb.append(it.next().getKey() + "  ::  Nothing could be selected <br>");
				i++;
			}
			Map.Entry<String, ArrayList<PriceReview>> entry = (Map.Entry<String, ArrayList<PriceReview>>) it.next();
			System.out.println(entry.getKey() + "  ::  " + entry.getValue().get(col).getProdName());
			sb.append(entry.getKey() + "  ::  " + entry.getValue().get(col).getProdName() + "<br>");
			i++;
		}
	}

}
