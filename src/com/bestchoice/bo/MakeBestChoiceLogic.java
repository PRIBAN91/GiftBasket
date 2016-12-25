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

	public String getBestChoiceList(List<String> desiredProducts, int budgetAmt) {
		Loadlist load = new Loadlist();
		List<Products> list = load.fetchProducts(desiredProducts);
		String result = separateProducts(list, budgetAmt);
		return result;
	}

	public String separateProducts(List<Products> list, int budgetAmt) {
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
				val[i][j] = (int) Math.round(arr.get(j).getReview() * NormFactor);
				wt[i][j] = (int) Math.round(arr.get(j).getAmount());
			}
			i++;
		}
		StringBuilder sb = new StringBuilder(determineResult(budgetAmt, marr, wt, val, n, lhm));
		return sb.toString();
	}

	public StringBuilder determineResult(int budgetAmt, int marr[], int wt[][], int val[][], int n,
			LinkedHashMap<String, ArrayList<PriceReview>> lhm) {
		StringBuilder sb = new StringBuilder();
		MckpExactlyOne mckp1 = new MckpExactlyOne();
		MckpReturn mckpExactlyone = mckp1.getMckpValues(budgetAmt, wt, val, n, marr);
		String picks[][] = mckpExactlyone.getPicks();
		int item = n, size = budgetAmt;
		ArrayList<String> pickList = new ArrayList<>();
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
						item--;
						size -= wt[row][column];
					} else {
						item--;
					}
				}
				sb.append("<br> Congratulations! Your Gift Basket is ready. <br> <br>");
				sb.append("It seems that taking at least one item from each category you selected")
						.append(" for your GiftBasket will exceed your budget. <br>")
						.append("However we have determined one item from few of the categories ")
						.append("you have chosen, which will produce the best GiftBasket. <br> <br>");
				sb.append("Amount to be spent is " + mckpAtmostOne.getAmountSpent() + " unit/s.<br><br>");
			} else {
				sb.append("<br><br> Sorry, we could not determine your Gift Basket for you. "
						+ "<br><br>The budget seems to be very low. <br><br>"
						+ " Please increase the budget accordingly for us to prepare a Gift Basket.");
			}
		} else {
			while (item > 0 && size > 0) {
				if (picks[item][size] != null && !picks[item][size].equals("-1")) {
					int row = Integer.valueOf(picks[item][size].split(",")[0]);
					int column = Integer.valueOf(picks[item][size].split(",")[1]);
					pickList.add(picks[item][size]);
					item--;
					size -= wt[row][column];
				} else {
					break;
				}
			}
			sb.append("<br> Congratulations! Your Gift Basket is ready. <br> <br>");
			sb.append("Amount to be spent is " + mckpExactlyone.getAmountSpent() + " unit/s.<br><br>");
		}
		prepareOutput(pickList, lhm, sb);
		return sb;
	}

	public void prepareOutput(List<String> pickList, LinkedHashMap<String, ArrayList<PriceReview>> lhm,
			StringBuilder sb) {
		Collections.reverse(pickList);
		int i = 0, row = 0, col = 0;
		Iterator<Entry<String, ArrayList<PriceReview>>> it = lhm.entrySet().iterator();
		int mapSize = lhm.size();
		for (String s : pickList) {
			String sarr[] = s.split(",");
			row = Integer.parseInt(sarr[0]);
			col = Integer.parseInt(sarr[1]);
			while (i < row) {
				sb.append(it.next().getKey() + "  ::  Nothing could be selected <br>");
				i++;
			}
			Map.Entry<String, ArrayList<PriceReview>> entry = (Map.Entry<String, ArrayList<PriceReview>>) it.next();
			sb.append(entry.getKey() + "  ::  " + entry.getValue().get(col).getProdName() + " of cost "
					+ entry.getValue().get(col).getAmount() + " unit/s<br>");
			i++;
		}
		while (i < mapSize) {
			sb.append(it.next().getKey() + "  ::  Nothing could be selected <br>");
			i++;
		}
	}

}
