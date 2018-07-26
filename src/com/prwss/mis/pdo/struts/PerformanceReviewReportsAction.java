package com.prwss.mis.pdo.struts;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.swing.tree.RowMapper;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.prwss.mis.common.MISJdcDaoImpl;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;

public class PerformanceReviewReportsAction extends DispatchAction {

	

	private String query = null;
	private String query2 = null;
	private String query3 = null;
	private String query4 = null;
	private String query5 = null;
	private String query6 = null;
	List<WaterSupplyCoverageStatusModel> getTableList = null;
	List<reportSection2n3Modal> getSection2and3 = null;
	List<reportSection4Modal> getSection4 = null;   
	List<reportSection5Modal> getSection5 = null;
	List<reportSection6Modal> getSection6 = null;
	List<reportSection7Modal> getSection7 = null;

	private static DataSource dataSource;

	public void view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws MISException {

	}

	public ActionForward fileExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PerformanceReviewReportsForm performanceform = (PerformanceReviewReportsForm) form;

		showvalues(performanceform);

		// If Every Thing is A
		if (performanceform.getSelectZone().equalsIgnoreCase("A")
				&& performanceform.getSelectCircle().equalsIgnoreCase("A")
				&& performanceform.getSelectDistrict().equalsIgnoreCase("A")
				&& performanceform.getSelectDivision().equalsIgnoreCase("A")
				&& performanceform.getSelectsubDivision().equalsIgnoreCase("A")) {
			System.out.println("Every thing is A .. No Worries");
			query = "SELECT * FROM prwss_main.water_spl_new";
			query2 = "select count(distinct TOT_SCH) as TOT_SCH,count(distinct ADMIN_SCH) as ADMIN_SCH,count(distinct PROC_SCH) as PROC_SCH,count(distinct COMP_SCH) as COMP_SCH,count(distinct ADMIN_SCH_1A) as ADMIN_SCH_1A,count(distinct PROC_SCH_1A) as PROC_SCH_1A,count(distinct COMP_SCH_1A) as COMP_SCH_1A,count(distinct ADMIN_SCH_2A) as ADMIN_SCH_2A,count(distinct PROC_SCH_2A) as PROC_SCH_2A,count(distinct COMP_SCH_2A) as COMP_SCH_2A,count(distinct ADMIN_SCH_NRDWP) as ADMIN_SCH_NRDWP,count(distinct PROC_SCH_NRDWP) as PROC_SCH_NRDWP,count(distinct COMP_SCH_NRDWP) as COMP_SCH_NRDWP,count(distinct ADMIN_SCH_OTHR) as ADMIN_SCH_OTHR,count(distinct PROC_SCH_OTHR) as PROC_SCH_OTHR,count(distinct COMP_SCH_OTHR) as COMP_SCH_OTHR,count(distinct ADMIN_SCH_3A) as ADMIN_SCH_3A,count(distinct PROC_SCH_3A) as PROC_SCH_3A,count(distinct COMP_SCH_3A) as COMP_SCH_3A,count(distinct ADMIN_SCH_3B) as ADMIN_SCH_3B,count(distinct PROC_SCH_3B) as PROC_SCH_3B,count(distinct COMP_SCH_3B) as COMP_SCH_3B,count(distinct ADMIN_SCH_OTHR3) as ADMIN_SCH_OTHR3,count(distinct PROC_SCH_OTHR3) as PROC_SCH_OTHR3,count(distinct COMP_SCH_OTHR3) as COMP_SCH_OTHR3 from prwss_main.report2and3";
			query3 = "select count(distinct gpwsc_sch) gpwsc_sch,count(distinct gpwsc_vill) gpwsc_vill,count(distinct dwss_sch) dwss_sch,count(distinct dwss_vill) dwss_vill, count(distinct hrs_vill) hrs_vill,count(distinct conn_vill) conn_vill,count(distinct lpcd_vill) lpcd_vill from prwss_main.report4";
			query4 = "SELECT COUNT(DISTINCT ROPLANT) AS ROPLANT ,COUNT(DISTINCT is_ro_functional) AS is_ro_functional FROM prwss_main.report5";
			query5 = "SELECT count(distinct aluminium) aluminium,count(distinct lead) lead,count(distinct selenium) selenium,count(distinct chromium) chromium, count(distinct mercury) mercury, count(distinct arsenic) arsenic,count(distinct cadium) cadium, count(distinct nikel) nikel, count(distinct tds) tds, count(distinct flouride) flouride,count(distinct nitrate) nitrate, count(distinct iron) iron, count(distinct failed_phase) failed_phase,count(distinct uranium_safe) uranium_safe, count(distinct uranium_failed_revised) uranium_failed_revised,count(distinct uranimum_failed_aerb) uranimum_failed_aerb, count(distinct uranimum_failed_conc) uranimum_failed_conc,sum(is_ro_functional) is_ro_functional,count(distinct no_of_failed_parameters_1) no_of_failed_parameters_1,count(distinct no_of_failed_parameters_2) no_of_failed_parameters_2 from prwss_main.report6 ";
			query6 = "select count(distinct scheme_id) scheme,count(distinct village) village,sum(households) households,sum(sew_connection)  sew_connection,sum(connection_percent)  connection_percent from prwss_main.report7";
			getTableList = getWaterSupplyCoverageStatus(performanceform, query);
			getSection2and3 = getdataforsection2and3(performanceform, query2);
			getSection4 = getdataforsection4(performanceform, query3);
			getSection5 = getdataforsection5(performanceform,query4);
			getSection6 = getdataforsection6(performanceform,query5);
			getSection7 = getdataforsection7(performanceform,query6);
			request.setAttribute("list", getTableList);
			request.setAttribute("list23", getSection2and3);
			request.setAttribute("list4", getSection4);
			request.setAttribute("list5", getSection5);
			request.setAttribute("list6", getSection6);
			request.setAttribute("list7", getSection7);
			request.setAttribute("zone", performanceform.getZoneId());
			request.setAttribute("circle", performanceform.getCircleId());
			request.setAttribute("district", performanceform.getDistrict_Id());
			request.setAttribute("division", performanceform.getDivisionId());
			request.setAttribute("subdivision",
					performanceform.getSubdivisonId());
			refresh(performanceform);
			return mapping.findForward("display");

		}
		// If Zone is Selected and Everything is A
		else if (performanceform.getSelectZone().equalsIgnoreCase("S")
				&& performanceform.getSelectCircle().equalsIgnoreCase("A")
				&& performanceform.getSelectDistrict().equalsIgnoreCase("A")
				&& performanceform.getSelectDivision().equalsIgnoreCase("A")
				&& performanceform.getSelectsubDivision().equalsIgnoreCase("A")) {
			System.out.println("Zone is Selected .. No Worries");
			if (MisUtility.ifEmpty(performanceform.getZoneId())) {
				System.out.println(performanceform.getZoneId());
				query = "SELECT count(village_id) AS total_village, sum(total_population) AS population_2011, sum(households) AS households_2011 ,count(village_bechirag) village_bechirag,count(village_own) village_own,count(fc_pws) fc_pws,count(fc_hp) fc_hp,count(fc_own) fc_own,count(pc_pws) pc_pws ,count(pc_hp) pc_hp,count(pc_own) pc_own,count(nc_pws) nc_pws,count(distinct piped_sch) piped_sch ,count(distinct piped_village) piped_village ,count(distinct hp_sch) hp_sch ,count(distinct hp_village) hp_village ,count(distinct pws_connection) pws_connection from prwss_main.water_spl_new1 WHERE zone_id = :zone_id ";
				query2 = "select count(distinct TOT_SCH) as TOT_SCH,count(distinct ADMIN_SCH) as ADMIN_SCH,count(distinct PROC_SCH) as PROC_SCH,count(distinct COMP_SCH) as COMP_SCH,count(distinct ADMIN_SCH_1A) as ADMIN_SCH_1A,count(distinct PROC_SCH_1A) as PROC_SCH_1A,count(distinct COMP_SCH_1A) as COMP_SCH_1A,count(distinct ADMIN_SCH_2A) as ADMIN_SCH_2A,count(distinct PROC_SCH_2A) as PROC_SCH_2A,count(distinct COMP_SCH_2A) as COMP_SCH_2A,count(distinct ADMIN_SCH_NRDWP) as ADMIN_SCH_NRDWP,count(distinct PROC_SCH_NRDWP) as PROC_SCH_NRDWP,count(distinct COMP_SCH_NRDWP) as COMP_SCH_NRDWP,count(distinct ADMIN_SCH_OTHR) as ADMIN_SCH_OTHR,count(distinct PROC_SCH_OTHR) as PROC_SCH_OTHR,count(distinct COMP_SCH_OTHR) as COMP_SCH_OTHR,count(distinct ADMIN_SCH_3A) as ADMIN_SCH_3A,count(distinct PROC_SCH_3A) as PROC_SCH_3A,count(distinct COMP_SCH_3A) as COMP_SCH_3A,count(distinct ADMIN_SCH_3B) as ADMIN_SCH_3B,count(distinct PROC_SCH_3B) as PROC_SCH_3B,count(distinct COMP_SCH_3B) as COMP_SCH_3B,count(distinct ADMIN_SCH_OTHR3) as ADMIN_SCH_OTHR3,count(distinct PROC_SCH_OTHR3) as PROC_SCH_OTHR3,count(distinct COMP_SCH_OTHR3) as COMP_SCH_OTHR3 from prwss_main.report2and3 WHERE zone_id = :zone_id ";
				query3 = "select count(distinct gpwsc_sch) gpwsc_sch,count(distinct gpwsc_vill) gpwsc_vill,count(distinct dwss_sch) dwss_sch,count(distinct dwss_vill) dwss_vill, count(distinct hrs_vill) hrs_vill,count(distinct conn_vill) conn_vill,count(distinct lpcd_vill) lpcd_vill from prwss_main.report4 WHERE zone_id = :zone_id ";
				query4 = "SELECT COUNT(DISTINCT ROPLANT) AS ROPLANT ,COUNT(DISTINCT is_ro_functional) AS is_ro_functional FROM prwss_main.report5 WHERE zone_id = :zone_id ";
				query5 = "SELECT count(distinct aluminium) aluminium,count(distinct lead) lead,count(distinct selenium) selenium,count(distinct chromium) chromium, count(distinct mercury) mercury, count(distinct arsenic) arsenic,count(distinct cadium) cadium, count(distinct nikel) nikel, count(distinct tds) tds, count(distinct flouride) flouride,count(distinct nitrate) nitrate, count(distinct iron) iron, count(distinct failed_phase) failed_phase,count(distinct uranium_safe) uranium_safe, count(distinct uranium_failed_revised) uranium_failed_revised,count(distinct uranimum_failed_aerb) uranimum_failed_aerb, count(distinct uranimum_failed_conc) uranimum_failed_conc,sum(is_ro_functional) is_ro_functional,count(distinct no_of_failed_parameters_1) no_of_failed_parameters_1,count(distinct no_of_failed_parameters_2) no_of_failed_parameters_2 from prwss_main.report6 WHERE zone_id = :zone_id";
				
				getTableList = getWaterSupplyCoverageStatus(performanceform,query);
				getSection2and3 = getdataforsection2and3(performanceform, query2);
				getSection4 = getdataforsection4(performanceform, query3);
				getSection5 = getdataforsection5(performanceform,query4);
				getSection6 = getdataforsection6(performanceform,query5);
				request.setAttribute("list", getTableList);
				request.setAttribute("list23", getSection2and3);
				request.setAttribute("list4", getSection4);
				request.setAttribute("list5", getSection5);
				request.setAttribute("list6", getSection6);
				request.setAttribute("zone", performanceform.getZoneId());
				request.setAttribute("circle", performanceform.getCircleId());
				request.setAttribute("district",
						performanceform.getDistrict_Id());
				request.setAttribute("division",
						performanceform.getDivisionId());
				request.setAttribute("subdivision",
						performanceform.getSubdivisonId());
				refresh(performanceform);
				return mapping.findForward("display");
			} else {
				System.out.println("Zone Id is Empty!!");
				return mapping.findForward("cScreen");
			}
		}
		// If Zone is Selected and circle is Selected
		else if (performanceform.getSelectZone().equalsIgnoreCase("S")
				&& performanceform.getSelectCircle().equalsIgnoreCase("S")
				&& performanceform.getSelectDistrict().equalsIgnoreCase("A")
				&& performanceform.getSelectDivision().equalsIgnoreCase("A")
				&& performanceform.getSelectsubDivision().equalsIgnoreCase("A")) {
			System.out.println("Zone and Circle is Selected.. No Worries");
			if (MisUtility.ifEmpty(performanceform.getZoneId())
					&& MisUtility.ifEmpty(performanceform.getCircleId())) {
				System.out.println(performanceform.getZoneId());
				System.out.println(performanceform.getCircleId());
				query = "SELECT count(village_id) AS total_village, sum(total_population) AS population_2011, sum(households) AS households_2011 ,count(village_bechirag) village_bechirag,count(village_own) village_own,count(fc_pws) fc_pws,count(fc_hp) fc_hp,count(fc_own) fc_own,count(pc_pws) pc_pws ,count(pc_hp) pc_hp,count(pc_own) pc_own,count(nc_pws) nc_pws,count(distinct piped_sch) piped_sch ,count(distinct piped_village) piped_village ,count(distinct hp_sch) hp_sch ,count(distinct hp_village) hp_village ,count(distinct pws_connection) pws_connection from prwss_main.water_spl_new1 WHERE zone_id = :zone_id AND circle_id = :circle_id ";
				query2 = "select count(distinct TOT_SCH) as TOT_SCH,count(distinct ADMIN_SCH) as ADMIN_SCH,count(distinct PROC_SCH) as PROC_SCH,count(distinct COMP_SCH) as COMP_SCH,count(distinct ADMIN_SCH_1A) as ADMIN_SCH_1A,count(distinct PROC_SCH_1A) as PROC_SCH_1A,count(distinct COMP_SCH_1A) as COMP_SCH_1A,count(distinct ADMIN_SCH_2A) as ADMIN_SCH_2A,count(distinct PROC_SCH_2A) as PROC_SCH_2A,count(distinct COMP_SCH_2A) as COMP_SCH_2A,count(distinct ADMIN_SCH_NRDWP) as ADMIN_SCH_NRDWP,count(distinct PROC_SCH_NRDWP) as PROC_SCH_NRDWP,count(distinct COMP_SCH_NRDWP) as COMP_SCH_NRDWP,count(distinct ADMIN_SCH_OTHR) as ADMIN_SCH_OTHR,count(distinct PROC_SCH_OTHR) as PROC_SCH_OTHR,count(distinct COMP_SCH_OTHR) as COMP_SCH_OTHR,count(distinct ADMIN_SCH_3A) as ADMIN_SCH_3A,count(distinct PROC_SCH_3A) as PROC_SCH_3A,count(distinct COMP_SCH_3A) as COMP_SCH_3A,count(distinct ADMIN_SCH_3B) as ADMIN_SCH_3B,count(distinct PROC_SCH_3B) as PROC_SCH_3B,count(distinct COMP_SCH_3B) as COMP_SCH_3B,count(distinct ADMIN_SCH_OTHR3) as ADMIN_SCH_OTHR3,count(distinct PROC_SCH_OTHR3) as PROC_SCH_OTHR3,count(distinct COMP_SCH_OTHR3) as COMP_SCH_OTHR3 from prwss_main.report2and3 WHERE zone_id = :zone_id AND circle_id = :circle_id ";
				query3 = "select count(distinct gpwsc_sch) gpwsc_sch,count(distinct gpwsc_vill) gpwsc_vill,count(distinct dwss_sch) dwss_sch,count(distinct dwss_vill) dwss_vill, count(distinct hrs_vill) hrs_vill,count(distinct conn_vill) conn_vill,count(distinct lpcd_vill) lpcd_vill from prwss_main.report4 WHERE zone_id = :zone_id AND circle_id = :circle_id ";
				query4 = "SELECT COUNT(DISTINCT ROPLANT) AS ROPLANT ,COUNT(DISTINCT is_ro_functional) AS is_ro_functional FROM prwss_main.report5 WHERE zone_id = :zone_id AND circle_id = :circle_id ";
				query5 = "SELECT count(distinct aluminium) aluminium,count(distinct lead) lead,count(distinct selenium) selenium,count(distinct chromium) chromium, count(distinct mercury) mercury, count(distinct arsenic) arsenic,count(distinct cadium) cadium, count(distinct nikel) nikel, count(distinct tds) tds, count(distinct flouride) flouride,count(distinct nitrate) nitrate, count(distinct iron) iron, count(distinct failed_phase) failed_phase,count(distinct uranium_safe) uranium_safe, count(distinct uranium_failed_revised) uranium_failed_revised,count(distinct uranimum_failed_aerb) uranimum_failed_aerb, count(distinct uranimum_failed_conc) uranimum_failed_conc,sum(is_ro_functional) is_ro_functional,count(distinct no_of_failed_parameters_1) no_of_failed_parameters_1,count(distinct no_of_failed_parameters_2) no_of_failed_parameters_2 from prwss_main.report6 WHERE zone_id = :zone_id AND circle_id = :circle_id ";
				
				getTableList = getWaterSupplyCoverageStatus(performanceform,query);
				getSection2and3 = getdataforsection2and3(performanceform, query2);
				getSection4 = getdataforsection4(performanceform, query3);
				getSection5 = getdataforsection5(performanceform,query4);
				getSection6 = getdataforsection6(performanceform,query5);
				request.setAttribute("list", getTableList);
				request.setAttribute("list23", getSection2and3);
				request.setAttribute("list4", getSection4);
				request.setAttribute("list5", getSection5);
				request.setAttribute("list6", getSection6);
				request.setAttribute("zone", performanceform.getZoneId());
				request.setAttribute("circle", performanceform.getCircleId());
				request.setAttribute("district",
						performanceform.getDistrict_Id());
				request.setAttribute("division",
						performanceform.getDivisionId());
				request.setAttribute("subdivision",
						performanceform.getSubdivisonId());
				refresh(performanceform);
				return mapping.findForward("display");
			} else {
				System.out.println("Zone Id is Empty!!");
				System.out.println("Circle ID is Empty!!");
				return mapping.findForward("cScreen");
			}
		}

		// If Zone ,circle , District is Selected
		else if (performanceform.getSelectZone().equalsIgnoreCase("S")
				&& performanceform.getSelectCircle().equalsIgnoreCase("S")
				&& performanceform.getSelectDistrict().equalsIgnoreCase("S")
				&& performanceform.getSelectDivision().equalsIgnoreCase("A")
				&& performanceform.getSelectsubDivision().equalsIgnoreCase("A")) {
			System.out
					.println("Zone , Circle and District is Selected.. No Worries");
			if (MisUtility.ifEmpty(performanceform.getZoneId())
					&& MisUtility.ifEmpty(performanceform.getCircleId())
					&& MisUtility.ifEmpty(performanceform.getDistrict_Id())) {
				System.out.println(performanceform.getZoneId());
				System.out.println(performanceform.getCircleId());
				System.out.println(performanceform.getDistrict_Id());
				query = "SELECT count(village_id) AS total_village, sum(total_population) AS population_2011, sum(households) AS households_2011 ,count(village_bechirag) village_bechirag,count(village_own) village_own,count(fc_pws) fc_pws,count(fc_hp) fc_hp,count(fc_own) fc_own,count(pc_pws) pc_pws ,count(pc_hp) pc_hp,count(pc_own) pc_own,count(nc_pws) nc_pws,count(distinct piped_sch) piped_sch ,count(distinct piped_village) piped_village ,count(distinct hp_sch) hp_sch ,count(distinct hp_village) hp_village ,count(distinct pws_connection) pws_connection from prwss_main.water_spl_new1 WHERE zone_id = :zone_id AND circle_id = :circle_id AND district_id= :district_id ";
				query2 = "select count(distinct TOT_SCH) as TOT_SCH,count(distinct ADMIN_SCH) as ADMIN_SCH,count(distinct PROC_SCH) as PROC_SCH,count(distinct COMP_SCH) as COMP_SCH,count(distinct ADMIN_SCH_1A) as ADMIN_SCH_1A,count(distinct PROC_SCH_1A) as PROC_SCH_1A,count(distinct COMP_SCH_1A) as COMP_SCH_1A,count(distinct ADMIN_SCH_2A) as ADMIN_SCH_2A,count(distinct PROC_SCH_2A) as PROC_SCH_2A,count(distinct COMP_SCH_2A) as COMP_SCH_2A,count(distinct ADMIN_SCH_NRDWP) as ADMIN_SCH_NRDWP,count(distinct PROC_SCH_NRDWP) as PROC_SCH_NRDWP,count(distinct COMP_SCH_NRDWP) as COMP_SCH_NRDWP,count(distinct ADMIN_SCH_OTHR) as ADMIN_SCH_OTHR,count(distinct PROC_SCH_OTHR) as PROC_SCH_OTHR,count(distinct COMP_SCH_OTHR) as COMP_SCH_OTHR,count(distinct ADMIN_SCH_3A) as ADMIN_SCH_3A,count(distinct PROC_SCH_3A) as PROC_SCH_3A,count(distinct COMP_SCH_3A) as COMP_SCH_3A,count(distinct ADMIN_SCH_3B) as ADMIN_SCH_3B,count(distinct PROC_SCH_3B) as PROC_SCH_3B,count(distinct COMP_SCH_3B) as COMP_SCH_3B,count(distinct ADMIN_SCH_OTHR3) as ADMIN_SCH_OTHR3,count(distinct PROC_SCH_OTHR3) as PROC_SCH_OTHR3,count(distinct COMP_SCH_OTHR3) as COMP_SCH_OTHR3 from prwss_main.report2and3 WHERE zone_id = :zone_id AND circle_id = :circle_id AND district_id= :district_id ";
				query3 = "select count(distinct gpwsc_sch) gpwsc_sch,count(distinct gpwsc_vill) gpwsc_vill,count(distinct dwss_sch) dwss_sch,count(distinct dwss_vill) dwss_vill, count(distinct hrs_vill) hrs_vill,count(distinct conn_vill) conn_vill,count(distinct lpcd_vill) lpcd_vill from prwss_main.report4 WHERE zone_id = :zone_id AND circle_id = :circle_id AND district_id= :district_id ";
				query4 = "SELECT COUNT(DISTINCT ROPLANT) AS ROPLANT ,COUNT(DISTINCT is_ro_functional) AS is_ro_functional FROM prwss_main.report5 WHERE zone_id = :zone_id AND circle_id = :circle_id AND district_id= :district_id ";
				query5 = "SELECT count(distinct aluminium) aluminium,count(distinct lead) lead,count(distinct selenium) selenium,count(distinct chromium) chromium, count(distinct mercury) mercury, count(distinct arsenic) arsenic,count(distinct cadium) cadium, count(distinct nikel) nikel, count(distinct tds) tds, count(distinct flouride) flouride,count(distinct nitrate) nitrate, count(distinct iron) iron, count(distinct failed_phase) failed_phase,count(distinct uranium_safe) uranium_safe, count(distinct uranium_failed_revised) uranium_failed_revised,count(distinct uranimum_failed_aerb) uranimum_failed_aerb, count(distinct uranimum_failed_conc) uranimum_failed_conc,sum(is_ro_functional) is_ro_functional,count(distinct no_of_failed_parameters_1) no_of_failed_parameters_1,count(distinct no_of_failed_parameters_2) no_of_failed_parameters_2 from prwss_main.report6 WHERE zone_id = :zone_id AND circle_id = :circle_id AND district_id= :district_id ";
				
				getTableList = getWaterSupplyCoverageStatus(performanceform,query);
				getSection2and3 = getdataforsection2and3(performanceform, query2);
				getSection4 = getdataforsection4(performanceform, query3);
				getSection5 = getdataforsection5(performanceform,query4);
				getSection6 = getdataforsection6(performanceform,query5);
				request.setAttribute("list", getTableList);
				request.setAttribute("list23", getSection2and3);
				request.setAttribute("list4", getSection4);
				request.setAttribute("list5", getSection5);
				request.setAttribute("list6", getSection6);
				request.setAttribute("zone", performanceform.getZoneId());
				request.setAttribute("circle", performanceform.getCircleId());
				request.setAttribute("district",
						performanceform.getDistrict_Id());
				request.setAttribute("division",
						performanceform.getDivisionId());
				request.setAttribute("subdivision",
						performanceform.getSubdivisonId());
				refresh(performanceform);
				return mapping.findForward("display");
			} else {
				System.out.println("Zone Id is Empty!!");
				System.out.println("Circle ID is Empty!!");
				System.out.println("District ID is Empty!!");
				return mapping.findForward("cScreen");
			}
		}

		// If Zone ,circle , District ,Divisonal is Selected
		else if (performanceform.getSelectZone().equalsIgnoreCase("S")
				&& performanceform.getSelectCircle().equalsIgnoreCase("S")
				&& performanceform.getSelectDistrict().equalsIgnoreCase("S")
				&& performanceform.getSelectDivision().equalsIgnoreCase("S")
				&& performanceform.getSelectsubDivision().equalsIgnoreCase("A")) {
			System.out
					.println("Zone , Circle , District and Divisonal is Selected.. No Worries");
			if (MisUtility.ifEmpty(performanceform.getZoneId())
					&& MisUtility.ifEmpty(performanceform.getCircleId())
					&& MisUtility.ifEmpty(performanceform.getDistrict_Id())
					&& MisUtility.ifEmpty(performanceform.getDivisionId())) {
				System.out.println(performanceform.getZoneId());
				System.out.println(performanceform.getCircleId());
				System.out.println(performanceform.getDistrict_Id());
				System.out.println(performanceform.getDivisionId());
				query = "SELECT count(village_id) AS total_village, sum(total_population) AS population_2011, sum(households) AS households_2011 ,count(village_bechirag) village_bechirag,count(village_own) village_own,count(fc_pws) fc_pws,count(fc_hp) fc_hp,count(fc_own) fc_own,count(pc_pws) pc_pws ,count(pc_hp) pc_hp,count(pc_own) pc_own,count(nc_pws) nc_pws,count(distinct piped_sch) piped_sch ,count(distinct piped_village) piped_village ,count(distinct hp_sch) hp_sch ,count(distinct hp_village) hp_village ,count(distinct pws_connection) pws_connection from prwss_main.water_spl_new1 WHERE zone_id = :zone_id AND circle_id = :circle_id AND district_id= :district_id AND  divisional_id = :divisional_id ";
				query2 = "select count(distinct TOT_SCH) as TOT_SCH,count(distinct ADMIN_SCH) as ADMIN_SCH,count(distinct PROC_SCH) as PROC_SCH,count(distinct COMP_SCH) as COMP_SCH,count(distinct ADMIN_SCH_1A) as ADMIN_SCH_1A,count(distinct PROC_SCH_1A) as PROC_SCH_1A,count(distinct COMP_SCH_1A) as COMP_SCH_1A,count(distinct ADMIN_SCH_2A) as ADMIN_SCH_2A,count(distinct PROC_SCH_2A) as PROC_SCH_2A,count(distinct COMP_SCH_2A) as COMP_SCH_2A,count(distinct ADMIN_SCH_NRDWP) as ADMIN_SCH_NRDWP,count(distinct PROC_SCH_NRDWP) as PROC_SCH_NRDWP,count(distinct COMP_SCH_NRDWP) as COMP_SCH_NRDWP,count(distinct ADMIN_SCH_OTHR) as ADMIN_SCH_OTHR,count(distinct PROC_SCH_OTHR) as PROC_SCH_OTHR,count(distinct COMP_SCH_OTHR) as COMP_SCH_OTHR,count(distinct ADMIN_SCH_3A) as ADMIN_SCH_3A,count(distinct PROC_SCH_3A) as PROC_SCH_3A,count(distinct COMP_SCH_3A) as COMP_SCH_3A,count(distinct ADMIN_SCH_3B) as ADMIN_SCH_3B,count(distinct PROC_SCH_3B) as PROC_SCH_3B,count(distinct COMP_SCH_3B) as COMP_SCH_3B,count(distinct ADMIN_SCH_OTHR3) as ADMIN_SCH_OTHR3,count(distinct PROC_SCH_OTHR3) as PROC_SCH_OTHR3,count(distinct COMP_SCH_OTHR3) as COMP_SCH_OTHR3 from prwss_main.report2and3 WHERE zone_id = :zone_id AND circle_id = :circle_id AND district_id= :district_id AND  divisional_id = :divisional_id ";
				query3 = "select count(distinct gpwsc_sch) gpwsc_sch,count(distinct gpwsc_vill) gpwsc_vill,count(distinct dwss_sch) dwss_sch,count(distinct dwss_vill) dwss_vill, count(distinct hrs_vill) hrs_vill,count(distinct conn_vill) conn_vill,count(distinct lpcd_vill) lpcd_vill from prwss_main.report4 WHERE zone_id = :zone_id AND circle_id = :circle_id AND district_id= :district_id AND  divisional_id = :divisional_id ";
				query4 = "SELECT COUNT(DISTINCT ROPLANT) AS ROPLANT ,COUNT(DISTINCT is_ro_functional) AS is_ro_functional FROM prwss_main.report5 WHERE zone_id = :zone_id AND circle_id = :circle_id AND district_id= :district_id AND  divisional_id = :divisional_id ";
				query5 = "SELECT count(distinct aluminium) aluminium,count(distinct lead) lead,count(distinct selenium) selenium,count(distinct chromium) chromium, count(distinct mercury) mercury, count(distinct arsenic) arsenic,count(distinct cadium) cadium, count(distinct nikel) nikel, count(distinct tds) tds, count(distinct flouride) flouride,count(distinct nitrate) nitrate, count(distinct iron) iron, count(distinct failed_phase) failed_phase,count(distinct uranium_safe) uranium_safe, count(distinct uranium_failed_revised) uranium_failed_revised,count(distinct uranimum_failed_aerb) uranimum_failed_aerb, count(distinct uranimum_failed_conc) uranimum_failed_conc,sum(is_ro_functional) is_ro_functional,count(distinct no_of_failed_parameters_1) no_of_failed_parameters_1,count(distinct no_of_failed_parameters_2) no_of_failed_parameters_2 from prwss_main.report6 WHERE zone_id = :zone_id AND circle_id = :circle_id AND district_id= :district_id AND  divisional_id = :divisional_id ";
				
				getTableList = getWaterSupplyCoverageStatus(performanceform,query);
				getSection2and3 = getdataforsection2and3(performanceform, query2);
				getSection4 = getdataforsection4(performanceform, query3);
				getSection5 = getdataforsection5(performanceform,query4);
				getSection6 = getdataforsection6(performanceform,query5);
				request.setAttribute("list", getTableList);
				request.setAttribute("list23", getSection2and3);
				request.setAttribute("list4", getSection4);
				request.setAttribute("list5", getSection5);
				request.setAttribute("list6", getSection6);
				request.setAttribute("zone", performanceform.getZoneId());
				request.setAttribute("circle", performanceform.getCircleId());
				request.setAttribute("district",
						performanceform.getDistrict_Id());
				request.setAttribute("division",
						performanceform.getDivisionId());
				request.setAttribute("subdivision",
						performanceform.getSubdivisonId());
				refresh(performanceform);
				return mapping.findForward("display");
			} else {
				System.out.println("Zone Id is Empty!!");
				System.out.println("Circle ID is Empty!!");
				System.out.println("District ID is Empty!!");
				System.out.println("Divisional ID is Empty!!");
				return mapping.findForward("cScreen");
			}
		}

		// If Zone ,circle , District ,Divisonal and Subdivisonal is Selected
		else if (performanceform.getSelectZone().equalsIgnoreCase("S")
				&& performanceform.getSelectCircle().equalsIgnoreCase("S")
				&& performanceform.getSelectDistrict().equalsIgnoreCase("S")
				&& performanceform.getSelectDivision().equalsIgnoreCase("S")
				&& performanceform.getSelectsubDivision().equalsIgnoreCase("S")) {
			System.out
					.println("Zone , Circle , District , Divisonal  and Subdivisional is Selected.. No Worries");
			if (MisUtility.ifEmpty(performanceform.getZoneId())
					&& MisUtility.ifEmpty(performanceform.getCircleId())
					&& MisUtility.ifEmpty(performanceform.getDistrict_Id())
					&& MisUtility.ifEmpty(performanceform.getDivisionId())
					&& MisUtility.ifEmpty(performanceform.getSubdivisonId())) {
				System.out.println(performanceform.getZoneId());
				System.out.println(performanceform.getCircleId());
				System.out.println(performanceform.getDistrict_Id());
				System.out.println(performanceform.getDivisionId());
				System.out.println(performanceform.getSubdivisonId());
				query = "SELECT count(village_id) AS total_village, sum(total_population) AS population_2011, sum(households) AS households_2011 ,count(village_bechirag) village_bechirag,count(village_own) village_own,count(fc_pws) fc_pws,count(fc_hp) fc_hp,count(fc_own) fc_own,count(pc_pws) pc_pws ,count(pc_hp) pc_hp,count(pc_own) pc_own,count(nc_pws) nc_pws,count(distinct piped_sch) piped_sch ,count(distinct piped_village) piped_village ,count(distinct hp_sch) hp_sch ,count(distinct hp_village) hp_village ,count(distinct pws_connection) pws_connection from prwss_main.water_spl_new1 WHERE zone_id = :zone_id AND circle_id = :circle_id AND district_id= :district_id AND  divisional_id = :divisional_id AND sub_div = :sub_div";
				query2 = "select count(distinct TOT_SCH) as TOT_SCH,count(distinct ADMIN_SCH) as ADMIN_SCH,count(distinct PROC_SCH) as PROC_SCH,count(distinct COMP_SCH) as COMP_SCH,count(distinct ADMIN_SCH_1A) as ADMIN_SCH_1A,count(distinct PROC_SCH_1A) as PROC_SCH_1A,count(distinct COMP_SCH_1A) as COMP_SCH_1A,count(distinct ADMIN_SCH_2A) as ADMIN_SCH_2A,count(distinct PROC_SCH_2A) as PROC_SCH_2A,count(distinct COMP_SCH_2A) as COMP_SCH_2A,count(distinct ADMIN_SCH_NRDWP) as ADMIN_SCH_NRDWP,count(distinct PROC_SCH_NRDWP) as PROC_SCH_NRDWP,count(distinct COMP_SCH_NRDWP) as COMP_SCH_NRDWP,count(distinct ADMIN_SCH_OTHR) as ADMIN_SCH_OTHR,count(distinct PROC_SCH_OTHR) as PROC_SCH_OTHR,count(distinct COMP_SCH_OTHR) as COMP_SCH_OTHR,count(distinct ADMIN_SCH_3A) as ADMIN_SCH_3A,count(distinct PROC_SCH_3A) as PROC_SCH_3A,count(distinct COMP_SCH_3A) as COMP_SCH_3A,count(distinct ADMIN_SCH_3B) as ADMIN_SCH_3B,count(distinct PROC_SCH_3B) as PROC_SCH_3B,count(distinct COMP_SCH_3B) as COMP_SCH_3B,count(distinct ADMIN_SCH_OTHR3) as ADMIN_SCH_OTHR3,count(distinct PROC_SCH_OTHR3) as PROC_SCH_OTHR3,count(distinct COMP_SCH_OTHR3) as COMP_SCH_OTHR3 from prwss_main.report2and3 WHERE zone_id = :zone_id AND circle_id = :circle_id AND district_id= :district_id AND  divisional_id = :divisional_id AND sub_div = :sub_div ";
				query3 = "select count(distinct gpwsc_sch) gpwsc_sch,count(distinct gpwsc_vill) gpwsc_vill,count(distinct dwss_sch) dwss_sch,count(distinct dwss_vill) dwss_vill, count(distinct hrs_vill) hrs_vill,count(distinct conn_vill) conn_vill,count(distinct lpcd_vill) lpcd_vill from prwss_main.report4 WHERE zone_id = :zone_id AND circle_id = :circle_id AND district_id= :district_id AND  divisional_id = :divisional_id AND sub_div = :sub_div ";
				query4 = "SELECT COUNT(DISTINCT ROPLANT) AS ROPLANT ,COUNT(DISTINCT is_ro_functional) AS is_ro_functional FROM prwss_main.report5 WHERE zone_id = :zone_id AND circle_id = :circle_id AND district_id= :district_id AND  divisional_id = :divisional_id AND sub_div = :sub_div ";
				query5 = "SELECT count(distinct aluminium) aluminium,count(distinct lead) lead,count(distinct selenium) selenium,count(distinct chromium) chromium, count(distinct mercury) mercury, count(distinct arsenic) arsenic,count(distinct cadium) cadium, count(distinct nikel) nikel, count(distinct tds) tds, count(distinct flouride) flouride,count(distinct nitrate) nitrate, count(distinct iron) iron, count(distinct failed_phase) failed_phase,count(distinct uranium_safe) uranium_safe, count(distinct uranium_failed_revised) uranium_failed_revised,count(distinct uranimum_failed_aerb) uranimum_failed_aerb, count(distinct uranimum_failed_conc) uranimum_failed_conc,sum(is_ro_functional) is_ro_functional,count(distinct no_of_failed_parameters_1) no_of_failed_parameters_1,count(distinct no_of_failed_parameters_2) no_of_failed_parameters_2 from prwss_main.report6 WHERE zone_id = :zone_id AND circle_id = :circle_id AND district_id= :district_id AND  divisional_id = :divisional_id AND sub_div = :sub_div ";
				
				getTableList = getWaterSupplyCoverageStatus(performanceform,query);
				getSection2and3 = getdataforsection2and3(performanceform, query2);
				getSection4 = getdataforsection4(performanceform, query3);
				getSection5 = getdataforsection5(performanceform,query4);
				getSection6 = getdataforsection6(performanceform,query5);
				request.setAttribute("list", getTableList);
				request.setAttribute("list23", getSection2and3);
				request.setAttribute("list4", getSection4);
				request.setAttribute("list5", getSection5);
				request.setAttribute("list6", getSection6);
				request.setAttribute("zone", performanceform.getZoneId());
				request.setAttribute("circle", performanceform.getCircleId());
				request.setAttribute("district",
						performanceform.getDistrict_Id());
				request.setAttribute("division",
						performanceform.getDivisionId());
				request.setAttribute("subdivision",
						performanceform.getSubdivisonId());
				refresh(performanceform);
				return mapping.findForward("display");
			} else {
				System.out.println("Zone Id is Empty!!");
				System.out.println("Circle ID is Empty!!");
				System.out.println("District ID is Empty!!");
				System.out.println("Divisional ID is Empty!!");
				System.out.println("Sub Divisional ID is Empty!!");
				refresh(performanceform);
				return mapping.findForward("cScreen");
			}
		}

		else {
			System.out.println("Something Went wrong ");
			refresh(performanceform);
			return mapping.findForward("cScreen");
		}

	}

	private List<reportSection7Modal> getdataforsection7(PerformanceReviewReportsForm performanceform, String query62) {
		System.out.println("Zone ID: " + performanceform.getZoneId());
		System.out.println("Circle ID: " + performanceform.getCircleId());
		System.out.println("Disrict ID: " + performanceform.getDistrict_Id());
		System.out.println("Divisonal ID: " + performanceform.getDivisionId());
		System.out.println("Sub Divisional ID:- "
				+ performanceform.getSubdivisonId());
		List<reportSection7Modal> outPatients = null;
		try {
			dataSource = getDataSource();

			NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(
					dataSource);

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("zone_id", performanceform.getZoneId());
			params.addValue("circle_id", performanceform.getCircleId());
			params.addValue("district_id", performanceform.getDistrict_Id());
			params.addValue("divisional_id", performanceform.getDivisionId());
			params.addValue("sub_div", performanceform.getSubdivisonId());

			outPatients = jdbcTemplate.query(query62, params,
					new reportSection7RowMapper());

			for ( reportSection7Modal pmmtable : outPatients) {
				System.out.println(pmmtable.toString());
			}

		} catch (Exception ex) {
			System.out.println("Death!! is Here" + ex.getLocalizedMessage());
		} finally {
			try {
				dataSource.getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Before Query 2: ");
		// query2 =
		// "select count(distinct TOT_SCH) as TOT_SCH,count(distinct ADMIN_SCH) as ADMIN_SCH,count(distinct PROC_SCH) as PROC_SCH,count(distinct COMP_SCH) as COMP_SCH,count(distinct ADMIN_SCH_1A) as ADMIN_SCH_1A,count(distinct PROC_SCH_1A) as PROC_SCH_1A,count(distinct COMP_SCH_1A) as COMP_SCH_1A,count(distinct ADMIN_SCH_2A) as ADMIN_SCH_2A,count(distinct PROC_SCH_2A) as PROC_SCH_2A,count(distinct COMP_SCH_2A) as COMP_SCH_2A,count(distinct ADMIN_SCH_NRDWP) as ADMIN_SCH_NRDWP,count(distinct PROC_SCH_NRDWP) as PROC_SCH_NRDWP,count(distinct COMP_SCH_NRDWP) as COMP_SCH_NRDWP,count(distinct ADMIN_SCH_OTHR) as ADMIN_SCH_OTHR,count(distinct PROC_SCH_OTHR) as PROC_SCH_OTHR,count(distinct COMP_SCH_OTHR) as COMP_SCH_OTHR,count(distinct ADMIN_SCH_3A) as ADMIN_SCH_3A,count(distinct PROC_SCH_3A) as PROC_SCH_3A,count(distinct COMP_SCH_3A) as COMP_SCH_3A,count(distinct ADMIN_SCH_3B) as ADMIN_SCH_3B,count(distinct PROC_SCH_3B) as PROC_SCH_3B,count(distinct COMP_SCH_3B) as COMP_SCH_3B,count(distinct ADMIN_SCH_OTHR3) as ADMIN_SCH_OTHR3,count(distinct PROC_SCH_OTHR3) as PROC_SCH_OTHR3,count(distinct COMP_SCH_OTHR3) as COMP_SCH_OTHR3 from prwss_main.report2and3";
		// getTableList = getWaterSupplyCoverageStatus(performanceform ,query);
		// getSection2and3 = getdataforsection2and3(performanceform ,query2);
		System.out.println("After Method call: ");
		return outPatients;
	}

	private List<reportSection6Modal> getdataforsection6(PerformanceReviewReportsForm performanceform, String query5) {
		System.out.println("Zone ID: " + performanceform.getZoneId());
		System.out.println("Circle ID: " + performanceform.getCircleId());
		System.out.println("Disrict ID: " + performanceform.getDistrict_Id());
		System.out.println("Divisonal ID: " + performanceform.getDivisionId());
		System.out.println("Sub Divisional ID:- "
				+ performanceform.getSubdivisonId());
		List<reportSection6Modal> outPatients = null;
		try {
			dataSource = getDataSource();

			NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(
					dataSource);

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("zone_id", performanceform.getZoneId());
			params.addValue("circle_id", performanceform.getCircleId());
			params.addValue("district_id", performanceform.getDistrict_Id());
			params.addValue("divisional_id", performanceform.getDivisionId());
			params.addValue("sub_div", performanceform.getSubdivisonId());

			outPatients = jdbcTemplate.query(query5, params,
					new reportSection6RowMapper());

			for ( reportSection6Modal pmmtable : outPatients) {
				System.out.println(pmmtable.toString());
			}

		} catch (Exception ex) {
			System.out.println("Death!! is Here" + ex.getLocalizedMessage());
		} finally {
			try {
				dataSource.getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Before Query 2: ");
		// query2 =
		// "select count(distinct TOT_SCH) as TOT_SCH,count(distinct ADMIN_SCH) as ADMIN_SCH,count(distinct PROC_SCH) as PROC_SCH,count(distinct COMP_SCH) as COMP_SCH,count(distinct ADMIN_SCH_1A) as ADMIN_SCH_1A,count(distinct PROC_SCH_1A) as PROC_SCH_1A,count(distinct COMP_SCH_1A) as COMP_SCH_1A,count(distinct ADMIN_SCH_2A) as ADMIN_SCH_2A,count(distinct PROC_SCH_2A) as PROC_SCH_2A,count(distinct COMP_SCH_2A) as COMP_SCH_2A,count(distinct ADMIN_SCH_NRDWP) as ADMIN_SCH_NRDWP,count(distinct PROC_SCH_NRDWP) as PROC_SCH_NRDWP,count(distinct COMP_SCH_NRDWP) as COMP_SCH_NRDWP,count(distinct ADMIN_SCH_OTHR) as ADMIN_SCH_OTHR,count(distinct PROC_SCH_OTHR) as PROC_SCH_OTHR,count(distinct COMP_SCH_OTHR) as COMP_SCH_OTHR,count(distinct ADMIN_SCH_3A) as ADMIN_SCH_3A,count(distinct PROC_SCH_3A) as PROC_SCH_3A,count(distinct COMP_SCH_3A) as COMP_SCH_3A,count(distinct ADMIN_SCH_3B) as ADMIN_SCH_3B,count(distinct PROC_SCH_3B) as PROC_SCH_3B,count(distinct COMP_SCH_3B) as COMP_SCH_3B,count(distinct ADMIN_SCH_OTHR3) as ADMIN_SCH_OTHR3,count(distinct PROC_SCH_OTHR3) as PROC_SCH_OTHR3,count(distinct COMP_SCH_OTHR3) as COMP_SCH_OTHR3 from prwss_main.report2and3";
		// getTableList = getWaterSupplyCoverageStatus(performanceform ,query);
		// getSection2and3 = getdataforsection2and3(performanceform ,query2);
		System.out.println("After Method call: ");
		return outPatients;
	}

	// Section5
	private List<reportSection5Modal> getdataforsection5(PerformanceReviewReportsForm performanceform, String query42) {
		
		System.out.println("Zone ID: " + performanceform.getZoneId());
		System.out.println("Circle ID: " + performanceform.getCircleId());
		System.out.println("Disrict ID: " + performanceform.getDistrict_Id());
		System.out.println("Divisonal ID: " + performanceform.getDivisionId());
		System.out.println("Sub Divisional ID:- "
				+ performanceform.getSubdivisonId());
		List<reportSection5Modal> outPatients = null;
		try {
			dataSource = getDataSource();

			NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(
					dataSource);

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("zone_id", performanceform.getZoneId());
			params.addValue("circle_id", performanceform.getCircleId());
			params.addValue("district_id", performanceform.getDistrict_Id());
			params.addValue("divisional_id", performanceform.getDivisionId());
			params.addValue("sub_div", performanceform.getSubdivisonId());

			outPatients = jdbcTemplate.query(query42, params,
					new reportSection5RowMapper());

			for ( reportSection5Modal pmmtable : outPatients) {
				System.out.println(pmmtable.toString());
			}

		} catch (Exception ex) {
			System.out.println("Death!! is Here" + ex.getLocalizedMessage());
		} finally {
			try {
				dataSource.getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Before Query 2: ");
		// query2 =
		// "select count(distinct TOT_SCH) as TOT_SCH,count(distinct ADMIN_SCH) as ADMIN_SCH,count(distinct PROC_SCH) as PROC_SCH,count(distinct COMP_SCH) as COMP_SCH,count(distinct ADMIN_SCH_1A) as ADMIN_SCH_1A,count(distinct PROC_SCH_1A) as PROC_SCH_1A,count(distinct COMP_SCH_1A) as COMP_SCH_1A,count(distinct ADMIN_SCH_2A) as ADMIN_SCH_2A,count(distinct PROC_SCH_2A) as PROC_SCH_2A,count(distinct COMP_SCH_2A) as COMP_SCH_2A,count(distinct ADMIN_SCH_NRDWP) as ADMIN_SCH_NRDWP,count(distinct PROC_SCH_NRDWP) as PROC_SCH_NRDWP,count(distinct COMP_SCH_NRDWP) as COMP_SCH_NRDWP,count(distinct ADMIN_SCH_OTHR) as ADMIN_SCH_OTHR,count(distinct PROC_SCH_OTHR) as PROC_SCH_OTHR,count(distinct COMP_SCH_OTHR) as COMP_SCH_OTHR,count(distinct ADMIN_SCH_3A) as ADMIN_SCH_3A,count(distinct PROC_SCH_3A) as PROC_SCH_3A,count(distinct COMP_SCH_3A) as COMP_SCH_3A,count(distinct ADMIN_SCH_3B) as ADMIN_SCH_3B,count(distinct PROC_SCH_3B) as PROC_SCH_3B,count(distinct COMP_SCH_3B) as COMP_SCH_3B,count(distinct ADMIN_SCH_OTHR3) as ADMIN_SCH_OTHR3,count(distinct PROC_SCH_OTHR3) as PROC_SCH_OTHR3,count(distinct COMP_SCH_OTHR3) as COMP_SCH_OTHR3 from prwss_main.report2and3";
		// getTableList = getWaterSupplyCoverageStatus(performanceform ,query);
		// getSection2and3 = getdataforsection2and3(performanceform ,query2);
		System.out.println("After Method call: ");
		return outPatients;
	}

	// Section1
	private List<WaterSupplyCoverageStatusModel> getWaterSupplyCoverageStatus(
			PerformanceReviewReportsForm performanceform, String query) {
		// TODO Auto-generated method stub

		System.out.println("Zone ID: " + performanceform.getZoneId());
		System.out.println("Circle ID: " + performanceform.getCircleId());
		System.out.println("Disrict ID: " + performanceform.getDistrict_Id());
		System.out.println("Divisonal ID: " + performanceform.getDivisionId());
		System.out.println("Sub Divisional ID:- "
				+ performanceform.getSubdivisonId());
		List<WaterSupplyCoverageStatusModel> outPatients = null;
		try {
			dataSource = getDataSource();

			NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(
					dataSource);

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("zone_id", performanceform.getZoneId());
			params.addValue("circle_id", performanceform.getCircleId());
			params.addValue("district_id", performanceform.getDistrict_Id());
			params.addValue("divisional_id", performanceform.getDivisionId());
			params.addValue("sub_div", performanceform.getSubdivisonId());

			outPatients = jdbcTemplate.query(query, params,
					new WaterSupplyCoverageStatusModelRowmapper());

			for (WaterSupplyCoverageStatusModel pmmtable : outPatients) {
				System.out.println(pmmtable.toString());
			}

		} catch (Exception ex) {
			System.out.println("Death!! is Here" + ex.getLocalizedMessage());
		} finally {
			try {
				dataSource.getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Before Query 2: ");
		// query2 =
		// "select count(distinct TOT_SCH) as TOT_SCH,count(distinct ADMIN_SCH) as ADMIN_SCH,count(distinct PROC_SCH) as PROC_SCH,count(distinct COMP_SCH) as COMP_SCH,count(distinct ADMIN_SCH_1A) as ADMIN_SCH_1A,count(distinct PROC_SCH_1A) as PROC_SCH_1A,count(distinct COMP_SCH_1A) as COMP_SCH_1A,count(distinct ADMIN_SCH_2A) as ADMIN_SCH_2A,count(distinct PROC_SCH_2A) as PROC_SCH_2A,count(distinct COMP_SCH_2A) as COMP_SCH_2A,count(distinct ADMIN_SCH_NRDWP) as ADMIN_SCH_NRDWP,count(distinct PROC_SCH_NRDWP) as PROC_SCH_NRDWP,count(distinct COMP_SCH_NRDWP) as COMP_SCH_NRDWP,count(distinct ADMIN_SCH_OTHR) as ADMIN_SCH_OTHR,count(distinct PROC_SCH_OTHR) as PROC_SCH_OTHR,count(distinct COMP_SCH_OTHR) as COMP_SCH_OTHR,count(distinct ADMIN_SCH_3A) as ADMIN_SCH_3A,count(distinct PROC_SCH_3A) as PROC_SCH_3A,count(distinct COMP_SCH_3A) as COMP_SCH_3A,count(distinct ADMIN_SCH_3B) as ADMIN_SCH_3B,count(distinct PROC_SCH_3B) as PROC_SCH_3B,count(distinct COMP_SCH_3B) as COMP_SCH_3B,count(distinct ADMIN_SCH_OTHR3) as ADMIN_SCH_OTHR3,count(distinct PROC_SCH_OTHR3) as PROC_SCH_OTHR3,count(distinct COMP_SCH_OTHR3) as COMP_SCH_OTHR3 from prwss_main.report2and3";
		// getTableList = getWaterSupplyCoverageStatus(performanceform ,query);
		// getSection2and3 = getdataforsection2and3(performanceform ,query2);
		System.out.println("After Method call: ");
		return outPatients;

	}

	// section 2 and 3
	private List<reportSection2n3Modal> getdataforsection2and3(
			PerformanceReviewReportsForm performanceform, String query22) {

		List<reportSection2n3Modal> outPatients2 = null;
		try {
			dataSource = getDataSource();

			NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(
					dataSource);

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("zone_id", performanceform.getZoneId());
			params.addValue("circle_id", performanceform.getCircleId());
			params.addValue("district_id", performanceform.getDistrict_Id());
			params.addValue("divisional_id", performanceform.getDivisionId());
			params.addValue("sub_div", performanceform.getSubdivisonId());

			outPatients2 = jdbcTemplate.query(query22, params,
					new reportSection2n3RowMapper());

			for (reportSection2n3Modal pmmtable : outPatients2) {
				System.out.println(pmmtable.toString());
			}

		} catch (Exception ex) {
			System.out.println("Death!! is Here" + ex.getLocalizedMessage());
		} finally {
			try {
				dataSource.getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return outPatients2;
	}

	// Section 4
	private List<reportSection4Modal> getdataforsection4(
			PerformanceReviewReportsForm performanceform, String query32) {

		List<reportSection4Modal> outPatients2 = null;
		try {
			dataSource = getDataSource();

			NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(
					dataSource);

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("zone_id", performanceform.getZoneId());
			params.addValue("circle_id", performanceform.getCircleId());
			params.addValue("district_id", performanceform.getDistrict_Id());
			params.addValue("divisional_id", performanceform.getDivisionId());
			params.addValue("sub_div", performanceform.getSubdivisonId());

			outPatients2 = jdbcTemplate.query(query32, params,
					new reportSection4RowMapper());

			for (reportSection4Modal pmmtable : outPatients2) {
				System.out.println(pmmtable.toString());
			}

		} catch (Exception ex) {
			System.out.println("Death!! is Here" + ex.getLocalizedMessage());
		} finally {
			try {
				dataSource.getConnection().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return outPatients2;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// this.setAttrib(request);
		try {
			System.out.println("I'm in Action PDO");

			// EstimatesAwardContractsReportForm
			// estimatesAwardContractsReportForm =
			// (EstimatesAwardContractsReportForm)form;
			// refreshEstimatesAwardContractsReportForm(estimatesAwardContractsReportForm);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		System.out.println("Unspecified.......PDO Reports");
		return mapping.findForward("cScreen");
	}

	public static BasicDataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		// DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(MISConstants.driverClassName);
		dataSource.setUrl(MISConstants.url);
		dataSource.setUsername(MISConstants.dbUsername);
		dataSource.setPassword(MISConstants.dbPassword);
		return dataSource;
	}

	private void refresh(PerformanceReviewReportsForm performanceform) {
		// TODO Auto-generated method stub
		performanceform.setZoneId(null);
		performanceform.setCircleId(null);
		performanceform.setDistrict_Id(null);
		performanceform.setDivisionId(null);
		performanceform.setSubdivisonId(null);
		getTableList = null;
		getSection2and3 = null;
		getSection4 = null;
		query = null;
		query2 = null;
		query3 = null;
	}

	private void showvalues(PerformanceReviewReportsForm performanceform) {

		System.out.println("ZONE ID:- " + performanceform.getZoneId());
		System.out.println("Circle ID:- " + performanceform.getCircleId());
		System.out.println("District ID:- " + performanceform.getDistrict_Id());
		System.out
				.println("Divisopnal ID:- " + performanceform.getDivisionId());
		System.out.println("Sub Divisinol ID:- "
				+ performanceform.getSubdivisonId());

		System.out.println("ZONE Select:- " + performanceform.getSelectZone());
		System.out.println("Circle Select:- "
				+ performanceform.getSelectCircle());
		System.out.println("District Select:- "
				+ performanceform.getSelectDistrict());
		System.out.println("Divisopnal Select:- "
				+ performanceform.getSelectDivision());
		System.out.println("Sub Divisinol Select:- "
				+ performanceform.getSelectsubDivision());

	}

}