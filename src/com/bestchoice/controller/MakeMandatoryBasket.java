package com.bestchoice.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bestchoice.bo.MandatoryBasketLogic;

/**
 * Servlet implementation class MakeMandatoryBasket
 */
@WebServlet("/MakeMandatoryBasket")
public class MakeMandatoryBasket extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MakeMandatoryBasket() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mandatoryArr[] = request.getParameterValues("mandatory");
		String optionalArr[] = request.getParameterValues("optional");
		String budget = request.getParameter("budget");
		int budgetAmt = Integer.parseInt(budget);
		String result = "<br><br>There was some system error at this point of time."
				+ " Please try again after sometime. We will investigate the issue.";
		List<String> mandatoryItemList = new ArrayList<>();
		List<String> optionalItemList = new ArrayList<>();
		try {
			if (mandatoryArr != null && optionalArr != null) {
				mandatoryItemList = Arrays.asList(mandatoryArr);
				optionalItemList = Arrays.asList(optionalArr);
				MandatoryBasketLogic luw = new MandatoryBasketLogic();
				result = luw.getBestChoiceList(mandatoryItemList, optionalItemList, budgetAmt);
			}
		} catch (Exception e) {
			System.out.println("Exception occurred in main logic of Mandatory Basket.");
			e.printStackTrace();
		}
		request.setAttribute("Result", result);
		RequestDispatcher rs = request.getRequestDispatcher("MandatoryResult.jsp");
		rs.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
