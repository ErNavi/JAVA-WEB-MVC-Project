package com.cardinalts.survey.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cardinalts.survey.entities.Company;
import com.cardinalts.survey.entities.SecurityQuestion;
import com.cardinalts.survey.factory.ServiceFactory;
import com.cardinalts.survey.util.SurveyHelper;

public class ForgotPasswordController extends HttpServlet {

	private static final long serialVersionUID = -5820799114953185517L;


	public ForgotPasswordController()
	{
		super();
	}
	
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		
		ArrayList<SecurityQuestion> questionList= ServiceFactory
				.getInstance().getCompanyRegistrationService().getSecurityQuestion();

		
		request.setAttribute("questionList", questionList);
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher( "/forgotPassword.jsp" );
		dispatcher.forward( request, response );
		System.out.println("after doget()");
		
	}
	
	
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{ Company comp=null;
		System.out.println("in dopost()");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	
		comp=ServiceFactory.getInstance().getForgotPasswordInfoService().getPassword(new SurveyHelper().convertRequestToGetPassword(request));
	
		System.out.println("in forgot controller_first method worked");
		System.out.println(comp);
		//System.out.println("after getting password"+comp.getPassword());
		final Properties prop=new Properties();
		InputStream inputStream=getServletContext().getResourceAsStream("/WEB-INF/email.properties");
		prop.load(inputStream);
		
				if(comp!=null)
				{
					System.out.println("in if loop");
					//request.setAttribute("password", password);
					 
					System.out.println("after write password");
					ServiceFactory.getInstance().getForgotPasswordInfoService().sendEmail(comp, prop);
					out.println("your password has been mailed to your email id");
				}
				else
				{
					System.out.println("in else loop");
					
					//ServiceFactory.getInstance().getSendEmailInfoService().sendEmail(emailList, surveyDetails,sid,cid, prop);
					
					out.println("your security question or answer is wrong..");
					
				}
	
	}
	
	}


