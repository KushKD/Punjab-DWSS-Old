package com.prwss.mis.WaterConnection.struts;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.prwss.mis.admin.UserAdminBean;
import com.prwss.mis.admin.struts.UserMasterForm;
import com.prwss.mis.common.MISJdcDaoImpl;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MISJasperUtil;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.login.dao.LoginUserBean;
import com.prwss.mis.masters.location.dao.LocationDao;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;
import net.sf.jasperreports.engine.util.JRSwapFile;

public class WaterConnectionDepartmentAction extends DispatchAction {

	private MISSessionBean misSessionBean;
	private Logger log = Logger.getLogger(WaterConnectionAction.class);
	private LocationDao locationDao;
	
Statement stmt = null;
DataSource db = null;
Boolean status = false;

private MISJdcDaoImpl misJdcDaoImpl;


	public MISJdcDaoImpl getMisJdcDaoImpl() {
	return misJdcDaoImpl;
}

public void setMisJdcDaoImpl(MISJdcDaoImpl misJdcDaoImpl) {
	this.misJdcDaoImpl = misJdcDaoImpl;
}

	public LocationDao getLocationDao() {
		return locationDao;
	}

	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}
	

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("In Water Connection Department ");
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
		System.out.println("In Water Connection ");
		return mapping.findForward("display");
	}  
	
	private void setAttrib(HttpServletRequest request) {
		System.out.println("Mode 1111111111"+request.getParameter("d__mode"));
		request.setAttribute("Rpt", "ent");		
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky","@appId@changeStatus@comments");
		request.setAttribute("d__auto", "advUploadId");
		request.setAttribute("d__auto", "appID");

	}
	
	

	

}