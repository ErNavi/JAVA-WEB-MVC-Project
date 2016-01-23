package com.cardinalts.survey.service;

import com.cardinalts.survey.entities.QuestionList;
import com.cardinalts.survey.entities.QuestionOption;

public interface AddQuestionInfo {

	public int saveQuestion(QuestionList questionList);
	public int saveOption(QuestionOption questionOption);
	

	
}
