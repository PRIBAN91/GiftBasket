package com.bestchoice.bo;

import java.util.List;
import com.bestchoice.model.KnapsackReturn;
import com.bestchoice.model.Products;
import com.bestchoice.optimization.Knapsack;
import com.bestchoice.util.Loadlist;

public class SimpleBasketLogic {

	private int NormFactor = 10;

	public String prepSimpleBasket(String prodName, int budgetAmt) {
		Loadlist load = new Loadlist();
		List<Products> list = load.fetchSingleProduct(prodName);
		StringBuilder sb = new StringBuilder(detemineResult(prodName, budgetAmt, list));
		return sb.toString();
	}

	public StringBuilder detemineResult(String prodName, int budgetAmt, List<Products> list) {
		StringBuilder sb = new StringBuilder();
		int sz = list.size();
		int val[] = new int[sz];
		int wt[] = new int[sz];
		int i = 0;
		for (Products prod : list) {
			wt[i] = (int) Math.round(prod.getPrice());
			val[i] = (int) Math.round(prod.getReviewValue() * NormFactor);
			i++;
		}
		Knapsack kp = new Knapsack();
		KnapsackReturn kpReturn = kp.knapSack(budgetAmt, wt, val, sz);
		int picks[][] = kpReturn.getPicks();
		int amountSpent = kpReturn.getAmountSpent();
		if (amountSpent > 0) {
			sb.append("<br> Congratulations! Your Simple Basket is ready. <br> <br>");
			sb.append("Your expenditure amount is : ").append(amountSpent);
			sb.append("<br><br><br> Here are your item/s with their individual cost/s. <br><br>");
			int item = sz, size = budgetAmt;
			while (item > 0) {
				if (picks[item][size] == 1) {
					Products singleProd = list.get(item - 1);
					sb.append(singleProd.getSubProductName()).append(" of cost ").append(singleProd.getPrice())
							.append(" unit/s<br>");
					item--;
					size -= wt[item];
				} else {
					item--;
				}
			}
		} else {
			sb.append("<br><br> Sorry, we could not determine your Simple Basket for you. "
					+ "<br><br>The budget seems to be very low. <br><br>"
					+ " Please increase the budget accordingly for us to prepare a Simple Basket.");
		}
		return sb;
	}

}
