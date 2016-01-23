package com.cardinalts.survey.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cardinalts.survey.entities.SurveyDetails;
import com.cardinalts.survey.factory.ServiceFactory;
import com.cardinalts.survey.util.SurveyHelper;

/**
 * Controller Servlet for surveyDetail.jsp page.
 * Servlet implementation class SurveyDetailsController
 */
public class SurveyDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * constructor
	 * @see HttpServlet#HttpServlet()
	 */
	public SurveyDetailsController() {
		super();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//checks the status of session
		HttpSession hs=request.getSession();
		if(null != hs.getAttribute("cemail"))
		{
		
			response.setContentType("text/html");

			int cid = Integer.parseInt(hs.getAttribute("cid").toString());
			
			ArrayList<SurveyDetails> surveyList=ServiceFactory.getInstance().getSurveyDetailInfoService().getSurveyList(cid);
			
			request.setAttribute("surveyList", surveyList);
			RequestDispatcher dispatcher=getServletContext().getRequestDispatcher( "/WEB-INF/jsp/surveyDetail.jsp" );
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

	/**
	 * @return saves the surveyDetail in DB and redirects to emailListInfo.jsp
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		int sid=0;
		 
		sid=ServiceFactory.getInstance().getSurveyDetailInfoService().saveSurveyDetailInfo(new SurveyHelper().convertRequestToSurveyDetails(request));
		System.out.println("value of sid in survey detail controler (post): "+sid);
		out.println(sid);
		
		//response.sendRedirect("ChooseTempletController?sid="+sid);
		

	}

}
