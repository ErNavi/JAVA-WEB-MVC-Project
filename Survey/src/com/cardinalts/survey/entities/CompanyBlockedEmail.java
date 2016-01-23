package com.cardinalts.survey.entities;

import java.util.Date;
/**
 * model page for CompanyBlockedEmail
 * @author Shashwati.Nigam
 *
 */
public class CompanyBlockedEmail {
private int id;
private int companyId;
private int emailListId;
private Date createDate;
private String createdBy;
private Date updateDate;
private String updatedBy;


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getCompanyId() {
	return companyId;
}
public void setCompanyId(int companyId) {
	this.companyId = companyId;
}
public int getEmailListId() {
	return emailListId;
}
public void setEmailListId(int emailListId) {
	this.emailListId = emailListId;
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
