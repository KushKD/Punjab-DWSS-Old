package com.prwss.mis.reports.performa.dao;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name = "draft_performa2", schema = MISConstants.MIS_DB_SCHEMA_NAME)
public class SchemeSourcePerformaBean {

	@Id
	@GeneratedValue(generator = "seq_id", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "seq_id", sequenceName = "prwss_main.seq_id")
	@Column(name = "id2")
	private int id;
	
	@Column(name = "village_id")
	private String villageId;
	
	@Column(name = "scheme_id")
	private String schemeId;
	
	@Column(name = "scheme_source")
	private String schemeSource;
	
	@Column(name = "scheme_type")
	private String schemeType;
	
	@Column(name = "date_of_commissioning")
	private Date commissioningDate;
	
	@Column(name = "service_level")
	private String serviceLevel;
	
	@Column(name = "scheme_upgraded")
	private Date schemeUpgraded;
	
	@Column(name = "scheme_expenditure")
	private Double schemeExpenditure;
	
	@Column(name = "depth_of_tubewell")
	private Double tubewellDepth;
	
	@Column(name = "prog_id")
	private String progId;
	
	@Column(name = "size_of_tubewell")
	private String tubewellSize;
	
	@Column(name = "year_of_drilling")
	private Date drillingYear;
	
	@Column(name = "present_discharge")
	private Double presentDischarge;
	
	@Column(name = "original_discharge_at_time_of_commissioning")
	private Double dischargeComm;
	
	@Column(name = "present_spring_level")
	private Double presentSpringLevel;
	
	@Column(name = "installation_of_new_machinery")
	private Date machineryInstallation;
	
	@Column(name = "capacity_of_machinery")
	private Double machineryCapacity;
	
	@Column(name = "inlet_channel_type")
	private String inletChannelType;
	
	@Column(name = "inlet_channel_lenght")
	private Double inletChannelLength;
	
	@Column(name = "pipe_type")
	private String pipeType;
	
	@Column(name = "capacity_of_ss_tank")
	private Double ssTankCapacity;
	
	@Column(name = "capacity_of_hl_tank")
	private Double hlTankCapacity;
	
	@Column(name = "capacity_of_cw_tank")
	private Double cwTankCapacity;
	
	@Column(name = "filtertion_type")
	private String filterationType;
	
	@Column(name = "filtertion_no")
	private Double filtertionNo;
	
	@Column(name = "filtertion_capacity")
	private Double filtertionCapacity;
	
	@Column(name = "capacity_of_raw_water")
	private Double rawWaterCaps;
	
	@Column(name = "capacity_of_clear_water")
	private Double clearWaterCaps;
	
	@Column(name = "no_of_ohsr")
	private Double ohsrNo;
	
	@Column(name = "ohsr_construction_date")
	private Date ohsrConsDate;
	
	@Column(name = "ohsr_capcity")
	private Double ohsrCaps;
	//added new two column
	@Column(name = "ohsr_ct_dia")
	private Double ohsr_ct;
	
	@Column(name = "ohsr_ugsr_dia")
	private Double ohsr_ugsr;
	
	@Column(name = "ohsr_ct_dep")
	private Double ohsr_ct_depth;
	
	@Column(name = "ohsr_ugsr_dep")
	private Double ohsr_ugsr_depth;
	
	
	
	
	
	@Column(name = "ohsr_working_condition")
	private Integer ohsrWorkingCondition;
	
	@Column(name = "ohsr_working_condition_if_no")
	private String ohsrConditionIfNo;
	
	@Column(name = "dismantling_received")
	private String dismantling_received;
	
	@Column(name = "ohsr_full_supply_level1")
	private String ohsr_full_supply_level;
	
	@Column(name = "disinfection_type")
	private String disinfectionType;
	
	@Column(name = "disinfection_instalation_time")
	private Date disinfectionInstalTime;
	
	@Column(name = "disinfection_present_status")
	private String disnPresentStatus;
	
	@Column(name = "scheme_operated_by")
	private String schemeOpertedBy;
	
	@Column(name = "dwss_operated_arrangement")
	private String dwss_operated_arrangement;
	
	@Column(name = "sanctioned_load")
	private Double sanctionedLoad;
	
	@Column(name = "pending__bill_31032017")
	private Double pendingBill3103;
	
	@Column(name = "pending__bill_30062017")
	private Double pendingBill3006;
	
	@Column(name = "avg_month_billof_wss")
	private Double avgMonthBill;

	
	
	
	public Double getOhsr_ct_depth() {
		return ohsr_ct_depth;
	}

	public void setOhsr_ct_depth(Double ohsr_ct_depth) {
		this.ohsr_ct_depth = ohsr_ct_depth;
	}

	public Double getOhsr_ugsr_depth() {
		return ohsr_ugsr_depth;
	}

	public void setOhsr_ugsr_depth(Double ohsr_ugsr_depth) {
		this.ohsr_ugsr_depth = ohsr_ugsr_depth;
	}

	/**
	 * @return the ohsr_ct
	 */
	public Double getOhsr_ct() {
		return ohsr_ct;
	}

	/**
	 * @param ohsr_ct the ohsr_ct to set
	 */
	public void setOhsr_ct(Double ohsr_ct) {
		this.ohsr_ct = ohsr_ct;
	}

	/**
	 * @return the ohsr_ugsr
	 */
	public Double getOhsr_ugsr() {
		return ohsr_ugsr;
	}

	/**
	 * @param ohsr_ugsr the ohsr_ugsr to set
	 */
	public void setOhsr_ugsr(Double ohsr_ugsr) {
		this.ohsr_ugsr = ohsr_ugsr;
	}

	/**
	 * @return the dismantling_received
	 */
	public String getDismantling_received() {
		return dismantling_received;
	}

	/**
	 * @param dismantling_received the dismantling_received to set
	 */
	public void setDismantling_received(String dismantling_received) {
		this.dismantling_received = dismantling_received;
	}

	/**
	 * @return the ohsr_full_supply_level
	 */
	public String getOhsr_full_supply_level() {
		return ohsr_full_supply_level;
	}

	/**
	 * @param ohsr_full_supply_level the ohsr_full_supply_level to set
	 */
	public void setOhsr_full_supply_level(String ohsr_full_supply_level) {
		this.ohsr_full_supply_level = ohsr_full_supply_level;
	}

	/**
	 * @return the dischargeComm
	 */
	public Double getDischargeComm() {
		return dischargeComm;
	}

	/**
	 * @param dischargeComm the dischargeComm to set
	 */
	public void setDischargeComm(Double dischargeComm) {
		this.dischargeComm = dischargeComm;
	}

	/**
	 * @param presentDischarge the presentDischarge to set
	 */
	public void setPresentDischarge(Double presentDischarge) {
		this.presentDischarge = presentDischarge;
	}

	/**
	 * @return the dwss_operated_arrangement
	 */
	public String getDwss_operated_arrangement() {
		return dwss_operated_arrangement;
	}

	/**
	 * @param dwss_operated_arrangement the dwss_operated_arrangement to set
	 */
	public void setDwss_operated_arrangement(String dwss_operated_arrangement) {
		this.dwss_operated_arrangement = dwss_operated_arrangement;
	}

	/**
	 * @return the villageId
	 */
	public String getVillageId() {
		return villageId;
	}

	/**
	 * @param villageId the villageId to set
	 */
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}

	/**
	 * @return the schemeId
	 */
	public String getSchemeId() {
		return schemeId;
	}

	/**
	 * @param schemeId the schemeId to set
	 */
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	/**
	 * @return the schemeExpenditure
	 */
	public Double getSchemeExpenditure() {
		return schemeExpenditure;
	}

	/**
	 * @param schemeExpenditure the schemeExpenditure to set
	 */
	public void setSchemeExpenditure(Double schemeExpenditure) {
		this.schemeExpenditure = schemeExpenditure;
	}

	/**
	 * @return the tubewellDepth
	 */
	public Double getTubewellDepth() {
		return tubewellDepth;
	}

	/**
	 * @param tubewellDepth the tubewellDepth to set
	 */
	public void setTubewellDepth(Double tubewellDepth) {
		this.tubewellDepth = tubewellDepth;
	}

	/**
	 * @return the progId
	 */
	public String getProgId() {
		return progId;
	}

	/**
	 * @param progId the progId to set
	 */
	public void setProgId(String progId) {
		this.progId = progId;
	}

	

	/**
	 * @return the tubewellSize
	 */
	public String getTubewellSize() {
		return tubewellSize;
	}

	/**
	 * @param tubewellSize the tubewellSize to set
	 */
	public void setTubewellSize(String tubewellSize) {
		this.tubewellSize = tubewellSize;
	}

	/**
	 * @return the drillingYear
	 */
	public Date getDrillingYear() {
		return drillingYear;
	}

	/**
	 * @param drillingYear the drillingYear to set
	 */
	public void setDrillingYear(Date drillingYear) {
		this.drillingYear = drillingYear;
	}

	
	/**
	 * @return the presentDischarge
	 */
	public Double getPresentDischarge() {
		return presentDischarge;
	}

	/**
	 * @return the presentSpringLevel
	 */
	public Double getPresentSpringLevel() {
		return presentSpringLevel;
	}

	/**
	 * @param presentSpringLevel the presentSpringLevel to set
	 */
	public void setPresentSpringLevel(Double presentSpringLevel) {
		this.presentSpringLevel = presentSpringLevel;
	}

	/**
	 * @return the machineryInstallation
	 */
	public Date getMachineryInstallation() {
		return machineryInstallation;
	}

	/**
	 * @param machineryInstallation the machineryInstallation to set
	 */
	public void setMachineryInstallation(Date machineryInstallation) {
		this.machineryInstallation = machineryInstallation;
	}

	

	/**
	 * @return the machineryCapacity
	 */
	public Double getMachineryCapacity() {
		return machineryCapacity;
	}

	/**
	 * @param machineryCapacity the machineryCapacity to set
	 */
	public void setMachineryCapacity(Double machineryCapacity) {
		this.machineryCapacity = machineryCapacity;
	}

	/**
	 * @return the inletChannelType
	 */
	public String getInletChannelType() {
		return inletChannelType;
	}

	/**
	 * @param inletChannelType the inletChannelType to set
	 */
	public void setInletChannelType(String inletChannelType) {
		this.inletChannelType = inletChannelType;
	}

	/**
	 * @return the inletChannelLength
	 */
	public Double getInletChannelLength() {
		return inletChannelLength;
	}

	/**
	 * @param inletChannelLength the inletChannelLength to set
	 */
	public void setInletChannelLength(Double inletChannelLength) {
		this.inletChannelLength = inletChannelLength;
	}

	/**
	 * @return the pipeType
	 */
	public String getPipeType() {
		return pipeType;
	}

	/**
	 * @param pipeType the pipeType to set
	 */
	public void setPipeType(String pipeType) {
		this.pipeType = pipeType;
	}

	/**
	 * @return the ssTankCapacity
	 */
	public Double getSsTankCapacity() {
		return ssTankCapacity;
	}

	/**
	 * @param ssTankCapacity the ssTankCapacity to set
	 */
	public void setSsTankCapacity(Double ssTankCapacity) {
		this.ssTankCapacity = ssTankCapacity;
	}

	/**
	 * @return the hlTankCapacity
	 */
	public Double getHlTankCapacity() {
		return hlTankCapacity;
	}

	/**
	 * @param hlTankCapacity the hlTankCapacity to set
	 */
	public void setHlTankCapacity(Double hlTankCapacity) {
		this.hlTankCapacity = hlTankCapacity;
	}

	/**
	 * @return the cwTankCapacity
	 */
	public Double getCwTankCapacity() {
		return cwTankCapacity;
	}

	/**
	 * @param cwTankCapacity the cwTankCapacity to set
	 */
	public void setCwTankCapacity(Double cwTankCapacity) {
		this.cwTankCapacity = cwTankCapacity;
	}

	/**
	 * @return the filterationType
	 */
	public String getFilterationType() {
		return filterationType;
	}

	/**
	 * @param filterationType the filterationType to set
	 */
	public void setFilterationType(String filterationType) {
		this.filterationType = filterationType;
	}

	

	/**
	 * @return the filtertionCapacity
	 */
	public Double getFiltertionCapacity() {
		return filtertionCapacity;
	}

	/**
	 * @param filtertionCapacity the filtertionCapacity to set
	 */
	public void setFiltertionCapacity(Double filtertionCapacity) {
		this.filtertionCapacity = filtertionCapacity;
	}

	/**
	 * @return the rawWaterCaps
	 */
	public Double getRawWaterCaps() {
		return rawWaterCaps;
	}

	/**
	 * @param rawWaterCaps the rawWaterCaps to set
	 */
	public void setRawWaterCaps(Double rawWaterCaps) {
		this.rawWaterCaps = rawWaterCaps;
	}

	/**
	 * @return the clearWaterCaps
	 */
	public Double getClearWaterCaps() {
		return clearWaterCaps;
	}

	/**
	 * @param clearWaterCaps the clearWaterCaps to set
	 */
	public void setClearWaterCaps(Double clearWaterCaps) {
		this.clearWaterCaps = clearWaterCaps;
	}

	

	/**
	 * @return the ohsrConsDate
	 */
	public Date getOhsrConsDate() {
		return ohsrConsDate;
	}

	/**
	 * @param ohsrConsDate the ohsrConsDate to set
	 */
	public void setOhsrConsDate(Date ohsrConsDate) {
		this.ohsrConsDate = ohsrConsDate;
	}

	/**
	 * @return the ohsrCaps
	 */
	public Double getOhsrCaps() {
		return ohsrCaps;
	}

	/**
	 * @param ohsrCaps the ohsrCaps to set
	 */
	public void setOhsrCaps(Double ohsrCaps) {
		this.ohsrCaps = ohsrCaps;
	}

	

	/**
	 * @return the filtertionNo
	 */
	public Double getFiltertionNo() {
		return filtertionNo;
	}

	/**
	 * @param filtertionNo the filtertionNo to set
	 */
	public void setFiltertionNo(Double filtertionNo) {
		this.filtertionNo = filtertionNo;
	}

	/**
	 * @return the ohsrNo
	 */
	public Double getOhsrNo() {
		return ohsrNo;
	}

	/**
	 * @param ohsrNo the ohsrNo to set
	 */
	public void setOhsrNo(Double ohsrNo) {
		this.ohsrNo = ohsrNo;
	}

	

	/**
	 * @return the ohsrWorkingCondition
	 */
	public Integer getOhsrWorkingCondition() {
		return ohsrWorkingCondition;
	}

	/**
	 * @param ohsrWorkingCondition the ohsrWorkingCondition to set
	 */
	public void setOhsrWorkingCondition(Integer ohsrWorkingCondition) {
		this.ohsrWorkingCondition = ohsrWorkingCondition;
	}

	/**
	 * @return the ohsrConditionIfNo
	 */
	public String getOhsrConditionIfNo() {
		return ohsrConditionIfNo;
	}

	/**
	 * @param ohsrConditionIfNo the ohsrConditionIfNo to set
	 */
	public void setOhsrConditionIfNo(String ohsrConditionIfNo) {
		this.ohsrConditionIfNo = ohsrConditionIfNo;
	}

	/**
	 * @return the disinfectionType
	 */
	public String getDisinfectionType() {
		return disinfectionType;
	}

	/**
	 * @param disinfectionType the disinfectionType to set
	 */
	public void setDisinfectionType(String disinfectionType) {
		this.disinfectionType = disinfectionType;
	}

	/**
	 * @return the disinfectionInstalTime
	 */
	public Date getDisinfectionInstalTime() {
		return disinfectionInstalTime;
	}

	/**
	 * @param disinfectionInstalTime the disinfectionInstalTime to set
	 */
	public void setDisinfectionInstalTime(Date disinfectionInstalTime) {
		this.disinfectionInstalTime = disinfectionInstalTime;
	}

	/**
	 * @return the disnPresentStatus
	 */
	public String getDisnPresentStatus() {
		return disnPresentStatus;
	}

	/**
	 * @param disnPresentStatus the disnPresentStatus to set
	 */
	public void setDisnPresentStatus(String disnPresentStatus) {
		this.disnPresentStatus = disnPresentStatus;
	}

	/**
	 * @return the schemeOpertedBy
	 */
	public String getSchemeOpertedBy() {
		return schemeOpertedBy;
	}

	/**
	 * @param schemeOpertedBy the schemeOpertedBy to set
	 */
	public void setSchemeOpertedBy(String schemeOpertedBy) {
		this.schemeOpertedBy = schemeOpertedBy;
	}

	/**
	 * @return the sanctionedLoad
	 */
	public Double getSanctionedLoad() {
		return sanctionedLoad;
	}

	/**
	 * @param sanctionedLoad the sanctionedLoad to set
	 */
	public void setSanctionedLoad(Double sanctionedLoad) {
		this.sanctionedLoad = sanctionedLoad;
	}

	/**
	 * @return the pendingBill3103
	 */
	public Double getPendingBill3103() {
		return pendingBill3103;
	}

	/**
	 * @param pendingBill3103 the pendingBill3103 to set
	 */
	public void setPendingBill3103(Double pendingBill3103) {
		this.pendingBill3103 = pendingBill3103;
	}

	/**
	 * @return the pendingBill3006
	 */
	public Double getPendingBill3006() {
		return pendingBill3006;
	}

	/**
	 * @param pendingBill3006 the pendingBill3006 to set
	 */
	public void setPendingBill3006(Double pendingBill3006) {
		this.pendingBill3006 = pendingBill3006;
	}

	/**
	 * @return the avgMonthBill
	 */
	public Double getAvgMonthBill() {
		return avgMonthBill;
	}

	/**
	 * @param avgMonthBill the avgMonthBill to set
	 */
	public void setAvgMonthBill(Double avgMonthBill) {
		this.avgMonthBill = avgMonthBill;
	}

	

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the schemeSource
	 */
	public String getSchemeSource() {
		return schemeSource;
	}

	/**
	 * @param schemeSource the schemeSource to set
	 */
	public void setSchemeSource(String schemeSource) {
		this.schemeSource = schemeSource;
	}

	/**
	 * @return the schemeType
	 */
	public String getSchemeType() {
		return schemeType;
	}

	/**
	 * @param schemeType the schemeType to set
	 */
	public void setSchemeType(String schemeType) {
		this.schemeType = schemeType;
	}

	/**
	 * @return the commissioningDate
	 */
	public Date getCommissioningDate() {
		return commissioningDate;
	}

	/**
	 * @param commissioningDate the commissioningDate to set
	 */
	public void setCommissioningDate(Date commissioningDate) {
		this.commissioningDate = commissioningDate;
	}

	/**
	 * @return the serviceLevel
	 */
	public String getServiceLevel() {
		return serviceLevel;
	}

	/**
	 * @param serviceLevel the serviceLevel to set
	 */
	public void setServiceLevel(String serviceLevel) {
		this.serviceLevel = serviceLevel;
	}

	/**
	 * @return the schemeUpgraded
	 */
	public Date getSchemeUpgraded() {
		return schemeUpgraded;
	}

	/**
	 * @param schemeUpgraded the schemeUpgraded to set
	 */
	public void setSchemeUpgraded(Date schemeUpgraded) {
		this.schemeUpgraded = schemeUpgraded;
	}

	

	/**
	 * @return the schemeUpgraded
	 */
	
	

	/**
	 * @return the schemeExpenditure
	 *//*
	public Double getSchemeExpenditure() {
		return schemeExpenditure;
	}

	*//**
	 * @param schemeExpenditure the schemeExpenditure to set
	 *//*
	public void setSchemeExpenditure(Double schemeExpenditure) {
		this.schemeExpenditure = schemeExpenditure;
	}

	*//**
	 * @return the tubewellDepth
	 *//*
	public Double getTubewellDepth() {
		return tubewellDepth;
	}

	*//**
	 * @param tubewellDepth the tubewellDepth to set
	 *//*
	public void setTubewellDepth(Double tubewellDepth) {
		this.tubewellDepth = tubewellDepth;
	}

	*//**
	 * @return the progId
	 *//*
	public String getProgId() {
		return progId;
	}

	*//**
	 * @param progId the progId to set
	 *//*
	public void setProgId(String progId) {
		this.progId = progId;
	}

	*//**
	 * @return the tubewellSize
	 *//*
	public Double getTubewellSize() {
		return tubewellSize;
	}

	*//**
	 * @param tubewellSize the tubewellSize to set
	 *//*
	public void setTubewellSize(Double tubewellSize) {
		this.tubewellSize = tubewellSize;
	}

	*//**
	 * @return the drillingYear
	 *//*
	public Double getDrillingYear() {
		return drillingYear;
	}

	*//**
	 * @param drillingYear the drillingYear to set
	 *//*
	public void setDrillingYear(Double drillingYear) {
		this.drillingYear = drillingYear;
	}

	*//**
	 * @return the presentDischarge
	 *//*
	public Double getPresentDischarge() {
		return presentDischarge;
	}

	*//**
	 * @param presentDischarge the presentDischarge to set
	 *//*
	public void setPresentDischarge(Double presentDischarge) {
		this.presentDischarge = presentDischarge;
	}

	*//**
	 * @return the dischargeComm
	 *//*
	public Double getDischargeComm() {
		return dischargeComm;
	}

	*//**
	 * @param dischargeComm the dischargeComm to set
	 *//*
	public void setDischargeComm(Double dischargeComm) {
		this.dischargeComm = dischargeComm;
	}

	*//**
	 * @return the presentSpringLevel
	 *//*
	public Double getPresentSpringLevel() {
		return presentSpringLevel;
	}

	*//**
	 * @param presentSpringLevel the presentSpringLevel to set
	 *//*
	public void setPresentSpringLevel(Double presentSpringLevel) {
		this.presentSpringLevel = presentSpringLevel;
	}

	*//**
	 * @return the machineryInstallation
	 *//*
	public Date getMachineryInstallation() {
		return machineryInstallation;
	}

	*//**
	 * @param machineryInstallation the machineryInstallation to set
	 *//*
	public void setMachineryInstallation(Date machineryInstallation) {
		this.machineryInstallation = machineryInstallation;
	}

	*//**
	 * @return the machineryCapacity
	 *//*
	public Double getMachineryCapacity() {
		return machineryCapacity;
	}

	*//**
	 * @param machineryCapacity the machineryCapacity to set
	 *//*
	public void setMachineryCapacity(Double machineryCapacity) {
		this.machineryCapacity = machineryCapacity;
	}

	*//**
	 * @return the inletChannelType
	 *//*
	public String getInletChannelType() {
		return inletChannelType;
	}

	*//**
	 * @param inletChannelType the inletChannelType to set
	 *//*
	public void setInletChannelType(String inletChannelType) {
		this.inletChannelType = inletChannelType;
	}

	*//**
	 * @return the inletChannelLength
	 *//*
	public Double getInletChannelLength() {
		return inletChannelLength;
	}

	*//**
	 * @param inletChannelLength the inletChannelLength to set
	 *//*
	public void setInletChannelLength(Double inletChannelLength) {
		this.inletChannelLength = inletChannelLength;
	}

	*//**
	 * @return the pipeType
	 *//*
	public String getPipeType() {
		return pipeType;
	}

	*//**
	 * @param pipeType the pipeType to set
	 *//*
	public void setPipeType(String pipeType) {
		this.pipeType = pipeType;
	}

	*//**
	 * @return the ssTankCapacity
	 *//*
	public Double getSsTankCapacity() {
		return ssTankCapacity;
	}

	*//**
	 * @param ssTankCapacity the ssTankCapacity to set
	 *//*
	public void setSsTankCapacity(Double ssTankCapacity) {
		this.ssTankCapacity = ssTankCapacity;
	}

	*//**
	 * @return the hlTankCapacity
	 *//*
	public Double getHlTankCapacity() {
		return hlTankCapacity;
	}

	*//**
	 * @param hlTankCapacity the hlTankCapacity to set
	 *//*
	public void setHlTankCapacity(Double hlTankCapacity) {
		this.hlTankCapacity = hlTankCapacity;
	}

	*//**
	 * @return the cwTankCapacity
	 *//*
	public Double getCwTankCapacity() {
		return cwTankCapacity;
	}

	*//**
	 * @param cwTankCapacity the cwTankCapacity to set
	 *//*
	public void setCwTankCapacity(Double cwTankCapacity) {
		this.cwTankCapacity = cwTankCapacity;
	}

	*//**
	 * @return the filterationType
	 *//*
	public String getFilterationType() {
		return filterationType;
	}

	*//**
	 * @param filterationType the filterationType to set
	 *//*
	public void setFilterationType(String filterationType) {
		this.filterationType = filterationType;
	}

	*//**
	 * @return the filtertionNo
	 *//*
	public Double getFiltertionNo() {
		return filtertionNo;
	}

	*//**
	 * @param filtertionNo the filtertionNo to set
	 *//*
	public void setFiltertionNo(Double filtertionNo) {
		this.filtertionNo = filtertionNo;
	}

	*//**
	 * @return the filtertionCapacity
	 *//*
	public Double getFiltertionCapacity() {
		return filtertionCapacity;
	}

	*//**
	 * @param filtertionCapacity the filtertionCapacity to set
	 *//*
	public void setFiltertionCapacity(Double filtertionCapacity) {
		this.filtertionCapacity = filtertionCapacity;
	}

	*//**
	 * @return the rawWaterCaps
	 *//*
	public Double getRawWaterCaps() {
		return rawWaterCaps;
	}

	*//**
	 * @param rawWaterCaps the rawWaterCaps to set
	 *//*
	public void setRawWaterCaps(Double rawWaterCaps) {
		this.rawWaterCaps = rawWaterCaps;
	}

	*//**
	 * @return the clearWaterCaps
	 *//*
	public Double getClearWaterCaps() {
		return clearWaterCaps;
	}

	*//**
	 * @param clearWaterCaps the clearWaterCaps to set
	 *//*
	public void setClearWaterCaps(Double clearWaterCaps) {
		this.clearWaterCaps = clearWaterCaps;
	}

	*//**
	 * @return the ohsrNo
	 *//*
	public Double getOhsrNo() {
		return ohsrNo;
	}

	*//**
	 * @param ohsrNo the ohsrNo to set
	 *//*
	public void setOhsrNo(Double ohsrNo) {
		this.ohsrNo = ohsrNo;
	}

	*//**
	 * @return the ohsrConsDate
	 *//*
	public Date getOhsrConsDate() {
		return ohsrConsDate;
	}

	*//**
	 * @param ohsrConsDate the ohsrConsDate to set
	 *//*
	public void setOhsrConsDate(Date ohsrConsDate) {
		this.ohsrConsDate = ohsrConsDate;
	}

	*//**
	 * @return the ohsrCaps
	 *//*
	public Double getOhsrCaps() {
		return ohsrCaps;
	}

	*//**
	 * @param ohsrCaps the ohsrCaps to set
	 *//*
	public void setOhsrCaps(Double ohsrCaps) {
		this.ohsrCaps = ohsrCaps;
	}

	*//**
	 * @return the ohsrWorkingCondition
	 *//*
	public Double getOhsrWorkingCondition() {
		return ohsrWorkingCondition;
	}

	*//**
	 * @param ohsrWorkingCondition the ohsrWorkingCondition to set
	 *//*
	public void setOhsrWorkingCondition(Double ohsrWorkingCondition) {
		this.ohsrWorkingCondition = ohsrWorkingCondition;
	}

	*//**
	 * @return the ohsrConditionIfNo
	 *//*
	public String getOhsrConditionIfNo() {
		return ohsrConditionIfNo;
	}

	*//**
	 * @param ohsrConditionIfNo the ohsrConditionIfNo to set
	 *//*
	public void setOhsrConditionIfNo(String ohsrConditionIfNo) {
		this.ohsrConditionIfNo = ohsrConditionIfNo;
	}

	*//**
	 * @return the disinfectionType
	 *//*
	public String getDisinfectionType() {
		return disinfectionType;
	}

	*//**
	 * @param disinfectionType the disinfectionType to set
	 *//*
	public void setDisinfectionType(String disinfectionType) {
		this.disinfectionType = disinfectionType;
	}

	*//**
	 * @return the disinfectionInstalTime
	 *//*
	public Date getDisinfectionInstalTime() {
		return disinfectionInstalTime;
	}

	*//**
	 * @param disinfectionInstalTime the disinfectionInstalTime to set
	 *//*
	public void setDisinfectionInstalTime(Date disinfectionInstalTime) {
		this.disinfectionInstalTime = disinfectionInstalTime;
	}

	*//**
	 * @return the disnPresentStatus
	 *//*
	public String getDisnPresentStatus() {
		return disnPresentStatus;
	}

	*//**
	 * @param disnPresentStatus the disnPresentStatus to set
	 *//*
	public void setDisnPresentStatus(String disnPresentStatus) {
		this.disnPresentStatus = disnPresentStatus;
	}

	*//**
	 * @return the schemeOpertedBy
	 *//*
	public String getSchemeOpertedBy() {
		return schemeOpertedBy;
	}

	*//**
	 * @param schemeOpertedBy the schemeOpertedBy to set
	 *//*
	public void setSchemeOpertedBy(String schemeOpertedBy) {
		this.schemeOpertedBy = schemeOpertedBy;
	}

	*//**
	 * @return the sanctionedLoad
	 *//*
	public Double getSanctionedLoad() {
		return sanctionedLoad;
	}

	*//**
	 * @param sanctionedLoad the sanctionedLoad to set
	 *//*
	public void setSanctionedLoad(Double sanctionedLoad) {
		this.sanctionedLoad = sanctionedLoad;
	}

	*//**
	 * @return the pendingBill3103
	 *//*
	public Double getPendingBill3103() {
		return pendingBill3103;
	}

	*//**
	 * @param pendingBill3103 the pendingBill3103 to set
	 *//*
	public void setPendingBill3103(Double pendingBill3103) {
		this.pendingBill3103 = pendingBill3103;
	}

	*//**
	 * @return the pendingBill3006
	 *//*
	public Double getPendingBill3006() {
		return pendingBill3006;
	}

	*//**
	 * @param pendingBill3006 the pendingBill3006 to set
	 *//*
	public void setPendingBill3006(Double pendingBill3006) {
		this.pendingBill3006 = pendingBill3006;
	}

	*//**
	 * @return the avgMonthBill
	 *//*
	public Double getAvgMonthBill() {
		return avgMonthBill;
	}

	*//**
	 * @param avgMonthBill the avgMonthBill to set
	 *//*
	public void setAvgMonthBill(Double avgMonthBill) {
		this.avgMonthBill = avgMonthBill;
	}

	 (non-Javadoc)
	 * @see java.lang.Object#toString()
	 
	@Override
	public String toString() {
		return "SchemeSourcePerformaBean [id=" + id + ", villageId=" + villageId + ", villageName=" + villageName
				+ ", schemeId=" + schemeId + ", schemeSource=" + schemeSource + ", schemeType=" + schemeType
				+ ", commissioningDate=" + commissioningDate + ", serviceLevel=" + serviceLevel + ", schemeUpgraded="
				+ schemeUpgraded + ", schemeExpenditure=" + schemeExpenditure + ", tubewellDepth=" + tubewellDepth
				+ ", progId=" + progId + ", tubewellSize=" + tubewellSize + ", drillingYear=" + drillingYear
				+ ", presentDischarge=" + presentDischarge + ", dischargeComm=" + dischargeComm
				+ ", presentSpringLevel=" + presentSpringLevel + ", machineryInstallation=" + machineryInstallation
				+ ", machineryCapacity=" + machineryCapacity + ", inletChannelType=" + inletChannelType
				+ ", inletChannelLength=" + inletChannelLength + ", pipeType=" + pipeType + ", ssTankCapacity="
				+ ssTankCapacity + ", hlTankCapacity=" + hlTankCapacity + ", cwTankCapacity=" + cwTankCapacity
				+ ", filterationType=" + filterationType + ", filtertionNo=" + filtertionNo + ", filtertionCapacity="
				+ filtertionCapacity + ", rawWaterCaps=" + rawWaterCaps + ", clearWaterCaps=" + clearWaterCaps
				+ ", ohsrNo=" + ohsrNo + ", ohsrConsDate=" + ohsrConsDate + ", ohsrCaps=" + ohsrCaps
				+ ", ohsrWorkingCondition=" + ohsrWorkingCondition + ", ohsrConditionIfNo=" + ohsrConditionIfNo
				+ ", disinfectionType=" + disinfectionType + ", disinfectionInstalTime=" + disinfectionInstalTime
				+ ", disnPresentStatus=" + disnPresentStatus + ", schemeOpertedBy=" + schemeOpertedBy
				+ ", sanctionedLoad=" + sanctionedLoad + ", pendingBill3103=" + pendingBill3103 + ", pendingBill3006="
				+ pendingBill3006 + ", avgMonthBill=" + avgMonthBill + "]";
	}
*/
	
	

}
