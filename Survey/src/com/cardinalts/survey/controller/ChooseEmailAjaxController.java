package com.cardinalts.survey.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cardinalts.survey.entities.Email;
import com.cardinalts.survey.factory.ServiceFactory;
import com.cardinalts.survey.util.SurveyHelper;

public class ChooseEmailAjaxController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4741213110479805604L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
//		HttpSession hs = request.getSession();
//		int cid = Integer.parseInt(hs.getAttribute("cid").toString());
//		int rowDeleted= ServiceFactory.getInstance().getChooseEmailInfoService().deleteEmail(request.getParameter("email"),cid);
//		int rowDeleted=ServiceFactory.getInstance().getChooseEmailInfoService().deleteEmail(Integer.parseInt(request.getParameter("id").toString()));
		int rowDeleted=ServiceFactory.getInstance().getChooseEmailInfoService().deleteEmail(new SurveyHelper().convertRequestToIntegerForChooseTemplet(request));
		out.println(rowDeleted+" email deleted");
	
	
	}

	/**
	 * Query 
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		int cid = Integer.parseInt(session.getAttribute("cid").toString());
		ArrayList<Email> emailList = null;
		String c_name=(String) session.getAttribute("cemail");
		emailList=ServiceFactory.getInstance().getEmailListInfoService().saveEmailList(new SurveyHelper().convertRequestToEmailList(request),c_name);
		System.out.println("============in ajax controlr wit output========: ");
		System.out.println("**********starts OUTPUT Display******** ");
		
		for (Email companyemaillist1 : emailList) {
		      System.out.println("Number Id = " + companyemaillist1.getId());
		      System.out.println("Number cid = " + cid);}
		
		System.out.println("**********ends OUTPUT Display******** ");
		ServiceFactory.getInstance().getCompanyEmailListInfoService().saveCompanyEmailList(emailList, cid);
		//below is single call of nested fn
		//ServiceFactory.getInstance().getCompanyEmailListInfoService().saveCompanyEmailList(ServiceFactory.getInstance().getEmailListInfoService().saveEmailList(new SurveyHelper().convertRequestToEmailList(request)));

		response.sendRedirect("ChooseEmailController");
		
//		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/ChooseEmailController");
//		dispatcher.forward( request, response );
		
	}
	
	
}
