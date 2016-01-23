package com.cardinalts.survey.entities;

import java.util.Date;
import java.util.List;



public class QuestionList {
	private int id;
	private CompanyDetail companyDetail;
	private SurveyDetails surveyDetails;
	private List<QuestionOption> questionOptionList;
	
	private String description;
	private int answerType;	
	private Date createDate;
	public CompanyDetail getCompanyDetail() {
		return companyDetail;
	}
	public void setCompanyDetail(CompanyDetail companyDetail) {
		this.companyDetail = companyDetail;
	}
	public SurveyDetails getSurveyDetails() {
		return surveyDetails;
	}
	public void setSurveyDetails(SurveyDetails surveyDetails) {
		this.surveyDetails = surveyDetails;
	}
	public List<QuestionOption> getQuestionOptionList() {
		return questionOptionList;
	}
	public void setQuestionOptionList(List<QuestionOption> questionOptionList) {
		this.questionOptionList = questionOptionList;
	}
	
	private String createdBy;
	private Date updateDate;
	private String updatedBy;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAnswerType() {
		return answerType;
	}
	public void setAnswerType(int answerType) {
		this.answerType = answerType;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	
}
