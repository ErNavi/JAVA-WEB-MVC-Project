package com.cardinalts.survey.serviceImpl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cardinalts.survey.datasource.ConnectionDB;
import com.cardinalts.survey.entities.SurveyDetails;
import com.cardinalts.survey.entities.Template;
import com.cardinalts.survey.service.ChooseTempletInfo;

/**
 * This method gets the email Template from DB and checks last used Tamplate
 * @author Naveen.Singh
 * @return String of Template choosed by loged in company
 */
public class ChooseTempletInfoImpl implements ChooseTempletInfo {

	public String saveTempletDetail(Template template, int cid) {
		// TODO Auto-generated method stub
		

		Connection con=null;
		ResultSet rs=null;
		PreparedStatement ps=null;  
		System.out.println("choose impl cid: "+cid);
		int ui = template.getId();
		
		con = ConnectionDB.getConnectionDB();
		String result=null;
		String query = "";
		String query2="";
		       //query="Select * from survey_detail where id=?";
		query="Select * from template where id=?";
		query2="SELECT * FROM survey_detail WHERE company_id=? ORDER BY id DESC LIMIT 1,1";
		       try {
		    	   if(ui!=0){
				ps=con.prepareStatement(query);
				ps.setInt(1, ui);
				}
		    	   else{
		    		   ps=con.prepareStatement(query2);
		    		   ps.setInt(1, cid);
		    	   }
				    
				    rs=ps.executeQuery();
				    
				    if(rs.next())
				    {
				    	result=rs.getString("template");
				    	
				    }
				    System.out.println("return value: "+result);
				    
				    
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally{
				 
				 if (con != null) {
						ConnectionDB.closeResultSet();
						ConnectionDB.closePrepareStatement();
						ConnectionDB.closeConnection();
					}

			 }
			if(result==null){result="no last choosen template";}

		return result;
	}

	/**
	 * This method saves the email Template in survey_detail table of DB
	 * @author Naveen.Singh
	 * @return int value of no. of updated row
	 */
	public int submitTempletDetail(SurveyDetails surveyDetails) {

		Connection con=null;
		
		PreparedStatement ps=null;  
		
		con = ConnectionDB.getConnectionDB();
		int sid=surveyDetails.getId();
		int output=sid;
		System.out.println("********************************************Value of output in Choose TempIMPL: "+output);
		int result = sid;
		
		String query = "";
		String template = surveyDetails.getTemplet().trim();
		String subject=surveyDetails.getSubject();
		//System.out.println("trimmed templete: "+template);
		System.out.println("trimmed sid: "+sid);
		query="UPDATE survey_detail SET template=?, subject=? WHERE id=?";
		       try {
		    	  
				ps=con.prepareStatement(query);
				ps.setString(1, template);
				ps.setString(2, subject);
				ps.setInt(3, sid);
				
				result = ps.executeUpdate();
				    
				if (result > 0) {
					System.out.println("Template Detail successfully inserted.");
				} else {
					System.out.println("Template Data not inserted in surveyDetails");
				}
				    
				    return output;
  
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
		System.out.println("OUTPUTTTTTTTTTTTTTTTTTTTTTTTTTT: "+output);
			 return output;
		
	}



}
