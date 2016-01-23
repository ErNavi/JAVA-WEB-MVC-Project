package com.cardinalts.survey.entities;

import java.util.List;

public class PreviewSurveyQuestion {
	
	private int companyId;
	private int surveyId;
	private int questionId;
	private String question;
	private List<PreviewOptions> list;
	
	
	public List<PreviewOptions> getList() {
		return list;
	}
	public void setList(List<PreviewOptions> list) {
		this.list = list;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	
	

}
