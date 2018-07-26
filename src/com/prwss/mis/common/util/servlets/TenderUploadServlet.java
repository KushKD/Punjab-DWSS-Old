package com.prwss.mis.common.util.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.prwss.mis.common.MISJdcDaoImpl;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.tender.TenderBO;
import com.prwss.mis.tender.dao.AdvUploadBean;
import com.prwss.mis.tender.dao.CorrigendumUploadBean;
import com.prwss.mis.tender.dao.EOIUploadBean;
import com.prwss.mis.tender.dao.QuoatationUploadBean;
import com.prwss.mis.tender.dao.TenderUploadBean;

public class TenderUploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8181613843355096252L;
	private Logger log = Logger.getLogger(TenderUploadServlet.class);
	private MISJdcDaoImpl misJdcDaoImpl;
	private TenderBO tenderBO;
	
	
	public TenderBO getTenderBO() {
		return tenderBO;
	}
	public void setTenderBO(TenderBO tenderBO) {
		this.tenderBO = tenderBO;
	}
	public void setMisJdcDaoImpl(MISJdcDaoImpl misJdcDaoImpl) {
		this.misJdcDaoImpl = misJdcDaoImpl;
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getDoc(request,response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getDoc(request,response);
	}
	protected void getDoc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String docType=request.getParameter("docType");
		System.out.println("docType: "+docType);
		if(docType.equals("tender")){
			getTender(request, response);
		}else if (docType.equals("eoi")){
			getEOI(request, response);
		}else if (docType.equals("quoatation")){
			getQuoatation(request, response);
		}else if (docType.equals("adv")){
			getAdv(request, response);
		}else if (docType.equals("conAward")){
			getContractAward(request, response);
		}else if (docType.equals("cUploadS")){
			getUploadedDocs(request,response);
		}else if (docType.equals("oldTender")){
			getOldTender(request,response);
		}else if (docType.equals("oldQuoatation")){
			getOldQuoatation(request,response);
		}else if (docType.equals("oldEoi")){
			getOldEOI(request,response);
		}else if (docType.equals("oldAdv")){
			getOldAdv(request, response);
		}		
		else if (docType.equals("conAwardPopup")){			
			getContractAwardPopup(request,response);
		}
	}
	protected void getContractAwardPopup(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		Connection connection = null;
		try { 
			WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
			this.misJdcDaoImpl=(MISJdcDaoImpl)webApplicationContext.getBean("misJdcDaoImpl");
			this.tenderBO = (TenderBO)webApplicationContext.getBean("tenderBO");
		//	System.out.println("misJdcDaoImpl: "+misJdcDaoImpl);
			connection = misJdcDaoImpl.getMISDataSource().getConnection();
		//	System.out.println("connection: "+connection);
			Statement stmt = connection.createStatement();
			String op=request.getParameter("op");
			String package_id = request.getParameter("package_id");
			String buffer="";
			if(op!=null && op.equalsIgnoreCase("popup_content")){
				String where="";
				System.out.println("INside Contract Award Bidder List");
				if(package_id=="null"){
					package_id = null;
				
				}
				else{
					where=package_id!=null?" where package_id='"+package_id+"'":"";					
				}
				
					
				System.out.println("select * from prwss_main.vw_contract_bidder"+where);
	            ResultSet rs = stmt.executeQuery("select * from prwss_main.vw_contract_bidder"+where );
	              
	            buffer="<table border='0' class='table-content'><tr><th>S.NO.</th>" +
	            		"<th>Bidder Name.</th>" +
	            		"<th>Bid Amount</th>" +
	            		"<th>Bid Submitted</th>"+
	            		"<th>Tender id</th>" +
	            		"<th>Responsive/Non-Responsive/Others </th>" +
	            			            		"</tr>";
	            
	            int i=0;
	        /*    String Responsive=rs.getString("not_responsive");
	            System.out.println("------------------------------"+Responsive);
	            if(Responsive.equals("Select")|| Responsive.equals("Select") || Responsive.equals("Select"))
	            {
	            	Responsive="REPONSIVE";
	            }else
	            {
	            	Responsive="NOTRESPONSIVE";
	            }*/
	          
	            while(rs.next()){
	            	System.out.println(rs.getString("bidder_name"));
	            	      String Responsive=rs.getString("not_responsive");
	            	      String bidder_name=rs.getString("bidder_name");
	            	      String vendor_id=rs.getString("vendor_id");
	            	      String vendor_name=rs.getString("vendor_name");
	            	      System.out.println("bidder_name=="+bidder_name+"vendor_id"+vendor_id+"vendor_name=="+vendor_name);
		            if(Responsive.equals("Select")|| Responsive.equals("Select") || Responsive.equals("Select"))
		            {
		            	Responsive="REPONSIVE";
		            }else if(Responsive.equals("Others")|| Responsive.equals("OTHERS") || Responsive.equals("others"))
		            {
		            	Responsive="Higher Rate";
		            }
		            else
		            {
		            	Responsive="NON-REPONSIVE";
		            }
		            if(bidder_name.equals(vendor_id)||bidder_name==vendor_id){
            		bidder_name=vendor_name;
		            }
		            buffer+="<tr><td>"+ ++i +"</td>" +
            		
            		"<td>"+bidder_name+"</td>" +		
            		"<td>"+rs.getString("bid_amount")+"</td>" +
            		"<td>"+rs.getString("bid_submitted")+"</td>"+
            		"<td>"+rs.getString("tender_id")+"("+rs.getString("scheme_name")+")"+"</td>"+
            		"<td>"+Responsive+"</td>" +
            
            		
            				"</tr>";	
	            }
	            buffer+="</table>";
	            PrintWriter out = response.getWriter();
				if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
					out.print(buffer.toString());
				}
			}            
            connection.close();        	
		}catch (Exception e) { 
			e.printStackTrace();			
		}	
		finally{
			try{
				if(connection !=  null){
					connection.close();
				}
			}catch(SQLException sq){
				sq.printStackTrace();
			}
		}
		
	}
	protected void getUploadedDocs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		Connection connection = null;
		try { 
			WebApplicationContext webApplicationContext = 
				WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
			this.misJdcDaoImpl=(MISJdcDaoImpl)webApplicationContext.getBean("misJdcDaoImpl");
			connection = misJdcDaoImpl.getMISDataSource().getConnection();
			Statement stmt = connection.createStatement();
			String locationId=request.getParameter("locationId");
			String corrigendumFor=request.getParameter("corrigendumFor");
			StringBuffer buffer = new StringBuffer();
			String sql="";
			if(corrigendumFor.equals("TENDER")){
				sql="select tender_id from prwss_main.tender_upload where location_id='"+locationId+"'";
			}else if(corrigendumFor.equals("EOI")){
				sql="select eoi_referance_no from prwss_main.eoi_upload where location_id='"+locationId+"'";
			}else if(corrigendumFor.equals("QUOTATION")){
				sql="select quoatation_upload_id from prwss_main.quoatation_upload where location_id='"+locationId+"'";
			}
			System.out.println("SQL: "+sql);
			if(MisUtility.ifEmpty(sql) && sql.length() > 1 ){
				ResultSet rs = stmt.executeQuery(sql);
				buffer.append("<option value='' selected>");
				buffer.append("Select");
				buffer.append("</option>");
	            while (rs.next()){
	            	buffer.append("<option value=\"").append(rs.getString(1)).append("\">");
	            	buffer.append(rs.getString(1));
	            	buffer.append("</option>");
	            }
	            System.out.println("buffer"+buffer.toString());
	            PrintWriter out = response.getWriter();
				if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
					out.print(buffer.toString());
				}
			}            
            connection.close();        	
		}catch (Exception e) { 
			e.printStackTrace();			
		}	
		finally{
			try{
				if(connection !=  null){
					connection.close();
				}
			}catch(SQLException sq){
				sq.printStackTrace();
			}
		}
	}
			
	protected void getTender(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("Start");
		response.setContentType("text/html");
		Connection connection = null;
		try { 
			WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
			this.misJdcDaoImpl=(MISJdcDaoImpl)webApplicationContext.getBean("misJdcDaoImpl");
			this.tenderBO = (TenderBO)webApplicationContext.getBean("tenderBO");
			connection = misJdcDaoImpl.getMISDataSource().getConnection();
			Statement stmt = connection.createStatement();
			String op=request.getParameter("op");
			String buffer="";
			if(op!=null && op.equalsIgnoreCase("display")){
				String district_id=request.getParameter("district_id");
				if(district_id=="null"){
					district_id = null;
				}				
				String where="";
				if (district_id.equals("SPMC"))	
					where=district_id!=null?" where a.location_id='"+district_id+"' and a.status<>'D'":"";
				else if (district_id.equals("All"))	
					where=district_id!=null?" where  a.district_id in ('D02','D19','D16','D15','D10','D13','D01','D06','D04','D05','D11','D17','D12','D14','D07','D20','D21','D22','D08','D18','D09','D03') and a.status<>'D'":"";
				else
					where=district_id!=null?" where a.district_id='"+district_id+"' and a.status<>'D'":"";
				System.out.println("where"+where);
	            ResultSet rs = stmt.executeQuery("select * from prwss_main.tender_upload a " +
	            		"left outer join (select * from prwss_main.corrigendum_upload where status<>'D' and corrigendum_for='TENDER') b on a.tender_id=b.doc_id "+where+
	            " order by a.tender_upload_id, b.corrigendum_upload_id "		
	            );
	            
	            buffer="<table border='0' class='table-content'>" +
	            		"<tr>" +
	            		"<th width='10%'>S.NO.</th>" +
	            		"<th >Description of Work</th>" +
	            		"<th width='20'>Tender Notice/IFB</th>" +
	            		"<th width='20'>Bid Document/DNIT</th>" +
	            		"<th width='20'>Bids Opening date (DD/MM/YYYY)</th>" +
	            		"<th width='20'>Last date for Receipt of Bids (DD/MM/YYYY)</th>" +
	            		"<th width='20'>Corrigendum</th>" +
	            		"</tr>";
	            int i=0;
	            int j=0;
	            String tenderUploadId="";
	            String tempTenderUploadId="";
	            String tenderCorringdum="";
	            while(rs.next()){
	            	Date exDate = rs.getDate("expiry_date");
	            	if(MisUtility.ifEmpty(exDate)){
	            	Date expiryDate = new Date(exDate.getTime()+(1000*60*60*24));
	            //	System.out.println("Expiry"+expiryDate);
	            	
	            	if(MisUtility.ifEmpty(expiryDate) && expiryDate.after(MisUtility.convertStringToDate(MisUtility.now("dd-MM-yyyy")))){	            		
	            		tenderUploadId=rs.getString("tender_upload_id");
	            		//System.out.println(i+":tempTenderUploadId:"+tempTenderUploadId+":tenderUploadId:"+tenderUploadId);	            		
	            		if(!tempTenderUploadId.equals(tenderUploadId)){
	            			if(!tempTenderUploadId.equals(""))
	            				buffer+="<td>" +tenderCorringdum+"</td>" +"</tr>";		            		
	            			buffer+="<tr>" +
			            				"<td>"+ ++i +"</td>" +
			            				"<td>"+rs.getString("work_description")+"</td>" +
			            				"<td>" +
			            					"<a href='/PRWSS_MIS/TenderUploadServlet.to?op=download&type=tender&docType=tender&tender_upload_id="+
			            						tenderUploadId +"' target='_blank'>Tender Notice" +
			            					"</a>" +
			            				"</td>" +
			            				"<td>" +
			            					"<a href='/PRWSS_MIS/TenderUploadServlet.to?op=download&type=bid&docType=tender&tender_upload_id="+
			            						rs.getString("tender_upload_id") +"' target='_blank'>" +
			            						"Bid Document" +
			            					"</a>" +
			            				"</td>"
			            				+"</td>" +
			            				"<td>"+MisUtility.convertDateToString(rs.getDate("bid_opening_date"))+"</td>" +
			            				"<td>"+MisUtility.convertDateToString(rs.getDate("last_date"))+"</td>" ;	
		            	}
	            		/*if(tempTenderUploadId.equals("") && tenderUploadId.equals("")){
	            			tenderCorringdum="";
	            			j=0;
	            		}*/
	            		if(!tempTenderUploadId.equals(tenderUploadId)){
	            			tenderCorringdum="";
	            			j=0;
	            		}
	            		if(MisUtility.ifEmpty(rs.getString("corrigendum_upload_id")) && MisUtility.ifEmpty(tenderUploadId)){            			
	            			tenderCorringdum+=	++j+". <a href='/PRWSS_MIS/TenderUploadServlet.to?op=download&type=tenderCorrigendum&docType=tender&corrigendum_upload_id="+
	            						rs.getString("corrigendum_upload_id") +"' target='_blank'>Corrigendum" +
	            					"</a><br>" ;
	            		}
	            	}
	            	}
	            	tempTenderUploadId=tenderUploadId=rs.getString("tender_upload_id");
	            }
	            buffer+="<td>" +tenderCorringdum+"</td>" +"</tr>";
	            buffer+="</table>";
	            System.out.println("Buffer: "+buffer);
	            PrintWriter out = response.getWriter();
				if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
					out.print(buffer.toString());
				}
			}
			if(op!=null && op.equalsIgnoreCase("download")){
				String tender_upload_id=request.getParameter("tender_upload_id");
				String corrigendumUploadId=request.getParameter("corrigendum_upload_id");
				//System.out.println("tender_upload_id"+tender_upload_id);
				String where=tender_upload_id!=null?" where tender_upload_id='"+tender_upload_id+"'":"";	            
	            ServletOutputStream servletOutputStream=response.getOutputStream();
				response.setContentType("application/pdf"); 
				response.addHeader("Content-Disposition","attachment; filename=Tender.pdf");
	            	String type=request.getParameter("type");
	            	//System.out.println("Type"+type);
	            	byte[] imgBytes=null;
	            	
	        		//System.out.println("Inside");
	            	if(type!= null && type.equalsIgnoreCase("tender")){
	            		TenderUploadBean tenderUploadBean = new TenderUploadBean();
	    	            tenderUploadBean.setTenderUploadId(new Long(tender_upload_id));
	    				TenderUploadBean tenderUploadBeans  = tenderBO.getTenderUploaded(tenderUploadBean).get(0);
	            		imgBytes = tenderUploadBeans.getTenderNoticeFile();
	            		//System.out.println(imgBytes.length);
	            		//System.out.println(imgBytes.toString());
	            	}else if(type!= null && type.equalsIgnoreCase("bid")){ 
	            		TenderUploadBean tenderUploadBean = new TenderUploadBean();
	    	            tenderUploadBean.setTenderUploadId(new Long(tender_upload_id));
	    				TenderUploadBean tenderUploadBeans  = tenderBO.getTenderUploaded(tenderUploadBean).get(0);
	            		imgBytes = tenderUploadBeans.getBidDocFile();
	            	}else if(type!= null && type.equalsIgnoreCase("tenderCorrigendum")){ 
	            		CorrigendumUploadBean corrigendumUploadBean=new CorrigendumUploadBean();
	            		corrigendumUploadBean.setCorrigendumUploadId(new Long(corrigendumUploadId));
	            		CorrigendumUploadBean corrigendumUploadBeans  = tenderBO.getCorrigendumUploaded(corrigendumUploadBean).get(0);
	            		imgBytes = corrigendumUploadBeans.getCorrigendumFile();
	            	}
	            	int len = imgBytes.length;
	            	response.setContentLength(len);
	            	servletOutputStream.write(imgBytes);
	            	servletOutputStream.flush();
	            	servletOutputStream.close();
			}            
            connection.close();        	
		}catch (Exception e) { 
			e.printStackTrace();			
		}	
		finally{
			try{
				if(connection !=  null){
					connection.close();
				}
			}catch(SQLException sq){
				sq.printStackTrace();
			}
		}
	}
	protected void getEOI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("EOI Start");
		response.setContentType("text/html");
		Connection connection = null;
		try { 
			WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
			this.misJdcDaoImpl=(MISJdcDaoImpl)webApplicationContext.getBean("misJdcDaoImpl");
			this.tenderBO = (TenderBO)webApplicationContext.getBean("tenderBO");
			connection = misJdcDaoImpl.getMISDataSource().getConnection();
			Statement stmt = connection.createStatement();
			String op=request.getParameter("op");
			String buffer="";
			if(op!=null && op.equalsIgnoreCase("display")){
				 String district_id=request.getParameter("district_id");
				System.out.println("district id before set==="+district_id);
				/*if(district_id=="Select District"||district_id.equals("Select District")){
					district_id = "All";
				}*/
				System.out.println("district id after set==="+district_id);
				
				if(district_id=="null"){
					district_id = null;
				}
				String where="";
				if (district_id.equals("SPMC"))	
					where=district_id!=null?" where a.location_id='"+district_id+"' and a.status<>'D'":"";
				else if (district_id.equals("All"))	
					where=district_id!=null?" where  a.district_id in ('D02','D19','D16','D15','D10','D13','D01','D06','D04','D05','D11','D17','D12','D14','D07','D20','D21','D22','D08','D18','D09','D03') and a.status<>'D'":"";
				else
					where=district_id!=null?" where a.district_id='"+district_id+"' and a.status<>'D'":"";

				System.out.println("where"+where);
	            ResultSet rs = stmt.executeQuery("select * from prwss_main.eoi_upload a " +
	            		"left outer join (select * from prwss_main.corrigendum_upload where status<>'D' and corrigendum_for='EOI' ) b on a.eoi_referance_no=b.doc_id "+where+
	            " order by a.eoi_upload_id, b.corrigendum_upload_id "		
	            );

	            
	            buffer="<table border='0' class='table-content'>" +
	            		"<tr>" +
	            		"<th width='10%'>S.NO.</th>" +
	            		"<th >EOI reference No.</th>" +
	            		"<th >EOI Title</th>" +
	            		"<th width='20'>EOI File 1</th>" +
	            		"<th width='20'>EOI File 2</th>" +
	            		"<th width='20'>EOI File 3</th>" +
	            		"<th width='20'>Expiry Date</th>"+
	            		"<th width='20'>Corrigendum</th>" +
	            		"</tr>";
	            int i=0;
	            int j=0;
	            String tenderUploadId="";
	            String tempTenderUploadId="";
	            String tenderCorringdum="";

	            while(rs.next()){
	            	Date exDate = rs.getDate("expiry_date");
	            	if(MisUtility.ifEmpty(exDate)){
	            	Date expiryDate = new Date(exDate.getTime()+(1000*60*60*24));
	            //	System.out.println("Expiry"+expiryDate);
	            	if(MisUtility.ifEmpty(expiryDate) && expiryDate.after(MisUtility.convertStringToDate(MisUtility.now("dd-MM-yyyy")))){
	            		tenderUploadId=rs.getString("eoi_upload_id");
	            		if(!tempTenderUploadId.equals(tenderUploadId)){
	            			if(!tempTenderUploadId.equals(""))
	            				buffer+="<td>" +tenderCorringdum+"</td>" +"</tr>";

		            		buffer+="<tr><td>"+ ++i +"</td>"+
		            		"<td>"+rs.getString("eoi_referance_no")+"</td>" +
		            		"<td>"+rs.getString("eoi_title")+"</td>";
		            		if(rs.getString("eoi_file_name1") !=null){
		            		buffer+="<td>" +
		            			"<a href='/PRWSS_MIS/TenderUploadServlet.to?op=download&type=eoiFile1&docType=eoi&eoi_upload_id="+
		            				rs.getString("eoi_upload_id") +"' target='_blank'>EOI File 1" +
		            			"</a>" +
		            		"</td>" ;
		            		}
		            		else{
		            			buffer+="<td></td>";
		            		}
		            		if(rs.getString("eoi_file_name2") !=null){
		            		buffer+="<td>" +
		            			"<a href='/PRWSS_MIS/TenderUploadServlet.to?op=download&type=eoiFile2&docType=eoi&eoi_upload_id="+
		            				rs.getString("eoi_upload_id") +"' target='_blank'>EOI File 2" +
		            			"</a>" +
		            		"</td>";
		            		}
		            		else{
		            			buffer+="<td></td>";
		            		}
		            		if(rs.getString("eoi_file_name3") !=null){
		            		buffer+="<td>" +
	            				"<a href='/PRWSS_MIS/TenderUploadServlet.to?op=download&type=eoiFile3&docType=eoi&eoi_upload_id="+
	            				rs.getString("eoi_upload_id") +"' target='_blank'>EOI File 3" +
	            				"</a>" +
	            			"</td>";
		            		}
		            		else{
		            			buffer+="<td></td>";
		            		}
		            		buffer+="<td>"+
            				rs.getString("expiry_date") +
            			"</td>";
	            		}
	            		if(!tempTenderUploadId.equals(tenderUploadId)){
	            			tenderCorringdum="";
	            			j=0;
	            		}
	            		if(MisUtility.ifEmpty(rs.getString("corrigendum_upload_id")) && MisUtility.ifEmpty(tenderUploadId)){            			
	            			tenderCorringdum+=	++j+". <a href='/PRWSS_MIS/TenderUploadServlet.to?op=download&type=eoiCorrigendum&docType=eoi&corrigendum_upload_id="+
	            						rs.getString("corrigendum_upload_id") +"' target='_blank'>Corrigendum" +
	            					"</a><br>" ;
	            		}
	            	}
	            	}
	            	tempTenderUploadId=tenderUploadId;
	            }
	            buffer+="<td>" +tenderCorringdum+"</td>" +"</tr>";
	            buffer+="</table>";	            	
	           // System.out.println("Buffer: "+buffer);
	            PrintWriter out = response.getWriter();
				if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
					out.print(buffer.toString());
				}
			}
			if(op!=null && op.equalsIgnoreCase("download")){
				String eoi_upload_id=request.getParameter("eoi_upload_id");
				String corrigendumUploadId=request.getParameter("corrigendum_upload_id");
				//System.out.println("eoi_upload_id"+eoi_upload_id);
				String where=eoi_upload_id!=null?" where eoi_upload_id='"+eoi_upload_id+"'":"";	            
	            ServletOutputStream servletOutputStream=response.getOutputStream();
				response.setContentType("application/pdf"); 
				response.addHeader("Content-Disposition","attachment; filename=Tender.pdf");
	            	String type=request.getParameter("type");
	            	//System.out.println("Type"+type);
	            	byte[] imgBytes=null;
	            	
	        		//System.out.println("Inside");
	            	if(type!= null && type.equalsIgnoreCase("eoiFile1")){
	            		EOIUploadBean eoiUploadBean = new EOIUploadBean();
	    	            eoiUploadBean.setEoiUploadId(new Long(eoi_upload_id));
	    				EOIUploadBean eoiUploadBeans  = tenderBO.getEOIUploaded(eoiUploadBean).get(0);
	            		imgBytes = eoiUploadBeans.getEoiFile1();
	            		//System.out.println(imgBytes.length);
	            		//System.out.println(imgBytes.toString());
	            	}else if(type!= null && type.equalsIgnoreCase("eoiFile2")){ 
	            		EOIUploadBean eoiUploadBean = new EOIUploadBean();
	    	            eoiUploadBean.setEoiUploadId(new Long(eoi_upload_id));
	    				EOIUploadBean eoiUploadBeans  = tenderBO.getEOIUploaded(eoiUploadBean).get(0);
	            		imgBytes = eoiUploadBeans.getEoiFile2();
	            	}else if(type!= null && type.equalsIgnoreCase("eoiFile3")){ 
	            		EOIUploadBean eoiUploadBean = new EOIUploadBean();
	    	            eoiUploadBean.setEoiUploadId(new Long(eoi_upload_id));
	    				EOIUploadBean eoiUploadBeans  = tenderBO.getEOIUploaded(eoiUploadBean).get(0);
	            		imgBytes = eoiUploadBeans.getEoiFile3();
	            	}else if(type!= null && type.equalsIgnoreCase("eoiCorrigendum")){ 
	            		CorrigendumUploadBean corrigendumUploadBean=new CorrigendumUploadBean();
	            		corrigendumUploadBean.setCorrigendumUploadId(new Long(corrigendumUploadId));
	            		CorrigendumUploadBean corrigendumUploadBeans  = tenderBO.getCorrigendumUploaded(corrigendumUploadBean).get(0);
	            		imgBytes = corrigendumUploadBeans.getCorrigendumFile();
	            	}
	            	int len = imgBytes.length;
	            	response.setContentLength(len);
	            	servletOutputStream.write(imgBytes);
	            	servletOutputStream.flush();
	            	servletOutputStream.close();
			}            
            connection.close();        	
		}catch (Exception e) { 
			e.printStackTrace();			
		}	
		finally{
			try{
				if(connection !=  null){
					connection.close();
				}
			}catch(SQLException sq){
				sq.printStackTrace();
			}
		}
	}
	
	protected void getQuoatation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("Quoatation Start");
		response.setContentType("text/html");
		Connection connection = null;
		try { 
			WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
			this.misJdcDaoImpl=(MISJdcDaoImpl)webApplicationContext.getBean("misJdcDaoImpl");
			this.tenderBO = (TenderBO)webApplicationContext.getBean("tenderBO");
			//System.out.println("misJdcDaoImpl: "+misJdcDaoImpl);
			connection = misJdcDaoImpl.getMISDataSource().getConnection();
			//System.out.println("connection: "+connection);
			Statement stmt = connection.createStatement();
			String op=request.getParameter("op");
			String buffer="";
			if(op!=null && op.equalsIgnoreCase("display")){
				String district_id=request.getParameter("district_id");
				if(district_id=="null"){
					district_id = null;
				//	System.out.println("HDF");
				}
				String where="";
				if (district_id.equals("SPMC"))	
					where=district_id!=null?" where a.location_id='"+district_id+"' and a.status<>'D'":"";
				else if (district_id.equals("All"))	
					where=district_id!=null?" where  a.district_id in ('D02','D19','D16','D15','D10','D13','D01','D06','D04','D05','D11','D17','D12','D14','D07','D20','D21','D22','D08','D18','D09','D03') and a.status<>'D'":"";
				else
					where=district_id!=null?" where a.district_id='"+district_id+"' and a.status<>'D'":"";
	            ResultSet rs = stmt.executeQuery("select * from prwss_main.quoatation_upload a " +
	            		"left outer join (select * from prwss_main.corrigendum_upload where status<>'D' and corrigendum_for='QUOTATION') b on cast(a.quoatation_upload_id as character varying)=b.doc_id "+where+
	            " order by quoatation_upload_id, b.corrigendum_upload_id "		
	            );
	            buffer="<table border='0' class='table-content'>" +
	            		"<tr>" +
	            		"<th width='10%'>S.NO.</th>" +
	            		"<th >Quotation</th>" +
	            		"<th width='20'>Corrigendum</th>" +
	            		"</tr>";
	            int i=0;
	            int j=0;
	            String tenderUploadId="";
	            String tempTenderUploadId="";
	            String tenderCorringdum="";

	            while(rs.next()){
	            	Date exDate = rs.getDate("expiry_date");
	            	if(MisUtility.ifEmpty(exDate)){
	            	Date expiryDate = new Date(exDate.getTime()+(1000*60*60*24));
	            //	System.out.println("Expiry Date: "+expiryDate);
	            	if(MisUtility.ifEmpty(expiryDate) && expiryDate.after(MisUtility.convertStringToDate(MisUtility.now("dd-MM-yyyy")))){
	            		tenderUploadId=rs.getString("quoatation_upload_id");
	            		if(!tempTenderUploadId.equals(tenderUploadId)){
	            			if(!tempTenderUploadId.equals(""))
	            				buffer+="<td>" +tenderCorringdum+"</td>" +"</tr>";

	            		buffer+="<tr><td>"+ ++i +"</td>"+
	            		"<td>" +
	            			"<a href='/PRWSS_MIS/TenderUploadServlet.to?op=download&type=quoatationFile&docType=quoatation&quoatation_upload_id="+
	            				rs.getString("quoatation_upload_id") +"' target='_blank'>"+rs.getString("quoatation_discription") +
	            			"</a>" +
	            		"</td>";	
	            	}		
	            	if(!tempTenderUploadId.equals(tenderUploadId)){
            			tenderCorringdum="";
            			j=0;
            		}
            		if(MisUtility.ifEmpty(rs.getString("corrigendum_upload_id")) && MisUtility.ifEmpty(tenderUploadId)){            			
            			tenderCorringdum+=	++j+". <a href='/PRWSS_MIS/TenderUploadServlet.to?op=download&type=quoatationCorrigendum&docType=quoatation&corrigendum_upload_id="+
            						rs.getString("corrigendum_upload_id") +"' target='_blank'>Corrigendum" +
            					"</a><br>" ;
            		}
            	}
	            	}
            	tempTenderUploadId=tenderUploadId;
            }
            buffer+="<td>" +tenderCorringdum+"</td>" +"</tr>";
            buffer+="</table>";

	            //System.out.println("Buffer: "+buffer);
	            PrintWriter out = response.getWriter();
				if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
					out.print(buffer.toString());
				}
			}
			if(op!=null && op.equalsIgnoreCase("download")){
				String quoatation_upload_id=request.getParameter("quoatation_upload_id");
				String corrigendumUploadId=request.getParameter("corrigendum_upload_id");
				String where=quoatation_upload_id!=null?" where quoatation_upload_id='"+quoatation_upload_id+"'":"";
				
	            ServletOutputStream servletOutputStream=response.getOutputStream();
				response.setContentType("application/pdf"); 
				response.addHeader("Content-Disposition","attachment; filename=quoatationFile.pdf");
	            	String type=request.getParameter("type");
	            	//System.out.println("Type"+type);
	            	byte[] imgBytes=null;
	            	
	        		//System.out.println("Inside");
	            	if(type!= null && type.equalsIgnoreCase("quoatationFile")){
	            		QuoatationUploadBean quoatationUploadBean = new QuoatationUploadBean();
	    				quoatationUploadBean.setQuoatationUploadId(new Long(quoatation_upload_id));
	    				QuoatationUploadBean quoatationUploadBeans  = tenderBO.getQuoatationUploaded(quoatationUploadBean).get(0);
	            		imgBytes = quoatationUploadBeans.getQuoatationFile();
	            	}else if(type!= null && type.equalsIgnoreCase("quoatationCorrigendum")){ 
	            		CorrigendumUploadBean corrigendumUploadBean=new CorrigendumUploadBean();
	            		corrigendumUploadBean.setCorrigendumUploadId(new Long(corrigendumUploadId));
	            		CorrigendumUploadBean corrigendumUploadBeans  = tenderBO.getCorrigendumUploaded(corrigendumUploadBean).get(0);
	            		imgBytes = corrigendumUploadBeans.getCorrigendumFile();
	            	}
	            	int len = imgBytes.length;
	            	response.setContentLength(len);
	            	servletOutputStream.write(imgBytes);
	            	servletOutputStream.flush();
	            	servletOutputStream.close();
			}            
            connection.close();        	
		}catch (Exception e) { 
			e.printStackTrace();			
		}	
		finally{
			try{
				if(connection !=  null){
					connection.close();
				}
			}catch(SQLException sq){
				sq.printStackTrace();
			}
		}
	}
	
	protected void getAdv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("Adv Start");
		response.setContentType("text/html");
		Connection connection = null;
		try { 
			WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
			this.misJdcDaoImpl=(MISJdcDaoImpl)webApplicationContext.getBean("misJdcDaoImpl");
			this.tenderBO = (TenderBO)webApplicationContext.getBean("tenderBO");
			//System.out.println("misJdcDaoImpl: "+misJdcDaoImpl);
			connection = misJdcDaoImpl.getMISDataSource().getConnection();
			//System.out.println("connection: "+connection);
			Statement stmt = connection.createStatement();
			String op=request.getParameter("op");
			String buffer="";
			if(op!=null && op.equalsIgnoreCase("display")){
//				String district_id=request.getParameter("district_id");
//				if(district_id=="null"){
//					district_id = null;
//				//	System.out.println("HDF");
//				}
//				String where="";
//				if (district_id.equals("SPMC"))	
//					where=district_id!=null?" where location_id='"+district_id+"' and status<>'D'":"";
//				else
//					where=district_id!=null?" where district_id='"+district_id+"' and status<>'D'":"";
//				System.out.println("where"+where);
	          //  ResultSet rs = stmt.executeQuery("select * from prwss_main.adv_upload"+where);
	            ResultSet rs = stmt.executeQuery("select * from prwss_main.adv_upload where status<>'D'");
	            buffer="<table border='0' class='table-content'><tr><th>S.NO.</th><th>Post Name</th><th>Description</th><th>Last Date</th><th>Download</th></tr>";
	            int i=0;
	            while(rs.next()){
	            	Date exDate = rs.getDate("expiry_date");
	            	System.out.println(rs.getString("post_name"));
	            	if(MisUtility.ifEmpty(exDate)){
	            	Date expiryDate = new Date(exDate.getTime()+(1000*60*60*24));
	            	System.out.println("Expiry Date: "+expiryDate);
	            	if(MisUtility.ifEmpty(expiryDate) && expiryDate.after(MisUtility.convertStringToDate(MisUtility.now("dd-MM-yyyy")))){
	            		System.out.println("inside");
	            		buffer+="<tr><td>"+ ++i +"</td>"+
	            		"<td>"+rs.getString("post_name")+"</td>" +
	            		"<td>"+rs.getString("discription")+"</td>" +
	            		"<td>"+MisUtility.convertDateToString(rs.getDate("last_date"))+"</td>"+
	            		"<td>" +
	            			"<a href='/PRWSS_MIS/TenderUploadServlet.to?op=download&type=advFile&docType=adv&adv_upload_id="+
	            				rs.getString("adv_upload_id") +"' target='_blank'>Download PDF" +
	            			"</a>" +
	            		"</td>" +	            		
		            "</tr>";	
	            	}
	            	}
	            }
	            buffer+="</table>";
	           System.out.println("Buffer: "+buffer);
	            PrintWriter out = response.getWriter();
				if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
					out.print(buffer.toString());
				}
			}
			if(op!=null && op.equalsIgnoreCase("download")){
				String adv_upload_id=request.getParameter("adv_upload_id");
				//System.out.println("adv_upload_id"+adv_upload_id);
				AdvUploadBean advUploadBean = new AdvUploadBean();
				advUploadBean.setAdvUploadId(new Long(adv_upload_id));
				AdvUploadBean advUploadBeans  = tenderBO.getAdvUploaded(advUploadBean).get(0);
	            ServletOutputStream servletOutputStream=response.getOutputStream();
				response.setContentType("application/pdf"); 
				response.addHeader("Content-Disposition","attachment; filename=advFile.pdf");
	            	String type=request.getParameter("type");
	            	//System.out.println("Type"+type);
	            	byte[] imgBytes=null;
	            	
	        		//System.out.println("Inside");
	            	if(type!= null && type.equalsIgnoreCase("advFile")){
	            		imgBytes = advUploadBeans.getAdvFile();
	            		//System.out.println(imgBytes.length);
	            		//System.out.println(imgBytes.toString());
	            	}
	            	int len = imgBytes.length;
	            	response.setContentLength(len);
	            	servletOutputStream.write(imgBytes);
	            	servletOutputStream.flush();
	            	servletOutputStream.close();
			}            
            connection.close();        	
		}catch (Exception e) { 
			e.printStackTrace();			
		}	
		finally{
			try{
				if(connection !=  null){
					connection.close();
				}
			}catch(SQLException sq){
				sq.printStackTrace();
			}
		}
	}

	protected void getContractAward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("Start");
		response.setContentType("text/html");
		Connection connection = null;
		try { 
			WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
			this.misJdcDaoImpl=(MISJdcDaoImpl)webApplicationContext.getBean("misJdcDaoImpl");
			this.tenderBO = (TenderBO)webApplicationContext.getBean("tenderBO");
		//	System.out.println("misJdcDaoImpl: "+misJdcDaoImpl);
			connection = misJdcDaoImpl.getMISDataSource().getConnection();
			System.out.println("connection: "+connection);
			Statement stmt = connection.createStatement();
			String op=request.getParameter("op");
			String package_type = request.getParameter("pac_type");
			String buffer="";
			if(op!=null && op.equalsIgnoreCase("display")){
				String district_id=request.getParameter("district_id");
				if(district_id=="null"){
					district_id = null;
				//	System.out.println("HDF");
				}
				String where="";
				String cond = "";
				if (district_id.equals("SPMC"))	{
					where=district_id!=null?" where location_id='"+district_id+"' and status<>'D'":"";
					cond = package_type!=null?" and package_type='"+package_type+"'":"";
				}
				else{
					where=district_id!=null?" where district_id='"+district_id+"' and status<>'D'":"";
					cond = package_type!=null?"and package_type='"+package_type+"'":"";
				}
			    System.out.println("select * from prwss_main.vw_web_contract_award"+where+" "+cond);
	            ResultSet rs = stmt.executeQuery("select * from prwss_main.vw_web_contract_award"+where+" "+cond);
	            buffer="<table border='0' class='table-content'><tr><th>S.NO.</th>" +
	            		"<th>Package No.</th>" +
	            		"<th>Work Description</th>" +
	            		"<th>Method of Procurement</th>" +
	            		"<th>Contract Awarded To</th>" +
	            		"<th>Price Offered</th>" +
	            		"<th>Duration</th>" +
	            		
	            		"</tr>";
	            int i=0;
	          while(rs.next()){
	            		buffer+="<tr><td>"+ ++i +"</td>" +
	            		"<td><a href=\"#\" class=\"topopup\" onclick=\"ajaxFunction('/PRWSS_MIS/TenderUploadServlet.to?op=popup_content&docType=conAwardPopup&package_id="+rs.getString("package_id")+"','popup_content');openpopup('popup_content')\">"+rs.getString("package_id")+"</a></td>" +		
	            		"<td>"+rs.getString("package_description")+"</td>" +
	            		"<td>"+rs.getString("procurement_type")+"</td>" +
	            		"<td>"+rs.getString("vendor_name")+"</td>" +
	            		"<td>"+rs.getString("price_offer")+"</td>" +
	            		"<td>"+(rs.getString("duration")==null?0:rs.getString("duration"))+" month</td>" +
	            		
	            				"</tr>";	
	            }
	           /* while(rs.next()){
            		buffer+="<tr><td>"+ ++i +"</td>" +
            		"<td>"+rs.getString("package_id")+"</td>" +		
            		"<td>"+rs.getString("package_description")+"</td>" +
            		"<td>"+rs.getString("procurement_type")+"</td>" +
            		"<td>"+rs.getString("vendor_name")+"</td>" +
            		"<td>"+rs.getString("price_offer")+"</td>" +
            		"<td>"+(rs.getString("duration")==null?0:rs.getString("duration"))+" month</td>" +
            		
            				"</tr>";
	            }*/
	            buffer+="</table>";
	            PrintWriter out = response.getWriter();
				if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
					out.print(buffer.toString());
				}
			}            
            connection.close();        	
		}catch (Exception e) { 
			e.printStackTrace();			
		}	
		finally{
			try{
				if(connection !=  null){
					connection.close();
				}
			}catch(SQLException sq){
				sq.printStackTrace();
			}
		}
	}
	
	protected void getOldTender(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("Start");
		response.setContentType("text/html");
		Connection connection = null;
		try { 
			WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
			this.misJdcDaoImpl=(MISJdcDaoImpl)webApplicationContext.getBean("misJdcDaoImpl");
			this.tenderBO = (TenderBO)webApplicationContext.getBean("tenderBO");
			connection = misJdcDaoImpl.getMISDataSource().getConnection();
			Statement stmt = connection.createStatement();
			String op=request.getParameter("op");
			String buffer="";
			if(op!=null && op.equalsIgnoreCase("display")){
				String district_id=request.getParameter("district_id");
				if(district_id=="null"){
					district_id = null;
				}				
				String where="";
				if (district_id.equals("SPMC"))	
					where=district_id!=null?" where a.location_id='"+district_id+"' and a.status<>'D'":"";
				else if (district_id.equals("All"))	
					where=district_id!=null?" where  a.district_id in ('D02','D19','D16','D15','D10','D13','D01','D06','D04','D05','D11','D17','D12','D14','D07','D20','D21','D22','D08','D18','D09','D03') and a.status<>'D'":"";
				else
					where=district_id!=null?" where a.district_id='"+district_id+"' and a.status<>'D'":"";
				System.out.println("where"+where);
	            ResultSet rs = stmt.executeQuery("select * from prwss_main.tender_upload a " +
	            		"left outer join (select * from prwss_main.corrigendum_upload where status<>'D') b on a.tender_id=b.doc_id "+where+
	            " order by a.tender_upload_id, b.corrigendum_upload_id "		
	            );
	            
	            buffer="<table border='0' class='table-content'>" +
	            		"<tr>" +
	            		"<th width='10%'>S.NO.</th>" +
	            		"<th >Description of Work</th>" +
	            		"<th width='20'>Tender Notice/IFB</th>" +
	            		"<th width='20'>Bid Document/DNIT</th>" +
	            		"<th width='20'>Bids Opening date (DD/MM/YYYY)</th>" +
	            		"<th width='20'>Last date for Receipt of Bids (DD/MM/YYYY)</th>" +
	            		"<th width='20'>Corrigendum</th>" +
	            		"</tr>";
	            int i=0;
	            int j=0;
	            String tenderUploadId="";
	            String tempTenderUploadId="";
	            String tenderCorringdum="";
	            while(rs.next()){
	            	Date expiryDate = rs.getDate("expiry_date");
	            	if(MisUtility.ifEmpty(expiryDate)){
	            //	Date expiryDate = new Date(exDate.getTime()+(1000*60*60*24));
	            	//System.out.println("Expiry"+expiryDate);
	            	
	            	if(MisUtility.ifEmpty(expiryDate) && expiryDate.before(MisUtility.convertStringToDate(MisUtility.now("dd-MM-yyyy")))){	            		
	            		tenderUploadId=rs.getString("tender_upload_id");
	            		//System.out.println(i+":tempTenderUploadId:"+tempTenderUploadId+":tenderUploadId:"+tenderUploadId);	            		
	            		if(!tempTenderUploadId.equals(tenderUploadId)){
	            			if(!tempTenderUploadId.equals(""))
	            				buffer+="<td>" +tenderCorringdum+"</td>" +"</tr>";		            		
	            			buffer+="<tr>" +
			            				"<td>"+ ++i +"</td>" +
			            				"<td>"+rs.getString("work_description")+"</td>" +
			            				"<td>" +
			            					"<a href='/PRWSS_MIS/TenderUploadServlet.to?op=download&type=tender&docType=tender&tender_upload_id="+
			            						tenderUploadId +"' target='_blank'>Tender Notice" +
			            					"</a>" +
			            				"</td>" +
			            				"<td>" +
			            					"<a href='/PRWSS_MIS/TenderUploadServlet.to?op=download&type=bid&docType=tender&tender_upload_id="+
			            						rs.getString("tender_upload_id") +"' target='_blank'>" +
			            						"Bid Document" +
			            					"</a>" +
			            				"</td>"
			            				+"</td>" +
			            				"<td>"+MisUtility.convertDateToString(rs.getDate("bid_opening_date"))+"</td>" +
			            				"<td>"+MisUtility.convertDateToString(rs.getDate("last_date"))+"</td>" ;	
		            	}
	            		/*if(tempTenderUploadId.equals("") && tenderUploadId.equals("")){
	            			tenderCorringdum="";
	            			j=0;
	            		}*/
	            		if(!tempTenderUploadId.equals(tenderUploadId)){
	            			tenderCorringdum="";
	            			j=0;
	            		}
	            		if(MisUtility.ifEmpty(rs.getString("corrigendum_upload_id")) && MisUtility.ifEmpty(tenderUploadId)){            			
	            			tenderCorringdum+=	++j+". <a href='/PRWSS_MIS/TenderUploadServlet.to?op=download&type=tenderCorrigendum&docType=tender&corrigendum_upload_id="+
	            						rs.getString("corrigendum_upload_id") +"' target='_blank'>Corrigendum" +
	            					"</a><br>" ;
	            		}
	            	}
	            	}
	            	tempTenderUploadId=tenderUploadId=rs.getString("tender_upload_id");
	            }
	            buffer+="<td>" +tenderCorringdum+"</td>" +"</tr>";
	            buffer+="</table>";
	            System.out.println("Buffer: "+buffer);
	            PrintWriter out = response.getWriter();
				if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
					out.print(buffer.toString());
				}
			}
			if(op!=null && op.equalsIgnoreCase("download")){
				String tender_upload_id=request.getParameter("tender_upload_id");
				String corrigendumUploadId=request.getParameter("corrigendum_upload_id");
				//System.out.println("tender_upload_id"+tender_upload_id);
				String where=tender_upload_id!=null?" where tender_upload_id='"+tender_upload_id+"'":"";	            
	            ServletOutputStream servletOutputStream=response.getOutputStream();
				response.setContentType("application/pdf"); 
				response.addHeader("Content-Disposition","attachment; filename=Tender.pdf");
	            	String type=request.getParameter("type");
	            	//System.out.println("Type"+type);
	            	byte[] imgBytes=null;
	            	
	        		//System.out.println("Inside");
	            	if(type!= null && type.equalsIgnoreCase("tender")){
	            		TenderUploadBean tenderUploadBean = new TenderUploadBean();
	    	            tenderUploadBean.setTenderUploadId(new Long(tender_upload_id));
	    				TenderUploadBean tenderUploadBeans  = tenderBO.getTenderUploaded(tenderUploadBean).get(0);
	            		imgBytes = tenderUploadBeans.getTenderNoticeFile();
	            		//System.out.println(imgBytes.length);
	            		//System.out.println(imgBytes.toString());
	            	}else if(type!= null && type.equalsIgnoreCase("bid")){ 
	            		TenderUploadBean tenderUploadBean = new TenderUploadBean();
	    	            tenderUploadBean.setTenderUploadId(new Long(tender_upload_id));
	    				TenderUploadBean tenderUploadBeans  = tenderBO.getTenderUploaded(tenderUploadBean).get(0);
	            		imgBytes = tenderUploadBeans.getBidDocFile();
	            	}else if(type!= null && type.equalsIgnoreCase("tenderCorrigendum")){ 
	            		CorrigendumUploadBean corrigendumUploadBean=new CorrigendumUploadBean();
	            		corrigendumUploadBean.setCorrigendumUploadId(new Long(corrigendumUploadId));
	            		CorrigendumUploadBean corrigendumUploadBeans  = tenderBO.getCorrigendumUploaded(corrigendumUploadBean).get(0);
	            		imgBytes = corrigendumUploadBeans.getCorrigendumFile();
	            	}
	            	int len = imgBytes.length;
	            	response.setContentLength(len);
	            	servletOutputStream.write(imgBytes);
	            	servletOutputStream.flush();
	            	servletOutputStream.close();
			}            
            connection.close();        	
		}catch (Exception e) { 
			e.printStackTrace();			
		}	
		finally{
			try{
				if(connection !=  null){
					connection.close();
				}
			}catch(SQLException sq){
				sq.printStackTrace();
			}
		}
	}
	
	protected void getOldQuoatation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("Quoatation Start");
		response.setContentType("text/html");
		Connection connection = null;
		try { 
			WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
			this.misJdcDaoImpl=(MISJdcDaoImpl)webApplicationContext.getBean("misJdcDaoImpl");
			this.tenderBO = (TenderBO)webApplicationContext.getBean("tenderBO");
			//System.out.println("misJdcDaoImpl: "+misJdcDaoImpl);
			connection = misJdcDaoImpl.getMISDataSource().getConnection();
			//System.out.println("connection: "+connection);
			Statement stmt = connection.createStatement();
			String op=request.getParameter("op");
			String buffer="";
			if(op!=null && op.equalsIgnoreCase("display")){
				String district_id=request.getParameter("district_id");
				if(district_id=="null"){
					district_id = null;
				//	System.out.println("HDF");
				}
				String where="";
				if (district_id.equals("SPMC"))	
					where=district_id!=null?" where a.location_id='"+district_id+"' and a.status<>'D'":"";
				else if (district_id.equals("All"))	
					where=district_id!=null?" where  a.district_id in ('D02','D19','D16','D15','D10','D13','D01','D06','D04','D05','D11','D17','D12','D14','D07','D20','D21','D22','D08','D18','D09','D03') and a.status<>'D'":"";
				else
					where=district_id!=null?" where a.district_id='"+district_id+"' and a.status<>'D'":"";
	            ResultSet rs = stmt.executeQuery("select * from prwss_main.quoatation_upload a " +
	            		"left outer join (select * from prwss_main.corrigendum_upload where status<>'D') b on cast(a.quoatation_upload_id as character varying)=b.doc_id "+where+
	            " order by quoatation_upload_id, b.corrigendum_upload_id "		
	            );
	            buffer="<table border='0' class='table-content'>" +
	            		"<tr>" +
	            		"<th width='10%'>S.NO.</th>" +
	            		"<th >Quotation</th>" +
	            		"<th width='20'>Corrigendum</th>" +
	            		"</tr>";
	            int i=0;
	            int j=0;
	            String tenderUploadId="";
	            String tempTenderUploadId="";
	            String tenderCorringdum="";

	            while(rs.next()){
	            	Date expiryDate = rs.getDate("expiry_date");
	            	if(MisUtility.ifEmpty(expiryDate)){
	            //	Date expiryDate = new Date(exDate.getTime()+(1000*60*60*24));
	            //	System.out.println("Expiry Date: "+expiryDate);
	            	if(MisUtility.ifEmpty(expiryDate) && expiryDate.before(MisUtility.convertStringToDate(MisUtility.now("dd-MM-yyyy")))){
	            		tenderUploadId=rs.getString("quoatation_upload_id");
	            		if(!tempTenderUploadId.equals(tenderUploadId)){
	            			if(!tempTenderUploadId.equals(""))
	            				buffer+="<td>" +tenderCorringdum+"</td>" +"</tr>";

	            		buffer+="<tr><td>"+ ++i +"</td>"+
	            		"<td>" +
	            			"<a href='/PRWSS_MIS/TenderUploadServlet.to?op=download&type=quoatationFile&docType=quoatation&quoatation_upload_id="+
	            				rs.getString("quoatation_upload_id") +"' target='_blank'>"+rs.getString("quoatation_discription") +
	            			"</a>" +
	            		"</td>";	
	            	}		
	            	if(!tempTenderUploadId.equals(tenderUploadId)){
            			tenderCorringdum="";
            			j=0;
            		}
            		if(MisUtility.ifEmpty(rs.getString("corrigendum_upload_id")) && MisUtility.ifEmpty(tenderUploadId)){            			
            			tenderCorringdum+=	++j+". <a href='/PRWSS_MIS/TenderUploadServlet.to?op=download&type=quoatationCorrigendum&docType=quoatation&corrigendum_upload_id="+
            						rs.getString("corrigendum_upload_id") +"' target='_blank'>Corrigendum" +
            					"</a><br>" ;
            		}
            	}
	            	}
            	tempTenderUploadId=tenderUploadId;
            }
            buffer+="<td>" +tenderCorringdum+"</td>" +"</tr>";
            buffer+="</table>";

	            //System.out.println("Buffer: "+buffer);
	            PrintWriter out = response.getWriter();
				if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
					out.print(buffer.toString());
				}
			}
			if(op!=null && op.equalsIgnoreCase("download")){
				String quoatation_upload_id=request.getParameter("quoatation_upload_id");
				String corrigendumUploadId=request.getParameter("corrigendum_upload_id");
				String where=quoatation_upload_id!=null?" where quoatation_upload_id='"+quoatation_upload_id+"'":"";
				
	            ServletOutputStream servletOutputStream=response.getOutputStream();
				response.setContentType("application/pdf"); 
				response.addHeader("Content-Disposition","attachment; filename=quoatationFile.pdf");
	            	String type=request.getParameter("type");
	            	//System.out.println("Type"+type);
	            	byte[] imgBytes=null;
	            	
	        		//System.out.println("Inside");
	            	if(type!= null && type.equalsIgnoreCase("quoatationFile")){
	            		QuoatationUploadBean quoatationUploadBean = new QuoatationUploadBean();
	    				quoatationUploadBean.setQuoatationUploadId(new Long(quoatation_upload_id));
	    				QuoatationUploadBean quoatationUploadBeans  = tenderBO.getQuoatationUploaded(quoatationUploadBean).get(0);
	            		imgBytes = quoatationUploadBeans.getQuoatationFile();
	            	}else if(type!= null && type.equalsIgnoreCase("quoatationCorrigendum")){ 
	            		CorrigendumUploadBean corrigendumUploadBean=new CorrigendumUploadBean();
	            		corrigendumUploadBean.setCorrigendumUploadId(new Long(corrigendumUploadId));
	            		CorrigendumUploadBean corrigendumUploadBeans  = tenderBO.getCorrigendumUploaded(corrigendumUploadBean).get(0);
	            		imgBytes = corrigendumUploadBeans.getCorrigendumFile();
	            	}
	            	int len = imgBytes.length;
	            	response.setContentLength(len);
	            	servletOutputStream.write(imgBytes);
	            	servletOutputStream.flush();
	            	servletOutputStream.close();
			}            
            connection.close();        	
		}catch (Exception e) { 
			e.printStackTrace();			
		}	
		finally{
			try{
				if(connection !=  null){
					connection.close();
				}
			}catch(SQLException sq){
				sq.printStackTrace();
			}
		}
	}
	
	protected void getOldEOI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("EOI Start");
		response.setContentType("text/html");
		Connection connection = null;
		try { 
			WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
			this.misJdcDaoImpl=(MISJdcDaoImpl)webApplicationContext.getBean("misJdcDaoImpl");
			this.tenderBO = (TenderBO)webApplicationContext.getBean("tenderBO");
			connection = misJdcDaoImpl.getMISDataSource().getConnection();
			Statement stmt = connection.createStatement();
			String op=request.getParameter("op");
			String buffer="";
			if(op!=null && op.equalsIgnoreCase("display")){
				String district_id=request.getParameter("district_id");
				if(district_id=="null"){
					district_id = null;
				}
				String where="";
				if (district_id.equals("SPMC"))	
					where=district_id!=null?" where a.location_id='"+district_id+"' and a.status<>'D'":"";
				else if (district_id.equals("All"))	
					where=district_id!=null?" where a.location_id='SPMC' and a.district_id in ('D02','D19','D16','D15','D10','D13','D01','D06','D04','D05','D11','D17','D12','D14','D07','D20','D21','D22','D08','D18','D09','D03') and a.status<>'D'":"";
				else
					where=district_id!=null?" where a.district_id='"+district_id+"' and a.status<>'D'":"";

				//System.out.println("where"+where);
	            ResultSet rs = stmt.executeQuery("select * from prwss_main.eoi_upload a " +
	            		"left outer join (select * from prwss_main.corrigendum_upload where status<>'D') b on a.eoi_referance_no=b.doc_id "+where+
	            " order by a.eoi_upload_id, b.corrigendum_upload_id "		
	            );

	            
	            buffer="<table border='0' class='table-content'>" +
	            		"<tr>" +
	            		"<th width='10%'>S.NO.</th>" +
	            		"<th >EOI reference No.</th>" +
	            		"<th >EOI Title</th>" +
	            		"<th width='20'>EOI File 1</th>" +
	            		"<th width='20'>EOI File 2</th>" +
	            		"<th width='20'>EOI File 3</th>" +
	            		"<th width='20'>Corrigendum</th>" +
	            		"</tr>";
	            int i=0;
	            int j=0;
	            String tenderUploadId="";
	            String tempTenderUploadId="";
	            String tenderCorringdum="";

	            while(rs.next()){
	            	Date expiryDate = rs.getDate("expiry_date");
	            	if(MisUtility.ifEmpty(expiryDate)){
	            //	Date expiryDate = new Date(exDate.getTime()+(1000*60*60*24));
	            	//System.out.println("Expiry"+expiryDate);
	            	if(MisUtility.ifEmpty(expiryDate) && expiryDate.before(MisUtility.convertStringToDate(MisUtility.now("dd-MM-yyyy")))){
	            		tenderUploadId=rs.getString("eoi_upload_id");
	            		if(!tempTenderUploadId.equals(tenderUploadId)){
	            			if(!tempTenderUploadId.equals(""))
	            				buffer+="<td>" +tenderCorringdum+"</td>" +"</tr>";

		            		buffer+="<tr><td>"+ ++i +"</td>"+
		            		"<td>"+rs.getString("eoi_referance_no")+"</td>" +
		            		"<td>"+rs.getString("eoi_title")+"</td>" +
		            		"<td>" +
		            			"<a href='/PRWSS_MIS/TenderUploadServlet.to?op=download&type=eoiFile1&docType=eoi&eoi_upload_id="+
		            				rs.getString("eoi_upload_id") +"' target='_blank'>EOI File 1" +
		            			"</a>" +
		            		"</td>" +
		            		"<td>" +
		            			"<a href='/PRWSS_MIS/TenderUploadServlet.to?op=download&type=eoiFile2&docType=eoi&eoi_upload_id="+
		            				rs.getString("eoi_upload_id") +"' target='_blank'>EOI File 2" +
		            			"</a>" +
		            		"</td>" +
		            		"<td>" +
	            				"<a href='/PRWSS_MIS/TenderUploadServlet.to?op=download&type=eoiFile3&docType=eoi&eoi_upload_id="+
	            				rs.getString("eoi_upload_id") +"' target='_blank'>EOI File 3" +
	            				"</a>" +
	            			"</td>";
	            		}
	            		if(!tempTenderUploadId.equals(tenderUploadId)){
	            			tenderCorringdum="";
	            			j=0;
	            		}
	            		if(MisUtility.ifEmpty(rs.getString("corrigendum_upload_id")) && MisUtility.ifEmpty(tenderUploadId)){            			
	            			tenderCorringdum+=	++j+". <a href='/PRWSS_MIS/TenderUploadServlet.to?op=download&type=eoiCorrigendum&docType=eoi&corrigendum_upload_id="+
	            						rs.getString("corrigendum_upload_id") +"' target='_blank'>Corrigendum" +
	            					"</a><br>" ;
	            		}
	            	}
	            	}
	            	tempTenderUploadId=tenderUploadId;
	            }
	            buffer+="<td>" +tenderCorringdum+"</td>" +"</tr>";
	            buffer+="</table>";	            	
	           // System.out.println("Buffer: "+buffer);
	            PrintWriter out = response.getWriter();
				if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
					out.print(buffer.toString());
				}
			}
			if(op!=null && op.equalsIgnoreCase("download")){
				String eoi_upload_id=request.getParameter("eoi_upload_id");
				String corrigendumUploadId=request.getParameter("corrigendum_upload_id");
				//System.out.println("eoi_upload_id"+eoi_upload_id);
				String where=eoi_upload_id!=null?" where eoi_upload_id='"+eoi_upload_id+"'":"";	            
	            ServletOutputStream servletOutputStream=response.getOutputStream();
				response.setContentType("application/pdf"); 
				response.addHeader("Content-Disposition","attachment; filename=Tender.pdf");
	            	String type=request.getParameter("type");
	            	//System.out.println("Type"+type);
	            	byte[] imgBytes=null;
	            	
	        		//System.out.println("Inside");
	            	if(type!= null && type.equalsIgnoreCase("eoiFile1")){
	            		EOIUploadBean eoiUploadBean = new EOIUploadBean();
	    	            eoiUploadBean.setEoiUploadId(new Long(eoi_upload_id));
	    				EOIUploadBean eoiUploadBeans  = tenderBO.getEOIUploaded(eoiUploadBean).get(0);
	            		imgBytes = eoiUploadBeans.getEoiFile1();
	            		//System.out.println(imgBytes.length);
	            		//System.out.println(imgBytes.toString());
	            	}else if(type!= null && type.equalsIgnoreCase("eoiFile2")){ 
	            		EOIUploadBean eoiUploadBean = new EOIUploadBean();
	    	            eoiUploadBean.setEoiUploadId(new Long(eoi_upload_id));
	    				EOIUploadBean eoiUploadBeans  = tenderBO.getEOIUploaded(eoiUploadBean).get(0);
	            		imgBytes = eoiUploadBeans.getEoiFile2();
	            	}else if(type!= null && type.equalsIgnoreCase("eoiFile3")){ 
	            		EOIUploadBean eoiUploadBean = new EOIUploadBean();
	    	            eoiUploadBean.setEoiUploadId(new Long(eoi_upload_id));
	    				EOIUploadBean eoiUploadBeans  = tenderBO.getEOIUploaded(eoiUploadBean).get(0);
	            		imgBytes = eoiUploadBeans.getEoiFile3();
	            	}else if(type!= null && type.equalsIgnoreCase("eoiCorrigendum")){ 
	            		CorrigendumUploadBean corrigendumUploadBean=new CorrigendumUploadBean();
	            		corrigendumUploadBean.setCorrigendumUploadId(new Long(corrigendumUploadId));
	            		CorrigendumUploadBean corrigendumUploadBeans  = tenderBO.getCorrigendumUploaded(corrigendumUploadBean).get(0);
	            		imgBytes = corrigendumUploadBeans.getCorrigendumFile();
	            	}
	            	int len = imgBytes.length;
	            	response.setContentLength(len);
	            	servletOutputStream.write(imgBytes);
	            	servletOutputStream.flush();
	            	servletOutputStream.close();
			}            
            connection.close();        	
		}catch (Exception e) { 
			e.printStackTrace();			
		}	
		finally{
			try{
				if(connection !=  null){
					connection.close();
				}
			}catch(SQLException sq){
				sq.printStackTrace();
			}
		}
	}
	
	protected void getOldAdv(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("Adv Start");
		response.setContentType("text/html");
		Connection connection = null;
		try { 
			WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
			this.misJdcDaoImpl=(MISJdcDaoImpl)webApplicationContext.getBean("misJdcDaoImpl");
			this.tenderBO = (TenderBO)webApplicationContext.getBean("tenderBO");
			//System.out.println("misJdcDaoImpl: "+misJdcDaoImpl);
			connection = misJdcDaoImpl.getMISDataSource().getConnection();
			//System.out.println("connection: "+connection);
			Statement stmt = connection.createStatement();
			String op=request.getParameter("op");
			String buffer="";
			if(op!=null && op.equalsIgnoreCase("display")){
//				String district_id=request.getParameter("district_id");
//				if(district_id=="null"){
//					district_id = null;
//				//	System.out.println("HDF");
//				}
//				String where="";
//				if (district_id.equals("SPMC"))	
//					where=district_id!=null?" where location_id='"+district_id+"' and status<>'D'":"";
//				else
//					where=district_id!=null?" where district_id='"+district_id+"' and status<>'D'":"";
//				System.out.println("where"+where);
	          //  ResultSet rs = stmt.executeQuery("select * from prwss_main.adv_upload"+where);
	            ResultSet rs = stmt.executeQuery("select * from prwss_main.adv_upload where status<>'D'");
	            buffer="<table border='0' class='table-content'><tr><th>S.NO.</th><th>Post Name</th><th>Description</th><th>Last Date</th><th>Download</th></tr>";
	            int i=0;
	            while(rs.next()){
	            	Date expiryDate = rs.getDate("expiry_date");
	            	//System.out.println(rs.getString("post_name"));
	            	if(MisUtility.ifEmpty(expiryDate)){
	            	//Date expiryDate = new Date(exDate.getTime()+(1000*60*60*24));
	            	System.out.println("Expiry Date: "+expiryDate);
	            	if(MisUtility.ifEmpty(expiryDate) && expiryDate.before(MisUtility.convertStringToDate(MisUtility.now("dd-MM-yyyy")))){
	            		//System.out.println("inside");
	            		buffer+="<tr><td>"+ ++i +"</td>"+
	            		"<td>"+rs.getString("post_name")+"</td>" +
	            		"<td>"+rs.getString("discription")+"</td>" +
	            		"<td>"+MisUtility.convertDateToString(rs.getDate("last_date"))+"</td>"+
	            		"<td>" +
	            			"<a href='/PRWSS_MIS/TenderUploadServlet.to?op=download&type=advFile&docType=adv&adv_upload_id="+
	            				rs.getString("adv_upload_id") +"' target='_blank'>Download PDF" +
	            			"</a>" +
	            		"</td>" +	            		
		            "</tr>";	
	            	}
	            	}
	            }
	            buffer+="</table>";
	        //   System.out.println("Buffer: "+buffer);
	            PrintWriter out = response.getWriter();
				if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
					out.print(buffer.toString());
				}
			}
			if(op!=null && op.equalsIgnoreCase("download")){
				String adv_upload_id=request.getParameter("adv_upload_id");
				//System.out.println("adv_upload_id"+adv_upload_id);
				AdvUploadBean advUploadBean = new AdvUploadBean();
				advUploadBean.setAdvUploadId(new Long(adv_upload_id));
				AdvUploadBean advUploadBeans  = tenderBO.getAdvUploaded(advUploadBean).get(0);
	            ServletOutputStream servletOutputStream=response.getOutputStream();
				response.setContentType("application/pdf"); 
				response.addHeader("Content-Disposition","attachment; filename=advFile.pdf");
	            	String type=request.getParameter("type");
	            	//System.out.println("Type"+type);
	            	byte[] imgBytes=null;
	            	
	        		//System.out.println("Inside");
	            	if(type!= null && type.equalsIgnoreCase("advFile")){
	            		imgBytes = advUploadBeans.getAdvFile();
	            		//System.out.println(imgBytes.length);
	            		//System.out.println(imgBytes.toString());
	            	}
	            	int len = imgBytes.length;
	            	response.setContentLength(len);
	            	servletOutputStream.write(imgBytes);
	            	servletOutputStream.flush();
	            	servletOutputStream.close();
			}            
            connection.close();        	
		}catch (Exception e) { 
			e.printStackTrace();			
		}	
		finally{
			try{
				if(connection !=  null){
					connection.close();
				}
			}catch(SQLException sq){
				sq.printStackTrace();
			}
		}
	}

	
	private static StringBuffer generateCsvFileBuffer()
	{
		StringBuffer writer = new StringBuffer();
	 
		writer.append("DisplayName");
		writer.append(',');
		writer.append("Age");
		writer.append(',');
		writer.append("HandPhone");
		writer.append('\n');
	 
	        writer.append("mkyong");
		writer.append(',');
		writer.append("26");
		writer.append(',');
		writer.append("0123456789");
		writer.append('\n');
		return writer;
	}
}
