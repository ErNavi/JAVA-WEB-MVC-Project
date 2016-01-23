package com.cardinalts.survey.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cardinalts.survey.entities.SecurityQuestion;
import com.cardinalts.survey.factory.ServiceFactory;
import com.cardinalts.survey.util.SurveyHelper;

/**
 * Controller Servlet for companyRegistrationNew.jsp page.
 * Servlet implementation class CompanyRegistration
 */
public class CompanyRegistrationController extends HttpServlet {

	private static final long serialVersionUID = -530356842704782958L;


	public CompanyRegistrationController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return ArrayList of security question to companyRegistrationNew.jsp
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ArrayList<SecurityQuestion> questionList= ServiceFactory
				.getInstance().getCompanyRegistrationService().getSecurityQuestion();

		
		request.setAttribute("questionList", questionList);
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher( "/companyRegistrationNew.jsp" );
		dispatcher.forward( request, response );
		
	}

	/**
	 * @return saves data in the DB and redirects to login.jsp
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String check="";
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String path=ServiceFactory.getInstance().getCompanyRegistrationService().saveCompanyLogo(request);
		
		check=ServiceFactory.getInstance().getCompanyRegistrationService().saveCompanyDetail(new SurveyHelper().copyRequestDataToCompany(request,path));
		System.out.println("check is="+check);
		if(check!=null)
		{
			
			out.println("");
			//response.sendRedirect("login.jsp");

		}
		else
		{
			out.println("your Email ID is already Exists ....!!!!");
			//response.sendRedirect("CompanyRegistrationController?message=This Email ID is already Exists ....!!!!");
			
		}
		
	}

}
