package com.bestchoice.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bestchoice.model.Products;
import com.bestchoice.util.Loadlist;

/**
 * Servlet implementation class CustomerReview
 */
@WebServlet("/CustomerReview")
public class CustomerReview extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerReview() {
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
		// Fetching product list from DB
		List<Products> prodList = new ArrayList<>();
		Loadlist load = new Loadlist();
		prodList = load.loadProductList();
		request.setAttribute("ProductList", prodList);
		// Forwarding the list from table to home page (this is for demo purpose
		// only)
		request.getRequestDispatcher("Review.jsp").forward(request, response);
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
