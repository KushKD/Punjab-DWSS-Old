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
import com.prwss.mis.tender.dao.CorrigendumUploadBean;
import com.prwss.mis.tender.dao.TenderDao;

public class CorrigendumUploadAction extends DispatchAction {
	private MISSessionBean misSessionBean;
	private Logger log = Logger.getLogger(CorrigendumUploadAction.class);
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

	public void setTenderDao(TenderDao TenderDao) {
		this.tenderDao = tenderDao;
	}
	private List <CorrigendumUploadBean> getCorrigendumUploaded(CorrigendumUploadForm corrigendumUploadForm, List<String> statusList) throws SQLException{
		List <CorrigendumUploadBean> corrigendumUploadBeans=new ArrayList<CorrigendumUploadBean>();
		Connection connection = null;
		Statement statement = null; 
		String locationId=corrigendumUploadForm.getLocationId();
		long corrigendumId=corrigendumUploadForm.getCorrigendumUploadId();
		String where=" 1=1 ";
		if(MisUtility.ifEmpty(locationId)) where+=" and location_id='"+locationId+"'";
		if(MisUtility.ifEmpty(corrigendumId)) where +=" and corrigendum_upload_id='"+corrigendumId+"'";
		if(!MisUtility.ifEmpty(statusList)){
			where += " and status in (";
			for (int i = 0; i < statusList.size(); i++) {
				where +="'"+statusList.get(i)+"',";
			}
			where=where.substring(0,where.length()-1);
			where += ")";
		}
		String sql="SELECT doc_id, district_id, location_id, corrigendum_description, corrigendum_for," +
				" status, ent_by, ent_date, auth_by, auth_date," +
				"freeze_by, freeze_date, corrigendum_file_name, " +
				"deploy_site_name, corrigendum_upload_id, corrigendum_file " +
				" FROM prwss_main.corrigendum_upload where "+where;	
		System.out.println("SQL: "+sql);
		try {
			connection = misJdcDaoImpl.getMISDataSource().getConnection();
			connection.setAutoCommit(false);
			statement= connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);			
			while (rs.next()) {
				CorrigendumUploadBean corrigendumUploadBean=new CorrigendumUploadBean();
				corrigendumUploadBean.setCorrigendumUploadId(rs.getLong("corrigendum_upload_id"));
				corrigendumUploadBean.setLocationId(rs.getString("location_id"));
				corrigendumUploadBean.setDistrictId(rs.getString("district_id"));
				corrigendumUploadBean.setDocId(rs.getString("doc_id"));
				corrigendumUploadBean.setCorrigendumFor(rs.getString("corrigendum_for"));
				corrigendumUploadBean.setCorrigendumDescription(rs.getString("corrigendum_description"));							
				corrigendumUploadBeans.add(corrigendumUploadBean);
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
		return corrigendumUploadBeans;
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
		CorrigendumUploadForm corrigendumUploadForm=(CorrigendumUploadForm)form;
		//System.out.println(statusList);
		List<CorrigendumUploadBean> corrigendumUploadBeans=null;
		corrigendumUploadBeans=getCorrigendumUploaded(corrigendumUploadForm, statusList);//corrigendumBO.findCorrigendumUploaded(corrigendumUploadForm, statusList);
		if(!MisUtility.ifEmpty(corrigendumUploadBeans)){
			request.setAttribute("corrigendumUploadList", corrigendumUploadBeans);
			ActionMessages errors = new ActionMessages();
			ActionMessage message = new ActionMessage("find.list");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveMessages(request, errors);
		}else{
			refreshCorrigendumUploadForm(corrigendumUploadForm);
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
		CorrigendumUploadForm corrigendumUploadForm=(CorrigendumUploadForm)form;
		String sql ="UPDATE prwss_main.corrigendum_upload SET status=?, ent_by=?, ent_date=now()" +
		"  WHERE location_id=? and corrigendum_upload_id=?";
		try {
			connection = misJdcDaoImpl.getMISDataSource().getConnection();
			connection.setAutoCommit(false);
			preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setString(1, MISConstants.MASTER_STATUS_DELETED);
			preparedStatement.setLong(2, misSessionBean.getEnteredBy());
			preparedStatement.setString(3, corrigendumUploadForm.getLocationId());
			preparedStatement.setLong(4, corrigendumUploadForm.getCorrigendumUploadId());
			int status=preparedStatement.executeUpdate();
			if (status>=1){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.delete", "Uploded Corrigendum for LocationID -->"+corrigendumUploadForm.getLocationId()+ " and Corrigendum No.--> "+ corrigendumUploadForm.getCorrigendumUploadId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.delete", "Uploded Corrigendum for LocationID -->"+corrigendumUploadForm.getLocationId()+ " and Corrigendum No.--> "+ corrigendumUploadForm.getCorrigendumUploadId());
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
		refreshCorrigendumUploadForm(corrigendumUploadForm);
		return mapping.findForward("display");
	}
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException,SQLException{
		this.setAttrib(request);
		if (request.getSession().getAttribute("misSessionBean") != null) {			
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
		} else {
			return mapping.findForward("login");
		}
		Connection connection = null;
		PreparedStatement preparedStatement= null;
		CorrigendumUploadForm corrigendumUploadForm=(CorrigendumUploadForm)form;
		String sql ="UPDATE prwss_main.corrigendum_upload SET doc_id=?, location_id=?, corrigendum_description=?, corrigendum_for=?," +
				" ent_by=?, ent_date=now()" +
				"  WHERE location_id=? and corrigendum_upload_id=?";
		System.out.println("Update: "+sql+
				"\n"+corrigendumUploadForm.getDocId()+
				"\n"+corrigendumUploadForm.getLocationId()+
				"\n"+corrigendumUploadForm.getCorrigendumDescription()+
				"\n"+corrigendumUploadForm.getCorrigendumFor()+
				"\n"+misSessionBean.getEnteredBy()+
				"\n"+corrigendumUploadForm.getCorrigendumUploadId());
		try {
			connection = misJdcDaoImpl.getMISDataSource().getConnection();
			connection.setAutoCommit(false);
			preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setString(1, corrigendumUploadForm.getDocId());
			preparedStatement.setString(2, corrigendumUploadForm.getLocationId());
			preparedStatement.setString(3, corrigendumUploadForm.getCorrigendumDescription());
			preparedStatement.setString(4, corrigendumUploadForm.getCorrigendumFor());
			preparedStatement.setLong(5, misSessionBean.getEnteredBy());
			preparedStatement.setString(6, corrigendumUploadForm.getLocationId());
			preparedStatement.setLong(7, corrigendumUploadForm.getCorrigendumUploadId());
			int status=preparedStatement.executeUpdate();
			if (status>=1){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("corrigendum.success.update", "Corrigendum No. "+ corrigendumUploadForm.getCorrigendumUploadId()+
						" has been modified for Location : "+corrigendumUploadForm.getLocationId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);

			} else {
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("error.update", "Uploded Corrigendum for LocationID -->"+corrigendumUploadForm.getLocationId()+ " and Corrigendum No.--> "+ corrigendumUploadForm.getCorrigendumUploadId());
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
		refreshCorrigendumUploadForm(corrigendumUploadForm);
		return mapping.findForward("display");
	}
	public ActionForward post(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
	//	System.out.println("Post");
		return mapping.findForward("display");
	}
	
	public ActionForward save(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		if (request.getSession().getAttribute("misSessionBean") != null) {
			misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
		} else {
			return mapping.findForward("login");
		}
		this.setAttrib(request);
		boolean status = false;
		CorrigendumUploadForm corrigendumUploadForm = (CorrigendumUploadForm)form;
		try {
				if(!MisUtility.ifEmpty(corrigendumUploadForm.getDocId())
						|| !MisUtility.ifEmpty(corrigendumUploadForm.getLocationId()) 
						|| !MisUtility.ifEmpty(corrigendumUploadForm.getCorrigendumFile().getFileSize())
						){
					throw new MISException(MISExceptionCodes.MIS004,"No Values Supplied to key values");
				}
				if(!corrigendumUploadForm.getCorrigendumFile().getContentType().equalsIgnoreCase("application/pdf")){
					throw new MISException(MISExceptionCodes.MIS003,"File Format Exception");
				}
			  LocationBean locationBean = new LocationBean();
			  locationBean.setLocationId(corrigendumUploadForm.getLocationId());
			  locationBean = locationDao.getLocation(locationBean);
			  CorrigendumUploadBean corrigendumUploadBean = new CorrigendumUploadBean();
			  corrigendumUploadBean.setDocId(corrigendumUploadForm.getDocId().trim()); 
			  corrigendumUploadBean.setLocationId(corrigendumUploadForm.getLocationId());
			  corrigendumUploadBean.setDistrictId(locationBean.getParentLocation());
			  //corrigendumUploadBean.setCorrigendumDescription(corrigendumUploadForm.getCorrigendumDescription().trim());
			  corrigendumUploadBean.setCorrigendumFor(corrigendumUploadForm.getCorrigendumFor().trim());
			  corrigendumUploadBean.setCorrigendumFileName(fileNameAppenderToPDF(corrigendumUploadForm.getCorrigendumFile().getFileName(), corrigendumUploadForm.getDocId()));
			  if(MisUtility.ifEmpty(corrigendumUploadForm.getCorrigendumFile().getFileData()))
				  corrigendumUploadBean.setCorrigendumFile(corrigendumUploadForm.getCorrigendumFile().getFileData());
			  status = tenderBO.uploadCorrigendum(corrigendumUploadBean, misSessionBean);
			  System.out.println("Status: "+status);
			  if (status){
					ActionMessages errors = new ActionMessages();
					ActionMessage message = new ActionMessage("corrigendum.upload.success.save");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveMessages(request, errors);
					refreshCorrigendumUploadForm(corrigendumUploadForm);
			  }else {
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("fatal.error.save", "Corrigendum Uploading Failed");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
			}
		} catch (MISException e) {
			if (MISExceptionCodes.MIS004.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(),e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("corrigendum.upload.key.field");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}if (MISExceptionCodes.MIS003.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(),e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("corrigendum.upload.file.type");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}if (MISExceptionCodes.MIS001.equals(e.getCode())) {
				log.error(e.getLocalizedMessage(),e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("corrigendum.upload.duplicate");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
			}
		}catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Saving of Corrigendum");
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
		//System.out.println("In Corrigendum Upload");
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");		
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky","locationId@corrigendumUploadId");
		request.setAttribute("d__auto", "corrigendumUploadId");

	}
	public ActionForward populate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		String locationId = request.getParameter("locationId");
		Long corrigendumUploadId = Long.parseLong(request.getParameter("corrigendumUploadId"));
		CorrigendumUploadForm corrigendumUploadForm=(CorrigendumUploadForm)form;
		corrigendumUploadForm.setLocationId(locationId);
		corrigendumUploadForm.setCorrigendumUploadId(corrigendumUploadId);
		List<CorrigendumUploadBean> corrigendumUploadBeans=null;
		corrigendumUploadBeans=getCorrigendumUploaded(corrigendumUploadForm, null);
		for (int i = 0; i < corrigendumUploadBeans.size(); i++) {
			CorrigendumUploadBean corrigendumUploadBean=(CorrigendumUploadBean)corrigendumUploadBeans.get(i);
			corrigendumUploadForm.setLocationId(corrigendumUploadBean.getLocationId());
			corrigendumUploadForm.setCorrigendumUploadId(corrigendumUploadBean.getCorrigendumUploadId());
			corrigendumUploadForm.setCorrigendumDescription(corrigendumUploadBean.getCorrigendumDescription());
			corrigendumUploadForm.setCorrigendumFor(corrigendumUploadBean.getCorrigendumFor());
			corrigendumUploadForm.setDocId(corrigendumUploadBean.getDocId());
		} 
		return mapping.findForward("display");
	}
	private void refreshCorrigendumUploadForm(CorrigendumUploadForm corrigendumUploadForm) {
		corrigendumUploadForm.setCorrigendumUploadId(null);
		corrigendumUploadForm.setCorrigendumFile(null);
		corrigendumUploadForm.setLocationId(null);
		corrigendumUploadForm.setCorrigendumDescription(null);
		corrigendumUploadForm.setDocId(null);
		corrigendumUploadForm.setCorrigendumFor(null);
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
