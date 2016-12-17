package com.bestchoice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bestchoice.bo.SimpleBasketLogic;

/**
 * Servlet implementation class PrepareSimpBasket
 */
@WebServlet("/PrepareSimpBasket")
public class PrepareSimpBasket extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PrepareSimpBasket() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String prodName = request.getParameter("products");
		String budgetAmt = request.getParameter("budget");
		double budget = Double.parseDouble(budgetAmt);
		SimpleBasketLogic makeSimpBasket = new SimpleBasketLogic();
		String result = makeSimpBasket.prepSimpleBasket(prodName, (int) budget);
		request.setAttribute("Result", result);
		RequestDispatcher rs = request.getRequestDispatcher("SimpbasketResult.jsp");
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
