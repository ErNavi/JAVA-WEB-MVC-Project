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
import com.cardinalts.survey.util.SurveyHelper;



/**
 * Controller Servlet for emailListInfo.jsp page
 * Servlet implementation class EmailListController
 */
public class EmailListController extends HttpServlet {

	private static final long serialVersionUID = 7153923092726449074L;

	/**
	 * constructor
	 * @see HttpServlet#HttpServlet()
	 */
	public EmailListController() {
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
		if(null != hs.getAttribute("cid"))
		{
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher( "/WEB-INF/jsp/emailListInfo.jsp" );
		dispatcher.forward( request, response );
		}
		else
		{
			// redirects to Login page if no session is found
			response.sendRedirect("login.jsp");
//		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher( "/login.jsp" );
//		dispatcher.forward( request, response.......LoginController );
		}
	}

	/**
	 * 
	 * @return makes emailList in DB and redirect to nextPage.jsp
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession hs = request.getSession();
		int cid = Integer.parseInt(hs.getAttribute("cid").toString());
		ArrayList<Email> output = null;
		String c_name=(String) hs.getAttribute("cemail");
		output=ServiceFactory.getInstance().getEmailListInfoService().saveEmailList(new SurveyHelper().convertRequestToEmailList(request),c_name);
		ServiceFactory.getInstance().getCompanyEmailListInfoService().saveCompanyEmailList(output, cid);
		//below is single call of nested fn
		//ServiceFactory.getInstance().getCompanyEmailListInfoService().saveCompanyEmailList(ServiceFactory.getInstance().getEmailListInfoService().saveEmailList(new SurveyHelper().convertRequestToEmailList(request)));
		
	
		response.sendRedirect("SurveyDetailsController");
		
//		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/SurveyDetailsController");
//		dispatcher.forward( request, response );
		
	}

}
