package com.cardinalts.survey.serviceImpl;

import com.cardinalts.survey.datasource.*;
import com.cardinalts.survey.entities.Company;
import com.cardinalts.survey.entities.SecurityQuestion;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

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
	public String saveCompanyDetail(Company company) {

	int check=checkRegistration(company);
		
		if(check==1)
		{
			
			return null;
			
		}
		else
		{
			Connection con = null;
			PreparedStatement ps = null;
			String query = "";

			int result = 0;

			System.out.println("we are at top of serviceImpl ***!!!!!");

			con = ConnectionDB.getConnectionDB();
			query = "INSERT INTO company (name, password, email, security_question_id, " +
					"security_answer, membership, create_date, created_by, url)" +
					" VALUES (?, ?, ?, ?, ?, ?,?, ?,?)";

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
				ps.setString(9, company.getUrl());
				//ps.setString(10, company.getLogo());
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
			return "not exists";
		}
	}//save method close

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

public int checkRegistration(Company company)
	
	{
		Connection con = null;
		PreparedStatement ps = null;
		String query = "";
		ResultSet rs = null;
		con = ConnectionDB.getConnectionDB();
		query="Select * from company where email=?";
		int ret=0;
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, company.getEmail());
			rs = ps.executeQuery();
			if(rs.next())
			{
				ret= 1;
			}
			
			else
				ret = 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally{
			 
			 if (con != null) {
					ConnectionDB.closeResultSet();
					ConnectionDB.closePrepareStatement();
					ConnectionDB.closeConnection();
				}

		 }
	
		
		return ret;
		
	}


/*
 * for uploading of logo
 * @see com.cardinalts.survey.service.CompanyRegistration#saveCompanyLogo(javax.servlet.http.HttpServletRequest)
 */
@Override
public String saveCompanyLogo(HttpServletRequest request) {
	// TODO Auto-generated method stub
	String savedPath="";

	String SAVE_DIR="images";
	String savePath = "C:" + File.separator + SAVE_DIR;
    File fileSaveDir=new File(savePath);
    if(!fileSaveDir.exists()){
        fileSaveDir.mkdir();
    }
		String firstName=request.getParameter("firstname");
		String lastName=request.getParameter("lastname");
		Part part=request.getPart("file");
		String fileName=extractFileName(part);
		part.write(savePath + File.separator + fileName);
	
	return savedPath;

}
	


}
