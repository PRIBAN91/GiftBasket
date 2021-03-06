package com.bestchoice.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bestchoice.util.Loadlist;

/**
 * Servlet implementation class SimpleBasket
 */
@WebServlet("/SimpleBasket")
public class SimpleBasket extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SimpleBasket() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		// Fetching product list from DB
		List<String> prodNameList = new ArrayList<>();
		Loadlist load = new Loadlist();
		prodNameList = load.fetchProductNames();
		request.setAttribute("ProductList", prodNameList);
		// Forwarding the list from table to home page (this is for demo purpose
		// only)
		request.getRequestDispatcher("Simplebasket.jsp").forward(request, response);
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
