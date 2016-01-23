package com.cardinalts.survey.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import com.cardinalts.survey.factory.ServiceFactory;
import com.cardinalts.survey.util.SurveyHelper;

public class SurveyDetailsRemoveController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5601835632798626992L;

	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
//		HttpSession hs = request.getSession();
//		int cid = Integer.parseInt(hs.getAttribute("cid").toString());
//		int rowDeleted= ServiceFactory.getInstance().getChooseEmailInfoService().deleteEmail(request.getParameter("email"),cid);
//		int rowDeleted=ServiceFactory.getInstance().getChooseEmailInfoService().deleteEmail(Integer.parseInt(request.getParameter("id").toString()));
		//int rowDeleted=ServiceFactory.getInstance().getChooseEmailInfoService().deleteEmail(new SurveyHelper().convertRequestToIntegerForChooseTemplet(request));
		
		int check=Integer.parseInt(request.getParameter("check"));
		if(check==1)
		{
			int sid=0;
			sid=ServiceFactory.getInstance().getSurveyDetailInfoService().saveSurveyDetailInfo(new SurveyHelper().convertRequestToSurveyDetails(request));
			System.out.println("get value of sid in survey detail Remove controler (get if): "+sid);
			//response.sendRedirect("ChooseTempletController?sid="+sid);
			
		}
		else{
		int rowDeleted=ServiceFactory.getInstance().getSurveyDetailInfoService().deleteSurvey(new SurveyHelper().convertRequestToIntegerForChooseTemplet(request));
		out.println(rowDeleted+" email deleted");
		}
	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		response.setContentType("text/html");
		response.setContentType("text/html");
	//	PrintWriter out = response.getWriter();
		
	//	HttpSession hs = request.getSession();
		int sid=0;
		 
		sid=ServiceFactory.getInstance().getSurveyDetailInfoService().updateSurveyDetailInfo(new SurveyHelper().convertRequestToSurveyDetails2(request));
		System.out.println("get value of sid in survey detail controler (post): "+sid);
		response.sendRedirect("ChooseTempletController?sid="+sid);
		
		
	}
	
	
}
