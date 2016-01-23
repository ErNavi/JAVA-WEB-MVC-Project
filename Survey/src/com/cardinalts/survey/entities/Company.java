package com.cardinalts.survey.entities;

import java.util.Date;
/**
 * model page for Company
 * @author Shashwati.Nigam
 *
 */
public class Company {
private int id;
private String name;
private String password;
private String email;
private String userType;
private int securityQuestionId;
private String securityAnswer;
private int membership;
private Date createDate;
private String createdBy;
private Date updateDate;
private String updatedBy;
private String logo;

/**
 * @return the logo
 */
public String getLogo() {
	return logo;
}
/**
 * @param logo the logo to set
 */
public void setLogo(String logo) {
	this.logo = logo;
}
/**
 * @return the url
 */
public String getUrl() {
	return url;
}
/**
 * @param url the url to set
 */
public void setUrl(String url) {
	this.url = url;
}
private String url;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getUserType() {
	return userType;
}
public void setUserType(String userType) {
	this.userType = userType;
}
public int getSecurityQuestionId() {
	return securityQuestionId;
}
public void setSecurityQuestionId(int securityQuestionId) {
	this.securityQuestionId = securityQuestionId;
}
public String getSecurityAnswer() {
	return securityAnswer;
}
public void setSecurityAnswer(String securityAnswer) {
	this.securityAnswer = securityAnswer;
}
public int getMembership() {
	return membership;
}
public void setMembership(int membership) {
	this.membership = membership;
}
public Date getCreateDate() {
	return createDate;
}
public void setCreateDate(Date createDate) {
	this.createDate = createDate;
}
public String getCreatedBy() {
	return createdBy;
}
public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
}
public Date getUpdateDate() {
	return updateDate;
}
public void setUpdateDate(Date updateDate) {
	this.updateDate = updateDate;
}
public String getUpdatedBy() {
	return updatedBy;
}
public void setUpdatedBy(String updatedBy) {
	this.updatedBy = updatedBy;
}



}
