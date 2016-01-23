package com.cardinalts.survey.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import com.cardinalts.survey.datasource.ConnectionDB;
import com.cardinalts.survey.dto.PreviewSurveyQuestion;
import com.cardinalts.survey.entities.QuestionList;
import com.cardinalts.survey.service.SurveyQuestions;

public class SurveyQuestionsImpl implements SurveyQuestions {

	
	public Integer saveSurveyQuestions(QuestionList questionList) {
		
		Connection con = null;
		PreparedStatement ps = null,ps2=null;
		
		con = ConnectionDB.getConnectionDB();
		int genereatedKeys1=0,genereatedKeys2=0;
		String query="insert into question_list (description,answer_type, create_date,created_by,company_id,survey_detail_id)values(?,?,?,?,?,?)";
		 try {
			 //save Data in  question_list
			  ps =	con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			//ps.setString(1, questionList.getDescription());
			  ps.setString(1,"heloozzzzzzz"+genereatedKeys2+"and"+ps2);
			ps.setInt(2, questionList.getAnswerType());
			ps.setDate(3, new java.sql.Date(new Date().getTime()));
			ps.setString(4, questionList.getCreatedBy());
			ps.setInt(5, questionList.getCompanyDetail().getCompanyId());
			ps.setInt(6, questionList.getSurveyDetails().getId());
			genereatedKeys1 = ps.executeUpdate();
			
//			//save Data in  question_option table
//			  ps =	con.prepareStatement("insert into question_option"+
//						"(question_list_id,description, order1,created_by,create_date) values" +
//				 		"(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
//			  ps2 =	con.prepareStatement("insert into survey_right_answer"+
//						"(question_list_id,question_option_id,survey_detail_id,company_id,created_by,create_date) values" +
//				 		"(?,?,?,?,?,?)");
//
//			  int order =1;
//			  for(QuestionOption questionOption:questionList.getQuestionOptionList()){
//					ps.setInt(1, questionOption.getId());
//					ps.setString(2, questionOption.getDescription());
//					ps.setInt(3,order++);
//					ps.setString(4, questionList.getCreatedBy());
//					ps.setDate(5, new java.sql.Date(new Date().getTime()));
//					genereatedKeys2 = ps.executeUpdate();
//					if (questionOption.isCorrectAnswer()){
//						ps2.setInt(1, genereatedKeys1);
//						ps2.setInt(2, genereatedKeys2);
//						ps2.setInt(3, questionList.getSurveyDetails().getId());
//						ps2.setInt(4, questionList.getCompanyDetail().getCompanyId());
//						ps.setString(5, questionList.getCreatedBy());
//						ps.setDate(6, new java.sql.Date(new Date().getTime()));
//						ps.executeUpdate();
//					}
//				  
//			  }
			
			
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

	@Override
	public Integer removeSurveyQuestions(int qid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PreviewSurveyQuestion> getSurveyById(int companyId, int surveyId) {
		// TODO Auto-generated method stub
		return null;
	}

}
