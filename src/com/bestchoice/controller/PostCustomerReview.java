package com.bestchoice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bestchoice.util.PostRatings;

/**
 * Servlet implementation class PostCustomerReview
 */
@WebServlet("/PostCustomerReview")
public class PostCustomerReview extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PostCustomerReview() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("In customer review");
		String subProductName = request.getParameter("products");
		String rating = request.getParameter("rating");
		System.out.println(subProductName);
		PostRatings postRating = new PostRatings();
		boolean flag = true;
		try {
			postRating.postCustomerRating(subProductName, Double.valueOf(rating));
		} catch (Exception e) {
			flag = false;
		}
		if (flag) {
			request.setAttribute("Message", "Your rating has been successfully posted!");
		} else {
			request.setAttribute("Message",
					"There seems to some trouble in posting your rating. Please try after sometimes.");
		}
		request.getRequestDispatcher("CustomerReview").forward(request, response);
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
