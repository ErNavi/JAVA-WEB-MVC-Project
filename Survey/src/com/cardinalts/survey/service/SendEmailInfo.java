package com.cardinalts.survey.service;

import java.util.ArrayList;
import java.util.Properties;

import com.cardinalts.survey.entities.Email;
import com.cardinalts.survey.entities.SurveyDetails;

public interface SendEmailInfo {
	public ArrayList<Email> getRecepientsList(int cid) ;
	public SurveyDetails getSurveyInfo(int sid);
	public int sendEmail(ArrayList<Email> arrayList,SurveyDetails surveyDetails,int sid,int cid,final Properties prop);
    
}
