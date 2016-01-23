package com.cardinalts.survey.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cardinalts.survey.datasource.ConnectionDB;
import com.cardinalts.survey.entities.SurveyDetails;
import com.cardinalts.survey.service.ChooseTempletUserInfo;

/**
 * This method gets the count of Survey by loged in company
 * @author Naveen.Singh
 * @return int value of No.of serveys made by loged in company
 */
public class ChooseTempletUserInfoImpl implements ChooseTempletUserInfo {

	Connection con = null;
	PreparedStatement ps = null;
	Statement st=null;
	ResultSet rs = null;
	
	public int getUserSurveyCount(SurveyDetails surveyDetails) {
		
		int result = 0;
		con = ConnectionDB.getConnectionDB();
		String query = "";
		query="SELECT count(company_id) as count_cid From survey_detail WHERE company_id=? group by company_id";
		
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, surveyDetails.getCompanyId());
			rs=ps.executeQuery();
			if(rs.next())
			    {
			    	result=rs.getInt("count_cid");
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
		return result;
	}

}
