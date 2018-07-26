package com.prwss.mis.WaterConnection.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

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
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.location.dao.LocationDao;
import com.prwss.mis.pdo.struts.PdoForm;

import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;

public class WaterConnectionAction extends DispatchAction {

	private MISSessionBean misSessionBean;
	private Logger log = Logger.getLogger(WaterConnectionAction.class);
	private LocationDao locationDao;

	private WaterConnectionBo waterConnectionBo;

	public WaterConnectionBo getWaterConnectionBo() {
		return waterConnectionBo;
	}

	public void setWaterConnectionBo(WaterConnectionBo waterConnectionBo) {
		this.waterConnectionBo = waterConnectionBo;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("In Water Connection ");
		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			// System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}
		setAttrib(request);

		System.out.println("In Water Connection ");
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		System.out.println("Mode 1111111111" + request.getParameter("d__mode"));
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationId@advUploadId");
		request.setAttribute("d__auto", "advUploadId");

	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String status = null;
		System.out.println("We are inside the Action");
		WaterConnectionForm water = (WaterConnectionForm) form;
		System.out.println(water.toString());
		setAttrib(request);

		try {

			// Send Data To Bean
			status = waterConnectionBo.saveWaterConnectionData(water);
			System.out.println(status);
			water.setDistrictIdurban(null);
			water.setRural(null);
			water.setUrban(null);
			water.setDistrictId(null);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("required.fields", status);
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);

		} catch (MISException e) {
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("WaterConnection.SaveError", "Water Connection ",
					"Application ID -->" + status);
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}

		return mapping.findForward("display");
	}

	// check Aadhaar
	public ActionForward chackAadhaar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {

		Boolean statusAadhaar = false;

		// List<DesignationBean> designations = null;
		try {

			request.getParameter("aadhaarNumber_");
			System.out.println(request.getParameter("aadhaarNumber_"));

			if (MisUtility.ifEmpty(request.getParameter("aadhaarNumber_"))) {

				statusAadhaar = waterConnectionBo.validateAadhaar(request.getParameter("aadhaarNumber_").trim());

			}

			if (!statusAadhaar) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("Aadhaar Number already exists!");

				PrintWriter out = MisUtility.getPrintWriter(response);
				out.print(buffer);
			} else {

				PrintWriter out = MisUtility.getPrintWriter(response);
				out.print("true");
			}

		} catch (Exception e) {
			log.debug(e.getLocalizedMessage());
		}
		return null;
	}

	// getOTP
	public ActionForward getOTP(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {

		Boolean statusOtp = false;
		String OTP = null;
		String MobileNumber = null;

		// List<DesignationBean> designations = null;
		try {

			OTP = request.getParameter("randomNumber");
			MobileNumber = request.getParameter("mobileNumber");

			if (MisUtility.ifEmpty(OTP) && MisUtility.ifEmpty(MobileNumber)) {

				// statusAadhaar =
				// waterConnectionBo.validateAadhaar(request.getParameter("aadhaarNumber_").trim());
				// Send OTP Function
				statusOtp = sendOtpToMobile(OTP, MobileNumber);
			}

			if (!statusOtp) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("Unable to Send the OTP . Please try again later .");

				PrintWriter out = MisUtility.getPrintWriter(response);
				out.print(buffer);
			} else {

				StringBuffer buffer = new StringBuffer();
				buffer.append("OTP Send successfully to the registered Mobile Number: " + MobileNumber);
				
				PrintWriter out = MisUtility.getPrintWriter(response);
				out.print(buffer);
			}

		} catch (Exception e) {
			log.debug(e.getLocalizedMessage());
		}
		return null;
	}

	private Boolean sendOtpToMobile(String oTP, String mobileNumber) {

		 String response = null;
		 
		 HttpURLConnection uc = null;
		try {
			
			String sendTo = mobileNumber;
			String message = MISConstants.MESSAGE_OTP + oTP;
			
			String requestUrl = MISConstants.URL_REQUEST + "username=" + MISConstants.USERNAME + "&password="
					+ MISConstants.PASSWORD + "&sender=" + MISConstants.SENDERID + "&sendto=" + sendTo + "&message=" + message;
			URL url = new URL(requestUrl);
			 uc = (HttpURLConnection) url.openConnection();
			System.out.println(uc.getResponseMessage());
			response = uc.getResponseMessage().toString().trim();
			
			if(response.equalsIgnoreCase("OK")){ 
				return true;
			}else{
				return false;
			}
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}finally {
			uc.disconnect();
		}
	}

}
