package com.prwss.mis.WaterConnection.struts;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
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
import org.hibernate.mapping.Array;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prwss.mis.common.MISJdcDaoImpl;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MISJasperUtil;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.location.dao.LocationDao;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;
import net.sf.jasperreports.engine.util.JRSwapFile;

public class WaterQualityStatusAction extends DispatchAction {

	private MISSessionBean misSessionBean;
	private Logger log = Logger.getLogger(WaterConnectionAction.class);
	private LocationDao locationDao;
	private MISJdcDaoImpl misJdcDaoImpl;
	private MISJasperUtil misJasperUtil;
	@SuppressWarnings("rawtypes")
	private Map parameters;
	private WaterQualityStatusDao waterQualityStatusDao;

	public MISJdcDaoImpl getMisJdcDaoImpl() {
		return misJdcDaoImpl;
	}

	public void setMisJdcDaoImpl(MISJdcDaoImpl misJdcDaoImpl) {
		this.misJdcDaoImpl = misJdcDaoImpl;
	}

	public MISJasperUtil getMisJasperUtil() {
		return misJasperUtil;
	}

	public void setMisJasperUtil(MISJasperUtil misJasperUtil) {
		this.misJasperUtil = misJasperUtil;
	}

	public Map getParameters() {
		return parameters;
	}

	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}

	public WaterQualityStatusDao getWaterQualityStatusDao() {
		return waterQualityStatusDao;
	}

	public void setWaterQualityStatusDao(WaterQualityStatusDao waterQualityStatusDao) {
		this.waterQualityStatusDao = waterQualityStatusDao;
	}

	public MISSessionBean getMisSessionBean() {
		return misSessionBean;
	}

	public void setMisSessionBean(MISSessionBean misSessionBean) {
		this.misSessionBean = misSessionBean;
	}

	public LocationDao getLocationDao() {
		return locationDao;
	}

	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
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

	public ActionForward finds(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("----------1------------");
		WaterQualityStatusForm waterQualityStatusForm = null;
		List<WaterQualityStatusBean> waterQualityStatusBeans = null;
		String waterStatus = null;
		try {

			setAttrib(request);
			System.out.println("inside---->");
			waterQualityStatusForm = (WaterQualityStatusForm) form;

			System.out.println(waterQualityStatusForm.toString());
			waterQualityStatusBeans = waterQualityStatusDao
					.getWaterQualityStatus(waterQualityStatusForm.getVillageId());

			if (!MisUtility.ifEmpty(waterQualityStatusBeans)) {
				for (WaterQualityStatusBean bean : waterQualityStatusBeans) {
					waterStatus = bean.getWater_staus();
				}
				if (MisUtility.ifEmpty(waterStatus)) {
					request.setAttribute("waterStatus", waterStatus);
					request.setAttribute("districtId", waterQualityStatusForm.getDistrictId());
					request.setAttribute("blockId", waterQualityStatusForm.getBlockId());
					request.setAttribute("villageId", waterQualityStatusForm.getVillageId());

				}
			} else {
				request.setAttribute("errorMessage", "error");
				/*
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("No.record", "Water Quality Data not available for selected village",
						"selected village");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);*/
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ",
					"the Transaction");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
		}
		return mapping.findForward("display");
	}

	public ActionForward findWaterQuality(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("----------1------findWaterQuality------");
		String villageId = null;
		List<WaterQualityStatusBean> waterQualityStatusBeans = null;
		List<WaterQualityStatusBean> waterQualityStatusBeanss = null;
		try {

			villageId = request.getParameter("villageId");
			System.out.println(villageId);
			waterQualityStatusBeans = waterQualityStatusDao.getWaterQualityStatus(villageId);
			if (!MisUtility.ifEmpty(waterQualityStatusBeans)) {
				waterQualityStatusBeanss = new ArrayList<WaterQualityStatusBean>();
				for (WaterQualityStatusBean bean : waterQualityStatusBeans) {

					if (MisUtility.ifEmpty(bean.getAlkalinity_val())) {
						if (!(bean.getAlkalinity_val().equalsIgnoreCase("ND")
								|| bean.getAlkalinity_val().equalsIgnoreCase("BDL")
								|| bean.getAlkalinity_val().equalsIgnoreCase("NT"))) {
							bean.setAlkalinity_val(String.format("%.4f", Double.parseDouble(bean.getAlkalinity_val())));
						}
					}
					
					if (MisUtility.ifEmpty(bean.getAluminium_val())) {
					if (!(bean.getAluminium_val().equalsIgnoreCase("ND")
							|| bean.getAluminium_val().equalsIgnoreCase("BDL")
							|| bean.getAluminium_val().equalsIgnoreCase("NT"))) {
						bean.setAluminium_val(String.format("%.4f", Double.parseDouble(bean.getAluminium_val())));
					}
					}

					if (MisUtility.ifEmpty(bean.getLead_val())) {
						if (!(bean.getLead_val().equalsIgnoreCase("ND")
								|| bean.getLead_val().equalsIgnoreCase("BDL")
								|| bean.getLead_val().equalsIgnoreCase("NT"))) {
							bean.setLead_val(String.format("%.4f", Double.parseDouble(bean.getLead_val())));
						}
					}

					if (MisUtility.ifEmpty(bean.getSelenium_val())) {
					if (!(bean.getSelenium_val().equalsIgnoreCase("ND")
							|| bean.getSelenium_val().equalsIgnoreCase("BDL")
							|| bean.getSelenium_val().equalsIgnoreCase("NT"))) {
						bean.setSelenium_val(String.format("%.4f", Double.parseDouble(bean.getSelenium_val())));
					}
					}
					if (MisUtility.ifEmpty(bean.getChromium_val())) {
					if (!(bean.getChromium_val().equalsIgnoreCase("ND")
							|| bean.getChromium_val().equalsIgnoreCase("BDL")
							|| bean.getChromium_val().equalsIgnoreCase("NT"))) {
						bean.setChromium_val(String.format("%.4f", Double.parseDouble(bean.getChromium_val())));
					}
					}
					if (MisUtility.ifEmpty(bean.getMercury_val())) {
					if (!(bean.getMercury_val().equalsIgnoreCase("ND")
							|| bean.getMercury_val().equalsIgnoreCase("BDL")
							|| bean.getMercury_val().equalsIgnoreCase("NT"))) {
						bean.setMercury_val(String.format("%.4f", Double.parseDouble(bean.getMercury_val())));
					}
					}
					if (MisUtility.ifEmpty(bean.getArsenic_val())) {
					if (!(bean.getArsenic_val().equalsIgnoreCase("ND")
							|| bean.getArsenic_val().equalsIgnoreCase("BDL")
							|| bean.getArsenic_val().equalsIgnoreCase("NT"))) {
						bean.setArsenic_val(String.format("%.4f", Double.parseDouble(bean.getArsenic_val())));
					}
					}
					if (MisUtility.ifEmpty(bean.getCadmium_val())) {
					if (!(bean.getCadmium_val().equalsIgnoreCase("ND")
							|| bean.getCadmium_val().equalsIgnoreCase("BDL")
							|| bean.getCadmium_val().equalsIgnoreCase("NT"))) {
						bean.setCadmium_val(String.format("%.4f", Double.parseDouble(bean.getCadmium_val())));
					}
					}
					if (MisUtility.ifEmpty(bean.getNickel_val())) {
					if (!(bean.getNickel_val().equalsIgnoreCase("ND")
							|| bean.getNickel_val().equalsIgnoreCase("BDL")
							|| bean.getNickel_val().equalsIgnoreCase("NT"))) {
						bean.setNickel_val(String.format("%.4f", Double.parseDouble(bean.getNickel_val())));
					}
					}
					if (MisUtility.ifEmpty(bean.getTds_val())) {
					if (!(bean.getTds_val().equalsIgnoreCase("ND")
							|| bean.getTds_val().equalsIgnoreCase("BDL")
							|| bean.getTds_val().equalsIgnoreCase("NT"))) {
						System.out.println("inside");
						bean.setTds_val(String.format("%.4f", Double.parseDouble(bean.getTds_val())));
					}
					}
					if (MisUtility.ifEmpty(bean.getIron_val())) {
					if (!(bean.getIron_val().equalsIgnoreCase("ND")
							|| bean.getIron_val().equalsIgnoreCase("BDL")
							|| bean.getIron_val().equalsIgnoreCase("NT"))) {
						bean.setIron_val(String.format("%.4f", Double.parseDouble(bean.getIron_val())));
					}
					}
					if (MisUtility.ifEmpty(bean.getCopper_val())) {
					if (!(bean.getCopper_val().equalsIgnoreCase("ND")
							|| bean.getCopper_val().equalsIgnoreCase("BDL")
							|| bean.getCopper_val().equalsIgnoreCase("NT"))) {
						bean.setCopper_val(String.format("%.4f", Double.parseDouble(bean.getCopper_val())));
					}
					}
					if (MisUtility.ifEmpty(bean.getUranium_val())) {
					if (!(bean.getUranium_val().equalsIgnoreCase("ND")
							|| bean.getUranium_val().equalsIgnoreCase("BDL")
							|| bean.getUranium_val().equalsIgnoreCase("NT"))) {
						bean.setUranium_val(String.format("%.4f", Double.parseDouble(bean.getUranium_val())));
					}
					}
					if (MisUtility.ifEmpty(bean.getFluoride_val())) {
					if (!(bean.getFluoride_val().equalsIgnoreCase("ND")
							|| bean.getFluoride_val().equalsIgnoreCase("BDL")
							|| bean.getFluoride_val().equalsIgnoreCase("NT"))) {
						bean.setFluoride_val(String.format("%.4f", Double.parseDouble(bean.getFluoride_val())));
					}
					}
					if (MisUtility.ifEmpty(bean.getChloride_val())) {
					if (!(bean.getChloride_val().equalsIgnoreCase("ND")
							|| bean.getChloride_val().equalsIgnoreCase("BDL")
							|| bean.getChloride_val().equalsIgnoreCase("NT"))) {
						bean.setChloride_val(String.format("%.4f", Double.parseDouble(bean.getChloride_val())));
					}
					}
					if (MisUtility.ifEmpty(bean.getNitrate_val())) {
					if (!(bean.getNitrate_val().equalsIgnoreCase("ND")
							|| bean.getNitrate_val().equalsIgnoreCase("BDL")
							|| bean.getNitrate_val().equalsIgnoreCase("NT"))) {
						bean.setNitrate_val(String.format("%.4f", Double.parseDouble(bean.getNitrate_val())));
					}
					}
					if (MisUtility.ifEmpty(bean.getSulphate_val())) {
					if (!(bean.getSulphate_val().equalsIgnoreCase("ND")
							|| bean.getSulphate_val().equalsIgnoreCase("BDL")
							|| bean.getSulphate_val().equalsIgnoreCase("NT"))) {
						bean.setSulphate_val(String.format("%.4f", Double.parseDouble(bean.getSulphate_val())));
					}
					}
					if (MisUtility.ifEmpty(bean.getCalcium_val())) {
					if (!(bean.getCalcium_val().equalsIgnoreCase("ND")
							|| bean.getCalcium_val().equalsIgnoreCase("BDL")
							|| bean.getCalcium_val().equalsIgnoreCase("NT"))) {
						bean.setCalcium_val(String.format("%.4f", Double.parseDouble(bean.getCalcium_val())));
					}
					}
					if (MisUtility.ifEmpty(bean.getMagnesium_val())) {
					if (!(bean.getMagnesium_val().equalsIgnoreCase("ND")
							|| bean.getMagnesium_val().equalsIgnoreCase("BDL")
							|| bean.getMagnesium_val().equalsIgnoreCase("NT"))) {
						bean.setMagnesium_val(String.format("%.4f", Double.parseDouble(bean.getMagnesium_val())));
					}
					}

					if (MisUtility.ifEmpty(bean.getSodium_val())) {
					if (!(bean.getSodium_val().equalsIgnoreCase("ND")
							|| bean.getSodium_val().equalsIgnoreCase("BDL")
							|| bean.getSodium_val().equalsIgnoreCase("NT"))) {
						bean.setSodium_val(String.format("%.4f", Double.parseDouble(bean.getSodium_val())));
					}
					}

					if (MisUtility.ifEmpty(bean.getCalcium_val())) {
					if (!(bean.getCalcium_val().equalsIgnoreCase("ND")
							|| bean.getCalcium_val().equalsIgnoreCase("BDL")
							|| bean.getCalcium_val().equalsIgnoreCase("NT"))) {
						bean.setCalcium_val(String.format("%.4f", Double.parseDouble(bean.getCalcium_val())));
					}
					}

					if (MisUtility.ifEmpty(bean.getPotasium_val())) {
					if (!(bean.getPotasium_val().equalsIgnoreCase("ND")
							|| bean.getPotasium_val().equalsIgnoreCase("BDL")
							|| bean.getPotasium_val().equalsIgnoreCase("NT"))) {
						bean.setPotasium_val(String.format("%.4f", Double.parseDouble(bean.getPotasium_val())));
					}
					}
					
					if (MisUtility.ifEmpty(bean.getColor_val())) {
					if (!(bean.getColor_val().equalsIgnoreCase("ND")
							|| bean.getColor_val().equalsIgnoreCase("BDL")
							|| bean.getColor_val().equalsIgnoreCase("NT"))) {
						bean.setColor_val(String.format("%.4f", Double.parseDouble(bean.getColor_val())));
					}
					}
					
					if (MisUtility.ifEmpty(bean.getTurbidity_val())) {
					if (!(bean.getTurbidity_val().equalsIgnoreCase("ND")
							|| bean.getTurbidity_val().equalsIgnoreCase("BDL")
							|| bean.getTurbidity_val().equalsIgnoreCase("NT"))) {
						bean.setTurbidity_val(String.format("%.4f", Double.parseDouble(bean.getTurbidity_val())));
					}
					}
					
					if (MisUtility.ifEmpty(bean.getPh_val())) {
					if (!(bean.getPh_val().equalsIgnoreCase("ND")
							|| bean.getPh_val().equalsIgnoreCase("BDL")
							|| bean.getPh_val().equalsIgnoreCase("NT"))) {
						bean.setPh_val(String.format("%.4f", Double.parseDouble(bean.getPh_val())));
					}
					}
					
					if (MisUtility.ifEmpty(bean.getHardness_val())) {
					if (!(bean.getHardness_val().equalsIgnoreCase("ND")
							|| bean.getHardness_val().equalsIgnoreCase("BDL")
							|| bean.getHardness_val().equalsIgnoreCase("NT"))) {
						bean.setHardness_val(String.format("%.4f", Double.parseDouble(bean.getHardness_val())));
					}
					}

					if (MisUtility.ifEmpty(bean.getTaste_val())) {
					if (!(bean.getTaste_val().equalsIgnoreCase("ND")
							|| bean.getTaste_val().equalsIgnoreCase("BDL")
							|| bean.getTaste_val().equalsIgnoreCase("NT"))) {
						bean.setTaste_val(String.format("%.4f", Double.parseDouble(bean.getTaste_val())));
					}
					}

					if (MisUtility.ifEmpty(bean.getOdor_val())) {
					if (!(bean.getOdor_val().equalsIgnoreCase("ND")
							|| bean.getOdor_val().equalsIgnoreCase("BDL")
							|| bean.getOdor_val().equalsIgnoreCase("NT"))) {
						bean.setOdor_val(String.format("%.4f", Double.parseDouble(bean.getOdor_val())));
					}
					}

					/*if (MisUtility.ifEmpty(bean.getTest_phase())) {
					if (!(bean.getTest_phase().equalsIgnoreCase("ND")
							|| bean.getTest_phase().equalsIgnoreCase("BDL")
							|| bean.getTest_phase().equalsIgnoreCase("NT"))) {
						bean.setTest_phase(String.format("%.4f", Double.parseDouble(bean.getTest_phase())));
					}
					}*/
					waterQualityStatusBeanss.add(bean);
				}
			}
			if (!MisUtility.ifEmpty(waterQualityStatusBeanss)) {
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json2 = gson.toJson(waterQualityStatusBeanss);
				System.out.println(json2);
				ServletOutputStream out = response.getOutputStream();
				out.print(json2);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getLocalizedMessage(), e);

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public void filePDF(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("-------------IN file PDF----------------");
		WaterQualityStatusForm waterQualityStatusForm = (WaterQualityStatusForm) form;
		String jasperFile = waterQualityStatusForm.getJasperFile();
		String fileTitle = waterQualityStatusForm.getFileTitle();
		setWhere(waterQualityStatusForm, request);
		JRSwapFileVirtualizer virtualizer = null;
		virtualizer = new JRSwapFileVirtualizer(10, new JRSwapFile("/usr", 90000, 15000), false);
		parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
		exportToPDF(jasperFile, fileTitle, parameters, request, response);
	}

	@SuppressWarnings("unchecked")
	public void fileExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		WaterQualityStatusForm waterQualityStatusForm = (WaterQualityStatusForm) form;
		String jasperFile = waterQualityStatusForm.getJasperFile();
		String fileTitle = waterQualityStatusForm.getFileTitle();
		setWhere(waterQualityStatusForm, request);
		JRSwapFileVirtualizer virtualizer = null;
		virtualizer = new JRSwapFileVirtualizer(10, new JRSwapFile("/usr", 90000, 15000), false);
		parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
		misJasperUtil.exportToExcel(jasperFile, fileTitle, parameters, request, response);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setWhere(WaterQualityStatusForm waterQualityStatusForm, HttpServletRequest request) {

		System.out.println("-----7-------------" + waterQualityStatusForm.getVillageIds());
		parameters = new HashMap();
		parameters.put("village", waterQualityStatusForm.getVillageIds());
	}

	public void exportToPDF(String jasperFile, String fileTitle, @SuppressWarnings("rawtypes") Map parameters,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String currentDate = MisUtility.now("dd-MM-yyyy");
		Connection connection = null;
		InputStream reportStream = request.getSession().getServletContext().getResourceAsStream(jasperFile);
		try {
			connection = misJdcDaoImpl.getMISDataSource().getConnection();
			connection.setAutoCommit(false);
			ServletOutputStream servletOutputStream = response.getOutputStream();
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename=" + fileTitle + currentDate + ".pdf");
			JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, parameters, connection);
			servletOutputStream.flush();
			servletOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			if (connection != null) {
				connection.close();
			}
		}
	}

}
