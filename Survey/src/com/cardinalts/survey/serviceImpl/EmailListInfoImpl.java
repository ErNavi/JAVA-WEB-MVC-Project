package com.cardinalts.survey.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.cardinalts.survey.datasource.ConnectionDB;
import com.cardinalts.survey.entities.Email;
import com.cardinalts.survey.service.EmailListInfo;

/**
 * Service class for EmailListInfo
 * 
 * @author Shashwati.Nigam
 * db_email[] : holds all emails from DB
 * returs the the arraylist of all ids of all entered emails from email_list table in DB
 */
public class EmailListInfoImpl implements EmailListInfo {
	

	/**
	 * takes Email List as Map and saves in DB table email_list
	 * @return ArrayList
	 */
	public ArrayList<Email> saveEmailList(Map<String, Email> emaillist, String c_name) {
		ArrayList<Email> AllChoosenEmailList = null;
		Connection con = null;
		PreparedStatement ps = null;
		Statement st = null;
		ResultSet rs = null;
		Email all_db_email = null;
		Email all_Choosen_email = null;
		Email all_Choosen_email1=null;
		Email emailSet=null;
		Email emailObj=null;
		
	
		//Initializing HashMap "db_email_map" for storing DB emails
		Map<String, Email> db_email_map = new HashMap<String, Email>();
		
	
		//Initializing ArrayList "AllChoosenEmailList" for output
		if (AllChoosenEmailList == null)
		AllChoosenEmailList = new ArrayList<Email>();
		all_Choosen_email = new Email();
		
		if (emaillist != null) { //if for null check opens
			
			con = ConnectionDB.getConnectionDB();

// *BEGINS* : Block to save prexisting DB emails in output Arraylist and remove it from input map
			
			try {
				st = con.createStatement();
				rs = st.executeQuery("select id,email from email_list");
				int rowcounter = 0;
				System.out.println("===============while opens==========");
				while (rs.next()) // Array of email list from db is made in db_email[]
				{
				//storing all DB emails in "db_email_map" map
					all_db_email = new Email();
					all_db_email.setId(rs.getInt("id"));
					all_db_email.setEmail(rs.getString("email"));
					all_db_email.setCreatedBy(c_name);
					db_email_map.put(rs.getString("email"), all_db_email);
				
					rowcounter++;

				}
System.out.println("===============while closes==========");

			// map to store the repeated values
				Map<String, String> toRemove = new HashMap<String, String>(); 

			// *BEGINS* : loop for Checking of already existing email
				int loop_count=0;
				for (String key1 : emaillist.keySet()) { 
					// loop will run for all keys from USER(i.e. all emails entered by USER)

					System.out.println("=========Loop no. "+loop_count+"========");
					System.out.println("value of key1 i.e. INPUT MAP key : "+key1);
					int matchEmailCounter = 0;
 
					for (String key2 : db_email_map.keySet()){
						// loop will run for all keys from DB(i.e. all emails from DB)
						
						if (key1.equals(key2)) {
					//adding the repeated emails to AllChoosenEmailList(i.e.to output)
							emailObj = new Email();
							all_Choosen_email1=new Email();
							
							emailObj = db_email_map.get(key2);
							all_Choosen_email1.setId(emailObj.getId());
							all_Choosen_email1.setEmail(emailObj.getEmail());
							all_Choosen_email1.setCreatedBy(c_name);
							AllChoosenEmailList.add(all_Choosen_email1);
					//adding the repeated emails to "toRemove" map for deleting these values from "emaillist" map
							toRemove.put(key1, "already exist");
							matchEmailCounter++; //count of similar email values
							break;
						}
					}
					loop_count++;
				}

				
			// *END*: loop for Checking of already existing email

			// *BEGINS* : Deletion of repeated email begins
				for (String toRemoveKey : toRemove.keySet()) {
					
					emaillist.remove(toRemoveKey);
				}
			// *END*: Deletion of repeated email
			}
			catch (SQLException e1) {

				e1.printStackTrace();
			}
			//"emaillist" map contains new emails not in DB
			//"AllChoosenEmailList" arraylist contains emails prexisting in DB
// *END* : Block to save prexisting DB emails in output Arraylist and remove it from input map
			
			
			

// *BEGINS* : block to Inserting final data into DB and Add new email in Output
			String query = "";
			query = "INSERT INTO email_list (email, first_name,last_name, create_date, created_by) VALUES (?, ?, ?, ?,?)";
			int result = 0;
			int result2 = 0;
			try {
				for (String key : emaillist.keySet()) {
					ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

					emailSet = emaillist.get(key);
					ps.setString(1, emailSet.getEmail());
					ps.setString(2, emailSet.getFirstName());
					ps.setString(3, emailSet.getLastName());
					ps.setDate(4, new java.sql.Date(new Date().getTime()));
					// ps.setString(5, company.getCreatedBy());
					ps.setString(5, "Default is hr");

					result = ps.executeUpdate();
					if (result > 0) {
						System.out.println("Row inserted");
					}
					try{
						ResultSet generatedKeys = ps.getGeneratedKeys();
						if (generatedKeys.next()) {
							result2=generatedKeys.getInt(1);
							all_Choosen_email=new Email();
						//adding email fed in DB and its id to output arraylist "AllChoosenEmailList"
							all_Choosen_email.setId(result2);
							all_Choosen_email.setEmail(emailSet.getEmail());
							all_Choosen_email.setCreatedBy(c_name);
							AllChoosenEmailList.add(all_Choosen_email);
							
			            }
			            else {
			            	
			                throw new SQLException("Creating user failed, no ID obtained.");
			            }
					}
					catch (Exception e) {
						System.out.println("Data not inserted in surveyDetails");
					}
				}

				
			} catch (SQLException e) {

				e.printStackTrace();
			} finally {
				if (con != null) {
					ConnectionDB.closeResultSet();
					ConnectionDB.closePrepareStatement();
					ConnectionDB.closeConnection();
				}
			}

		}

// *END*: block to Inserting final data into DB and Add new email in Output

		return AllChoosenEmailList;

	}



	public int saveCompanyEmailList(ArrayList<Email> companyemaillist, int cid) {
		// TODO Auto-generated method stub
		return 0;
	}
}