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
import com.prwss.mis.tender.dao.AdvUploadBean;

public class AdvUploadAction extends DispatchAction {
	private MISSessionBean misSessionBean;
	private Logger log = Logger.getLogger(TenderUploadAction.class);
	private LocationDao locationDao;
	private TenderBO tenderBO;	
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
	private List <AdvUploadBean> getAdvUploaded(AdvUploadForm advUploadForm, List<String> statusList) throws SQLException{
		List <AdvUploadBean> advUploadBeans=new ArrayList<AdvUploadBean>();
		Connection connection = null;
		Statement statement = null; 
		String locationId=advUploadForm.getLocationId();
		Long advUploadId=advUploadForm.getAdvUploadId();
		String where=" 1=1 ";
		if(MisUtility.ifEmpty(locationId)) where+=" and location_id='"+locationId+"'";
		if(MisUtility.ifEmpty(advUploadId)) where +=" and adv_upload_id='"+advUploadId+"'";
		if(!MisUtility.ifEmpty(statusList)){
			where += " and status in (";
			for (int i = 0; i < statusList.size(); i++) {
				where +="'"+statusList.get(i)+"',";
			}
			where=where.substring(0,where.length()-1);
			where += ")";
		}
		String sql="SELECT  district_id, location_id, post_name, last_date, discription,  "+ 
					"status, ent_by, ent_date, auth_by, auth_date, freeze_by, freeze_date, "+ 
					"adv_file_name,  deploy_site_name, "+ 
					"adv_upload_id, adv_file, expiry_date "+
					" FROM prwss_main.adv_upload where "+where;	
		System.out.println("SQL: "+sql);
		try {
			connection = misJdcDaoImpl.getMISDataSource().getConnection();
			connection.setAutoCommit(false);
			statement= connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);			
			while (rs.next()) {
				System.out.println("In While");
				AdvUploadBean advUploadBean=new AdvUploadBean();
				advUploadBean.setLocationId(rs.getString("location_id"));
				advUploadBean.setAdvUploadId(rs.getInt("adv_upload_id"));
				advUploadBean.setLastDate(rs.getDate("last_date"));
				advUploadBean.setExpiryDate(rs.getDate("expiry_date"));
				advUploadBean.setAdvDiscription(rs.getString("discription"));
				advUploadBean.setPostName(rs.getString("post_name"));
				advUploadBeans.add(advUploadBean);
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
		return advUploadBeans;
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
		this.setAttrib(request);

		AdvUploadForm advUploadForm=(AdvUploadForm)form;
		//System.out.println(statusList);
		List<AdvUploadBean> advUploadBeans=null;
		advUploadBeans=getAdvUploaded(advUploadForm, statusList);//tenderBO.findTenderUploaded(tenderUploadForm, statusList);
		if(!MisUtility.ifEmpty(advUploadBeans)){
			request.setAttribute("advUploadList", advUploadBeans);
			ActionMessages errors = new ActionMessages();
			ActionMessage message = new ActionMessage("find.list");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveMessages(request, errors);
		}else{
			refreshAdvUploadForm(advUploadForm);
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
		AdvUploadForm advUploadForm=(AdvUploadForm)form;
		String sql ="UPDATE prwss_main.adv_upload SET status=?, ent_by=?, ent_date=now()" +
		"  WHERE location_id=? and adv_upload_id=?";
		try {
			connection = misJdcDaoImpl.getMISDataSource().getConnection();
			connection.setAutoCommit(false);
			preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setString(1, MISConstants.MASTER_STATUS_DELETED);
			preparedStatement.setLong(2, misSessionBean.getEnteredBy());
			preparedStatement.setString(3, advUploadForm.getLocationId());
			preparedStatement.setLong(4, advUploadForm.getAdvUploadId());
			int status=preparedStatement.executeUpdate();
			if (status>=1){
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("success.delete", "Uploded Advertisement  for LocationID -->"+advUploadForm.getLocationId()+ " and Advertisement Upload No.--> "+ advUploadForm.getAdvUploadId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete", "Uploded Advertisement for LocationID -->"+advUploadForm.getLocationId()+ " and Advertisement upload No.--> "+ advUploadForm.getAdvUploadId());
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
		refreshAdvUploadForm(advUploadForm);
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
		/*Connection connection = null;
		PreparedStatement preparedStatement= null;
		AdvUploadForm advUploadForm=(AdvUploadForm)form;
		String sql ="UPDATE prwss_main.adv_upload SET location_id=?, " +
				"discription=?, ent_by=?, ent_date=now()," +
				" expiry_date=?, last_date=?, post_name=?,adv_upload_id=? WHERE location_id='"+advUploadForm.getLocationId()+"' and adv_upload_id="+advUploadForm.getAdvUploadId();
		System.out.println("sql is "+sql);
		try {
			connection = misJdcDaoImpl.getMISDataSource().getConnection();
			connection.setAutoCommit(false);
			System.out.println("in.................");
			preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setString(1, advUploadForm.getLocationId());
			preparedStatement.setString(2, advUploadForm.getAdvDiscription());
			preparedStatement.setLong(3, misSessionBean.getEnteredBy());
			preparedStatement.setDate(4, MisUtility.convertStringToDate(advUploadForm.getExpiryDate()));
			preparedStatement.setDate(5, MisUtility.convertStringToDate(advUploadForm.getLastDate()));
			preparedStatement.setString(6, advUploadForm.getPostName());
			preparedStatement.setLong(7, advUploadForm.getAdvUploadId());
			int status=preparedStatement.executeUpdate();
			if (status>=1){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update", "Uploded Advertisement for LocationID -->"+advUploadForm.getLocationId()+ " and Advertisement upload No.--> "+ advUploadForm.getAdvUploadId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update", "Uploded Advertisement for LocationID -->"+advUploadForm.getLocationId()+ " and Advertisement No.--> "+ advUploadForm.getAdvUploadId());
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
			System.out.println("out................");
		    connection.setAutoCommit(true);
			System.out.println("out................innnnnnnnnn");
		    connection.close();
		}
		refreshAdvUploadForm(advUploadForm);*/
		AdvUploadForm advUploadForm = (AdvUploadForm)form;
		boolean status = false;
		try {
			if(!MisUtility.ifEmpty(advUploadForm.getExpiryDate())||!MisUtility.ifEmpty(advUploadForm.getAdvDiscription())  || !MisUtility.ifEmpty(advUploadForm.getLocationId()) || !MisUtility.ifEmpty(advUploadForm.getAdvFile().getFileSize())){
				 // System.out.println("exception+MIS004");
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}
			if(!advUploadForm.getAdvFile().getContentType().equalsIgnoreCase("application/pdf")){
				// System.out.println("exception+MIS003");
				throw new MISException(MISExceptionCodes.MIS003,"File Format Exception");
			}
			 // System.out.println("File Name"+tenderUploadForm.getBidDocumentFile().getFileName()+tenderUploadForm.getTenderNo());
			  //System.out.println("File Size"+tenderUploadForm.getBidDocumentFile().getFileSize());
			  //System.out.println("File Type"+tenderUploadForm.getBidDocumentFile().getContentType());
			  LocationBean locationBean = new LocationBean();
			  locationBean.setLocationId(advUploadForm.getLocationId());
			  locationBean = locationDao.getLocation(locationBean);
			  AdvUploadBean advUploadBean = new AdvUploadBean();
			  advUploadBean.setLocationId(advUploadForm.getLocationId());
			  advUploadBean.setDistrictId(locationBean.getParentLocation());
			  advUploadBean.setPostName(advUploadForm.getPostName().trim());
			  advUploadBean.setAdvDiscription(advUploadForm.getAdvDiscription().trim());
			  advUploadBean.setAdvFileName(fileNameAppenderToPDF(advUploadForm.getAdvFile().getFileName(), advUploadForm.getAdvUploadId()));
			  advUploadBean.setExpiryDate(MisUtility.convertStringToDate(advUploadForm.getExpiryDate()));
			  advUploadBean.setAdvUploadId(advUploadForm.getAdvUploadId());
			  advUploadBean.setLastDate(MisUtility.convertStringToDate(advUploadForm.getLastDate()));
			  if(MisUtility.ifEmpty(advUploadForm.getAdvFile().getFileData()))
				  advUploadBean.setAdvFile(advUploadForm.getAdvFile().getFileData());
			  status = tenderBO.updateAdv(advUploadBean, misSessionBean);
			  if (status){
						ActionMessages errors = new ActionMessages();
						ActionMessage message = new ActionMessage("tender.upload.success.update");
						errors.add(ActionMessages.GLOBAL_MESSAGE, message);
						saveMessages(request, errors);
						refreshAdvUploadForm(advUploadForm);
				} else {
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("fatal.error.update", "Tender Updating Failed");
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
			ActionMessage message = new ActionMessage("fatal.error.update","Updating of Tender");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		
		
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
		AdvUploadForm advUploadForm = (AdvUploadForm)form;
		boolean status = false;
		try {
			if(!MisUtility.ifEmpty(advUploadForm.getExpiryDate())||!MisUtility.ifEmpty(advUploadForm.getAdvDiscription())  || !MisUtility.ifEmpty(advUploadForm.getLocationId()) || !MisUtility.ifEmpty(advUploadForm.getAdvFile().getFileSize())){
				 // System.out.println("exception+MIS004");
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}
			if(!advUploadForm.getAdvFile().getContentType().equalsIgnoreCase("application/pdf")){
				// System.out.println("exception+MIS003");
				throw new MISException(MISExceptionCodes.MIS003,"File Format Exception");
			}
			 // System.out.println("File Name"+tenderUploadForm.getBidDocumentFile().getFileName()+tenderUploadForm.getTenderNo());
			  //System.out.println("File Size"+tenderUploadForm.getBidDocumentFile().getFileSize());
			  //System.out.println("File Type"+tenderUploadForm.getBidDocumentFile().getContentType());
			  LocationBean locationBean = new LocationBean();
			  locationBean.setLocationId(advUploadForm.getLocationId());
			  locationBean = locationDao.getLocation(locationBean);
			  AdvUploadBean advUploadBean = new AdvUploadBean();
			  advUploadBean.setLocationId(advUploadForm.getLocationId());
			  advUploadBean.setDistrictId(locationBean.getParentLocation());
			  advUploadBean.setPostName(advUploadForm.getPostName().trim());
			  advUploadBean.setAdvDiscription(advUploadForm.getAdvDiscription().trim());
			  advUploadBean.setAdvFileName(fileNameAppenderToPDF(advUploadForm.getAdvFile().getFileName(), advUploadForm.getAdvUploadId()));
			  advUploadBean.setExpiryDate(MisUtility.convertStringToDate(advUploadForm.getExpiryDate()));
			  advUploadBean.setLastDate(MisUtility.convertStringToDate(advUploadForm.getLastDate()));
			  if(MisUtility.ifEmpty(advUploadForm.getAdvFile().getFileData()))
				  advUploadBean.setAdvFile(advUploadForm.getAdvFile().getFileData());
			  status = tenderBO.uploadAdv(advUploadBean, misSessionBean);
			  if (status){
						ActionMessages errors = new ActionMessages();
						ActionMessage message = new ActionMessage("tender.upload.success.save");
						errors.add(ActionMessages.GLOBAL_MESSAGE, message);
						saveMessages(request, errors);
						refreshAdvUploadForm(advUploadForm);
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
		System.out.println("In adv Upload");
		return mapping.findForward("display");
	}

	
	public ActionForward populate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		this.setAttrib(request);
		request.setAttribute("level2","true");
		String locationId = request.getParameter("locationId");
		String advUploadId = request.getParameter("advUploadId");
		AdvUploadForm advUploadForm=(AdvUploadForm)form;
		advUploadForm.setLocationId(locationId);
		advUploadForm.setAdvUploadId(Long.parseLong(advUploadId));
		List<AdvUploadBean> advUploadBeans=null;
		advUploadBeans=getAdvUploaded(advUploadForm, null);
		for (int i = 0; i < advUploadBeans.size(); i++) {
			AdvUploadBean advUploadBean=(AdvUploadBean)advUploadBeans.get(i);
			advUploadForm.setLocationId(advUploadBean.getLocationId());
			advUploadForm.setAdvDiscription(advUploadBean.getAdvDiscription());
			advUploadForm.setExpiryDate(MisUtility.convertDateToString(advUploadBean.getExpiryDate()));	
			advUploadForm.setPostName(advUploadBean.getPostName());
			advUploadForm.setLastDate(MisUtility.convertDateToString(advUploadBean.getLastDate()));
			
		} 
		return mapping.findForward("display");
	}
	private void refreshAdvUploadForm(AdvUploadForm advUploadForm) {
		advUploadForm.setAdvUploadId(null);
		advUploadForm.setAdvDiscription(null);
		advUploadForm.setExpiryDate(null);
		advUploadForm.setLastDate(null);
		advUploadForm.setPostName(null);
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
	private void setAttrib(HttpServletRequest request) {
		System.out.println("Mode 1111111111"+request.getParameter("d__mode"));
		request.setAttribute("Rpt", "ent");		
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky","locationId@advUploadId");
		request.setAttribute("d__auto", "advUploadId");

	}

}
