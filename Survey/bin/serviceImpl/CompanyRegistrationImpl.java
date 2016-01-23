package com.cardinalts.survey.serviceImpl;

import com.cardinalts.survey.datasource.*;
import com.cardinalts.survey.entities.Company;
import com.cardinalts.survey.entities.SecurityQuestion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.cardinalts.survey.service.*;

/**
 * Service class for CompanyRegistration 
 * Servlet implementation class CompanyRegistration
 */
public class CompanyRegistrationImpl implements CompanyRegistration {
/**
 * constructor
 */
	public CompanyRegistrationImpl() {
		super();

	}
/**
 * saves company detail
 */
	public int saveCompanyDetail(Company company) {

		Connection con = null;
		PreparedStatement ps = null;
		String query = "";

		int result = 0;

		System.out.println("we are at top of serviceImpl ***!!!!!");

		con = ConnectionDB.getConnectionDB();
		query = "INSERT INTO survey_db.company (name, password, email, security_question_id, security_answer, membership, create_date, created_by) VALUES (?, ?, ?, ?, ?, ?,?, ?)";

		System.out.println("query:" + query);
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, company.getName());
			ps.setString(2, company.getPassword());
			ps.setString(3, company.getEmail());
			ps.setInt(4, company.getSecurityQuestionId());
			ps.setString(5, company.getSecurityAnswer());
			ps.setInt(6, company.getMembership());
			ps.setDate(7, new java.sql.Date(new Date().getTime()));
			ps.setString(8, company.getCreatedBy());
			System.out.println("we are at mid of serviceImpl");
			result = ps.executeUpdate();
			if (result > 0) {
				System.out.println("Company Detail successfully inserted.");

			} else {
				System.out.println("Data not inserted.");
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
		return (1);

	}
	
/**
 * gets ArrayList of Security Question
 * @return ArrayList
 */
	public ArrayList<SecurityQuestion> getSecurityQuestion() {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "";
		con = ConnectionDB.getConnectionDB();
		ArrayList<SecurityQuestion> questionList = null;
		query = "select * from security_question";
		SecurityQuestion sq = null;
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				if (questionList == null)
					questionList = new ArrayList<SecurityQuestion>();
				sq = new SecurityQuestion();

				sq.setId(rs.getInt("id"));
				sq.setDetail(rs.getString("detail"));
				questionList.add(sq);

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
		return questionList;
	}

}
