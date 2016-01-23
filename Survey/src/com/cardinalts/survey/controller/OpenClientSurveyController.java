package com.cardinalts.survey.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cardinalts.survey.dto.PreviewSurveyQuestion;
import com.cardinalts.survey.factory.ServiceFactory;
import com.cardinalts.survey.util.SurveyHelper;

/**
 * Controller Servlet for surveyDetail.jsp page.
 * Servlet implementation class SurveyDetailsController
 */
public class OpenClientSurveyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * constructor
	 * @see HttpServlet#HttpServlet()
	 */
	public OpenClientSurveyController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		int cid=Integer.parseInt(request.getParameter("cid"));
		int sid=Integer.parseInt(request.getParameter("sid"));
		int eid=Integer.parseInt(request.getParameter("eid"));
		
		List<PreviewSurveyQuestion> questionList=ServiceFactory.getInstance().getSurveyQuestionsService().getSurveyById(cid, sid);
		request.setAttribute("questionList", questionList);
		request.setAttribute("cid", cid);
		request.setAttribute("sid", sid);
		request.setAttribute("eid", eid);
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher( "/WEB-INF/jsp/openClientSurvey.jsp" );
		dispatcher.forward( request, response );
	}
	/**
	 * @return saves the surveyDetail in DB and redirects to emailListInfo.jsp
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("value of option: "+request.getParameter("option"));
//		System.out.println("value of description: "+request.getParameter("textarea"));
//		System.out.println("value of option: "+request.getParameter("option"));
		
//		if(request.getParameter("textarea")!=null||request.getParameter("textarea")!="" || request.getParameter("option")!=null)
//		{
		String url = "";
		url=ServiceFactory.getInstance().getOpenClientSurveyInfoService().saveResponse(new SurveyHelper().saveResponseToUserResponse(request));

		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher(url);
		dispatcher.forward( request, response );
//		}
		
	}

}
