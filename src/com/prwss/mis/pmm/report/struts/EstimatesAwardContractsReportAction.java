package com.prwss.mis.pmm.report.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
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

import com.prwss.mis.common.MISJdcDaoImpl;
import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MISJasperUtil;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.masters.constituency.dao.ConstituencyBean;
import com.prwss.mis.masters.constituency.dao.ConstituencyDao;
import com.prwss.mis.masters.location.dao.LocationBean;
import com.prwss.mis.masters.location.dao.LocationDao;
import com.prwss.mis.masters.program.dao.ProgramBean;
import com.prwss.mis.masters.program.dao.ProgramDao;

public class EstimatesAwardContractsReportAction extends DispatchAction {

	private ProgramDao programDao;
	private LocationDao locationDao;
	@SuppressWarnings("unused")
	private MISSessionBean misAuditBean;
	private MISJdcDaoImpl misJdcDaoImpl;
	private MISJasperUtil misJasperUtil;
	private ConstituencyDao constituencyDao;
	@SuppressWarnings("rawtypes")
	private Map parameters;
	private Logger log = Logger
			.getLogger(EstimatesAwardContractsReportAction.class);

	public void setConstituencyDao(ConstituencyDao constituencyDao) {
		this.constituencyDao = constituencyDao;
	}

	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	public void setProgramDao(ProgramDao programDao) {
		this.programDao = programDao;
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
		EstimatesAwardContractsReportForm estimatesAwardContractsReportForm = (EstimatesAwardContractsReportForm) form;
		request.setAttribute("misJdcDaoImpl", misJdcDaoImpl);
		request.setAttribute("form", estimatesAwardContractsReportForm);
		this.setAttrib(request);
		return mapping.findForward("display");
	}

	public void filePDF(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("-------------IN file PDF----------------");
		EstimatesAwardContractsReportForm estimatesAwardContractsReportForm = (EstimatesAwardContractsReportForm) form;
		String jasperFile = estimatesAwardContractsReportForm.getJasperFile();
		String fileTitle = estimatesAwardContractsReportForm.getFileTitle();
		setWhere(estimatesAwardContractsReportForm, request);
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
		EstimatesAwardContractsReportForm estimatesAwardContractsReportForm = (EstimatesAwardContractsReportForm) form;
		String jasperFile = estimatesAwardContractsReportForm.getJasperFile();
		String fileTitle = estimatesAwardContractsReportForm.getFileTitle();
		setWhere(estimatesAwardContractsReportForm, request);
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
			Set<LabelValueBean> programs = getPrograms();
			request.getSession().setAttribute("programs", programs);
			// EstimatesAwardContractsReportForm
			// estimatesAwardContractsReportForm =
			// (EstimatesAwardContractsReportForm)form;
			// refreshEstimatesAwardContractsReportForm(estimatesAwardContractsReportForm);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		// System.out.println("Unspecified.......EstimatesAwardContractsReport");
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

	private Set<LabelValueBean> getPrograms() {
		Set<LabelValueBean> programs = null;
		List<ProgramBean> programBeans = null;
		try {
			ProgramBean programBean = new ProgramBean();
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			programBeans = programDao.findProgram(programBean, statusList);
			programs = new HashSet<LabelValueBean>();
			for (ProgramBean programBean2 : programBeans) {
				System.out.println(programBean2);
				programs.add(new LabelValueBean(programBean2.getProgramId()
						+ MISConstants.LABEL_VALUE_BEAN_SEPARATOR
						+ programBean2.getProgramName(), programBean2
						.getProgramId()));
			}
		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return programs;
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "rpt");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky", "locationId");
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	private void setWhere(
			EstimatesAwardContractsReportForm estimatesAwardContractsReportForm,
			HttpServletRequest request) {

		String zone = null;
		String circle = null;
		String division1 = null;

		// String swap3=null;
		String innerWhere = " and 1=1 ";
		String selectZone = estimatesAwardContractsReportForm.getSelectZone();
		String zoneId = estimatesAwardContractsReportForm.getZoneId();
		String selectCircle = estimatesAwardContractsReportForm
				.getSelectCircle();
		String circleId = estimatesAwardContractsReportForm.getCircleId();
		String selectDistrict = estimatesAwardContractsReportForm
				.getSelectDistrict();
		String districtId = estimatesAwardContractsReportForm.getDistrictId();
		String selectProgram = estimatesAwardContractsReportForm
				.getSelectProgram();
		String programId = estimatesAwardContractsReportForm.getProgramId();
		String approvalStatus = estimatesAwardContractsReportForm
				.getApprovalStatus();
		String selectPeriod = estimatesAwardContractsReportForm
				.getSelectPeriod();
		String fromPeriod = estimatesAwardContractsReportForm.getFromDate();
		String toPeriod = estimatesAwardContractsReportForm.getToDate();
		String selectReport = estimatesAwardContractsReportForm
				.getSelectReport();
		String swap = estimatesAwardContractsReportForm.getSwap();
		String swap1 = estimatesAwardContractsReportForm.getSwap1();
		String swap2 = estimatesAwardContractsReportForm.getSwap2();
		String swap3 = estimatesAwardContractsReportForm.getSwap3();
		String swap4 = estimatesAwardContractsReportForm.getSwap4();

		/* String swap4=estimatesAwardContractsReportForm.getSwap4(); */
		String schemeType = estimatesAwardContractsReportForm.getSchemeType();
		String schemeTypeR = estimatesAwardContractsReportForm.getSchemeTypeR();
		String schemeTypeRR = estimatesAwardContractsReportForm
				.getSchemeTypeRR();
		String village_category = estimatesAwardContractsReportForm
				.getVillage_category();

		String ncc = estimatesAwardContractsReportForm.getNcfc();
		String ncFc = estimatesAwardContractsReportForm.getNcFc();
		String month = estimatesAwardContractsReportForm.getMonthId();
		String finYearId = estimatesAwardContractsReportForm.getFinYearId();
		String status = estimatesAwardContractsReportForm.getStatus();
		String IDAType = estimatesAwardContractsReportForm.getIDAType();
		String SMType = estimatesAwardContractsReportForm.getSMType();
		String IDA = estimatesAwardContractsReportForm.getIDA5();
		// String sType=estimatesAwardContractsReportForm.getSchemeTypeRRR();
		String yType = estimatesAwardContractsReportForm.getYearType();
		String component = estimatesAwardContractsReportForm.getComponentName();
		String circleName = estimatesAwardContractsReportForm.getCircleName();

		if (MisUtility.ifEmpty(zoneId)) {

			if (MisUtility.ifEmpty(estimatesAwardContractsReportForm
					.getZoneId())) {
				zone = getLocationNameById(estimatesAwardContractsReportForm
						.getZoneId());
			}
		}
		if (selectZone.equalsIgnoreCase("A")) {
			zone = "";
		}
		if (MisUtility.ifEmpty(estimatesAwardContractsReportForm.getCircleId())) {
			circle = getLocationNameById(estimatesAwardContractsReportForm
					.getCircleId());
			;
		}
		if (selectCircle.equalsIgnoreCase("A")) {
			circle = "";
		}
		if (MisUtility.ifEmpty(estimatesAwardContractsReportForm.getDivision())) {
			division1 = estimatesAwardContractsReportForm.getDivision();
			if (selectDistrict.equalsIgnoreCase("A")) {
				division1 = "";
			}
			System.out
					.println("-----------------------------division1--------------------------------------->"
							+ division1);
		} else {

			System.out.println("inside division1------------------------");
			division1 = "";
		}

		// String IDA_IMP=estimatesAwardContractsReportForm.getIDA();
		System.out.println("-----------Status is-------" + status);
		parameters = new HashMap();
		String scheme_source = "1=1 ";
		String from = "All";
		String to = "All";
		String from_date = "All";
		String to_date = "All";
		String where = "1=1 ";
		String program_id = "1=1 ";
		String program_id2 = "1=1 ";
		String source_of_scheme = "1=1 ";
		String prog_id = "1=1 ";
		String plan_id = "1=1 ";
		String scheme_source1 = "1=1 ";
		String phase1 = "(All)";
		String mv_sv = "1=1 ";
		String ncPc = "1=1 ";
		String sv_mv = "1=1 ";
		String swap5 = "1=1 ";
		String Plan_id = "1=1 ";
		String year = "1=1 ";
		String scheme_source2 = "1=1 ";

		String category = "1=1 ";
		String top_category = "1=1 ";

		String circle1 = "1=1 ";

		if (estimatesAwardContractsReportForm.getSelectCircle()
				.equalsIgnoreCase("A")) {
			circle1 = "All";
		} else {
			circle1 = circleName;
		}

		System.out.println("selectReport: " + selectReport);
		System.out.println("ncpc===" + ncc);
		// -----------------------Select
		// Zone-----------------------------------------------------
		if (selectZone.equals("S")) {
			if (selectReport.equals("PMMRPT001_23")) {
				where = where + " and z.zone_id='" + zoneId + "' "; // Due to
																	// ambiguous
																	// field
			} else {
				where = where + " and zone_id='" + zoneId + "' ";
			}
		}
		// -----------------------Select
		// Circle-----------------------------------------------------
		if (selectCircle.equals("S")) {
			if (selectReport.equals("PMMRPT001_23")) {
				where = where + " and c.circle_id='" + circleId + "' "; // Due
																		// to
																		// ambiguous
																		// field
			} else {
				where = where + " and circle_id='" + circleId + "' ";
			}
		}
		// -----------------------Select
		// District-----------------------------------------------------
		if (selectDistrict.equals("S")) {
			if (selectReport.equals("PMMRPT001_23")) {
				where = where + "and d.district_id='" + districtId + "' "; // Due
																			// to
																			// ambiguous
																			// field
			} else if (selectReport.equals("PMMRPT001_24")
					|| selectReport.equals("PMMRPT001_001")) {
				where = where + "and district_id='" + districtId + "' ";
			} else {
				where = where + "and district_id='" + districtId + "' ";
			}
		}
		// -----------------------Select
		// Program-----------------------------------------------------
		if (selectProgram.equals("S")) {
			if (selectReport.equals("PMMRPT001_6")
					|| selectReport.equals("PMMRPT001_7")
					|| selectReport.equals("PMMRPT001_8")
					|| selectReport.equals("PMMRPT001_9")
					|| selectReport.equals("PMMRPT001_10")) {
				innerWhere = innerWhere + "and prog_id='" + programId + "' ";
			} else if (selectReport.equals("PMMRPT001_18")
					|| selectReport.equals("PMMRPT001_19")) {
				innerWhere = programId;
			}
		}

		if (selectReport.equals("TTL001_0011")
				|| selectReport.equals("TTL001_002")
				|| selectReport.equals("TTL001_003")) {

			if (MisUtility.ifEmpty(component)) {
				category = "village_category='" + component + "'";
				top_category = component;
			} else {
				category = "village_category in('1A','1A (WL)','2A','2B','3B1')";
				top_category = "All";
			}

		}
		// -----------------------Select Approval
		// Status-----------------------------------------------------
		if (approvalStatus.equals("A") || approvalStatus.equals("U")) {
			where = where + "and approval_status='" + approvalStatus + "' ";
		}
		// -----------------------Select
		// Period-----------------------------------------------------
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
		if (selectPeriod.equals("S")) {

			if (selectReport.equals("PMMRPT001_22")
					|| selectReport.equals("PMMRPT001_23")) {
				where = where + " and  month_of_commissioning between '"
						+ from_date + "' and '" + to_date + "'";

			} else if (selectReport.equals("PMMRPT001_20")) {
				where = where + " and  month_of_commissioning between '"
						+ from_date + "' and '" + to_date + "'";
			}
			/*
			 * else if(selectReport.equals("PMMRPT001_21")||selectReport.equals(
			 * "PMMRPT001_21_all")){
			 * where=where+" and scheme_commissioned_date between '"
			 * +from_date+"' and '"+to_date+"'"; }else
			 * if(selectReport.equals("PMMRPT001_21_all")){
			 * innerWhere=innerWhere
			 * +" and scheme_commissioned_date <= '"+to_date+"'"; }
			 */else if (selectReport.equals("RPT001_3")
					|| selectReport.equals("RPT001_3_blk")
					|| selectReport.equals("RPT001_3_div")
					|| selectReport.equals("RPT001_3_consti")) {
				where = where + " and  scheme_commissioned_date between '"
						+ from_date + "' and '" + to_date + "'";
			}
		}

		else {
			if (selectReport.equals("PMMRPT001_21")
					|| selectReport.equals("PMMRPT001_21_all")
					|| selectReport.equals("PMMRPT001_21_all_const")
					|| selectReport.equals("PMMRPT001_21_const")) {
				where = where;
				from_date = "01-01-01";
			}
			from = "Beginning";
			to = "All";
		}

		if (selectReport.equals("PMMRPT001_A12_abs")) {
			if (selectPeriod.equals("S")) {
				if (SMType.equals("single_village")) {
					sv_mv = "  sv_mv='SV'";
				}
				if (SMType.equals("multi_village")) {
					sv_mv = "  sv_mv='MV'";
				}
			}
		}
		if (selectReport.equals("PMMRPT001_24_details")) {
			if (selectPeriod.equals("S")) {
				if (SMType.equals("single_village")) {
					sv_mv = "  sv_mv='SV'";
				}
				if (SMType.equals("multi_village")) {
					sv_mv = "  sv_mv='MV'";
				}
			}
		}

		if (selectReport.equals("PMMRPT001_A2.4_imp")
				|| selectReport.equals("PMMRPT001_A2.5_imp")) {
			// System.out.println("in report PMMRPT001_A2.4_imp");
			if (selectPeriod.equals("S")) {
				/*
				 * System.out.println("inside select");
				 * System.out.println("idatype==="+IDAType);
				 */
				if (IDAType.equals("phase1")) {

					plan_id = "  plan_id not like '%W-2014-2015-PROG15'";
					phase1 = "(phase1)";
				}
				if (IDAType.equals("phase2")) {
					plan_id = "  plan_id  like '%W-2014-2015-PROG15'";
					phase1 = "(phase2)";
				}
				// System.out.println("idatype==="+IDA);
				/*
				 * if(IDA_IMP.equals("IDA")){
				 * 
				 * prog_id="  prog_id='PROG15'"; // phase1="(phase1)"; }
				 * if(IDA_IMP.equals("IDA2")){ prog_id="  prog_id='PROG30'";
				 * //phase1="(phase2)"; }
				 */
			}
		}
		if (selectPeriod.equals("S")) {
			if (selectReport.equals("PMMRPT001_50")
					|| selectReport.equals("PMMRPT001_51")
					|| selectReport.equals("PMMRPT001_52")) {

				Plan_id = "  Plan_id like '%" + yType + "%'";
				year = yType;
				System.out.println("plan id=== year===" + Plan_id);
			}
		} else {
			year = "All";
		}

		if (selectReport.equals("PMMRPT001_A2.5_dis")
				|| selectReport.equals("PMMRPT001_A2.5_sew")
				|| selectReport.equals("PMMRPT001_A2.5_wm")) {

			if (selectPeriod.equals("S")) {
				System.out.println("inside select");
				System.out.println("idatype===" + IDA);

				if (IDA.equals("IDA")) {

					prog_id = "  prog_id='PROG15'";
					// phase1="(phase1)";
				}
				if (IDA.equals("IDA2")) {
					prog_id = "  prog_id='PROG30'";
					// phase1="(phase2)";
				}
			}
		}

		if (selectReport.equals("PMMRPT001_5")) {
			System.out.println("ncPc====" + ncPc);

			if (ncc.equals("A")) {
				parameters.put("nc_pc_label", "All");
			} else {
				parameters.put("nc_pc_label", ncc);
				ncPc = "  nc_pc_status='" + ncc + "'";

			}
			parameters.put("nc_pc", ncPc);
			if (swap4.equals("NONSWAP")) {
				// where=where+" and swap_nonswap='NONSWAP' and program_id<>'PROG15'";
				program_id = "  program_id not in('PROG15','PROG06','PROG11','PROG20')";
				// program_id2="  program_id not in('PROG15','PROG06','PROG11','PROG20')";
			} else if (swap4.equals("SWAP-NON IDA")) {
				program_id = "  program_id in ('PROG06','PROG11','PROG20')";
				// program_id2="  program_id in ('PROG06','PROG11','PROG20')";
				// where=where+" and swap_nonswap='SWAP-NON IDA' and program_id<>'PROG15'";

				// program_id="  program_id in ('PROG06','PROG11','PROG20')";
			} else if (swap4.equals("SWAP-IDA")) {
				program_id = "  program_id='PROG15'";
				// program_id2="  program_id='PROG15'";
				// where=where+" and swap_nonswap='SWAP-IDA' and program_id='PROG15'";

			} else if (swap4.equals("IBRD")) {
				program_id = "  program_id='PROG30'";
				// where=where+" and swap_nonswap='IBRD' and program_id='PROG30'";
			}

			if (schemeTypeR.equals("WATERSUPPLY")) {
				scheme_source = "  sh.scheme_source not in ('SEWERAGE','IMPROVEMENT','HANDPUMP')";
				// scheme_source2="  sh.scheme_source not in ('SEWERAGE','HANDPUMP')";

				scheme_source1 = "  sh.scheme_source in ('0')";
				// System.out.println("water supply"+scheme_source);
			} else if (schemeTypeR.equals("IMPROVEMENT")) {
				scheme_source1 = "  sh.scheme_source  not in ('SEWERAGE','IMPROVEMENT','HANDPUMP')";
				// scheme_source2="  sh.scheme_source='IMPROVEMENT'";

				scheme_source = "  sh.scheme_source='IMPROVEMENT'";
			} else if (schemeTypeR.equals("SEWERAGE")) {
				// program_id="  program_id not in('PROG15','PROG06','PROG11','PROG20')";
				scheme_source = "  scheme_source='SEWERAGE'";
				scheme_source1 = "  sh.scheme_source  in ('0')";

				// where="  source_of_scheme='SEWERAGE'";
				// where=where+" and source_of_scheme='SEWERAGE'";
			}
		}

		if (selectReport.equals("PMMRPT001_5_abstr_np_swap")
				|| selectReport.equals("PMMRPT001_5_abstr_np_nonswap")
				|| selectReport.equals("PMMRPT001_5_abstr70")
				|| selectReport.equals("PMMRPT001_5_dtl_np_swap")
				|| selectReport.equals("PMMRPT001_5_dtl_np_nonswap")
				|| selectReport.equals("PMMRPT001_5_detail")
				|| selectReport.equals("PMMRPT001_5_abstr_p_ida")
				|| selectReport.equals("PMMRPT001_5_abstr_p_nonida")
				|| selectReport.equals("PMMRPT001_5_dtl_p_ida")
				|| selectReport.equals("PMMRPT001_5_dtl_p_nonida")) {
			to = to == "All" ? to_date : to;
		}
		if (selectReport.equals("PMMRPT001_5_dtl_sew")) {
			to = toPeriod == null ? "All" : MisUtility.convertStringToDate(
					toPeriod).toString();
			to = to == "All" ? to_date : to;
			System.out.println("to in if " + to);
		}

		/*
		 * if(selectReport.equals("PMMRPT001_24_details")){
		 * System.out.println("HabitationType=="+HabitationType);
		 * if(HabitationType.equals("SV")||HabitationType.equals("MV")){
		 * mv_sv="  sv_mv='"+HabitationType+"'";
		 * System.out.println("sv mv=="+mv_sv); } }
		 */

		if (selectReport.equals("Abs_MH")) {

			if (village_category.equals("ALL")) {
				village_category = "  village_category in('1A','1A(WL)','2A','2B')";
			}
			if (village_category.equals("NONSWAP")) {
				village_category = "  village_category ='1A'";
			} else if (village_category.equals("SWAP-NON IDA")) {
				village_category = "  village_category in('2A')";
			} else if (village_category.equals("SWAP-IDA")) {
				village_category = "  village_category in('2B')";
			} else if (village_category.equals("OH")) { // All IBRD by Ranjay
				village_category = "  village_category NOT in('1A','1A(WL)','2A','2B')";
			}
			System.out.println("village category ::" + village_category);

			System.out.println("where===" + where);

		}
		if (selectReport.equals("Dtl_MH")) {
			if (village_category.equals("ALL")) {
				village_category = "  village_category in('1A','1A(WL)','2A','2B')";
			}
			if (village_category.equals("NONSWAP")) {
				village_category = "  village_category ='1A'";
			} else if (village_category.equals("SWAP-NON IDA")) {
				village_category = "  village_category in('2A')";
			} else if (village_category.equals("SWAP-IDA")) {
				village_category = "  village_category in('2B')";
			} else if (village_category.equals("OH")) { // All IBRD by Ranjay
				village_category = "  village_category NOT in('1A','1A(WL)','2A','2B')";
			}

			System.out.println("where===" + where);

		}

		if (selectReport.equals("PMMRPT001_20")) {

			System.out.println("swap: " + swap1);
			System.out.println("nc-pc-fc-all====" + ncFc);
			System.out.println("schemetyper====" + schemeTypeRR);

			if (swap1.equals("NONSWAP")) {
				program_id = "  program_id not in('PROG15','PROG06','PROG11','PROG20')";
				// where=where+" and swap_nonswap='NONSWAP' and program_id<>'PROG15'";
			} else if (swap1.equals("SWAP-NON IDA")) {
				program_id = "  program_id in ('PROG06','PROG11','PROG20')";
				// where=where+" and swap_nonswap='SWAP-NON IDA' and program_id<>'PROG15'";
			} else if (swap1.equals("SWAP-IDA")) {
				program_id = "  program_id='PROG15'";
				// where=where+" and swap_nonswap='SWAP-IDA' and program_id='PROG15'";
			} else if (swap1.equals("IBRD")) { // All IBRD by Ranjay
				program_id = "  program_id='PROG30'";
				// where=where+" and swap_nonswap='SWAP-IDA' and program_id='PROG15'";
			}
			/*
			 * if(ncFc.equals("NC")){
			 * 
			 * where = where+" and village_commissioned_nc is not null"; }else
			 * if(ncFc.equals("PC")){ where =
			 * where+" and village_commissioned_pc is not null"; }else
			 * if(ncFc.equals("FC")){ where =
			 * where+" and village_commissioned_sb is not null"; }
			 */
			if (schemeTypeRR.equals("WATERSUPPLY")) {
				source_of_scheme = "  source_of_scheme not in('SEWERAGE','IMPROVEMENT')";
				// program_id="  program_id not in('PROG06','PROG11','PROG20')";
				// source_of_scheme="  source_of_scheme='WATERSUPPLY'";
			} else if (schemeTypeRR.equals("IMPROVEMENT")) {
				// source_of_scheme="  source_of_scheme='IMPROVEMENT'";
				// program_id="  program_id not in('PROG15','PROG06','PROG11','PROG20')";
				where = "  source_of_scheme='IMPROVEMENT'";

			} else if (schemeTypeRR.equals("SEWERAGE")) {
				// program_id="  program_id not in('PROG15','PROG06','PROG11','PROG20')";
				source_of_scheme = "  source_of_scheme='SEWERAGE'";
				// where="  source_of_scheme='SEWERAGE'";
				// where=where+" and source_of_scheme='SEWERAGE'";
			}
			System.out.println("where===" + where);

		}

		if (selectReport.equals("PMMRPT001_21")) {
			// System.out.println("swap: "+swap2);
			if (swap2.equals("NONSWAP")) {
				System.out.println("NONSWAP");
				// program_id="  program_id not in('PROG15','PROG06','PROG11','PROG20') and village_id not in('7163','13777','13781','13817','13853','13857','13858','13859','14571','10817','10845','7169','7247','10668','10694','10818','10822','10837','11059','11073')";
				program_id = "  program_id not in('PROG15','PROG06','PROG11','PROG20')";
				// prog_id="  prog_id not in('PROG15','PROG06','PROG11','PROG20') and village_id not in('7163','13777','13781','13817','13853','13857','13858','13859','14571','10817','10845','7169','7247','10668','10694','10818','10822','10837','11059','11073')";
				prog_id = "  prog_id not in('PROG15','PROG06','PROG11','PROG20')";

				// where=where+" and swap_nonswap='NONSWAP' and program_id<>'PROG15'";
			} else if (swap2.equals("SWAP-NON IDA")) {
				// program_id="  program_id in ('PROG06','PROG11','PROG20') and village_id  not in('7163','13777','13781','13817','13853','13857','13858','13859','14571','10817','10845','7169','7247','10668','10694','10818','10822','10837','11059','11073')";
				System.out.println("SWAP-NON IDA");

				program_id = "  program_id  in('PROG06','PROG11','PROG20')";

				// prog_id="  prog_id in ('PROG06','PROG11','PROG20') and village_id  not in('7163','13777','13781','13817','13853','13857','13858','13859','14571','10817','10845','7169','7247','10668','10694','10818','10822','10837','11059','11073')";
				prog_id = "  prog_id  in('PROG06','PROG11','PROG20')";

				// where=where+" and swap_nonswap='SWAP-NON IDA' and program_id<>'PROG15'";
			} else if (swap2.equals("SWAP-IDA")) {
				// where=where+" and swap_nonswap='SWAP-IDA' and program_id='PROG15'";
				// program_id="  program_id='PROG15' and village_id  not in('7163','13777','13781','13817','13853','13857','13858','13859','14571','10817','10845','7169','7247','10668','10694','10818','10822','10837','11059','11073')";
				System.out.println("SWAP-IDA");

				program_id = "  program_id='PROG15'";

				// prog_id="  prog_id='PROG15' and village_id  not in('7163','13777','13781','13817','13853','13857','13858','13859','14571','10817','10845','7169','7247','10668','10694','10818','10822','10837','11059','11073')";
				prog_id = "  prog_id='PROG15'";

			} else if (swap2.equals("IBRD")) {
				prog_id = "  prog_id='PROG30'";

				program_id = "  program_id='PROG30'";
				// where=where+" and swap_nonswap='IBRD' and program_id='PROG30'";
			}

		}
		if (selectReport.equals("PMMRPT001_21_all")) {
			if (swap2.equals("NONSWAP")) {
				// where=where+" and swap_nonswap='NONSWAP' and program_id<>'PROG15'";
				// where=where+" and  prog_id<>'PROG15'";
				prog_id = "  prog_id not in('PROG15','PROG06','PROG11','PROG20')";

			} else if (swap2.equals("SWAP-NON IDA")) {
				// where=where+" and swap_nonswap='SWAP-NON IDA' and program_id<>'PROG15'";
				prog_id = "  prog_id in('PROG06','PROG11','PROG20')";
			} else if (swap2.equals("SWAP-IDA")) {
				// where=where+" and swap_nonswap='SWAP-IDA' and program_id='PROG15'";
				prog_id = "  prog_id='PROG15'";

			} else if (swap2.equals("IBRD")) {
				prog_id = "  prog_id='PROG30'";
				// program_id="  program_id='PROG30'";
				// where=where+" and swap_nonswap='IBRD' and program_id='PROG30'";
			}
		}

		if (selectReport.equals("PMMRPT001_21_all_const")
				|| selectReport.equals("PMMRPT001_21_const")) {
			// System.out.println("swap: "+swap2);
			if (swap2.equals("NONSWAP")) {
				where = where
						+ " and swap_nonswap='NONSWAP' and program_id<>'PROG15'";
				// prog_id="  prog_id not in('PROG15','PROG06','PROG11','PROG20')";

			} else if (swap2.equals("SWAP-NON IDA")) {
				where = where
						+ " and swap_nonswap='SWAP-NON IDA' and program_id<>'PROG15'";
				// prog_id="  prog_id in('PROG15','PROG06','PROG11','PROG20')";

			} else if (swap2.equals("SWAP-IDA")) {
				where = where
						+ " and swap_nonswap='SWAP-IDA' and program_id='PROG15'";
				// prog_id="  prog_id='PROG15'";

			} else if (swap2.equals("IBRD")) {
				// program_id="  program_id='PROG30'";
				where = where
						+ " and swap_nonswap='IBRD' and program_id='PROG30'";
			}
		}
		if (selectReport.equals("PMMRPT001_27")
				|| selectReport.equals("PMMRPT001_17")
				|| selectReport.equals("PMMRPT001_28P1")
				|| selectReport.equals("PMMRPT001_28P2")
				|| selectReport.equals("PMMRPT001_28P3")) {
			System.out.println("swap: " + swap);
			if (swap.equals("NONSWAP")) {
				where = where
						+ " and swap_nonswap='NONSWAP' and program_id<>'PROG15'";
			} else if (swap.equals("SWAP-NON IDA")) {
				where = where
						+ " and swap_nonswap='SWAP-NON IDA' and program_id<>'PROG15'";
			} else if (swap.equals("SWAP-IDA")) {
				where = where
						+ " and swap_nonswap='SWAP-IDA' and program_id='PROG15'";
			} else if (swap.equals("IBRD")) {
				// program_id="  program_id='PROG30'";
				where = where
						+ " and swap_nonswap='IBRD' and program_id='PROG30'";
			}
		}

		// rohit 22 Nov start
		if (selectReport.equals("PMMRPT001_33P1")
				|| selectReport.equals("PMMRPT001_33P2")) {
			System.out.println("swap: " + swap);
			if (swap.equals("NONSWAP")) {
				where = where + " and swap_nonswap='NONSWAP'";
			} else if (swap.equals("SWAP-NON IDA")) {
				where = where + " and swap_nonswap='SWAP-NON IDA'";
			} else if (swap.equals("SWAP-IDA")) {
				where = where + " and swap_nonswap='SWAP-IDA'";
			} else if (swap.equals("IBRD")) {
				// program_id="  program_id='PROG30'";
				where = where + " and swap_nonswap='IBRD'";
			}
		}
		// rohit 22 Nov end

		/*
		 * if(selectReport.equals("PMMRPT001_24_deatils")){
		 * System.out.println("swap: "+swap); if(swap4.equals("NONSWAP")){
		 * where=where+" and swap_nonswap='NONSWAP'"; }else
		 * if(swap.equals("SWAP-NON IDA")){
		 * where=where+" and swap_nonswap='SWAP-NON IDA'"; }else
		 * if(swap.equals("SWAP-IDA")){
		 * where=where+" and swap_nonswap='SWAP-IDA'"; } }
		 */

		if (selectReport.equals("PMMRPT001_34")
				|| selectReport.equals("PMMRPT001_34_dtl")) {
			System.out.println(" inside 34 and 34 dtl----------------- "
					+ swap2);
			if (swap2.equals("NONSWAP")) {
				where = where + " and swap_nonswap='NONSWAP'";
			} else if (swap2.equals("SWAP-NON IDA")) {
				where = where + " and swap_nonswap='SWAP-NON IDA'";
			} else if (swap2.equals("SWAP-IDA")) {
				where = where + " and swap_nonswap='SWAP-IDA'";
			} else if (swap2.equals("IBRD")) {
				// program_id="  program_id='PROG30'";
				where = where + " and swap_nonswap='IBRD'";
			}
		}
		if (selectReport.equals("PMMRPT001_G4_abs")) {
			System.out.println("swap: " + swap3);
			if (swap3.equals("NONSWAP")) {
				swap5 = "  program_id not in('PROG15','PROG06','PROG11','PROG20')";
			} else if (swap3.equals("SWAP-NON IDA")) {
				System.out.println("inside swap non ida");
				swap5 = "  program_id in('PROG06','PROG11','PROG20')";
			} else if (swap3.equals("SWAP-IDA")) {
				swap5 = "  program_id='PROG15'";
			} else if (swap3.equals("IBRD")) {
				swap5 = "  program_id='PROG30'";
				// where=where+" and swap_nonswap='IBRD' and program_id='PROG30'";
			}
		}
		if (selectReport.equals("PMMRPT001_G5_det")) {
			System.out.println("swap: " + swap3);
			if (swap3.equals("NONSWAP")) {
				swap5 = "  program_id not in('PROG15','PROG06','PROG11','PROG20')";

				// where=where+" and swap_nonswap='NONSWAP' ";
			} else if (swap3.equals("SWAP-NON IDA")) {
				swap5 = "  program_id in('PROG06','PROG11','PROG20')";

				// where=where+" and swap_nonswap='SWAP-NON IDA' ";
			} else if (swap3.equals("SWAP-IDA")) {
				swap5 = "  program_id='PROG15'";

				// where=where+" and swap_nonswap='SWAP-IDA' ";
			} else if (swap3.equals("IBRD")) {
				swap5 = "  program_id='PROG30'";
				// where=where+" and swap_nonswap='IBRD' and program_id='PROG30'";
			}
		}
		if (selectReport.equals("PMMRPT001_35")) {
			System.out.println("status :" + status);
			if (status.equals("Open")) {
				where = where + " and status = 'Open'";
			} else if (status.equals("Close")) {
				where = where + " and status = 'Close'";
			}
		}
		if (schemeType.equals("SEW"))
			schemeType = "ss.scheme_id like 'SEW%'";

		else if (schemeType.equals("WS"))
			schemeType = "ss.scheme_id not like 'SEW%' and ss.scheme_id not like 'IMP%'";
		else if (schemeType.equals("IMP"))
			schemeType = "ss.scheme_id like 'IMP%'";

		if (selectReport.equals("PMMRPT001_32")) {
			Date finDate = MisUtility.convertStringToDate(toPeriod);
			SimpleDateFormat sdf;
			SimpleDateFormat sdf1;
			System.out.println("-----------Date : " + finDate);
			sdf = new SimpleDateFormat("MM");
			sdf1 = new SimpleDateFormat("yyyy");
			month = String.valueOf(sdf.format(finDate));
			finYearId = String.valueOf(sdf1.format(finDate));
		}

		System.out.println("circle1----------------->" + circle1);
		parameters.put("sv_mv", sv_mv);

		parameters.put("zone", zone);
		parameters.put("circle", circle);
		parameters.put("division", division1);
		parameters.put("where", where);
		parameters.put("scheme_source", scheme_source);
		parameters.put("scheme_source2", scheme_source2);
		parameters.put("scheme_source1", scheme_source1);
		parameters.put("source_of_scheme", source_of_scheme);
		parameters.put("month", month);
		parameters.put("finYearId", finYearId);
		parameters.put("from", from);
		parameters.put("to", to);
		parameters.put("swap", swap);
		parameters.put("swap1", swap1);
		parameters.put("swap2", swap2);
		parameters.put("swap3", swap3);
		parameters.put("swap4", swap4);
		parameters.put("swap5", swap5);
		parameters.put("schemeType", schemeType);
		parameters.put("from_date", from_date);
		parameters.put("to_date", to_date);
		parameters.put("status", status);
		parameters.put("innerWhere", innerWhere);
		parameters.put("program_id", program_id);
		parameters.put("program_id2", program_id2);
		parameters.put("prog_id", prog_id);
		parameters.put("plan_id", plan_id);
		parameters.put("village_category", village_category);
		parameters.put("phase1", phase1);
		parameters.put("mv_sv", mv_sv);
		parameters.put("Plan_id", Plan_id);
		parameters.put("year", year);
		parameters.put("circle", circle1);
		parameters.put("category", category);
		parameters.put("top_category", top_category);

		System.out.println("swap5" + swap5);
		System.out.println("year===" + year);
		System.out.println("Parameters: " + parameters.size() + ":"
				+ parameters.toString());
		System.out.println("selectReport: " + selectReport);
		System.out.println("Action: where : " + where);
		System.out.println("Action: innerWhere : " + innerWhere);
		System.out.println("program_id2===" + program_id2);
		System.out.println("source_scheme2===" + scheme_source2);

	}

	private String getLocationNameById(String locationId) {
		Set<LocationBean> locationBeans = null;
		String locationName = null;
		try {

			locationBeans = locationDao.getLocationNameById(locationId);
			for (LocationBean locationBean2 : locationBeans) {
				locationName = locationBean2.getLocationName();
			}
		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		return locationName;
	}

	@SuppressWarnings("unused")
	private void refreshEstimatesAwardContractsReportForm(
			EstimatesAwardContractsReportForm estimatesAwardContractsReportForm) {
		estimatesAwardContractsReportForm.setSelectZone(null);
		estimatesAwardContractsReportForm.setSelectCircle(null);
		estimatesAwardContractsReportForm.setSelectDistrict(null);
		estimatesAwardContractsReportForm.setSelectProgram(null);
		estimatesAwardContractsReportForm.setApprovalStatus(null);
		estimatesAwardContractsReportForm.setAsOnDate(null);
	}

	public ActionForward fetchDivisions(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		Set<LocationBean> locationBeans = null;
		StringBuilder buffer = new StringBuilder();
		List<String> locationTypeList = new ArrayList<String>();
		locationTypeList.add("SPMC");
		locationTypeList.add("DO");
		locationTypeList.add("DPMC");

		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		// statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		try {
			if (MisUtility.ifEmpty(request.getParameter("districtId"))) {
				locationBeans = locationDao.getChildLocationIds(
						request.getParameter("districtId"), locationTypeList);
				for (LocationBean locationBean : locationBeans) {
					buffer.append("<option value=\"")
							.append(locationBean.getLocationId()).append("\">");
					buffer.append(locationBean.getLocationName() + " - ("
							+ locationBean.getLocationId() + ")");
					buffer.append("</option>");
				}
			}
			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}

		return null;
	}

	public ActionForward fetchBlock(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {
		Set<LocationBean> locationBeans = null;
		StringBuilder buffer = new StringBuilder();
		List<String> locationTypeList = new ArrayList<String>();
		locationTypeList.add("BLOCK");
		// locationTypeList.add("DO");
		// locationTypeList.add("DPMC");

		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		// statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		try {
			if (MisUtility.ifEmpty(request.getParameter("districtId"))) {
				locationBeans = locationDao.getChildLocationIds(
						request.getParameter("districtId"), locationTypeList);
				for (LocationBean locationBean : locationBeans) {
					buffer.append("<option value=\"")
							.append(locationBean.getLocationId()).append("\">");
					buffer.append(locationBean.getLocationName() + " - ("
							+ locationBean.getLocationId() + ")");
					buffer.append("</option>");
				}
			}
			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}

		return null;
	}

	public ActionForward fetchConstituency(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		Set<ConstituencyBean> constituencyBeans = null;
		StringBuilder buffer = new StringBuilder();
		// locationTypeList.add("DO");
		// locationTypeList.add("DPMC");

		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		// statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		try {
			if (MisUtility.ifEmpty(request.getParameter("districtId"))) {
				constituencyBeans = constituencyDao.findConstituency(request
						.getParameter("districtId"));
				for (ConstituencyBean bean : constituencyBeans) {
					buffer.append("<option value=\"")
							.append(bean.getConstituencyId()).append("\">");
					buffer.append(bean.getConstituencyName() + " - ("
							+ bean.getConstituencyId() + ")");
					buffer.append("</option>");
				}
			}
			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
		} catch (DataAccessException e) {
			log.error(e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}

		return null;
	}
}