/**

 * 
 */
package com.prwss.mis.reports.performa.dao;

import java.io.Serializable;

/**
 * @author bhsingh
 *
 */
public class VillageSourceGridBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8719959082734589898L;	
	private String vilIds;
	private String schmSource;
	private String schmType;
	private String dateComm;
	private String programId;
	private String srvcLevel;
	private String schemeUpgrd;
	private String schemeExpen;
	private String depthTubewell;
	private String sizeTubewell;
	private String yearDrilling;
	private String dischargeCommun;
	private String presentDischrg;
	private String presentSpringLvl;
	private String installationNewMachinery;
	private String capacityMachinery;
	private String inltType;
	private String inletLnght;
	private String pipType;
	private String capacitySTank;
	private String capacityHTank;
	private String capacityCTank;
	private String filterType;
	private String filterNo;
	private String filterCapacity;
	private String capacityRawWatr;
	private String capacityClrWater;
	private String capMachinery;
	
	private String oHSRConstructionDate;
	private String oHSRCap;
	private String oHSRWorkingCond;
	private String oHSRCond;
	private String disinfType;
	private String disInstallationTime;
	private String disinPrStatus;
	private String schemeOperatBy;
	private String stafScheme;
	private String sanctionLoad;
	private String pendingBill3103;
	private String pendingBill3006;
	private String avgMonthBillWSS;
	private String oHSRDismantling1;
	private String oHSRFullSuplyLvl1;
	private String noOhsrOhsr;
	
	private String ground_water_potablestatus;
	private String ground_water_potableNo;
	private String preventive_measuresadopted;
	private String any_other_desc;
	private String capacity_ofplant;
	private String dateInstallation;
	private String being_operatedby;
	private String volume_of_Water_Dailybasis;
	private String disposal_of_rejectwater;
	private String penetration_inpercentage;
	private String present_rate_of_usercharges;
	private String seperate_SanctionedLoad;
	private String seperate_Pending_eletricbill31032017;
	private String average_monthly_bill_of_Treatmentplant;
	private String schemeSrc;
	private String oHSRCtDepth1;
	private String oHSRUgsrDepth1;
	private String oHSRCtDia1;
	private String oHSRUgsrDia1;
	
	
	
	
	
	public String getAny_other_desc() {
		return any_other_desc;
	}
	public void setAny_other_desc(String any_other_desc) {
		this.any_other_desc = any_other_desc;
	}
	public String getoHSRCtDepth1() {
		return oHSRCtDepth1;
	}
	public void setoHSRCtDepth1(String oHSRCtDepth1) {
		this.oHSRCtDepth1 = oHSRCtDepth1;
	}
	public String getoHSRUgsrDepth1() {
		return oHSRUgsrDepth1;
	}
	public void setoHSRUgsrDepth1(String oHSRUgsrDepth1) {
		this.oHSRUgsrDepth1 = oHSRUgsrDepth1;
	}
	public String getoHSRCtDia1() {
		return oHSRCtDia1;
	}
	public void setoHSRCtDia1(String oHSRCtDia1) {
		this.oHSRCtDia1 = oHSRCtDia1;
	}
	public String getoHSRUgsrDia1() {
		return oHSRUgsrDia1;
	}
	public void setoHSRUgsrDia1(String oHSRUgsrDia1) {
		this.oHSRUgsrDia1 = oHSRUgsrDia1;
	}
	/**
	 * @return the schemeSrc
	 */
	public String getSchemeSrc() {
		return schemeSrc;
	}
	/**
	 * @param schemeSrc the schemeSrc to set
	 */
	public void setSchemeSrc(String schemeSrc) {
		this.schemeSrc = schemeSrc;
	}
	/**
	 * @return the oHSRDismantling1
	 */
	public String getoHSRDismantling1() {
		return oHSRDismantling1;
	}
	/**
	 * @param oHSRDismantling1 the oHSRDismantling1 to set
	 */
	public void setoHSRDismantling1(String oHSRDismantling1) {
		this.oHSRDismantling1 = oHSRDismantling1;
	}
	/**
	 * @return the oHSRFullSuplyLvl1
	 */
	public String getoHSRFullSuplyLvl1() {
		return oHSRFullSuplyLvl1;
	}
	/**
	 * @param oHSRFullSuplyLvl1 the oHSRFullSuplyLvl1 to set
	 */
	public void setoHSRFullSuplyLvl1(String oHSRFullSuplyLvl1) {
		this.oHSRFullSuplyLvl1 = oHSRFullSuplyLvl1;
	}
	/**
	 * @return the ground_water_potablestatus
	 */
	public String getGround_water_potablestatus() {
		return ground_water_potablestatus;
	}
	/**
	 * @param ground_water_potablestatus the ground_water_potablestatus to set
	 */
	public void setGround_water_potablestatus(String ground_water_potablestatus) {
		this.ground_water_potablestatus = ground_water_potablestatus;
	}
	/**
	 * @return the ground_water_potableNo
	 */
	public String getGround_water_potableNo() {
		return ground_water_potableNo;
	}
	/**
	 * @param ground_water_potableNo the ground_water_potableNo to set
	 */
	public void setGround_water_potableNo(String ground_water_potableNo) {
		this.ground_water_potableNo = ground_water_potableNo;
	}
	
	/**
	 * @return the preventive_measuresadopted
	 */
	public String getPreventive_measuresadopted() {
		return preventive_measuresadopted;
	}
	/**
	 * @param preventive_measuresadopted the preventive_measuresadopted to set
	 */
	public void setPreventive_measuresadopted(String preventive_measuresadopted) {
		this.preventive_measuresadopted = preventive_measuresadopted;
	}
	/**
	 * @return the capacity_ofplant
	 */
	public String getCapacity_ofplant() {
		return capacity_ofplant;
	}
	/**
	 * @param capacity_ofplant the capacity_ofplant to set
	 */
	public void setCapacity_ofplant(String capacity_ofplant) {
		this.capacity_ofplant = capacity_ofplant;
	}
	/**
	 * @return the dateInstallation
	 */
	public String getDateInstallation() {
		return dateInstallation;
	}
	/**
	 * @param dateInstallation the dateInstallation to set
	 */
	public void setDateInstallation(String dateInstallation) {
		this.dateInstallation = dateInstallation;
	}
	/**
	 * @return the being_operatedby
	 */
	public String getBeing_operatedby() {
		return being_operatedby;
	}
	/**
	 * @param being_operatedby the being_operatedby to set
	 */
	public void setBeing_operatedby(String being_operatedby) {
		this.being_operatedby = being_operatedby;
	}
	/**
	 * @return the volume_of_Water_Dailybasis
	 */
	public String getVolume_of_Water_Dailybasis() {
		return volume_of_Water_Dailybasis;
	}
	/**
	 * @param volume_of_Water_Dailybasis the volume_of_Water_Dailybasis to set
	 */
	public void setVolume_of_Water_Dailybasis(String volume_of_Water_Dailybasis) {
		this.volume_of_Water_Dailybasis = volume_of_Water_Dailybasis;
	}
	/**
	 * @return the disposal_of_rejectwater
	 */
	public String getDisposal_of_rejectwater() {
		return disposal_of_rejectwater;
	}
	/**
	 * @param disposal_of_rejectwater the disposal_of_rejectwater to set
	 */
	public void setDisposal_of_rejectwater(String disposal_of_rejectwater) {
		this.disposal_of_rejectwater = disposal_of_rejectwater;
	}
	/**
	 * @return the penetration_inpercentage
	 */
	public String getPenetration_inpercentage() {
		return penetration_inpercentage;
	}
	/**
	 * @param penetration_inpercentage the penetration_inpercentage to set
	 */
	public void setPenetration_inpercentage(String penetration_inpercentage) {
		this.penetration_inpercentage = penetration_inpercentage;
	}
	/**
	 * @return the present_rate_of_usercharges
	 */
	public String getPresent_rate_of_usercharges() {
		return present_rate_of_usercharges;
	}
	/**
	 * @param present_rate_of_usercharges the present_rate_of_usercharges to set
	 */
	public void setPresent_rate_of_usercharges(String present_rate_of_usercharges) {
		this.present_rate_of_usercharges = present_rate_of_usercharges;
	}
	/**
	 * @return the seperate_SanctionedLoad
	 */
	public String getSeperate_SanctionedLoad() {
		return seperate_SanctionedLoad;
	}
	/**
	 * @param seperate_SanctionedLoad the seperate_SanctionedLoad to set
	 */
	public void setSeperate_SanctionedLoad(String seperate_SanctionedLoad) {
		this.seperate_SanctionedLoad = seperate_SanctionedLoad;
	}
	/**
	 * @return the seperate_Pending_eletricbill31032017
	 */
	public String getSeperate_Pending_eletricbill31032017() {
		return seperate_Pending_eletricbill31032017;
	}
	/**
	 * @param seperate_Pending_eletricbill31032017 the seperate_Pending_eletricbill31032017 to set
	 */
	public void setSeperate_Pending_eletricbill31032017(String seperate_Pending_eletricbill31032017) {
		this.seperate_Pending_eletricbill31032017 = seperate_Pending_eletricbill31032017;
	}
	/**
	 * @return the average_monthly_bill_of_Treatmentplant
	 */
	public String getAverage_monthly_bill_of_Treatmentplant() {
		return average_monthly_bill_of_Treatmentplant;
	}
	/**
	 * @param average_monthly_bill_of_Treatmentplant the average_monthly_bill_of_Treatmentplant to set
	 */
	public void setAverage_monthly_bill_of_Treatmentplant(String average_monthly_bill_of_Treatmentplant) {
		this.average_monthly_bill_of_Treatmentplant = average_monthly_bill_of_Treatmentplant;
	}
	/**
	 * @return the disInstallationTime
	 */
	public String getDisInstallationTime() {
		return disInstallationTime;
	}
	/**
	 * @param disInstallationTime the disInstallationTime to set
	 */
	public void setDisInstallationTime(String disInstallationTime) {
		this.disInstallationTime = disInstallationTime;
	}
	/**
	 * @return the filterNo
	 */
	public String getFilterNo() {
		return filterNo;
	}
	/**
	 * @param filterNo the filterNo to set
	 */
	public void setFilterNo(String filterNo) {
		this.filterNo = filterNo;
	}
	/**
	 * @return the filterCapacity
	 */
	public String getFilterCapacity() {
		return filterCapacity;
	}
	/**
	 * @param filterCapacity the filterCapacity to set
	 */
	public void setFilterCapacity(String filterCapacity) {
		this.filterCapacity = filterCapacity;
	}
	/**
	 * @return the capacityRawWatr
	 */
	public String getCapacityRawWatr() {
		return capacityRawWatr;
	}
	/**
	 * @param capacityRawWatr the capacityRawWatr to set
	 */
	public void setCapacityRawWatr(String capacityRawWatr) {
		this.capacityRawWatr = capacityRawWatr;
	}
	/**
	 * @return the capacityClrWater
	 */
	public String getCapacityClrWater() {
		return capacityClrWater;
	}
	/**
	 * @param capacityClrWater the capacityClrWater to set
	 */
	public void setCapacityClrWater(String capacityClrWater) {
		this.capacityClrWater = capacityClrWater;
	}
	/**
	 * @return the capMachinery
	 */
	public String getCapMachinery() {
		return capMachinery;
	}
	/**
	 * @param capMachinery the capMachinery to set
	 */
	public void setCapMachinery(String capMachinery) {
		this.capMachinery = capMachinery;
	}
	
	
	/**
	 * @return the noOhsrOhsr
	 */
	public String getNoOhsrOhsr() {
		return noOhsrOhsr;
	}
	/**
	 * @param noOhsrOhsr the noOhsrOhsr to set
	 */
	public void setNoOhsrOhsr(String noOhsrOhsr) {
		this.noOhsrOhsr = noOhsrOhsr;
	}
	/**
	 * @return the oHSRConstructionDate
	 */
	public String getoHSRConstructionDate() {
		return oHSRConstructionDate;
	}
	/**
	 * @param oHSRConstructionDate the oHSRConstructionDate to set
	 */
	public void setoHSRConstructionDate(String oHSRConstructionDate) {
		this.oHSRConstructionDate = oHSRConstructionDate;
	}
	/**
	 * @return the oHSRCap
	 */
	public String getoHSRCap() {
		return oHSRCap;
	}
	/**
	 * @param oHSRCap the oHSRCap to set
	 */
	public void setoHSRCap(String oHSRCap) {
		this.oHSRCap = oHSRCap;
	}
	/**
	 * @return the oHSRWorkingCond
	 */
	public String getoHSRWorkingCond() {
		return oHSRWorkingCond;
	}
	/**
	 * @param oHSRWorkingCond the oHSRWorkingCond to set
	 */
	public void setoHSRWorkingCond(String oHSRWorkingCond) {
		this.oHSRWorkingCond = oHSRWorkingCond;
	}
	/**
	 * @return the oHSRCond
	 */
	public String getoHSRCond() {
		return oHSRCond;
	}
	/**
	 * @param oHSRCond the oHSRCond to set
	 */
	public void setoHSRCond(String oHSRCond) {
		this.oHSRCond = oHSRCond;
	}
	/**
	 * @return the disinfType
	 */
	public String getDisinfType() {
		return disinfType;
	}
	/**
	 * @param disinfType the disinfType to set
	 */
	public void setDisinfType(String disinfType) {
		this.disinfType = disinfType;
	}
	/**
	 * @return the disinPrStatus
	 */
	public String getDisinPrStatus() {
		return disinPrStatus;
	}
	/**
	 * @param disinPrStatus the disinPrStatus to set
	 */
	public void setDisinPrStatus(String disinPrStatus) {
		this.disinPrStatus = disinPrStatus;
	}
	/**
	 * @return the schemeOperatBy
	 */
	public String getSchemeOperatBy() {
		return schemeOperatBy;
	}
	/**
	 * @param schemeOperatBy the schemeOperatBy to set
	 */
	public void setSchemeOperatBy(String schemeOperatBy) {
		this.schemeOperatBy = schemeOperatBy;
	}
	/**
	 * @return the stafScheme
	 */
	public String getStafScheme() {
		return stafScheme;
	}
	/**
	 * @param stafScheme the stafScheme to set
	 */
	public void setStafScheme(String stafScheme) {
		this.stafScheme = stafScheme;
	}
	/**
	 * @return the sanctionLoad
	 */
	public String getSanctionLoad() {
		return sanctionLoad;
	}
	/**
	 * @param sanctionLoad the sanctionLoad to set
	 */
	public void setSanctionLoad(String sanctionLoad) {
		this.sanctionLoad = sanctionLoad;
	}
	/**
	 * @return the pendingBill3103
	 */
	public String getPendingBill3103() {
		return pendingBill3103;
	}
	/**
	 * @param pendingBill3103 the pendingBill3103 to set
	 */
	public void setPendingBill3103(String pendingBill3103) {
		this.pendingBill3103 = pendingBill3103;
	}
	/**
	 * @return the pendingBill3006
	 */
	public String getPendingBill3006() {
		return pendingBill3006;
	}
	/**
	 * @param pendingBill3006 the pendingBill3006 to set
	 */
	public void setPendingBill3006(String pendingBill3006) {
		this.pendingBill3006 = pendingBill3006;
	}
	/**
	 * @return the avgMonthBillWSS
	 */
	public String getAvgMonthBillWSS() {
		return avgMonthBillWSS;
	}
	/**
	 * @param avgMonthBillWSS the avgMonthBillWSS to set
	 */
	public void setAvgMonthBillWSS(String avgMonthBillWSS) {
		this.avgMonthBillWSS = avgMonthBillWSS;
	}
	/**
	 * @return the vilIds
	 */
	public String getVilIds() {
		return vilIds;
	}
	/**
	 * @return the schemeUpgrd
	 */
	public String getSchemeUpgrd() {
		return schemeUpgrd;
	}
	/**
	 * @param schemeUpgrd the schemeUpgrd to set
	 */
	public void setSchemeUpgrd(String schemeUpgrd) {
		this.schemeUpgrd = schemeUpgrd;
	}
	/**
	 * @return the schemeExpen
	 */
	public String getSchemeExpen() {
		return schemeExpen;
	}
	/**
	 * @param schemeExpen the schemeExpen to set
	 */
	public void setSchemeExpen(String schemeExpen) {
		this.schemeExpen = schemeExpen;
	}
	/**
	 * @return the depthTubewell
	 */
	public String getDepthTubewell() {
		return depthTubewell;
	}
	/**
	 * @param depthTubewell the depthTubewell to set
	 */
	public void setDepthTubewell(String depthTubewell) {
		this.depthTubewell = depthTubewell;
	}
	/**
	 * @return the sizeTubewell
	 */
	public String getSizeTubewell() {
		return sizeTubewell;
	}
	/**
	 * @param sizeTubewell the sizeTubewell to set
	 */
	public void setSizeTubewell(String sizeTubewell) {
		this.sizeTubewell = sizeTubewell;
	}
	/**
	 * @return the yearDrilling
	 */
	public String getYearDrilling() {
		return yearDrilling;
	}
	/**
	 * @param yearDrilling the yearDrilling to set
	 */
	public void setYearDrilling(String yearDrilling) {
		this.yearDrilling = yearDrilling;
	}
	/**
	 * @return the dischargeCommun
	 */
	public String getDischargeCommun() {
		return dischargeCommun;
	}
	/**
	 * @param dischargeCommun the dischargeCommun to set
	 */
	public void setDischargeCommun(String dischargeCommun) {
		this.dischargeCommun = dischargeCommun;
	}
	/**
	 * @return the presentDischrg
	 */
	public String getPresentDischrg() {
		return presentDischrg;
	}
	/**
	 * @param presentDischrg the presentDischrg to set
	 */
	public void setPresentDischrg(String presentDischrg) {
		this.presentDischrg = presentDischrg;
	}
	/**
	 * @return the presentSpringLvl
	 */
	public String getPresentSpringLvl() {
		return presentSpringLvl;
	}
	/**
	 * @param presentSpringLvl the presentSpringLvl to set
	 */
	public void setPresentSpringLvl(String presentSpringLvl) {
		this.presentSpringLvl = presentSpringLvl;
	}
	/**
	 * @return the installationNewMachinery
	 */
	public String getInstallationNewMachinery() {
		return installationNewMachinery;
	}
	/**
	 * @param installationNewMachinery the installationNewMachinery to set
	 */
	public void setInstallationNewMachinery(String installationNewMachinery) {
		this.installationNewMachinery = installationNewMachinery;
	}
	/**
	 * @return the capacityMachinery
	 */
	public String getCapacityMachinery() {
		return capacityMachinery;
	}
	/**
	 * @param capacityMachinery the capacityMachinery to set
	 */
	public void setCapacityMachinery(String capacityMachinery) {
		this.capacityMachinery = capacityMachinery;
	}
	/**
	 * @return the inltType
	 */
	public String getInltType() {
		return inltType;
	}
	/**
	 * @param inltType the inltType to set
	 */
	public void setInltType(String inltType) {
		this.inltType = inltType;
	}
	/**
	 * @return the inletLnght
	 */
	public String getInletLnght() {
		return inletLnght;
	}
	/**
	 * @param inletLnght the inletLnght to set
	 */
	public void setInletLnght(String inletLnght) {
		this.inletLnght = inletLnght;
	}
	/**
	 * @return the pipType
	 */
	public String getPipType() {
		return pipType;
	}
	/**
	 * @param pipType the pipType to set
	 */
	public void setPipType(String pipType) {
		this.pipType = pipType;
	}
	/**
	 * @return the capacitySTank
	 */
	public String getCapacitySTank() {
		return capacitySTank;
	}
	/**
	 * @param capacitySTank the capacitySTank to set
	 */
	public void setCapacitySTank(String capacitySTank) {
		this.capacitySTank = capacitySTank;
	}
	/**
	 * @return the capacityHTank
	 */
	public String getCapacityHTank() {
		return capacityHTank;
	}
	/**
	 * @param capacityHTank the capacityHTank to set
	 */
	public void setCapacityHTank(String capacityHTank) {
		this.capacityHTank = capacityHTank;
	}
	/**
	 * @return the capacityCTank
	 */
	public String getCapacityCTank() {
		return capacityCTank;
	}
	/**
	 * @param capacityCTank the capacityCTank to set
	 */
	public void setCapacityCTank(String capacityCTank) {
		this.capacityCTank = capacityCTank;
	}
	/**
	 * @return the filterType
	 */
	public String getFilterType() {
		return filterType;
	}
	/**
	 * @param filterType the filterType to set
	 */
	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}
	/**
	 * @return the schmSource
	 */
	public String getSchmSource() {
		return schmSource;
	}
	/**
	 * @param schmSource the schmSource to set
	 */
	public void setSchmSource(String schmSource) {
		this.schmSource = schmSource;
	}
	/**
	 * @return the schmType
	 */
	public String getSchmType() {
		return schmType;
	}
	/**
	 * @param schmType the schmType to set
	 */
	public void setSchmType(String schmType) {
		this.schmType = schmType;
	}
	/**
	 * @return the dateComm
	 */
	public String getDateComm() {
		return dateComm;
	}
	/**
	 * @param dateComm the dateComm to set
	 */
	public void setDateComm(String dateComm) {
		this.dateComm = dateComm;
	}
	/**
	 * @return the programId
	 */
	public String getProgramId() {
		return programId;
	}
	/**
	 * @param programId the programId to set
	 */
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	/**
	 * @return the srvcLevel
	 */
	public String getSrvcLevel() {
		return srvcLevel;
	}
	/**
	 * @param srvcLevel the srvcLevel to set
	 */
	public void setSrvcLevel(String srvcLevel) {
		this.srvcLevel = srvcLevel;
	}
	/**
	 * @param vilIds the vilIds to set
	 */
	public void setVilIds(String vilIds) {
		this.vilIds = vilIds;
	}
	/**
	 * @return the schmSource
	 *//*
	public String getSchmSource() {
		return schmSource;
	}
	*//**
	 * @param schmSource the schmSource to set
	 *//*
	public void setSchmSource(String schmSource) {
		this.schmSource = schmSource;
	}
	*//**
	 * @return the schmType
	 *//*
	public String getSchmType() {
		return schmType;
	}
	*//**
	 * @param schmType the schmType to set
	 *//*
	public void setSchmType(String schmType) {
		this.schmType = schmType;
	}
	*//**
	 * @return the dateComm
	 *//*
	public String getDateComm() {
		return dateComm;
	}
	*//**
	 * @param dateComm the dateComm to set
	 *//*
	public void setDateComm(String dateComm) {
		this.dateComm = dateComm;
	}
	*//**
	 * @return the programId
	 *//*
	public String getProgramId() {
		return programId;
	}
	*//**
	 * @param programId the programId to set
	 *//*
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	*//**
	 * @return the srvcLevel
	 *//*
	public String getSrvcLevel() {
		return srvcLevel;
	}
	*//**
	 * @param srvcLevel the srvcLevel to set
	 *//*
	public void setSrvcLevel(String srvcLevel) {
		this.srvcLevel = srvcLevel;
	}
	*//**
	 * @return the schemeUpgrd
	 *//*
	public String getSchemeUpgrd() {
		return schemeUpgrd;
	}
	*//**
	 * @param schemeUpgrd the schemeUpgrd to set
	 *//*
	public void setSchemeUpgrd(String schemeUpgrd) {
		this.schemeUpgrd = schemeUpgrd;
	}
	*//**
	 * @return the schemeExpen
	 *//*
	public String getSchemeExpen() {
		return schemeExpen;
	}
	*//**
	 * @param schemeExpen the schemeExpen to set
	 *//*
	public void setSchemeExpen(String schemeExpen) {
		this.schemeExpen = schemeExpen;
	}
	*//**
	 * @return the depthTubewell
	 *//*
	public String getDepthTubewell() {
		return depthTubewell;
	}
	*//**
	 * @param depthTubewell the depthTubewell to set
	 *//*
	public void setDepthTubewell(String depthTubewell) {
		this.depthTubewell = depthTubewell;
	}
	*//**
	 * @return the sizeTubewell
	 *//*
	public String getSizeTubewell() {
		return sizeTubewell;
	}
	*//**
	 * @param sizeTubewell the sizeTubewell to set
	 *//*
	public void setSizeTubewell(String sizeTubewell) {
		this.sizeTubewell = sizeTubewell;
	}
	*//**
	 * @return the yearDrilling
	 *//*
	public String getYearDrilling() {
		return yearDrilling;
	}
	*//**
	 * @param yearDrilling the yearDrilling to set
	 *//*
	public void setYearDrilling(String yearDrilling) {
		this.yearDrilling = yearDrilling;
	}
	*//**
	 * @return the dischargeCommun
	 *//*
	public String getDischargeCommun() {
		return dischargeCommun;
	}
	*//**
	 * @param dischargeCommun the dischargeCommun to set
	 *//*
	public void setDischargeCommun(String dischargeCommun) {
		this.dischargeCommun = dischargeCommun;
	}
	*//**
	 * @return the presentDischrg
	 *//*
	public String getPresentDischrg() {
		return presentDischrg;
	}
	*//**
	 * @param presentDischrg the presentDischrg to set
	 *//*
	public void setPresentDischrg(String presentDischrg) {
		this.presentDischrg = presentDischrg;
	}
	*//**
	 * @return the presentSpringLvl
	 *//*
	public String getPresentSpringLvl() {
		return presentSpringLvl;
	}
	*//**
	 * @param presentSpringLvl the presentSpringLvl to set
	 *//*
	public void setPresentSpringLvl(String presentSpringLvl) {
		this.presentSpringLvl = presentSpringLvl;
	}
	*//**
	 * @return the installationNewMachinery
	 *//*
	public String getInstallationNewMachinery() {
		return installationNewMachinery;
	}
	*//**
	 * @param installationNewMachinery the installationNewMachinery to set
	 *//*
	public void setInstallationNewMachinery(String installationNewMachinery) {
		this.installationNewMachinery = installationNewMachinery;
	}
	*//**
	 * @return the capacityMachinery
	 *//*
	public String getCapacityMachinery() {
		return capacityMachinery;
	}
	*//**
	 * @param capacityMachinery the capacityMachinery to set
	 *//*
	public void setCapacityMachinery(String capacityMachinery) {
		this.capacityMachinery = capacityMachinery;
	}
	*//**
	 * @return the inltType
	 *//*
	public String getInltType() {
		return inltType;
	}
	*//**
	 * @param inltType the inltType to set
	 *//*
	public void setInltType(String inltType) {
		this.inltType = inltType;
	}
	*//**
	 * @return the inletLnght
	 *//*
	public String getInletLnght() {
		return inletLnght;
	}
	*//**
	 * @param inletLnght the inletLnght to set
	 *//*
	public void setInletLnght(String inletLnght) {
		this.inletLnght = inletLnght;
	}
	*//**
	 * @return the pipType
	 *//*
	public String getPipType() {
		return pipType;
	}
	*//**
	 * @param pipType the pipType to set
	 *//*
	public void setPipType(String pipType) {
		this.pipType = pipType;
	}
	*//**
	 * @return the capacitySTank
	 *//*
	public String getCapacitySTank() {
		return capacitySTank;
	}
	*//**
	 * @param capacitySTank the capacitySTank to set
	 *//*
	public void setCapacitySTank(String capacitySTank) {
		this.capacitySTank = capacitySTank;
	}
	*//**
	 * @return the capacityHTank
	 *//*
	public String getCapacityHTank() {
		return capacityHTank;
	}
	*//**
	 * @param capacityHTank the capacityHTank to set
	 *//*
	public void setCapacityHTank(String capacityHTank) {
		this.capacityHTank = capacityHTank;
	}
	*//**
	 * @return the capacityCTank
	 *//*
	public String getCapacityCTank() {
		return capacityCTank;
	}
	*//**
	 * @param capacityCTank the capacityCTank to set
	 *//*
	public void setCapacityCTank(String capacityCTank) {
		this.capacityCTank = capacityCTank;
	}
	*//**
	 * @return the filterType
	 *//*
	public String getFilterType() {
		return filterType;
	}
	*//**
	 * @param filterType the filterType to set
	 *//*
	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}
	*//**
	 * @return the filterNo
	 *//*
	public String getFilterNo() {
		return filterNo;
	}
	*//**
	 * @param filterNo the filterNo to set
	 *//*
	public void setFilterNo(String filterNo) {
		this.filterNo = filterNo;
	}
	*//**
	 * @return the filterCapacity
	 *//*
	public String getFilterCapacity() {
		return filterCapacity;
	}
	*//**
	 * @param filterCapacity the filterCapacity to set
	 *//*
	public void setFilterCapacity(String filterCapacity) {
		this.filterCapacity = filterCapacity;
	}
	*//**
	 * @return the capacityRawWatr
	 *//*
	public String getCapacityRawWatr() {
		return capacityRawWatr;
	}
	*//**
	 * @param capacityRawWatr the capacityRawWatr to set
	 *//*
	public void setCapacityRawWatr(String capacityRawWatr) {
		this.capacityRawWatr = capacityRawWatr;
	}
	*//**
	 * @return the capacityClrWater
	 *//*
	public String getCapacityClrWater() {
		return capacityClrWater;
	}
	*//**
	 * @param capacityClrWater the capacityClrWater to set
	 *//*
	public void setCapacityClrWater(String capacityClrWater) {
		this.capacityClrWater = capacityClrWater;
	}
	*//**
	 * @return the capMachinery
	 *//*
	public String getCapMachinery() {
		return capMachinery;
	}
	*//**
	 * @param capMachinery the capMachinery to set
	 *//*
	public void setCapMachinery(String capMachinery) {
		this.capMachinery = capMachinery;
	}
	*//**
	 * @return the no_ofOhsr
	 *//*
	public String getNo_ofOhsr() {
		return No_ofOhsr;
	}
	*//**
	 * @param no_ofOhsr the no_ofOhsr to set
	 *//*
	public void setNo_ofOhsr(String no_ofOhsr) {
		No_ofOhsr = no_ofOhsr;
	}
	*//**
	 * @return the oHSRConstructionDate
	 *//*
	public String getoHSRConstructionDate() {
		return oHSRConstructionDate;
	}
	*//**
	 * @param oHSRConstructionDate the oHSRConstructionDate to set
	 *//*
	public void setoHSRConstructionDate(String oHSRConstructionDate) {
		this.oHSRConstructionDate = oHSRConstructionDate;
	}
	*//**
	 * @return the oHSRCap
	 *//*
	public String getoHSRCap() {
		return oHSRCap;
	}
	*//**
	 * @param oHSRCap the oHSRCap to set
	 *//*
	public void setoHSRCap(String oHSRCap) {
		this.oHSRCap = oHSRCap;
	}
	*//**
	 * @return the oHSRWorkingCond
	 *//*
	public String getoHSRWorkingCond() {
		return oHSRWorkingCond;
	}
	*//**
	 * @param oHSRWorkingCond the oHSRWorkingCond to set
	 *//*
	public void setoHSRWorkingCond(String oHSRWorkingCond) {
		this.oHSRWorkingCond = oHSRWorkingCond;
	}
	*//**
	 * @return the oHSRCond
	 *//*
	public String getoHSRCond() {
		return oHSRCond;
	}
	*//**
	 * @param oHSRCond the oHSRCond to set
	 *//*
	public void setoHSRCond(String oHSRCond) {
		this.oHSRCond = oHSRCond;
	}
	*//**
	 * @return the disinfType
	 *//*
	public String getDisinfType() {
		return disinfType;
	}
	*//**
	 * @param disinfType the disinfType to set
	 *//*
	public void setDisinfType(String disinfType) {
		this.disinfType = disinfType;
	}
	*//**
	 * @return the disinPrStatus
	 *//*
	public String getDisinPrStatus() {
		return disinPrStatus;
	}
	*//**
	 * @param disinPrStatus the disinPrStatus to set
	 *//*
	public void setDisinPrStatus(String disinPrStatus) {
		this.disinPrStatus = disinPrStatus;
	}
	*//**
	 * @return the schemeOperatBy
	 *//*
	public String getSchemeOperatBy() {
		return schemeOperatBy;
	}
	*//**
	 * @param schemeOperatBy the schemeOperatBy to set
	 *//*
	public void setSchemeOperatBy(String schemeOperatBy) {
		this.schemeOperatBy = schemeOperatBy;
	}
	*//**
	 * @return the stafScheme
	 *//*
	public String getStafScheme() {
		return stafScheme;
	}
	*//**
	 * @param stafScheme the stafScheme to set
	 *//*
	public void setStafScheme(String stafScheme) {
		this.stafScheme = stafScheme;
	}
	*//**
	 * @return the sanctionLoad
	 *//*
	public String getSanctionLoad() {
		return sanctionLoad;
	}
	*//**
	 * @param sanctionLoad the sanctionLoad to set
	 *//*
	public void setSanctionLoad(String sanctionLoad) {
		this.sanctionLoad = sanctionLoad;
	}
	*//**
	 * @return the pendingBill3103
	 *//*
	public String getPendingBill3103() {
		return pendingBill3103;
	}
	*//**
	 * @param pendingBill3103 the pendingBill3103 to set
	 *//*
	public void setPendingBill3103(String pendingBill3103) {
		this.pendingBill3103 = pendingBill3103;
	}
	*//**
	 * @return the pendingBill3006
	 *//*
	public String getPendingBill3006() {
		return pendingBill3006;
	}
	*//**
	 * @param pendingBill3006 the pendingBill3006 to set
	 *//*
	public void setPendingBill3006(String pendingBill3006) {
		this.pendingBill3006 = pendingBill3006;
	}
	*//**
	 * @return the avgMonthBillWSS
	 *//*
	public String getAvgMonthBillWSS() {
		return avgMonthBillWSS;
	}
	*//**
	 * @param avgMonthBillWSS the avgMonthBillWSS to set
	 *//*
	public void setAvgMonthBillWSS(String avgMonthBillWSS) {
		this.avgMonthBillWSS = avgMonthBillWSS;
	}
	
	*/
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VillageSourceGridBean [vilIds=" + vilIds + ", schmSource=" + schmSource + ", schmType=" + schmType
				+ ", dateComm=" + dateComm + ", programId=" + programId + ", srvcLevel=" + srvcLevel + ", schemeUpgrd="
				+ schemeUpgrd + ", schemeExpen=" + schemeExpen + ", depthTubewell=" + depthTubewell + ", sizeTubewell="
				+ sizeTubewell + ", yearDrilling=" + yearDrilling + ", dischargeCommun=" + dischargeCommun
				+ ", presentDischrg=" + presentDischrg + ", presentSpringLvl=" + presentSpringLvl
				+ ", installationNewMachinery=" + installationNewMachinery + ", capacityMachinery=" + capacityMachinery
				+ ", inltType=" + inltType + ", inletLnght=" + inletLnght + ", pipType=" + pipType + ", capacitySTank="
				+ capacitySTank + ", capacityHTank=" + capacityHTank + ", capacityCTank=" + capacityCTank
				+ ", filterType=" + filterType + ", filterNo=" + filterNo + ", filterCapacity=" + filterCapacity
				+ ", capacityRawWatr=" + capacityRawWatr + ", capacityClrWater=" + capacityClrWater + ", capMachinery="
				+ capMachinery + ", oHSRConstructionDate=" + oHSRConstructionDate + ", oHSRCap=" + oHSRCap
				+ ", oHSRWorkingCond=" + oHSRWorkingCond + ", oHSRCond=" + oHSRCond + ", disinfType=" + disinfType
				+ ", disInstallationTime=" + disInstallationTime + ", disinPrStatus=" + disinPrStatus
				+ ", schemeOperatBy=" + schemeOperatBy + ", stafScheme=" + stafScheme + ", sanctionLoad=" + sanctionLoad
				+ ", pendingBill3103=" + pendingBill3103 + ", pendingBill3006=" + pendingBill3006 + ", avgMonthBillWSS="
				+ avgMonthBillWSS + ", oHSRDismantling1=" + oHSRDismantling1 + ", oHSRFullSuplyLvl1="
				+ oHSRFullSuplyLvl1 + ", noOhsrOhsr=" + noOhsrOhsr + ", ground_water_potablestatus="
				+ ground_water_potablestatus + ", ground_water_potableNo=" + ground_water_potableNo
				+ ", preventive_measuresadopted=" + preventive_measuresadopted + ", any_other_desc=" + any_other_desc
				+ ", capacity_ofplant=" + capacity_ofplant + ", dateInstallation=" + dateInstallation
				+ ", being_operatedby=" + being_operatedby + ", volume_of_Water_Dailybasis="
				+ volume_of_Water_Dailybasis + ", disposal_of_rejectwater=" + disposal_of_rejectwater
				+ ", penetration_inpercentage=" + penetration_inpercentage + ", present_rate_of_usercharges="
				+ present_rate_of_usercharges + ", seperate_SanctionedLoad=" + seperate_SanctionedLoad
				+ ", seperate_Pending_eletricbill31032017=" + seperate_Pending_eletricbill31032017
				+ ", average_monthly_bill_of_Treatmentplant=" + average_monthly_bill_of_Treatmentplant + ", schemeSrc="
				+ schemeSrc + ", oHSRCtDepth1=" + oHSRCtDepth1 + ", oHSRUgsrDepth1=" + oHSRUgsrDepth1 + ", oHSRCtDia1="
				+ oHSRCtDia1 + ", oHSRUgsrDia1=" + oHSRUgsrDia1 + "]";
	}
	
	
	
	
}
