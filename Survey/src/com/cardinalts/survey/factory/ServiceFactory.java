package com.cardinalts.survey.factory;

import com.cardinalts.survey.service.ChooseEmailInfo;
import com.cardinalts.survey.service.ChooseTempletInfo;
import com.cardinalts.survey.service.ChooseTempletUserInfo;
import com.cardinalts.survey.service.CompanyRegistration;
import com.cardinalts.survey.service.EmailListInfo;
import com.cardinalts.survey.service.ForgotPasswordInfo;
import com.cardinalts.survey.service.LoginInfo;
import com.cardinalts.survey.service.OpenClientSurveyInfo;
import com.cardinalts.survey.service.SendEmailInfo;
import com.cardinalts.survey.service.SurveyDetailInfo;
import com.cardinalts.survey.service.SurveyQuestions;
import com.cardinalts.survey.serviceImpl.AddQuestionInfoImpl;
import com.cardinalts.survey.serviceImpl.ChooseEmailInfoImpl;
import com.cardinalts.survey.serviceImpl.ChooseTempletInfoImpl;
import com.cardinalts.survey.serviceImpl.ChooseTempletUserInfoImpl;
import com.cardinalts.survey.serviceImpl.CompanyEmailListInfoImpl;
import com.cardinalts.survey.serviceImpl.CompanyRegistrationImpl;
import com.cardinalts.survey.serviceImpl.EmailListInfoImpl;
import com.cardinalts.survey.serviceImpl.ForgotPasswordInfoImpl;
import com.cardinalts.survey.serviceImpl.LoginInfoImpl;
import com.cardinalts.survey.serviceImpl.OpenClientSurveyInfoImpl;
import com.cardinalts.survey.serviceImpl.SendEmailInfoImpl;
import com.cardinalts.survey.serviceImpl.SurveyDetailInfoImpl;

/**
 * Factory class for Creating Singleton Service Objects
 * 
 * @author Deepak.Dogra
 * 
 */
public class ServiceFactory {

	private static ServiceFactory singleton = new ServiceFactory();

	private ServiceFactory() {
	}
	public static ServiceFactory getInstance() {
		return singleton;
	}

   /**
    * 
    * @return object of CompanyRegistrationImpl
    */
	public CompanyRegistration getCompanyRegistrationService() {

		return new CompanyRegistrationImpl();
	}

	/**
	 * 
	 * @return object of EmailListInfoImpl
	 */
	public EmailListInfo getEmailListInfoService() {

		return new EmailListInfoImpl();
	}
	
	/**
	    * 
	    * @return object of AddQuestionInfoImpl
	    */
		public SurveyQuestions getSurveyQuestionsService() {

			return new AddQuestionInfoImpl();
		}
	
	/**
	 * 
	 * @return object of EmailListInfoImpl
	 */
	public EmailListInfo getCompanyEmailListInfoService() {

		return new CompanyEmailListInfoImpl();
	}

	/**
	 * 
	 * @return object of LoginInfoImpl
	 */
	public LoginInfo getLoginInfoService() {
		return new LoginInfoImpl();
	}

	/**
	 * 
	 * @return object of SurveyDetailInfoImpl
	 */
	public SurveyDetailInfo getSurveyDetailInfoService() {
		return new SurveyDetailInfoImpl();
	}
	
	/**
	 * 
	 * @return object of ChooseTempletInfoImpl
	 */
	public ChooseTempletInfo getChooseTempletInfoService() {
		return new ChooseTempletInfoImpl();
	}
	/**
	 * 
	 * @return object of ChooseTempletUserInfoImpl
	 */
	public ChooseTempletUserInfo getChooseTemplatUserInfoService() {
		return new ChooseTempletUserInfoImpl();
	}
	/**
	 * 
	 * @return object of ForgotPasswordInfoImpl
	 */
	public ForgotPasswordInfo getForgotPasswordInfoService()
	{
		return new ForgotPasswordInfoImpl();
		
	}
	
	/**
     * 
     * @return object of ChooseEmailInfoImpl
     */
	public ChooseEmailInfo getChooseEmailInfoService() {

		return new ChooseEmailInfoImpl();
	}
	
	/**
     * 
     * @return object of SendEmailInfoImpl
     */
	public SendEmailInfo getSendEmailInfoService()
	{
		return new SendEmailInfoImpl();
	}
	
	/**
     * 
     * @return object of OpenClientSurveyInfo
     */
	public OpenClientSurveyInfo getOpenClientSurveyInfoService()
	{
		return new OpenClientSurveyInfoImpl();
	}
	
	
}
