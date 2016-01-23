package com.cardinalts.survey.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;



import com.cardinalts.survey.entities.Company;
import com.cardinalts.survey.entities.SecurityQuestion;
/**
 * interface for CompanyRegistration
 * @author Shashwati.Nigam
 *
 */
public interface CompanyRegistration {
public String saveCompanyDetail(Company company);
public ArrayList<SecurityQuestion> getSecurityQuestion();
public String saveCompanyLogo(HttpServletRequest request);

}
