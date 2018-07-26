package com.prwss.mis.pmm.alert.struts;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.activity.dao.ActivityBean;
import com.prwss.mis.masters.activity.struts.ActivityForm;
import com.prwss.mis.pmm.alert.dao.AlertChecklistBean;
import com.prwss.mis.pmm.alert.dao.AlertChecklistDao;

public class AlertChecklistAction extends DispatchAction{
	private MISSessionBean misSessionBean;
	private AlertChecklistDao alertChecklistDao;
	
	
	public ActionForward save(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)	throws MISException {
		this.setAttrib(request);
		request.setAttribute("level2","true");
		AlertChecklistForm alertChecklistForm= (AlertChecklistForm)form;
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
					setMisSessionBean((MISSessionBean) request.getSession().getAttribute("misSessionBean"));
			} else {
				return mapping.findForward("login");
			}			
			AlertChecklistBean alertChecklistBean=new AlertChecklistBean();
			alertChecklistBean.setLocationId(alertChecklistForm.getLocationId());
			alertChecklistBean.settMonth(alertChecklistForm.gettMonth());
			alertChecklistBean.settYear(alertChecklistForm.gettYear());
			alertChecklistBean.setAdminApproval(alertChecklistForm.isAdminApproval());
			alertChecklistBean.setSchemeCommissioning(alertChecklistForm.isSchemeCommissioning());
			alertChecklistBean.setWaterConnection(alertChecklistForm.isWaterConnection());
			alertChecklistBean.setOperationSustainability(alertChecklistForm.isOperationSustainability());
			alertChecklistBean.setHousehold(alertChecklistForm.isHousehold());
			alertChecklistBean.setIecTraining(alertChecklistForm.isIecTraining());
			alertChecklistBean.setBeneficiayShare(alertChecklistForm.isBeneficiayShare());
			alertChecklistBean.setSpmcPaymentVoucher(alertChecklistForm.isSpmcPaymentVoucher());
			alertChecklistBean.setDpmcPaymentVoucher(alertChecklistForm.isDpmcPaymentVoucher());
			alertChecklistBean.setGpwscRegisterEntry(alertChecklistForm.isGpwscRegisterEntry());
			alertChecklistBean.setUpdationProcurementPlan(alertChecklistForm.isUpdationProcurementPlan());
			alertChecklistBean.setMailStatus("WAIT");
			status = alertChecklistDao.saveOrUpdate(alertChecklistBean);
			if (status){
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.save", "");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
				refreshAlertChecklistForm(alertChecklistForm);

			} else {
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("error.save", "");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save","Village Details Saving");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			
		}
		request.setAttribute("d__mode",null);
		return mapping.findForward("cScreen");
	}
	public ActionForward getStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AlertChecklistForm alertChecklistForm= (AlertChecklistForm)form;
		List<AlertChecklistBean> alertChecklistBeans=null;
		System.out.println("---------------------getStatus---------------------"+request.getParameter("locationId"));
		if(MisUtility.ifEmpty(request.getParameter("locationId"))){
			String locationId=request.getParameter("locationId");
			String[] monthName = {"January", "February",
					  "March", "April", "May", "June", "July",
					  "August", "September", "October", "November",
					  "December"
					  };
			String output=null;
			Calendar cal = Calendar.getInstance();
			int date=cal.get(Calendar.DATE);
	        int month = cal.get(Calendar.MONTH);
	        int year = cal.get(Calendar.YEAR);
	        if(date<10){
	        	if(month!=0){
	        		month=month-1;
	        	}else{
	        		month=11;
	        	}
	        }
	        
	        System.out.println(locationId+":"+monthName[month]+":"+year);
	        alertChecklistBeans=alertChecklistDao.findAlertChecklistBean(locationId, monthName[month], year,null);
	        if(!MisUtility.ifEmpty(alertChecklistBeans)){
	        	try{
		        	AlertChecklistBean alertChecklistBean=alertChecklistBeans.get(0);
		        	System.out.println("alertChecklistBean------------------>:"+alertChecklistBean.toString());
		        	
		        	System.out.println(alertChecklistBean.getLocationId());
		        	System.out.println(alertChecklistBean.gettMonth());
		        	System.out.println(alertChecklistBean.gettYear());
		        	System.out.println(alertChecklistBean.isAdminApproval());
		        	System.out.println(alertChecklistBean.isSchemeCommissioning());
		        	System.out.println(alertChecklistBean.isWaterConnection());
		        	System.out.println(alertChecklistBean.isHousehold());
		        	System.out.println(alertChecklistBean.isOperationSustainability());
		        	System.out.println(alertChecklistBean.isIecTraining());
		        	System.out.println(alertChecklistBean.isBeneficiayShare());
		        	System.out.println(alertChecklistBean.isSpmcPaymentVoucher() );
		        	System.out.println(alertChecklistBean.isDpmcPaymentVoucher());
		        	System.out.println(alertChecklistBean.isGpwscRegisterEntry());
		        	System.out.println(alertChecklistBean.isUpdationProcurementPlan());
		        	/*request.setAttribute("locationId",alertChecklistBean.getLocationId());
		        	request.setAttribute("tMonth",alertChecklistBean.gettMonth());
		        	request.setAttribute("tYear",alertChecklistBean.gettYear());
		        	request.setAttribute("adminApproval",alertChecklistBean.isAdminApproval());
		        	request.setAttribute("schemeCommissioning",alertChecklistBean.isSchemeCommissioning());
		        	request.setAttribute("waterConnection",alertChecklistBean.isWaterConnection());
		        	request.setAttribute("household",alertChecklistBean.isHousehold());
		        	request.setAttribute("operationSustainability",alertChecklistBean.isOperationSustainability());*/
		        	output="locationId@!"+alertChecklistBean.getLocationId()+"~"+
		        	"tMonth@!"+alertChecklistBean.gettMonth()+"~"+
		        	"tYear@!"+alertChecklistBean.gettYear()+"~"+
		        	"adminApproval_"+alertChecklistBean.isAdminApproval()+"@!"+alertChecklistBean.isAdminApproval()+"~"+
		        	"schemeCommissioning_"+alertChecklistBean.isSchemeCommissioning()+"@!"+alertChecklistBean.isSchemeCommissioning()+"~"+
		        	"waterConnection_"+alertChecklistBean.isWaterConnection()+"@!"+alertChecklistBean.isWaterConnection()+"~"+
		        	"household_"+alertChecklistBean.isHousehold()+"@!"+alertChecklistBean.isHousehold()+"~"+
		        	"operationSustainability_"+alertChecklistBean.isOperationSustainability()+"@!"+alertChecklistBean.isOperationSustainability()+"~"+
		        	"iecTraining_"+alertChecklistBean.isIecTraining()+"@!"+alertChecklistBean.isIecTraining()+"~"+
		        	"beneficiayShare_"+alertChecklistBean.isBeneficiayShare()+"@!"+alertChecklistBean.isBeneficiayShare()+"~"+
		        	"spmcPaymentVoucher_"+alertChecklistBean.isSpmcPaymentVoucher()+"@!"+alertChecklistBean.isSpmcPaymentVoucher()+"~"+
		        	"dpmcPaymentVoucher_"+alertChecklistBean.isDpmcPaymentVoucher()+"@!"+alertChecklistBean.isDpmcPaymentVoucher()+"~"+
		        	"gpwscRegisterEntry_"+alertChecklistBean.isGpwscRegisterEntry()+"@!"+alertChecklistBean.isGpwscRegisterEntry()+"~"+
		        	"updationProcurementPlan_"+alertChecklistBean.isUpdationProcurementPlan()+"@!"+alertChecklistBean.isUpdationProcurementPlan();
		        	
		        	
		        	
	        	}catch(Exception e){
	        		e.printStackTrace();
	        	}
	        	
	        }else{
	        	output="locationId@!"+locationId+"~"+
	        	"tMonth@!"+monthName[month]+"~"+
	        	"tYear@!"+year;
	        }
	        System.out.println("output:"+output);
	        PrintWriter out = response.getWriter();  
        	out.println(output);  
        	out.flush();  
		}
		
		
		return null;
	}
	
	private void setAttrib(HttpServletRequest request){
		request.setAttribute("Rpt","ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode","ent_add");
		request.setAttribute("d__ky", "locationId@villageId@blockId");
		
	}
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,	HttpServletRequest request, HttpServletResponse response)	throws MISException {
		this.setAttrib(request);
		try {
			System.out.println("Unspecified------------------------------------");
			AlertChecklistForm alertChecklistForm = (AlertChecklistForm)form;
			refreshAlertChecklistForm(alertChecklistForm);
		
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return mapping.findForward("display");
	}
	public void setAlertChecklistDao(AlertChecklistDao alertChecklistDao) {
		this.alertChecklistDao = alertChecklistDao;
	}
	public void setMisSessionBean(MISSessionBean misSessionBean) {
		this.misSessionBean = misSessionBean;
	}
	private void refreshAlertChecklistForm(AlertChecklistForm alertChecklistForm){
		alertChecklistForm.setLocationId(null);
		alertChecklistForm.settMonth(null);
		alertChecklistForm.settYear(0);
		alertChecklistForm.setAdminApproval(false);
		alertChecklistForm.setBeneficiayShare(false);
		alertChecklistForm.setDpmcPaymentVoucher(false);
		alertChecklistForm.setGpwscRegisterEntry(false);
		alertChecklistForm.setHousehold(false);
		alertChecklistForm.setIecTraining(false);
		alertChecklistForm.setOperationSustainability(false);	
		alertChecklistForm.setSchemeCommissioning(false);
		alertChecklistForm.setSpmcPaymentVoucher(false);
		alertChecklistForm.setUpdationProcurementPlan(false);
		alertChecklistForm.setWaterConnection(false);
	}
}
