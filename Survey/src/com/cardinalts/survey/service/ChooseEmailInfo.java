package com.cardinalts.survey.service;

import java.util.ArrayList;

import com.cardinalts.survey.entities.CompanyEmailList;
import com.cardinalts.survey.entities.Email;

public interface ChooseEmailInfo {
	public ArrayList<CompanyEmailList> getCompanyEmailList(int cid);
	
//	public ArrayList<EmailList> getEmailList(ArrayList<CompanyEmailList> CompanyEmailIdList);
	public ArrayList<Email> getEmailList(int cid);
	//public int deleteEmail(String email,int cid);
	public int deleteEmail(int id);

}
