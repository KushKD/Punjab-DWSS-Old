package com.prwss.mis.common.contractCheck;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.notification.dao.MailNotificationArchiveBean;
import com.prwss.mis.common.notification.dao.MailNotificationBean;
import com.prwss.mis.common.notification.dao.MailNotificationDao;
import com.prwss.mis.common.tenderCheck.TenderCheckDao;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.procurement.check.ContractCheckBean;
import com.prwss.mis.procurement.check.TenderCheckBean;
import com.prwss.mis.tender.dao.TenderHeaderBean;

public class ContractNotificationBOImpl implements ContractNotificationBO{

	private Logger log = Logger.getLogger(ContractNotificationBOImpl.class);
	private ContractNotificationDao contractNotificationDao;
	public void setContractNotificationDao(
			ContractNotificationDao contractNotificationDao) {
		this.contractNotificationDao = contractNotificationDao;
	}

	@SuppressWarnings("deprecation")
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void mailNotifier() throws MISException, ParseException {
		//System.out.println("--------------------mailNotifier------------------------");
		try {
				List<MailNotificationBean> mailNotificationBeansSave =new ArrayList<MailNotificationBean>();
				
				List<ContractCheckBean> contractCheckBeans=contractNotificationDao.findAllContracts();
				
				//System.out.println("hello do the mail");
	        	//System.out.println("second for loop");
	        	/*"\n\t\tThis is an alert as the Expenditure Amount exceeds the Awarded Amount for the Contract No. "+checkBean.getContractNo()+
	        	 * 
	        	 * Dear Sir/Madam, \n\n Contract Status of your Division:+checkBean.getLocationName() +*/
				
		        System.out.println("alerts");
	        		
		        	for (ContractCheckBean checkBean:contractCheckBeans) {
		        		 if(checkBean.getLocationId().equals("DIV64")||checkBean.getLocationId()=="DIV64"){
		        			 System.out.println("tender id ====="+checkBean.getTenderId());
				    			 
		        		if(checkBean.getTenderId().equals("T-DWSS-W-DIV64-3157-1")||checkBean.getTenderId()=="T-DWSS-W-DIV64-3157-1"){
		    				  
		        		long day1=checkBean.getOpenDiff()%90;
		        		long day2=(day1+15)%15;
		        	///	log.debug("day1==="+day1+"  day2==="+day2);
		        		
		        	if((day1==0||day2==0)&&(checkBean.getSigning_of_contract()==null)){
		        			StringBuffer emailBody=new StringBuffer();
		        			System.out.println("mail");
					        emailBody.append(
							"  This is an alert regarding non entry of  contract award  related information  of package  "+checkBean.getPackageId()+" of scheme  "+checkBean.getSchemeName()+
							"("+checkBean.getSchemeCode()+")"+"has not been entered in MIS till date	\n\n This is for your information and appropriate action"+
							"\n\nRegards \n IT-Help Desk\n Department of Water Supply and Sanitation, Punjab");
					
					MailNotificationBean mailNotificationBean = new MailNotificationBean();
					mailNotificationBean.setEmailBody(emailBody.toString());
					mailNotificationBean.setEmailTo("er.bhagwanraj@gmail.com");
					
					///mailNotificationBean.setEmailTo(checkBean.getOfficeEmail());
					
					mailNotificationBean.setEmailFrom("alert.prwss@gmail.com");
					mailNotificationBean.setEmailStatus("WAIT");
					mailNotificationBean.setEmailSubject("Alert: Non entry of award of contract of package "+checkBean.getPackageId()+" of scheme  "+checkBean.getSchemeName());
					mailNotificationBean.setEntBy("0");
					java.util.Date cDate= new java.util.Date();
					mailNotificationBean.setEntDate(new Timestamp(cDate.getTime()));
					mailNotificationBeansSave.add(mailNotificationBean);
				
	        	
			
			if(!MisUtility.ifEmpty(contractCheckBeans)){
				 doMail(mailNotificationBeansSave);
			   }
		
		     }		
		      	
		     		//Date contractend=null;
		     		//Date com_date=null;
		        	Date contractNextEndDate=null;
		     		Date contractEndDate=null;
		     		Date current_Date=null;
		     		String current_date=null;
		     		String end_date=null;
		     		SimpleDateFormat formatter=null;
		     		String actual_Date=null;
		     		Date actualCompDate=null;
		     	
			           
		        	
		     		if(checkBean.getContractEndDate()!=null){
		        	
			            Date date = new Date();
			            String datePattern = "dd-MM-yyyy";
					   // SimpleDateFormat df = new SimpleDateFormat(datePattern);
			            formatter = new SimpleDateFormat(datePattern);
			            
			            
			            //contract approx date 
			            if(checkBean.getContractEndDate()!=null){
			            Date endDate=checkBean.getContractEndDate();
					    end_date =formatter.format(endDate);	
			            String[] end_con=end_date.split("-");
			            int day=Integer.parseInt(end_con[0]);
			            int nextDay=day+4;
			            int month=Integer.parseInt(end_con[1]);
			            int year=Integer.parseInt(end_con[2]);
			            
			            contractNextEndDate=new Date(year,month,nextDay);
			            contractEndDate=new Date(year,month,day);
			            }
			            
			            //actual completion date 
			          if(  checkBean.getActualCompletionDate()!=null){
			            Date actualComDate=checkBean.getActualCompletionDate();
			            actual_Date =formatter.format(actualComDate);
			        	
			            String[] actualDate=actual_Date.split("-");
			            int actualDay=Integer.parseInt(actualDate[0]);
			            int actualmonth=Integer.parseInt(actualDate[1]);
			            int actualyear=Integer.parseInt(actualDate[2]);
			            actualCompDate=new Date(actualyear,actualmonth,actualDay);
			           
			          }
			            //current date
			            current_date = formatter.format(date);
			            
			            String[] currentDate=current_date.split("-");
			            int currentDay=Integer.parseInt(currentDate[0]);
			            int currentMon=Integer.parseInt(currentDate[1]);
			            int currentYear=Integer.parseInt(currentDate[2]);
				          
			            current_Date=new Date(currentYear,currentMon,currentDay);
		        		//String end_day=String.valueOf(day);
		        		
		        		
		        		
				    //actual completion date
			            //"T-DWSS-W-DIV64-3157-1"
			            System.out.println("contract next end date===="+contractNextEndDate);
		        		 System.out.println("current  date===="+current_Date);
			       if(current_Date.compareTo(contractNextEndDate)==0 && (checkBean.getActualCompletionDate()==null)){
		        	
					StringBuffer emailBody=new StringBuffer();
		        	System.out.println("mail");
		            emailBody.append(
							" This is an alert to intimate that the date of actual completion of  package  "+checkBean.getPackageId()+" of scheme  "+checkBean.getSchemeName()+
								"("+checkBean.getSchemeCode()+")"+"has not been entered in MIS yet  	\n\n This is for your information and appropriate action"+
								"\n\nRegards \n IT-Help Desk\n Department of Water Supply and Sanitation, Punjab" );
					
					MailNotificationBean mailNotificationBean = new MailNotificationBean();
					mailNotificationBean.setEmailBody(emailBody.toString());
					mailNotificationBean.setEmailTo("er.bhagwanraj@gmail.com");
					
					///mailNotificationBean.setEmailTo(checkBean.getOfficeEmail());
					
					mailNotificationBean.setEmailFrom("alert.prwss@gmail.com");
					mailNotificationBean.setEmailStatus("WAIT");
					mailNotificationBean.setEmailSubject("Alert: Non entry of award of contract of package "+checkBean.getPackageId()+" of scheme  "+checkBean.getSchemeName());
					mailNotificationBean.setEntBy("0");
					java.util.Date cDate= new java.util.Date();
					mailNotificationBean.setEntDate(new Timestamp(cDate.getTime()));
					mailNotificationBeansSave.add(mailNotificationBean);
				if(!MisUtility.ifEmpty(contractCheckBeans)){
				 doMail(mailNotificationBeansSave);
							}
			        }
				     
	        		 
	        		 
	        		 if(checkBean.getActualCompletionDate()!=null && actualCompDate.compareTo(contractEndDate)>0){
	 		        	
						   StringBuffer emailBody=new StringBuffer();
			        	System.out.println("mail");
			            emailBody.append("  This is an alert to intimate that the actual completion of  package  "+checkBean.getPackageId()+" of scheme  "+checkBean.getSchemeName()+
								"("+checkBean.getSchemeCode()+")"+"has exceeded the date of completion as per agreement 	\n\n This is for your information and appropriate action"+
								"\n\nRegards \n IT-Help Desk\n Department of Water Supply and Sanitation, Punjab");
						
						MailNotificationBean mailNotificationBean = new MailNotificationBean();
						mailNotificationBean.setEmailBody(emailBody.toString());
						mailNotificationBean.setEmailTo("er.bhagwanraj@gmail.com");
						
						///mailNotificationBean.setEmailTo(checkBean.getOfficeEmail());
						
						mailNotificationBean.setEmailFrom("alert.prwss@gmail.com");
						mailNotificationBean.setEmailStatus("WAIT");
						mailNotificationBean.setEmailSubject("Alert: Non entry of award of contract of package "+checkBean.getPackageId()+" of scheme  "+checkBean.getSchemeName());
						mailNotificationBean.setEntBy("0");
						java.util.Date cDate= new java.util.Date();
						mailNotificationBean.setEntDate(new Timestamp(cDate.getTime()));
						mailNotificationBeansSave.add(mailNotificationBean);
					if(!MisUtility.ifEmpty(contractCheckBeans)){
					 doMail(mailNotificationBeansSave);
								}
				        	}
		        		
	        	 
		        	 
		        	 
		        	}
		        	
		        	}
	
		         }		  
    }
	   
	 } catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}

	}
	
	private void doMail(List<MailNotificationBean> mailNotificationBeans){
		List<MailNotificationBean> successMailNotificationBeans = new ArrayList<MailNotificationBean>();
		try {
			//System.out.println("do mail in");
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		final String SMTP_HOST_NAME = "smtp.gmail.com";
		final String SMTP_PORT = "465";
//		final String emailMsgTxt = "Test Message Contents";
//		final String emailSubjectTxt = "A test from gmail";
//		final String emailFromAddress = "";
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		Properties javaMailProperties = System.getProperties();
		javaMailProperties.put("mail.smtp.host", SMTP_HOST_NAME);
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.debug", "true");
		javaMailProperties.put("mail.smtp.port", SMTP_PORT);
		javaMailProperties.put("mail.smtp.socketFactory.port", SMTP_PORT);
		javaMailProperties.put("mail.smtp.socketFactory.class", SSL_FACTORY);
		javaMailProperties.put("mail.smtp.socketFactory.fallback", "false");
		sender.setJavaMailProperties(javaMailProperties);
		sender.setUsername("alert.prwss@gmail.com");
		sender.setPassword("3xchange!");
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = null;
		for (MailNotificationBean mailNotificationBean : mailNotificationBeans) {
			
				helper = new MimeMessageHelper(message, true);
				if(MisUtility.ifEmpty(mailNotificationBean.getEmailTo()))
				helper.setTo("er.bhagwanraj@gmail.com");
				
				//helper.setTo(mailNotificationBean.getEmailTo().split(","));
				if(MisUtility.ifEmpty(mailNotificationBean.getEmailCC()))
				helper.setCc("er.bhagwanraj@gmail.com");
				helper.setSubject(mailNotificationBean.getEmailSubject());
				helper.setFrom(mailNotificationBean.getEmailFrom());
				helper.setSentDate(new Date(System.currentTimeMillis()));
				helper.setText(mailNotificationBean.getEmailBody());

				sender.send(message);
				successMailNotificationBeans.add(mailNotificationBean);
		}
		} catch (MailException e) {
			// Just logging the exception and kill so that the rest of the notifications are processed
			log.error(e.getLocalizedMessage(),e);
			
		} catch (MessagingException e) {
			// Just logging the exception and kill so that the rest of the notifications are processed
			log.error(e.getLocalizedMessage(),e);
		} catch (Exception e) {
			// Just logging the exception and kill so that the rest of the notifications are processed
			log.error(e.getLocalizedMessage(),e);
		}
		
	}
}	

