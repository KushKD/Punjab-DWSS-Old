package com.prwss.mis.reports.performa;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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
import org.apache.struts.util.LabelValueBean;
import org.springframework.dao.DataAccessException;

import com.prwss.mis.common.MISSessionBean;
import com.prwss.mis.common.exception.MISException;
import com.prwss.mis.common.exception.MISExceptionCodes;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.common.util.MisUtility;
import com.prwss.mis.reports.performa.dao.FiveYearPlanGridBean;
import com.prwss.mis.reports.performa.dao.PerformaMasterBean;
import com.prwss.mis.reports.performa.dao.PerformaMasterBo;
import com.prwss.mis.reports.performa.dao.PerformaMasterDao;
import com.prwss.mis.reports.performa.dao.SchemeBean1;
import com.prwss.mis.reports.performa.dao.SchemeSourcePerformaBean;
import com.prwss.mis.reports.performa.dao.VillagePerformaGridBean;
import com.prwss.mis.reports.performa.dao.VillageSourceGridBean;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class PerformaMasterAction extends DispatchAction {

	Logger log = Logger.getLogger(PerformaMasterAction.class);

	private PerformaMasterBo performaMasterBo;
	private PerformaMasterDao performaMasterDao;
	private MISSessionBean misSessionBean;
	Map<String, List<String>> hashMap = new HashMap<String, List<String>>();
	// Set<LabelValueBean> villages = new TreeSet<LabelValueBean>();

	/**
	 * @param performaMasterDao
	 *            the performaMasterDao to set
	 */
	public void setPerformaMasterDao(PerformaMasterDao performaMasterDao) {
		this.performaMasterDao = performaMasterDao;
	}

	/**
	 * @param performaMasterBo
	 *            the performaMasterBo to set
	 */
	public void setPerformaMasterBo(PerformaMasterBo performaMasterBo) {
		this.performaMasterBo = performaMasterBo;
	}

	/**
	 * @param misSessionBean
	 *            the misSessionBean to set
	 */
	public void setMisSessionBean(MISSessionBean misSessionBean) {
		this.misSessionBean = misSessionBean;
	}

	public ActionForward find(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		System.out.println("Inside Find");

		List<PerformaMasterBean> performaMasterBean = null;
		List<PerformaMasterBean> performaBean = null;
		List<SchemeSourcePerformaBean> fiveYearPlanBeans = null;
		List<SchemeBean1> schemeBean = null;

		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			return mapping.findForward("login");
		}
		String mode = (String) request.getParameter("d__mode");
		this.setAttrib(request);

		List<String> statusList = new ArrayList<String>();

		if (mode != null && MISConstants.D_MODE_ENQUIRE.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		if (mode != null && MISConstants.D_MODE_MODIFY.equalsIgnoreCase(mode.trim())) {
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		}
		PerformaMasterForm performaMasterForm = (PerformaMasterForm) form;
		String blockId = performaMasterForm.getBlockId();
		String villgId = performaMasterForm.getVillgId();
		String schemeId = performaMasterForm.getSchemeId();

		System.out.println("performaMasterForm---->" + performaMasterForm.toString());

		performaMasterBean = performaMasterBo.findPerformaMaster(performaMasterForm, statusList);
		performaBean = performaMasterDao.findPerforma(performaMasterForm, statusList);

		System.out.println("performaMasterBean---->" + performaMasterBean.toString());
		System.out.println("performaBean---->" + performaBean.toString());

		if (!MisUtility.ifEmpty(performaMasterBean)) {
			for (PerformaMasterBean performaMaster : performaBean) {
				performaMasterForm.setSchemeType(performaMaster.getSv_mv());
				performaMasterForm.setSchemeName(performaMaster.getSchemeName());
				performaMasterForm.setSchemeSource(performaMaster.getSchemeSource());
				performaMasterForm
						.setDateOfComm(MisUtility.convertDateString(performaMaster.getDate_of_commissioning()));
				performaMasterForm.setProgId(performaMaster.getProgram_name());
				request.setAttribute("blockId", blockId);
				request.setAttribute("schemeId", schemeId);
				request.setAttribute("villgId", villgId);
				performaMasterForm.setServiceLevel(performaMaster.getService_level());

				performaMasterForm.setSchemeUpgraded(MisUtility.convertDateString(performaMaster.getScheme_upgraded()));
				performaMasterForm.setSchemeExpenditure(performaMaster.getScheme_expenditure());
				performaMasterForm.setPresentSpringLevel(performaMaster.getPresent_spring_level());
				performaMasterForm.setPresentDis(performaMaster.getPresent_discharge());
				performaMasterForm.setDepthOfTubewell(performaMaster.getDepth_of_tubewell());
				performaMasterForm.setSizeOfTubewell(performaMaster.getSize_of_tubewell());
				performaMasterForm
						.setYearOfDrilling(MisUtility.convertDateString(performaMaster.getYear_of_drilling()));
				performaMasterForm.setDischargeComm(performaMaster.getOriginal_discharge_at_time_of_commissioning());
				performaMasterForm.setInstallation_of_new_machinery(
						MisUtility.convertDateString(performaMaster.getInstallation_of_new_machinery()));
				performaMasterForm.setCapMachinery((performaMaster.getCapacity_of_machinery()));
				performaMasterForm.setInletType(performaMaster.getInlet_channel_type());
				performaMasterForm.setInletLenght(performaMaster.getInlet_channel_lenght());
				performaMasterForm.setPipeType(performaMaster.getPipe_type());
				performaMasterForm.setCapacitySSTank(performaMaster.getCapacity_of_ss_tank());
				performaMasterForm.setCapacityHLTank(performaMaster.getCapacity_of_hl_tank());
				performaMasterForm.setCapacityCWTank(performaMaster.getCapacity_of_cw_tank());
				performaMasterForm.setFiltertionType(performaMaster.getFiltertion_type());
				performaMasterForm.setFiltertionNo(performaMaster.getFiltertion_no());
				performaMasterForm.setCapacityRawWater(performaMaster.getCapacity_of_raw_water());
				performaMasterForm.setCapacityClearWater(performaMaster.getCapacity_of_clear_water());

				performaMasterForm.setNoOfOhsr(performaMaster.getNo_of_ohsr());

				performaMasterForm.setoHSR_construction_date(
						MisUtility.convertDateString(performaMaster.getOhsr_construction_date()));
				performaMasterForm.setoHSRCapcity(performaMaster.getOhsr_capcity());
				performaMasterForm.setoHSRDismantling(performaMaster.getDismantling_received());
				performaMasterForm.setoHSRFullSuplyLvl(performaMaster.getOhsr_full_supply_level1());
				performaMasterForm.setoHSRWorkingCondition(performaMaster.getOhsr_working_condition());
				performaMasterForm.setoHSRCondition(performaMaster.getOhsr_working_condition_if_no());
				// disinfecReq
				if (MisUtility.ifEmptyField(performaMaster.getDisinfection_type())) {
					performaMasterForm.setDisinfecReq(MISConstants.YES);
				}
				performaMasterForm.setDisinfectionType(performaMaster.getDisinfection_type());
				performaMasterForm.setGround_water_potable_status(performaMaster.getGround_water_potable_status());
				performaMasterForm.setGround_water_potable_No(performaMaster.getGround_water_potable_no());
				performaMasterForm.setPreventive_measures_adopted(performaMaster.getPreventive_measures_adopted());
				performaMasterForm.setCapacity_of_plant(performaMaster.getCapacity_of_plant());
				performaMasterForm.setDate_of_installation(
						MisUtility.convertDateString(performaMaster.getDate_of_installation()));
				performaMasterForm.setBeing_operated_by(performaMaster.getBeing_operated_by());
				performaMasterForm.setVolume_of_Water_Daily_basis(performaMaster.getVolume_of_water_daily_basis());
				performaMasterForm.setoHSRCTDia(performaMaster.getOhsr_ct());
				performaMasterForm.setoHSRCTDepth(performaMaster.getOhsr_ct_dep());
				
				
				performaMasterForm.setoHSRUGSRDia(performaMaster.getOhsr_ugsr());
				performaMasterForm.setoHSRUGSRDepth(performaMaster.getOhsr_ugsr_dep());
				
				
				performaMasterForm.setDisposal_of_reject_water(performaMaster.getDisposal_of_reject_water());
				performaMasterForm.setPenetration_in_percentage(performaMaster.getPenetration_in_percentage());
				performaMasterForm.setPresent_rate_of_user_charges(performaMaster.getPresent_rate_of_user_charges());
				performaMasterForm.setSeperate_Sanctioned_Load(performaMaster.getSeperate_sanctioned_load());
				performaMasterForm.setSeperate_Pending_eletric_bill31032017(
						performaMaster.getSeperate_pending_eletric_bill31032017());
				performaMasterForm.setAverage_monthly_bill_of_Treatment_plant(
						performaMaster.getAverage_monthly_bill_of_treatment_plant());
				performaMasterForm.setDisinfection_Instalation_time(
						MisUtility.convertDateString(performaMaster.getDisinfection_instalation_time()));
				performaMasterForm.setDisinfectionPrStatus(performaMaster.getDisinfection_present_status());
				performaMasterForm.setSchemeOperatedBy(performaMaster.getScheme_operated_by());
				performaMasterForm.setStaffScheme(performaMaster.getDwss_operated_arrangement());
				performaMasterForm.setSanctionedLoad(performaMaster.getSanctioned_load());
				performaMasterForm.setPendingBill3103(performaMaster.getPending__bill_31032017());
				performaMasterForm.setPendingBill3006(performaMaster.getPending__bill_30062017());
				performaMasterForm.setAvgMonthBillofWSS(performaMaster.getAvg_month_billof_wss());
				performaMasterForm.setPipelineAC(performaMaster.getPipeline_ac());
				performaMasterForm.setPipelineGI(performaMaster.getPipeline_gi());
				performaMasterForm.setPipelineMSDICi(performaMaster.getPipeline_ms_di_ci());
				performaMasterForm.setPipelinePVC(performaMaster.getPipeline_pvc());

				performaMasterForm
						.setFunctionalDistributionPercentage(performaMaster.getFunctional_distribution_percentage());
				performaMasterForm.setHouseholdWaterConnection(performaMaster.getNo_of_water_connection());
				performaMasterForm.setWatered_connection(performaMaster.getWatered_connection());
				performaMasterForm.setMetered_connection(performaMaster.getMetered_connection());
				performaMasterForm.setMetered_supply_recovery(performaMaster.getMetered_supply_recovery());
				performaMasterForm.setFlat_rate_charges_per_month(performaMaster.getFlat_rate_charges_per_month());
				performaMasterForm.setArrear_of_recovery_with_consumers_as01042017(
						performaMaster.getArrear_of_recovery_with_consumers_as01042017());
				performaMasterForm.setScheme_Functional(performaMaster.getScheme_functional());
				performaMasterForm.setScheme_NonFunctional(performaMaster.getScheme_nonfunctional());

				if (MisUtility
						.ifEmptyField(MisUtility.convertDateString(performaMaster.getScheme_nonfunctional_date()))) {
					performaMasterForm.setScheme_Functional("1");
				}
				performaMasterForm.setScheme_NonFunctional_date(
						MisUtility.convertDateString(performaMaster.getScheme_nonfunctional_date()));
				performaMasterForm
						.setFunctionalDistributionPercentage(performaMaster.getPercentage_functional_pipeline());

				request.getSession().setAttribute("schemeId", performaMaster.getSchemeId());

			}

			performaMasterForm.setVillagePerformaGrid(getPerformaDataGrid(performaMasterBean));
			
			fiveYearPlanBeans=performaMasterDao.findFiveYearPlanData(performaMasterForm);
			
			if(fiveYearPlanBeans.size()>0 && fiveYearPlanBeans!=null ){
				performaMasterForm.setVillageSourceGrid(getSourceDataGrid(performaMasterBean));
			}

			performaMasterForm.setFiveYearPlanGridBean(getFiveYearPlanDataGrid(performaMasterBean, request));

			ActionMessages errors = new ActionMessages();
			ActionMessage message = new ActionMessage("find.list");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveMessages(request, errors);
		} else {
			refereshPerformaMaster(performaMasterForm);
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("No.record", "found or available for this mode for ",
					"the Transaction");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			System.out.println("Message from" + message);
		}
		// request.getSession().setAttribute("villages", villages);
		System.out.println("after call dao---->");
		request.setAttribute("level2", "true");
		return mapping.findForward("display");
	}

	private void setAttrib(HttpServletRequest request) {
		request.setAttribute("Rpt", "ent");
		request.setAttribute("menuId", request.getParameter("menuId"));
		request.setAttribute("d__mode", request.getParameter("d__mode"));
		request.setAttribute("d__ky",
				"schemeId@locationId@blockId@villgId@schemeSource@progId@dateOfComm@schemeName@serviceLevel@schemeType@schemeOperatedBy");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("inside update--->");
		this.setAttrib(request);
		PerformaMasterForm performaMasterForm = (PerformaMasterForm) form;
		System.out.println("performaMasterForm--->" + performaMasterForm.toString());
		boolean status = false;
		try {
			if (request.getSession().getAttribute("misSessionBean") != null) {

				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			} else {
				log.debug("Bidder save : Your session timed out");
				return mapping.findForward("login");
			}

			request.setAttribute("blockId", performaMasterForm.getBlockId());
			request.setAttribute("schemeId", performaMasterForm.getSchemeId());
			request.setAttribute("villgId", performaMasterForm.getVillgId());
			StringBuffer strngMessage = new StringBuffer();
			String msg = validateVillageGrid(performaMasterForm);

			/*
			 * if(MisUtility.ifEmptyFieldNo(performaMasterForm.getVillId())&&
			 * MisUtility.ifEmptyFieldNo(performaMasterForm.getMultipleSource())
			 * &&MisUtility.ifEmptyFieldNo(performaMasterForm.getSchemeSrc())){
			 * if(!MisUtility.ifEmpty(performaMasterForm.getVillageSourceGrid().
			 * getAddedData())){
			 * 
			 * @SuppressWarnings("unchecked") Collection<VillageSourceGridBean>
			 * villagePerformaGrid=performaMasterForm.getVillageSourceGrid().
			 * getAddedData(); if(villagePerformaGrid.size()<2){ throw new
			 * MISException(MISExceptionCodes.MIS012,
			 * "Please select more than source in Grid"); } } }
			 */

			if (MisUtility.ifEmptyField(msg)) {
				throw new MISException(MISExceptionCodes.MIS012, msg);
			} else {
				// if(MisUtility.ifEmptyField(performaMasterForm.getIndependent_New_WSS()))
				System.out.println("Independent New WSS " + performaMasterForm.getIndependent_New_WSS());
				System.out.println("performaMasterForm-------------->" + performaMasterForm.toString());
				if (performaMasterForm.getUnderProcessSelection().equalsIgnoreCase("No")) {
					if (MisUtility.ifEmptyFieldNo(performaMasterForm.getIndependent_New_WSS())
							|| MisUtility.ifEmptyFieldNo(performaMasterForm.getUpgradation_of_existing_WSS())
							|| MisUtility.ifEmptyFieldNo(performaMasterForm.getInstltion_Wtr_Treatment())) {
						
						if (MisUtility.ifEmpty(performaMasterForm.getFiveYearPlanGridBean().getAddedData())) {
							strngMessage.append("Please attach Five Year Plan.");
							throw new MISException(MISExceptionCodes.MIS012, strngMessage.toString());
						}
					}
				}
				
			}
			if(MisUtility.ifEmptyFieldYes(performaMasterForm.getMultipleSource())){
				if(performaMasterForm.getVillageSourceGrid().getAddedData().size()<=1){
					strngMessage.append("Please add data in grid for second source by pressing the plus(+) button");
					throw new MISException(MISExceptionCodes.MIS012, strngMessage.toString());
				}
			}
			String selectedVillage = request.getSession().getAttribute("selectedVillage").toString();
			performaMasterForm.setVillageId(selectedVillage);
			if(MisUtility.ifEmpty(performaMasterForm.getSchemeId())){
				status = performaMasterBo.updatePerformaMaster(performaMasterForm, misSessionBean);
			}if(!MisUtility.ifEmpty(performaMasterForm.getSchemeId())){
					
					ActionErrors errors = new ActionErrors();
					ActionMessage message = new ActionMessage("fatal.error.save", "Scheme Information");
					errors.add(ActionMessages.GLOBAL_MESSAGE, message);
					saveErrors(request, errors);
					request.setAttribute("level2", "true");
			}

			if (status) {
				ActionMessages errors = new ActionMessages();
				ActionMessage message = new ActionMessage("success.update",
						"Scheme ID -->" + performaMasterForm.getSchemeId());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveMessages(request, errors);
				refereshPerformaMaster(performaMasterForm);
			}
		}

		catch (MISException e) {
			if (MISExceptionCodes.MIS012.equals(e.getCode())) {

				System.out.println(
						"e.getcode in action------------++++++++++" + e.getCode() + "messages----->" + e.getMessage());
				log.error(e.getLocalizedMessage(), e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("required.fields", e.getMessage());
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				request.setAttribute("level2", "true");
			}
			else{
				log.error(e.getLocalizedMessage(), e);
				ActionErrors errors = new ActionErrors();
				ActionMessage message = new ActionMessage("fatal.error.save", "Scheme Information");
				errors.add(ActionMessages.GLOBAL_MESSAGE, message);
				saveErrors(request, errors);
				request.setAttribute("level2", "true");
			}
			
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionMessage message = new ActionMessage("fatal.error.save", "Scheme Information");
			errors.add(ActionMessages.GLOBAL_MESSAGE, message);
			saveErrors(request, errors);
			request.setAttribute("level2", "true");
             e.printStackTrace();
		}
		return mapping.findForward("display");
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		this.setAttrib(request);
		PerformaMasterForm performaMasterForm = (PerformaMasterForm) form;

		if (request.getSession().getAttribute("misSessionBean") != null) {
			{
				misSessionBean = (MISSessionBean) request.getSession().getAttribute("misSessionBean");
			}
		} else {
			System.out.println("NO AUDIT");
			return mapping.findForward("login");
		}

		refereshPerformaMaster(performaMasterForm);
		System.out.println("Unspecified.......PDO Reports");
		// villages.add(new LabelValueBean("Please
		// select",performaMasterForm.getVillagesId()));
		return mapping.findForward("display");
	}

	private PerformaMasterForm getSchemeData(SchemeBean1 schemeBean) {

		PerformaMasterForm performaMasterForm = null;
		try {

		} catch (Exception ex) {

		}

		return performaMasterForm;
	}

	private void refereshPerformaMaster(PerformaMasterForm performaMasterForm) {
		// performaMasterForm.setVillageId(null);
		performaMasterForm.setBlockId(null);
		performaMasterForm.setVillageId(null);
		performaMasterForm.setSchemeId(null);
		performaMasterForm.setVillageName(null);
		performaMasterForm.setSchemeName(null);
		performaMasterForm.setSchemeSource(null);
		performaMasterForm.setSchemeType(null);
		performaMasterForm.setDisinfecReq(null);
		performaMasterForm.setDateOfComm(null);
		performaMasterForm.setProgId(null);
		performaMasterForm.setSchemeUpgraded(null);
		performaMasterForm.setSchemeExpenditure(null);
		performaMasterForm.setServiceLevel(null);
		performaMasterForm.setDepthOfTubewell(null);
		performaMasterForm.setSizeOfTubewell(null);
		performaMasterForm.setYearOfDrilling(null);
		performaMasterForm.setDischargeComm(null);
		performaMasterForm.setPresentDis(null);
		performaMasterForm.setPresentSpringLevel(null);
		performaMasterForm.setInstallation_of_new_machinery(null);
		performaMasterForm.setCapacity_of_machinery(null);
		performaMasterForm.setInletLenght(null);
		performaMasterForm.setInletType(null);
		performaMasterForm.setCanal_based_Inlet_channel_length(null);
		performaMasterForm.setPipeType(null);
		performaMasterForm.setCapacitySSTank(null);
		performaMasterForm.setCapacityHLTank(null);
		performaMasterForm.setCapacityCWTank(null);
		performaMasterForm.setFiltertionType(null);
		performaMasterForm.setFiltertionNo(null);
		performaMasterForm.setCapacityRawWater(null);
		performaMasterForm.setCapacityClearWater(null);
		performaMasterForm.setNoOfOhsr(null);
		performaMasterForm.setoHSR_construction_date(null);
		performaMasterForm.setoHSRCapcity(null);
		performaMasterForm.setoHSRCondition(null);
		performaMasterForm.setoHSRWorkingCondition(null);
		performaMasterForm.setMultipleSource(null);
		performaMasterForm.setoHSRFullSuplyLvl(null);
		performaMasterForm.setGround_water_potable_No(null);
		performaMasterForm.setPreventive_measures_adopted(null);
		performaMasterForm.setAny_other_desc(null);
		performaMasterForm.setCapacity_of_plant(null);
		performaMasterForm.setDate_of_installation(null);
		performaMasterForm.setBeing_operated_by(null);
		performaMasterForm.setCapacity_of_plant(null);
		performaMasterForm.setVolume_of_Water_Daily_basis(null);
		performaMasterForm.setDisposal_of_reject_water(null);
		performaMasterForm.setPenetration_in_percentage(null);
		performaMasterForm.setPresent_rate_of_user_charges(null);
		performaMasterForm.setSeperate_Pending_eletric_bill31032017(null);
		performaMasterForm.setSeperate_Pending_eletric_bill31032017(null);
		performaMasterForm.setSeperate_Sanctioned_Load(null);
		performaMasterForm.setAverage_monthly_bill_of_Treatment_plant(null);
		performaMasterForm.setDisinfection_Instalation_time(null);
		performaMasterForm.setSanctionedLoad(null);
		performaMasterForm.setPendingBill3103(null);
		performaMasterForm.setPendingBill3006(null);
		performaMasterForm.setAvgMonthBillofWSS(null);
		performaMasterForm.setSchemeOperatedBy(null);
		performaMasterForm.getStaffScheme();
		performaMasterForm.setMultipleSource(null);
		performaMasterForm.setPipelineAC(null);
		performaMasterForm.setPipelineMSDICi(null);
		performaMasterForm.setPipelineGI(null);
		performaMasterForm.setPipelinePVC(null);
		performaMasterForm.setFunctionalDistributionPercentage(null);
		performaMasterForm.setHouseholdWaterConnection(null);
		performaMasterForm.setWatered_connection(null);
		performaMasterForm.setMetered_connection(null);
		performaMasterForm.setMetered_supply_recovery(null);
		performaMasterForm.setoHSRCTDepth(null);
		performaMasterForm.setoHSRUGSRDepth(null);
		performaMasterForm.setoHSRCTDia(null);
		performaMasterForm.setoHSRUGSRDia(null);
		
		
		performaMasterForm.setFlat_rate_charges_per_month(null);
		performaMasterForm.setArrear_of_recovery_with_consumers_as01042017(null);
		performaMasterForm.setScheme_NonFunctional_date(null);
		performaMasterForm.setIndependent_New_WSS_Cost(null);
		performaMasterForm.setUpgradation_of_existing_WSS_Cost(null);
		performaMasterForm.setInstltion_Wtr_Treatment_plant(null);
		performaMasterForm.setSource_of_WSS(null);
		performaMasterForm.setDriling_of_new_tubewell_machinery_size(null);
		performaMasterForm.setDriling_of_new_tubewell_machinery_depth(null);
		performaMasterForm.setDriling_of_new_tubewell_machinery_capacity(null);
		performaMasterForm.setDriling_of_new_tubewell_machinery_cost(null);
		performaMasterForm.setCanal_based_Inlet_channel_Size_of_pipe(null);
		performaMasterForm.setCanal_based_s_and_s_Capacity(null);
		performaMasterForm.setCanal_based_Filteration_Plan_type(null);
		performaMasterForm.setCanal_based_Filteration_Plan_capacity(null);
		performaMasterForm.setCanal_based_Cost(null);
		performaMasterForm.setoHSR_Capacity(null);
		performaMasterForm.setoHSR_Full_Supply_Level(null);
		performaMasterForm.setoHSR_Cost(null);
		performaMasterForm.setOther_structures_at_waterworks_Cost(null);
		performaMasterForm.setDistribution_WSS_to_village_type(null);
		performaMasterForm.setDistribution_WSS_to_village_length(null);
		performaMasterForm.setDistribution_WSS_within_village_type(null);
		performaMasterForm.setDistribution_WSS_within_village_length(null);
		performaMasterForm.setDistribution_Cost(null);
		performaMasterForm.setNo_of_connections_100_mtr(null);
		performaMasterForm.setNo_of_mtrs_100_mtr(null);
		performaMasterForm.setCost_100_mtr(null);
		performaMasterForm.setDisinfection_Unit_Cost(null);
		performaMasterForm.setDisinfection_Unit_Type(null);
		performaMasterForm.setWater_Treatment_plant_in_case_of_quality_village_type(null);
		performaMasterForm.setWater_Treatment_plant_in_case_of_quality_village_Cost(null);
		performaMasterForm.setWater_Treatment_plant_in_case_of_quality_village_capacity(null);
		performaMasterForm.setBulk_Water_meter_cost(null);
		performaMasterForm.setExtension_Sanction_of_new_electric_connection_cost(null);
		performaMasterForm.setAngarwaries(null);
		performaMasterForm.setAreaSqrMtr(null);
		performaMasterForm.setArrear_of_recovery_with_consumers_as01042017(null);
		performaMasterForm.setAssemblyConstituency(null);
		performaMasterForm.setAverage_monthly_bill_of_Treatment_plant(null);
		performaMasterForm.setAvgMonthBillofWSS(null);
		performaMasterForm.setBeing_operated_by(null);
		performaMasterForm.setBeing_operated_by(null);
		performaMasterForm.setBulk_Water_meter_cost(null);
		performaMasterForm.setBlockId(null);
		performaMasterForm.setBulk_Water_meter_cost(null);
		performaMasterForm.setBulk_Water_meter_no(null);
		performaMasterForm.setCanal_based_Cost(null);
		performaMasterForm.setCanal_based_Filteration_Plan_capacity(null);
		performaMasterForm.setCanal_based_Filteration_Plan_type(null);
		performaMasterForm.setCanal_based_Inlet_channel_length(null);
		performaMasterForm.setCanal_based_Inlet_channel_Size_of_pipe(null);
		performaMasterForm.setCanal_based_Inlet_channel_Size_of_pipe(null);
		performaMasterForm.setCanal_based_s_and_s_Capacity(null);
		performaMasterForm.setCapacity_of_machinery(null);
		performaMasterForm.setCapacity_of_plant(null);
		performaMasterForm.setCapacityClearWater(null);
		performaMasterForm.setCapacityCWTank(null);
		performaMasterForm.setCapacityHLTank(null);
		performaMasterForm.setCapacityRawWater(null);
		performaMasterForm.setCapacitySSTank(null);
		performaMasterForm.setCapMachinery(null);
		performaMasterForm.setCommCenterDhar(null);
		performaMasterForm.setCost_100_mtr(null);
		performaMasterForm.setDistribution_WSS_within_village_type_pvc(null);
		performaMasterForm.setDistribution_WSS_within_village_type_di_ms(null);
		performaMasterForm.setDistribution_WSS_within_village_type_gi(null);
		performaMasterForm.setDate_of_installation(null);
		performaMasterForm.setDateOfComm(null);
		performaMasterForm.setDepthOfTubewell(null);
		performaMasterForm.setDischargeComm(null);
		performaMasterForm.setDisinfection_Instalation_time(null);
		performaMasterForm.setDisinfection_Unit_Cost(null);
		performaMasterForm.setDisinfection_Unit_Type(null);
		performaMasterForm.setDisinfectionPrStatus(null);
		performaMasterForm.setDisinfectionType(null);
		performaMasterForm.setDistribution_Cost(null);
		performaMasterForm.setVillagePerformaGrid(getPerformaDataGrid(null));
		performaMasterForm.setVillageSourceGrid(getSourceDataGrid(null));
		performaMasterForm.setFiveYearPlanGridBean(getFiveYearPlanDataGrid(null, null));
		performaMasterForm.setPresentDis(null);
		performaMasterForm.setVillagesId(null);
		performaMasterForm.setIndependent_New_WSS(null);
		
		//Other Four Fields
		performaMasterForm.setoHSR_CT_Depth(null);
		performaMasterForm.setoHSR_UGS_RDia(null);
		performaMasterForm.setoHSR_UGSR_Depth(null);
		performaMasterForm.setoHSRCTDiameter(null);
		performaMasterForm.setSchemeSrc(null);
		
		performaMasterForm.setRemarksdateOfComplition(null);
		performaMasterForm.setDateOfComplition(null);
	}

	public ActionForward fetchScheme(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {

		System.out.println("inside fetchScheme");
		List<PerformaMasterBean> performaMaserBeans = null;
		StringBuffer buffer = new StringBuffer();
		List<String> statusList = new ArrayList<String>();
		statusList.add(MISConstants.MASTER_STATUS_APPROVED);
		statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
		PerformaMasterBean performaMasterBean = new PerformaMasterBean();
		String ss = request.getParameter("villgId");

		request.getSession().setAttribute("selectedVillage", request.getParameter("villgId"));
		try {
			if (MisUtility.ifEmpty(request.getParameter("villgId"))) {
				performaMasterBean.setVillageId(request.getParameter("villgId"));
				performaMaserBeans = performaMasterDao.findSchemeFromVillage(performaMasterBean);
				System.out.println(performaMaserBeans.toString());
				buffer.append("<option value='' selected>");
				buffer.append("Select Scheme");
				buffer.append("</option>");
				for (PerformaMasterBean schemeHeaderBean2 : performaMaserBeans) {
					buffer.append("<option value=\"").append(schemeHeaderBean2.getSchemeId()).append("\">");
					buffer.append(schemeHeaderBean2.getSchemeName() + " - (" + schemeHeaderBean2.getSchemeId() + ")");
					buffer.append("</option>");
				}
			}
			PrintWriter out = response.getWriter();
			if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1) {
				out.print(buffer.toString());
			}
		} /*
			 * catch (DataAccessException e) { log.error(e); } catch
			 * (IOException e) { log.error(e); e.printStackTrace(); }
			 */
		catch (Exception e) {
			log.error(e);
		}

		return null;
	}

	private Datagrid getPerformaDataGrid(List<PerformaMasterBean> villageSchemePerformaList) {

		Datagrid villageDatagrid = null;
		List<VillagePerformaGridBean> villagePerformaGridBean = new ArrayList<VillagePerformaGridBean>();
		try {
			villageDatagrid = Datagrid.getInstance();
			villageDatagrid.setDataClass(VillagePerformaGridBean.class);
			if (!MisUtility.ifEmpty(villageSchemePerformaList)) {
				VillagePerformaGridBean villagePerformaBean = null;
				for (PerformaMasterBean performaMasterBean : villageSchemePerformaList) {
					villagePerformaBean = new VillagePerformaGridBean();
					villagePerformaBean.setVillageId(performaMasterBean.getVillageId());
					villagePerformaBean.setVillageName(performaMasterBean.getVillageName());
					
					//Check the Value of hadbast number, remove the values after decimal
					
					villagePerformaBean.setHadBastNo(performaMasterBean.getHadBstNo());   
					
					villagePerformaBean.setNameOfHabitation(performaMasterBean.getVillageName());
					
					System.out.println("performaMasterBean.getNoOfHabitation()---------->"+performaMasterBean.getNoOfHabitation());
					villagePerformaBean.setNoOfHabitation(performaMasterBean.getNoOfHabitation());
					villagePerformaBean.setTypeOfHabitation(performaMasterBean.getHabitationType());
					villagePerformaBean.setMisCode(performaMasterBean.getSchemeId());
					villagePerformaBean.setPoputlationTotal(performaMasterBean.getTotalPopulation());
					villagePerformaBean.setPopulationSc(performaMasterBean.getScPopulation());
					villagePerformaBean.setNoOfHouseholdsTotal(performaMasterBean.getTotalHouseHolds());
					villagePerformaBean.setNoOfHouseholdsSc(performaMasterBean.getScHouseholds());
					villagePerformaBean.setPanchayatGhr(performaMasterBean.getPanchayat_ghar());
					if (performaMasterBean.getAngarwaries() != null
							&& Integer.parseInt(performaMasterBean.getAngarwaries()) > 0) {
						villagePerformaBean.setIsAngarwaries("Yes");
					}
					villagePerformaBean.setAngarwaries(performaMasterBean.getAngarwaries());

					villagePerformaBean.setAssemblyConstituency(performaMasterBean.getParConsName());
					villagePerformaBean.setCommCenterDhar(performaMasterBean.getCommunity_centre_or_dharamshala());

					if (performaMasterBean.getGovt_schools() != null
							&& Integer.parseInt(performaMasterBean.getGovt_schools()) > 0) {
						villagePerformaBean.setIsGovtSchools("Yes");
					}
					villagePerformaBean.setGovtSchools(performaMasterBean.getGovt_schools());

					villagePerformaBean.setGovtBuildings(performaMasterBean.getGovt_building());
					villagePerformaBean.setPrivateBuildings(performaMasterBean.getPrivate_building());
					if (performaMasterBean.getHealth_centre() != null
							&& Integer.parseInt(performaMasterBean.getHealth_centre()) > 0) {
						villagePerformaBean.setIsHealthCntr("Yes");
					}
					villagePerformaBean.setHealthCntr(performaMasterBean.getHealth_centre());

					if (performaMasterBean.getVillage_ponds() != null
							&& Integer.parseInt(performaMasterBean.getVillage_ponds()) > 0) {
						villagePerformaBean.setIsVillagePonds("Yes");
					}
					villagePerformaBean.setVillagePonds(performaMasterBean.getVillage_ponds());
					villagePerformaBean.setAreaSqrMtr(performaMasterBean.getArea_in_square_meters());
					villagePerformaBean.setSewerageConSc(performaMasterBean.getSew_connectio_sc_households());
					villagePerformaBean.setWaterConSc(performaMasterBean.getWater_connectio_sc_households());
					villagePerformaBean.setFemaleGnPop(performaMasterBean.getFemale_gen_population());
					villagePerformaBean.setFemaleScPop(performaMasterBean.getFemale_sc_population());
					if (performaMasterBean.getSew_connectio_sc_households() != null
							&& Integer.parseInt(performaMasterBean.getSew_connectio_sc_households()) > 0) {
						villagePerformaBean.setSewerageScheme("Yes");
					}
					villagePerformaBean.setHabitationWaterSupply(
							performaMasterBean.getSewrahabitation_distance_from_water_supply_schge());
					villagePerformaGridBean.add(villagePerformaBean);
				}

			}
		} catch (Exception ex) {
			// ex.printStackTrace();
		}
		villageDatagrid.setData(villagePerformaGridBean);
		return villageDatagrid;

	}

	private Datagrid getFiveYearPlanDataGrid(List<PerformaMasterBean> performaMaster, HttpServletRequest request) {

		Datagrid villageDatagrid = null;
		List<FiveYearPlanGridBean> villagePerformaGridBean = new ArrayList<FiveYearPlanGridBean>();
		Set<LabelValueBean> villages = null;
		try {
			villageDatagrid = Datagrid.getInstance();
			villageDatagrid.setDataClass(FiveYearPlanGridBean.class);
			FiveYearPlanGridBean fiveYearPlanGridBean = null;
			villages = new TreeSet<LabelValueBean>();
			if (!MisUtility.ifEmpty(performaMaster)) {
				for (PerformaMasterBean fiveYearPlanBean : performaMaster) {

					fiveYearPlanGridBean = new FiveYearPlanGridBean();
					villages.add(new LabelValueBean(
							fiveYearPlanBean.getVillageName() + " - (" + fiveYearPlanBean.getVillageId() + ")",
							fiveYearPlanBean.getVillageId()));

					if (fiveYearPlanBean.getIndependent_new_wss() != null
							|| fiveYearPlanBean.getIndependent_new_wss_cost() != null
							|| fiveYearPlanBean.getUpgradation_of_existing_wss() != null
							|| fiveYearPlanBean.getUpgradation_of_existing_wss_cost() != null
							|| fiveYearPlanBean.getSource_of_wss() != null
							|| fiveYearPlanBean.getShifted_to_canal_from_other() != null
							|| fiveYearPlanBean.getDriling_of_new_tubewell_machinery_size() != null
							|| fiveYearPlanBean.getDriling_of_new_tubewell_machinery_depth() != null
							|| fiveYearPlanBean.getDriling_of_new_tubewell_machinery_capacity() != null
							|| fiveYearPlanBean.getDriling_of_new_tubewell_machinery_cost() != null
							|| fiveYearPlanBean.getCanal_based_inlet_channel_length() != null
							|| fiveYearPlanBean.getCanal_based_inlet_channel_size_of_pipe() != null
							|| fiveYearPlanBean.getCanal_based_s_and_s_capacity() != null
							|| fiveYearPlanBean.getCanal_based_filteration_plan_type() != null
							|| fiveYearPlanBean.getCanal_based_filteration_plan_capacity() != null
							|| fiveYearPlanBean.getCanal_based_cost() != null
							|| fiveYearPlanBean.getOhsr_capacity() != null
							|| fiveYearPlanBean.getOhsr_full_supply_level() != null
							|| fiveYearPlanBean.getOhsr_cost() != null
							|| fiveYearPlanBean.getOther_structures_at_waterworks() != null
							|| fiveYearPlanBean.getOther_structures_at_waterworks_cost() != null
							|| fiveYearPlanBean.getDistribution_wss_to_village_type() != null
							|| fiveYearPlanBean.getDistribution_wss_to_village_length() != null
							|| fiveYearPlanBean.getDistribution_wss_within_village_type() != null
							|| fiveYearPlanBean.getDistribution_wss_within_village_length() != null
							|| fiveYearPlanBean.getDistribution_cost() != null
							|| fiveYearPlanBean.getNo_of_connections_100_mtr() != null
							|| fiveYearPlanBean.getNo_of_mtrs_100_mtr() != null
							|| fiveYearPlanBean.getCost_100_mtr() != null
							|| fiveYearPlanBean.getDisinfection_unit_type() != null
							|| fiveYearPlanBean.getDisinfection_unit_cost() != null
							|| fiveYearPlanBean.getWater_treatment_plant_in_case_of_quality_village_capacity() != null
							|| fiveYearPlanBean.getWater_treatment_plant_in_case_of_quality_village_type() != null
							|| fiveYearPlanBean.getWater_treatment_plant_in_case_of_quality_village_cost() != null
							|| fiveYearPlanBean.getBulk_water_meter_no() != null
									&& fiveYearPlanBean.getBulk_water_meter_cost() != null
							|| fiveYearPlanBean.getExtension_sanction_of_new_electric_connection_cost() != null) {

						fiveYearPlanGridBean.setVilllgId(fiveYearPlanBean.getVillageId());
						fiveYearPlanGridBean.setIndependentNewWSS(fiveYearPlanBean.getIndependent_new_wss());
						// fiveYearPlanGridBean.setIndependentNewWSSCost(fiveYearPlanBean.getIndependent_new_wss_cost());
						fiveYearPlanGridBean
								.setUpgradationexistingWSS(fiveYearPlanBean.getUpgradation_of_existing_wss());
						// fiveYearPlanGridBean.setUpgradation_of_existing_WSS_Cost1(fiveYearPlanBean.getUpgradation_of_existing_wss_cost());
						fiveYearPlanGridBean.setInstltion_Wtr_Treatment_plant1(
								fiveYearPlanBean.getInstltion_wtr_treatment_plant_cost());
						fiveYearPlanGridBean.setSource_of_WSS1(fiveYearPlanBean.getSource_of_wss());
						fiveYearPlanGridBean
								.setShifted_to_canal_from_Other1(fiveYearPlanBean.getShifted_to_canal_from_other());
						fiveYearPlanGridBean.setDriling_of_new_tubewell_machinery_size1(
								fiveYearPlanBean.getDriling_of_new_tubewell_machinery_size());
						fiveYearPlanGridBean.setDriling_of_new_tubewell_machinery_depth1(
								fiveYearPlanBean.getDriling_of_new_tubewell_machinery_depth());
						fiveYearPlanGridBean.setDriling_of_new_tubewell_machinery_capacity1(
								fiveYearPlanBean.getDriling_of_new_tubewell_machinery_capacity());
						fiveYearPlanGridBean.setDriling_of_new_tubewell_machinery_cost1(
								fiveYearPlanBean.getDriling_of_new_tubewell_machinery_cost());
						fiveYearPlanGridBean.setCanal_based_Inlet_channel_length1(
								fiveYearPlanBean.getCanal_based_inlet_channel_length());
						fiveYearPlanGridBean.setCanal_based_Inlet_channel_Size_of_pipe1(
								fiveYearPlanBean.getCanal_based_inlet_channel_size_of_pipe());
						fiveYearPlanGridBean
								.setCanal_based_s_and_s_Capacity1(fiveYearPlanBean.getCanal_based_s_and_s_capacity());
						fiveYearPlanGridBean.setCanal_based_Filteration_Plan_type1(
								fiveYearPlanBean.getCanal_based_filteration_plan_type());
						fiveYearPlanGridBean.setCanal_based_Filteration_Plan_capacity1(
								fiveYearPlanBean.getCanal_based_filteration_plan_capacity());
						fiveYearPlanGridBean.setCanal_based_Cost1(fiveYearPlanBean.getCanal_based_cost());
						fiveYearPlanGridBean.setoHSR_Capacity1(fiveYearPlanBean.getOhsr_capacity());
						fiveYearPlanGridBean.setoHSR_Full_Supply_Level1(fiveYearPlanBean.getOhsr_full_supply_level());
						fiveYearPlanGridBean.setOHSR_Cost(fiveYearPlanBean.getOhsr_cost());
						fiveYearPlanGridBean.setOther_structures_at_waterworks1(
								fiveYearPlanBean.getOther_structures_at_waterworks());
						fiveYearPlanGridBean.setOther_structures_at_waterworks_Cost1(
								fiveYearPlanBean.getOther_structures_at_waterworks_cost());
						fiveYearPlanGridBean.setDistribution_WSS_to_village_type1(
								fiveYearPlanBean.getDistribution_wss_to_village_type());
						fiveYearPlanGridBean.setDistribution_WSS_to_village_length1(
								fiveYearPlanBean.getDistribution_wss_to_village_length());
						fiveYearPlanGridBean.setDistribution_WSS_within_village_type1(
								fiveYearPlanBean.getDistribution_wss_within_village_type());
						fiveYearPlanGridBean.setDistribution_WSS_within_village_length1(fiveYearPlanBean.getDistribution_wss_within_village_length());
						fiveYearPlanGridBean.setDistribution_WSS_within_village_type_pvc1(fiveYearPlanBean.getPvc());
						
						fiveYearPlanGridBean.setDistribution_WSS_within_village_type_di_ms1(fiveYearPlanBean.getMs_di_ci());
						fiveYearPlanGridBean.setDistribution_WSS_within_village_type_gi1(fiveYearPlanBean.getNetwork_gi());
						fiveYearPlanGridBean.setDistribution_Cost1(fiveYearPlanBean.getDistribution_cost());
						fiveYearPlanGridBean
								.setNo_of_connections_100_mtr1(fiveYearPlanBean.getNo_of_connections_100_mtr());
						fiveYearPlanGridBean.setNo_of_mtrs_100_mtr1(fiveYearPlanBean.getNo_of_mtrs_100_mtr());
						fiveYearPlanGridBean.setCost_100_mtr1(fiveYearPlanBean.getCost_100_mtr());
						fiveYearPlanGridBean.setDisinfection_Unit_Type1(fiveYearPlanBean.getDisinfection_unit_type());
						fiveYearPlanGridBean.setDisinfection_Unit_Cost1(fiveYearPlanBean.getDisinfection_unit_cost());
						fiveYearPlanGridBean.setWater_Treatment_plant_in_case_of_quality_village_capacity1(
								fiveYearPlanBean.getWater_treatment_plant_in_case_of_quality_village_capacity());
						fiveYearPlanGridBean.setWater_Treatment_plant_in_case_of_quality_village_type1(
								fiveYearPlanBean.getWater_treatment_plant_in_case_of_quality_village_type());
						fiveYearPlanGridBean.setWater_Treatment_plant_in_case_of_quality_village_Cost1(
								fiveYearPlanBean.getWater_treatment_plant_in_case_of_quality_village_cost());
						fiveYearPlanGridBean.setBulk_Water_meter_no1(fiveYearPlanBean.getBulk_water_meter_no());
						fiveYearPlanGridBean.setBulk_Water_meter_cost1(fiveYearPlanBean.getBulk_water_meter_cost());
						fiveYearPlanGridBean.setExtension_Sanction_of_new_electric_connection_cost1(
								fiveYearPlanBean.getExtension_sanction_of_new_electric_connection_cost());
						
						//oHSRCTDiameter1
						fiveYearPlanGridBean.setoHSRCTDiameter1(fiveYearPlanBean.getCt_dia());
						//oHSR_CT_Depth1
						fiveYearPlanGridBean.setoHSR_CT_Depth1(fiveYearPlanBean.getCt_dep());
						//oHSR_UGS_RDia1
								fiveYearPlanGridBean.setoHSR_UGS_RDia1(fiveYearPlanBean.getUgsr_dia());
						//oHSR_UGSR_Depth1
										fiveYearPlanGridBean.setoHSR_UGSR_Depth1(fiveYearPlanBean.getUgsr_dep());

						System.out.println("inside grid loop----------->" + fiveYearPlanGridBean.toString());
						villagePerformaGridBean.add(fiveYearPlanGridBean);
					}
				}
			}
			request.getSession().setAttribute("villages", villages);
		} catch (Exception ex) {
		}

		villageDatagrid.setData(villagePerformaGridBean);
		return villageDatagrid;
	}

	private Datagrid getSourceDataGrid(List<PerformaMasterBean> villageSchemePerformaList) {

		Datagrid villageDatagrid = null;
		List<VillageSourceGridBean> villagePerformaGridBean = new ArrayList<VillageSourceGridBean>();
		try {
			villageDatagrid = Datagrid.getInstance();
			villageDatagrid.setDataClass(VillageSourceGridBean.class);
			
			if (!MisUtility.ifEmpty(villageSchemePerformaList)) {
				VillageSourceGridBean villagePerformaBean = null;
				for (PerformaMasterBean villageSchemePerforma : villageSchemePerformaList) {

					villagePerformaBean = new VillageSourceGridBean();

					villagePerformaBean.setVilIds(villageSchemePerforma.getVillageId());
					villagePerformaBean.setSchmSource(villageSchemePerforma.getSchemeSource());
					villagePerformaBean.setSchmType(villageSchemePerforma.getScheme_type());
					villagePerformaBean.setDateComm(
							MisUtility.convertDateString(villageSchemePerforma.getDate_of_commissioning()));
					villagePerformaBean.setProgramId(villageSchemePerforma.getProg_id());
					villagePerformaBean.setSrvcLevel(villageSchemePerforma.getService_level());
					villagePerformaBean
							.setSchemeUpgrd(MisUtility.convertDateString(villageSchemePerforma.getScheme_upgraded()));
					villagePerformaBean.setSchemeExpen(villageSchemePerforma.getScheme_expenditure());
					villagePerformaBean.setDepthTubewell(villageSchemePerforma.getDepth_of_tubewell());

					// 2
					villagePerformaBean.setSizeTubewell(villageSchemePerforma.getSize_of_tubewell());
					villagePerformaBean
							.setYearDrilling(MisUtility.convertDateString(villageSchemePerforma.getYear_of_drilling()));
					villagePerformaBean
							.setDischargeCommun(villageSchemePerforma.getOriginal_discharge_at_time_of_commissioning());
					villagePerformaBean.setPresentDischrg(villageSchemePerforma.getPresent_discharge());
					villagePerformaBean.setPresentSpringLvl(villageSchemePerforma.getPresent_spring_level());
					villagePerformaBean.setInstallationNewMachinery(
							MisUtility.convertDateString(villageSchemePerforma.getInstallation_of_new_machinery()));
					villagePerformaBean.setCapacityMachinery(villageSchemePerforma.getCapacity_of_machinery());
					villagePerformaBean.setInltType(villageSchemePerforma.getInlet_channel_type());
					villagePerformaBean.setInletLnght(villageSchemePerforma.getInlet_channel_lenght());
					villagePerformaBean.setPipType(villageSchemePerforma.getPipe_type());
					villagePerformaBean.setCapacitySTank(villageSchemePerforma.getCapacity_of_ss_tank());
					villagePerformaBean.setCapacityCTank(villageSchemePerforma.getCapacity_of_cw_tank());
					villagePerformaBean.setCapacityHTank(villageSchemePerforma.getCapacity_of_hl_tank());
					villagePerformaBean.setFilterType(villageSchemePerforma.getFiltertion_type());
					villagePerformaBean.setFilterNo(villageSchemePerforma.getFiltertion_no());
					villagePerformaBean.setCapacityRawWatr(villageSchemePerforma.getCapacity_of_raw_water());
					villagePerformaBean.setCapacityClrWater(villageSchemePerforma.getCapacity_of_clear_water());
					villagePerformaBean.setNoOhsrOhsr(villageSchemePerforma.getNo_of_ohsr());
					villagePerformaBean.setoHSRConstructionDate(
							MisUtility.convertDateString(villageSchemePerforma.getOhsr_construction_date()));
					villagePerformaBean.setoHSRCap(villageSchemePerforma.getOhsr_capcity());
					//villagePerformaBean.setoHSRCt(villageSchemePerforma.getohsrc);
					//villagePerformaBean.setoHSRCt(villageSchemePerforma.getOhsr_ct());
					//villagePerformaBean.setoHSRUgsr(villageSchemePerforma.getOhsr_ugsr());
					villagePerformaBean.setoHSRFullSuplyLvl1(villageSchemePerforma.getOhsr_full_supply_level1());
					villagePerformaBean.setoHSRWorkingCond(villageSchemePerforma.getOhsr_working_condition());
					villagePerformaBean.setoHSRCond(villageSchemePerforma.getOhsr_working_condition_if_no());
					villagePerformaBean.setoHSRDismantling1(villageSchemePerforma.getDismantling_received());

					villagePerformaBean
							.setGround_water_potablestatus(villageSchemePerforma.getGround_water_potable_status());
					villagePerformaBean.setGround_water_potableNo(villageSchemePerforma.getGround_water_potable_no());
					villagePerformaBean
							.setPreventive_measuresadopted(villageSchemePerforma.getPreventive_measures_adopted());
					villagePerformaBean.setCapacity_ofplant(villageSchemePerforma.getCapacity_of_plant());
					villagePerformaBean.setDateInstallation(
							MisUtility.convertDateString(villageSchemePerforma.getDate_of_installation()));
					villagePerformaBean.setBeing_operatedby(villageSchemePerforma.getBeing_operated_by());
					villagePerformaBean
							.setVolume_of_Water_Dailybasis(villageSchemePerforma.getVolume_of_water_daily_basis());
					villagePerformaBean.setDisposal_of_rejectwater(villageSchemePerforma.getDisposal_of_reject_water());
					villagePerformaBean
							.setPenetration_inpercentage(villageSchemePerforma.getPenetration_in_percentage());
					villagePerformaBean
							.setPresent_rate_of_usercharges(villageSchemePerforma.getPresent_rate_of_user_charges());
					villagePerformaBean.setSeperate_SanctionedLoad(villageSchemePerforma.getSeperate_sanctioned_load());
					villagePerformaBean.setSeperate_Pending_eletricbill31032017(
							villageSchemePerforma.getSeperate_pending_eletric_bill31032017());
					villagePerformaBean.setAverage_monthly_bill_of_Treatmentplant(
							villageSchemePerforma.getAverage_monthly_bill_of_treatment_plant());

					villagePerformaBean.setDisinfType(villageSchemePerforma.getDisinfection_type());
					villagePerformaBean.setDisInstallationTime(
							MisUtility.convertDateString(villageSchemePerforma.getDisinfection_instalation_time()));
					villagePerformaBean.setDisinPrStatus(villageSchemePerforma.getDisinfection_present_status());
					villagePerformaBean.setSchemeOperatBy(villageSchemePerforma.getScheme_operated_by());
					villagePerformaBean.setStafScheme(villageSchemePerforma.getDwss_operated_arrangement());
					villagePerformaBean.setSanctionLoad(villageSchemePerforma.getSanctioned_load());
					villagePerformaBean.setPendingBill3006(villageSchemePerforma.getPending__bill_30062017());
					villagePerformaBean.setPendingBill3103(villageSchemePerforma.getPending__bill_31032017());
					villagePerformaBean.setAvgMonthBillWSS(villageSchemePerforma.getAvg_month_billof_wss());

					villagePerformaGridBean.add(villagePerformaBean);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		villageDatagrid.setData(villagePerformaGridBean);
		return villageDatagrid;

	}

	/*
	 * public ActionForward fetchScheme(ActionMapping mapping, ActionForm form,
	 * HttpServletRequest request, HttpServletResponse response) throws
	 * MISException { List<SchemeHeaderBean> schemeHeaderBeans = null;
	 * StringBuffer buffer = new StringBuffer(); List<String> statusList = new
	 * ArrayList<String>(); statusList.add(MISConstants.MASTER_STATUS_APPROVED);
	 * statusList.add(MISConstants.MASTER_STATUS_VERIFIED); SchemeHeaderBean
	 * schemeHeaderBean = new SchemeHeaderBean(); try {
	 * if(MisUtility.ifEmpty(request.getParameter("locationId"))){
	 * schemeHeaderBean.setLocationId(request.getParameter("locationId"));
	 * schemeHeaderBeans = schemeHeaderDao.findSchemeHeader(schemeHeaderBean,
	 * statusList); buffer.append("<option value='NA'>");
	 * buffer.append("Select"); buffer.append("</option>"); for
	 * (SchemeHeaderBean schemeHeaderBean2 : schemeHeaderBeans) { buffer.append(
	 * "<option value=\""
	 * ).append(schemeHeaderBean2.getSchemeId()).append("\">");
	 * buffer.append(schemeHeaderBean2.getSchemeName()+" - ("
	 * +schemeHeaderBean2.getSchemeId()+")"); buffer.append("</option>"); } }
	 * PrintWriter out = response.getWriter();
	 * if(MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 1 ){
	 * out.print(buffer.toString()); } } catch (DataAccessException e) {
	 * log.error(e); } catch (IOException e) { log.error(e);
	 * e.printStackTrace(); } catch (Exception e) { log.error(e);
	 * e.printStackTrace(); }
	 * 
	 * return null; }
	 */

	public ActionForward fetchVillage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws MISException {
		System.out.println("inside-fetchVillage->" + request.getParameter("multipleSource"));
		List<PerformaMasterBean> performaMasterBeans = null;
		if (request.getParameter("multipleSource").equalsIgnoreCase("Yes")) {

			StringBuffer buffer = new StringBuffer();
			List<String> statusList = new ArrayList<String>();
			statusList.add(MISConstants.MASTER_STATUS_APPROVED);
			statusList.add(MISConstants.MASTER_STATUS_VERIFIED);
			PerformaMasterBean performaMasterBean = new PerformaMasterBean();
			// String ss=request.getSession().getAttribute("schemeId"));
			try {
				// if(MisUtility.ifEmpty(request.getParameter("schemeId"))){
				performaMasterBean.setSchemeId(String.valueOf(request.getSession().getAttribute("schemeId")));
				performaMasterBeans = performaMasterDao.findVillage(performaMasterBean, statusList);
				buffer.append("<option value='NA'>");
				buffer.append("Select");
				buffer.append("</option>");
				for (PerformaMasterBean performaMasterBean2 : performaMasterBeans) {
					
					buffer.append("<option value=\"").append(performaMasterBean2.getVillageId()).append("\">");
					buffer.append(
							performaMasterBean2.getVillageName() + " - (" + performaMasterBean2.getVillageId() + ")");
					buffer.append("</option>");
				}
				// }
				PrintWriter out = response.getWriter();
				if (MisUtility.ifEmpty(buffer.toString()) && buffer.length() > 0) {
					out.print(buffer.toString());
				}
			} catch (DataAccessException e) {
				log.error(e);
			} catch (IOException e) {
				log.error(e);
				e.printStackTrace();
			} catch (Exception e) {
				log.error(e);
				e.printStackTrace();
			}
		}
		return null;
	}

	public String validateVillageGrid(PerformaMasterForm performaMasterForm) throws MISException {
		System.out.println("inside validateVillageGrid");

		boolean flag = false;
		boolean flag1 = false;
		StringBuffer buffer = new StringBuffer();
		try {
			int count = 0;
			@SuppressWarnings("unchecked")
			Collection<VillagePerformaGridBean> villagePerformaGrid = performaMasterForm.getVillagePerformaGrid()
					.getModifiedData();

			if (!(MisUtility.ifEmpty(villagePerformaGrid))) {
				for (VillagePerformaGridBean performaGrid : villagePerformaGrid) {

					if (count == 0) {
						if (!MisUtility.ifEmptyField(performaGrid.getPanchayatGhr())) {
							buffer.append("No of Panchayat Ghar field could not be empty ");
							buffer.append("<br>");
							flag1 = true;
						}
						if (MisUtility.ifEmptyField(performaGrid.getPanchayatGhr())) {
							flag = validateIntData(performaGrid.getPanchayatGhr());
							if (flag) {
								buffer.append("Please enter only number in Panchayat Ghar");
								buffer.append("<br>");
								flag1 = true;
							}

						}
						if (!MisUtility.ifEmptyField(performaGrid.getCommCenterDhar())) {
							buffer.append("Community center/Dharamshala field could not be empty");
							buffer.append("<br>");
							flag1 = true;
						}
						
						if (MisUtility.ifEmptyField(performaGrid.getCommCenterDhar())) {
							flag = validateIntData(performaGrid.getCommCenterDhar());
							if (flag) {
								buffer.append("Please enter only number in Community center/Dharamshala");
								buffer.append("<br>");
								flag1 = true;
							}

						}

						if (performaGrid.getIsAngarwaries().equalsIgnoreCase("Yes")) {
							if (!MisUtility.ifEmptyField(performaGrid.getAngarwaries())) {
								buffer.append("No of Angarwaries field could not be empty");
								buffer.append("<br>");
								flag1 = true;
							}
							if (!MisUtility.ifEmptyField(performaGrid.getGovtBuildings())) {
								buffer.append("No of Govt buildings for Angarwaries could not be empty");
								buffer.append("<br>");
								flag1 = true;
							}
							if (!MisUtility.ifEmptyField(performaGrid.getPrivateBuildings())) {
								buffer.append("No of Private buildings for Angarwaries could not be empty");
								buffer.append("<br>");
								flag1 = true;
							}
							
						}
						
						
						if (performaGrid.getIsAngarwaries().equalsIgnoreCase("No")) {
							if (MisUtility.ifEmptyFieldNo(performaGrid.getAngarwaries())) {
								buffer.append(
										"No of Angarwaries field should be empty as selection in Angarwaries field is No");
								buffer.append("<br>");
								flag1 = true;
							}
							if (MisUtility.ifEmptyFieldNo(performaGrid.getGovtBuildings())) {
								buffer.append(
										"No of Govt buildings should be empty as selection in Angarwaries field is No");
								buffer.append("<br>");
								flag1 = true;
							}
							if (MisUtility.ifEmptyFieldNo(performaGrid.getPrivateBuildings())) {
								buffer.append(
										"No of Private building should be empty as selection in Angarwaries field is No");
								buffer.append("<br>");
								flag1 = true;
							}
						}

						if (performaGrid.getIsAngarwaries().equalsIgnoreCase("Yes")) {
							if (MisUtility.ifEmptyField(performaGrid.getAngarwaries())) {

								flag = validateIntData(performaGrid.getAngarwaries());
								if (flag) {
									buffer.append("Please enter only number in No of Angarwaries field");
									buffer.append("<br>");
									flag1 = true;
								}
							}
							if (MisUtility.ifEmptyField(performaGrid.getGovtBuildings())) {

								flag = validateIntData(performaGrid.getGovtBuildings());
								if (flag) {
									buffer.append("Please enter only number in No of Govt building field");
									buffer.append("<br>");
									flag1 = true;
								}
							}
							if (MisUtility.ifEmptyField(performaGrid.getPrivateBuildings())) {

								flag = validateIntData(performaGrid.getPrivateBuildings());
								if (flag) {
									buffer.append("Please enter only number in No of Private building field");
									buffer.append("<br>");
									flag1 = true;
								}
							}
							
							int totalAngarwaries=Integer.parseInt(performaGrid.getAngarwaries());
							int govtBuildings=Integer.parseInt(performaGrid.getGovtBuildings());
							int privateBuildings=Integer.parseInt(performaGrid.getPrivateBuildings());
							int total_buildings=govtBuildings+privateBuildings;
							if(total_buildings>totalAngarwaries){
								buffer.append("Total of Govt and Private Buildings should not be greater than  No of Angarwaries");
								buffer.append("<br>");
								flag1 = true;
							}
							
							
							
						}

						if (performaGrid.getIsGovtSchools().equalsIgnoreCase("Yes")) {
							if (!MisUtility.ifEmptyField(performaGrid.getGovtSchools())) {
								buffer.append("No of Govt. Schools field could not be empty");
								buffer.append("<br>");
								flag1 = true;
							}
						}
						if (performaGrid.getIsGovtSchools().equalsIgnoreCase("No")) {
							if (MisUtility.ifEmptyFieldNo(performaGrid.getGovtSchools())) {
								buffer.append(
										"No of Govt. Schools field should be empty as selection in Govt Schools field is No");
								buffer.append("<br>");
								flag1 = true;
							}
						}

						if (performaGrid.getIsGovtSchools().equalsIgnoreCase("Yes")) {
							if (MisUtility.ifEmptyField(performaGrid.getGovtSchools())) {

								flag = validateIntData(performaGrid.getGovtSchools());
								if (flag) {
									buffer.append("Please enter only number in No of Govt. Schools field");
									buffer.append("<br>");
									flag1 = true;
								}

							}
						}

						if (performaGrid.getIsHealthCntr().equalsIgnoreCase("Yes")) {
							if (!MisUtility.ifEmptyField(performaGrid.getHealthCntr())) {
								buffer.append("No of Health Centres field could not be empty");
								buffer.append("<br>");
								flag1 = true;
							}
						}
						if (performaGrid.getIsHealthCntr().equalsIgnoreCase("No")) {
							if (MisUtility.ifEmptyFieldNo(performaGrid.getHealthCntr())) {
								buffer.append(
										"No of Health Centres field should not be empty as selection in Health Centres field is No");
								buffer.append("<br>");
								flag1 = true;
							}
						}
						if (performaGrid.getIsHealthCntr().equalsIgnoreCase("Yes")) {
							if (MisUtility.ifEmptyField(performaGrid.getHealthCntr())) {

								flag = validateIntData(performaGrid.getHealthCntr());
								if (flag) {
									buffer.append("Please enter only number in No of Health Centres field");
									buffer.append("<br>");
									flag1 = true;
								}

							}
						}
						if (performaGrid.getIsVillagePonds().equalsIgnoreCase("Yes")) {
							if (!MisUtility.ifEmptyField(performaGrid.getVillagePonds())) {
								buffer.append("No of Village Ponds field could not be empty");
								buffer.append("<br>");
								flag1 = true;
							}
							if (!MisUtility.ifEmptyField(performaGrid.getAreaSqrMtr())) {
								buffer.append("Area in Square meter field could not be empty");
								buffer.append("<br>");
								flag1 = true;
							}
						}
						if (performaGrid.getIsVillagePonds().equalsIgnoreCase("No")) {
							if (MisUtility.ifEmptyFieldNo(performaGrid.getVillagePonds())) {
								buffer.append(
										"No of Village Ponds field should  be empty as selection in Village Ponds field is No");
								buffer.append("<br>");
								flag1 = true;
							}
							if (MisUtility.ifEmptyField(performaGrid.getAreaSqrMtr())) {
								buffer.append(
										"Area in Square meter field should be empty as selection in Village Ponds field is No");
								buffer.append("<br>");
								flag1 = true;
							}
						}

						if (performaGrid.getIsVillagePonds().equalsIgnoreCase("Yes")) {
							if (MisUtility.ifEmptyField(performaGrid.getVillagePonds())) {
								flag = validateIntData(performaGrid.getVillagePonds());
								if (flag) {
									buffer.append("Please enter only number in No of Village Ponds field");
									buffer.append("<br>");
									flag1 = true;
								}

							}
							if (MisUtility.ifEmptyField(performaGrid.getAreaSqrMtr())) {
								flag = validateDoubleData(performaGrid.getAreaSqrMtr());
								if (flag) {
									buffer.append(
											"Please enter only numeric value(String and Special character are not allowed instead of dot(.)) in Area in Square meter field");
									buffer.append("<br>");
									flag1 = true;
								}

							}
						}
						if (performaGrid.getSewerageScheme().equalsIgnoreCase("Yes")) {
							if (!MisUtility.ifEmptyField(performaGrid.getSewerageConSc())) {
								buffer.append("No of Sewer Connection in SC households field could not be empty");
								buffer.append("<br>");
								flag1 = true;
							}
						}

						if (performaGrid.getSewerageScheme().equalsIgnoreCase("No")) {
							if (MisUtility.ifEmptyFieldNo(performaGrid.getSewerageConSc())) {
								buffer.append(
										"No of Sewer Connection in SC households field should be empty as selection in Sewerage Scheme field is No ");
								buffer.append("<br>");
								flag1 = true;
							}
						}
						if (performaGrid.getSewerageScheme().equalsIgnoreCase("Yes")) {
							if (MisUtility.ifEmptyField(performaGrid.getSewerageConSc())) {
								flag = validateIntData(performaGrid.getSewerageConSc());
								if (flag) {
									buffer.append("Please enter only number in No of Village Ponds field");
									buffer.append("<br>");
									flag1 = true;
								}

							}
						}

						if (!MisUtility.ifEmptyField(performaGrid.getFemaleScPop())) {
							buffer.append("Number of Female in SC population field could not be empty");
							buffer.append("<br>");
							flag1 = true;
						}

						if (MisUtility.ifEmptyField(performaGrid.getFemaleScPop())) {
							flag = validateIntData(performaGrid.getFemaleScPop());
							if (flag) {
								buffer.append("Please enter only number in Number of Female in SC population field");
								buffer.append("<br>");
								flag1 = true;
							}

						}
						if (!MisUtility.ifEmptyField(performaGrid.getFemaleGnPop())) {
							buffer.append("Number of Female in Gen population field could not be empty");
							buffer.append("<br>");
							flag1 = true;
						}
						if (MisUtility.ifEmptyField(performaGrid.getFemaleGnPop())) {
							flag = validateIntData(performaGrid.getFemaleGnPop());
							if (flag) {
								buffer.append("Please enter only number in Number of Female in Gen population field");
								buffer.append("<br>");
								flag1 = true;
							}

						}
						if (!MisUtility.ifEmptyField(performaGrid.getWaterConSc())) {
							buffer.append("Water Connection in SC households field could not be empty");
							buffer.append("<br>");
							flag1 = true;
						}
						if (MisUtility.ifEmptyField(performaGrid.getWaterConSc())) {
							flag = validateIntData(performaGrid.getWaterConSc());
							if (flag) {
								buffer.append("Please enter only number in Water Connection in SC households field");
								buffer.append("<br>");
								flag1 = true;
							}

						}
						if (!MisUtility.ifEmptyField(performaGrid.getHabitationWaterSupply())) {
							buffer.append(
									"Distance of habitation/village from Water supply schemes field could not be empty");
							buffer.append("<br>");
							flag1 = true;
						}
						if (MisUtility.ifEmptyField(performaGrid.getHabitationWaterSupply())) {
							flag = validateDoubleData(performaGrid.getHabitationWaterSupply());
							if (flag) {
								buffer.append(
										"Please enter only number in Distance of habitation/village from Water supply schemes field");
								buffer.append("<br>");
								flag1 = true;
							}

						}

						if (flag1) {
							count++;
						}
					}
				}
			}
		} catch (Exception e) {

		}
		return buffer.toString();
	}

	private boolean validateIntData(String data) {
		System.out.println("data------>"+data);
		boolean flag = false;
		try {
			if (!data.matches("[0-9]+")) {
				flag = true;
			}
		} catch (Exception e) {

		}
		return flag;
	}

	private boolean validateDoubleData(String data) {

		boolean flag = false;
		try {
			if (!data.matches("[0-9]+([.][0-9]{1,8})?") && !data.matches("([.][0-9]{1,8})?")) {
				flag = true;
			}
		} catch (Exception e) {

		}
		return flag;
	}
}