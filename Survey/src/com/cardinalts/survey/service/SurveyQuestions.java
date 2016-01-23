package com.cardinalts.survey.service;

import java.util.List;

import com.cardinalts.survey.dto.PreviewSurveyQuestion;
import com.cardinalts.survey.entities.QuestionList;

public interface SurveyQuestions {

	public Integer saveSurveyQuestions(QuestionList questionList);
	public Integer removeSurveyQuestions(int qid);
	public List<PreviewSurveyQuestion> getSurveyById(int companyId,int surveyId);

}
