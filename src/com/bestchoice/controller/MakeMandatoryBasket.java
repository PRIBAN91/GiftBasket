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

import com.bestchoice.bo.MakeBestChoiceLogic;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mandatoryArr[] = request.getParameterValues("products");
		String optionalArr[] = request.getParameterValues("products");
		String budget = request.getParameter("budget");
		double budgetAmt = Double.parseDouble(budget);
		String result = "";
		List<String> mandatoryItemList = new ArrayList<>();
		List<String> optionalItemList = new ArrayList<>();
		try {
			if (mandatoryArr != null && optionalArr!=null) {
				mandatoryItemList = Arrays.asList(mandatoryArr);
				optionalItemList = Arrays.asList(optionalArr);
				System.out.println("Budget Amount : " + budgetAmt);
				MandatoryBasketLogic luw = new MandatoryBasketLogic();
				result = luw.getBestChoiceList(mandatoryItemList, optionalItemList, (int) budgetAmt);
			}
		} catch (Exception e) {
			System.out.println("Exception occurred in main logic. ");
			e.printStackTrace();
		}
		request.setAttribute("Result", result);
		RequestDispatcher rs = request.getRequestDispatcher("Result.jsp");
		rs.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
