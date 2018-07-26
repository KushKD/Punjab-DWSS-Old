package com.prwss.mis.common.notification;

import java.sql.Timestamp;
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
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.employee.dao.EmployeeBean;
import com.prwss.mis.pmm.alert.dao.AlertChecklistBean;
import com.prwss.mis.pmm.alert.dao.AlertChecklistDao;


public class MailNotificationBOImpl implements MailNotificationBO {
	
	private Logger log = Logger.getLogger(MailNotificationBOImpl.class);
	private AlertChecklistDao alertChecklistDao;
	private MailNotificationDao mailNotificationDao;

	public void setMailNotificationDao(MailNotificationDao mailNotificationDao) {
		this.mailNotificationDao = mailNotificationDao;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void mailNotifier() throws MISException {
		boolean archiveStatus = false;
		System.out.println("--------------------mailNotifier------------------------");
		try {
			List<MailNotificationArchiveBean> mailNotificationArchiveBeans=null;
			mailNotificationArchiveBeans= new ArrayList<MailNotificationArchiveBean>();
			
			/*List<String> statusList = new ArrayList<String>();
			statusList.add("WAIT");
			statusList.add("FAILED");
			String[] monthName = {"January", "February",
					  "March", "April", "May", "June", "July",
					  "August", "September", "October", "November",
					  "December"
					  };*/
			
			/*Calendar cal = Calendar.getInstance();
			int date=cal.get(Calendar.DATE);
	        int month = cal.get(Calendar.MONTH)+1;
	        int year = cal.get(Calendar.YEAR);
	        if(date==7){
	        	System.out.println(date);
	        	if(month!=0){
	        		month=month-1;
	        		System.out.println(month);
	        	}else{
	        		month=11;
	        	}
	        	*/
	        	List<MailNotificationBean> mailNotificationBeansSave =new ArrayList<MailNotificationBean>();
	        	List<EmployeeBean> employeeBean=alertChecklistDao.getEmployeeMail();
	        	
	        	/*List<AlertChecklistBean> alertChecklistBeans=alertChecklistDao.findAlertChecklistBean(null, monthName[month], year, "WAIT");
	        	List<Object> alerts=alertChecklistDao.getMailData(monthName[month], year,"WAIT");
	        	System.out.println("hello do the mail");*/
	        	//System.out.println("alerts"+ alerts);
	        	
	        	/*+(String)row[2] +
				" for the month/year: "+monthName[month]+"/"+year+" is as below.\n "+
				" \n 1. Admin Approval			  : " + ((Boolean)row[5] != null && (Boolean)row[5]?"Complete":"Pending")+
				" \n 2. Scheme Commissioning	  : " + ((Boolean)row[6] != null && (Boolean)row[6]?"Complete":"Pending")+
				" \n 3. Water Connections		  : " + ((Boolean)row[7] != null && (Boolean)row[7]?"Complete":"Pending")+
				" \n 4. Household 				  : " + ((Boolean)row[8] != null && (Boolean)row[8]?"Complete":"Pending")+
				" \n 5. Operation Sustainability : " + ((Boolean)row[9] != null && (Boolean)row[9]?"Complete":"Pending")+
				" \n 6. IEC Activity : " + ((Boolean)row[10] != null && (Boolean)row[10]?"Complete":"Pending")+
				" \n 7. Beneficiay Share : " + ((Boolean)row[11] != null && (Boolean)row[11]?"Complete":"Pending")+
				" \n 8. Spmc Payment Voucher : " + ((Boolean)row[12] != null && (Boolean)row[12]?"Complete":"Pending")+
				" \n 9. Dpmc Payment Voucher : " + ((Boolean)row[13] != null && (Boolean)row[13]?"Complete":"Pending")+
				" \n 10. GPWSC Register Entry : " + ((Boolean)row[14] != null && (Boolean)row[14]?"Complete":"Pending")+
				" \n 11. Updation of Procurement Plan : " + ((Boolean)row[15] != null && (Boolean)row[15]?"Complete":"Pending")+
				*/
				
	        /*	for (Object r : alerts) {
					Object[] row = (Object[]) r;*/
	        	//for(EmployeeBean eMail:employeeBean){
					StringBuffer emailBody=new StringBuffer();
					emailBody.append("Dear Sir/Madam, \n\n This is a system generated email reminder for Executive Engineer to ensure timely updations of following parameters, pertaining to your divisions, in MIS:  "+"\n A) " +
							" Last date for entry of progress achieved up to previous month in terms of following attributes, is 7th of this month \n\n 1) Admin/Technical Approval \t\t 2) IEC/ Capacity Building \t\t  3)Tender publishing \n 4) Tender/contract award \t\t   5) Village commissioning \t\t  6) Operating arrangement \n 7)Water/ Sewer connections"+
							
							"\n B) Last of date for entry of following activities carried out in the previous month, is 10th of this month \n\n 1)LOC request and release \t\t 2) Voucher of Payment \t\t 3) Receipt voucher \n 4)Adjustment voucher\t\t            5) GPWSC Register \t\t      6) Sustainability Scoring "+"\n\nRegards \n IT-Help Desk\n Department of Water Supply and Sanitation, Punjab");
					
					MailNotificationBean mailNotificationBean = new MailNotificationBean();
					mailNotificationBean.setEmailBody(emailBody.toString());
					/*mailNotificationBean.setEmailTo((String)(row[0]));
					*/
					mailNotificationBean.setEmailTo("er.bhagwanraj@gmail.com");
					//mailNotificationBean.setEmailTo(eMail.getOfficialEmailId());
					mailNotificationBean.setEmailFrom("alert.prwss@gmail.com");
					//mailNotificationBean.setEmailTo("er.bhagwanraj@gmail.com");
					mailNotificationBean.setEmailStatus("WAIT");
					mailNotificationBean.setEmailSubject("System Generated advance reminder for entry in MIS according to Data Entry Protocol, Approved by Government for www.pbdwss.gov");
					mailNotificationBean.setEntBy("0");
					java.util.Date cDate= new java.util.Date();
					mailNotificationBean.setEntDate(new Timestamp(cDate.getTime()));
					mailNotificationBeansSave.add(mailNotificationBean);
				
					doMail(mailNotificationBeansSave);
					
	       /* 	
				for (AlertChecklistBean alertChecklistBean : alertChecklistBeans) {
					alertChecklistBean.setMailStatus("FIRST");
				}
				
				mailNotificationDao.saveMailNotificationBean(mailNotificationBeansSave);
				alertChecklistDao.saveOrUpdateAll(alertChecklistBeans);
				
	        }	        
	        
	        
			
			
			
			List<MailNotificationBean> mailNotificationBeans =  mailNotificationDao.findMailNotification(null, statusList);
			if(!MisUtility.ifEmpty(mailNotificationBeans)){
				List<MailNotificationBean> successMailNotificationBeans = doMail(mailNotificationBeans);
				if(!MisUtility.ifEmpty(successMailNotificationBeans)){
					boolean status = mailNotificationDao.deleteMailNotification(successMailNotificationBeans);
					if(status){
						MailNotificationArchiveBean mailNotificationArchiveBean = null;

						for (MailNotificationBean mailNotificationBean : successMailNotificationBeans) {
							mailNotificationArchiveBean = new MailNotificationArchiveBean();

							mailNotificationArchiveBean.setNotificationId(mailNotificationBean.getNotificationId());
							mailNotificationArchiveBean.setEmailAttachments(mailNotificationBean.getEmailAttachments());
							mailNotificationArchiveBean.setEmailBody(mailNotificationBean.getEmailBody());
							mailNotificationArchiveBean.setEmailCC(mailNotificationBean.getEmailCC());
							mailNotificationArchiveBean.setEmailFrom(mailNotificationBean.getEmailFrom());
							mailNotificationArchiveBean.setEmailStatus(mailNotificationBean.getEmailStatus());
							mailNotificationArchiveBean.setEmailSubject(mailNotificationBean.getEmailSubject());
							mailNotificationArchiveBean.setEmailTo(mailNotificationBean.getEmailTo());
							mailNotificationArchiveBean.setEntBy("Web Application");
							mailNotificationArchiveBean.setEntDate(new Timestamp(System.currentTimeMillis()));

							mailNotificationArchiveBeans.add(mailNotificationArchiveBean);
						}
						archiveStatus =  mailNotificationDao.saveMailNotification(mailNotificationArchiveBeans);
						if(!archiveStatus){
							log.error("Mail Notification Archive failed");
							throw new MISException("Mail Notification Archive failed");
						}
					}
				}
			}*/

	        	//}
		}
	        catch (DataAccessException e) {
			log.error(e.getLocalizedMessage(),e);
			throw new MISException(e);
		}

	}
	
	private List<MailNotificationBean> doMail(List<MailNotificationBean> mailNotificationBeans){
		List<MailNotificationBean> successMailNotificationBeans = new ArrayList<MailNotificationBean>();
		try {
			System.out.println("do mail in");
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
				//if(MisUtility.ifEmpty(mailNotificationBean.getEmailTo()))
					//helper.setTo(mailNotificationBean.getEmailTo().split(","));
					helper.setTo("er.bhagwanraj@gmail.com");
				//if(MisUtility.ifEmpty(mailNotificationBean.getEmailCC()))
					helper.setCc("priteshmca@gmail.com");
				//helper.setCc(mailNotificationBean.getEmailCC().split(","));
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
		return successMailNotificationBeans;
	}

	public void setAlertChecklistDao(AlertChecklistDao alertChecklistDao) {
		this.alertChecklistDao = alertChecklistDao;
	}


}
