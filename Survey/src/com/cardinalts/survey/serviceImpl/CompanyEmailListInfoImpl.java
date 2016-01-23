package com.cardinalts.survey.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.cardinalts.survey.datasource.ConnectionDB;
import com.cardinalts.survey.entities.Email;
import com.cardinalts.survey.service.EmailListInfo;

/**
 * 
 * @author Naveen.Singh
 * this is used for inserting Email List from "emailListInfo.jsp" in company_email_lsit (DB table)
 */

public class CompanyEmailListInfoImpl implements EmailListInfo {

	
	/**
	 * used for mapping all email_lsit (DB) data in company_email_lsit (DB) 
	 * @param ArrayList
	 * @return int
	 */
	public int saveCompanyEmailList(ArrayList<Email> companyemaillist, int cid) {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		try {

			System.out.println("**********starts Arraylist Display******** ");
			
			for (Email companyemaillist1 : companyemaillist) {
			      System.out.println("Number Id = " + companyemaillist1.getId());
			      System.out.println("Number cid = " + cid);}
			
			System.out.println("**********ends Arraylist Display******** ");
			
			
			con = ConnectionDB.getConnectionDB();
			String query = "";

			query = "insert into company_email_list (email_list_id ,company_id ,create_date ,created_by) values(?,?,?, ?)";

			 for (Email companyemaillist1 : companyemaillist) {
			    
			try {
				ps = con.prepareStatement(query);
				
				ps.setInt(1, companyemaillist1.getId());
				ps.setInt(2, cid);
				ps.setDate(3, new java.sql.Date(new Date().getTime()));
				ps.setString(4, companyemaillist1.getCreatedBy());
				result = ps.executeUpdate();
				System.out.println("in CompanyEmailListInfoImpl impl-b4 rs if loop");
				if (result > 0) {
					System.out.println("in CompanyEmailListInfoImpl impl-after rs if loop");
					System.out.println("Row inserted");
					}
				}
			catch (Exception e) {
				}
			
			 }//for loop ends
			return 0;
			}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (con != null) {
				ConnectionDB.closeResultSet();
				ConnectionDB.closePrepareStatement();
				ConnectionDB.closeConnection();
			}
		}
		return 0;
	
	}
	

	public ArrayList<Email> saveEmailList(Map<String, Email> emailList, String c_name) {
		return null;
	}



}
