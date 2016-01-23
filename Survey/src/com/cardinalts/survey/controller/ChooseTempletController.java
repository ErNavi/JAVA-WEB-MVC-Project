package com.cardinalts.survey.controller;

import java.io.*;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.cardinalts.survey.factory.ServiceFactory;
import com.cardinalts.survey.util.SurveyHelper;


public class ChooseTempletController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6531699360473566333L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

		
		HttpSession hs = request.getSession();
		if(null != hs.getAttribute("cid"))
		{
		
			
//			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher( "/WEB-INF/jsp/chooseTemplet.jsp" );
//			dispatcher.forward( request, response );
			
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html");
		int sid = Integer.parseInt(request.getParameter("sid").toString());
		int total_survey =0;
		System.out.println("get value of sid in Choose tepmpet controler(GET): "+sid);
		String output="";
		
		total_survey=ServiceFactory.getInstance().getChooseTemplatUserInfoService().getUserSurveyCount(new SurveyHelper().convertRequestToSaveTemplateUser(request));
		System.out.println("get value of total_survey in Choose tepmpet controler(GET): "+total_survey);
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher( "/WEB-INF/jsp/chooseTemplet.jsp?sid="+sid+"&newuser="+total_survey);
		dispatcher.forward( request, response );
		//response.sendRedirect("/WEB-INF/jsp/chooseTemplet.jsp?sid="+sid);
		//int cid = Integer.parseInt(request.getParameter("sid").toString());
		//output=ServiceFactory.getInstance().getChooseTempletInfoService().saveTempletDetail(new SurveyHelper().convertRequestToChooseTemplet(request),sid);

		        out.println(output);
		     
		}
		else
		{
			// redirects to Login page if no session is found
			response.sendRedirect("login.jsp");
		}
        
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
	response.setContentType("text/html;charset=UTF-8");
	//PrintWriter out = response.getWriter();
	//HttpSession hs = request.getSession();
	int sid=0;
	
	sid=ServiceFactory.getInstance().getChooseTempletInfoService().submitTempletDetail(new SurveyHelper().convertRequestToSaveTemplate(request));

	System.out.println("sid : "+sid);
	response.sendRedirect("AddQuestionController?sid="+sid+"&check=0");
	//response.sendRedirect("ChooseEmailController");
//	RequestDispatcher dispatcher=getServletContext().getRequestDispatcher( "/WEB-INF/jsp/chooseEmail.jsp");
//	dispatcher.forward( request, response );
	}
}
