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
import com.prwss.mis.tender.dao.QuoatationUploadBean;
import com.prwss.mis.tender.dao.TenderDao;

public class QuoatationUploadAction extends DispatchAction {
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
	private List <QuoatationUploadBean> getQuoatationUploaded(QuoatationUploadForm quoatationUploadForm, List<String> statusList) throws SQLException{
		List <QuoatationUploadBean> quoatationUploadBeans=new ArrayList<QuoatationUploadBean>();
		Connection connection = null;
		Statement statement = null; 
		String locationId=quoatationUploadForm.getLocationId();
		Long quoatationUploadId=quoatationUploadForm.getQuoatationUploadId();
		String where=" 1=1 ";
		if(MisUtility.ifEmpty(locationId)) where+=" and location_id='"+locationId+"'";
		if(MisUtility.ifEmpty(quoatationUploadId)) where +=" and quoatation_upload_id='"+quoatationUploadId+"'";
		if(!MisUtility.ifEmpty(statusList)){
			where += " and status in (";
			for (int i = 0; i < statusList.size(); i++) {
				where +="'"+statusList.get(i)+"',";
			}
			where=where.substring(0,where.length()-1);
			where += ")";
		}
		String sql="SELECT  district_id, location_id, quoatation_discription,  "+ 
					"status, ent_by, ent_date, auth_by, auth_date, freeze_by, freeze_date, "+ 
					"quoatation_file_name,  deploy_site_name, "+ 
					"quoatation_upload_id, quoatation_file, expiry_date "+
					" FROM prwss_main.quoatation_upload where "+where;	
		System.out.println("SQL: "+sql);
		try {
			connection = misJdcDaoImpl.getMISDataSource().getConnection();
			connection.setAutoCommit(false);
			statement= connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);			
			while (rs.next()) {
				System.out.println("In While");
				QuoatationUploadBean quoatationUploadBean=new QuoatationUploadBean();
				quoatationUploadBean.setLocationId(rs.getString("location_id"));
				quoatationUploadBean.setQuoatationUploadId(rs.getInt("quoatation_upload_id"));
				quoatationUploadBean.setExpiryDate(rs.getDate("expiry_date"));
				quoatationUploadBean.setQuoatationDiscription(rs.getString("quoatation_discription"));
				quoatationUploadBeans.add(quoatationUploadBean);
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
		return quoatationUploadBeans;
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
		QuoatationUploadForm quoatationUploadForm=(QuoatationUploadForm)form;
		//System.out.println(statusList);
		List<QuoatationUploadBean> quoatationUploadBeans=null;
		quoatationUploadBeans=getQuoatationUploaded(quoatationUploadForm, statusList);//tenderBO.findTenderUploaded(tenderUploadForm, statusList);
		if(!MisUtility.ifEmpty(quoatationUploadBeans)){
			request.setAttribute("quoatationUploadList", quoatationUploadBeans);
			ActionMessages errors = new ActionMessages();
			ActionMessage message = new ActionMessage("find.list");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveMessages(request, errors);
		}else{
			refreshQuoatationUploadForm(quoatationUploadForm);
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
		QuoatationUploadForm quoatationUploadForm=(QuoatationUploadForm)form;
		String sql ="UPDATE prwss_main.quoatation_upload SET status=?, ent_by=?, ent_date=now()" +
		"  WHERE location_id=? and quoatation_upload_id=?";
		try {
			connection = misJdcDaoImpl.getMISDataSource().getConnection();
			connection.setAutoCommit(false);
			preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setString(1, MISConstants.MASTER_STATUS_DELETED);
			preparedStatement.setLong(2, misSessionBean.getEnteredBy());
			preparedStatement.setString(3, quoatationUploadForm.getLocationId());
			preparedStatement.setLong(4, quoatationUploadForm.getQuoatationUploadId());
			int status=preparedStatement.executeUpdate();
			if (status>=1){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete", "Uploded Quoatation Notices for LocationID -->"+quoatationUploadForm.getLocationId()+ " and Quotation Upload No.--> "+ quoatationUploadForm.getQuoatationUploadId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete", "Uploded Quoatation Notices for LocationID -->"+quoatationUploadForm.getLocationId()+ " and Quotation upload No.--> "+ quoatationUploadForm.getQuoatationUploadId());
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
		refreshQuoatationUploadForm(quoatationUploadForm);
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
		QuoatationUploadForm quoatationUploadForm=(QuoatationUploadForm)form;
		String sql ="UPDATE prwss_main.quoatation_upload SET location_id=?, " +
				"quoatation_discription=?, ent_by=?, ent_date=now()," +
				" expiry_date=? WHERE location_id=? and quoatation_upload_id=?";
		System.out.println("SQL : "+sql);
		try {
			connection = misJdcDaoImpl.getMISDataSource().getConnection();
			connection.setAutoCommit(false);
			preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setString(1, quoatationUploadForm.getLocationId());
			preparedStatement.setString(2, quoatationUploadForm.getQuoatationDiscription());
			preparedStatement.setLong(3, misSessionBean.getEnteredBy());
			preparedStatement.setDate(4, MisUtility.convertStringToDate(quoatationUploadForm.getExpiryDate()));
			preparedStatement.setString(5, quoatationUploadForm.getLocationId());
			preparedStatement.setLong(6, quoatationUploadForm.getQuoatationUploadId());
			
			int status=preparedStatement.executeUpdate();
			if (status>=1){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update", "Uploded Quoatation Notices for LocationID -->"+quoatationUploadForm.getLocationId()+ " and Quoatation upload No.--> "+ quoatationUploadForm.getQuoatationUploadId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update", "Uploded Quoatation Notices for LocationID -->"+quoatationUploadForm.getLocationId()+ " and Quoatation No.--> "+ quoatationUploadForm.getQuoatationUploadId());
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
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("error.update", "Uploded Quoatation Notices for LocationID -->"+quoatationUploadForm.getLocationId()+ " and Quoatation No.--> "+ quoatationUploadForm.getQuoatationUploadId());
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			e.printStackTrace();
		}finally {
			if (preparedStatement!= null) { 
				preparedStatement.close(); 
			}
		    connection.setAutoCommit(true);
		    connection.close();
		}
		refreshQuoatationUploadForm(quoatationUploadForm);
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
		QuoatationUploadForm quoatationUploadForm = (QuoatationUploadForm)form;
		boolean status = false;
		try {
			if(!MisUtility.ifEmpty(quoatationUploadForm.getExpiryDate())||!MisUtility.ifEmpty(quoatationUploadForm.getQuoatationDiscription())  || !MisUtility.ifEmpty(quoatationUploadForm.getLocationId()) || !MisUtility.ifEmpty(quoatationUploadForm.getQuoatationFile().getFileSize())){
				 // System.out.println("exception+MIS004");
				throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
			}
			if(!quoatationUploadForm.getQuoatationFile().getContentType().equalsIgnoreCase("application/pdf")){
				// System.out.println("exception+MIS003");
				throw new MISException(MISExceptionCodes.MIS003,"File Format Exception");
			}
			 // System.out.println("File Name"+tenderUploadForm.getBidDocumentFile().getFileName()+tenderUploadForm.getTenderNo());
			  //System.out.println("File Size"+tenderUploadForm.getBidDocumentFile().getFileSize());
			  //System.out.println("File Type"+tenderUploadForm.getBidDocumentFile().getContentType());
			  LocationBean locationBean = new LocationBean();
			  locationBean.setLocationId(quoatationUploadForm.getLocationId());
			  locationBean = locationDao.getLocation(locationBean);
			  QuoatationUploadBean quoatationUploadBean = new QuoatationUploadBean();
			  quoatationUploadBean.setLocationId(quoatationUploadForm.getLocationId());
			  quoatationUploadBean.setDistrictId(locationBean.getParentLocation());
			  quoatationUploadBean.setQuoatationDiscription(quoatationUploadForm.getQuoatationDiscription().trim());
			  quoatationUploadBean.setQuoatationFileName(fileNameAppenderToPDF(quoatationUploadForm.getQuoatationFile().getFileName(), quoatationUploadForm.getQuoatationUploadId()));
			  quoatationUploadBean.setExpiryDate(MisUtility.convertStringToDate(quoatationUploadForm.getExpiryDate()));
			  
			  if(MisUtility.ifEmpty(quoatationUploadForm.getQuoatationFile().getFileData()))
				  quoatationUploadBean.setQuoatationFile(quoatationUploadForm.getQuoatationFile().getFileData());
			  status = tenderBO.uploadQuoatation(quoatationUploadBean, misSessionBean);
			  if (status){
						ActionMessages errors = new ActionMessages();
						ActionMessage message = new ActionMessage("tender.upload.success.save");
						errors.add(ActionMessages.GLOBAL_MESSAGE, message);
						saveMessages(request, errors);
						refreshQuoatationUploadForm(quoatationUploadForm);
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
		System.out.println("In quoatation Upload");
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");		
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky","locationId@quoatationUploadId");
		request.setAttribute("d__auto", "quoatationUploadId");

	}
	public ActionForward populate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		String locationId = request.getParameter("locationId");
		String quoatationUploadId = request.getParameter("quoatationUploadId");
		QuoatationUploadForm quoatationUploadForm=(QuoatationUploadForm)form;
		quoatationUploadForm.setLocationId(locationId);
		quoatationUploadForm.setQuoatationUploadId(Long.parseLong(quoatationUploadId));
		List<QuoatationUploadBean> quoatationUploadBeans=null;
		quoatationUploadBeans=getQuoatationUploaded(quoatationUploadForm, null);
		for (int i = 0; i < quoatationUploadBeans.size(); i++) {
			QuoatationUploadBean quoatationUploadBean=(QuoatationUploadBean)quoatationUploadBeans.get(i);
			quoatationUploadForm.setLocationId(quoatationUploadBean.getLocationId());
			quoatationUploadForm.setQuoatationDiscription(quoatationUploadBean.getQuoatationDiscription());
			quoatationUploadForm.setExpiryDate(MisUtility.convertDateToString(quoatationUploadBean.getExpiryDate()));			
		} 
		return mapping.findForward("display");
	}
	private void refreshQuoatationUploadForm(QuoatationUploadForm quoatationUploadForm) {
		quoatationUploadForm.setQuoatationUploadId(null);
		quoatationUploadForm.setQuoatationDiscription(null);
		quoatationUploadForm.setExpiryDate(null);
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
