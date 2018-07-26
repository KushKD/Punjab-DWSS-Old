package com.prwss.mis.tender.struts;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.MISJdcDaoImpl;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.exception.MISSessionTimeOutException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;
import com.prwss.mis.masters.village.dao.VillageViewBean;
import com.prwss.mis.masters.village.struts.VillageForm;
import com.prwss.mis.tender.TenderBO;
import com.prwss.mis.tender.dao.TenderDao;
import com.prwss.mis.tender.dao.TenderUploadBean;

public class TenderUploadAction extends DispatchAction {
	private MISSessionBean misSessionBean;
	private Logger log = Logger.getLogger(TenderUploadAction.class);
	private TenderDao tenderDao;
	private LocationDao locationDao;
	private TenderBO tenderBO;
	private MISJdcDaoImpl misJdcDaoImpl;
	
	public void setMisJdcDaoImpl(MISJdcDaoImpl misJdcDaoImpl) {
		this.misJdcDaoImpl = misJdcDaoImpl;
	}

	public void setTenderBO(TenderBO tenderBO) {
		this.tenderBO = tenderBO;
	}

	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	public void setTenderDao(TenderDao tenderDao) {
		this.tenderDao = tenderDao;
	}
	private List <TenderUploadBean> getTenderUploaded(TenderUploadForm tenderUploadForm, List<String> statusList) throws SQLException{
		List <TenderUploadBean> tenderUploadBeans=new ArrayList<TenderUploadBean>();
		Connection connection = null;
		Statement statement = null; 
		String locationId=tenderUploadForm.getLocationId();
		String tenderId=tenderUploadForm.getTenderNo();
		String where=" 1=1 ";
		if(MisUtility.ifEmpty(locationId)) where+=" and location_id='"+locationId+"'";
		if(MisUtility.ifEmpty(tenderId)) where +=" and tender_id='"+tenderId+"'";
		if(!MisUtility.ifEmpty(statusList)){
			where += " and status in (";
			for (int i = 0; i < statusList.size(); i++) {
				where +="'"+statusList.get(i)+"',";
			}
			where=where.substring(0,where.length()-1);
			where += ")";
		}
		String sql="SELECT tender_id, district_id, location_id, work_description, last_date," +
				"bid_opening_date, status, ent_by, ent_date, auth_by, auth_date," +
				"freeze_by, freeze_date, bid_doc_file_name, tender_notice_file_name," +
				"deploy_site_name, tender_upload_id, bid_doc_file, tender_notice_file,expiry_date" +
				" FROM prwss_main.tender_upload where "+where;	
		//System.out.println("SQL: "+sql);
		try {
			connection = misJdcDaoImpl.getMISDataSource().getConnection();
			connection.setAutoCommit(false);
			statement= connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);			
			while (rs.next()) {
				TenderUploadBean tenderUploadBean=new TenderUploadBean();
				tenderUploadBean.setLocationId(rs.getString("location_id"));
				tenderUploadBean.setTenderId(rs.getString("tender_id"));
				tenderUploadBean.setBidsOpeningDate(rs.getDate("bid_opening_date"));
				tenderUploadBean.setLastDateofReceipt((rs.getDate("last_date")));
				tenderUploadBean.setExpiryDate(rs.getDate("expiry_date"));
				tenderUploadBean.setWorkDescription(rs.getString("work_description"));							
				tenderUploadBeans.add(tenderUploadBean);
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
		return tenderUploadBeans;
	}
	
	public ActionForward find(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException,SQLException{
		//System.out.println("In-find");
		if (request.getSession().getAttribute("misSessionBean") != null) {
			misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
		} else {
			return mapping.findForward("login");
		}
		this.setAttrib(request);
		String mode = (String) request.getParameter("d__mode");
		List<String> statusList = new ArrayList<String>();
		if (mode != null && MISConstants.D_MODE_ENQUIRE.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		if (mode != null && MISConstants.D_MODE_MODIFY.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		if (mode != null && MISConstants.D_MODE_DELETE.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		if (mode != null && MISConstants.D_MODE_POST.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		TenderUploadForm tenderUploadForm=(TenderUploadForm)form;
		//System.out.println(statusList);
		List<TenderUploadBean> tenderUploadBeans=null;
		tenderUploadBeans=getTenderUploaded(tenderUploadForm, statusList);//tenderBO.findTenderUploaded(tenderUploadForm, statusList);
		if(!MisUtility.ifEmpty(tenderUploadBeans)){
			request.setAttribute("tenderUploadList", tenderUploadBeans);
			ActionMessages errors = new ActionMessages();
			ActionMessage message = new ActionMessage("find.list");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveMessages(request, errors);
		}else{
			refreshTenderUploadForm(tenderUploadForm);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ","the Transaction");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		return mapping.findForward("display");
	}
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException,SQLException {
		//System.out.println("Delete");
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession()
						.getAttribute("misSessionBean");
			}
		} else {
			//System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		this.setAttrib(request);
		Connection connection = null;
		PreparedStatement preparedStatement= null;
		TenderUploadForm tenderUploadForm=(TenderUploadForm)form;
		String sql ="UPDATE prwss_main.tender_upload SET status=?, ent_by=?, ent_date=now()" +
		"  WHERE location_id=? and tender_id=?";
		try {
			connection = misJdcDaoImpl.getMISDataSource().getConnection();
			connection.setAutoCommit(false);
			preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setString(1, MISConstants.MASTER_STATUS_DELETED);
			preparedStatement.setLong(2, misSessionBean.getEnteredBy());
			preparedStatement.setString(3, tenderUploadForm.getLocationId());
			preparedStatement.setString(4, tenderUploadForm.getTenderNo());
			int status=preparedStatement.executeUpdate();
			if (status>=1){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete", "Uploded Tender for LocationID -->"+tenderUploadForm.getLocationId()+ " and Tender No.--> "+ tenderUploadForm.getTenderNo());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete", "Uploded Tender for LocationID -->"+tenderUploadForm.getLocationId()+ " and Tender No.--> "+ tenderUploadForm.getTenderNo());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
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
			if (preparedStatement!= null) { 
				preparedStatement.close(); 
			}
		    connection.setAutoCommit(true);
		    connection.close();
		}
		refreshTenderUploadForm(tenderUploadForm);
		return mapping.findForward("display");
	}
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException,SQLException{
		//System.out.println("Update");
		this.setAttrib(request);
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession()
						.getAttribute("misSessionBean");
			}
		} else {
			//System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		Connection connection = null;
		PreparedStatement preparedStatement= null;
		TenderUploadForm tenderUploadForm=(TenderUploadForm)form;
		String sql ="UPDATE prwss_main.tender_upload SET tender_id=?, location_id=?, work_description=?," +
				"last_date=?, bid_opening_date=?, ent_by=?, ent_date=now()," +
				" expiry_date=? WHERE location_id=? and tender_id=?";
		try {
			connection = misJdcDaoImpl.getMISDataSource().getConnection();
			connection.setAutoCommit(false);
			preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setString(1, tenderUploadForm.getTenderNo());
			preparedStatement.setString(2, tenderUploadForm.getLocationId());
			preparedStatement.setString(3, tenderUploadForm.getWorkDescription());
			preparedStatement.setDate(4, MisUtility.convertStringToDate(tenderUploadForm.getLastDateofReceipt()));
			preparedStatement.setDate(5, MisUtility.convertStringToDate(tenderUploadForm.getBidsOpeningDate()));
			preparedStatement.setLong(6, misSessionBean.getEnteredBy());
			preparedStatement.setDate(7, MisUtility.convertStringToDate(tenderUploadForm.getExpiryDate()));
			preparedStatement.setString(8, tenderUploadForm.getLocationId());
			preparedStatement.setString(9, tenderUploadForm.getTenderNo());
			int status=preparedStatement.executeUpdate();
			if (status>=1){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update", "Uploded Tender for LocationID -->"+tenderUploadForm.getLocationId()+ " and Tender No.--> "+ tenderUploadForm.getTenderNo());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update", "Uploded Tender for LocationID -->"+tenderUploadForm.getLocationId()+ " and Tender No.--> "+ tenderUploadForm.getTenderNo());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
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
			if (preparedStatement!= null) { 
				preparedStatement.close(); 
			}
		    connection.setAutoCommit(true);
		    connection.close();
		}
		refreshTenderUploadForm(tenderUploadForm);
		return mapping.findForward("display");
	}
	public ActionForward post(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
	//	System.out.println("Post");
		return mapping.findForward("display");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession()
						.getAttribute("misSessionBean");
			}
		} else {
			//System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
			//System.out.println("In Tender Upload Save");
			this.setAttrib(request);
			boolean status = false;
		 
		  TenderUploadForm tenderUploadForm = (TenderUploadForm)form;
		try {
			if(!MisUtility.ifEmpty(tenderUploadForm.getTenderNo())||!MisUtility.ifEmpty(tenderUploadForm.getExpiryDate())||!MisUtility.ifEmpty(tenderUploadForm.getBidsOpeningDate()) || !MisUtility.ifEmpty(tenderUploadForm.getLastDateofReceipt()) || !MisUtility.ifEmpty(tenderUploadForm.getLocationId()) || !MisUtility.ifEmpty(tenderUploadForm.getWorkDescription()) || !MisUtility.ifEmpty(tenderUploadForm.getBidDocumentFile().getFileSize())||!MisUtility.ifEmpty(tenderUploadForm.getTenderNoticeFile().getFileSize())){
				 // System.out.println("exception+MIS004");
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
				
			}
			if(!tenderUploadForm.getBidDocumentFile().getContentType().equalsIgnoreCase("application/pdf")||!tenderUploadForm.getTenderNoticeFile().getContentType().equalsIgnoreCase("application/pdf")){
				// System.out.println("exception+MIS003");
				throw new MISException(MISExceptionCodes.MIS003,"File Format Exception");
			}
			 // System.out.println("File Name"+tenderUploadForm.getBidDocumentFile().getFileName()+tenderUploadForm.getTenderNo());
			  //System.out.println("File Size"+tenderUploadForm.getBidDocumentFile().getFileSize());
			  //System.out.println("File Type"+tenderUploadForm.getBidDocumentFile().getContentType());
			  LocationBean locationBean = new LocationBean();
			  locationBean.setLocationId(tenderUploadForm.getLocationId());
			  locationBean = locationDao.getLocation(locationBean);
			  TenderUploadBean tenderUploadBean = new TenderUploadBean();
			  tenderUploadBean.setTenderId(tenderUploadForm.getTenderNo().trim()); 
			  tenderUploadBean.setLocationId(tenderUploadForm.getLocationId());
			  tenderUploadBean.setDistrictId(locationBean.getParentLocation());
			  tenderUploadBean.setLastDateofReceipt(MisUtility.convertStringToDate(tenderUploadForm.getLastDateofReceipt()));
			  tenderUploadBean.setBidsOpeningDate(MisUtility.convertStringToDate(tenderUploadForm.getBidsOpeningDate()));
			  tenderUploadBean.setWorkDescription(tenderUploadForm.getWorkDescription().trim());
			  tenderUploadBean.setBidDocFileName(fileNameAppenderToPDF(tenderUploadForm.getBidDocumentFile().getFileName(), tenderUploadForm.getTenderNo()));
			  tenderUploadBean.setTenderNoticeFileName(fileNameAppenderToPDF(tenderUploadForm.getTenderNoticeFile().getFileName(), tenderUploadForm.getTenderNo()));
			  
			  
			  tenderUploadBean.setExpiryDate(MisUtility.convertStringToDate(tenderUploadForm.getExpiryDate()));
			  if(MisUtility.ifEmpty(tenderUploadForm.getBidDocumentFile().getFileData()))
			  tenderUploadBean.setBidDocFile(tenderUploadForm.getBidDocumentFile().getFileData());
			 
			  if(MisUtility.ifEmpty(tenderUploadForm.getTenderNoticeFile().getFileData()))
			  tenderUploadBean.setTenderNoticeFile(tenderUploadForm.getTenderNoticeFile().getFileData());
			  
			 // System.out.println("Byte Lenght"+tenderUploadForm.getTenderNoticeFile().getFileData().length);
			  
			  status = tenderBO.uploadTender(tenderUploadBean, misSessionBean);
			  if (status){
						ActionMessages errors = new ActionMessages();
						ActionMessage message = new ActionMessage("tender.upload.success.save");
						errors.add(ActionMessages.GLOBAL_MESSAGE, message);
						saveMessages(request, errors);
						refreshTenderUploadForm(tenderUploadForm);
				} else {
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("fatal.error.save", "Tender Uploading Failed");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
				}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS004.equals(e.getCode())) {
				//System.out.println("in MIS004");
				log.error(e.getLocalizedMessage(),e);
				//e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("tender.upload.key.field");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}if (MISExceptionCodes.MIS003.equals(e.getCode())) {
				//System.out.println("in MIS003");
				log.error(e.getLocalizedMessage(),e);
				//e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("tender.upload.file.type");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				//System.out.println("in MIS001");
				log.error(e.getLocalizedMessage(),e);
				//e.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("tender.upload.duplicate");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
			
		}catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			//e.printStackTrace();
			//log.error(e.getLocalizedMessage(),e);
			//e.printStackTrace();	
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Saving of Tender");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		  
		return mapping.findForward("display");
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession()
						.getAttribute("misSessionBean");
			}
		} else {
			//System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		setAttrib(request);
		//System.out.println("In Tender Upload");
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");		
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky","locationId@tenderNo");
		// request.setAttribute("d__auto", "locRequestNo");

	}
	public ActionForward populate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		String locationId = request.getParameter("locationId");
		String tenderId = request.getParameter("tenderId");
		TenderUploadForm tenderUploadForm=(TenderUploadForm)form;
		tenderUploadForm.setLocationId(locationId);
		tenderUploadForm.setTenderNo(tenderId);
		List<TenderUploadBean> tenderUploadBeans=null;
		tenderUploadBeans=getTenderUploaded(tenderUploadForm, null);
		for (int i = 0; i < tenderUploadBeans.size(); i++) {
			TenderUploadBean tenderUploadBean=(TenderUploadBean)tenderUploadBeans.get(i);
			tenderUploadForm.setLocationId(tenderUploadBean.getLocationId());
			tenderUploadForm.setTenderNo(tenderUploadBean.getTenderId());
			tenderUploadForm.setBidsOpeningDate(MisUtility.convertDateToString(tenderUploadBean.getBidsOpeningDate()));
			tenderUploadForm.setLastDateofReceipt(MisUtility.convertDateToString(tenderUploadBean.getLastDateofReceipt()));
			tenderUploadForm.setExpiryDate(MisUtility.convertDateToString(tenderUploadBean.getExpiryDate()));
			tenderUploadForm.setWorkDescription(tenderUploadBean.getWorkDescription());
		} 
		return mapping.findForward("display");
	}
	private void refreshTenderUploadForm(TenderUploadForm tenderUploadForm) {
		tenderUploadForm.setTenderNo(null);
		tenderUploadForm.setBidDocumentFile(null);
		tenderUploadForm.setBidsOpeningDate(null);
		tenderUploadForm.setLastDateofReceipt(null);
		tenderUploadForm.setLocationId(null);
		tenderUploadForm.setExpiryDate(null);
		tenderUploadForm.setWorkDescription(null);
		tenderUploadForm.setTenderNoticeFile(null);
	}
	
	private String fileNameAppenderToPDF(String fileName,String toAppend){
		
		if(!fileName.isEmpty() && !toAppend.isEmpty() ){
		String file[] = fileName.split(".pdf");
		String changedFileName = file[0];
		changedFileName = changedFileName+"-"+toAppend+".pdf";
		return changedFileName;
		}
		return null;
		
	}

}
