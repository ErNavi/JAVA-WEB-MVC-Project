package com.cardinalts.survey.serviceImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.cardinalts.survey.datasource.ConnectionDB;
import com.cardinalts.survey.entities.Email;
import com.cardinalts.survey.entities.SurveyDetails;
import com.cardinalts.survey.service.SendEmailInfo;

public class SendEmailInfoImpl implements SendEmailInfo{
	Session session=null;
	@Override
	public int sendEmail(ArrayList<Email> emailList,SurveyDetails surveyDetails, int sid,int cid,final Properties prop){
		
		
		
		System.out.println("in the sendEmail(ArrayList<Email> emailList) ");
		System.out.println("**********starts emailList Arraylist Display******** ");
		int count=0;
		for (Email emailList1 : emailList) {
		      System.out.println("Email ID: = " + emailList1.getId());
		      System.out.println("count: = " + count++);
		      }
		
		
		System.out.println("**********ends emailList Arraylist Display******** ");
		
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
					
					
					//Email emailIdObj=null;
					/*Iterator<Email> itr = emailList.iterator();
			        while(itr.hasNext())*/
					int count2=0;
					for (Email emailIdObj : emailList)
			        {
						MimeMessage message = new MimeMessage(session);
						message.setFrom(new InternetAddress(prop.getProperty("mail.fromEmail")));//change accordingly
						String to=null;
						int eid=emailIdObj.getId();
						String link=prop.getProperty("mail.link")+"?sid="+sid+"&cid="+cid+"&eid="+eid;
			        	to=emailIdObj.getEmail();
			        	//System.out.println("in while to="+to);
			        
			           // message.addRecipient(Message.RecipientType.TO,new InternetAddress(itr.next())); 
			        	message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			        	System.out.println("in while to="+to);
			          //message.setSubject("from project send email"); 
			        	
						message.setSubject(surveyDetails.getSubject());
						//message.setText("Testing.......from project to send email");
						
						String email=emailIdObj.getEmail();//List.object.attributeValue
						 String description=surveyDetails.getDescription();
						 String sender=surveyDetails.getCreatedBy();//List.object.attributeValue
						 String firstName=emailIdObj.getFirstName();//List.object.attributeValue
						 String lastName=emailIdObj.getLastName(); //List.object.attributeValue
					//	 String str = "Hi {firstName} {lastName},\n <strong>This is our Survey:</strong>\n {description} \n \n Regards \n{Sender}";//List.object.attributeValue(i.e. template)
						 String str=surveyDetails.getTemplet();
						 String newStr1=str.replaceAll("\\{Email\\}",email);
						 String newStr2=newStr1.replaceAll("\\{Description\\}",description);
						 String newStr3=newStr2.replaceAll("\\{Link\\}",link);
						 String newStr4=newStr3.replaceAll("\\{Sender\\}",sender);
						 String newStr5=newStr4.replaceAll("\\{FirstName\\}",firstName);
						 
						 String newStr=newStr5.replaceAll("\\{LastName\\}",lastName);
						
						
						 
						
						message.setText(newStr,"utf-8","html");
						//send message
						Transport.send(message); 
						count2++;
						//System.out.println("message sent successfully...!!!");
					
			        }
			       
			        System.out.println("sid is="+sid);
					updateUsed(sid);
					System.out.println("counter2 value is="+count2);
					
				return 1;
				}
				catch (MessagingException e) 
				{
					return 0;
				//	throw new RuntimeException(e);
					}
	
	}//method close
	
	
	
	
	@Override
	public ArrayList<Email> getRecepientsList(int cid) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "";
		con = ConnectionDB.getConnectionDB();
		ArrayList<Email> emailList = null;
		
		query ="Select c.email_list_id, e.email, e.first_name, e.last_name  from company_email_list c inner join email_list e on e.id=c.email_list_id and c.company_id=?  where e.blocked=0 and e.valid_email=1";
		Email emailIdObj = null;
		emailList = new ArrayList<Email>();
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, cid);
			rs = ps.executeQuery();
			while (rs.next()) {
				emailIdObj = new Email();
				emailIdObj.setId(rs.getInt("email_list_id"));
				emailIdObj.setEmail(rs.getString("email"));
				emailIdObj.setFirstName(rs.getString("first_name"));
				emailIdObj.setLastName(rs.getString("last_name"));
				emailList.add(emailIdObj);
				
			//	emailList.add(rs.getString("email"));
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
	public SurveyDetails getSurveyInfo(int sid) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "";
		con = ConnectionDB.getConnectionDB();
		
		SurveyDetails surveyDetails=null;
		
		query ="SELECT description,template,created_by,subject FROM survey_detail s where id=?";
		try
		{
			ps = con.prepareStatement(query);
			ps.setInt(1, sid);
			rs = ps.executeQuery();
			while (rs.next())
			{
				surveyDetails=new SurveyDetails();
				surveyDetails.setDescription(rs.getString("description"));
				surveyDetails.setTemplet(rs.getString("template"));
				surveyDetails.setSubject(rs.getString("subject"));
				surveyDetails.setCreatedBy(rs.getString("created_by"));
				
					
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if (con != null) {
				ConnectionDB.closeResultSet();
				ConnectionDB.closePrepareStatement();
				ConnectionDB.closeConnection();
			}
		}
		
		
		
		return surveyDetails;
	}
	
	private void updateUsed(int sid) 
	{
		Connection con = null;
		PreparedStatement ps = null;
		String query = "";
		con = ConnectionDB.getConnectionDB();
		
		query="update survey_detail set used=1 where id=?";
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, sid);
			int result= ps.executeUpdate();
			if(result>0)
			{
				System.out.println("used colm in survey_detail updated");
			}
			else
			{
				System.out.println("used colm in survey_detail nottttt updated");
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

	}




	
	
}//class close
