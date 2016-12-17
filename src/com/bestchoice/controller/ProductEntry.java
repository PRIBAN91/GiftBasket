package com.bestchoice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bestchoice.util.InputProdDetails;

/**
 * Servlet implementation class ProductEntry
 */
@WebServlet(urlPatterns = { "/ProductEntry" })
public class ProductEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductEntry() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String prodName = request.getParameter("prodName");
		String subProdName = request.getParameter("subProdName");
		String priceStr = request.getParameter("price");
		double price = Double.valueOf(priceStr);
		InputProdDetails input = new InputProdDetails();
		input.insertProductData(prodName, subProdName, price);
		request.setAttribute("Message", "Your product has been successfully inserted in database!");
		request.getRequestDispatcher("DataEntry.jsp").forward(request, response);
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
