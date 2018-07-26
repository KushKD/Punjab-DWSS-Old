package com.prwss.mis.common.util.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.prwss.mis.common.notification.dao.MailNotificationBean;
import com.prwss.mis.common.util.MisUtility;


public class SendMailServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1738910969424259354L;
	
	private Logger log = Logger.getLogger(SendMailServlet.class);
	
	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		try {
			System.out.println("email Start");
			JavaMailSenderImpl sender = new JavaMailSenderImpl();
			final String SMTP_HOST_NAME = "smtp.gmail.com";
			final String SMTP_PORT = "465";
//			final String emailMsgTxt = "Test Message Contents";
//			final String emailSubjectTxt = "A test from gmail";
//			final String emailFromAddress = "vrastogi@deloitte.com";
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
			helper = new MimeMessageHelper(message, true);
			if(req.getParameter("snk_name").equals("snk_guest")){
				helper.setTo("vrastogi@deloitte.com");
				helper.setCc("ankitk@deloitte.com");
				helper.setFrom("alert.prwss@gmail.com");
				String emailBody=null;
				emailBody="Complainer Name: "+	req.getParameter("snk_name")!=null?(String)req.getParameter("snk_name"):""+
						" Mobile Number: "+	req.getParameter("snk_mobile")!=null?(String)req.getParameter("snk_mobile"):""+
						" Complainer Address: "+	req.getParameter("snk_address")!=null?(String)req.getParameter("snk_address"):""+
						" Complaint: "+	req.getParameter("snk_description")!=null?(String)req.getParameter("snk_description"):"";
				System.out.println("emailBody: "+emailBody);
				helper.setText(emailBody);	
				sender.send(message);
				PrintWriter out = resp.getWriter();
				out.print(emailBody);
			}
			
		} catch (MailException e) {
			// Just logging the exception and kill so that the rest of the notifications are processed
			log.error(e.getLocalizedMessage(),e);			
		}catch (Exception e) {
			// Just logging the exception and kill so that the rest of the notifications are processed
			log.error(e.getLocalizedMessage(),e);
		}				
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		this.processRequest(req,resp);
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.processRequest(req,resp);
	}
}