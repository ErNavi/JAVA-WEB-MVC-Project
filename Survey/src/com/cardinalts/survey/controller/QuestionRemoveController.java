package com.cardinalts.survey.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cardinalts.survey.factory.ServiceFactory;
import com.cardinalts.survey.util.SurveyHelper;

public class QuestionRemoveController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7829099724944619154L;
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		System.out.println("didnt Bunked controller..i swer mam..");
//		HttpSession hs = request.getSession();
//		int cid = Integer.parseInt(hs.getAttribute("cid").toString());
//		int rowDeleted= ServiceFactory.getInstance().getChooseEmailInfoService().deleteEmail(request.getParameter("email"),cid);
//		int rowDeleted=ServiceFactory.getInstance().getChooseEmailInfoService().deleteEmail(Integer.parseInt(request.getParameter("id").toString()));
		int rowDeleted=ServiceFactory.getInstance().getSurveyQuestionsService().removeSurveyQuestions(new SurveyHelper().convertRequestToIntegerForQuestionRemove(request));
		System.out.println("u r a good boy..u did it..");
		out.println(rowDeleted+" email deleted");

	}

}
