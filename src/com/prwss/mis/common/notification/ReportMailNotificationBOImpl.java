package com.prwss.mis.common.notification;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRVirtualizer;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;
import net.sf.jasperreports.engine.util.JRSwapFile;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.prwss.mis.common.MISJdcDaoImpl;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.notification.dao.MailNotificationBean;
import com.prwss.mis.common.notification.dao.MailNotificationDao;
import com.prwss.mis.common.util.MISJasperUtil;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.pmm.alert.dao.AlertChecklistDao;
import com.prwss.mis.pmm.report.struts.EstimatesAwardContractsReportForm;

public class ReportMailNotificationBOImpl implements ReportMailNotificationBO{

	private Logger log = Logger.getLogger(MailNotificationBOImpl.class);
	private AlertChecklistDao alertChecklistDao;
	private MailNotificationDao mailNotificationDao;
	private MISJasperUtil misJasperUtil;
	@SuppressWarnings("rawtypes")
	private Map parameters;
	String currentDate=MisUtility.now("dd-MM-yyyy"); 
	private MISJdcDaoImpl misJdcDaoImpl;
	
	public void setMisJdcDaoImpl(MISJdcDaoImpl misJdcDaoImpl) {
		this.misJdcDaoImpl = misJdcDaoImpl;
	}
	
	
	@SuppressWarnings("rawtypes")
	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}
	@SuppressWarnings("rawtypes")
	public Map getParameters() {
		return parameters;
	}
	public void setMailNotificationDao(MailNotificationDao mailNotificationDao) {
		this.mailNotificationDao = mailNotificationDao;
	}
	public void setAlertChecklistDao(AlertChecklistDao alertChecklistDao) {
		this.alertChecklistDao = alertChecklistDao;
	}
	public void setMisJasperUtil(MISJasperUtil misJasperUtil) {
		this.misJasperUtil = misJasperUtil;
	}
	public void filePDF(String fileName)
			throws Exception {
		System.out.println("-------------IN file PDF----------------");
		
		Connection connection = null;
		EstimatesAwardContractsReportForm estimatesAwardContractsReportForm= new EstimatesAwardContractsReportForm();
		try{
			System.out.println("Start download");
		connection = misJdcDaoImpl.getMISDataSource().getConnection();
		connection.setAutoCommit(false);
		String jasperFile="/usr/apache-tomcat-6.0.32/webapps/PRWSS_MIS/pmm/reports/PMMRPT001_001.jrxml";	
		JasperReport jasperReport = JasperCompileManager.compileReport(jasperFile);
		String fileTitle="PMMRPT001_001";
		setWhere(estimatesAwardContractsReportForm);
		JRVirtualizer virtualizer = null; 
		virtualizer = new JRSwapFileVirtualizer(10, new JRSwapFile("/usr", 90000, 15000), false);
		parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
		System.out.println("Inbetween download");
		JasperPrint filledReport = JasperFillManager.fillReport(jasperReport, parameters, connection);
		
		JRExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, filledReport);
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "/usr/report/"+fileTitle+currentDate+".pdf");
		exporter.exportReport();
		System.out.println("End download");
		}
		catch(Exception e){
			System.out.println("JASPER EXCEPTION :: ");
			e.printStackTrace();
		}
	}	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setWhere(EstimatesAwardContractsReportForm estimatesAwardContractsReportForm){
		String innerWhere=" and 1=1 ";
		String selectZone=estimatesAwardContractsReportForm.getSelectZone();
		String zoneId=estimatesAwardContractsReportForm.getZoneId();
		String selectCircle=estimatesAwardContractsReportForm.getSelectCircle();
		String circleId=estimatesAwardContractsReportForm.getCircleId();
		String selectDistrict=estimatesAwardContractsReportForm.getSelectDistrict();
		String districtId=estimatesAwardContractsReportForm.getDistrictId();
		String selectProgram=estimatesAwardContractsReportForm.getSelectProgram();
		String programId=estimatesAwardContractsReportForm.getProgramId();
		String approvalStatus=estimatesAwardContractsReportForm.getApprovalStatus();
		String selectPeriod=estimatesAwardContractsReportForm.getSelectPeriod();
		String fromPeriod=estimatesAwardContractsReportForm.getFromDate();
		String toPeriod=estimatesAwardContractsReportForm.getToDate();
		String selectReport=estimatesAwardContractsReportForm.getSelectReport();
		String month = estimatesAwardContractsReportForm.getMonthId();
		String finYearId = estimatesAwardContractsReportForm.getFinYearId(); 
		String status = estimatesAwardContractsReportForm.getStatus();
		System.out.println("-----------Status is-------"+status);
		parameters = new HashMap();
		String from = "All";
		String to = "All";
		String from_date = "All";
		String to_date = "All";
		String where="1=1 ";
		
//-----------------------Select Period-----------------------------------------------------		
	System.out.println("selectPeriod: "+selectPeriod);
	System.out.println("fromPeriod: "+fromPeriod);
	System.out.println("toPeriod: "+toPeriod);
	from = fromPeriod==null?"All":fromPeriod;
	//System.out.println("from: "+from);
	to = toPeriod==null?"All":toPeriod;
	//System.out.println("to: "+to);
	from_date = fromPeriod==null?"All":MisUtility.convertStringToDate(fromPeriod).toString();
	//System.out.println("from_date: "+from_date);
	to_date = toPeriod==null?"All":MisUtility.convertStringToDate(toPeriod).toString();
	//System.out.println("to_date: "+to_date);
	
		parameters.put("where", where);
		parameters.put("month",month);
		parameters.put("finYearId",finYearId);
		parameters.put("from", from);
		parameters.put("to", to);
		parameters.put("from_date", from_date);
		parameters.put("to_date", to_date);
		parameters.put("status", status);
		parameters.put("innerWhere", innerWhere);
		System.out.println("Parameters: "+parameters.size()+":"+parameters.toString());
		System.out.println("selectReport: "+selectReport);
		System.out.println("Action: where : "+where);
		System.out.println("Action: innerWhere : "+innerWhere);
	}
	
	@Override
	public void mailNotifier() throws MISException {
		System.out.println("--------------------mailNotifier------------------------");
		try {
			
	        	List<MailNotificationBean> mailNotificationBeansSave =new ArrayList<MailNotificationBean>();
	        	//List<TenderCheckBean> tenderCheckBeans=tenderCheckDao.findAllTenders(null);
	        	try{
	        	filePDF("PMMRPT001_001");
	        	
	        	}catch(Exception e){
	        		e.printStackTrace();
	        	}
	        	System.out.println("hello do the mail");
	        	//System.out.println("alerts"+ alerts);
	        		StringBuffer emailBody=new StringBuffer();
					emailBody.append("Dear Sir/Madam, \n\n Please Find the Attachments :"+							
							"\n\nRegards \n IT-Help Desk\n Department of Water Supply and Sanitation, Punjab");
					
					MailNotificationBean mailNotificationBean = new MailNotificationBean();
					mailNotificationBean.setEmailBody(emailBody.toString());
					mailNotificationBean.setEmailTo("er.bhagwanraj@gmail.com");
					mailNotificationBean.setEmailCC("er.bhagwanraj@gmail.com");
					mailNotificationBean.setEmailFrom("alert.prwss@gmail.com");
					mailNotificationBean.setEmailStatus("WAIT");
					mailNotificationBean.setEmailSubject("Auto-Generated Report");
					mailNotificationBean.setEntBy("0");
					java.util.Date cDate= new java.util.Date();
					mailNotificationBean.setEntDate(new Timestamp(cDate.getTime()));
					mailNotificationBeansSave.add(mailNotificationBean);
					
					doMail(mailNotificationBeansSave);
			

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
				if(MisUtility.ifEmpty(mailNotificationBean.getEmailCC()))
					helper.setCc("er.bhagwanraj@gmail.com");
				helper.setSubject(mailNotificationBean.getEmailSubject());
				helper.setFrom(mailNotificationBean.getEmailFrom());
				helper.setSentDate(new Date(System.currentTimeMillis()));
				helper.setText(mailNotificationBean.getEmailBody());
				//helper.addAttachment("PMMRPT001_001"+currentDate+".pdf", new ClassPathResource("D:/PMMRPT001_001"+currentDate+".pdf"));
				helper.addAttachment("PMMRPT001_001"+currentDate+".pdf", new File("/usr/report/PMMRPT001_001"+currentDate+".pdf"));
				sender.send(message);
				//successMailNotificationBeans.add(mailNotificationBean);
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
