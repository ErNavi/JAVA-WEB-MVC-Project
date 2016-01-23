 package com.cardinalts.survey.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cardinalts.survey.datasource.ConnectionDB;
import com.cardinalts.survey.dto.PreviewOptions;
import com.cardinalts.survey.dto.PreviewSurveyQuestion;
import com.cardinalts.survey.entities.QuestionList;
import com.cardinalts.survey.entities.QuestionOption;
import com.cardinalts.survey.service.SurveyQuestions;

public class AddQuestionInfoImpl implements SurveyQuestions {

	
	public Integer saveSurveyQuestions(QuestionList questionList) {
		
		Connection con = null;
		PreparedStatement ps = null,ps2=null;
		int result = 0;
		int result2 = 0;
		
		
		int listSize=questionList.getQuestionOptionList().size();
		System.out.println("ListSize : "+listSize);
		
		con = ConnectionDB.getConnectionDB();
		int genereatedKeys1=0,genereatedKeys2=0;
		String query="insert into question_list (description,answer_type, create_date,created_by,company_id,survey_detail_id)values(?,?,?,?,?,?)";
		 try {
			 //save Data in  question_list
			ps =con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, questionList.getDescription());
			ps.setInt(2, questionList.getAnswerType());
			ps.setDate(3, new java.sql.Date(new Date().getTime()));
			ps.setString(4, questionList.getCreatedBy());
			ps.setInt(5, questionList.getCompanyDetail().getCompanyId());
			ps.setInt(6, questionList.getSurveyDetails().getId());

			result = ps.executeUpdate();
			
			ResultSet r_generatedKeys = ps.getGeneratedKeys();
			if (r_generatedKeys.next()) {
				genereatedKeys1=r_generatedKeys.getInt(1);
				System.out.println("genereatedKeys1 : "+genereatedKeys1+""+result+""+result2);
			
			//save Data in  question_option table
			  ps =	con.prepareStatement("insert into question_option"+
						"(question_list_id,description, order1,created_by,create_date,correct_answer) values" +
				 		"(?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			  ps2 =	con.prepareStatement("insert into survey_right_answer"+
						"(question_list_id,question_option_id,survey_detail_id,company_id,created_by,create_date) values" +
				 		"(?,?,?,?,?,?)");
 
			  int order =1;
			  for(QuestionOption questionOption:questionList.getQuestionOptionList()){
				  System.out.println("INside Question Option List");
					ps.setInt(1, genereatedKeys1);
					ps.setString(2, questionOption.getDescription());
					ps.setInt(3, order++);
					ps.setString(4, questionList.getCreatedBy());
					ps.setDate(5, new java.sql.Date(new Date().getTime()));
					System.out.println("value of correcteed ans "+questionOption.isCorrectAnswer());
					ps.setBoolean(6,questionOption.isCorrectAnswer());
					
					
					
					result2 = ps.executeUpdate();
					ResultSet r2_generatedKeys = ps.getGeneratedKeys();
					if (r2_generatedKeys.next()) {
						genereatedKeys2=r2_generatedKeys.getInt(1);
						System.out.println("genereatedKeys2 : "+genereatedKeys2);
					
					
					if (questionOption.isCorrectAnswer()){
						ps2.setInt(1, genereatedKeys1);
						ps2.setInt(2, genereatedKeys2);
						ps2.setInt(3, questionList.getSurveyDetails().getId());
						ps2.setInt(4, questionList.getCompanyDetail().getCompanyId());
						ps2.setString(5, questionList.getCreatedBy());
						ps2.setDate(6, new java.sql.Date(new Date().getTime()));
						ps2.executeUpdate();
					}
					//int res=ps.executeUpdate();
			  	}
			  }
		 }
			
			
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
		return genereatedKeys1;
	}
	
	/**
	 * deletes the question from question list n related child data
	 */
	
	@Override
	public Integer removeSurveyQuestions(int qid) {
	
		System.out.println("in top of removeSurveyQuestions");
		
		Connection con = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		String query1 = "";
		String query2 = "";
		String query3 = "";
		int result1=0;
		int result2=0;
		int result3=0;
		con = ConnectionDB.getConnectionDB();
		
		query1="Delete from question_list where id=?";
		query2="Delete from question_option where question_list_id=?";
		query3="Delete from survey_right_answer where question_list_id=?";
		try {
			
			ps3 = con.prepareStatement(query3);
			ps3.setInt(1, qid);
			result3 = ps3.executeUpdate();
		
		if(result3>0)
		{
			ps2 = con.prepareStatement(query2);
			ps2.setInt(1, qid);
			result2 = ps2.executeUpdate();	
			
		if(result2>0){
			ps1 = con.prepareStatement(query1);
			ps1.setInt(1, qid);
			result1 = ps1.executeUpdate();
			
			if(result1>0)
			{
				System.out.println("data  deleted from question_list");	
				
			}
			else
			{
				System.out.println("data not  deleted from question_list");	
			}
		}
		else
		{
			System.out.println("data not deleted from question_option");
		}
		}
		else
		{
			System.out.println("data not deleted from survey_right_answer");
		}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("row NOT deleted");
			e.printStackTrace();
		}
		
		finally {
			if (con != null) {
				ConnectionDB.closeResultSet();
				ConnectionDB.closePrepareStatement();
				ConnectionDB.closeConnection();
			}
		}
		
		System.out.println("at the end of removeSurveyQuestions");
		return 0;
	}

	/**
	 * to show the survey question.
	 */
	
	
	public List<PreviewSurveyQuestion> getSurveyById(int companyId,int surveyId){

		 Connection con = null;
		 PreparedStatement ps = null;
		 List<PreviewSurveyQuestion> list=null;
		 PreviewSurveyQuestion previewSurvey =null;
		 PreviewOptions previewOptions =null;
		 List<PreviewOptions> po=null;
		 try{
		 con = ConnectionDB.getConnectionDB();
		 String query="select ql.company_id as company_id ,ql.survey_detail_id as survey_id,ql.id as question_id ," +
		 " ql.description as question,qo.description as option_description,qo.id as option_id," +
		 " qo.correct_answer as correct_answer from question_list ql,question_option qo where ql.company_id=? and ql.survey_detail_id=? and" +
		 " qo.question_list_id=ql.id order by qo.id asc ,order1 asc";
		 ps =con.prepareStatement(query);
		 ps.setInt(1, companyId);
		 ps.setInt(2, surveyId);
		 ResultSet rs =ps.executeQuery();
		 int questionId=0;


		 
		 int countquestions=0;
		 while (rs.next()){
		 countquestions=countquestions+1;
		 if (list == null ) list=new ArrayList<PreviewSurveyQuestion>();

		 if (questionId == 0 ){
		 previewSurvey = new PreviewSurveyQuestion();
		 previewSurvey.setCompanyId(rs.getInt("company_id"));
		 previewSurvey.setSurveyId(rs.getInt("survey_id"));
		 previewSurvey.setQuestionId(rs.getInt("question_id"));
		 previewSurvey.setQuestion(rs.getString("question"));
		 po = new ArrayList<PreviewOptions>();
		 previewSurvey.setList(po);
		 list.add(previewSurvey);
		 }

		 if (questionId != 0){
		 if (rs.getInt("question_id") != questionId){

		 previewSurvey = new PreviewSurveyQuestion();
		 previewSurvey.setCompanyId(rs.getInt("company_id"));
		 previewSurvey.setSurveyId(rs.getInt("survey_id"));
		 previewSurvey.setQuestionId(rs.getInt("question_id"));
		 previewSurvey.setQuestion(rs.getString("question"));
		 System.out.println("Impl######"+rs.getString("question"));
		 po = new ArrayList<PreviewOptions>();
		 previewSurvey.setList(po);
		 list.add(previewSurvey);

		 }else{

		 }
		 }
		 previewOptions = new PreviewOptions();
		 previewOptions.setCorrect(rs.getString("correct_answer"));
		 previewOptions.setOption(rs.getString("option_description"));
		 previewOptions.setOptionId(rs.getInt("option_id"));
		 po.add(previewOptions);
		 questionId = rs.getInt("question_id");

		 }
		 if (countquestions == 1){
		 }

		 }catch (Exception e) {

		 // TODO: handle exception
		 }
		 finally{
			 
			 if (con != null) {
					ConnectionDB.closeResultSet();
					ConnectionDB.closePrepareStatement();
					ConnectionDB.closeConnection();
				}

		 }
		 System.out.println("about to end*********************************************");
		//System.out.println(list.size());

		 return list;
		 }
	

}
