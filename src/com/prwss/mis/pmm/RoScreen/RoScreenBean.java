package com.prwss.mis.pmm.RoScreen;

import java.io.Serializable;
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
//@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name="ro_status_screen", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class RoScreenBean implements Serializable{

	private static final long serialVersionUID = 2762579261353797041L;
	
	@Id
	@GeneratedValue(generator="ro_connection_id_seq",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="ro_connection_id_seq",sequenceName="prwss_main.ro_connection_id_seq")
	@Column(name="ro_connection_id")
	private Integer roConnectionId;
	
	@Column(name="division_id")
	private String division;
	
	@Column(name="division_name")
	private String divisionName;
	
	@Column(name="sub_div_id")
	private String subDivision;
	
	@Column(name="sub_div_name")
	private String subDivisionName;
	
	@Column(name="village_id")
	private String village;
	
	@Column(name="village_name")
	private String villageName;
	
	@Column(name="ro_capacity")
	private String capacityOfRO;
	
	@Column(name="program_name")
	private String headProgramme;
	
	@Column(name="adm_app_cost")
	private Double adminAppCost;
	
	@Column(name="exec_agency")
	private String executingAgency;
	
	@Column(name="OM_comp_date")
	private Date compOandMDate;
	
	@Column(name="seasonal_ro")
	private String seasonalRO;
	
	@Column(name="tot_hh")
	private Integer noHHsVillage;
	
	@Column(name="locked_houses")
	private Integer lockedHouses;
	
	@Column(name="card_holders")
	private Integer cardHolders;
	
	@Column(name="penetration_perc")
	private Double percenPenetration;
	
	@Column(name="status_overall")
	private Integer tubeInstStatus;
	
	@Column(name="status_platform")
	private Integer platform;
	
	@Column(name="status_housing")
	private Integer housingStr;
	
	@Column(name="status_machine")
	private Integer machinery;
	
	@Column(name="status_plant")
	private Integer plant;
	
	@Column(name="status_elec_conn")
	private Integer elecConnStatus;
	
	@Column(name="allotment_cost")
	private Double allotCost;
	
	@Column(name="exepend_till_dt")
	private Double expenTillDate;
	
	@Column(name="plant_commision")
	private Integer plantCommissioned;
	
	@Column(name="commision_date")
	private Date commisioningDate;
	
	@Column(name="target_comm_dt")
	private Date targetCommDate;
	
	@Column(name="plant_compleated")
	private Integer plantComplete;
	
	@Column(name="comp_date")
	private Date completeDate;
	
	@Column(name="target_comp_date")
	private Date targetCopmDate;
	
	@Column(name="plant_functional")
	private Integer plantFunction;
	
	@Column(name="dt_non_func")
	private Date sinceNonFunction;
	
	@Column(name="reason_non_func")
	private Integer reason;
	
	@Column(name="func_by_target")
	private Date functionalDate;
	
	@Column(name="active_flag")
	private Integer activeFlag;
	
	@Column(name="Crt_By_User")
	private Long createdByUser;
	
	@Column(name="lst_updated_date")
	private Date lastUpdatedDate;
	
	@Column(name="lst_updated_user")
	private Long lastUpdatedUser;
	
	@Column(name="others_reason")
	private String othersReason;
	
	
	public Integer getRoConnectionId() {
		return roConnectionId;
	}

	public void setRoConnectionId(Integer roConnectionId) {
		this.roConnectionId = roConnectionId;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public String getSubDivision() {
		return subDivision;
	}

	public void setSubDivision(String subDivision) {
		this.subDivision = subDivision;
	}

	public String getSubDivisionName() {
		return subDivisionName;
	}

	public void setSubDivisionName(String subDivisionName) {
		this.subDivisionName = subDivisionName;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public String getCapacityOfRO() {
		return capacityOfRO;
	}

	public void setCapacityOfRO(String capacityOfRO) {
		this.capacityOfRO = capacityOfRO;
	}

	public String getHeadProgramme() {
		return headProgramme;
	}

	public void setHeadProgramme(String headProgramme) {
		this.headProgramme = headProgramme;
	}

	public Double getAdminAppCost() {
		return adminAppCost;
	}

	public void setAdminAppCost(Double adminAppCost) {
		this.adminAppCost = adminAppCost;
	}

	public String getExecutingAgency() {
		return executingAgency;
	}

	public void setExecutingAgency(String executingAgency) {
		this.executingAgency = executingAgency;
	}

	public Date getCompOandMDate() {
		return compOandMDate;
	}

	public void setCompOandMDate(Date compOandMDate) {
		this.compOandMDate = compOandMDate;
	}
	
	public String getSeasonalRO() {
		return seasonalRO;
	}

	public void setSeasonalRO(String seasonalRO) {
		this.seasonalRO = seasonalRO;
	}

	public Integer getNoHHsVillage() {
		return noHHsVillage;
	}

	public void setNoHHsVillage(Integer noHHsVillage) {
		this.noHHsVillage = noHHsVillage;
	}

	public Integer getLockedHouses() {
		return lockedHouses;
	}

	public void setLockedHouses(Integer lockedHouses) {
		this.lockedHouses = lockedHouses;
	}

	public Integer getCardHolders() {
		return cardHolders;
	}

	public void setCardHolders(Integer cardHolders) {
		this.cardHolders = cardHolders;
	}

	public Double getPercenPenetration() {
		return percenPenetration;
	}

	public void setPercenPenetration(Double percenPenetration) {
		this.percenPenetration = percenPenetration;
	}

	public Integer getTubeInstStatus() {
		return tubeInstStatus;
	}

	public void setTubeInstStatus(Integer tubeInstStatus) {
		this.tubeInstStatus = tubeInstStatus;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

	public Integer getHousingStr() {
		return housingStr;
	}

	public void setHousingStr(Integer housingStr) {
		this.housingStr = housingStr;
	}

	public Integer getMachinery() {
		return machinery;
	}

	public void setMachinery(Integer machinery) {
		this.machinery = machinery;
	}

	public Integer getPlant() {
		return plant;
	}

	public void setPlant(Integer plant) {
		this.plant = plant;
	}

	public Integer getElecConnStatus() {
		return elecConnStatus;
	}

	public void setElecConnStatus(Integer elecConnStatus) {
		this.elecConnStatus = elecConnStatus;
	}

	public Double getAllotCost() {
		return allotCost;
	}

	public void setAllotCost(Double allotCost) {
		this.allotCost = allotCost;
	}

	public Double getExpenTillDate() {
		return expenTillDate;
	}

	public void setExpenTillDate(Double expenTillDate) {
		this.expenTillDate = expenTillDate;
	}

	public Integer getPlantCommissioned() {
		return plantCommissioned;
	}

	public void setPlantCommissioned(Integer plantCommissioned) {
		this.plantCommissioned = plantCommissioned;
	}

	public Date getCommisioningDate() {
		return commisioningDate;
	}

	public void setCommisioningDate(Date commisioningDate) {
		this.commisioningDate = commisioningDate;
	}

	public Date getTargetCommDate() {
		return targetCommDate;
	}

	public void setTargetCommDate(Date targetCommDate) {
		this.targetCommDate = targetCommDate;
	}

	public Integer getPlantComplete() {
		return plantComplete;
	}

	public void setPlantComplete(Integer plantComplete) {
		this.plantComplete = plantComplete;
	}

	public Date getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}

	public Date getTargetCopmDate() {
		return targetCopmDate;
	}

	public void setTargetCopmDate(Date targetCopmDate) {
		this.targetCopmDate = targetCopmDate;
	}

	public Integer getPlantFunction() {
		return plantFunction;
	}

	public void setPlantFunction(Integer plantFunction) {
		this.plantFunction = plantFunction;
	}

	public Date getSinceNonFunction() {
		return sinceNonFunction;
	}

	public void setSinceNonFunction(Date sinceNonFunction) {
		this.sinceNonFunction = sinceNonFunction;
	}

	public Integer getReason() {
		return reason;
	}

	public void setReason(Integer reason) {
		this.reason = reason;
	}

	public Date getFunctionalDate() {
		return functionalDate;
	}

	public void setFunctionalDate(Date functionalDate) {
		this.functionalDate = functionalDate;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Long getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(Long createdByUser) {
		this.createdByUser = createdByUser;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public Long getLastUpdatedUser() {
		return lastUpdatedUser;
	}

	public void setLastUpdatedUser(Long lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}

	public String getOthersReason() {
		return othersReason;
	}

	public void setOthersReason(String othersReason) {
		this.othersReason = othersReason;
	}

	@Override
	public String toString() {
		return "RoScreenBean [roConnectionId=" + roConnectionId + ", division=" + division + ", divisionName="
				+ divisionName + ", subDivision=" + subDivision + ", subDivisionName=" + subDivisionName + ", village="
				+ village + ", villageName=" + villageName + ", capacityOfRO=" + capacityOfRO + ", headProgramme="
				+ headProgramme + ", adminAppCost=" + adminAppCost + ", executingAgency=" + executingAgency
				+ ", compOandMDate=" + compOandMDate + ", seasonalRO=" + seasonalRO + ", noHHsVillage=" + noHHsVillage
				+ ", lockedHouses=" + lockedHouses + ", cardHolders=" + cardHolders + ", percenPenetration="
				+ percenPenetration + ", tubeInstStatus=" + tubeInstStatus + ", platform=" + platform + ", housingStr="
				+ housingStr + ", machinery=" + machinery + ", plant=" + plant + ", elecConnStatus=" + elecConnStatus
				+ ", allotCost=" + allotCost + ", expenTillDate=" + expenTillDate + ", plantCommissioned="
				+ plantCommissioned + ", commisioningDate=" + commisioningDate + ", targetCommDate=" + targetCommDate
				+ ", plantComplete=" + plantComplete + ", completeDate=" + completeDate + ", targetCopmDate="
				+ targetCopmDate + ", plantFunction=" + plantFunction + ", sinceNonFunction=" + sinceNonFunction
				+ ", reason=" + reason + ", functionalDate=" + functionalDate + ", activeFlag=" + activeFlag
				+ ", createdByUser=" + createdByUser + ", lastUpdatedDate=" + lastUpdatedDate + ", lastUpdatedUser="
				+ lastUpdatedUser + ", othersReason=" + othersReason + "]";
	}

}
