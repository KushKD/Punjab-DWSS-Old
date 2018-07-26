package com.prwss.mis.WaterConnection.struts;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

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

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISJasperUtil;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.location.dao.LocationDao;
import com.prwss.mis.pmm.report.struts.EstimatesAwardContractsReportForm;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;
import net.sf.jasperreports.engine.util.JRSwapFile;

public class WaterConnectionsuccessAction extends DispatchAction {

	private MISSessionBean misSessionBean;
	private Logger log = Logger.getLogger(WaterConnectionAction.class);
	private LocationDao locationDao;
	private MISJasperUtil misJasperUtil;
	
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
	
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("In Water Connection ");
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
		System.out.println("In Water Connection Success ");
		return mapping.findForward("display");
	}
	
	private void setAttrib(HttpServletRequest request) {
		System.out.println("Mode 1111111111"+request.getParameter("d__mode"));
		request.setAttribute("Rpt", "ent");		
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky","locationId@advUploadId");
		request.setAttribute("d__auto", "advUploadId");

	}
	
	public void getReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String jasperFile = null;
		String fileTitle = null;
		System.out.println("-------------IN file PDF----------------");
		WaterConnectionsuccessForm waterConnectionForm=(WaterConnectionsuccessForm)form;
		
		if(waterConnectionForm.getAppName().trim().contains("B")){
			//Rural Report
			 jasperFile= waterConnectionForm.getFileName();
			 fileTitle= waterConnectionForm.getFileTitle();
		}else{
			//Urban Report
			jasperFile= waterConnectionForm.getFileNameUrban();
			 fileTitle= waterConnectionForm.getFileTitleUrban();
		}
		
		setWhere(waterConnectionForm,request);
		JRSwapFileVirtualizer virtualizer = null; 
		virtualizer = new JRSwapFileVirtualizer(10, new JRSwapFile("/usr", 90000, 15000), false);
		parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
		misJasperUtil.exportToPDF(jasperFile,fileTitle, parameters, request, response);	
		
		
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	private void setWhere(WaterConnectionsuccessForm waterConnectionForm, HttpServletRequest request) {
		// TODO Auto-generated method stub
		parameters = new HashMap();
		//parameters.put("applicationId", waterConnectionForm.getAppId().toString().trim());
		//System.out.println(waterConnectionForm.getAppId().toString().trim());
		parameters.put("application", waterConnectionForm.getAppName());
		System.out.println(waterConnectionForm.getAppName());
		
	}	
	
	
	
	
	
}
