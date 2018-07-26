package com.prwss.mis.common.util.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.dao.DataAccessException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.prwss.mis.common.MISJdcDaoImpl;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.complaint.dao.ComplaintBean;
import com.prwss.mis.masters.complaint.dao.ComplaintDao;
import com.prwss.mis.masters.employee.dao.EmployeeBean;
import com.prwss.mis.masters.employee.dao.EmployeeDao;
import com.prwss.mis.service.ticket.ComplaintBookBO;
import com.prwss.mis.service.ticket.dao.ComplaintBookBean;
import com.prwss.mis.service.ticket.dao.ComplaintBookDao;
import com.prwss.mis.service.ticket.dao.ComplaintHistoryBean;

public class PbDwssController extends HttpServlet{
	private static final long serialVersionUID = -1738910969424259354L;
	private Logger log = Logger.getLogger(PbDwssController.class);
	private ComplaintBookDao complaintBookDao;
	private ComplaintDao complaintDao;
	private ComplaintBookBO complaintBookBO;
	private EmployeeDao employeeDao;
	private MISJdcDaoImpl misJdcDaoImpl;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		Connection connection = null;
		Statement statement = null;
		try {
			WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(req.getSession().getServletContext());
			
			StringBuffer buffer = new StringBuffer();
			String op=null;
			if(MisUtility.ifEmpty(req.getParameter("op"))){
				op = (String)req.getParameter("op");
			}
			System.out.println("op: "+op);
			StringBuffer emailBody=new StringBuffer();
			String subject=null;

			JavaMailSenderImpl sender = new JavaMailSenderImpl();
			final String SMTP_HOST_NAME = "smtp.gmail.com";
			final String SMTP_PORT = "465";
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
			sender.setUsername("dwsshelpdesk@gmail.com");
			sender.setPassword("spmcmohali");
			
			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = null;
			PrintWriter out = resp.getWriter();
			if(op.equals("sendGrievances")){
				boolean okFlag=true;
				this.complaintDao = (ComplaintDao)webApplicationContext.getBean("complaintDao");
				this.complaintBookDao = (ComplaintBookDao)webApplicationContext.getBean("complaintBookDao");
				String qName=null,qEmail=null,qAddress=null,qFeedbackType=null,qSubject=null,qMsg=null,qDistrict=null,qMobile=null;;
				if(MisUtility.ifEmpty(req.getParameter("qName"))){
					qName = (String)req.getParameter("qName");					
				}else okFlag=false;
				if(MisUtility.ifEmpty(req.getParameter("qEmail"))){
					qEmail = (String)req.getParameter("qEmail");					
				}else okFlag=false;
				if(MisUtility.ifEmpty(req.getParameter("qMobile"))){
					qMobile = (String)req.getParameter("qMobile");					
				}else okFlag=false;
				if(MisUtility.ifEmpty(req.getParameter("qAddress"))){
					qAddress = (String)req.getParameter("qAddress");					
				}else okFlag=false;
				if(MisUtility.ifEmpty(req.getParameter("qFeedbackType"))){
					qFeedbackType = (String)req.getParameter("qFeedbackType");					
				}else okFlag=false;
				if(MisUtility.ifEmpty(req.getParameter("qSubject"))){
					qSubject = (String)req.getParameter("qSubject");					
				}else okFlag=false;
				if(MisUtility.ifEmpty(req.getParameter("qMsg"))){
					qMsg = (String)req.getParameter("qMsg");					
				}else okFlag=false;
				if(MisUtility.ifEmpty(req.getParameter("qDistrict"))){
					System.out.println("Distric Id is --> " +req.getParameter("qDistrict"));
					qDistrict = (String)req.getParameter("qDistrict");					
				}else okFlag=false;
				
				System.out.println("okFlag---" +okFlag);
				if(okFlag){
					Timestamp loginTimestamp = new Timestamp(System.currentTimeMillis());
					System.out.println("PbDwssController: "+qName+","+qEmail+","+qAddress+","+qFeedbackType+","+qSubject+","+qMsg+","+qMobile);
					long ticketId = 0;
					long assignedEmpId=0;
					try {
						ComplaintBookBean complaintBookBean = new ComplaintBookBean();
						ComplaintBean complaintBean = new ComplaintBean();
						complaintBean.setComplaintId(new Long(8));
						System.out.println("PBdwaa complaintId: "+complaintBean.getComplaintId());
						List<String> status=new ArrayList<String>();
						status.add("A");
						
						List<ComplaintBean> complaintBeans = complaintDao.findComplaint(complaintBean, status);
						
						if(!MisUtility.ifEmpty(complaintBeans)){
							complaintBean = complaintBeans.get(0);
						}
						complaintBookBean.setComplaintBean(complaintBean);
						complaintBookBean.setSubject(qSubject);
						complaintBookBean.setStatus(MISConstants.TICKET_STATUS_OPEN);
						complaintBookBean.setPriority("Normal");
						
						complaintBookBean.setPublicName(qName);
						complaintBookBean.setPublicAddress(qAddress);
						complaintBookBean.setPublicEmail(qEmail);
						complaintBookBean.setPublicMobile(qMobile);
						complaintBookBean.setPublicFeedbackType(qFeedbackType);
						complaintBookBean.setDistrictId(qDistrict);
						complaintBookBean.setComplaintBookType("PUBLIC");
						
						EmployeeBean assignedEmployeeBean = new EmployeeBean();
						assignedEmployeeBean.setEmployeeId(complaintBean.getLevel1EmployeeId());
						complaintBookBean.setAssignedEmployeeBean(assignedEmployeeBean);
						
						complaintBookBean.setLocationId("SPMC");
						complaintBookBean.setDescription(qMsg);
						EmployeeBean ownedEmployeeBean = new EmployeeBean();
						ownedEmployeeBean.setEmployeeId(new Long(80000));
						complaintBookBean.setOwnedEmployeeBean(ownedEmployeeBean);
						complaintBookBean.setEntDate(loginTimestamp);
						
						ticketId = complaintBookDao.saveComplaint(complaintBookBean);
						
						ComplaintHistoryBean complaintHistoryBean = new ComplaintHistoryBean();
						complaintHistoryBean.setTicketId(ticketId);
						complaintHistoryBean.setComments(qMsg);
						
						EmployeeBean assignedEmployeeBeanInHistory = new EmployeeBean();
						assignedEmployeeBeanInHistory.setEmployeeId(complaintBean.getLevel1EmployeeId());
						complaintHistoryBean.setAssignedEmployeeBean(assignedEmployeeBeanInHistory);
						
						assignedEmpId=complaintBean.getLevel1EmployeeId();
						
						complaintHistoryBean.setEntDate(loginTimestamp);
						complaintHistoryBean.setStatus(MISConstants.TICKET_STATUS_OPEN);
						complaintHistoryBean.setEntBy(new Long(80000));
						complaintBookDao.saveComplaintHistory(complaintHistoryBean);
						
						
					} catch (DataAccessException e) {
						log.error(e.getLocalizedMessage(),e);
						throw new MISException(e);
					} catch (Exception e) {
						log.error(e.getLocalizedMessage(),e);
						throw new MISException(e);
					}
					buffer.append("<label><b>Your Complaint/Feedback Number is "+ticketId+", </b>" +
							"Please keep this number for future comunication. You can also check the status of your complaint on-line " +
							"by clicking on Queries Redressal. </label>");
					System.out.println("Complaint No.="+buffer.toString());
						
					if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
						out.print(buffer.toString());
					}
					
					System.out.println("email Start");
							
					
					//-----------------------------Email to Complainer--------------------------
					
					helper = new MimeMessageHelper(message, true);
					helper.setTo(qEmail);
					
					subject="DWSS Querry Redressal:Complaint/Feedback No. "+ticketId;
					helper.setSubject(subject);
					emailBody.append("Dear Sir/Madam, \n\n Thanks for registering your "+ qFeedbackType+".\n The registration number of your "+qFeedbackType+
							" is "+ticketId+", \n This number can be used for future " +
							"correspondance/communication and checking the status of your "+qFeedbackType+". The status of "+qFeedbackType+
							" can be checked on-line by clicking following link \n" +
							" http://www.pbdwss.gov.in/dwss/left_menu/querriesRedressal.html" +
							"\n\nRegards \n Queries Redressal-Help Desk\n Department of Water Supply and Sanitation, Punjab");
					System.out.println("Complainer emailBody: "+emailBody.toString());
					helper.setText(emailBody.toString());	
					sender.send(message);
					//-----------------------------Email to DWSS Queries Redressal-Help Desk--------------------------
					message = sender.createMimeMessage();
					helper = new MimeMessageHelper(message, true);
					/*this.employeeDao= (EmployeeDao)webApplicationContext.getBean("employeeDao");
					List<Long> employeeIds=new ArrayList<Long>();
					employeeIds.add(assignedEmpId);
					String assignedToEmail=null;
					List<EmployeeBean> employeeBeans=employeeDao.findEmployee(employeeIds);
					if(!MisUtility.ifEmpty(employeeBeans)){
						assignedToEmail=employeeBeans.get(0).getOfficialEmailId();
					}*/
					
					this.misJdcDaoImpl = (MISJdcDaoImpl)webApplicationContext.getBean("misJdcDaoImpl");
					connection = misJdcDaoImpl.getMISDataSource().getConnection();
					connection.setAutoCommit(false);
					/* if(qFeedbackType.equalsIgnoreCase("procurementComplaint")){
						helper.addTo("vrastogi@deloitte.com");
						helper.addTo("priteshmca@gmail.com");	
						helper.addCc("vickeyrastogi@gmail.com");
						helper.addCc("kumarpriteshmca@yahoo.co.in");
					}else{  */
						if(qDistrict.equals("SPMC")){
								helper.addTo("miscell.dwss@gmail.com");
							
							//helper.addCc("goeljj@gmail.com");						
						}
						else{
						
							String sql="select * from prwss_main.website_link_table where district_id='"+qDistrict+"' ";
							statement= connection.createStatement();
							ResultSet rs = statement.executeQuery(sql);
							System.out.println("Email to DWSS sql: "+sql);
							List <InternetAddress> internetAddressesTO=new ArrayList<InternetAddress>();
							List <InternetAddress> internetAddressesCC=new ArrayList<InternetAddress>();
							List <InternetAddress> internetAddressesBCC=new ArrayList<InternetAddress>();
							while(rs.next()){
								if(rs.getString("to_cc_bcc").equals("TO")){
									System.out.println("Email to DWSS TO: "+rs.getString("email_id"));
									//internetAddressesTO.add(new InternetAddress(rs.getString("email_id")));
									helper.addTo(rs.getString("email_id"));
									
								}else if(rs.getString("to_cc_bcc").equals("CC")){
									System.out.println("Email to DWSS CC: "+rs.getString("email_id"));
									//internetAddressesCC.add(new InternetAddress(rs.getString("email_id")));
									helper.addCc(rs.getString("email_id"));
									
								}else if(rs.getString("to_cc_bcc").equals("BCC")){
									System.out.println("Email to DWSS BCC: "+rs.getString("email_id"));
									//internetAddressesBCC.add(new InternetAddress(rs.getString("email_id")));
									helper.addBcc(rs.getString("email_id"));
									
								}
							}
						}
					/* } */
						/*
					helper.addTo("vrastogi@deloitte.com");
					helper.addTo("rastogi_vikash@rediffmail.com");
					*/
					
					
					
					
					
					/*if(!MisUtility.ifEmpty(internetAddressesTO)){
						System.out.println("Email to DWSS TO is ok: ");
						helper.setTo((InternetAddress[])internetAddressesTO.toArray());
					}
					if(!MisUtility.ifEmpty(internetAddressesCC)){
						System.out.println("Email to DWSS CC is ok: ");
						helper.setCc((InternetAddress[])internetAddressesCC.toArray());
					}
					if(!MisUtility.ifEmpty(internetAddressesBCC)){
						System.out.println("Email to DWSS BCC is ok: ");
						helper.setBcc((InternetAddress[])internetAddressesBCC.toArray());
					}*/					
					subject="DWSS Querry Redressal:Complaint/Feedback No. "+ticketId;
					helper.setSubject(subject);
					emailBody=new StringBuffer();
					emailBody.append("Dear Sir/Madam, \n\n Complaint/Feedback as detailed below has been received, and assigned the registration No. "
							+ticketId+"\n");
					emailBody.append("\n Name of Complainant:        "+qName);
					emailBody.append("\n E-Mail of Complainant:      "+qEmail);
					emailBody.append("\n Mobile of Complainant:      "+qMobile);
					emailBody.append("\n Address of Complainant:     "+qAddress);
					emailBody.append("\n Complaint/Feedback Type:    "+qFeedbackType);
					emailBody.append("\n Complaint/Feedback Subject: "+qSubject);
					emailBody.append("\n Complaint/Feedback Details: "+qMsg);
					emailBody.append("\n\n For further action, kindly login to MIS.");
					
					emailBody.append("\n \n Regards,");
					emailBody.append("\n DWSS Queries Redressal-Help Desk");
					
					System.out.println("Help Desk emailBody: "+emailBody.toString());
					helper.setText(emailBody.toString());
					sender.send(message);
				}
			}
			if(op.equals("querriesRedressal")){
				long ticketId=0;
				this.complaintBookBO = (ComplaintBookBO)webApplicationContext.getBean("complaintBookBO");
				this.complaintBookDao = (ComplaintBookDao)webApplicationContext.getBean("complaintBookDao");
				if(MisUtility.ifEmpty(req.getParameter("qComplaintId"))){
					ticketId =new Long( (String)req.getParameter("qComplaintId"));					
				}
				System.out.println("querriesRedressal TicketId: "+ticketId);
				
				try {
					int i=0;
				
					Set<ComplaintHistoryBean> ticketHistoryList = null;
					ComplaintBean complaintBean = new ComplaintBean();
					complaintBean.setComplaintId(ticketId);
					ComplaintBookBean complaintBookBean = complaintBookDao.findComplaint(ticketId);
					
					if(!MisUtility.ifEmpty(complaintBookBean)){
						System.out.println("querriesRedressal empty complaintBookBean: "+complaintBookBean);
						throw new Exception("No Registration found for registration No. "+ticketId); 
					}else{
						System.out.println("querriesRedressal not empty complaintBookBean: "+complaintBookBean);
					}
					ticketHistoryList = complaintBookBO.getComplaintHistory(ticketId);
					
					System.out.println("ticketHistoryList: "+ticketHistoryList);
					
					buffer.append("<table border='0' class='table-content'>");
					
					buffer.append("<tr>");
					buffer.append("<th width='200px' align='left'>"+complaintBookBean.getPublicFeedbackType()+" Registration No.</td>");
					buffer.append("<td width='400px' align='left'>"+ticketId+"</td>");
					buffer.append("</tr>");
					buffer.append("<tr>");
					buffer.append("<th align='left'>"+complaintBookBean.getPublicFeedbackType()+" Registration Date</td>");
					buffer.append("<td align='left'>"+new SimpleDateFormat("dd-MMM-yyyy").format(complaintBookBean.getEntDate())+"</td>");
					buffer.append("</tr>");
					
					buffer.append("<tr>");
					buffer.append("<th align='left'>"+complaintBookBean.getPublicFeedbackType()+" Description</td>");
					buffer.append("<td align='left'>"+complaintBookBean.getDescription()+"</td>");
					buffer.append("</tr>");
					
					buffer.append("</table>");
					
					buffer.append("</br>");
					
					buffer.append("<strong><span style='font-size: 10pt; color: #990066; font-family: Verdana'><span style='color: #0066ff'>Action Taken details are Below:</span></span></strong>");
					
					buffer.append("<table border='0' class='table-content'>");
					
					buffer.append("<tr>");
					buffer.append("<th width='10%'>S.NO.</th>");
					buffer.append("<th width='400px'>Action Taken</th>");
					buffer.append("<th width='100px'>Date</th>");
					buffer.append("<th width='100px'>Status</th>");
					buffer.append("</tr>");
					
					int cnt=ticketHistoryList.size();
					if(cnt>1){
						int innerCnt=0;
						for (ComplaintHistoryBean complaintHistoryBean : ticketHistoryList) {							
		            			if(innerCnt>0){
									buffer.append("<tr>");
					            	buffer.append("<td>"+ ++i +"</td>");
					            	buffer.append("<td>" +complaintHistoryBean.getComments() +"</td>");
					            	buffer.append("<td>" +MisUtility.convertDateToString(new Date(complaintHistoryBean.getEntDate().getTime()))+"</td>");
					            	buffer.append("<td>"+complaintHistoryBean.getStatus()+"</td>");
					            	buffer.append("</tr>");
			            		}
				            	innerCnt++;
						}
					}else{
						buffer.append("<tr>");
		            	buffer.append("<td>"+ ++i +"</td>");
		            	buffer.append("<td> Your "+complaintBookBean.getPublicFeedbackType()+" is under process.</td>");
		            	buffer.append("<td>-</td>");
		            	buffer.append("<td>Open</td>");
		            	buffer.append("</tr>");
					}
					buffer.append("</table>");
					System.out.println("querriesRedressal buffer: "+buffer.toString());
					if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
						out.print(buffer.toString());
					}
				} catch (MISException e) {
					log.error(e);
					e.printStackTrace();
				}

			}
			if(op.equals("sendSNK")){
				JavaMailSenderImpl sender1 = new JavaMailSenderImpl();
				 sender1.setUsername("snk.dwss@gmail.com");
				 sender1.setPassword("snk@dwss123");
				 sender1.setJavaMailProperties(javaMailProperties);
				 message = sender1.createMimeMessage();
				
				boolean okFlag=true;
				String sName=null,sMobile=null,sAddress=null,sMsg=null;
				if(MisUtility.ifEmpty(req.getParameter("sName"))){
					sName = (String)req.getParameter("sName");					
				}else okFlag=false;
				if(MisUtility.ifEmpty(req.getParameter("sMobile"))){
					sMobile = (String)req.getParameter("sMobile");					
				}else okFlag=false;
				if(MisUtility.ifEmpty(req.getParameter("sAddress"))){
					sAddress = (String)req.getParameter("sAddress");					
				}else okFlag=false;
				if(MisUtility.ifEmpty(req.getParameter("sMsg"))){
					sMsg = (String)req.getParameter("sMsg");					
				}else okFlag=false;
				
				if(okFlag){
					message = sender1.createMimeMessage();
					helper = new MimeMessageHelper(message, true);
										
					helper.setTo("snkhelpdesk1@gmail.com");
				//	helper.setTo("snk.dwss@gmail.com");
					subject="DWSS-Helpdesk(SNK):Complaint/Feedback";
					helper.setSubject(subject);
					emailBody=new StringBuffer();
					emailBody.append("Dear Sir/Madam, \n\n SNK Complaint/Feedback  has been received, details are below:\n");
					emailBody.append("\n Name of Complainant:          "+sName);
					emailBody.append("\n Address of Complainant:       "+sAddress);
					emailBody.append("\n Mobile of Complainant:        "+sMobile);
					emailBody.append("\n Complaint Details:            "+sMsg);
					
					emailBody.append("\n \n Regards,");
					emailBody.append("\n DWSS-Help Desk");
					
					System.out.println("SNK Help Desk emailBody: "+emailBody.toString());
					helper.setText(emailBody.toString());
					sender1.send(message);
					buffer.append("<font color='navy'>Your Complaint has been lodged successfully.</font>");
					if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
						out.print(buffer.toString());
					}
				}
			}
			if(op.equals("habitationDetails")){
				String where=" 1=1 ";
				if(MisUtility.ifEmpty(req.getParameter("villageId"))) {
					where+=" and village_id='"+req.getParameter("villageId")+"'";
				}						
				String sql="select * from prwss_main.vw_habitation_details_dwss where "+where; 
				//System.out.println("sql:"+sql);
				try {
					this.misJdcDaoImpl = (MISJdcDaoImpl)webApplicationContext.getBean("misJdcDaoImpl");
					connection = misJdcDaoImpl.getMISDataSource().getConnection();
					connection.setAutoCommit(false);
					statement= connection.createStatement();
					ResultSet rs = statement.executeQuery(sql);
					if(rs.next()){
						//System.out.println("In Rs: "+rs.getString(1));
						buffer.append("<table border='1' class='table-content'>");
						buffer.append("<tr  height='40px'>");
						buffer.append("<td style='background-color:navy;color:white' colspan='2'>Village Name</td>");
						buffer.append("<td><font color='navy'>"+rs.getString("village_name")+"</font></td>");
						buffer.append("</tr>");
						
						buffer.append("<tr height='40px'>");
						buffer.append("<td colspan='2' style='background-color:navy;color:white'>Name of Constituency</td>");
						buffer.append("<td ><font color='navy'>"+rs.getString("constituency_name")+"</font></td>");
						buffer.append("</tr>");
						
						buffer.append("<tr height='40px'>");
						buffer.append("<td colspan='2' style='background-color:navy;color:white'>Population</td>");
						buffer.append("<td ><font color='navy'>"+rs.getLong("population")+"</font></td>");
						buffer.append("</tr>");
						
						buffer.append("<tr height='40px'>");
						buffer.append("<td width='110px' rowspan='2' style='background-color:navy;color:white'>House Holds</td>");
						buffer.append("<td width='110px' style='background-color:navy;color:white'>General</td>");
						buffer.append("<td width='330px'><font color='navy'>"+rs.getLong("gc_households")+"</font></td>");
						buffer.append("</tr>");
						
						buffer.append("<tr height='40px'>");
						buffer.append("<td width='110px' style='background-color:navy;color:white'>SC</td>");
						buffer.append("<td colspan='2' width='330px'><font color='navy'>"+rs.getLong("sc_households")+"</font></td>");
						buffer.append("</tr>");
						
						buffer.append("<tr height='40px'>");
						buffer.append("<td colspan='2' style='background-color:navy;color:white'>Category</td>");
						buffer.append("<td ><font color='navy'>"+(MisUtility.ifEmpty(rs.getString("category"))?rs.getString("category"):"-")+"</font></td>");
						buffer.append("</tr>");
						
						buffer.append("<tr height='40px'>");
						buffer.append("<td colspan='2' style='background-color:navy;color:white'>Name of Water Supply Scheme</td>");
						buffer.append("<td ><font color='navy'>"+rs.getString("scheme_name")+"</font></td>");
						buffer.append("</tr>");
						
						buffer.append("<tr height='40px'>");
						buffer.append("<td colspan='2' style='background-color:navy;color:white'>Commissioning/Augmentation</td>");
						buffer.append("<td ><font color='navy'>"+rs.getString("scheme_commissioned_date")+"</font></td>");
						buffer.append("</tr>");
						
						buffer.append("<tr height='40px'>");
						buffer.append("<td colspan='2' style='background-color:navy;color:white'>Source</td>");
						buffer.append("<td ><b><font color='navy'>");
						
						if(rs.getString("scheme_source").equals(MISConstants.VILLAGE_WATER_SOURCE_CANNAL)){
							buffer.append("<input type='checkbox'  checked='checked'>Canal</input>");
							buffer.append("<input type='checkbox' >Tubewell</input>");
							buffer.append("<input type='checkbox' >Handpump</input>");
							
						/*	buffer.append("<input type='checkbox' disabled>Distribution</input>");
							buffer.append("<input type='checkbox' disabled>Lifting Water RSD Lake</input>");
							buffer.append("<input type='checkbox' disabled>Perculation Well</input>");
							buffer.append("<input type='checkbox' disabled>Ponds</input>");
							buffer.append("<input type='checkbox' disabled>Roof Top</input>");*/
							
						}else if(rs.getString("scheme_source").equals(MISConstants.VILLAGE_WATER_SOURCE_TUBE_WELL)||
								rs.getString("scheme_source").equals(MISConstants.VILLAGE_WATER_SOURCE_TUBE_WELL_WITH_RO)){
							buffer.append("<input type='checkbox' >Canal</input>");
							buffer.append("<input type='checkbox' checked='checked'>Tubewell</input>");
							buffer.append("<input type='checkbox' >Handpump</input>");
							
							/*buffer.append("<input type='checkbox' disabled>Distribution</input>");
							buffer.append("<input type='checkbox' disabled>Lifting Water RSD Lake</input>");
							buffer.append("<input type='checkbox' disabled>Perculation Well</input>");
							buffer.append("<input type='checkbox' disabled>Ponds</input>");
							buffer.append("<input type='checkbox' disabled>Roof Top</input>");*/
						}else if(rs.getString("scheme_source").equals(MISConstants.VILLAGE_WATER_SOURCE_HAND_PUMP)){
							buffer.append("<input type='checkbox' >Canal</input>");
							buffer.append("<input type='checkbox' >Tubewell</input>");
							buffer.append("<input type='checkbox' checked='checked'>Handpump</input>");
							
							/*buffer.append("<input type='checkbox' disabled>Distribution</input>");
							buffer.append("<input type='checkbox' disabled>Lifting Water RSD Lake</input>");
							buffer.append("<input type='checkbox' disabled>Perculation Well</input>");
							buffer.append("<input type='checkbox' disabled>Ponds</input>");
							buffer.append("<input type='checkbox' disabled>Roof Top</input>");*/
						}else if(rs.getString("scheme_source").equals(MISConstants.VILLAGE_WATER_SOURCE_DISTRIBUTION)){
							buffer.append("<input type='checkbox' >Canal</input>");
							buffer.append("<input type='checkbox' >Tubewell</input>");
							buffer.append("<input type='checkbox' >Handpump</input>");
							
							/*buffer.append("<input type='checkbox' disabled checked='checked'>Distribution</input>");
							buffer.append("<input type='checkbox' disabled>Lifting Water RSD Lake</input>");
							buffer.append("<input type='checkbox' disabled>Perculation Well</input>");
							buffer.append("<input type='checkbox' disabled>Ponds</input>");
							buffer.append("<input type='checkbox' disabled>Roof Top</input>");*/
						}else if(rs.getString("scheme_source").equals(MISConstants.VILLAGE_WATER_SOURCE_LIFTING_WATER_RSD_LAKE)){
							buffer.append("<input type='checkbox' >Canal</input>");
							buffer.append("<input type='checkbox' >Tubewell</input>");
							buffer.append("<input type='checkbox' >Handpump</input>");
							
							/*buffer.append("<input type='checkbox' disabled>Distribution</input>");
							buffer.append("<input type='checkbox' disabled checked='checked'>Lifting Water RSD Lake</input>");
							buffer.append("<input type='checkbox' disabled>Perculation Well</input>");
							buffer.append("<input type='checkbox' disabled>Ponds</input>");
							buffer.append("<input type='checkbox' disabled>Roof Top</input>");*/
						}else if(rs.getString("scheme_source").equals(MISConstants.VILLAGE_WATER_SOURCE_PERCULATION_WELL)){
							buffer.append("<input type='checkbox' >Canal</input>");
							buffer.append("<input type='checkbox' >Tubewell</input>");
							buffer.append("<input type='checkbox' >Handpump</input>");
							
							/*buffer.append("<input type='checkbox' disabled>Distribution</input>");
							buffer.append("<input type='checkbox' disabled>Lifting Water RSD Lake</input>");
							buffer.append("<input type='checkbox' disabled checked='checked'>Perculation Well</input>");
							buffer.append("<input type='checkbox' disabled>Ponds</input>");
							buffer.append("<input type='checkbox' disabled>Roof Top</input>");*/
						}else if(rs.getString("scheme_source").equals(MISConstants.VILLAGE_WATER_SOURCE_PONDS)){
							buffer.append("<input type='checkbox' >Canal</input>");
							buffer.append("<input type='checkbox' >Tubewell</input>");
							buffer.append("<input type='checkbox' >Handpump</input>");
							
							/*buffer.append("<input type='checkbox' disabled>Distribution</input>");
							buffer.append("<input type='checkbox' disabled>Lifting Water RSD Lake</input>");
							buffer.append("<input type='checkbox' disabled>Perculation Well</input>");
							buffer.append("<input type='checkbox' disabled checked='checked'>Ponds</input>");
							buffer.append("<input type='checkbox' disabled>Roof Top</input>");*/
						}else if(rs.getString("scheme_source").equals(MISConstants.VILLAGE_WATER_SOURCE_ROOF_TOP)){
							buffer.append("<input type='checkbox' >Canal</input>");
							buffer.append("<input type='checkbox' >Tubewell</input>");
							buffer.append("<input type='checkbox' >Handpump</input>");
							/*
							buffer.append("<input type='checkbox' disabled>Distribution</input>");
							buffer.append("<input type='checkbox' disabled>Lifting Water RSD Lake</input>");
							buffer.append("<input type='checkbox' disabled>Perculation Well</input>");
							buffer.append("<input type='checkbox' disabled>Ponds</input>");
							buffer.append("<input type='checkbox' disabled checked='checked'>Roof Top</input>");*/
						}
						
						buffer.append("</font></b></td>");
						buffer.append("</tr>");
						if(MisUtility.ifEmpty(rs.getString("scheme_operated_by"))){
						if(rs.getString("scheme_operated_by").equals("GP-Self")){
						buffer.append("<tr height='40px'>");
						buffer.append("<td width='110px' rowspan='3' style='background-color:navy;color:white'>W/S operated By</td>");
						buffer.append("<td width='110px' style='background-color:navy;color:white'>GP</td>");
						buffer.append("<td colspan='2' width='330px'><b><font color='navy'><input type='checkbox' checked='checked' >Self</input><input type='checkbox' >Outsourced</input><b></font></td>");
						buffer.append("</tr>");
						
						buffer.append("<tr height='40px'>");
						buffer.append("<td width='110px' style='background-color:navy;color:white'>GPWSC</td>");
						buffer.append("<td colspan='2' width='330px'><b><font color='navy'><input type='checkbox' >Self</input><input type='checkbox' >Outsourced</input><b></font></td>");
						buffer.append("</tr>");
						
						buffer.append("<tr height='40px'>");
						buffer.append("<td width='110px' style='background-color:navy;color:white'>DWSS</td>");
						buffer.append("<td colspan='2' width='330px'><b><font color='navy'><input type='checkbox' >Self</input><input type='checkbox' >Outsourced</input><b></font></td>");
						buffer.append("</tr>");
						}else if(rs.getString("scheme_operated_by").equals("GP-Outsourced")){
							buffer.append("<tr height='40px'>");
							buffer.append("<td width='110px' rowspan='3' style='background-color:navy;color:white'>W/S operated By</td>");
							buffer.append("<td width='110px' style='background-color:navy;color:white'>GP</td>");
							buffer.append("<td colspan='2' width='330px'><b><font color='navy'><input type='checkbox' >Self</input><input type='checkbox' checked='checked'>Outsourced</input><b></font></td>");
							buffer.append("</tr>");
							
							buffer.append("<tr height='40px'>");
							buffer.append("<td width='110px' style='background-color:navy;color:white'>GPWSC</td>");
							buffer.append("<td colspan='2' width='330px'><b><font color='navy'><input type='checkbox' >Self</input><input type='checkbox' >Outsourced</input><b></font></td>");
							buffer.append("</tr>");
							
							buffer.append("<tr height='40px'>");
							buffer.append("<td width='110px' style='background-color:navy;color:white'>DWSS</td>");
							buffer.append("<td colspan='2' width='330px'><b><font color='navy'><input type='checkbox' >Self</input><input type='checkbox' >Outsourced</input><b></font></td>");
							buffer.append("</tr>");
							
						}else if(rs.getString("scheme_operated_by").equals("DWSS-Outsourced")){
							buffer.append("<tr height='40px'>");
							buffer.append("<td width='110px' rowspan='3' style='background-color:navy;color:white'>W/S operated By</td>");
							buffer.append("<td width='110px' style='background-color:navy;color:white'>GP</td>");
							buffer.append("<td colspan='2' width='330px'><b><font color='navy'><input type='checkbox' >Self</input><input type='checkbox' >Outsourced</input><b></font></td>");
							buffer.append("</tr>");
							
							buffer.append("<tr height='40px'>");
							buffer.append("<td width='110px' style='background-color:navy;color:white'>GPWSC</td>");
							buffer.append("<td colspan='2' width='330px'><b><font color='navy'><input type='checkbox' >Self</input><input type='checkbox' >Outsourced</input><b></font></td>");
							buffer.append("</tr>");
							
							buffer.append("<tr height='40px'>");
							buffer.append("<td width='110px' style='background-color:navy;color:white'>DWSS</td>");
							buffer.append("<td colspan='2' width='330px'><b><font color='navy'><input type='checkbox' >Self</input><input type='checkbox' checked='checked'>Outsourced</input><b></font></td>");
							buffer.append("</tr>");
							
						}else if(rs.getString("scheme_operated_by").equals("DWSS-Self")){
							buffer.append("<tr height='40px'>");
							buffer.append("<td width='110px' rowspan='3' style='background-color:navy;color:white'>W/S operated By</td>");
							buffer.append("<td width='110px' style='background-color:navy;color:white'>GP</td>");
							buffer.append("<td colspan='2' width='330px'><b><font color='navy'><input type='checkbox' >Self</input><input type='checkbox' >Outsourced</input><b></font></td>");
							buffer.append("</tr>");
							
							buffer.append("<tr height='40px'>");
							buffer.append("<td width='110px' style='background-color:navy;color:white'>GPWSC</td>");
							buffer.append("<td colspan='2' width='330px'><b><font color='navy'><input type='checkbox' >Self</input><input type='checkbox' >Outsourced</input><b></font></td>");
							buffer.append("</tr>");
							
							buffer.append("<tr height='40px'>");
							buffer.append("<td width='110px' style='background-color:navy;color:white'>DWSS</td>");
							buffer.append("<td colspan='2' width='330px'><b><font color='navy'><input type='checkbox' checked='checked'>Self</input><input type='checkbox' >Outsourced</input><b></font></td>");
							buffer.append("</tr>");
							
						}else if(rs.getString("scheme_operated_by").equals("GPWSC-Self")){
							buffer.append("<tr height='40px'>");
							buffer.append("<td width='110px' rowspan='3' style='background-color:navy;color:white'>W/S operated By</td>");
							buffer.append("<td width='110px' style='background-color:navy;color:white'>GP</td>");
							buffer.append("<td colspan='2' width='330px'><b><font color='navy'><input type='checkbox' >Self</input><input type='checkbox' >Outsourced</input><b></font></td>");
							buffer.append("</tr>");
							
							buffer.append("<tr height='40px'>");
							buffer.append("<td width='110px' style='background-color:navy;color:white'>GPWSC</td>");
							buffer.append("<td colspan='2' width='330px'><b><font color='navy'><input type='checkbox' checked='checked'>Self</input><input type='checkbox' >Outsourced</input><b></font></td>");
							buffer.append("</tr>");
							
							buffer.append("<tr height='40px'>");
							buffer.append("<td width='110px' style='background-color:navy;color:white'>DWSS</td>");
							buffer.append("<td colspan='2' width='330px'><b><font color='navy'><input type='checkbox' >Self</input><input type='checkbox' >Outsourced</input><b></font></td>");
							buffer.append("</tr>");
							
						}else if(rs.getString("scheme_operated_by").equals("GPWSC-Outsourced")){
							buffer.append("<tr height='40px'>");
							buffer.append("<td width='110px' rowspan='3' style='background-color:navy;color:white'>W/S operated By</td>");
							buffer.append("<td width='110px' style='background-color:navy;color:white'>GP</td>");
							buffer.append("<td colspan='2' width='330px'><b><font color='navy'><input type='checkbox' >Self</input><input type='checkbox' >Outsourced</input><b></font></td>");
							buffer.append("</tr>");
							
							buffer.append("<tr height='40px'>");
							buffer.append("<td width='110px' style='background-color:navy;color:white'>GPWSC</td>");
							buffer.append("<td colspan='2' width='330px'><b><font color='navy'><input type='checkbox' >Self</input><input type='checkbox' checked='checked' >Outsourced</input><b></font></td>");
							buffer.append("</tr>");
							
							buffer.append("<tr height='40px'>");
							buffer.append("<td width='110px' style='background-color:navy;color:white'>DWSS</td>");
							buffer.append("<td colspan='2' width='330px'><b><font color='navy'><input type='checkbox' >Self</input><input type='checkbox' >Outsourced</input><b></font></td>");
							buffer.append("</tr>");
							
						}
						}else {
							System.out.println("inside nullll");
							buffer.append("<tr height='40px'>");
							buffer.append("<td width='110px' rowspan='3' style='background-color:navy;color:white'>W/S operated By</td>");
							buffer.append("<td width='110px' style='background-color:navy;color:white'>GP</td>");
							buffer.append("<td colspan='2' width='330px'><b><font color='navy'><input type='checkbox' >Self</input><input type='checkbox' >Outsourced</input><b></font></td>");
							buffer.append("</tr>");
							
							buffer.append("<tr height='40px'>");
							buffer.append("<td width='110px' style='background-color:navy;color:white'>GPWSC</td>");
							buffer.append("<td colspan='2' width='330px'><b><font color='navy'><input type='checkbox' >Self</input><input type='checkbox'>Outsourced</input><b></font></td>");
							buffer.append("</tr>");
							
							buffer.append("<tr height='40px'>");
							buffer.append("<td width='110px' style='background-color:navy;color:white'>DWSS</td>");
							buffer.append("<td colspan='2' width='330px'><b><font color='navy'><input type='checkbox' >Self</input><input type='checkbox' >Outsourced</input><b></font></td>");
							buffer.append("</tr>");
							
						}
						
												
						buffer.append("<tr height='40px'>");
						buffer.append("<td colspan='2' style='background-color:navy;color:white'>No. of Water Connection</td>");
						buffer.append("<td ><font color='navy'>"+rs.getLong("water_connection")+"</font></td>");
						buffer.append("</tr>");
						buffer.append("</table>");
						
					}else{
						buffer.append("No-Data found");
					}
					System.out.println("Buffer: "+buffer.toString());
					if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
						out.print(buffer.toString());
					}
				} catch (SQLException e) {
					if (connection != null) {
				        try {
				        	connection.rollback();
				        } catch(SQLException excep) {
				        	excep.printStackTrace();
				        }
				      }
				}finally {
					if (statement!= null) { 
						statement.close(); 
					}
				    connection.setAutoCommit(true);
				    connection.close();
				}
			}
		} catch (BeansException e) {
			log.error(e.getLocalizedMessage(),e);
		} catch(Exception e){
			log.error(e.getLocalizedMessage(),e);
		}
	}
	
}
