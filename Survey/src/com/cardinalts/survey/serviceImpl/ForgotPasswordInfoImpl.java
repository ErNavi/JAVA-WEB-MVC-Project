package com.cardinalts.survey.serviceImpl;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import com.cardinalts.survey.datasource.ConnectionDB;
import com.cardinalts.survey.entities.Company;
import com.cardinalts.survey.entities.Email;
import com.cardinalts.survey.service.ForgotPasswordInfo;

public class ForgotPasswordInfoImpl implements ForgotPasswordInfo{
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String query = "";
	

	public ForgotPasswordInfoImpl()
	{
		super();
	}

	@Override
	public Company getPassword(Company company) {
		
		con = ConnectionDB.getConnectionDB();
		int sid=company.getSecurityQuestionId();
		String ans=company.getSecurityAnswer();
		String email=company.getEmail();
		Company result = null;
		
		System.out.println("*************in forget pass impl");
		System.out.println("sid:_"+sid);
		System.out.println("ans:_"+ans);
		System.out.println("email:_"+email);
		System.out.println("*************in forget pass impl");
		
		query="select * from company where email=?";
		
		try {
			System.out.println("in try top");
			ps = con.prepareStatement(query);
			ps.setString(1,email);
			rs = ps.executeQuery();
			System.out.println("in try : below rs");
			if(rs.next())
			{
				if(sid==rs.getInt("security_question_id"))
				{
					if(ans.equalsIgnoreCase(rs.getString("security_answer")))
					{ 
						String password=rs.getString("password");
						Company comp=new Company();
						comp.setEmail(email);
						comp.setPassword(password);
						//sendEmail(comp);
						result= comp;
						
					}
					else 
						result =null;
				}
				else
					result= null;
				
			}
			else
			{
				result= null;
			}
			
			
			
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
		
		return result;
	}
	Session session=null;
	
	/*
	 * method sends email with password to user
	 * @see com.cardinalts.survey.service.ForgotPasswordInfo#sendEmail(com.cardinalts.survey.entities.Company, java.util.Properties)
	 */
	@Override
	public int sendEmail(Company comp, final Properties prop) {
		// TODO Auto-generated method stub
		
		if(prop.getProperty("mail.authenticate").equals("true"))
		{
		System.out.println("in if loop");
		
		prop.put("mail.smtp.host",prop.getProperty("mail.smtp.host"));
		prop.put("mail.smtp.socketFactory.port",prop.getProperty("mail.smtp.socketFactory.port"));
		prop.put("mail.smtp.socketFactory.class",prop.getProperty("mail.smtp.socketFactory.class"));
		prop.put("mail.smtp.auth",prop.getProperty("mail.smtp.auth"));
		prop.put("mail.smtp.port",prop.getProperty("mail.smtp.port"));	
			 session = Session.getDefaultInstance(prop,new javax.mail.Authenticator() 
			{//inner class start
				protected PasswordAuthentication getPasswordAuthentication() 
				{
					return new PasswordAuthentication(prop.getProperty("mail.fromEmail"),prop.getProperty("mail.password"));//change accordingly
				}
			});

		 }//if close
		else
		{
		System.out.println("in else loop");
		System.out.println("mail.authenticate is false");
		
	    //Get the session object  
	      Properties properties = System.getProperties();  
	      properties.setProperty("mail.smtp.host", prop.getProperty("mail.smtp.host"));  
	       session = Session.getDefaultInstance(properties);  
	      
		}//else close
			
			//compose message
				
		try {
			System.out.println("in the try");

				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(prop.getProperty("mail.fromEmail")));//change accordingly
				
				String to=null;
				String subject="Password Recovery Information.";
				String mailText="<p><strong>Hi {Email}</strong>,</p><p>Your login Details are:</p><p>User Id: <strong>{Email}</strong></p><p>Password: <strong>{Password}</strong></p><p>please login using this to acces your account.</p><p>&nbsp;</p><p>PS: This is an auto generated mail, please <strong>do not</strong> reply.</p>";
	        	to=comp.getEmail();
	        	String email=comp.getEmail();
	        	String password=comp.getPassword();
	 
	        	message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
				message.setSubject(subject);
				 String str=mailText;
				 String newStr1=str.replaceAll("\\{Email\\}",email);
				 String newStr=newStr1.replaceAll("\\{Password\\}",password); 
				message.setText(newStr,"utf-8","html");
				//send message
				Transport.send(message); 

		return 1;
		}
		catch (MessagingException e) 
		{
			return 0;
		//	throw new RuntimeException(e);
			}
	}
	
}
