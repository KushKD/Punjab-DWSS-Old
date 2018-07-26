package com.prwss.mis.WaterConnection.struts;

import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.prwss.mis.common.MISJdcDaoImpl;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.util.MISJasperUtil;
import com.prwss.mis.masters.location.dao.LocationDao;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;
import net.sf.jasperreports.engine.util.JRSwapFile;

public class WaterConnectionResultAction extends DispatchAction {

	private MISSessionBean misSessionBean;
	private Logger log = Logger.getLogger(WaterConnectionAction.class);
	private LocationDao locationDao;
	
	private WaterConnectionDao waterConnectionDao;
	
	

	public WaterConnectionDao getWaterConnectionDao() {
		return waterConnectionDao;
	}

	public void setWaterConnectionDao(WaterConnectionDao waterConnectionDao) {
		this.waterConnectionDao = waterConnectionDao;
	}




	private List<WaterConnectionBean> waterConnectionBean = null;

	public LocationDao getLocationDao() {
		return locationDao;
	}

	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}
	
	
	
	
	private MISJasperUtil misJasperUtil;

	private MISJdcDaoImpl misJdcDaoImpl;

	public MISJdcDaoImpl getMisJdcDaoImpl() {
		return misJdcDaoImpl;
	}

	public void setMisJdcDaoImpl(MISJdcDaoImpl misJdcDaoImpl) {
		this.misJdcDaoImpl = misJdcDaoImpl;
	}

	@SuppressWarnings("rawtypes")
	private Map parameters = null;

	public void setMisJasperUtil(MISJasperUtil misJasperUtil) {
		this.misJasperUtil = misJasperUtil;
	}

	@SuppressWarnings("rawtypes")
	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}

	@SuppressWarnings("rawtypes")
	public Map getParameters() {
		return parameters;
	}

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("In Water Connection Result ");
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{

				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			// System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		setAttrib(request);
		System.out.println("In Water Connection Result ");

		try {
			String Name = misSessionBean.getUserId1();
			System.out.println(Name);
			// GET all the Users From
			// getApplications(misSessionBean.getEnteredBy());
			waterConnectionBean = locationDao.getApplications(Name);
			System.out.println(waterConnectionBean.toString());
			request.setAttribute("waterConnectionList", waterConnectionBean);

		} catch (Exception ex) {

		}

		return mapping.findForward("display");
	}
	
	
	 public  ActionForward getReportbyStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("In Water Connection Result Change Staus ");
		setAttrib(request);
		System.out.println("In Water Connection Result ");

		try {
			WaterConnectionResultForm waterConnectionResultForm=(WaterConnectionResultForm)form;
			System.out.println(waterConnectionResultForm.toString());
			String Name = misSessionBean.getUserId1();
			System.out.println(Name);
			// GET all the Users From
			// getApplications(misSessionBean.getEnteredBy());
			waterConnectionBean = waterConnectionDao.getApplicationschangedStatus(Name,waterConnectionResultForm.getChangeStatus());
			System.out.println(waterConnectionBean.toString());
			request.setAttribute("waterConnectionList", waterConnectionBean);

		} catch (Exception ex) { ex.printStackTrace();

		}

		return mapping.findForward("display");
	}
	
	 
	
	private void setAttrib(HttpServletRequest request) {
		System.out.println("Mode 1111111111" + request.getParameter("d__mode"));
		System.out.println("USER ID:===== Employee Name" + misSessionBean.getEmployeeName());
		System.out.println("USER ID Entered By:===== " + misSessionBean.getEnteredBy());
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		// request.setAttribute("d__ky","@appId");
		  //request.setAttribute("d__auto", "advUploadId");
		 //@changeStatus@comments

	}

	public void getReport(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String jasperFile = null;
		String fileTitle = null;
		System.out.println("-------------IN file PDF----------------");
		WaterConnectionResultForm waterConnectionForm = (WaterConnectionResultForm) form;
		//jasperFile = waterConnectionForm.getFileName();
		//fileTitle = waterConnectionForm.getFileTitle(); //appId  request.getParameter("appId")
		if(request.getParameter("connType").equalsIgnoreCase("1")){
			//Get the Urban Report
			jasperFile = waterConnectionForm.getFileNameUrban();
			fileTitle = waterConnectionForm.getFileTitleUrban(); 
			
		}else{
			//Get the Rural Report
			jasperFile = waterConnectionForm.getFileName();
			fileTitle = waterConnectionForm.getFileTitle(); //appId  request.getParameter("appId")
			
					
		}
		waterConnectionForm.setAppId(request.getParameter("appId")); 
		setWhere(waterConnectionForm, request); 
		JRSwapFileVirtualizer virtualizer = null;
		virtualizer = new JRSwapFileVirtualizer(10, new JRSwapFile("/usr", 90000, 15000), false);
		parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
		misJasperUtil.exportToPDF(jasperFile, fileTitle, parameters, request, response);  

	}

	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	private void setWhere(WaterConnectionResultForm waterConnectionForm, HttpServletRequest request) {
		// TODO Auto-generated method stub  
		parameters = new HashMap();
		
		System.out.println("-------1-------------"+waterConnectionForm.getAppId());
		// parameters.put("applicationId",
		// waterConnectionForm.getAppId().toString().trim());
		// System.out.println(waterConnectionForm.getAppId().toString().trim());
		parameters.put("application", waterConnectionForm.getAppId());
		System.out.println(waterConnectionForm.getAppId());

	}

}
