package com.cardinalts.survey.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	Connection con = null;
	PreparedStatement ps1 = null;
	PreparedStatement ps2 = null;
	ResultSet rs = null;
	String query = "";
	/**
	 * saves survey details in DB
	 * 
	 */
	public int saveSurveyDetailInfo(SurveyDetails surveyDetails) {
		int result = 0;

		System.out.println("at the top of survey impl");

		con = ConnectionDB.getConnectionDB();
		query = "INSERT INTO survey_db.survey_detail (company_id, name, description, create_date, created_by) VALUES (?,?,?,?,?)";

		try {
			ps1 = con.prepareStatement(query);
			ps1.setInt(1, surveyDetails.getCompanyId());
			ps1.setString(2, surveyDetails.getName());
			ps1.setString(3, surveyDetails.getDescription());
			ps1.setDate(4, new java.sql.Date(new Date().getTime()));
			ps1.setString(5, surveyDetails.getCreatedBy());

			System.out.println("at the below of survey impl preparedstatement");

			result = ps1.executeUpdate();
			if (result > 0) {
				System.out.println("Survey Detail successfully inserted.");
			} else {
				System.out.println("Data not inserted in surveyDetails");
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
		return result;
	}

}
