package com.cardinalts.survey.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cardinalts.survey.datasource.ConnectionDB;
import com.cardinalts.survey.entities.CompanyEmailList;
import com.cardinalts.survey.entities.Email;
import com.cardinalts.survey.service.ChooseEmailInfo;

public class ChooseEmailInfoImpl implements ChooseEmailInfo{

	
	
	/**
	 * gets ArrayList of all Email List Ids from DB wit given company id
	 * @return ArrayList
	 * @param int 'company id'
	 */
	public ArrayList<Email> getEmailList(int cid) {

			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			String query = "";
			con = ConnectionDB.getConnectionDB();
			ArrayList<Email> emailList = null;
			//query = "select email_list_id from company_email_list";
			query ="Select c.id, e.email, e.first_name, e.last_name  from company_email_list c inner join email_list e on e.id=c.email_list_id and c.company_id=?";
			Email emailIdObj = null;
			emailList = new ArrayList<Email>();
			try {
				ps = con.prepareStatement(query);
				ps.setInt(1, cid);
				rs = ps.executeQuery();
				while (rs.next()) {
					//System.out.println("value inside rs(while): indx 1="+rs.getInt(8));
					emailIdObj = new Email();
					emailIdObj.setId(rs.getInt("id"));
					emailIdObj.setEmail(rs.getString("email"));
					emailIdObj.setFirstName(rs.getString("first_name"));
					emailIdObj.setLastName(rs.getString("last_name"));
					emailList.add(emailIdObj);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				if (con != null) {
					ConnectionDB.closeResultSet();
					ConnectionDB.closePrepareStatement();
					ConnectionDB.closeConnection();
				}
			}
			return emailList;
		}

	@Override
	public ArrayList<CompanyEmailList> getCompanyEmailList(int cid) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * Deletes email from company_email_list Tablein DB
	 */
	
//	public int deleteEmail(String email,int id) {
	public int deleteEmail(int id){
		
		
//		the delete query goes here
		Connection con = null;
		PreparedStatement ps = null;
		String query = "";
		int result=0;
		con = ConnectionDB.getConnectionDB();
		
		query="Delete from company_email_list where id=?";
		try {
			ps = con.prepareStatement(query);
		
		ps.setInt(1, id);
		result = ps.executeUpdate();
		if(result>=0){
			System.out.println("row deleted successfully");
			
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("row NOT deleted");
			e.printStackTrace();
		}
		finally{
			 
			 if (con != null) {
					ConnectionDB.closeResultSet();
					ConnectionDB.closePrepareStatement();
					ConnectionDB.closeConnection();
				}

		 }
		return result;
	}


}
