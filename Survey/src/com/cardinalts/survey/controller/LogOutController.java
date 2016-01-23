package com.cardinalts.survey.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Controller Servlet for logout event.
 * Invalidates session and redirects to login.jsp
 */

public class LogOutController extends HttpServlet {

	
	private static final long serialVersionUID = 7373544226723263106L;

	public LogOutController() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	HttpSession	hs=request.getSession(false);
		hs.removeAttribute("cid");
		hs.removeAttribute("cemail");
		hs.removeAttribute("password");
		hs.removeAttribute("created_by");
		hs.invalidate();
		response.sendRedirect("login.jsp");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
	}
	
	
	}
	


