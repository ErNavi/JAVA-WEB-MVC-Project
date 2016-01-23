package com.cardinalts.survey.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.cardinalts.survey.datasource.ConnectionDB;
import com.cardinalts.survey.entities.EmailList;
import com.cardinalts.survey.service.EmailListInfo;
/**
 * Service class for EmailListInfo
 * @author Shashwati.Nigam
 *
 */
public class EmailListInfoImpl implements EmailListInfo {
	Connection con = null;
	PreparedStatement ps = null;
	Statement st = null;
	ResultSet rs = null;
/**
 * takes Email List as Map and saves in DB
 * 
 */
	public int saveEmailList(Map<String, EmailList> emaillist) {
		int result = 0;

		// ======*BEGINS* : select query ends with result saved in
				// db_email[]begins================

		System.out.println("***********emaillImpl strarts: ");

		String[] db_email = new String[150];
		System.out.println("++++++++++++++++checking if null? : " + emaillist);
		if (emaillist != null) { // ******* if for null check opens
			con = ConnectionDB.getConnectionDB();

			try {
				st = con.createStatement();
				ResultSet rs = st
						.executeQuery("select email from survey_db.email_list");
				System.out.println("******above while 11 ");

				int rowcounter = 0;
				while (rs.next()) // Array of email list from db is made======
				{

					String db_email_2 = rs.getString("email");
					System.out.println("db_email_2 value is: " + db_email_2
							+ " for rowcounter = " + rowcounter);
					db_email[rowcounter] = db_email_2;
					rowcounter++;
				}

				// ======*END*: select query with result saved in db_email[]
				// ends================

				System.out.println("rowcounter value after rs.next is : "
						+ rowcounter);
				for (int l = 0; l < rowcounter; l++)
					System.out.println("db_email array value " + l + " = "
							+ db_email[l]);

				Map<String, String> toRemove = new HashMap<String, String>(); // map
																				// to
																				// store
																				// the
																				// repeated
																				// values

				// =========*BEGINS* : loop for Checking of already existing
				// email==============

				for (String key1 : emaillist.keySet()) { // loop will run for
															// all keys(i.e. all
															// emails entered)

					System.out.println("**********inside for & key is : "
							+ key1);
					int matchEmailCounter = 0;

					for (int x = 0; x < rowcounter; x++) // for current key, db
															// email are
															// iterated
					{

						if (key1.equals(db_email[x])) {
							System.out
									.println("******inside if : db_email is = "
											+ db_email[x] + " and key is : "
											+ key1);
							toRemove.put(key1, "already exist");
							matchEmailCounter++; // count of similar email
													// values

							System.out.println("valur mail counter is : "
									+ matchEmailCounter);
							break;
						}
					}

				}
				// ===========*END*: loop for Checking of already existing
				// email===========

				// ===========*BEGINS* : Deletion of repeated email
				// begins==========================

				for (String toRemoveKey : toRemove.keySet()) {

					emaillist.remove(toRemoveKey);
				}

				// =========== *END*: Deletion of repeated
				// email==========================

			}

			// ==============*END*: of try
			// block==========================================
			catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// =============*BEGINS* : insert query for Inserting fynal data
			// into DB from emaillist Map (eddited map) ==============
			String query = "";
			query = "INSERT INTO survey_db.email_list (email, first_name,last_name, create_date, created_by) VALUES (?, ?, ?, ?,?)";

			try {

				con.setAutoCommit(false); // setting auto commit false to
											// increase performace of Query

				ps = con.prepareStatement(query);

				for (String key : emaillist.keySet()) {
					System.out.println("**********key being inserted is: "
							+ key);
					EmailList emailSet = emaillist.get(key);
					ps.setString(1, emailSet.getEmail());
					ps.setString(2, emailSet.getFirstName());
					ps.setString(3, emailSet.getLastName());
					ps.setDate(4, new java.sql.Date(new Date().getTime()));
					// ps.setString(5, company.getCreatedBy());
					ps.setString(5, "Default is hr");

					ps.addBatch(); // this will add it to batch

				}
				ps.executeBatch();
				con.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (con != null) {
					ConnectionDB.closeResultSet();
					ConnectionDB.closePrepareStatement();
					ConnectionDB.closeConnection();
				}
			}

		} // ******* if for null check opens

		// =============*END*: of insert query for Inserting fynal data into DB
		// from emaillist Map (eddited map) ==============

		return result;

	}

}