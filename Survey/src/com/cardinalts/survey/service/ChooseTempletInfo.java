package com.cardinalts.survey.service;

import com.cardinalts.survey.entities.SurveyDetails;
import com.cardinalts.survey.entities.Template;

public interface ChooseTempletInfo {
	public String saveTempletDetail(Template template,int cid);
	public int submitTempletDetail(SurveyDetails surveyDetails);
}
