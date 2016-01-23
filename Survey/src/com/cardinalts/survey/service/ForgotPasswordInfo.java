package com.cardinalts.survey.service;

import java.util.ArrayList;
import java.util.Properties;

import com.cardinalts.survey.entities.Company;
import com.cardinalts.survey.entities.Email;
import com.cardinalts.survey.entities.SurveyDetails;

public interface ForgotPasswordInfo {
	public Company getPassword(Company company);
	public int sendEmail(Company comp, final Properties prop);
}
