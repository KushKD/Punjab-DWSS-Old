package com.prwss.mis.tender.struts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

import com.prwss.mis.common.MISJdcDaoImpl;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;
import com.prwss.mis.tender.TenderBO;
import com.prwss.mis.tender.dao.EOIUploadBean;
import com.prwss.mis.tender.dao.TenderDao;

public class EOIUploadAction extends DispatchAction {
	private MISSessionBean misSessionBean;
	private Logger log = Logger.getLogger(TenderUploadAction.class);
	private LocationDao locationDao;
	private TenderDao tenderDao;
	private TenderBO tenderBO;	
	public void setTenderDao(TenderDao tenderDao) {
		this.tenderDao = tenderDao;
	}
	public void setTenderBO(TenderBO tenderBO) {
		this.tenderBO = tenderBO;
	}

	private MISJdcDaoImpl misJdcDaoImpl;
	
	public void setMisJdcDaoImpl(MISJdcDaoImpl misJdcDaoImpl) {
		this.misJdcDaoImpl = misJdcDaoImpl;
	}
	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}
	private List <EOIUploadBean> getEOIUploaded(EOIUploadForm eoiUploadForm, List<String> statusList) throws SQLException{
		List <EOIUploadBean> eoiUploadBeans=new ArrayList<EOIUploadBean>();
		Connection connection = null;
		Statement statement = null; 
		String locationId=eoiUploadForm.getLocationId();
		Long eoiUploadId=eoiUploadForm.getEoiUploadId();
		String where=" 1=1 ";
		if(MisUtility.ifEmpty(locationId)) where+=" and location_id='"+locationId+"'";
		if(MisUtility.ifEmpty(eoiUploadId)) where +=" and eoi_upload_id='"+eoiUploadId+"'";
		if(!MisUtility.ifEmpty(statusList)){
			where += " and status in (";
			for (int i = 0; i < statusList.size(); i++) {
				where +="'"+statusList.get(i)+"',";
			}
			where=where.substring(0,where.length()-1);
			where += ")";
		}
		String sql="SELECT  district_id, location_id, eoi_referance_no, eoi_title, "+ 
					"status, ent_by, ent_date, auth_by, auth_date, freeze_by, freeze_date, "+ 
					"eoi_file_name1, eoi_file_name2, eoi_file_name3, deploy_site_name, "+ 
					"eoi_upload_id, eoi_file1, eoi_file2, eoi_file3, expiry_date "+
					" FROM prwss_main.eoi_upload where "+where;	
		System.out.println("SQL: "+sql);
		try {
			connection = misJdcDaoImpl.getMISDataSource().getConnection();
			connection.setAutoCommit(false);
			statement= connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);			
			while (rs.next()) {
				System.out.println("In While");
				EOIUploadBean eoiUploadBean=new EOIUploadBean();
				eoiUploadBean.setLocationId(rs.getString("location_id"));
				eoiUploadBean.setEoiUploadId(rs.getInt("eoi_upload_id"));
				eoiUploadBean.setExpiryDate(rs.getDate("expiry_date"));
				eoiUploadBean.setEoiReferanceNo(rs.getString("eoi_referance_no"));
				eoiUploadBean.setEoiTitle(rs.getString("eoi_title"));
				eoiUploadBeans.add(eoiUploadBean);
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
		return eoiUploadBeans;
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
		EOIUploadForm eoiUploadForm=(EOIUploadForm)form;
		//System.out.println(statusList);
		List<EOIUploadBean> eoiUploadBeans=null;
		eoiUploadBeans=getEOIUploaded(eoiUploadForm, statusList);//tenderBO.findTenderUploaded(tenderUploadForm, statusList);
		if(!MisUtility.ifEmpty(eoiUploadBeans)){
			request.setAttribute("eoiUploadList", eoiUploadBeans);
			ActionMessages errors = new ActionMessages();
			ActionMessage message = new ActionMessage("find.list");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveMessages(request, errors);
		}else{
			refreshEOIUploadForm(eoiUploadForm);
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
		EOIUploadForm eoiUploadForm=(EOIUploadForm)form;
		String sql ="UPDATE prwss_main.eoi_upload SET status=?, ent_by=?, ent_date=now()" +
		"  WHERE location_id=? and eoi_upload_id=?";
		try {
			connection = misJdcDaoImpl.getMISDataSource().getConnection();
			connection.setAutoCommit(false);
			preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setString(1, MISConstants.MASTER_STATUS_DELETED);
			preparedStatement.setLong(2, misSessionBean.getEnteredBy());
			preparedStatement.setString(3, eoiUploadForm.getLocationId());
			preparedStatement.setLong(4, eoiUploadForm.getEoiUploadId());
			int status=preparedStatement.executeUpdate();
			if (status>=1){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete", "Uploded EOI for LocationID -->"+eoiUploadForm.getLocationId()+ " and EOI Upload No.--> "+ eoiUploadForm.getEoiUploadId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete", "Uploded EOI for LocationID -->"+eoiUploadForm.getLocationId()+ " and EOI upload No.--> "+ eoiUploadForm.getEoiUploadId());
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
		refreshEOIUploadForm(eoiUploadForm);
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
		EOIUploadForm eoiUploadForm=(EOIUploadForm)form;
		String sql ="UPDATE prwss_main.eoi_upload SET location_id=?, eoi_title=?," +
				"eoi_referance_no=?, ent_by=?, ent_date=now()," +
				" expiry_date=? WHERE location_id=? and eoi_upload_id=?";
		try {
			connection = misJdcDaoImpl.getMISDataSource().getConnection();
			connection.setAutoCommit(false);
			preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setString(1, eoiUploadForm.getLocationId());
			preparedStatement.setString(2, eoiUploadForm.getEoiTitle());
			preparedStatement.setString(3, eoiUploadForm.getEoiReferenceNo());
			preparedStatement.setLong(4, misSessionBean.getEnteredBy());
			preparedStatement.setDate(5, MisUtility.convertStringToDate(eoiUploadForm.getExpiryDate()));
			preparedStatement.setString(6, eoiUploadForm.getLocationId());
			preparedStatement.setLong(7, eoiUploadForm.getEoiUploadId());
			int status=preparedStatement.executeUpdate();
			if (status>=1){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update", "Uploded EOI for LocationID -->"+eoiUploadForm.getLocationId()+ " and EOI upload No.--> "+ eoiUploadForm.getEoiUploadId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update", "Uploded EOI for LocationID -->"+eoiUploadForm.getLocationId()+ " and Tender No.--> "+ eoiUploadForm.getEoiUploadId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}

		} catch (SQLException e) {
			if (connection != null) {
		        try {
		        	System.out.println("RollBacked Statement");
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
		refreshEOIUploadForm(eoiUploadForm);
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
		this.setAttrib(request);
		EOIUploadForm eoiUploadForm = (EOIUploadForm)form;
		boolean status = false;
		try {
			if(!MisUtility.ifEmpty(eoiUploadForm.getExpiryDate())||!MisUtility.ifEmpty(eoiUploadForm.getEoiReferenceNo()) || !MisUtility.ifEmpty(eoiUploadForm.getEoiTitle()) || !MisUtility.ifEmpty(eoiUploadForm.getLocationId()) || !MisUtility.ifEmpty(eoiUploadForm.getEoiFile1().getFileSize())){
				 // System.out.println("exception+MIS004");
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}
			if(!eoiUploadForm.getEoiFile1().getContentType().equalsIgnoreCase("application/pdf")){
				// System.out.println("exception+MIS003");
				throw new MISException(MISExceptionCodes.MIS003,"File Format Exception");
			}
			 // System.out.println("File Name"+tenderUploadForm.getBidDocumentFile().getFileName()+tenderUploadForm.getTenderNo());
			  //System.out.println("File Size"+tenderUploadForm.getBidDocumentFile().getFileSize());
			  //System.out.println("File Type"+tenderUploadForm.getBidDocumentFile().getContentType());
			  LocationBean locationBean = new LocationBean();
			  locationBean.setLocationId(eoiUploadForm.getLocationId());
			  locationBean = locationDao.getLocation(locationBean);
			  EOIUploadBean eoiUploadBean = new EOIUploadBean();
			  //eoiUploadBean.setEoiUploadId(eoiUploadForm.getEoiUploadId()); 
			  eoiUploadBean.setLocationId(eoiUploadForm.getLocationId());
			  eoiUploadBean.setDistrictId(locationBean.getParentLocation());
			  eoiUploadBean.setEoiReferanceNo(eoiUploadForm.getEoiReferenceNo().trim());
			  eoiUploadBean.setEoiTitle(eoiUploadForm.getEoiTitle().trim());
			  eoiUploadBean.setEoiFileName1(fileNameAppenderToPDF(eoiUploadForm.getEoiFile1().getFileName(), eoiUploadForm.getEoiUploadId()));
			  eoiUploadBean.setEoiFileName2(fileNameAppenderToPDF(eoiUploadForm.getEoiFile2().getFileName(), eoiUploadForm.getEoiUploadId()));
			  eoiUploadBean.setEoiFileName3(fileNameAppenderToPDF(eoiUploadForm.getEoiFile3().getFileName(), eoiUploadForm.getEoiUploadId()));
			  eoiUploadBean.setExpiryDate(MisUtility.convertStringToDate(eoiUploadForm.getExpiryDate()));
			  
			  if(MisUtility.ifEmpty(eoiUploadForm.getEoiFile1().getFileData()))
				  eoiUploadBean.setEoiFile1(eoiUploadForm.getEoiFile1().getFileData());
			  if(MisUtility.ifEmpty(eoiUploadForm.getEoiFile2().getFileData()))
				  eoiUploadBean.setEoiFile2(eoiUploadForm.getEoiFile2().getFileData());
			  if(MisUtility.ifEmpty(eoiUploadForm.getEoiFile3().getFileData()))
				  eoiUploadBean.setEoiFile3(eoiUploadForm.getEoiFile3().getFileData());
			  status = tenderBO.uploadEOI(eoiUploadBean, misSessionBean);
			  if (status){
						ActionMessages errors = new ActionMessages();
						ActionMessage message = new ActionMessage("tender.upload.success.save");
						errors.add(ActionMessages.GLOBAL_MESSAGE, message);
						saveMessages(request, errors);
						refreshEOIUploadForm(eoiUploadForm);
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
		System.out.println("In EOI Upload");
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");		
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky","locationId@eoiUploadId");
		request.setAttribute("d__auto", "eoiUploadId");

	}
	public ActionForward populate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		String locationId = request.getParameter("locationId");
		String eoiUploadId = request.getParameter("eoiUploadId");
		EOIUploadForm eoiUploadForm=(EOIUploadForm)form;
		eoiUploadForm.setLocationId(locationId);
		eoiUploadForm.setEoiUploadId(Long.parseLong(eoiUploadId));
		List<EOIUploadBean> eoiUploadBeans=null;
		eoiUploadBeans=getEOIUploaded(eoiUploadForm, null);
		for (int i = 0; i < eoiUploadBeans.size(); i++) {
			EOIUploadBean eoiUploadBean=(EOIUploadBean)eoiUploadBeans.get(i);
			eoiUploadForm.setLocationId(eoiUploadBean.getLocationId());
			eoiUploadForm.setEoiReferenceNo(eoiUploadBean.getEoiReferanceNo());
			eoiUploadForm.setEoiTitle(eoiUploadBean.getEoiTitle());
			eoiUploadForm.setExpiryDate(MisUtility.convertDateToString(eoiUploadBean.getExpiryDate()));			
		} 
		return mapping.findForward("display");
	}
	private void refreshEOIUploadForm(EOIUploadForm eoiUploadForm) {
		eoiUploadForm.setEoiUploadId(null);
		eoiUploadForm.setEoiReferenceNo(null);
		eoiUploadForm.setEoiTitle(null);
		eoiUploadForm.setExpiryDate(null);
	}
	
	private String fileNameAppenderToPDF(String fileName,Long toAppendInt){
		String toAppend=toAppendInt+"";
		if(!fileName.isEmpty() && !toAppend.isEmpty() ){
		String file[] = fileName.split(".pdf");
		String changedFileName = file[0];
		changedFileName = changedFileName+"-"+toAppend+".pdf";
		return changedFileName;
		}
		return null;
		
	}

}
