package com.cardinalts.survey.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cardinalts.survey.factory.ServiceFactory;
import com.cardinalts.survey.util.SurveyHelper;

public class ChooseTempletAjaxController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6933426944159773624L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession hs = request.getSession();
		int check=Integer.parseInt(request.getParameter("uname"));
		if(check>=0)
		{
//			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher( "/WEB-INF/jsp/chooseTemplet.jsp" );
//			dispatcher.forward( request, response );
		
		response.setContentType("text/html");

		String output="";
		
		int cid = Integer.parseInt(hs.getAttribute("cid").toString());
		output=ServiceFactory.getInstance().getChooseTempletInfoService().saveTempletDetail(new SurveyHelper().convertRequestToChooseTemplet(request),cid);

		        out.println(output);
		     
		}
		else
		{
			// redirects to Login page if no session is found
//			response.sendRedirect("login.jsp");
			out.println("Please Choose any Email Template.");
		}
        
		
		
		
	}
	
}
