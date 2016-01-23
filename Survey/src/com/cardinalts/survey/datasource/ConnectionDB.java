package com.cardinalts.survey.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionDB {

	private static Connection connection;
	private static PreparedStatement prepareStatement;
	private static ResultSet resultSet;

	/** Uses JNDI and Datasource (preferred style). */
	public static Connection getJNDIConnection(String contextPath) {

		Context initialContext = null;
		String DATASOURCE_CONTEXT = "java:/survey_db"; //this is the name of DB dir.
		try {
			initialContext = new InitialContext();
			DataSource datasource = (DataSource) initialContext
					.lookup(DATASOURCE_CONTEXT);

			if (datasource != null) {
				connection = datasource.getConnection();
				System.out.println("connected");
			} else {
				System.out.println("Failed to lookup datasource.");
			}
		} catch (NamingException ex) {
			System.out.println("Cannot get connection: " + ex);
		} catch (SQLException ex) {
			System.out.println("Cannot get connection: " + ex);
		}
		return connection;
	}

	public static Connection getConnectionDB() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			// connection=DriverManager.getConnection("jdbc:mysql://192.168.1.201:3306/survey_db","root","root");
			connection = getJNDIConnection("");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return connection;
	}

	public static void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void closePrepareStatement() {
		if (prepareStatement != null) {
			try {
				prepareStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void closeResultSet() {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String a[]) throws Exception {
		Connection c = getJNDIConnection("file:D:\\java\\jboss-5.1.0.GA\\server\\default\\deploy\\surveydb-ds");
		System.out.println("Connection String is =" + c.getCatalog());

	}

}