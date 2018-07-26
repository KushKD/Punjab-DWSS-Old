package com.prwss.mis.common.tenderCheck;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

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
import com.prwss.mis.common.notification.MailNotificationBOImpl;
import com.prwss.mis.common.notification.dao.MailNotificationBean;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.procurement.check.TenderCheckBean;
import com.prwss.mis.tender.biddersdetail.dao.BidderHeaderBean;
import com.prwss.mis.tender.dao.TenderHeaderBean;
public class TenderNotificationBOImpl implements TenderNotificationBO{
	
	private Logger log = Logger.getLogger(MailNotificationBOImpl.class);

	private TenderCheckDao tenderCheckDao;
	public void setTenderCheckDao(TenderCheckDao tenderCheckDao) {
		this.tenderCheckDao = tenderCheckDao;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void mailNotifier() throws MISException, ParseException {
		//System.out.println("--------------------mailNotifier------------------------");
		
		List<TenderCheckBean> tenderCheckBeans=null;
		try {
			
	        	List<MailNotificationBean> mailNotificationBeansSave =new ArrayList<MailNotificationBean>();
	        	
	        	
	        	//System.out.println("in string method==="+tenderCheckBean.toString());
	        	//System.out.println("hello do the mail");
	        	List<TenderHeaderBean> tenderheadersbean=tenderCheckDao.findAllTenders();
	        	for (TenderHeaderBean checkBean:tenderheadersbean) {
	        		tenderCheckBeans=tenderCheckDao.findAllTender(checkBean.getTenderId());
	        			for(TenderCheckBean tenderCheckBean:tenderCheckBeans){
	        						
	        			if((tenderCheckBean.getOpen_date_diff()==10 )&& (tenderCheckBean.getNotResponsive()==null||tenderCheckBean.getNotResponsive().isEmpty())){	
	        				
		        		
	        	/*String current_date;
	            SimpleDateFormat formatter;
	            Date date = new Date();
	            
	            String datePattern = "dd MM yyyy";
			   // SimpleDateFormat df = new SimpleDateFormat(datePattern);
	            Date openDate=checkBean.getOpenDate();
			   // String openDate=MisUtility.convertDateToString(checkBean.getOpenDate());
			   
			  
			    
	            formatter = new SimpleDateFormat(datePattern);
	            current_date = formatter.format(date);
	            String open_date=formatter.format(openDate);
	            
	            Date date1 = formatter.parse(open_date);
        	    Date date2 = formatter.parse(current_date);
        	    long diff = date2.getTime() - date1.getTime();
        	    System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));*/
	        		//System.out.println("done");
	        		StringBuffer emailBody=new StringBuffer();
					emailBody.append("Dear Sir/Madam, \n\n Tender Status of your Division:  "+tenderCheckBean.getLocationId() +
							"  is as below.\n "+
							"\n\t\tNotice period of 90 Days has been passed since, Open Date "+tenderCheckBean.getOpenDate()+
							" for Tender Id "+tenderCheckBean.getTenderId()+
							"\n\nTake Appropriate Actions"+
							
							"\n\nRegards \n IT-Help Desk\n Department of Water Supply and Sanitation, Punjab");
					
					MailNotificationBean mailNotificationBean = new MailNotificationBean();
					mailNotificationBean.setEmailBody(emailBody.toString());
					mailNotificationBean.setEmailTo("er.bhagwanraj@gmail.com");
					
					//mailNotificationBean.setEmailTo(checkBean.getOfficeEmail());
					mailNotificationBean.setEmailFrom("alert.prwss@gmail.com");
					mailNotificationBean.setEmailStatus("WAIT");
					mailNotificationBean.setEmailSubject("Alert: Status of Tender");
					mailNotificationBean.setEntBy("0");
					java.util.Date cDate= new java.util.Date();
					mailNotificationBean.setEntDate(new Timestamp(cDate.getTime()));
					mailNotificationBeansSave.add(mailNotificationBean);
					 if(!MisUtility.ifEmpty(tenderCheckBeans)){
				   			doMail(mailNotificationBeansSave);
					 		}
	        			}
	        
	        	
	        			if(tenderCheckBean.getOpen_date_diff()==10 && (tenderCheckBean.getNotResponsive()!="Select" || !tenderCheckBean.getNotResponsive().equals("Select"))){	
	        				
			        		
	        					StringBuffer emailBody=new StringBuffer();
	        					emailBody.append("Dear Sir/Madam, \n\n Tender Status of your Division:  "+tenderCheckBean.getLocationId() +
	        							"  is as below.\n "+
	        							"\n\t\tNotice period of 90 Days has been passed since, Open Date "+tenderCheckBean.getOpenDate()+
	        							" for Tender Id "+tenderCheckBean.getTenderId()+
	        							"\n\nTake Appropriate Actions"+
	        							
	        							"\n\nRegards \n IT-Help Desk\n Department of Water Supply and Sanitation, Punjab");
	        					
	        					MailNotificationBean mailNotificationBean = new MailNotificationBean();
	        					mailNotificationBean.setEmailBody(emailBody.toString());
	        					mailNotificationBean.setEmailTo("er.bhagwanraj@gmail.com");
	        					
	        					//mailNotificationBean.setEmailTo(checkBean.getOfficeEmail());
	        					mailNotificationBean.setEmailFrom("alert.prwss@gmail.com");
	        					mailNotificationBean.setEmailStatus("WAIT");
	        					mailNotificationBean.setEmailSubject("Alert: Status of Tender");
	        					mailNotificationBean.setEntBy("0");
	        					java.util.Date cDate= new java.util.Date();
	        					mailNotificationBean.setEntDate(new Timestamp(cDate.getTime()));
	        					mailNotificationBeansSave.add(mailNotificationBean);
	        					 if(!MisUtility.ifEmpty(tenderCheckBeans)){
	        				   			doMail(mailNotificationBeansSave);
	        					 		}
	        	        			}
	        	        
	        	        		}
	        	        }
	            
		   }
	        	
		catch (DataAccessException e) {
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
			//	helper.setTo(mailNotificationBean.getEmailTo().split(","));
				//if(MisUtility.ifEmpty(mailNotificationBean.getEmailCC()))
				helper.setCc("priteshmca@gmail.com");
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
