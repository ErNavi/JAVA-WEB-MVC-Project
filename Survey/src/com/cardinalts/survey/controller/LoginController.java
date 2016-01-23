package com.cardinalts.survey.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cardinalts.survey.entities.Company;
import com.cardinalts.survey.factory.ServiceFactory;

/**
 * Controller Servlet for login.jsp page.
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2064949419263172657L;
	/**
	 * constructor
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession(false);
		if(null != hs.getAttribute("cemail"))
		{
		hs.removeAttribute("cid");
		hs.removeAttribute("cemail");
		hs.removeAttribute("password");
		hs.removeAttribute("created_by");
		hs.invalidate();
		System.out.println("session destroyed");
		}
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher( "/login.jsp" );
		dispatcher.forward( request, response );
		}
	/**
	 * @return checks login detail and redirects to surveyDetail.jsp or login.jsp accordingly
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
	   PrintWriter out = response.getWriter();
		
		Company company = ServiceFactory
				.getInstance()
				.getLoginInfoService()
				.checkLoginDetail(request.getParameter("companyloginid"),
						request.getParameter("companyloginpassword"));
		
		System.out.println("==================="+company);

		if (company != null) 
		{
			HttpSession hs = request.getSession(true);
			hs.setAttribute("cid", company.getId());
			hs.setAttribute("cemail", company.getEmail());
			hs.setAttribute("cname", company.getName());
			hs.setAttribute("password", company.getPassword());
			hs.setAttribute("created_by", company.getCreatedBy());

			out.println("");
		
		} else {
			
		//response.sendRedirect("login.jsp?message=User ID or Password is wrong ....!!!!");
			
			out.println("User ID or Password is wrong ....!!!!");
		}
		
	}

}
