package com.cardinalts.survey.service;

import java.util.ArrayList;

import com.cardinalts.survey.entities.SurveyDetails;
/**
 * interface for SurveyDetailInfo
 * @author Shashwati.Nigam
 *
 */
public interface SurveyDetailInfo {
	
		public int saveSurveyDetailInfo(SurveyDetails surveyDetails );
		public int updateSurveyDetailInfo(SurveyDetails surveyDetails );
		public ArrayList<SurveyDetails> getSurveyList(int cid);
		public int deleteSurvey(int id);

		
}
