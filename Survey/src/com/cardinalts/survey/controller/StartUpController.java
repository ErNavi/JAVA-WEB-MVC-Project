package com.cardinalts.survey.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import java.io.InputStream;

import com.cardinalts.survey.entities.Email;
import com.cardinalts.survey.entities.SurveyDetails;
import com.cardinalts.survey.factory.ServiceFactory;


public class StartUpController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9006440460090206820L;
	
	public StartUpController()
	{
		super();
	}
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In do get StartUpController");
		
		
		
		HttpSession hs = request.getSession();
		int cid = Integer.parseInt(hs.getAttribute("cid").toString());
		ArrayList<Email> emailList=ServiceFactory.getInstance().getSendEmailInfoService().getRecepientsList(cid);
		
		
		
		int sid=Integer.parseInt(request.getParameter("sid"));
		SurveyDetails surveyDetails=ServiceFactory.getInstance().getSendEmailInfoService().getSurveyInfo(sid);
		
		final Properties prop=new Properties();
		InputStream inputStream=getServletContext().getResourceAsStream("/WEB-INF/email.properties");
		prop.load(inputStream);
		/*String subject="hello every one";
		String emailMessage="on 9 sept from just try..";*/
		ServiceFactory.getInstance().getSendEmailInfoService().sendEmail(emailList, surveyDetails,sid,cid, prop);
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher( "/WEB-INF/jsp/nextPage.jsp" );
		dispatcher.forward( request, response );
		
		
		
		
		
		
		
		
		
		
		
		
		
		
/*final Properties prop=new Properties();
		
		InputStream inputStream=getServletContext().getResourceAsStream("/WEB-INF/email.properties");
		prop.load(inputStream);
		
		SendEmailInfo s=new SendEmailInfoImpl();
		ArrayList<String> emailList=new ArrayList<String>();
		emailList.add("shasha141294@gmail.com");
		emailList.add("shashwati.nigam.cardinalts@gmail.com");
		emailList.add("naveen.singh.cardinalts@gmail.com");
		String subject="hello";
		String emailMessage="have a nice day...";
		s.sendEmail(emailList,subject,emailMessage,prop);
		*/
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In StartUpController do post");
		
		/*HttpSession hs = request.getSession();
		int cid = Integer.parseInt(hs.getAttribute("cid").toString());
		ArrayList<String> emailList=ServiceFactory.getInstance().getSendEmailInfoService().getRecepientsList(cid);
		
		final Properties prop=new Properties();
		InputStream inputStream=getServletContext().getResourceAsStream("/WEB-INF/email.properties");
		prop.load(inputStream);
		String subject="hello every one";
		String emailMessage="on 9 sept from sasha";
		ServiceFactory.getInstance().getSendEmailInfoService().sendEmail(emailList, subject, emailMessage, prop);*/
		
	}
	
	

}
