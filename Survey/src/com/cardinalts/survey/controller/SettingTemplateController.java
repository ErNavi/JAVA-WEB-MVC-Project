package com.cardinalts.survey.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

public class SettingTemplateController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6421207141580589753L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
		
		System.out.println("HERE AT BEGNING OF SettingTemplateController");
		
		response.setContentType("text/html");
		response.setContentType("text/html");
	//	HttpSession hs = request.getSession();
		//int cid = Integer.parseInt(hs.getAttribute("cid").toString());
		

	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		response.setContentType("text/html");
		response.setContentType("text/html");

		//HttpSession hs = request.getSession();
	//	int cid = Integer.parseInt(hs.getAttribute("cid").toString());
	}


}
