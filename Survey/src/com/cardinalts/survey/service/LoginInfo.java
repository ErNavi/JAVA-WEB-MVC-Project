package com.cardinalts.survey.service;

import com.cardinalts.survey.entities.Company;
/**
 * interface for LoginInfo
 * @author Shashwati.Nigam
 *
 */
public interface LoginInfo {
	public Company checkLoginDetail(String uid,String password);
}
