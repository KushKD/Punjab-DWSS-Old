package com.prwss.mis.reports.performa;

import org.apache.struts.validator.ValidatorForm;

import com.prwss.mis.reports.performa.dao.FiveYearPlanGridBean;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

public class PerformaMasterForm extends ValidatorForm {
	
	private static final long serialVersionUID = -8446654655918222222L;


	private String locationId;
	private String schemeId;
	private String villageId ;
	private String serialID;
	private String villageName ;	
	private String scheme_id ;
	private String hadBastNo;
	private int noOfHabitation ;
	private String nameOfHabitation ;
	private String typeOfHabitation ;
	private String misCode;
	private String assemblyConstituency ;
	private String poputlationTotal ;
	private String populationSc ;
	private String noOfHouseholdsTotal ;
	private String noOfHouseholdsSc ;
	private String panchayatGhr ;
	private String commCenterDhar ;
	private String angarwaries ;
	private String typeBuilding ;
	private String govtSchools ;
	private String healthCntr ;
	private String villagePonds ;
	private String areaSqrMtr ;
	private String waterScheme ;
	private String sewerageScheme;
	private Datagrid villagePerformaGrid;
	private String schemeSource;
	private String schemeType;
	private String dateOfComm;
	private String progId;
	private String serviceLevel;
	private String schemeUpgraded;
	private String schemeExpenditure;
	private String depthOfTubewell;
	private String sizeOfTubewell;
	private String yearOfDrilling;
	private String dischargeComm;
	private String presentDis;
	private String presentSpringLevel;
	private String installation_of_new_machinery;
	private String capacity_of_machinery;
	private String inletType;
	private String inletLenght;
	private String pipeType;
	private String capacitySSTank;
	private String capacityHLTank;
	private String capacityCWTank;
	private String filtertionType;
	private String filtertionNo;
	private String filtertionCapacity;
	private String capacityRawWater;
	private String capacityClearWater;
	private String noOfOhsr;
	private String oHSR_construction_date;
	private String oHSRCapcity;
	private String oHSRCondition;
	private String oHSRWorkingCondition;
	private String capMachinery;
	private String multipleSource;
	private Datagrid villageSourceGrid;
	private String villId;
	private String depthTubewell;
	private String sizeTubewell;
	private String yearDrill;
	private String disinfectionType;
	private String disinfection_Instalation_time;
	private String disinfectionPrStatus;
	private String schemeOperatedBy;
	private String staffScheme;
	private String sanctionedLoad;
	private String pendingBill3103;
	private String pendingBill3006;
	private String avgMonthBillofWSS;
	private String pipelineAC;
	private String pipelineMSDICi;
	private String pipelineGI;
	private String pipelinePVC;
	private String functionalDistributionPercentage;
	private String householdWaterConnection;
	private String watered_connection;
	private String metered_connection;
	private String metered_supply_recovery;
	private String flat_rate_charges_per_month;
	private String arrear_of_recovery_with_consumers_as01042017;
	private String scheme_Functional;
	private String scheme_NonFunctional;
	private String scheme_NonFunctional_date;
	private String ground_water_potable_status;
	private String ground_water_potable_No;
	private String preventive_measures_adopted;
	private String capacity_of_plant;
	private String date_of_installation;
	private String being_operated_by;
	private String volume_of_Water_Daily_basis;
	private String disposal_of_reject_water;
	private String penetration_in_percentage;
	private String present_rate_of_user_charges;
	private String seperate_Sanctioned_Load;
	private String seperate_Pending_eletric_bill31032017;
	private String average_monthly_bill_of_Treatment_plant;
	private String independent_New_WSS;
	private String upgradation_of_existing_WSS;
	private String instltion_Wtr_Treatment_plant;
	private String source_of_WSS;
	private String shifted_to_canal_from_Other;
	private String Driling_of_new_tubewell_machinery_size;
	private String driling_of_new_tubewell_machinery_depth;
	private String driling_of_new_tubewell_machinery_capacity;
	private String driling_of_new_tubewell_machinery_cost;
	private String canal_based_Inlet_channel_Size_of_pipe;
	private String canal_based_Inlet_channel_length;
	private String canal_based_s_and_s_Capacity;
	private String canal_based_Filteration_Plan_type;
	private String canal_based_Filteration_Plan_capacity;
	private String canal_based_Cost;
	private String oHSR_Capacity;
	private String oHSR_Full_Supply_Level;
	private String oHSR_Cost;
	private String other_structures_at_waterworks;
	private String other_structures_at_waterworks_Cost;
	private String distribution_WSS_to_village_type;
	private String distribution_WSS_to_village_length;
	private String distribution_WSS_within_village_type;
	private String distribution_WSS_within_village_length;
	private String distribution_Cost;
	private String no_of_connections_100_mtr;
	private String no_of_mtrs_100_mtr;
	private String cost_100_mtr;
	private String disinfection_Unit_Type;
	private String water_Treatment_plant_in_case_of_quality_village_type;
	private String water_Treatment_plant_in_case_of_quality_village_capacity;
	private String water_Treatment_plant_in_case_of_quality_village_Cost;
	private String bulk_Water_meter_no;
	private String bulk_Water_meter_cost;
	private String extension_Sanction_of_new_electric_connection_cost;
	private String Block;
	private String disinfection_Unit_Cost;
	private Datagrid fiveYearPlanGridBean;
	private String independent_New_WSS_Cost;
	private String upgradation_of_existing_WSS_Cost;
	private String blockId;
	private String villgId;
	private String villagesId;
	private String blockIds;
	private String schemeName;
	private String instltion_Wtr_Treatment;
	
	private String driling_of_new_tubewell_machinery;
	private String canal_based;
	private String ohsr;
	private String otherStructure;
	private String distrNetwork;
	private String disincUnit;
	private String insWaterPlant;
	private String otherWork;
	private String mtrConnection;
	private String oHSRDismantling;
	private String oHSRFullSuplyLvl;
	private String clear_water_tank;
	private String schemeSrc;
	
	private String disinfecReq;
	
	private String firstSrc;
	private String oHSRCTDia;
	private String oHSRCTDepth;
	private String oHSRUGSRDepth;
	private String oHSRUGSRDia;
	
	
	private String distribution_WSS_within_village_type_pvc;
	private String distribution_WSS_within_village_type_di_ms;
	private String distribution_WSS_within_village_type_gi;
	
	
	private String oHSRCTDiameter;
	private String oHSR_CT_Depth;
	private String oHSR_UGS_RDia;
	private String oHSR_UGSR_Depth;
	
	
	/** 
	 * KD
	 */
	private String dateOfComplition;
	private String remarksdateOfComplition;
	private String underProcessSelection;
	private String any_other_desc;
	
	
	
	public String getUnderProcessSelection() {
		return underProcessSelection;
	}
	public void setUnderProcessSelection(String underProcessSelection) {
		this.underProcessSelection = underProcessSelection;
	}
	public String getDateOfComplition() {
		return dateOfComplition;
	}
	public void setDateOfComplition(String dateOfComplition) {
		this.dateOfComplition = dateOfComplition;
	}
	public String getRemarksdateOfComplition() {
		return remarksdateOfComplition;
	}
	public void setRemarksdateOfComplition(String remarksdateOfComplition) {
		this.remarksdateOfComplition = remarksdateOfComplition;
	}
	
	
	public String getAny_other_desc() {
		return any_other_desc;
	}
	public void setAny_other_desc(String any_other_desc) {
		this.any_other_desc = any_other_desc;
	}
	/** 
	 * KD
	 */
	
	
	
	
	public String getoHSRCTDiameter() {
		return oHSRCTDiameter;
	}
	public void setoHSRCTDiameter(String oHSRCTDiameter) {
		this.oHSRCTDiameter = oHSRCTDiameter;
	}
	public String getoHSR_CT_Depth() {
		return oHSR_CT_Depth;
	}
	public void setoHSR_CT_Depth(String oHSR_CT_Depth) {
		this.oHSR_CT_Depth = oHSR_CT_Depth;
	}
	public String getoHSR_UGS_RDia() {
		return oHSR_UGS_RDia;
	}
	public void setoHSR_UGS_RDia(String oHSR_UGS_RDia) {
		this.oHSR_UGS_RDia = oHSR_UGS_RDia;
	}
	public String getoHSR_UGSR_Depth() {
		return oHSR_UGSR_Depth;
	}
	public void setoHSR_UGSR_Depth(String oHSR_UGSR_Depth) {
		this.oHSR_UGSR_Depth = oHSR_UGSR_Depth;
	}
	/**
	 * @return the distribution_WSS_within_village_type_pvc
	 */
	public String getDistribution_WSS_within_village_type_pvc() {
		return distribution_WSS_within_village_type_pvc;
	}
	/**
	 * @param distribution_WSS_within_village_type_pvc the distribution_WSS_within_village_type_pvc to set
	 */
	public void setDistribution_WSS_within_village_type_pvc(String distribution_WSS_within_village_type_pvc) {
		this.distribution_WSS_within_village_type_pvc = distribution_WSS_within_village_type_pvc;
	}
	/**
	 * @return the distribution_WSS_within_village_type_di_ms
	 */
	public String getDistribution_WSS_within_village_type_di_ms() {
		return distribution_WSS_within_village_type_di_ms;
	}
	/**
	 * @param distribution_WSS_within_village_type_di_ms the distribution_WSS_within_village_type_di_ms to set
	 */
	public void setDistribution_WSS_within_village_type_di_ms(String distribution_WSS_within_village_type_di_ms) {
		this.distribution_WSS_within_village_type_di_ms = distribution_WSS_within_village_type_di_ms;
	}
	/**
	 * @return the distribution_WSS_within_village_type_gi
	 */
	public String getDistribution_WSS_within_village_type_gi() {
		return distribution_WSS_within_village_type_gi;
	}
	/**
	 * @param distribution_WSS_within_village_type_gi the distribution_WSS_within_village_type_gi to set
	 */
	public void setDistribution_WSS_within_village_type_gi(String distribution_WSS_within_village_type_gi) {
		this.distribution_WSS_within_village_type_gi = distribution_WSS_within_village_type_gi;
	}
	
	public String getoHSRCTDia() {
		return oHSRCTDia;
	}
	public void setoHSRCTDia(String oHSRCTDia) {
		this.oHSRCTDia = oHSRCTDia;
	}
	public String getoHSRCTDepth() {
		return oHSRCTDepth;
	}
	public void setoHSRCTDepth(String oHSRCTDepth) {
		this.oHSRCTDepth = oHSRCTDepth;
	}
	public String getoHSRUGSRDepth() {
		return oHSRUGSRDepth;
	}
	public void setoHSRUGSRDepth(String oHSRUGSRDepth) {
		this.oHSRUGSRDepth = oHSRUGSRDepth;
	}
	public String getoHSRUGSRDia() {
		return oHSRUGSRDia;
	}
	public void setoHSRUGSRDia(String oHSRUGSRDia) {
		this.oHSRUGSRDia = oHSRUGSRDia;
	}
	/**
	 * @return the firstSrc
	 */
	public String getFirstSrc() {
		return firstSrc;
	}
	/**
	 * @param firstSrc the firstSrc to set
	 */
	public void setFirstSrc(String firstSrc) {
		this.firstSrc = firstSrc;
	}
	/**
	 * @return the disinfecReq
	 */
	public String getDisinfecReq() {
		return disinfecReq;
	}
	/**
	 * @param disinfecReq the disinfecReq to set
	 */
	public void setDisinfecReq(String disinfecReq) {
		this.disinfecReq = disinfecReq;
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
	 * @return the clear_water_tank
	 */
	public String getClear_water_tank() {
		return clear_water_tank;
	}
	/**
	 * @param clear_water_tank the clear_water_tank to set
	 */
	public void setClear_water_tank(String clear_water_tank) {
		this.clear_water_tank = clear_water_tank;
	}
	/**
	 * @return the oHSRDismantling
	 */
	public String getoHSRDismantling() {
		return oHSRDismantling;
	}
	/**
	 * @param oHSRDismantling the oHSRDismantling to set
	 */
	public void setoHSRDismantling(String oHSRDismantling) {
		this.oHSRDismantling = oHSRDismantling;
	}
	/**
	 * @return the oHSRFullSuplyLvl
	 */
	public String getoHSRFullSuplyLvl() {
		return oHSRFullSuplyLvl;
	}
	/**
	 * @param oHSRFullSuplyLvl the oHSRFullSuplyLvl to set
	 */
	public void setoHSRFullSuplyLvl(String oHSRFullSuplyLvl) {
		this.oHSRFullSuplyLvl = oHSRFullSuplyLvl;
	}
	/**
	 * @return the mtrConnection
	 */
	public String getMtrConnection() {
		return mtrConnection;
	}
	/**
	 * @param mtrConnection the mtrConnection to set
	 */
	public void setMtrConnection(String mtrConnection) {
		this.mtrConnection = mtrConnection;
	}
	/**
	 * @return the driling_of_new_tubewell_machinery
	 */
	public String getDriling_of_new_tubewell_machinery() {
		return driling_of_new_tubewell_machinery;
	}
	/**
	 * @param driling_of_new_tubewell_machinery the driling_of_new_tubewell_machinery to set
	 */
	public void setDriling_of_new_tubewell_machinery(String driling_of_new_tubewell_machinery) {
		this.driling_of_new_tubewell_machinery = driling_of_new_tubewell_machinery;
	}
	/**
	 * @return the canal_based
	 */
	public String getCanal_based() {
		return canal_based;
	}
	/**
	 * @param canal_based the canal_based to set
	 */
	public void setCanal_based(String canal_based) {
		this.canal_based = canal_based;
	}
	/**
	 * @return the ohsr
	 */
	public String getOhsr() {
		return ohsr;
	}
	/**
	 * @param ohsr the ohsr to set
	 */
	public void setOhsr(String ohsr) {
		this.ohsr = ohsr;
	}
	/**
	 * @return the otherStructure
	 */
	public String getOtherStructure() {
		return otherStructure;
	}
	/**
	 * @param otherStructure the otherStructure to set
	 */
	public void setOtherStructure(String otherStructure) {
		this.otherStructure = otherStructure;
	}
	/**
	 * @return the distrNetwork
	 */
	public String getDistrNetwork() {
		return distrNetwork;
	}
	/**
	 * @param distrNetwork the distrNetwork to set
	 */
	public void setDistrNetwork(String distrNetwork) {
		this.distrNetwork = distrNetwork;
	}
	/**
	 * @return the disincUnit
	 */
	public String getDisincUnit() {
		return disincUnit;
	}
	/**
	 * @param disincUnit the disincUnit to set
	 */
	public void setDisincUnit(String disincUnit) {
		this.disincUnit = disincUnit;
	}
	/**
	 * @return the insWaterPlant
	 */
	public String getInsWaterPlant() {
		return insWaterPlant;
	}
	/**
	 * @param insWaterPlant the insWaterPlant to set
	 */
	public void setInsWaterPlant(String insWaterPlant) {
		this.insWaterPlant = insWaterPlant;
	}
	/**
	 * @return the otherWork
	 */
	public String getOtherWork() {
		return otherWork;
	}
	/**
	 * @param otherWork the otherWork to set
	 */
	public void setOtherWork(String otherWork) {
		this.otherWork = otherWork;
	}
	/**
	 * @return the instltion_Wtr_Treatment
	 */
	public String getInstltion_Wtr_Treatment() {
		return instltion_Wtr_Treatment;
	}
	/**
	 * @param instltion_Wtr_Treatment the instltion_Wtr_Treatment to set
	 */
	public void setInstltion_Wtr_Treatment(String instltion_Wtr_Treatment) {
		this.instltion_Wtr_Treatment = instltion_Wtr_Treatment;
	}
	/**
	 * @return the schemeName
	 */
	public String getSchemeName() {
		return schemeName;
	}
	/**
	 * @param schemeName the schemeName to set
	 */
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	/**
	 * @return the serialID
	 */
	public String getSerialID() {
		return serialID;
	}
	/**
	 * @param serialID the serialID to set
	 */
	public void setSerialID(String serialID) {
		this.serialID = serialID;
	}
	/**
	 * @return the blockIds
	 */
	public String getBlockIds() {
		return blockIds;
	}
	/**
	 * @param blockIds the blockIds to set
	 */
	public void setBlockIds(String blockIds) {
		this.blockIds = blockIds;
	}
	/**
	 * @return the villagesId
	 */
	public String getVillagesId() {
		return villagesId;
	}
	/**
	 * @param villagesId the villagesId to set
	 */
	public void setVillagesId(String villagesId) {
		this.villagesId = villagesId;
	}
	/**
	 * @return the blockId
	 */
	public String getBlockId() {
		return blockId;
	}
	/**
	 * @param blockId the blockId to set
	 */
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}
	/**
	 * @return the villgId
	 */
	public String getVillgId() {
		return villgId;
	}
	/**
	 * @param villgId the villgId to set
	 */
	public void setVillgId(String villgId) {
		this.villgId = villgId;
	}
	/**
	 * @return the upgradation_of_existing_WSS_Cost
	 */
	public String getUpgradation_of_existing_WSS_Cost() {
		return upgradation_of_existing_WSS_Cost;
	}
	/**
	 * @param upgradation_of_existing_WSS_Cost the upgradation_of_existing_WSS_Cost to set
	 */
	public void setUpgradation_of_existing_WSS_Cost(String upgradation_of_existing_WSS_Cost) {
		this.upgradation_of_existing_WSS_Cost = upgradation_of_existing_WSS_Cost;
	}
	/**
	 * @return the independent_New_WSS_Cost
	 */
	public String getIndependent_New_WSS_Cost() {
		return independent_New_WSS_Cost;
	}
	/**
	 * @param independent_New_WSS_Cost the independent_New_WSS_Cost to set
	 */
	public void setIndependent_New_WSS_Cost(String independent_New_WSS_Cost) {
		this.independent_New_WSS_Cost = independent_New_WSS_Cost;
	}
	/**
	 * @return the fiveYearPlanGridBean
	 */
	public Datagrid getFiveYearPlanGridBean() {
		return fiveYearPlanGridBean;
	}
	/**
	 * @param fiveYearPlanGridBean the fiveYearPlanGridBean to set
	 */
	public void setFiveYearPlanGridBean(Datagrid fiveYearPlanGridBean) {
		this.fiveYearPlanGridBean = fiveYearPlanGridBean;
	}
	/**
	 * @return the disinfection_Unit_Cost
	 */
	public String getDisinfection_Unit_Cost() {
		return disinfection_Unit_Cost;
	}
	/**
	 * @param disinfection_Unit_Cost the disinfection_Unit_Cost to set
	 */
	public void setDisinfection_Unit_Cost(String disinfection_Unit_Cost) {
		this.disinfection_Unit_Cost = disinfection_Unit_Cost;
	}
	/**
	 * @return the block
	 */
	public String getBlock() {
		return Block;
	}
	/**
	 * @param block the block to set
	 */
	public void setBlock(String block) {
		Block = block;
	}
	/**
	 * @return the pipelineAC
	 */
	public String getPipelineAC() {
		return pipelineAC;
	}
	/**
	 * @param pipelineAC the pipelineAC to set
	 */
	public void setPipelineAC(String pipelineAC) {
		this.pipelineAC = pipelineAC;
	}
	/**
	 * @return the pipelineMSDICi
	 */
	public String getPipelineMSDICi() {
		return pipelineMSDICi;
	}
	/**
	 * @param pipelineMSDICi the pipelineMSDICi to set
	 */
	public void setPipelineMSDICi(String pipelineMSDICi) {
		this.pipelineMSDICi = pipelineMSDICi;
	}
	/**
	 * @return the pipelineGI
	 */
	public String getPipelineGI() {
		return pipelineGI;
	}
	/**
	 * @param pipelineGI the pipelineGI to set
	 */
	public void setPipelineGI(String pipelineGI) {
		this.pipelineGI = pipelineGI;
	}
	/**
	 * @return the pipelinePVC
	 */
	public String getPipelinePVC() {
		return pipelinePVC;
	}
	/**
	 * @param pipelinePVC the pipelinePVC to set
	 */
	public void setPipelinePVC(String pipelinePVC) {
		this.pipelinePVC = pipelinePVC;
	}
	/**
	 * @return the functionalDistributionPercentage
	 */
	public String getFunctionalDistributionPercentage() {
		return functionalDistributionPercentage;
	}
	/**
	 * @param functionalDistributionPercentage the functionalDistributionPercentage to set
	 */
	public void setFunctionalDistributionPercentage(String functionalDistributionPercentage) {
		this.functionalDistributionPercentage = functionalDistributionPercentage;
	}
	/**
	 * @return the householdWaterConnection
	 */
	public String getHouseholdWaterConnection() {
		return householdWaterConnection;
	}
	/**
	 * @param householdWaterConnection the householdWaterConnection to set
	 */
	public void setHouseholdWaterConnection(String householdWaterConnection) {
		this.householdWaterConnection = householdWaterConnection;
	}
	/**
	 * @return the watered_connection
	 */
	public String getWatered_connection() {
		return watered_connection;
	}
	/**
	 * @param watered_connection the watered_connection to set
	 */
	public void setWatered_connection(String watered_connection) {
		this.watered_connection = watered_connection;
	}
	/**
	 * @return the metered_connection
	 */
	public String getMetered_connection() {
		return metered_connection;
	}
	/**
	 * @param metered_connection the metered_connection to set
	 */
	public void setMetered_connection(String metered_connection) {
		this.metered_connection = metered_connection;
	}
	/**
	 * @return the metered_supply_recovery
	 */
	public String getMetered_supply_recovery() {
		return metered_supply_recovery;
	}
	/**
	 * @param metered_supply_recovery the metered_supply_recovery to set
	 */
	public void setMetered_supply_recovery(String metered_supply_recovery) {
		this.metered_supply_recovery = metered_supply_recovery;
	}
	/**
	 * @return the flat_rate_charges_per_month
	 */
	public String getFlat_rate_charges_per_month() {
		return flat_rate_charges_per_month;
	}
	/**
	 * @param flat_rate_charges_per_month the flat_rate_charges_per_month to set
	 */
	public void setFlat_rate_charges_per_month(String flat_rate_charges_per_month) {
		this.flat_rate_charges_per_month = flat_rate_charges_per_month;
	}
	/**
	 * @return the arrear_of_recovery_with_consumers_as01042017
	 */
	public String getArrear_of_recovery_with_consumers_as01042017() {
		return arrear_of_recovery_with_consumers_as01042017;
	}
	/**
	 * @param arrear_of_recovery_with_consumers_as01042017 the arrear_of_recovery_with_consumers_as01042017 to set
	 */
	public void setArrear_of_recovery_with_consumers_as01042017(String arrear_of_recovery_with_consumers_as01042017) {
		this.arrear_of_recovery_with_consumers_as01042017 = arrear_of_recovery_with_consumers_as01042017;
	}
	/**
	 * @return the scheme_Functional
	 */
	public String getScheme_Functional() {
		return scheme_Functional;
	}
	/**
	 * @param scheme_Functional the scheme_Functional to set
	 */
	public void setScheme_Functional(String scheme_Functional) {
		this.scheme_Functional = scheme_Functional;
	}
	/**
	 * @return the scheme_NonFunctional
	 */
	public String getScheme_NonFunctional() {
		return scheme_NonFunctional;
	}
	/**
	 * @param scheme_NonFunctional the scheme_NonFunctional to set
	 */
	public void setScheme_NonFunctional(String scheme_NonFunctional) {
		this.scheme_NonFunctional = scheme_NonFunctional;
	}
	/**
	 * @return the scheme_NonFunctional_date
	 */
	public String getScheme_NonFunctional_date() {
		return scheme_NonFunctional_date;
	}
	/**
	 * @param scheme_NonFunctional_date the scheme_NonFunctional_date to set
	 */
	public void setScheme_NonFunctional_date(String scheme_NonFunctional_date) {
		this.scheme_NonFunctional_date = scheme_NonFunctional_date;
	}
	/**
	 * @return the ground_water_potable_status
	 */
	public String getGround_water_potable_status() {
		return ground_water_potable_status;
	}
	/**
	 * @param ground_water_potable_status the ground_water_potable_status to set
	 */
	public void setGround_water_potable_status(String ground_water_potable_status) {
		this.ground_water_potable_status = ground_water_potable_status;
	}
	/**
	 * @return the ground_water_potable_No
	 */
	public String getGround_water_potable_No() {
		return ground_water_potable_No;
	}
	/**
	 * @param ground_water_potable_No the ground_water_potable_No to set
	 */
	public void setGround_water_potable_No(String ground_water_potable_No) {
		this.ground_water_potable_No = ground_water_potable_No;
	}
	/**
	 * @return the preventive_measures_adopted
	 */
	public String getPreventive_measures_adopted() {
		return preventive_measures_adopted;
	}
	/**
	 * @param preventive_measures_adopted the preventive_measures_adopted to set
	 */
	public void setPreventive_measures_adopted(String preventive_measures_adopted) {
		this.preventive_measures_adopted = preventive_measures_adopted;
	}
	/**
	 * @return the capacity_of_plant
	 */
	public String getCapacity_of_plant() {
		return capacity_of_plant;
	}
	/**
	 * @param capacity_of_plant the capacity_of_plant to set
	 */
	public void setCapacity_of_plant(String capacity_of_plant) {
		this.capacity_of_plant = capacity_of_plant;
	}
	/**
	 * @return the date_of_installation
	 */
	public String getDate_of_installation() {
		return date_of_installation;
	}
	/**
	 * @param date_of_installation the date_of_installation to set
	 */
	public void setDate_of_installation(String date_of_installation) {
		this.date_of_installation = date_of_installation;
	}
	/**
	 * @return the being_operated_by
	 */
	public String getBeing_operated_by() {
		return being_operated_by;
	}
	/**
	 * @param being_operated_by the being_operated_by to set
	 */
	public void setBeing_operated_by(String being_operated_by) {
		this.being_operated_by = being_operated_by;
	}
	/**
	 * @return the volume_of_Water_Daily_basis
	 */
	public String getVolume_of_Water_Daily_basis() {
		return volume_of_Water_Daily_basis;
	}
	/**
	 * @param volume_of_Water_Daily_basis the volume_of_Water_Daily_basis to set
	 */
	public void setVolume_of_Water_Daily_basis(String volume_of_Water_Daily_basis) {
		this.volume_of_Water_Daily_basis = volume_of_Water_Daily_basis;
	}
	/**
	 * @return the disposal_of_reject_water
	 */
	public String getDisposal_of_reject_water() {
		return disposal_of_reject_water;
	}
	/**
	 * @param disposal_of_reject_water the disposal_of_reject_water to set
	 */
	public void setDisposal_of_reject_water(String disposal_of_reject_water) {
		this.disposal_of_reject_water = disposal_of_reject_water;
	}
	/**
	 * @return the penetration_in_percentage
	 */
	public String getPenetration_in_percentage() {
		return penetration_in_percentage;
	}
	/**
	 * @param penetration_in_percentage the penetration_in_percentage to set
	 */
	public void setPenetration_in_percentage(String penetration_in_percentage) {
		this.penetration_in_percentage = penetration_in_percentage;
	}
	/**
	 * @return the present_rate_of_user_charges
	 */
	public String getPresent_rate_of_user_charges() {
		return present_rate_of_user_charges;
	}
	/**
	 * @param present_rate_of_user_charges the present_rate_of_user_charges to set
	 */
	public void setPresent_rate_of_user_charges(String present_rate_of_user_charges) {
		this.present_rate_of_user_charges = present_rate_of_user_charges;
	}
	/**
	 * @return the seperate_Sanctioned_Load
	 */
	public String getSeperate_Sanctioned_Load() {
		return seperate_Sanctioned_Load;
	}
	/**
	 * @param seperate_Sanctioned_Load the seperate_Sanctioned_Load to set
	 */
	public void setSeperate_Sanctioned_Load(String seperate_Sanctioned_Load) {
		this.seperate_Sanctioned_Load = seperate_Sanctioned_Load;
	}
	/**
	 * @return the seperate_Pending_eletric_bill31032017
	 */
	public String getSeperate_Pending_eletric_bill31032017() {
		return seperate_Pending_eletric_bill31032017;
	}
	/**
	 * @param seperate_Pending_eletric_bill31032017 the seperate_Pending_eletric_bill31032017 to set
	 */
	public void setSeperate_Pending_eletric_bill31032017(String seperate_Pending_eletric_bill31032017) {
		this.seperate_Pending_eletric_bill31032017 = seperate_Pending_eletric_bill31032017;
	}
	/**
	 * @return the average_monthly_bill_of_Treatment_plant
	 */
	public String getAverage_monthly_bill_of_Treatment_plant() {
		return average_monthly_bill_of_Treatment_plant;
	}
	/**
	 * @param average_monthly_bill_of_Treatment_plant the average_monthly_bill_of_Treatment_plant to set
	 */
	public void setAverage_monthly_bill_of_Treatment_plant(String average_monthly_bill_of_Treatment_plant) {
		this.average_monthly_bill_of_Treatment_plant = average_monthly_bill_of_Treatment_plant;
	}
	/**
	 * @return the independent_New_WSS
	 */
	public String getIndependent_New_WSS() {
		return independent_New_WSS;
	}
	/**
	 * @param independent_New_WSS the independent_New_WSS to set
	 */
	public void setIndependent_New_WSS(String independent_New_WSS) {
		this.independent_New_WSS = independent_New_WSS;
	}
	/**
	 * @return the upgradation_of_existing_WSS
	 */
	public String getUpgradation_of_existing_WSS() {
		return upgradation_of_existing_WSS;
	}
	/**
	 * @param upgradation_of_existing_WSS the upgradation_of_existing_WSS to set
	 */
	public void setUpgradation_of_existing_WSS(String upgradation_of_existing_WSS) {
		this.upgradation_of_existing_WSS = upgradation_of_existing_WSS;
	}
	/**
	 * @return the instltion_Wtr_Treatment_plant
	 */
	public String getInstltion_Wtr_Treatment_plant() {
		return instltion_Wtr_Treatment_plant;
	}
	/**
	 * @param instltion_Wtr_Treatment_plant the instltion_Wtr_Treatment_plant to set
	 */
	public void setInstltion_Wtr_Treatment_plant(String instltion_Wtr_Treatment_plant) {
		this.instltion_Wtr_Treatment_plant = instltion_Wtr_Treatment_plant;
	}
	/**
	 * @return the source_of_WSS
	 */
	public String getSource_of_WSS() {
		return source_of_WSS;
	}
	/**
	 * @param source_of_WSS the source_of_WSS to set
	 */
	public void setSource_of_WSS(String source_of_WSS) {
		this.source_of_WSS = source_of_WSS;
	}
	/**
	 * @return the shifted_to_canal_from_Other
	 */
	public String getShifted_to_canal_from_Other() {
		return shifted_to_canal_from_Other;
	}
	/**
	 * @param shifted_to_canal_from_Other the shifted_to_canal_from_Other to set
	 */
	public void setShifted_to_canal_from_Other(String shifted_to_canal_from_Other) {
		this.shifted_to_canal_from_Other = shifted_to_canal_from_Other;
	}
	/**
	 * @return the driling_of_new_tubewell_machinery_size
	 */
	public String getDriling_of_new_tubewell_machinery_size() {
		return Driling_of_new_tubewell_machinery_size;
	}
	/**
	 * @param driling_of_new_tubewell_machinery_size the driling_of_new_tubewell_machinery_size to set
	 */
	public void setDriling_of_new_tubewell_machinery_size(String driling_of_new_tubewell_machinery_size) {
		Driling_of_new_tubewell_machinery_size = driling_of_new_tubewell_machinery_size;
	}
	/**
	 * @return the driling_of_new_tubewell_machinery_depth
	 */
	public String getDriling_of_new_tubewell_machinery_depth() {
		return driling_of_new_tubewell_machinery_depth;
	}
	/**
	 * @param driling_of_new_tubewell_machinery_depth the driling_of_new_tubewell_machinery_depth to set
	 */
	public void setDriling_of_new_tubewell_machinery_depth(String driling_of_new_tubewell_machinery_depth) {
		this.driling_of_new_tubewell_machinery_depth = driling_of_new_tubewell_machinery_depth;
	}
	/**
	 * @return the driling_of_new_tubewell_machinery_capacity
	 */
	public String getDriling_of_new_tubewell_machinery_capacity() {
		return driling_of_new_tubewell_machinery_capacity;
	}
	/**
	 * @param driling_of_new_tubewell_machinery_capacity the driling_of_new_tubewell_machinery_capacity to set
	 */
	public void setDriling_of_new_tubewell_machinery_capacity(String driling_of_new_tubewell_machinery_capacity) {
		this.driling_of_new_tubewell_machinery_capacity = driling_of_new_tubewell_machinery_capacity;
	}
	/**
	 * @return the driling_of_new_tubewell_machinery_cost
	 */
	public String getDriling_of_new_tubewell_machinery_cost() {
		return driling_of_new_tubewell_machinery_cost;
	}
	/**
	 * @param driling_of_new_tubewell_machinery_cost the driling_of_new_tubewell_machinery_cost to set
	 */
	public void setDriling_of_new_tubewell_machinery_cost(String driling_of_new_tubewell_machinery_cost) {
		this.driling_of_new_tubewell_machinery_cost = driling_of_new_tubewell_machinery_cost;
	}
	/**
	 * @return the canal_based_Inlet_channel_Size_of_pipe
	 */
	public String getCanal_based_Inlet_channel_Size_of_pipe() {
		return canal_based_Inlet_channel_Size_of_pipe;
	}
	/**
	 * @param canal_based_Inlet_channel_Size_of_pipe the canal_based_Inlet_channel_Size_of_pipe to set
	 */
	public void setCanal_based_Inlet_channel_Size_of_pipe(String canal_based_Inlet_channel_Size_of_pipe) {
		this.canal_based_Inlet_channel_Size_of_pipe = canal_based_Inlet_channel_Size_of_pipe;
	}
	/**
	 * @return the canal_based_Inlet_channel_length
	 */
	public String getCanal_based_Inlet_channel_length() {
		return canal_based_Inlet_channel_length;
	}
	/**
	 * @param canal_based_Inlet_channel_length the canal_based_Inlet_channel_length to set
	 */
	public void setCanal_based_Inlet_channel_length(String canal_based_Inlet_channel_length) {
		this.canal_based_Inlet_channel_length = canal_based_Inlet_channel_length;
	}
	/**
	 * @return the canal_based_s_and_s_Capacity
	 */
	public String getCanal_based_s_and_s_Capacity() {
		return canal_based_s_and_s_Capacity;
	}
	/**
	 * @param canal_based_s_and_s_Capacity the canal_based_s_and_s_Capacity to set
	 */
	public void setCanal_based_s_and_s_Capacity(String canal_based_s_and_s_Capacity) {
		this.canal_based_s_and_s_Capacity = canal_based_s_and_s_Capacity;
	}
	/**
	 * @return the canal_based_Filteration_Plan_type
	 */
	public String getCanal_based_Filteration_Plan_type() {
		return canal_based_Filteration_Plan_type;
	}
	/**
	 * @param canal_based_Filteration_Plan_type the canal_based_Filteration_Plan_type to set
	 */
	public void setCanal_based_Filteration_Plan_type(String canal_based_Filteration_Plan_type) {
		this.canal_based_Filteration_Plan_type = canal_based_Filteration_Plan_type;
	}
	/**
	 * @return the canal_based_Filteration_Plan_capacity
	 */
	public String getCanal_based_Filteration_Plan_capacity() {
		return canal_based_Filteration_Plan_capacity;
	}
	/**
	 * @param canal_based_Filteration_Plan_capacity the canal_based_Filteration_Plan_capacity to set
	 */
	public void setCanal_based_Filteration_Plan_capacity(String canal_based_Filteration_Plan_capacity) {
		this.canal_based_Filteration_Plan_capacity = canal_based_Filteration_Plan_capacity;
	}
	/**
	 * @return the canal_based_Cost
	 */
	public String getCanal_based_Cost() {
		return canal_based_Cost;
	}
	/**
	 * @param canal_based_Cost the canal_based_Cost to set
	 */
	public void setCanal_based_Cost(String canal_based_Cost) {
		this.canal_based_Cost = canal_based_Cost;
	}
	/**
	 * @return the oHSR_Capacity
	 */
	public String getoHSR_Capacity() {
		return oHSR_Capacity;
	}
	/**
	 * @param oHSR_Capacity the oHSR_Capacity to set
	 */
	public void setoHSR_Capacity(String oHSR_Capacity) {
		this.oHSR_Capacity = oHSR_Capacity;
	}
	/**
	 * @return the oHSR_Full_Supply_Level
	 */
	public String getoHSR_Full_Supply_Level() {
		return oHSR_Full_Supply_Level;
	}
	/**
	 * @param oHSR_Full_Supply_Level the oHSR_Full_Supply_Level to set
	 */
	public void setoHSR_Full_Supply_Level(String oHSR_Full_Supply_Level) {
		this.oHSR_Full_Supply_Level = oHSR_Full_Supply_Level;
	}
	/**
	 * @return the oHSR_Cost
	 */
	public String getoHSR_Cost() {
		return oHSR_Cost;
	}
	/**
	 * @param oHSR_Cost the oHSR_Cost to set
	 */
	public void setoHSR_Cost(String oHSR_Cost) {
		this.oHSR_Cost = oHSR_Cost;
	}
	/**
	 * @return the other_structures_at_waterworks
	 */
	public String getOther_structures_at_waterworks() {
		return other_structures_at_waterworks;
	}
	/**
	 * @param other_structures_at_waterworks the other_structures_at_waterworks to set
	 */
	public void setOther_structures_at_waterworks(String other_structures_at_waterworks) {
		this.other_structures_at_waterworks = other_structures_at_waterworks;
	}
	/**
	 * @return the other_structures_at_waterworks_Cost
	 */
	public String getOther_structures_at_waterworks_Cost() {
		return other_structures_at_waterworks_Cost;
	}
	/**
	 * @param other_structures_at_waterworks_Cost the other_structures_at_waterworks_Cost to set
	 */
	public void setOther_structures_at_waterworks_Cost(String other_structures_at_waterworks_Cost) {
		this.other_structures_at_waterworks_Cost = other_structures_at_waterworks_Cost;
	}
	/**
	 * @return the distribution_WSS_to_village_type
	 */
	public String getDistribution_WSS_to_village_type() {
		return distribution_WSS_to_village_type;
	}
	/**
	 * @param distribution_WSS_to_village_type the distribution_WSS_to_village_type to set
	 */
	public void setDistribution_WSS_to_village_type(String distribution_WSS_to_village_type) {
		this.distribution_WSS_to_village_type = distribution_WSS_to_village_type;
	}
	/**
	 * @return the distribution_WSS_to_village_length
	 */
	public String getDistribution_WSS_to_village_length() {
		return distribution_WSS_to_village_length;
	}
	/**
	 * @param distribution_WSS_to_village_length the distribution_WSS_to_village_length to set
	 */
	public void setDistribution_WSS_to_village_length(String distribution_WSS_to_village_length) {
		this.distribution_WSS_to_village_length = distribution_WSS_to_village_length;
	}
	/**
	 * @return the distribution_WSS_within_village_type
	 */
	public String getDistribution_WSS_within_village_type() {
		return distribution_WSS_within_village_type;
	}
	/**
	 * @param distribution_WSS_within_village_type the distribution_WSS_within_village_type to set
	 */
	public void setDistribution_WSS_within_village_type(String distribution_WSS_within_village_type) {
		this.distribution_WSS_within_village_type = distribution_WSS_within_village_type;
	}
	/**
	 * @return the distribution_WSS_within_village_length
	 */
	public String getDistribution_WSS_within_village_length() {
		return distribution_WSS_within_village_length;
	}
	/**
	 * @param distribution_WSS_within_village_length the distribution_WSS_within_village_length to set
	 */
	public void setDistribution_WSS_within_village_length(String distribution_WSS_within_village_length) {
		this.distribution_WSS_within_village_length = distribution_WSS_within_village_length;
	}
	/**
	 * @return the distribution_Cost
	 */
	public String getDistribution_Cost() {
		return distribution_Cost;
	}
	/**
	 * @param distribution_Cost the distribution_Cost to set
	 */
	public void setDistribution_Cost(String distribution_Cost) {
		this.distribution_Cost = distribution_Cost;
	}
	/**
	 * @return the no_of_connections_100_mtr
	 */
	public String getNo_of_connections_100_mtr() {
		return no_of_connections_100_mtr;
	}
	/**
	 * @param no_of_connections_100_mtr the no_of_connections_100_mtr to set
	 */
	public void setNo_of_connections_100_mtr(String no_of_connections_100_mtr) {
		this.no_of_connections_100_mtr = no_of_connections_100_mtr;
	}
	/**
	 * @return the no_of_mtrs_100_mtr
	 */
	public String getNo_of_mtrs_100_mtr() {
		return no_of_mtrs_100_mtr;
	}
	/**
	 * @param no_of_mtrs_100_mtr the no_of_mtrs_100_mtr to set
	 */
	public void setNo_of_mtrs_100_mtr(String no_of_mtrs_100_mtr) {
		this.no_of_mtrs_100_mtr = no_of_mtrs_100_mtr;
	}
	/**
	 * @return the cost_100_mtr
	 */
	public String getCost_100_mtr() {
		return cost_100_mtr;
	}
	/**
	 * @param cost_100_mtr the cost_100_mtr to set
	 */
	public void setCost_100_mtr(String cost_100_mtr) {
		this.cost_100_mtr = cost_100_mtr;
	}
	/**
	 * @return the disinfection_Unit_Type
	 */
	public String getDisinfection_Unit_Type() {
		return disinfection_Unit_Type;
	}
	/**
	 * @param disinfection_Unit_Type the disinfection_Unit_Type to set
	 */
	public void setDisinfection_Unit_Type(String disinfection_Unit_Type) {
		this.disinfection_Unit_Type = disinfection_Unit_Type;
	}
	/**
	 * @return the water_Treatment_plant_in_case_of_quality_village_type
	 */
	public String getWater_Treatment_plant_in_case_of_quality_village_type() {
		return water_Treatment_plant_in_case_of_quality_village_type;
	}
	/**
	 * @param water_Treatment_plant_in_case_of_quality_village_type the water_Treatment_plant_in_case_of_quality_village_type to set
	 */
	public void setWater_Treatment_plant_in_case_of_quality_village_type(
			String water_Treatment_plant_in_case_of_quality_village_type) {
		this.water_Treatment_plant_in_case_of_quality_village_type = water_Treatment_plant_in_case_of_quality_village_type;
	}
	/**
	 * @return the water_Treatment_plant_in_case_of_quality_village_capacity
	 */
	public String getWater_Treatment_plant_in_case_of_quality_village_capacity() {
		return water_Treatment_plant_in_case_of_quality_village_capacity;
	}
	/**
	 * @param water_Treatment_plant_in_case_of_quality_village_capacity the water_Treatment_plant_in_case_of_quality_village_capacity to set
	 */
	public void setWater_Treatment_plant_in_case_of_quality_village_capacity(
			String water_Treatment_plant_in_case_of_quality_village_capacity) {
		this.water_Treatment_plant_in_case_of_quality_village_capacity = water_Treatment_plant_in_case_of_quality_village_capacity;
	}
	/**
	 * @return the water_Treatment_plant_in_case_of_quality_village_Cost
	 */
	public String getWater_Treatment_plant_in_case_of_quality_village_Cost() {
		return water_Treatment_plant_in_case_of_quality_village_Cost;
	}
	/**
	 * @param water_Treatment_plant_in_case_of_quality_village_Cost the water_Treatment_plant_in_case_of_quality_village_Cost to set
	 */
	public void setWater_Treatment_plant_in_case_of_quality_village_Cost(
			String water_Treatment_plant_in_case_of_quality_village_Cost) {
		this.water_Treatment_plant_in_case_of_quality_village_Cost = water_Treatment_plant_in_case_of_quality_village_Cost;
	}
	/**
	 * @return the bulk_Water_meter_no
	 */
	public String getBulk_Water_meter_no() {
		return bulk_Water_meter_no;
	}
	/**
	 * @param bulk_Water_meter_no the bulk_Water_meter_no to set
	 */
	public void setBulk_Water_meter_no(String bulk_Water_meter_no) {
		this.bulk_Water_meter_no = bulk_Water_meter_no;
	}
	/**
	 * @return the bulk_Water_meter_cost
	 */
	public String getBulk_Water_meter_cost() {
		return bulk_Water_meter_cost;
	}
	/**
	 * @param bulk_Water_meter_cost the bulk_Water_meter_cost to set
	 */
	public void setBulk_Water_meter_cost(String bulk_Water_meter_cost) {
		this.bulk_Water_meter_cost = bulk_Water_meter_cost;
	}
	/**
	 * @return the extension_Sanction_of_new_electric_connection_cost
	 */
	public String getExtension_Sanction_of_new_electric_connection_cost() {
		return extension_Sanction_of_new_electric_connection_cost;
	}
	/**
	 * @param extension_Sanction_of_new_electric_connection_cost the extension_Sanction_of_new_electric_connection_cost to set
	 */
	public void setExtension_Sanction_of_new_electric_connection_cost(
			String extension_Sanction_of_new_electric_connection_cost) {
		this.extension_Sanction_of_new_electric_connection_cost = extension_Sanction_of_new_electric_connection_cost;
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
	 * @return the disinfection_Instalation_time
	 */
	public String getDisinfection_Instalation_time() {
		return disinfection_Instalation_time;
	}
	/**
	 * @param disinfection_Instalation_time the disinfection_Instalation_time to set
	 */
	public void setDisinfection_Instalation_time(String disinfection_Instalation_time) {
		this.disinfection_Instalation_time = disinfection_Instalation_time;
	}
	/**
	 * @return the disinfectionPrStatus
	 */
	public String getDisinfectionPrStatus() {
		return disinfectionPrStatus;
	}
	/**
	 * @param disinfectionPrStatus the disinfectionPrStatus to set
	 */
	public void setDisinfectionPrStatus(String disinfectionPrStatus) {
		this.disinfectionPrStatus = disinfectionPrStatus;
	}
	/**
	 * @return the schemeOperatedBy
	 */
	public String getSchemeOperatedBy() {
		return schemeOperatedBy;
	}
	/**
	 * @param schemeOperatedBy the schemeOperatedBy to set
	 */
	public void setSchemeOperatedBy(String schemeOperatedBy) {
		this.schemeOperatedBy = schemeOperatedBy;
	}
	/**
	 * @return the staffScheme
	 */
	public String getStaffScheme() {
		return staffScheme;
	}
	/**
	 * @param staffScheme the staffScheme to set
	 */
	public void setStaffScheme(String staffScheme) {
		this.staffScheme = staffScheme;
	}
	/**
	 * @return the sanctionedLoad
	 */
	public String getSanctionedLoad() {
		return sanctionedLoad;
	}
	/**
	 * @param sanctionedLoad the sanctionedLoad to set
	 */
	public void setSanctionedLoad(String sanctionedLoad) {
		this.sanctionedLoad = sanctionedLoad;
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
	 * @return the avgMonthBillofWSS
	 */
	public String getAvgMonthBillofWSS() {
		return avgMonthBillofWSS;
	}
	/**
	 * @param avgMonthBillofWSS the avgMonthBillofWSS to set
	 */
	public void setAvgMonthBillofWSS(String avgMonthBillofWSS) {
		this.avgMonthBillofWSS = avgMonthBillofWSS;
	}
	//private List<PerformaMasterBean> performaMasterBean = null;
	/**
	 * @return the villageSourceGrid
	 */
	public Datagrid getVillageSourceGrid() {
		return villageSourceGrid;
	}
	/**
	 * @return the villId
	 */
	public String getVillId() {
		return villId;
	}
	/**
	 * @param villId the villId to set
	 */
	public void setVillId(String villId) {
		this.villId = villId;
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
	 * @return the yearDrill
	 */
	public String getYearDrill() {
		return yearDrill;
	}
	/**
	 * @param yearDrill the yearDrill to set
	 */
	public void setYearDrill(String yearDrill) {
		this.yearDrill = yearDrill;
	}
	/**
	 * @param villageSourceGrid the villageSourceGrid to set
	 */
	public void setVillageSourceGrid(Datagrid villageSourceGrid) {
		this.villageSourceGrid = villageSourceGrid;
	}
	/**
	 * @return the performaMasterBean
	 */
/*	public List<PerformaMasterBean> getPerformaMasterBean() {
		return performaMasterBean;
	}
	*//**
	 * @param performaMasterBean the performaMasterBean to set
	 *//*
	public void setPerformaMasterBean(List<PerformaMasterBean> performaMasterBean) {
		this.performaMasterBean = performaMasterBean;
	}*/
	/**
	 * @return the sewerageScheme
	 */
	public String getSewerageScheme() {
		return sewerageScheme;
	}
	/**
	 * @return the multipleSource
	 */
	public String getMultipleSource() {
		return multipleSource;
	}
	/**
	 * @param multipleSource the multipleSource to set
	 */
	public void setMultipleSource(String multipleSource) {
		this.multipleSource = multipleSource;
	}
	/**
	 * @param sewerageScheme the sewerageScheme to set
	 */
	public void setSewerageScheme(String sewerageScheme) {
		this.sewerageScheme = sewerageScheme;
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
	 * @return the capacity_of_machinery
	 */
	public String getCapacity_of_machinery() {
		return capacity_of_machinery;
	}
	/**
	 * @param capacity_of_machinery the capacity_of_machinery to set
	 */
	public void setCapacity_of_machinery(String capacity_of_machinery) {
		this.capacity_of_machinery = capacity_of_machinery;
	}
	/**
	 * @return the inletType
	 */
	public String getInletType() {
		return inletType;
	}
	/**
	 * @param inletType the inletType to set
	 */
	public void setInletType(String inletType) {
		this.inletType = inletType;
	}
	/**
	 * @return the inletLenght
	 */
	public String getInletLenght() {
		return inletLenght;
	}
	/**
	 * @param inletLenght the inletLenght to set
	 */
	public void setInletLenght(String inletLenght) {
		this.inletLenght = inletLenght;
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
	 * @return the capacitySSTank
	 */
	public String getCapacitySSTank() {
		return capacitySSTank;
	}
	/**
	 * @param capacitySSTank the capacitySSTank to set
	 */
	public void setCapacitySSTank(String capacitySSTank) {
		this.capacitySSTank = capacitySSTank;
	}
	/**
	 * @return the capacityHLTank
	 */
	public String getCapacityHLTank() {
		return capacityHLTank;
	}
	/**
	 * @param capacityHLTank the capacityHLTank to set
	 */
	public void setCapacityHLTank(String capacityHLTank) {
		this.capacityHLTank = capacityHLTank;
	}
	/**
	 * @return the capacityCWTank
	 */
	public String getCapacityCWTank() {
		return capacityCWTank;
	}
	/**
	 * @param capacityCWTank the capacityCWTank to set
	 */
	public void setCapacityCWTank(String capacityCWTank) {
		this.capacityCWTank = capacityCWTank;
	}
	/**
	 * @return the filtertionType
	 */
	public String getFiltertionType() {
		return filtertionType;
	}
	/**
	 * @param filtertionType the filtertionType to set
	 */
	public void setFiltertionType(String filtertionType) {
		this.filtertionType = filtertionType;
	}
	/**
	 * @return the filtertionNo
	 */
	public String getFiltertionNo() {
		return filtertionNo;
	}
	/**
	 * @param filtertionNo the filtertionNo to set
	 */
	public void setFiltertionNo(String filtertionNo) {
		this.filtertionNo = filtertionNo;
	}
	/**
	 * @return the filtertionCapacity
	 */
	public String getFiltertionCapacity() {
		return filtertionCapacity;
	}
	/**
	 * @param filtertionCapacity the filtertionCapacity to set
	 */
	public void setFiltertionCapacity(String filtertionCapacity) {
		this.filtertionCapacity = filtertionCapacity;
	}
	/**
	 * @return the capacityRawWater
	 */
	public String getCapacityRawWater() {
		return capacityRawWater;
	}
	/**
	 * @param capacityRawWater the capacityRawWater to set
	 */
	public void setCapacityRawWater(String capacityRawWater) {
		this.capacityRawWater = capacityRawWater;
	}
	/**
	 * @return the capacityClearWater
	 */
	public String getCapacityClearWater() {
		return capacityClearWater;
	}
	/**
	 * @param capacityClearWater the capacityClearWater to set
	 */
	public void setCapacityClearWater(String capacityClearWater) {
		this.capacityClearWater = capacityClearWater;
	}
	/**
	 * @return the noOfOhsr
	 */
	public String getNoOfOhsr() {
		return noOfOhsr;
	}
	/**
	 * @param noOfOhsr the noOfOhsr to set
	 */
	public void setNoOfOhsr(String noOfOhsr) {
		this.noOfOhsr = noOfOhsr;
	}
	/**
	 * @return the oHSR_construction_date
	 */
	public String getoHSR_construction_date() {
		return oHSR_construction_date;
	}
	/**
	 * @param oHSR_construction_date the oHSR_construction_date to set
	 */
	public void setoHSR_construction_date(String oHSR_construction_date) {
		this.oHSR_construction_date = oHSR_construction_date;
	}
	/**
	 * @return the oHSRCapcity
	 */
	public String getoHSRCapcity() {
		return oHSRCapcity;
	}
	/**
	 * @param oHSRCapcity the oHSRCapcity to set
	 */
	public void setoHSRCapcity(String oHSRCapcity) {
		this.oHSRCapcity = oHSRCapcity;
	}
	/**
	 * @return the oHSRCondition
	 */
	public String getoHSRCondition() {
		return oHSRCondition;
	}
	/**
	 * @param oHSRCondition the oHSRCondition to set
	 */
	public void setoHSRCondition(String oHSRCondition) {
		this.oHSRCondition = oHSRCondition;
	}
	/**
	 * @return the oHSRWorkingCondition
	 */
	public String getoHSRWorkingCondition() {
		return oHSRWorkingCondition;
	}
	/**
	 * @param oHSRWorkingCondition the oHSRWorkingCondition to set
	 */
	public void setoHSRWorkingCondition(String oHSRWorkingCondition) {
		this.oHSRWorkingCondition = oHSRWorkingCondition;
	}
	/**
	 * @return the schemeUpgraded
	 */
	public String getSchemeUpgraded() {
		return schemeUpgraded;
	}
	/**
	 * @param schemeUpgraded the schemeUpgraded to set
	 */
	public void setSchemeUpgraded(String schemeUpgraded) {
		this.schemeUpgraded = schemeUpgraded;
	}
	/**
	 * @return the schemeExpenditure
	 */
	public String getSchemeExpenditure() {
		return schemeExpenditure;
	}
	/**
	 * @param schemeExpenditure the schemeExpenditure to set
	 */
	public void setSchemeExpenditure(String schemeExpenditure) {
		this.schemeExpenditure = schemeExpenditure;
	}
	/**
	 * @return the depthOfTubewell
	 */
	public String getDepthOfTubewell() {
		return depthOfTubewell;
	}
	/**
	 * @param depthOfTubewell the depthOfTubewell to set
	 */
	public void setDepthOfTubewell(String depthOfTubewell) {
		this.depthOfTubewell = depthOfTubewell;
	}
	/**
	 * @return the sizeOfTubewell
	 */
	public String getSizeOfTubewell() {
		return sizeOfTubewell;
	}
	/**
	 * @param sizeOfTubewell the sizeOfTubewell to set
	 */
	public void setSizeOfTubewell(String sizeOfTubewell) {
		this.sizeOfTubewell = sizeOfTubewell;
	}
	/**
	 * @return the yearOfDrilling
	 */
	public String getYearOfDrilling() {
		return yearOfDrilling;
	}
	/**
	 * @param yearOfDrilling the yearOfDrilling to set
	 */
	public void setYearOfDrilling(String yearOfDrilling) {
		this.yearOfDrilling = yearOfDrilling;
	}
	/**
	 * @return the dischargeComm
	 */
	public String getDischargeComm() {
		return dischargeComm;
	}
	/**
	 * @param dischargeComm the dischargeComm to set
	 */
	public void setDischargeComm(String dischargeComm) {
		this.dischargeComm = dischargeComm;
	}
	/**
	 * @return the presentDis
	 */
	public String getPresentDis() {
		return presentDis;
	}
	/**
	 * @param presentDis the presentDis to set
	 */
	public void setPresentDis(String presentDis) {
		this.presentDis = presentDis;
	}
	/**
	 * @return the presentSpringLevel
	 */
	public String getPresentSpringLevel() {
		return presentSpringLevel;
	}
	/**
	 * @param presentSpringLevel the presentSpringLevel to set
	 */
	public void setPresentSpringLevel(String presentSpringLevel) {
		this.presentSpringLevel = presentSpringLevel;
	}
	/**
	 * @return the installation_of_new_machinery
	 */
	public String getInstallation_of_new_machinery() {
		return installation_of_new_machinery;
	}
	/**
	 * @param installation_of_new_machinery the installation_of_new_machinery to set
	 */
	public void setInstallation_of_new_machinery(String installation_of_new_machinery) {
		this.installation_of_new_machinery = installation_of_new_machinery;
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
	 * @return the dateOfComm
	 */
	public String getDateOfComm() {
		return dateOfComm;
	}
	/**
	 * @param dateOfComm the dateOfComm to set
	 */
	public void setDateOfComm(String dateOfComm) {
		this.dateOfComm = dateOfComm;
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
	 * @return the villagePerformaGrid
	 */
	public Datagrid getVillagePerformaGrid() {
		return villagePerformaGrid;
	}
	/**
	 * @param villagePerformaGrid the villagePerformaGrid to set
	 */
	public void setVillagePerformaGrid(Datagrid villagePerformaGrid) {
		this.villagePerformaGrid = villagePerformaGrid;
	}
	/**
	 * @return the locationId
	 */
	public String getLocationId() {
		return locationId;
	}
	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
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
	 * @return the villageName
	 */
	public String getVillageName() {
		return villageName;
	}
	/**
	 * @param villageName the villageName to set
	 */
	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	/**
	 * @return the scheme_id
	 */
	public String getScheme_id() {
		return scheme_id;
	}
	/**
	 * @param scheme_id the scheme_id to set
	 */
	public void setScheme_id(String scheme_id) {
		this.scheme_id = scheme_id;
	}
	
	
	/**
	 * @return the hadBastNo
	 */
	public String getHadBastNo() {
		return hadBastNo;
	}
	/**
	 * @param hadBastNo the hadBastNo to set
	 */
	public void setHadBastNo(String hadBastNo) {
		this.hadBastNo = hadBastNo;
	}
	
	/**
	 * @return the noOfHabitation
	 */
	public int getNoOfHabitation() {
		return noOfHabitation;
	}
	/**
	 * @param noOfHabitation the noOfHabitation to set
	 */
	public void setNoOfHabitation(int noOfHabitation) {
		this.noOfHabitation = noOfHabitation;
	}
	/**
	 * @return the nameOfHabitation
	 */
	public String getNameOfHabitation() {
		return nameOfHabitation;
	}
	/**
	 * @param nameOfHabitation the nameOfHabitation to set
	 */
	public void setNameOfHabitation(String nameOfHabitation) {
		this.nameOfHabitation = nameOfHabitation;
	}
	/**
	 * @return the typeOfHabitation
	 */
	public String getTypeOfHabitation() {
		return typeOfHabitation;
	}
	/**
	 * @param typeOfHabitation the typeOfHabitation to set
	 */
	public void setTypeOfHabitation(String typeOfHabitation) {
		this.typeOfHabitation = typeOfHabitation;
	}
	/**
	 * @return the misCode
	 */
	public String getMisCode() {
		return misCode;
	}
	/**
	 * @param misCode the misCode to set
	 */
	public void setMisCode(String misCode) {
		this.misCode = misCode;
	}
	/**
	 * @return the assemblyConstituency
	 */
	public String getAssemblyConstituency() {
		return assemblyConstituency;
	}
	/**
	 * @param assemblyConstituency the assemblyConstituency to set
	 */
	public void setAssemblyConstituency(String assemblyConstituency) {
		this.assemblyConstituency = assemblyConstituency;
	}
	/**
	 * @return the poputlationTotal
	 */
	public String getPoputlationTotal() {
		return poputlationTotal;
	}
	/**
	 * @param poputlationTotal the poputlationTotal to set
	 */
	public void setPoputlationTotal(String poputlationTotal) {
		this.poputlationTotal = poputlationTotal;
	}
	/**
	 * @return the populationSc
	 */
	public String getPopulationSc() {
		return populationSc;
	}
	/**
	 * @param populationSc the populationSc to set
	 */
	public void setPopulationSc(String populationSc) {
		this.populationSc = populationSc;
	}
	/**
	 * @return the noOfHouseholdsTotal
	 */
	public String getNoOfHouseholdsTotal() {
		return noOfHouseholdsTotal;
	}
	/**
	 * @param noOfHouseholdsTotal the noOfHouseholdsTotal to set
	 */
	public void setNoOfHouseholdsTotal(String noOfHouseholdsTotal) {
		this.noOfHouseholdsTotal = noOfHouseholdsTotal;
	}
	/**
	 * @return the noOfHouseholdsSc
	 */
	public String getNoOfHouseholdsSc() {
		return noOfHouseholdsSc;
	}
	/**
	 * @param noOfHouseholdsSc the noOfHouseholdsSc to set
	 */
	public void setNoOfHouseholdsSc(String noOfHouseholdsSc) {
		this.noOfHouseholdsSc = noOfHouseholdsSc;
	}
	/**
	 * @return the panchayatGhr
	 */
	public String getPanchayatGhr() {
		return panchayatGhr;
	}
	/**
	 * @param panchayatGhr the panchayatGhr to set
	 */
	public void setPanchayatGhr(String panchayatGhr) {
		this.panchayatGhr = panchayatGhr;
	}
	/**
	 * @return the commCenterDhar
	 */
	public String getCommCenterDhar() {
		return commCenterDhar;
	}
	/**
	 * @param commCenterDhar the commCenterDhar to set
	 */
	public void setCommCenterDhar(String commCenterDhar) {
		this.commCenterDhar = commCenterDhar;
	}
	/**
	 * @return the angarwaries
	 */
	public String getAngarwaries() {
		return angarwaries;
	}
	/**
	 * @param angarwaries the angarwaries to set
	 */
	public void setAngarwaries(String angarwaries) {
		this.angarwaries = angarwaries;
	}
	/**
	 * @return the typeBuilding
	 */
	public String getTypeBuilding() {
		return typeBuilding;
	}
	/**
	 * @param typeBuilding the typeBuilding to set
	 */
	public void setTypeBuilding(String typeBuilding) {
		this.typeBuilding = typeBuilding;
	}
	/**
	 * @return the govtSchools
	 */
	public String getGovtSchools() {
		return govtSchools;
	}
	/**
	 * @param govtSchools the govtSchools to set
	 */
	public void setGovtSchools(String govtSchools) {
		this.govtSchools = govtSchools;
	}
	/**
	 * @return the healthCntr
	 */
	public String getHealthCntr() {
		return healthCntr;
	}
	/**
	 * @param healthCntr the healthCntr to set
	 */
	public void setHealthCntr(String healthCntr) {
		this.healthCntr = healthCntr;
	}
	/**
	 * @return the villagePonds
	 */
	public String getVillagePonds() {
		return villagePonds;
	}
	/**
	 * @param villagePonds the villagePonds to set
	 */
	public void setVillagePonds(String villagePonds) {
		this.villagePonds = villagePonds;
	}
	/**
	 * @return the areaSqrMtr
	 */
	public String getAreaSqrMtr() {
		return areaSqrMtr;
	}
	/**
	 * @param areaSqrMtr the areaSqrMtr to set
	 */
	public void setAreaSqrMtr(String areaSqrMtr) {
		this.areaSqrMtr = areaSqrMtr;
	}
	/**
	 * @return the waterScheme
	 */
	public String getWaterScheme() {
		return waterScheme;
	}
	/**
	 * @param waterScheme the waterScheme to set
	 */
	public void setWaterScheme(String waterScheme) {
		this.waterScheme = waterScheme;
	}
	@Override
	public String toString() {
		return "PerformaMasterForm [locationId=" + locationId + ", schemeId=" + schemeId + ", villageId=" + villageId
				+ ", serialID=" + serialID + ", villageName=" + villageName + ", scheme_id=" + scheme_id
				+ ", hadBastNo=" + hadBastNo + ", noOfHabitation=" + noOfHabitation + ", nameOfHabitation="
				+ nameOfHabitation + ", typeOfHabitation=" + typeOfHabitation + ", misCode=" + misCode
				+ ", assemblyConstituency=" + assemblyConstituency + ", poputlationTotal=" + poputlationTotal
				+ ", populationSc=" + populationSc + ", noOfHouseholdsTotal=" + noOfHouseholdsTotal
				+ ", noOfHouseholdsSc=" + noOfHouseholdsSc + ", panchayatGhr=" + panchayatGhr + ", commCenterDhar="
				+ commCenterDhar + ", angarwaries=" + angarwaries + ", typeBuilding=" + typeBuilding + ", govtSchools="
				+ govtSchools + ", healthCntr=" + healthCntr + ", villagePonds=" + villagePonds + ", areaSqrMtr="
				+ areaSqrMtr + ", waterScheme=" + waterScheme + ", sewerageScheme=" + sewerageScheme
				+ ", villagePerformaGrid=" + villagePerformaGrid + ", schemeSource=" + schemeSource + ", schemeType="
				+ schemeType + ", dateOfComm=" + dateOfComm + ", progId=" + progId + ", serviceLevel=" + serviceLevel
				+ ", schemeUpgraded=" + schemeUpgraded + ", schemeExpenditure=" + schemeExpenditure
				+ ", depthOfTubewell=" + depthOfTubewell + ", sizeOfTubewell=" + sizeOfTubewell + ", yearOfDrilling="
				+ yearOfDrilling + ", dischargeComm=" + dischargeComm + ", presentDis=" + presentDis
				+ ", presentSpringLevel=" + presentSpringLevel + ", installation_of_new_machinery="
				+ installation_of_new_machinery + ", capacity_of_machinery=" + capacity_of_machinery + ", inletType="
				+ inletType + ", inletLenght=" + inletLenght + ", pipeType=" + pipeType + ", capacitySSTank="
				+ capacitySSTank + ", capacityHLTank=" + capacityHLTank + ", capacityCWTank=" + capacityCWTank
				+ ", filtertionType=" + filtertionType + ", filtertionNo=" + filtertionNo + ", filtertionCapacity="
				+ filtertionCapacity + ", capacityRawWater=" + capacityRawWater + ", capacityClearWater="
				+ capacityClearWater + ", noOfOhsr=" + noOfOhsr + ", oHSR_construction_date=" + oHSR_construction_date
				+ ", oHSRCapcity=" + oHSRCapcity + ", oHSRCondition=" + oHSRCondition + ", oHSRWorkingCondition="
				+ oHSRWorkingCondition + ", capMachinery=" + capMachinery + ", multipleSource=" + multipleSource
				+ ", villageSourceGrid=" + villageSourceGrid + ", villId=" + villId + ", depthTubewell=" + depthTubewell
				+ ", sizeTubewell=" + sizeTubewell + ", yearDrill=" + yearDrill + ", disinfectionType="
				+ disinfectionType + ", disinfection_Instalation_time=" + disinfection_Instalation_time
				+ ", disinfectionPrStatus=" + disinfectionPrStatus + ", schemeOperatedBy=" + schemeOperatedBy
				+ ", staffScheme=" + staffScheme + ", sanctionedLoad=" + sanctionedLoad + ", pendingBill3103="
				+ pendingBill3103 + ", pendingBill3006=" + pendingBill3006 + ", avgMonthBillofWSS=" + avgMonthBillofWSS
				+ ", pipelineAC=" + pipelineAC + ", pipelineMSDICi=" + pipelineMSDICi + ", pipelineGI=" + pipelineGI
				+ ", pipelinePVC=" + pipelinePVC + ", functionalDistributionPercentage="
				+ functionalDistributionPercentage + ", householdWaterConnection=" + householdWaterConnection
				+ ", watered_connection=" + watered_connection + ", metered_connection=" + metered_connection
				+ ", metered_supply_recovery=" + metered_supply_recovery + ", flat_rate_charges_per_month="
				+ flat_rate_charges_per_month + ", arrear_of_recovery_with_consumers_as01042017="
				+ arrear_of_recovery_with_consumers_as01042017 + ", scheme_Functional=" + scheme_Functional
				+ ", scheme_NonFunctional=" + scheme_NonFunctional + ", scheme_NonFunctional_date="
				+ scheme_NonFunctional_date + ", ground_water_potable_status=" + ground_water_potable_status
				+ ", ground_water_potable_No=" + ground_water_potable_No + ", preventive_measures_adopted="
				+ preventive_measures_adopted + ", capacity_of_plant=" + capacity_of_plant + ", date_of_installation="
				+ date_of_installation + ", being_operated_by=" + being_operated_by + ", volume_of_Water_Daily_basis="
				+ volume_of_Water_Daily_basis + ", disposal_of_reject_water=" + disposal_of_reject_water
				+ ", penetration_in_percentage=" + penetration_in_percentage + ", present_rate_of_user_charges="
				+ present_rate_of_user_charges + ", seperate_Sanctioned_Load=" + seperate_Sanctioned_Load
				+ ", seperate_Pending_eletric_bill31032017=" + seperate_Pending_eletric_bill31032017
				+ ", average_monthly_bill_of_Treatment_plant=" + average_monthly_bill_of_Treatment_plant
				+ ", independent_New_WSS=" + independent_New_WSS + ", upgradation_of_existing_WSS="
				+ upgradation_of_existing_WSS + ", instltion_Wtr_Treatment_plant=" + instltion_Wtr_Treatment_plant
				+ ", source_of_WSS=" + source_of_WSS + ", shifted_to_canal_from_Other=" + shifted_to_canal_from_Other
				+ ", Driling_of_new_tubewell_machinery_size=" + Driling_of_new_tubewell_machinery_size
				+ ", driling_of_new_tubewell_machinery_depth=" + driling_of_new_tubewell_machinery_depth
				+ ", driling_of_new_tubewell_machinery_capacity=" + driling_of_new_tubewell_machinery_capacity
				+ ", driling_of_new_tubewell_machinery_cost=" + driling_of_new_tubewell_machinery_cost
				+ ", canal_based_Inlet_channel_Size_of_pipe=" + canal_based_Inlet_channel_Size_of_pipe
				+ ", canal_based_Inlet_channel_length=" + canal_based_Inlet_channel_length
				+ ", canal_based_s_and_s_Capacity=" + canal_based_s_and_s_Capacity
				+ ", canal_based_Filteration_Plan_type=" + canal_based_Filteration_Plan_type
				+ ", canal_based_Filteration_Plan_capacity=" + canal_based_Filteration_Plan_capacity
				+ ", canal_based_Cost=" + canal_based_Cost + ", oHSR_Capacity=" + oHSR_Capacity
				+ ", oHSR_Full_Supply_Level=" + oHSR_Full_Supply_Level + ", oHSR_Cost=" + oHSR_Cost
				+ ", other_structures_at_waterworks=" + other_structures_at_waterworks
				+ ", other_structures_at_waterworks_Cost=" + other_structures_at_waterworks_Cost
				+ ", distribution_WSS_to_village_type=" + distribution_WSS_to_village_type
				+ ", distribution_WSS_to_village_length=" + distribution_WSS_to_village_length
				+ ", distribution_WSS_within_village_type=" + distribution_WSS_within_village_type
				+ ", distribution_WSS_within_village_length=" + distribution_WSS_within_village_length
				+ ", distribution_Cost=" + distribution_Cost + ", no_of_connections_100_mtr="
				+ no_of_connections_100_mtr + ", no_of_mtrs_100_mtr=" + no_of_mtrs_100_mtr + ", cost_100_mtr="
				+ cost_100_mtr + ", disinfection_Unit_Type=" + disinfection_Unit_Type
				+ ", water_Treatment_plant_in_case_of_quality_village_type="
				+ water_Treatment_plant_in_case_of_quality_village_type
				+ ", water_Treatment_plant_in_case_of_quality_village_capacity="
				+ water_Treatment_plant_in_case_of_quality_village_capacity
				+ ", water_Treatment_plant_in_case_of_quality_village_Cost="
				+ water_Treatment_plant_in_case_of_quality_village_Cost + ", bulk_Water_meter_no=" + bulk_Water_meter_no
				+ ", bulk_Water_meter_cost=" + bulk_Water_meter_cost
				+ ", extension_Sanction_of_new_electric_connection_cost="
				+ extension_Sanction_of_new_electric_connection_cost + ", Block=" + Block + ", disinfection_Unit_Cost="
				+ disinfection_Unit_Cost + ", fiveYearPlanGridBean=" + fiveYearPlanGridBean
				+ ", independent_New_WSS_Cost=" + independent_New_WSS_Cost + ", upgradation_of_existing_WSS_Cost="
				+ upgradation_of_existing_WSS_Cost + ", blockId=" + blockId + ", villgId=" + villgId + ", villagesId="
				+ villagesId + ", blockIds=" + blockIds + ", schemeName=" + schemeName + ", instltion_Wtr_Treatment="
				+ instltion_Wtr_Treatment + ", driling_of_new_tubewell_machinery=" + driling_of_new_tubewell_machinery
				+ ", canal_based=" + canal_based + ", ohsr=" + ohsr + ", otherStructure=" + otherStructure
				+ ", distrNetwork=" + distrNetwork + ", disincUnit=" + disincUnit + ", insWaterPlant=" + insWaterPlant
				+ ", otherWork=" + otherWork + ", mtrConnection=" + mtrConnection + ", oHSRDismantling="
				+ oHSRDismantling + ", oHSRFullSuplyLvl=" + oHSRFullSuplyLvl + ", clear_water_tank=" + clear_water_tank
				+ ", schemeSrc=" + schemeSrc + ", disinfecReq=" + disinfecReq + ", firstSrc=" + firstSrc
				+ ", oHSRCTDia=" + oHSRCTDia + ", oHSRCTDepth=" + oHSRCTDepth + ", oHSRUGSRDepth=" + oHSRUGSRDepth
				+ ", oHSRUGSRDia=" + oHSRUGSRDia + ", distribution_WSS_within_village_type_pvc="
				+ distribution_WSS_within_village_type_pvc + ", distribution_WSS_within_village_type_di_ms="
				+ distribution_WSS_within_village_type_di_ms + ", distribution_WSS_within_village_type_gi="
				+ distribution_WSS_within_village_type_gi + ", oHSRCTDiameter=" + oHSRCTDiameter + ", oHSR_CT_Depth="
				+ oHSR_CT_Depth + ", oHSR_UGS_RDia=" + oHSR_UGS_RDia + ", oHSR_UGSR_Depth=" + oHSR_UGSR_Depth
				+ ", dateOfComplition=" + dateOfComplition + ", remarksdateOfComplition=" + remarksdateOfComplition
				+ ", underProcessSelection=" + underProcessSelection + ", any_other_desc=" + any_other_desc + "]";
	}
	
	
	
	
}
