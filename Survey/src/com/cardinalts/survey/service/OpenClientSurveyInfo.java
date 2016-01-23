package com.cardinalts.survey.service;

import java.util.ArrayList;

import com.cardinalts.survey.entities.QuestionList;
import com.cardinalts.survey.entities.UserResponse;

public interface OpenClientSurveyInfo {
	public String saveResponse(ArrayList<UserResponse> userResponse);
	
}
