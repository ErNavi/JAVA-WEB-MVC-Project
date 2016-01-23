package com.cardinalts.survey.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.cardinalts.survey.entities.Company;
import com.cardinalts.survey.entities.CompanyDetail;
import com.cardinalts.survey.entities.Email;
import com.cardinalts.survey.entities.QuestionList;
import com.cardinalts.survey.entities.QuestionOption;
import com.cardinalts.survey.entities.SurveyDetails;
import com.cardinalts.survey.entities.Template;
import com.cardinalts.survey.entities.UserResponse;
/**
 * utility class to get or set data in Entities
 * @author Shashwati.Nigam
 *
 */
public class SurveyHelper {
/**
 * gets data from view (companyRegistrationNew.jsp)
 * set data in company Entity.
 * @param request
 * @return Company Object
 */
	public Company copyRequestDataToCompany(HttpServletRequest request,String path) {
		System.out.println("in at te top of convertRequestToCompany(HttpServletRequest request)");
		Company company = new Company();
		int sid = Integer.parseInt(request.getParameter("st"));
		int mid = Integer.parseInt(request.getParameter("membership"));
		System.out.println("sid and mid " + sid + " " + mid);
		company.setName(request.getParameter("txtname"));
		company.setPassword(request.getParameter("txtupass"));
		company.setEmail(request.getParameter("txtemail"));
		company.setUrl(request.getParameter("txturl"));
		company.setLogo(path);
		company.setSecurityQuestionId(sid);
		company.setSecurityAnswer(request.getParameter("txtans"));
		company.setMembership(mid);
		company.setCreatedBy(request.getParameter("txtemail"));

		return company;
	}

	/**
	 * gets data from view (emailListInfo.jsp)
	 * set data in emailList Entity.
	 * @param request
	 * @return Map of email list
	 */
	public Map<String, Email> convertRequestToEmailList(
			HttpServletRequest request) {

		Email emaillist = null;
		Map<String, Email> m = new HashMap<String, Email>();

		String[] emailCheck = request.getParameterValues("email");
		String[] fnameSet = request.getParameterValues("fname");
		String[] lnameSet = request.getParameterValues("lname");

		int i = 0;
		System.out.println("emailCheck value= " + emailCheck[0]);
		if (emailCheck[0] != null && emailCheck.length != 0) {
			for (int j = 0; j < emailCheck.length; j++) {
				if (fnameSet[j].equals(null)) { // changing null to "" in
												// Firstname
					fnameSet[j] = "";
				}
				if (lnameSet[j] == null) { // changing null to "" in
											// Lasttname
					fnameSet[j] = "";
				}
				if (emailCheck[j] != null && emailCheck[j] != "") { // null
																	// check to
																	// descard
																	// value
																	// from
					System.out.println("the data fynaly being fed : "+ emailCheck[j]); // feeding in map
					emaillist = new Email();
					emaillist.setFirstName(fnameSet[j]);
					emaillist.setLastName(lnameSet[j]);
					emaillist.setEmail(emailCheck[j]);

					System.out
							.println("+++++++inside map feeding IF : value of emailCheck= "
									+ emailCheck[j]);
					m.put(emailCheck[i], emaillist); // key is "emailCheck[j]"
														// and valur is object
														// i.e. "emaillist"
					i++;
				}
			}
			return m;}
		return null;
	}
	
	/**
	 * gets data from view (emailListInfo.jsp)
	 * set data in emailList Entity to be fed in company_email_list table in DB
	 * @param request
	 * @return Map of email list
	 */
	public Map<String, Email> convertRequestToCompanyEmailList(
			HttpServletRequest request) {

		Email emaillist = null;
		Map<String, Email> m = new HashMap<String, Email>();

		String[] emailCheck = request.getParameterValues("email");
		String[] fnameSet = request.getParameterValues("fname");
		String[] lnameSet = request.getParameterValues("lname");

		int i = 0;
		System.out.println("emailCheck value= " + emailCheck[0]);
		if (emailCheck[0] != null && emailCheck.length != 1) {
			for (int j = 0; j < emailCheck.length; j++) {
				if (fnameSet[j].equals(null)) { // changing null to "" in
												// Firstname
					fnameSet[j] = "";
				}
				if (lnameSet[j] == null) { // changing null to "" in
											// Lasttname
					fnameSet[j] = "";
				}
				if (emailCheck[j] != null && emailCheck[j] != "") { // null
																	// check to
																	// descard
																	// value
																	// from
					System.out.println("the data fynaly being fed : "
							+ emailCheck[j]); // feeding in map
					emaillist = new Email();
					emaillist.setFirstName(fnameSet[j]);
					emaillist.setLastName(lnameSet[j]);
					emaillist.setEmail(emailCheck[j]);

					System.out
							.println("+++++++inside map feeding IF : value of emailCheck= "
									+ emailCheck[j]);
					m.put(emailCheck[i], emaillist); // key is "emailCheck[j]"
														// and valur is object
														// i.e. "emaillist"
					i++;
				}
			}
			return m;}
		return null;
	}
	
	
/**
 * gets data from view(surveyDetail.jsp)
 * @param request
 * @return surveyDetail object
 */
	public SurveyDetails convertRequestToSurveyDetails(
			HttpServletRequest request) {
		HttpSession hs = request.getSession();
		int cid = Integer.parseInt(hs.getAttribute("cid").toString());
		int id=Integer.parseInt(request.getParameter("id"));
		String created_by = hs.getAttribute("cemail").toString();
		SurveyDetails surveydetails = new SurveyDetails();
		surveydetails.setCompanyId(cid);
		surveydetails.setName(request.getParameter("survey_name"));
		surveydetails.setDescription(request.getParameter("description"));
		surveydetails.setId(id);
		System.out.println("survey helper : "+id);
		surveydetails.setCreatedBy(created_by);

		return surveydetails;
	}
	
	/**
	 * gets data from view(surveyDetail.jsp)and update DB
	 * @param request
	 * @return surveyDetail object
	 */
		public SurveyDetails convertRequestToSurveyDetails2(
				HttpServletRequest request) {
			HttpSession hs = request.getSession();
			//int cid = Integer.parseInt(hs.getAttribute("cid").toString());
			String updated_by = hs.getAttribute("cemail").toString();
			SurveyDetails surveydetails = new SurveyDetails();
			surveydetails.setId(Integer.parseInt(request.getParameter("id")));
			surveydetails.setName(request.getParameter("survey_name"));
			surveydetails.setDescription(request.getParameter("description"));
			surveydetails.setUpdatedBy(updated_by);

			return surveydetails;
		}
	/**
	 * gets data from view(chooseTemplet.jsp)
	 * @param request
	 * @return surveyDetail object
	 */
	public Template convertRequestToChooseTemplet(HttpServletRequest request)
	{
		HttpSession hs = request.getSession();
		
		String created_by = hs.getAttribute("created_by").toString();
		int ui=Integer.parseInt(request.getParameter("uname"));
		System.out.println("survey helpr ui value: "+ui);
		Template template = new Template();
		template.setCreatedBy(created_by);
		template.setId(ui);
		
		return template;	
	}
	/**
	 * gets data from view(surveyDetail.jsp)
	 * @param request
	 * @return surveyDetail object
	 */
		public SurveyDetails convertRequestToSaveTemplate(
				HttpServletRequest request) {
			
			int sid = Integer.parseInt(request.getParameter("sid"));
			String temp=request.getParameter("temp");
			String subject=request.getParameter("subject");
			System.out.println("inside helper: value od sid= "+sid+" : value of temp= "+temp);
			SurveyDetails surveydetails = new SurveyDetails();
			surveydetails.setId(sid); //id of surveyDetails
			surveydetails.setSubject(subject);
			surveydetails.setTemplet(temp); //choosen template text
		
			return surveydetails;
		}
		
		/**
		 * gets data from view (chooseTemplet.jsp)
		 * set data in SurveyDetails Entity.
		 * @param request
		 * @return surveyDetail object
		 */
		public SurveyDetails convertRequestToSaveTemplateUser(
				HttpServletRequest request) {
			HttpSession hs = request.getSession();
			int cid = Integer.parseInt(hs.getAttribute("cid").toString());
			int sid = Integer.parseInt(request.getParameter("sid"));
			System.out.println("inside helper: value od sid= "+sid+" : value of cid= "+cid);
			SurveyDetails surveydetails = new SurveyDetails();
			surveydetails.setCompanyId(cid);
			surveydetails.setId(sid); //id of surveyDetails
		
			return surveydetails;
		}
		
		/**
		 * gets data from view (forgotPassword.jsp)
		 * set data in Company Entity.
		 * @param request
		 * @return Company object
		 */
		public Company convertRequestToGetPassword(HttpServletRequest request)
		{
		
		Company company = new Company();
		company.setEmail(request.getParameter("fuserid"));
		company.setSecurityQuestionId(Integer.parseInt(request.getParameter("st")));
		company.setSecurityAnswer(request.getParameter("fSecurityAnswer"));
		
			return company;
			
		}
		
		/**
		 * gets data from view(addQuestion.jsp)
		 * @param request
		 * @return int
		 */
		public int convertRequestToIntegerForChooseTemplet(HttpServletRequest request)
		{
			//HttpSession hs = request.getSession();
			
			//String created_by = hs.getAttribute("created_by").toString();
			int id=Integer.parseInt(request.getParameter("id"));
			System.out.println("survey helpr delete id value: "+id);
			
			
			return id;	
		}
		
		public QuestionList convertRequestToQuestionList(HttpServletRequest request)
		{
			QuestionList questionList=new QuestionList();
			ArrayList<QuestionOption> questionOptionList=null;
			QuestionOption questionOptionObject=null;
			
			HttpSession hs = request.getSession();
			int cid = Integer.parseInt(hs.getAttribute("cid").toString());
			int sid = Integer.parseInt(request.getParameter("sid"));
			System.out.println("value of sid in survey helper: "+sid);
			Boolean rightAns = false;
			String email=(String) hs.getAttribute("cemail");
			CompanyDetail companyDetail = new CompanyDetail();
			companyDetail.setCompanyId(cid);
			SurveyDetails surveyDetails =new SurveyDetails();
			surveyDetails.setId(sid);
			String option = request.getParameter("option");
			String correctAnswer = request.getParameter("correctAnswer");
			String[] optionArr=option.split(","); //array of options
			String[] correctAnswerArr=correctAnswer.split(","); //arrat of correct ans
			
			
			int limit=optionArr.length; //total no of options
			System.out.println("wit out values : "+limit);
			
			
			questionList.setCompanyDetail(companyDetail); //1
			questionList.setDescription(request.getParameter("textarea"));//2
			//questionList.setDescription("here i m");//2
			questionList.setCreatedBy(email);//3
			questionList.setAnswerType(limit);//4
			questionList.setSurveyDetails(surveyDetails);//5
			
			System.out.println("================*BEGINS* survey helper check=================");
			System.out.println("cid: "+companyDetail.getCompanyId());
			System.out.println("Description: "+request.getParameter("textarea"));
			System.out.println("created by : "+email);
			System.out.println("Total Answers: "+limit);
			System.out.println("Survey Detail Id: "+surveyDetails.getId());
			System.out.println("================*ENDS* survey helper check=================");
			
			questionOptionList = new ArrayList<QuestionOption>();
			
			for(int i=0;i<limit;i++)
			{ //loop for saving options in array list as QuestionOption object
				
				System.out.println("*************issue resolver check:"+correctAnswerArr[i]);
			questionOptionObject = new QuestionOption();
			questionOptionObject.setDescription(optionArr[i]);//1
			questionOptionObject.setCreatedBy(email);//2
			if (Integer.parseInt(correctAnswerArr[i])==0){
				rightAns=true;
			}
			questionOptionObject.setCorrectAnswer(rightAns);//3
			rightAns=false;
			//System.out.println("Arrray checkkkkkkk  "+Integer.parseInt(correctAnswerArr[i]));
			//questionOptionObject.setOrder1(order1);
			
			System.out.println("================*BEGINS* survey helper check =================");
			System.out.println("Option desc : "+optionArr[i]);
			System.out.println("Option correst ans aray value: "+rightAns);
			System.out.println("Option correst ans: "+Boolean.parseBoolean(correctAnswerArr[i]));
			System.out.println("================*ENDS* survey helper check 2=================");
			
			questionOptionList.add(questionOptionObject);
			
			
			}
			questionList.setQuestionOptionList(questionOptionList);
			return questionList;
		}
		
		
		/**
		 * gets data from view(addQuestion.jsp)
		 * @param request
		 * @return int
		 */
		public int convertRequestToIntegerForQuestionRemove(HttpServletRequest request)
		{
			//HttpSession hs = request.getSession();
			System.out.println(" in top of convertRequestToIntegerForQuestionRemove");
			System.out.println("survey helpr delete id wtout paerse:"+request.getParameter("id"));
			System.out.println("survey helpr delete id with paerse: "+Integer.parseInt(request.getParameter("id")));
			int id=Integer.parseInt(request.getParameter("id"));
			
			System.out.println("survey helpr delete id value:"+id);
			return id;	
		}	
		
		/**
		 * gets data from view(openClientSurvey.jsp)
		 * @param request
		 * @return ArrayList
		 */
		public ArrayList<UserResponse> saveResponseToUserResponse(HttpServletRequest request) {
			
			ArrayList<UserResponse> userResponseList=null;
			userResponseList=new ArrayList<UserResponse>();
			ArrayList<UserResponse> result=null;
			UserResponse userResponse=null;
			int cid=Integer.parseInt(request.getParameter("cid"));
			int sid=Integer.parseInt(request.getParameter("sid"));
			int eid=Integer.parseInt(request.getParameter("eid"));
			
			System.out.println("cid,sid,eid is ="+cid+","+sid+","+eid);
			
			String[] question = request.getParameterValues("question");

				for (int j = 0; j < question.length; j++)	
				{
					if(request.getParameterValues("option_"+question[j])!=null)
					{
						String[] option=request.getParameterValues("option_"+question[j]);	
						System.out.println("*********************#START# options of qid "+question[j]);
						for(int k=0;k<option.length;k++)//iterating options
							{
								int optionID=0;
								int questionID=0;
							System.out.println("option value="+option[k]+" id is option_"+question[j]);
							optionID=Integer.parseInt(option[k]);
							questionID=Integer.parseInt(question[j]);
							
							userResponse=new UserResponse();
							userResponse.setSurveyDetailId(sid);
							userResponse.setCompanyId(cid);
							userResponse.setEmailListId(eid);
							userResponse.setQuestionListId(questionID);
							userResponse.setQuestionOptionId(optionID);
							userResponseList.add(userResponse);
							}
						System.out.println("*********************#END# options of qid "+question[j]);
						result=userResponseList;
				
					}
					else
					{
						System.out.println("not answered");
					}
			}
				return result;

			
		}
		
		
		
}
