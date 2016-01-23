package com.cardinalts.survey.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.cardinalts.survey.datasource.ConnectionDB;
import com.cardinalts.survey.entities.SurveyDetails;
import com.cardinalts.survey.service.SurveyDetailInfo;
/**
 * Service class for SurveyDetailInfo
 * @author Shashwati.Nigam
 *
 */
public class SurveyDetailInfoImpl implements SurveyDetailInfo {


	/**
	 * saves survey details in DB
	 * inserts data from survey detail form into DB
	 */
	public int saveSurveyDetailInfo(SurveyDetails surveyDetails) {
		int result = 0;
		int result2 = 0;

		Connection con = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps = null;

		
		con = ConnectionDB.getConnectionDB();
		String query = "";
		String query2 = "";

		query = "INSERT INTO survey_detail (company_id, name, description, create_date, created_by) VALUES (?,?,?,?,?)";

		query2 = "UPDATE survey_detail SET name=?, description=?, update_date=?, updated_by=? WHERE id=?";
		try {
			
			if(surveyDetails.getId()==0){
			ps1 = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			ps1.setInt(1, surveyDetails.getCompanyId());
			ps1.setString(2, surveyDetails.getName());
			ps1.setString(3, surveyDetails.getDescription());
			ps1.setDate(4, new java.sql.Date(new Date().getTime()));
			ps1.setString(5, surveyDetails.getCreatedBy());
			result = ps1.executeUpdate();
			System.out.println("in survey impl-b4 rs INSERT if loop");
			
			}
			else {
				ps = con.prepareStatement(query2, Statement.RETURN_GENERATED_KEYS);
				
				ps.setString(1, surveyDetails.getName());
				ps.setString(2, surveyDetails.getDescription());
				ps.setDate(3, new java.sql.Date(new Date().getTime()));
				ps.setString(4, surveyDetails.getCreatedBy());
				ps.setInt(5, surveyDetails.getId());
				result = ps.executeUpdate();
				System.out.println("in survey impl-b4 rs UPDATE if loop");
			}
			
			
			if (result > 0) {
				System.out.println("in survey impl-after rs if loop");
				System.out.println("Row inserted");
			}
			try{
				System.out.println("in survey impl-after rs in try2");
				ResultSet generatedKeys = ps1.getGeneratedKeys();
				System.out.println("in survey impl- below resultset");
				if (generatedKeys.next()) {
					System.out.println("in survey impl..next()- value of generated key= "+generatedKeys.getInt(1));
					result2=generatedKeys.getInt(1);
	                
	            }
	            else {
	            	
	                throw new SQLException("Creating user failed, no ID obtained.");
	            }
			}
			catch (Exception e) {
				System.out.println("Data not inserted in surveyDetails");
			}
				
				System.out.println("Survey Detail successfully inserted.");
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
/**
 * Closing conections
 */
		finally {
			if (con != null) {
				ConnectionDB.closeResultSet();
				ConnectionDB.closePrepareStatement();
				ConnectionDB.closeConnection();
			}
		}
		return result2;
	}

	@Override
	
		// TODO Auto-generated method stub
		
		/**
		 * gets ArrayList of all Email List Ids from DB wit given company id
		 * @return ArrayList
		 * @param int 'company id'
		 */
	public ArrayList<SurveyDetails> getSurveyList(int cid) {

				Connection con = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				String query = "";
				con = ConnectionDB.getConnectionDB();
				ArrayList<SurveyDetails> surveyList = null;
				//query = "select email_list_id from company_email_list";
				query ="SELECT * FROM survey_detail where company_id=? order by create_date desc";
				SurveyDetails surveyListObj = null;
				surveyList = new ArrayList<SurveyDetails>();
				try {
					ps = con.prepareStatement(query);
					ps.setInt(1, cid);
					rs = ps.executeQuery();
					while (rs.next()) {
						//System.out.println("value inside rs(while): indx 1="+rs.getInt(8));
						surveyListObj = new SurveyDetails();
						surveyListObj.setId(rs.getInt("id"));
						surveyListObj.setName(rs.getString("name"));
						surveyListObj.setDescription(rs.getString("description"));
						surveyListObj.setCreateDate(rs.getDate("create_date"));
						surveyListObj.setUsed(rs.getInt("used"));
						
						surveyList.add(surveyListObj);
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
		
		return surveyList;
	}

	@Override
	/**
	 * Deletes the survey from DB
	 */
	public int deleteSurvey(int id) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		String query = "";
		int result=0;
		con = ConnectionDB.getConnectionDB();
		
		query="Delete from survey_detail where id=? and used=0";
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
		
		return result;
	}

	
	/**
	 * this updates the data in survey_detail table of DB
	 * @return int (id of updated row)
	 */
	@Override
	public int updateSurveyDetailInfo(SurveyDetails surveyDetails) {
//		int result = 0;
//		int result2 = 0;
//
//		Connection con = null;
//		PreparedStatement ps = null;
//
//		
//		con = ConnectionDB.getConnectionDB();
//		String query = "";
//
//		query = "UPDATE survey_detail SET name=?, description=?, update_date=?, updated_by=? WHERE id=?";
//
//		try {
//			ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//			
//			ps.setString(1, surveyDetails.getName());
//			ps.setString(2, surveyDetails.getDescription());
//			ps.setDate(3, new java.sql.Date(new Date().getTime()));
//			ps.setString(4, surveyDetails.getUpdatedBy());
//			ps.setInt(5, surveyDetails.getId());
//			result = ps.executeUpdate();
//			System.out.println("in survey impl-b4 rs if loop");
//			
//			
//			if (result > 0) {
//				System.out.println("in survey impl-after rs if loop");
//				System.out.println("Row inserted");
//			}
//			try{
//				System.out.println("in survey impl-after rs in try2");
//				ResultSet generatedKeys = ps.getGeneratedKeys();
//				System.out.println("in survey impl- below resultset");
//				if (generatedKeys.next()) {
//					System.out.println("in survey impl..next()- value of generated key= "+generatedKeys.getInt(1));
//					result2=generatedKeys.getInt(1);
//	                
//	            }
//	            else {
//	            	
//	                throw new SQLException("Creating user failed, no ID obtained.");
//	            }
//			}
//			catch (Exception e) {
//				System.out.println("Data not inserted in surveyDetails");
//			}
//				
//				System.out.println("Survey Detail successfully inserted.");
//			 
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
///**
// * Closing conections
// */
//		finally {
//			if (con != null) {
//				ConnectionDB.closeResultSet();
//				ConnectionDB.closePrepareStatement();
//				ConnectionDB.closeConnection();
//			}
//		}
//		return result2;
		return 0;
	}

}
