package com.bestchoice.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bestchoice.bo.MakeBestChoiceLogic;

/**
 * Servlet implementation class MakeBestChoice
 */
@WebServlet("/MakeBestChoice")
public class MakeBestChoice extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MakeBestChoice() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String arr[] = request.getParameterValues("products");
		String budget = request.getParameter("budget");
		double budgetAmt = Double.parseDouble(budget);
		List<String> desiredProductList = new ArrayList<>();
		if (arr != null) {
			desiredProductList = Arrays.asList(arr);
			for (String car : arr)
				System.out.println(car);
		}
		System.out.println("Budget Amount : " + budgetAmt);
		MakeBestChoiceLogic luw = new MakeBestChoiceLogic();
		luw.getBestChoiceList(desiredProductList, (int) budgetAmt);
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
