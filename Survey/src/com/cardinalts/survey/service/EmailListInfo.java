package com.cardinalts.survey.service;


import java.util.ArrayList;
import java.util.Map;

import com.cardinalts.survey.entities.Email;
/**
 * interface for EmailListInfo
 * @author Shashwati.Nigam
 *
 */
public interface EmailListInfo {
public ArrayList<Email> saveEmailList(Map<String,Email> emailList, String c_name);
public int saveCompanyEmailList(ArrayList<Email> companyemaillist, int cid);
}
