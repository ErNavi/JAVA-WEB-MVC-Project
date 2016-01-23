package com.cardinalts.survey.entities;

import java.util.Date;

/**
 * model page for EmailList
 * @author Shashwati.Nigam
 *
 */
public class Email {

	private int id;
	private String email;
	private String firstName;
	private String lastName;
	private Boolean blocked;
	private Boolean validEmail;
	private Date createDate;
	private String createdBy;
	private Date updateDate;
	private String updatedBy;
	private int noOfEmail;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Boolean getBlocked() {
		return blocked;
	}
	public void setBlocked(Boolean blocked) {
		this.blocked = blocked;
	}
	public Boolean getValidEmail() {
		return validEmail;
	}
	public void setValidEmail(Boolean validEmail) {
		this.validEmail = validEmail;
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
	public int getNoOfEmail() {
		return noOfEmail;
	}
	public void setNoOfEmail(int noOfEmail) {
		this.noOfEmail = noOfEmail;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
