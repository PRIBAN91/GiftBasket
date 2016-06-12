package com.bestchoice.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bestchoice.bo.FetchProductLogic;

/**
 * Servlet implementation class BasicServlet
 */
@WebServlet(value = "/BasicServlet", loadOnStartup = 0)
public class BasicServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BasicServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		System.out.println("In basic servlet");
		// PrintWriter out = response.getWriter();
		FetchProductLogic fetch = new FetchProductLogic();
		List<String> prodList = new ArrayList<>();
		prodList = fetch.extractProductList();
		System.out.println("Product List : " + prodList);
		request.setAttribute("ProductList", prodList);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
