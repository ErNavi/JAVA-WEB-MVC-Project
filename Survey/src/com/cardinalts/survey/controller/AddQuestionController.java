package com.cardinalts.survey.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cardinalts.survey.dto.PreviewSurveyQuestion;
import com.cardinalts.survey.factory.ServiceFactory;
import com.cardinalts.survey.util.SurveyHelper;

public class AddQuestionController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9051648062589455922L;
	public AddQuestionController() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs=request.getSession();
		int cid=Integer.parseInt(hs.getAttribute("cid").toString());
		int check=Integer.parseInt(request.getParameter("check"));
		int sid=Integer.parseInt(request.getParameter("sid"));
		
		if(check==1)
		{
	
		System.out.println("value of sid in add question"+sid);
		System.out.println("value of cid in add question"+cid);
		List<PreviewSurveyQuestion> questionList=ServiceFactory.getInstance().getSurveyQuestionsService().getSurveyById(cid, sid);
		request.setAttribute("questionList", questionList);
		//System.out.println("questionList.size()"+questionList);
//				if(questionList.size()!=0){
				RequestDispatcher dispatcher=getServletContext().getRequestDispatcher( "/WEB-INF/jsp/previewSurvey.jsp" );
				dispatcher.forward( request, response );
//				}
//				else{
//					response.sendRedirect("AddQuestionController?sid="+sid+"&check=0");
//				}
		}
		else
		{
		RequestDispatcher dispatcher=getServletContext().getRequestDispatcher( "/WEB-INF/jsp/addQuestion.jsp" );
	dispatcher.forward( request, response );

		}
	}
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		int qid=0;
		System.out.println("top of post in add question");
		System.out.println("value of option: "+request.getParameter("option"));
		System.out.println("value of description: "+request.getParameter("textarea"));
		if(request.getParameter("textarea")!=null && request.getParameter("textarea")!="" && request.getParameter("option")!=null)
			{
		qid=ServiceFactory.getInstance().getSurveyQuestionsService().saveSurveyQuestions(new SurveyHelper().convertRequestToQuestionList(request));
		out.println(qid);
	// ServiceFactory.getInstance().getAddQuestionInfoService().saveOption(new SurveyHelper().convertRequestToQuestionOption(request,question_id,optionArr[i],correctAnswerArr[i]));
	}
		else{
			System.out.println("question empty");
		}
	}

	
}
