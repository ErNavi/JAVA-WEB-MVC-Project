package com.cardinalts.survey.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.cardinalts.survey.datasource.ConnectionDB;
import com.cardinalts.survey.entities.UserResponse;
import com.cardinalts.survey.service.OpenClientSurveyInfo;

public class OpenClientSurveyInfoImpl implements OpenClientSurveyInfo {

@Override
	public String saveResponse(ArrayList<UserResponse> userResponse) {

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet res=null;
	PreparedStatement ps2 = null;
	String url = "";
	String query = "";
	int result = 0;

	query="insert into user_response (survey_detail_id, email_list_id, company_id," +
		" question_list_id, question_option_id, create_date, created_by)values(?,?,?,?,?,?,?)";
	String query2="select url from company where company_id=?";
	con = ConnectionDB.getConnectionDB();
	
	System.out.println("query:" + query);
	try {
		
		for(UserResponse userResponse1 : userResponse)
		
		{
			System.out.println(userResponse1.getSurveyDetailId());
			System.out.println(userResponse1.getEmailListId());
			System.out.println(userResponse1.getQuestionListId());
			System.out.println(userResponse1.getQuestionOptionId());
			
			
			ps = con.prepareStatement(query);
			ps2 = con.prepareStatement(query2);
			ps.setInt(1, userResponse1.getSurveyDetailId());
			ps.setInt(2, userResponse1.getEmailListId());
			ps.setInt(3, userResponse1.getCompanyId());
			ps.setInt(4, userResponse1.getQuestionListId());
			ps.setInt(5, userResponse1.getQuestionOptionId());
			ps.setDate(6, new java.sql.Date(new Date().getTime()));
			ps.setString(7, userResponse1.getEmailListId()+"");
		
			ps2.setInt(1, userResponse1.getCompanyId());
			res=ps2.executeQuery();
			url=res.getNString("url");
			result = ps.executeUpdate();
			
			if (result > 0) {
				System.out.println("userResponse successfully inserted.");
	
			}
			else {
				System.out.println("Data not inserted.");
			}
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
	return url;

		
	}

}
