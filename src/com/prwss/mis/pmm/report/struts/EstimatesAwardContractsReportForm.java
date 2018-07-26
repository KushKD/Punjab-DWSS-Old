/**
 * 
 */
package com.prwss.mis.pmm.report.struts;

import org.apache.struts.validator.ValidatorForm;

import com.prwss.mis.common.util.MisUtility;

public class EstimatesAwardContractsReportForm extends ValidatorForm {
	private static final long serialVersionUID = 8095551153236274729L;
	private String currentDate= MisUtility.now("dd-MM-yyyy");
	private String fileTitle;
	private String selectZone="A";
	private String selectCircle="A";
	private String selectDistrict="A";
	private String selectProgram="A";
	private String selectPeriod="A";
	private String zoneId;
	private String circleId;
	private String districtId;
	private String programId;
	private String schemeTypeR;
	private String IDAType;
	private String schemeTypeRR;
	private String SMType;
	private String village_category;
	private String componentName;
	private String division="";
	
	
	
	
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public String getVillage_category() {
		return village_category;
	}
	public void setVillage_category(String village_category) {
		this.village_category = village_category;
	}
	public String getSMType() {
		return SMType;
	}
	public void setSMType(String sMType) {
		SMType = sMType;
	}
	public String getSchemeTypeRR() {
		return schemeTypeRR;
	}
	public void setSchemeTypeRR(String schemeTypeRR) {
		this.schemeTypeRR = schemeTypeRR;
	}
	public String getIDAType() {
		return IDAType;
	}
	public void setIDAType(String iDAType) {
		IDAType = iDAType;
	}
	public String getSchemeTypeR() {
		return schemeTypeR;
	}
	public void setSchemeTypeR(String schemeTypeR) {
		this.schemeTypeR = schemeTypeR;
	}
	public String getSchemeType() {
		return schemeType;
	}
	public void setSchemeType(String schemeType) {
		this.schemeType = schemeType;
	}
	private String MSHabitation;
	private String schemeType;
	private String asOnDate=this.currentDate;
	private String fromDate=this.currentDate;
	private String toDate=this.currentDate;
	private String approvalStatus="UA";
	private String selectReport="swap_nonswap_abstract";
	private String jasperFile;
	private String blockId;
	private String divisionsId;
	private String constituencyId;
	private String swap="A";
	private String swap1="A";
	private String swap2="A";
	private String swap3="A";
	private String swap4="A";
	private String IDA5;
	private String selectReport6;
	private String yearType;
	
	private String circleName;
	
	
	
	
	
	public String getCircleName() {
		return circleName;
	}
	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}
	/**
	 * @return the selectReport6
	 */
	public String getSelectReport6() {
		return selectReport6;
	}
	/**
	 * @param selectReport6 the selectReport6 to set
	 */
	public void setSelectReport6(String selectReport6) {
		this.selectReport6 = selectReport6;
	}
	//private String schemeTypeRRR;
	//private String IDA;
	private String IBRD;
	
	//private String swap4="A";
	
	private String status="A";
	
	
	/*
	public String getSwap4() {
		return swap4;
	}
	public void setSwap4(String swap4) {
		this.swap4 = swap4;
	}*/
	
	public String getStatus() {
		return status;
	}
	/*public String getIDA() {
		return IDA;
	}
	public void setIDA(String iDA) {
		IDA = iDA;
	}*/
	
	
	/*public String getSchemeTypeRRR() {
		return schemeTypeRRR;
	}
	public void setSchemeTypeRRR(String schemeTypeRRR) {
		this.schemeTypeRRR = schemeTypeRRR;
	}*/
	public String getYearType() {
		return yearType;
	}
	public void setYearType(String yearType) {
		this.yearType = yearType;
	}
	public String getMSHabitation() {
		return MSHabitation;
	}
	
	public String getIDA5() {
		return IDA5;
	}
	public void setIDA5(String iDA5) {
		IDA5 = iDA5;
	}
	public void setMSHabitation(String mSHabitation) {
		MSHabitation = mSHabitation;
	}
	public String getSwap4() {
		return swap4;
	}
	public void setSwap4(String swap4) {
		this.swap4 = swap4;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSwap3() {
		return swap3;
	}
	public void setSwap3(String swap3) {
		this.swap3 = swap3;
	}
	public String getSwap2() {
		return swap2;
	}
	public void setSwap2(String swap2) {
		this.swap2 = swap2;
	}
	private String ncfc;
	public String getNcfc() {
		return ncfc;
	}
	public void setNcfc(String ncfc) {
		this.ncfc = ncfc;
	}
	private String ncFc;
	
	public String getNcFc() {
		return ncFc;
	}
	public void setNcFc(String ncFc) {
		this.ncFc = ncFc;
	}
	private String monthId;
	private String finYearId;
	
	public String getBlockId() {
		return blockId;
	}
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}
	public String getDivisionsId() {
		return divisionsId;
	}
	public void setDivisionsId(String divisionsId) {
		this.divisionsId = divisionsId;
	}
	public String getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(String constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	
	public String getSelectZone() {
		return selectZone;
	}
	public void setSelectZone(String selectZone) {
		this.selectZone = selectZone;
	}
	public String getSelectCircle() {
		return selectCircle;
	}
	public void setSelectCircle(String selectCircle) {
		this.selectCircle = selectCircle;
	}
	public String getSelectDistrict() {
		return selectDistrict;
	}
	public void setSelectDistrict(String selectDistrict) {
		this.selectDistrict = selectDistrict;
	}
	public String getSelectProgram() {
		return selectProgram;
	}
	public void setSelectProgram(String selectProgram) {
		this.selectProgram = selectProgram;
	}
	public void setSelectPeriod(String selectPeriod) {
		this.selectPeriod = selectPeriod;
	}
	public String getSelectPeriod() {
		return selectPeriod;
	}
	public String getAsOnDate() {
		return asOnDate;
	}
	public void setAsOnDate(String asOnDate) {
		this.asOnDate = asOnDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getFromDate() {
		return fromDate;
	}			
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public void setSelectReport(String selectReport) {
		this.selectReport = selectReport;
	}
	public String getSelectReport() {
		return selectReport;
	}
	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
	public String getZoneId() {
		return zoneId;
	}
	public void setCircleId(String circleId) {
		this.circleId = circleId;
	}
	public String getCircleId() {
		return circleId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	public String getProgramId() {
		return programId;
	}
	public void setJasperFile(String jasperFile) {
		this.jasperFile = jasperFile;
	}
	public String getJasperFile() {
		System.out.println("inside form--selectReport-->"+selectReport);
		if(selectReport.equals("PMMRPT001_50"))
			return "/pmm/reports/PMMRPT001_50.jasper";
		if(selectReport.equals("PMMRPT001_51"))
			return "/pmm/reports/PMMRPT001_51.jasper";
		if(selectReport.equals("PMMRPT001_52"))
			return "/pmm/reports/PMMRPT001_52.jasper";
		
		if(selectReport.equals("TTL001_0011"))
			return "/pmm/reports/TTL001_001.jasper";
		if(selectReport.equals("TTL001_002"))
			return "/pmm/reports/TTL001_002.jasper";
		if(selectReport.equals("TTL001_003"))
			return "/pmm/reports/TTL001_003.jasper";
		
		if(selectReport.equals("PMMRPT001_A2.4_2A_new"))
			return "/pmm/reports/PMMRPT001_A2.4_2A_new.jasper";
		if(selectReport.equals("PMMRPT001_A2.4_2B_new"))
			return "/pmm/reports/PMMRPT001_A2.4_2B_new.jasper";
		if(selectReport.equals("PMMRPT001_A2.4_A1_new"))
			return "/pmm/reports/PMMRPT001_A2.4_A1_new.jasper";
		
		if(selectReport.equals("RPT001_1_3_div"))
			return "/pmm/reports/RPT001_1_3_div.jasper";
		
		if(selectReport.equals("RPT001_1_2_consti"))
			return "/pmm/reports/RPT001_1_2_consti.jasper";
		
		if(selectReport.equals("RPT001_1_1_blk"))
			return "/pmm/reports/RPT001_1_1_blk.jasper";
		
		
		if(selectReport.equals("RPT001_2_div"))
			return "/pmm/reports/RPT001_2_div.jasper";
		
		if(selectReport.equals("RPT001_2_consti"))
			return "/pmm/reports/RPT001_2_consti.jasper";
		
		if(selectReport.equals("RPT001_2_blk"))
			return "/pmm/reports/RPT001_2_blk.jasper";
		
		if(selectReport.equals("RPT001_3_div"))
			return "/pmm/reports/RPT001_3_div.jasper";
		
		if(selectReport.equals("RPT001_3_consti"))
			return "/pmm/reports/RPT001_3_consti.jasper";
		
		if(selectReport.equals("RPT001_3_blk"))
			return "/pmm/reports/RPT001_3_blk.jasper";
		
		if(selectReport.equals("RPT001_4_div"))
			return "/pmm/reports/RPT001_4_div.jasper";
		
		if(selectReport.equals("RPT001_4_consti"))
			return "/pmm/reports/RPT001_4_consti.jasper";
		
		if(selectReport.equals("RPT001_4_blk"))
			return "/pmm/reports/RPT001_4_blk.jasper";
		
		
		if(selectReport.equals("RO0001_001"))
			return "/pmm/reports/RO0001_001.jasper";
		if(selectReport.equals("RO0001_002"))
			return "/pmm/reports/RO0001_002.jasper";
		
		
		if(selectReport.equals("RPT001_5_div"))
			return "/pmm/reports/RPT001_5_div.jasper";
		
		if(selectReport.equals("RPT001_5_consti"))
			return "/pmm/reports/RPT001_5_consti.jasper";
		
		if(selectReport.equals("RPT001_5_blk"))
			return "/pmm/reports/RPT001_5_blk.jasper";
		

		if(selectReport.equals("RPT001_6_div"))
			return "/pmm/reports/RPT001_6_div.jasper";
		
		if(selectReport.equals("RPT001_6_consti"))
			return "/pmm/reports/RPT001_6_consti.jasper";
		
		if(selectReport.equals("RPT001_6_blk"))
			return "/pmm/reports/RPT001_6_blk.jasper";
		
		
		
		

		if(selectReport.equals("PMMRPT001_A12_abs")){
			return "/pmm/reports/PMMRPT001_A12_abs.jasper";
		}
		if(selectReport.equals("PMMRPT001_1"))
			return "/pmm/reports/PMMRPT001_1.jasper";
		if(selectReport.equals("PMMRPT001_2"))
			return "/pmm/reports/PMMRPT001_2.jasper";
		if(selectReport.equals("PMMRPT001_3"))
			return "/pmm/reports/PMMRPT001_3.jasper";
		if(selectReport.equals("PMMRPT001_1_dtl"))
			return "/pmm/reports/PMMRPT001_1_dtl.jasper";
		if(selectReport.equals("PMMRPT001_2_dtl"))
			return "/pmm/reports/PMMRPT001_2_dtl.jasper";
		if(selectReport.equals("PMMRPT001_3_dtl"))
			return "/pmm/reports/PMMRPT001_3_dtl.jasper";
		if(selectReport.equals("PMMRPT001_4"))
			return "/pmm/reports/PMMRPT001_4.jasper";
		if(selectReport.equals("PMMRPT001_5"))
			return "/pmm/reports/PMMRPT001_5.jasper";
		if(selectReport.equals("PMMRPT001_6"))
			return "/pmm/reports/PMMRPT001_6.jasper";
		if(selectReport.equals("PMMRPT001_7"))
			return "/pmm/reports/PMMRPT001_7.jasper";
		if(selectReport.equals("PMMRPT001_8"))
			return "/pmm/reports/PMMRPT001_8.jasper";
		if(selectReport.equals("PMMRPT001_9"))
			return "/pmm/reports/PMMRPT001_9.jasper";
		if(selectReport.equals("PMMRPT001_10"))
			return "/pmm/reports/PMMRPT001_10.jasper";
		if(selectReport.equals("PMMRPT001_11"))
			return "/pmm/reports/PMMRPT001_11.jasper";
		if(selectReport.equals("PMMRPT001_12"))
			return "/pmm/reports/PMMRPT001_12.jasper";
		if(selectReport.equals("PMMRPT001_13"))
			return "/pmm/reports/PMMRPT001_13.jasper";
		if(selectReport.equals("PMMRPT001_14"))
			return "/pmm/reports/PMMRPT001_14.jasper";
		if(selectReport.equals("PMMRPT001_15"))
			return "/pmm/reports/PMMRPT001_15.jasper";
		if(selectReport.equals("PMMRPT001_16"))
			return "/pmm/reports/PMMRPT001_16.jasper";
		if(selectReport.equals("PMMRPT001_17"))
			return "/pmm/reports/PMMRPT001_17.jasper";
		if(selectReport.equals("PMMRPT001_18"))
			return "/pmm/reports/PMMRPT001_18.jasper";
		if(selectReport.equals("PMMRPT001_19"))
			return "/pmm/reports/PMMRPT001_19.jasper";
		if(selectReport.equals("ibrd1"))
			return "/pmm/reports/ibrd1.jasper";
		if(selectReport.equals("Abs_MH"))
			return "/pmm/reports/Abs_MH.jasper";		
		if(selectReport.equals("Dtl_MH"))
			return "/pmm/reports/Dtl_MH.jasper";
		
		
		
		
		if(selectReport.equals("PMMRPT001_20"))
			return "/pmm/reports/PMMRPT001_20.jasper";
		if(selectReport.equals("PMMRPT001_21"))
			return "/pmm/reports/PMMRPT001_21.jasper";
		if(selectReport.equals("PMMRPT001_21_const"))
			return "/pmm/reports/PMMRPT001_21_const.jasper";
		if(selectReport.equals("PMMRPT001_21_all_const"))
			return "/pmm/reports/PMMRPT001_21_all_const.jasper";
		if(selectReport.equals("PMMRPT001_21_all"))
			return "/pmm/reports/PMMRPT001_21_all.jasper";
		if(selectReport.equals("PMMRPT001_22"))
			return "/pmm/reports/PMMRPT001_22.jasper";
		if(selectReport.equals("PMMRPT001_23"))
			return "/pmm/reports/PMMRPT001_23.jasper";
		if(selectReport.equals("PMMRPT001_29"))
			return "/pmm/reports/PMMRPT001_29.jasper";
		if(selectReport.equals("PMMRPT001_30"))
			return "/pmm/reports/PMMRPT001_30.jasper";
		if(selectReport.equals("PMMRPT001_31"))
			return "/pmm/reports/PMMRPT001_31.jasper";
		
		if(selectReport.equals("PMMRPT001_32"))
			return "/pmm/reports/PMMRPT001_32.jasper";
		
		if(selectReport.equals("PMMRPT001_34"))
			return "/pmm/reports/PMMRPT001_34.jasper";
		
		if(selectReport.equals("PMMRPT001_34_dtl"))
			return "/pmm/reports/PMMRPT001_34_dtl.jasper";
		
		if(selectReport.equals("PMMRPT001_35"))
			return "/pmm/reports/PMMRPT001_35.jasper";
		if(selectReport.equals("PMMRPT001_36"))
			return "/pmm/reports/PMMRPT001_36.jasper";
		
		if(selectReport.equals("PMMRPT001_33P1"))
			return "/pmm/reports/PMMRPT001_33P1.jasper";
		if(selectReport.equals("PMMRPT001_33P2"))
			return "/pmm/reports/PMMRPT001_33P2.jasper";
		
		
		if(selectReport.equals("MH_3161"))
			return "/pmm/reports/MH_3161.jasper";
		
		if(selectReport.equals("status_MH"))
			return "/pmm/reports/status_MH.jasper";
		
		if(selectReport.equals("status_OH"))
			return "/pmm/reports/status_OH.jasper";
		
		if(selectReport.equals("commissioning_IDA_RPT"))
			return "/pmm/reports/commissioning_IDA_RPT.jasper";
		
		if(selectReport.equals("PMMRPT001_24"))
			return "/pmm/reports/PMMRPT001_24.jasper";
		
		if(selectReport.equals("PMMRPT001_001"))
			return "/pmm/reports/PMMRPT001_001.jasper";
		
		if(selectReport.equals("PMMRPT001_001_details"))
			return "/pmm/reports/PMMRPT001_001_details.jasper";
		
		if(selectReport.equals("PMMRPT001_18_districtwise"))
			return "/pmm/reports/PMMRPT001_18_districtwise.jasper";
		
		if(selectReport.equals("RPT001_1"))
			return "/pmm/reports/RPT001_1.jasper";
		
		if(selectReport.equals("RPT001_2"))
			return "/pmm/reports/RPT001_2.jasper";
		
		if(selectReport.equals("RPT001_3"))
			return "/pmm/reports/RPT001_3.jasper";
		
		if(selectReport.equals("RPT001_4"))
			return "/pmm/reports/RPT001_4.jasper";
		
		if(selectReport.equals("RPT001_5"))
			return "/pmm/reports/RPT001_5.jasper";
		
		if(selectReport.equals("RPT001_6"))
			return "/pmm/reports/RPT001_6.jasper";
		
		if(selectReport.equals("rpt_3141_list"))
			return "/pmm/reports/rpt_3141_list.jasper";
		//--------------------------------------------------------------------------------------------
		if(selectReport.equals("PMMRPT001_5_abstr_np_swap"))
			return "/pmm/reports/PMMRPT001_5_abstr_np_swap.jasper";
		if(selectReport.equals("PMMRPT001_5_abstr_np_nonswap"))
			return "/pmm/reports/PMMRPT001_5_abstr_np_nonswap.jasper";
		if(selectReport.equals("PMMRPT001_5_abstr70"))
			return "/pmm/reports/PMMRPT001_5_abstr70.jasper";
		if(selectReport.equals("PMMRPT001_5_dtl_np_swap"))
			return "/pmm/reports/PMMRPT001_5_dtl_np_swap.jasper";
		if(selectReport.equals("PMMRPT001_5_dtl_np_nonswap"))
			return "/pmm/reports/PMMRPT001_5_dtl_np_nonswap.jasper";
		
		if(selectReport.equals("PMMRPT001_5_abstr_p_ida"))
			return "/pmm/reports/PMMRPT001_5_abstr_p_ida.jasper";
		if(selectReport.equals("PMMRPT001_5_abstr_p_nonida"))
			return "/pmm/reports/PMMRPT001_5_abstr_p_nonida.jasper";
		if(selectReport.equals("PMMRPT001_5_dtl_p_ida"))
			return "/pmm/reports/PMMRPT001_5_dtl_p_ida.jasper";
		if(selectReport.equals("PMMRPT001_5_dtl_sew"))
			return "/pmm/reports/PMMRPT001_5_dtl_sew.jasper";
		if(selectReport.equals("PMMRPT001_5_dtl_p_nonida"))
			return "/pmm/reports/PMMRPT001_5_dtl_p_nonida.jasper";
		if(selectReport.equals("PMMRPT001_5_detail"))
			return "/pmm/reports/PMMRPT001_5_detail.jasper";
		if(selectReport.equals("PMMRPT001_25"))
			return "/pmm/reports/PMMRPT001_25.jasper";
		if(selectReport.equals("PMMRPT001_26"))
			return "/pmm/reports/PMMRPT001_26.jasper";
		if(selectReport.equals("PMMRPT001_27"))
			return "/pmm/reports/PMMRPT001_27.jasper";
		if(selectReport.equals("PMMRPT001_28P1"))
			return "/pmm/reports/PMMRPT001_28P1.jasper";
		if(selectReport.equals("PMMRPT001_28P2"))
			return "/pmm/reports/PMMRPT001_28P2.jasper";
		if(selectReport.equals("PMMRPT001_28P3"))
			return "/pmm/reports/PMMRPT001_28P3.jasper";
		
		//New Reports Added Afterwards
		if(selectReport.equals("PMMRPT001_G3"))
			return "/pmm/reports/PMMRPT001_G3.jasper";
		
		if(selectReport.equals("PMMRPT001_F4"))
			return "/pmm/reports/PMMRPT001_F4.jasper";
		
		if(selectReport.equals("PMMRPT001_G4_abs"))
			return "/pmm/reports/PMMRPT001_G4_abs.jasper";
		
		if(selectReport.equals("PMMRPT001_G5_det"))
			return "/pmm/reports/PMMRPT001_G5_det.jasper";
		
		if(selectReport.equals("PMMRPT001_A2.4_sew"))
			return "/pmm/reports/PMMRPT001_A2.4_sew.jasper";
		if(selectReport.equals("PMMRPT001_A2.4_wm"))
			return "/pmm/reports/PMMRPT001_A2.4_dis_wm.jasper";
		
		if(selectReport.equals("PMMRPT001_A2.4_imp"))
			return "/pmm/reports/PMMRPT001_A2.4_imp.jasper";
		if(selectReport.equals("PMMRPT001_A2.5_sew"))
			return "/pmm/reports/PMMRPT001_A2.5_sew.jasper";
		if(selectReport.equals("PMMRPT001_A2.5_imp"))
			return "/pmm/reports/PMMRPT001_A2.5_imp.jasper";
		if(selectReport.equals("PMMRPT001_A2.5_wm"))
			return "/pmm/reports/PMMRPT001_A2.5_wm.jasper";
		if(selectReport.equals("PMMRPT001_A2.4_dis"))
			return "/pmm/reports/PMMRPT001_A2.4_dis.jasper";
		
		
		if(selectReport.equals("PMMRPT001_A2.5_dis"))
			return "/pmm/reports/PMMRPT001_A2.5_dis.jasper";
		if(selectReport.equals("PMMRPT001_A2.4_A1"))
			return "/pmm/reports/PMMRPT001_A2.4_A1.jasper";	
		
		if(selectReport.equals("PMMRPT001_A2.4_1AWL"))
			return "/pmm/reports/PMMRPT001_A2.4_1AWL.jasper";	
		
		if(selectReport.equals("PMMRPT001_A2.4_2A"))
			return "/pmm/reports/PMMRPT001_A2.4_2A.jasper";	
		if(selectReport.equals("PMMRPT001_A2.4_2B"))
			return "/pmm/reports/PMMRPT001_A2.4_2B.jasper";
		
		if(selectReport.equals("PMMRPT001_A2.5_A1"))
			return "/pmm/reports/PMMRPT001_A2.5_A1.jasper";	
		
		/**
		 * KD CODE
		 */
		if(selectReport.equals("PMMRPT001_A2.5_1AWL"))
			return "/pmm/reports/PMMRPT001_A2.5_1AWL.jasper";	
		
		if(selectReport.equals("PMMRPT001_A2.5_2A"))
			return "/pmm/reports/PMMRPT001_A2.5_2A.jasper";	
		if(selectReport.equals("PMMRPT001_A2.5_2B"))
			return "/pmm/reports/PMMRPT001_A2.5_2B.jasper";	
		
		if(selectReport.equals("PMMRPT001_QT"))
			return "/pmm/reports/PMMRPT001_QT.jasper";
		if(selectReport.equals("PMMRPT001_A11"))
			return "/pmm/reports/PMMRPT001_A11.jasper";
		//new added
		if(selectReport.equals("PMMRPT001_24_details"))
			return "/pmm/reports/PMMRPT001_24_details.jasper";
		if(selectReport.equals("RPT001_4_water_teriff_cell_div"))
			return "/pmm/reports/RPT001_4_water_teriff_cell_div.jasper";
		if(selectReport.equals("PMMRPT001_24_blocks"))
			return "/pmm/reports/PMMRPT001_24_blocks.jasper";
		if(selectReport.equals("PMMRPT001_24_consi"))
			return "/pmm/reports/PMMRPT001_24_consi.jasper";
		if(selectReport.equals("PMMRPT001_24_41"))
			return "/pmm/reports/RPT001_61.jasper";
		if(selectReport.equals("PMMRPT001_24_42"))
			return "/pmm/reports/PMRPT001_61.jasper";
		if(selectReport.equals("PMMRPT001_24_43"))
			return "/pmm/reports/PMRPT001_62.jasper";
		if(selectReport.equals("PMMRPT001_24_44"))
			return "/pmm/reports/PMRPT001_68.jasper";
		if(selectReport.equals("PMMRPT001_A12_13"))
			return "/pmm/reports/RPT001_60.jasper";
		if(selectReport.equals("PMMRPT001_A14"))
			return "/pmm/reports/PMRPT001_63.jasper";
		if(selectReport.equals("PMMRPT001_A15"))
			return "/pmm/reports/PMRPT001_64.jasper";
		if(selectReport.equals("PMMRPT001_A16"))
			return "/pmm/reports/PMRPT001_69.jasper";
		
		if(selectReport.equals("PMMRPT001_200"))
			return "/pmm/reports/PMMRPT001_200.jasper";
		if(selectReport.equals("PMMRPT001_201"))
			return "/pmm/reports/PMMRPT001_201.jasper";
		if(selectReport.equals("PMMRPT001_202"))
			return "/pmm/reports/PMMRPT001_202.jasper";
		if(selectReport.equals("PMMRPT001_203"))
			return "/pmm/reports/PMMRPT001_203.jasper";
		if(selectReport.equals("PMMRPT001_204"))
			return "/pmm/reports/PMMRPT001_204.jasper";
		if(selectReport.equals("PMMRPT001_205"))
			return "/pmm/reports/PMMRPT001_205.jasper";
		
		//table 3& 4 link
		
		if(selectReport.equals("IBRD21"))
			return "/pmm/reports/Table03_abs_01.jasper";
		
		if(selectReport.equals("IBRD22"))
			return "/pmm/reports/Table03_abs_02.jasper";
		
		if(selectReport.equals("IBRD23"))
			return "/pmm/reports/Table04_abs_01.jasper";
		
		if(selectReport.equals("IBRD24"))
			return "/pmm/reports/Table04_abs_02.jasper";
		
		if(selectReport.equals("IBRD25"))
			return "/pmm/reports/Table03_dtl.jasper";
		
		if(selectReport.equals("IBRD26"))
			return "/pmm/reports/Table04_dtl.jasper";
		
		
		if(selectReport.equals("PMMRPT001_205"))
			return "/pmm/reports/PMMRPT001_205.jasper";
		
		if(selectReport.equals("PMMRPT001_206"))
			return "/pmm/reports/PMMRPT001_206.jasper";
		
		if(selectReport.equals("PMMRPT001_207"))
			return "/pmm/reports/PMMRPT001_207.jasper";
		
		
		
			/*Service delivery report */	
		
		
		if(selectReport.equals("IBRD1"))
			return "/pmm/reports/IBRD01.jasper";
		if(selectReport.equals("IBRD2"))
			return "/pmm/reports/IBRD02.jasper";
		if(selectReport.equals("IBRD3"))
			return "/pmm/reports/IBRD03.jasper";
		if(selectReport.equals("IBRD4"))
			return "/pmm/reports/IBRD04.jasper";
		if(selectReport.equals("IBRD5"))
			return "/pmm/reports/IBRD05.jasper";
		if(selectReport.equals("IBRD6"))
			return "/pmm/reports/IBRD06.jasper";
		if(selectReport.equals("IBRD7"))
			return "/pmm/reports/IBRD07.jasper";
		if(selectReport.equals("IBRD8"))
			return "/pmm/reports/IBRD08.jasper";
		if(selectReport.equals("IBRD9"))
			return "/pmm/reports/IBRD09.jasper";
		if(selectReport.equals("IBRD10"))
			return "/pmm/reports/IBRD10.jasper";
		if(selectReport.equals("IBRD11"))
			return "/pmm/reports/IBRD11.jasper";
		if(selectReport.equals("IBRD12"))
			return "/pmm/reports/IBD12.jasper";
		if(selectReport.equals("IBRD13"))
			return "/pmm/reports/IBRD13.jasper";
		if(selectReport.equals("IBRD14"))
			return "/pmm/reports/IBRD14.jasper";
		if(selectReport.equals("IBRD15"))
			return "/pmm/reports/IBRD15.jasper";
		if(selectReport.equals("IBRD16"))
			return "/pmm/reports/IBRD16.jasper";
		if(selectReport.equals("IBRD17"))
			return "/pmm/reports/IBRD17.jasper";
		if(selectReport.equals("IBRD18"))
			return "/pmm/reports/IBRD18.jasper";
		if(selectReport.equals("IBRD19"))
			return "/pmm/reports/IBRD19.jasper";
		if(selectReport.equals("IBRD20"))
			return "/pmm/reports/IBRD20.jasper";
		if(selectReport.equals("IBRD21"))
			return "/pmm/reports/Table03_abs_01.jasper";
		if(selectReport.equals("IBRD22"))
			return "/pmm/reports/Table03_abs_02.jasper";
		if(selectReport.equals("IBRD23"))
			return "/pmm/reports/Table04_abs_01.jasper";
		if(selectReport.equals("IBRD24"))
			return "/pmm/reports/Table04_abs_02.jasper";
		if(selectReport.equals("IBRD25"))
			return "/pmm/reports/Table03_dtl.jasper";
		if(selectReport.equals("IBRD26"))
			return "/pmm/reports/Table04_dtl.jasper";
		
		
		
		
		/*
		if(selectReport.equals("PMMRPT001_200"))
			return "/pmm/reports/PMMRPT001_200.jasper";
		if(selectReport.equals("PMMRPT001_201"))
			return "/pmm/reports/PMMRPT001_201.jasper";
		if(selectReport.equals("PMMRPT001_202"))
			return "/pmm/reports/PMMRPT001_202.jasper";
		if(selectReport.equals("PMMRPT001_203"))
			return "/pmm/reports/PMMRPT001_203.jasper";
		if(selectReport.equals("PMMRPT001_204"))
			return "/pmm/reports/PMMRPT001_204.jasper";
		*/
		
		/*if(selectReport.equals("PMMRPT001_24_details_bw"))
			return "/pmm/reports/PMMRPT001_31.jasper";
		*//*	if(selectReport.equals("PMMRPT001_24_details_consti"))
			return "/pmm/reports/PMMRPT001_21_const.jasper";
		if(selectReport.equals("PMMRPT001_24_details_bw"))
			return "/pmm/reports/RPT001_5_blk.jasper";
		if(selectReport.equals("RPT001_6_div"))
			return "/pmm/reports/RPT001_6_div.jasper";
		*/
		
	return null;
	}
	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}
	public String getFileTitle() {
		
		
		/*if(selectReport.equals("rpt_3141_list"))
			return "PRWSS_Project_Habitation_Proposed";
		
		if(selectReport.equals("PMMRPT001_1"))
			return "Beneficiary_Share-1_";
		if(selectReport.equals("PMMRPT001_2"))
			return "Beneficiary_Share-2_";
		if(selectReport.equals("PMMRPT001_3"))
			return "Beneficiary_Share-3_";
		if(selectReport.equals("PMMRPT001_4"))
			return "Scheme_Village_";
		if(selectReport.equals("PMMRPT001_5"))
			return "Water_Connection_";
		if(selectReport.equals("PMMRPT001_6"))
			return "PMM_Table2_";
		if(selectReport.equals("PMMRPT001_7"))
			return "PMM_Table2a_";
		if(selectReport.equals("PMMRPT001_8"))
			return "PMM_Table2b_";
		if(selectReport.equals("PMMRPT001_9"))
			return "PMM_Table2c_";
		if(selectReport.equals("PMMRPT001_10"))
			return "PMM_Table1_";
		if(selectReport.equals("PMMRPT001_11"))
			return "PMM_Table3_";
		if(selectReport.equals("PMMRPT001_12"))
			return "PMM_Table3a_";
		if(selectReport.equals("PMMRPT001_13"))
			return "PMM_Table3b_";
		if(selectReport.equals("PMMRPT001_14"))
			return "PMM_Table4_";
		if(selectReport.equals("PMMRPT001_15"))
			return "PMM_Table4a_";
		if(selectReport.equals("PMMRPT001_16"))
			return "PMM_Table4b_";
		if(selectReport.equals("PMMRPT001_17"))
			return "Operational_Sustainability";
		if(selectReport.equals("PMMRPT001_18"))
			return "State Habitation Abstract";
		if(selectReport.equals("PMMRPT001_19"))
			return "State Habitation Abstract WB";
		if(selectReport.equals("PMMRPT001_20"))
			return "Scheme-wise details for commissioned villages";
		if(selectReport.equals("PMMRPT001_21"))
			return "Abstract of commissioned villages";
		if(selectReport.equals("PMMRPT001_22"))
			return "Scheme-wise details for commissioned villages (1-B)";
		if(selectReport.equals("PMMRPT001_23"))
			return "Abstract of commissioned villages (1-A)";
		
		if(selectReport.equals("MH_3161"))
			return "MH-3161 Commissioning Report";
		
		if(selectReport.equals("status_MH"))
			return "MH- Commissioning Report";
		
		if(selectReport.equals("status_OH"))
			return "OH- Commissioning Report";
		
		if(selectReport.equals("commissioning_IDA_RPT"))
			return "Village Commissioned under IDA";
		
		if(selectReport.equals("PMMRPT001_24"))
			return "Habitation_Abstract";
		
		if(selectReport.equals("PMMRPT001_18_districtwise"))
			return "Habitation_Abstract_District_Wise";
		
		if(selectReport.equals("RPT001_1"))
			return "RPT-1.jasper";
		
		if(selectReport.equals("RPT001_2"))
			return "RPT-2.jasper";
		
		if(selectReport.equals("RPT001_3"))
			return "RPT-3.jasper";
		
		if(selectReport.equals("RPT001_4"))
			return "RPT-4.jasper";
		
		if(selectReport.equals("RPT001_5"))
			return "RPT-5.jasper";
		
		if(selectReport.equals("RPT001_6"))
			return "RPT-6.jasper";
		
		if(selectReport.equals("RPT001_1_3_div"))
			return "/pmm/reports/RPT001_1_3_div";
		
		if(selectReport.equals("RPT001_1_2_consti"))
			return "/pmm/reports/RPT001_1_2_consti";
		
		if(selectReport.equals("RPT001_1_1_blk"))
			return "/pmm/reports/RPT001_1_1_blk";
		
		
		if(selectReport.equals("RPT001_2_div"))
			return "/pmm/reports/RPT001_2_div.jasper";
		
		if(selectReport.equals("RPT001_2_consti"))
			return "/pmm/reports/RPT001_2_consti.jasper";
		
		if(selectReport.equals("RPT001_2_blk"))
			return "/pmm/reports/RPT001_2_blk.jasper";
		
		if(selectReport.equals("RPT001_3_div"))
			return "/pmm/reports/RPT001_3_div.jasper";
		
		if(selectReport.equals("RPT001_3_consti"))
			return "/pmm/reports/RPT001_3_consti.jasper";
		
		if(selectReport.equals("RPT001_3_blk"))
			return "/pmm/reports/RPT001_3_blk.jasper";
		
		if(selectReport.equals("RPT001_4_div"))
			return "/pmm/reports/RPT001_4_div.jasper";
		
		if(selectReport.equals("RPT001_4_consti"))
			return "/pmm/reports/RPT001_4_consti.jasper";
		
		if(selectReport.equals("RPT001_4_blk"))
			return "/pmm/reports/RPT001_4_blk.jasper";
		
		
		if(selectReport.equals("RPT001_5_div"))
			return "/pmm/reports/RPT001_5_div.jasper";
		
		if(selectReport.equals("RPT001_5_consti"))
			return "/pmm/reports/RPT001_5_consti.jasper";
		
		if(selectReport.equals("RPT001_5_blk"))
			return "/pmm/reports/RPT001_5_blk.jasper";
		

		if(selectReport.equals("RPT001_6_div"))
			return "/pmm/reports/RPT001_6_div.jasper";
		
		if(selectReport.equals("RPT001_6_consti"))
			return "/pmm/reports/RPT001_6_consti.jasper";
		
		if(selectReport.equals("RPT001_6_blk"))
			return "/pmm/reports/RPT001_6_blk.jasper";
*/		
	return selectReport;
	}
	
	@Override
	public String toString() {
		return "EstimatesAwardContractsReportForm [currentDate=" + currentDate
				+ ", fileTitle=" + fileTitle + ", selectZone=" + selectZone
				+ ", selectCircle=" + selectCircle + ", selectDistrict="
				+ selectDistrict + ", selectProgram=" + selectProgram
				+ ", selectPeriod=" + selectPeriod + ", zoneId=" + zoneId
				+ ", circleId=" + circleId + ", districtId=" + districtId
				+ ", programId=" + programId + ", schemeTypeR=" + schemeTypeR
				+ ", IDAType=" + IDAType + ", schemeTypeRR=" + schemeTypeRR
				+ ", SMType=" + SMType + ", village_category="
				+ village_category + ", division=" + division
				+ ", MSHabitation=" + MSHabitation + ", schemeType="
				+ schemeType + ", asOnDate=" + asOnDate + ", fromDate="
				+ fromDate + ", toDate=" + toDate + ", approvalStatus="
				+ approvalStatus + ", selectReport=" + selectReport
				+ ", jasperFile=" + jasperFile + ", blockId=" + blockId
				+ ", divisionsId=" + divisionsId + ", constituencyId="
				+ constituencyId + ", swap=" + swap + ", swap1=" + swap1
				+ ", swap2=" + swap2 + ", swap3=" + swap3 + ", swap4=" + swap4
				+ ", IDA5=" + IDA5 + ", selectReport6=" + selectReport6
				+ ", yearType=" + yearType + ", IBRD=" + IBRD + ", status="
				+ status + ", ncfc=" + ncfc + ", ncFc=" + ncFc + ", monthId="
				+ monthId + ", finYearId=" + finYearId + "]";
	}
	public void setSwap(String swap) {
		this.swap = swap;
	}
	public String getSwap() {
		return swap;
	}
	
	public void setSwap1(String swap1) {
		this.swap1 = swap1;
	}
	public String getSwap1() {
		return swap1;
	}
	public void setMonthId(String monthId) {
		this.monthId = monthId;
	}
	public String getMonthId() {
		return monthId;
	}
	public void setFinYearId(String finYearId) {
		this.finYearId = finYearId;
	}
	public String getFinYearId() {
		return finYearId;
	}
	public String getIBRD() {
		return IBRD;
	}
	public void setIBRD(String iBRD) {
		IBRD = iBRD;
	}	
}
