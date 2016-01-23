package com.cardinalts.survey.serviceImpl;

import java.sql.*;

import com.cardinalts.survey.datasource.ConnectionDB;
import com.cardinalts.survey.entities.Company;
import com.cardinalts.survey.service.LoginInfo;
/**
 * Service class for LoginInfo
 * @author Shashwati.Nigam
 *
 */
public class LoginInfoImpl implements LoginInfo {

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String query = "";
/**
 * Takes email and Password and validates it.
 */
	public Company checkLoginDetail(String email, String password) {

		con = ConnectionDB.getConnectionDB();
		query = "select * from company where email=? and password=?";
		try {
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				Company company = new Company();
				company.setId(rs.getInt("id"));
				company.setEmail(email);
				company.setPassword(password);
				company.setCreatedBy(rs.getString("created_by"));
				return company;
			} else {
				return null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			if (con != null) {
				ConnectionDB.closeResultSet();
				ConnectionDB.closePrepareStatement();
				ConnectionDB.closeConnection();
			}
		}
		return null;
	}

}
