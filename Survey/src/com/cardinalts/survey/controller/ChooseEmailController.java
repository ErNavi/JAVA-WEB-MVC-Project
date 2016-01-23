package com.cardinalts.survey.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cardinalts.survey.entities.Email;
import com.cardinalts.survey.factory.ServiceFactory;

public class ChooseEmailController extends HttpServlet {

	
	private static final long serialVersionUID = 2287298561111647620L;
	/**
	 * obtains ArrayList of All choosen Emails of company logged in
	 * @param Request
	 * @return Response with Array List
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
		
		HttpSession hs=request.getSession();
		if(null != hs.getAttribute("cid"))
		{
		
		System.out.println("HERE AT BEGNING OF CHOOSE EMAIL CONTROLLER");
		
		response.setContentType("text/html");
		response.setContentType("text/html");
		//HttpSession hs = request.getSession();
		int cid = Integer.parseInt(hs.getAttribute("cid").toString());
		
		ArrayList<Email> emailList=ServiceFactory.getInstance().getChooseEmailInfoService().getEmailList(cid);
		System.out.println("HERE AT MID OF CHOOSE EMAIL CONTROLLER");
		request.setAttribute("emailList", emailList);
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher( "/WEB-INF/jsp/chooseEmail.jsp");
		dispatcher.forward( request, response );
		}
		
		else
		{
			// redirects to Login page if no session is found
//		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher( "/login.jsp" );
//		dispatcher.forward( request, response );
			response.sendRedirect("login.jsp");
		}
		}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		response.setContentType("text/html");

		HttpSession hs = request.getSession();
		int cid = Integer.parseInt(hs.getAttribute("cid").toString());
		
		ArrayList<Email> emailList=ServiceFactory.getInstance().getChooseEmailInfoService().getEmailList(cid);
		
		request.setAttribute("emailList", emailList);
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher( "/WEB-INF/jsp/nextPage.jsp");
		dispatcher.forward( request, response );
	
	}

	
	
}
