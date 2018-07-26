package com.prwss.mis.ccdu.reports.struts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;
import net.sf.jasperreports.engine.util.JRSwapFile;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.LabelValueBean;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.ccdu.plan.dao.CCDUPlanMasterBean;
import com.prwss.mis.ccdu.plan.dao.CCDUPlanMasterDao;
import com.prwss.mis.common.MISJdcDaoImpl;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MISJasperUtil;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;
import com.prwss.mis.pmm.report.struts.EstimatesAwardContractsReportAction;

public class CcduReportsAction extends DispatchAction {
	private LocationDao locationDao;
	@SuppressWarnings("unused")
	private MISSessionBean misAuditBean;
	private MISJdcDaoImpl misJdcDaoImpl;
	private MISJasperUtil misJasperUtil;
	private CCDUPlanMasterDao ccduPlanMasterDao;

	public void setCcduPlanMasterDao(CCDUPlanMasterDao ccduPlanMasterDao) {
		this.ccduPlanMasterDao = ccduPlanMasterDao;
	}

	@SuppressWarnings("rawtypes")
	private Map parameters;
	private Logger log = Logger
			.getLogger(EstimatesAwardContractsReportAction.class);

	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	public void setMisJasperUtil(MISJasperUtil misJasperUtil) {
		this.misJasperUtil = misJasperUtil;
	}

	public void setMisJdcDaoImpl(MISJdcDaoImpl misJdcDaoImpl) {
		this.misJdcDaoImpl = misJdcDaoImpl;
	}

	@SuppressWarnings("rawtypes")
	public void setParameters(Map parameters) {
		this.parameters = parameters;
	}

	@SuppressWarnings("rawtypes")
	public Map getParameters() {
		return parameters;
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		CcduReportsForm ccduReportsForm = (CcduReportsForm) form;
		request.setAttribute("misJdcDaoImpl", misJdcDaoImpl);
		request.setAttribute("form", ccduReportsForm);
		this.setAttrib(request);
		return mapping.findForward("display");
	}

	public void filePDF(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CcduReportsForm ccduReportsForm = (CcduReportsForm) form;
		String jasperFile = ccduReportsForm.getJasperFile();
		String fileTitle = ccduReportsForm.getFileTitle();
		System.out.println("-------------Check1------"+ccduReportsForm.getFinYear());
		setWhere(ccduReportsForm, request);
		JRSwapFileVirtualizer virtualizer = null;
		virtualizer = new JRSwapFileVirtualizer(10, new JRSwapFile("/usr",
				90000, 15000), false);
		parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
		misJasperUtil.exportToPDF(jasperFile, fileTitle, parameters, request,
				response);
	}

	public void fileExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CcduReportsForm ccduReportsForm = (CcduReportsForm) form;
		String jasperFile = ccduReportsForm.getJasperFile();
		String fileTitle = ccduReportsForm.getFileTitle();
		setWhere(ccduReportsForm, request);
		JRSwapFileVirtualizer virtualizer = null;
		virtualizer = new JRSwapFileVirtualizer(10, new JRSwapFile("/usr",
				90000, 15000), false);
		parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
		misJasperUtil.exportToExcel(jasperFile, fileTitle, parameters, request,
				response);
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		this.setAttrib(request);
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {
				{
					misAuditBean = (MISSessionBean) request.getSession()
							.getAttribute("misSessionBean");
				}
			} else {
				return mapping.findForward("login");
			}
			Set<LabelValueBean> zones = getZones();
			request.getSession().setAttribute("zones", zones);
			Set<LabelValueBean> circles = getCircles();
			request.getSession().setAttribute("circles", circles);
			Set<LabelValueBean> districts = getDistricts();
			request.getSession().setAttribute("districts", districts);
			Set<LabelValueBean> divisional = getDivisionalOffice();
			request.getSession().setAttribute("divisional", divisional);
			Set<LabelValueBean> plans = getPlanId();
			request.getSession().setAttribute("plans", plans);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		System.out.println("Unspecified.......CCDUReport");
		return mapping.findForward("cScreen");
	}

	private Set<LabelValueBean> getZones() {
		Set<LabelValueBean> zones = null;
		Set<LocationBean> locationBeans = null;
		try {
			locationBeans = locationDao.getLocationIds("ZONE");
			zones = new HashSet<LabelValueBean>();
			for (LocationBean locationBean2 : locationBeans) {
				zones.add(new LabelValueBean(locationBean2.getLocationId()
						+ MISConstants.LABEL_VALUE_BEAN_SEPARATOR
						+ locationBean2.getLocationName(), locationBean2
						.getLocationId()));
			}
		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return zones;
	}

	private Set<LabelValueBean> getCircles() {
		Set<LabelValueBean> circles = null;
		Set<LocationBean> locationBeans = null;
		try {
			locationBeans = locationDao.getLocationIds("CIRCLE");
			circles = new HashSet<LabelValueBean>();
			for (LocationBean locationBean2 : locationBeans) {
				circles.add(new LabelValueBean(locationBean2.getLocationId()
						+ MISConstants.LABEL_VALUE_BEAN_SEPARATOR
						+ locationBean2.getLocationName(), locationBean2
						.getLocationId()));
			}
		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return circles;
	}

	private Set<LabelValueBean> getPlanId() {
		Set<LabelValueBean> plans = null;
		Set<CCDUPlanMasterBean> ccduPlanMasterBeans = null;
		String planId = null;
		// String fromDate = null;
		// String toDate = null;
		try {

			ccduPlanMasterBeans = ccduPlanMasterDao.getDistinctCCDUPlan();
			plans = new HashSet<LabelValueBean>();
			for (CCDUPlanMasterBean ccduPlanMasterBean : ccduPlanMasterBeans) {
				planId = new Long(ccduPlanMasterBean.getPlanId()).toString();
				plans.add(new LabelValueBean(
						ccduPlanMasterBean.getPlanId()
								+ " - ("
								+ MisUtility
										.convertDateToStringForDisplay(ccduPlanMasterBean
												.getFromDate())
								+ MISConstants.LABEL_VALUE_DATE_SEPARATOR
								+ MisUtility
										.convertDateToStringForDisplay(ccduPlanMasterBean
												.getToDate()) + ")", planId));
			}
		} catch (DataAccessException e) {
			log.error(e);
		}
		return plans;
	}

	private Set<LabelValueBean> getDistricts() {
		Set<LabelValueBean> districts = null;
		Set<LocationBean> locationBeans = null;
		try {
			locationBeans = locationDao.getLocationIds("DISTRICT");
			districts = new HashSet<LabelValueBean>();
			for (LocationBean locationBean2 : locationBeans) {
				districts.add(new LabelValueBean(locationBean2.getLocationId()
						+ MISConstants.LABEL_VALUE_BEAN_SEPARATOR
						+ locationBean2.getLocationName(), locationBean2
						.getLocationId()));
			}
		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return districts;
	}

	private Set<LabelValueBean> getDivisionalOffice() {
		Set<LabelValueBean> divisionalOffice = null;
		Set<LocationBean> locationBeans = null;
		List<String> locationType = new ArrayList<String>();
		locationType.add("DO");
		try {

			locationBeans = locationDao.getLocationIdOnTypeList(locationType);
			divisionalOffice = new HashSet<LabelValueBean>();
			for (LocationBean bean : locationBeans) {
				divisionalOffice.add(new LabelValueBean(bean.getLocationName(),
						bean.getLocationId()));
			}

		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return divisionalOffice;
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "rpt");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationId");
	}

	@SuppressWarnings("unused")
	private void refreshCcduReportsForm(CcduReportsForm ccduReportsForm) {
		ccduReportsForm.setSelectZone(null);
		ccduReportsForm.setSelectCircle(null);
		ccduReportsForm.setSelectDistrict(null);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setWhere(CcduReportsForm ccduReportsForm,
			HttpServletRequest request) {
		String innerWhere = "";
		String selectZone = ccduReportsForm.getSelectZone();
		String zoneId = ccduReportsForm.getZoneId();
		String selectCircle = ccduReportsForm.getSelectCircle();
		String circleId = ccduReportsForm.getCircleId();
		String selectDistrict = ccduReportsForm.getSelectDistrict();
		String districtId = ccduReportsForm.getDistrictId();
		String selectDivisionalOfficeId = ccduReportsForm
				.getSelectDivisionalOfficeId();
		String divisionalOfficeId = ccduReportsForm.getDivisionalOfficeId();
		String selectReport = ccduReportsForm.getSelectReport();
		String finYearId = ccduReportsForm.getFinYear();
		String month = ccduReportsForm.getMonth();
		String monthId = ccduReportsForm.getMonthId();
		String year = ccduReportsForm.getYear();
		String fromPeriod = ccduReportsForm.getFromDate();
		String toPeriod = ccduReportsForm.getToDate();
		String selectPeriod = ccduReportsForm.getSelectPeriod();
		long planId = ccduReportsForm.getPlanId();
		String from = "All";
		String to = "All";
		String from_date = "All";
		String to_date = "All";
		parameters = new HashMap();
		String queryString = "";
		String where = "1=1 ";
		if (selectZone.equals("S")) {
			where = where + " and zone_id='" + zoneId + "' ";
			queryString = queryString + " Zone: " + zoneId;
		} else {
			queryString = queryString + " Zone: All";
		}
		if (selectCircle.equals("S")) {
			where = where + " and circle_id='" + circleId + "' ";
			queryString = queryString + ", Circle: " + circleId;
		} else {
			queryString = queryString + ", Circle: All";
		}
		if (selectDistrict.equals("S")) {
			where = where + "and district_id='" + districtId + "' ";
			queryString = queryString + ", District: " + districtId;
		} else {
			queryString = queryString + ", District: All";
		}
		if (selectDivisionalOfficeId.equals("S")) {
			where = where + "and location_id='" + divisionalOfficeId + "' ";
		}
		System.out.println("selectPeriod: " + selectPeriod);
		System.out.println("fromPeriod: " + fromPeriod);
		System.out.println("toPeriod: " + toPeriod);
		from = fromPeriod == null ? "All" : fromPeriod;
		System.out.println("from: " + from);
		to = toPeriod == null ? "All" : toPeriod;
		System.out.println("to: " + to);
		from_date = fromPeriod == null ? "All" : MisUtility
				.convertStringToDate(fromPeriod).toString();
		System.out.println("from_date: " + from_date);
		to_date = toPeriod == null ? "All" : MisUtility.convertStringToDate(
				toPeriod).toString();
		System.out.println("to_date: " + to_date);
		System.out.println("-------------Check2------"+ccduReportsForm.getFinYear());
		
		/*
		 * if(selectReport.equals("CCDURPT001_1")||selectReport.equals(
		 * "CCDURPT001_3")||selectReport.equals("CCDURPT001_4")){ where =
		 * where+"and plan_id="+planId; } if(selectPeriod.equals("S") &&
		 * selectReport.equals("CCDURPT001_3")){ where =
		 * where+"and date_of_training between '"
		 * +MisUtility.convertStringToDate(
		 * fromDate)+"' and '"+MisUtility.convertStringToDate(toDate)+"'"; }
		 * 
		 * if(selectPeriod.equals("S") && selectReport.equals("CCDURPT001_4")){
		 * where =
		 * where+"and date_of_training between '"+MisUtility.convertStringToDate
		 * (fromDate)+"' and '"+MisUtility.convertStringToDate(toDate)+"'"; }
		 * 
		 * if(selectPeriod.equals("S") &&
		 * selectReport.equals("CCDURPT001_6")||selectPeriod.equals("S") &&
		 * selectReport.equals("CCDURPT001_7")){ where =
		 * where+"and activity_date between '"
		 * +MisUtility.convertStringToDate(fromDate
		 * )+"' and '"+MisUtility.convertStringToDate(toDate)+"'"; from_date =
		 * fromDate; to_date = toDate; }
		 */

		parameters.put("where", where);
		parameters.put("fincode", finYearId);
		parameters.put("month", monthId);
		parameters.put("year", year);
		// parameters.put("SUBREPORT_DIR",
		// "C:/vikash/apache-tomcat-6.0.29/webapps/PRWSS_MIS/ccdu/reports/");
		parameters.put("SUBREPORT_DIR",
				"/usr/apache-tomcat-6.0.32/webapps/PRWSS_MIS/ccdu/reports/");
		parameters.put("from", from);
		parameters.put("to", to);
		parameters.put("from_date", from_date);
		parameters.put("to_date", to_date);
		parameters.put("queryString", queryString);
		parameters.put("innerWhere", innerWhere);
		System.out.println("selectReport: " + selectReport);
		System.out.println("Action: where : " + where);
		System.out.println("Action: innerWhere : " + innerWhere);
		System.out.println("Action: queryString: " + queryString);
		System.out.println("Action: fincode : "+finYearId);
		System.out.println("Action: month: "+monthId);
	}
}
