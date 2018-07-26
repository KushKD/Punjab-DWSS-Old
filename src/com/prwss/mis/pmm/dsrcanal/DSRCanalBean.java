/**
 * 
 */
package com.prwss.mis.pmm.dsrcanal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.pmm.dsrcanal.dao.DSRCanalDistributionBean;
import com.prwss.mis.pmm.dsrcanal.dao.DSRCanalInletBean;

/**
 * @author pjha
 *
 */
@Entity
@Table(name="pmm_dsr_canal", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class DSRCanalBean implements Serializable {

	private static final long serialVersionUID = -3541321940464277794L;
	
	@Id
	@Column(name = "scheme_id", nullable = false)
	private String schemeId;
	
	@Column(name = "village_id", nullable = false)
	private String villageId;
	
	@Column(name = "dsr_date")
	private Date dsrDate;
	
	
	@Column(name = "location_id")
	private String locationId;
	@Column(name = "disinfection_type")
	private String disinfectionType;
	@Column(name = "disinfection_cost")
	private double disinfectionCost;
	@Column(name = "disinfection_quantity")
	private long disinfectionQuantity; 
	@Column(name = "pump_select")
	private String pumpSelect;
	@Column(name = "pump_quantity")
	private long pumpQuantity; 
	@Column(name = "pump_cost")
	private double pumpCost; 
	@Column(name = "rcc_capacity")
	private long rccCapacity;  
	@Column(name = "rcc_fsl")
	private long rccFsl;  
	@Column(name = "rcc_quantity")
	private long rccQuantity; 
	@Column(name = "rcc_cost")
	private double rccCost; 
	@Column(name = "electronic_quantity")
	private long electronicQuantity; 
	@Column(name = "electronic_cost")
	private double electronicCost; 
	@Column(name = "plinth_cost")
	private double plinthCost;   
	@Column(name = "pumping_centrifugal")
	private String pumpingCentrifugal;
	@Column(name = "pumping_submersible")
	private String pumpingSubmersible;
	@Column(name = "pumping_head")
	private long pumpingHead; 
	@Column(name = "pumping_discharge")
	private long pumpingDischarge; 
	@Column(name = "pumping_quantity")
	private long pumpingQuantity;  
	@Column(name = "pumping_cost")
	private double pumpingCost;  
	@Column(name = "pipline_size")
	private String piplineSize;
	@Column(name = "pipline_type")
	private String piplineType;
	@Column(name = "pipline_length")
	private long piplineLength;   
	@Column(name = "pipline_cost")
	private double piplineCost; 
	@Column(name = "total_cost_site_laying")
	private long totalCostSiteLaying; 
	@Column(name = "distribution_cost")
	private double distributionCost; 
	@Column(name = "slueice_size")
	private String slueiceSize;
	@Column(name = "slueice_cost")
	private double slueiceCost; 
	@Column(name = "cost_of_electric_connection_provision")
	private double costOfElectricConnectionProvision; 
	@Column(name = "total_cost_of_topographical_survey")
	private double totalCostOfTopographicalSurvey; 
	@Column(name = "storage_size")
	private String storageSize ;
	@Column(name = "storage_depth")
	private long storageDepth; 
	@Column(name = "storage_quantity")
	private long storageQuantity; 
	@Column(name = "storage_cost")
	private double storageCost; 
	@Column(name = "suction_dimeter")
	private long suctionDimeter; 
	@Column(name = "suction_cost")
	private double suctionCost; 
	@Column(name = "suction_quantity")
	private long suctionQuantity;  
	@Column(name = "high_type")
	private String highType;
	@Column(name = "high_size")
	private String highSize;
	@Column(name = "high_quantity")
	private long highQuantity;   
	@Column(name = "high_cost")
	private double highCost;  
	@Column(name = "filter_type")
	private String filterType;
	@Column(name = "filter_size")
	private String filterSize;
	@Column(name = "filter_quantity")
	private long filterQuantity;  
	@Column(name = "filter_cost")
	private double filterCost;  
	@Column(name = "clear_type")
	private String clearType;
	@Column(name = "clear_quantity")
	private long clearQuantity;   
	@Column(name = "clear_cost")
	private double clearCost;    
	@Column(name = "bulk_size")
	private String bulkSize;
	@Column(name = "bulk_count")
	private long bulkCount;  
	@Column(name = "bulk_total_cost")
	private double bulkTotalCost;  
	@Column(name = "inlet_total_cost")
	private double inletTotalCost;  
	@Column(name = "pumping_machinery_applicable")
	private boolean pumpingMachineryApplicable;  
	@Column(name = "pumping_machinery_cost")
	private double pumpingMachineryCost; 	
	@Column(name = "pumping_machinery_size")
	private String pumpingMachinerySize;
	@Column(name = "pumping_power")
	private String pumpingPower;
	@Column(name = "pumping_machinery_discharge")
	private String pumpingMachineryDischarge;
	@Column(name = "total_cost_of_structure")
	private BigDecimal totalCostOfStructure; 	
	@Column(name = "percapita_cost")
	private BigDecimal percapitaCost; 
	@Column(name = "environment_cost")
	private BigDecimal environmentCost;   
	@Column(name = "contigency_charges")
	private BigDecimal contigencyCharges;  
	@Column(name = "grand_total")
	private BigDecimal grandTotal;  
	@Column(name = "clear_size")
	private String clearSize; 
	
	@Column(name = "rcc_capacity_1")
	private long rccCapacity1; 
	@Column(name = "rcc_fsl_1")
	private long rccFsl1;  
	@Column(name = "rcc_quantity_1")
	private long rccQuantity1; 
	@Column(name = "rcc_cost_1")
	private double rccCost1; 
	@Column(name = "pumping_centrifugal_1")
	private String pumpingCentrifugal1;
	@Column(name = "pumping_submersible_1")
	private String pumpingSubmersible1;
	@Column(name = "pumping_head_1")
	private long pumpingHead1; 
	@Column(name = "pumping_discharge_1")
	private long pumpingDischarge1; 
	@Column(name = "pump_quantity_1")
	private long pumpQuantity1; 
	@Column(name = "pump_cost_1")
	private double pumpCost1; 
	
	
	

	@OneToMany(targetEntity=DSRCanalInletBean.class,fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="scheme_id", updatable = false , insertable = false)
	private Set<DSRCanalInletBean> dsrCanalInletBeans;
	
	@OneToMany(targetEntity=DSRCanalDistributionBean.class,fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="scheme_id", updatable = false , insertable = false)
	private Set<DSRCanalDistributionBean> dsrCanalDistributionBeans;
	
	@Embedded
	private MISAuditBean misAuditBean;
	
	
	
	

	public long getRccCapacity1() {
		return rccCapacity1;
	}

	public void setRccCapacity1(long rccCapacity1) {
		this.rccCapacity1 = rccCapacity1;
	}

	public long getRccFsl1() {
		return rccFsl1;
	}

	public void setRccFsl1(long rccFsl1) {
		this.rccFsl1 = rccFsl1;
	}

	public long getRccQuantity1() {
		return rccQuantity1;
	}

	public void setRccQuantity1(long rccQuantity1) {
		this.rccQuantity1 = rccQuantity1;
	}

	public double getRccCost1() {
		return rccCost1;
	}

	public void setRccCost1(double rccCost1) {
		this.rccCost1 = rccCost1;
	}

	public String getPumpingCentrifugal1() {
		return pumpingCentrifugal1;
	}

	public void setPumpingCentrifugal1(String pumpingCentrifugal1) {
		this.pumpingCentrifugal1 = pumpingCentrifugal1;
	}

	public String getPumpingSubmersible1() {
		return pumpingSubmersible1;
	}

	public void setPumpingSubmersible1(String pumpingSubmersible1) {
		this.pumpingSubmersible1 = pumpingSubmersible1;
	}

	public long getPumpingHead1() {
		return pumpingHead1;
	}

	public void setPumpingHead1(long pumpingHead1) {
		this.pumpingHead1 = pumpingHead1;
	}

	public long getPumpingDischarge1() {
		return pumpingDischarge1;
	}

	public void setPumpingDischarge1(long pumpingDischarge1) {
		this.pumpingDischarge1 = pumpingDischarge1;
	}

	public long getPumpQuantity1() {
		return pumpQuantity1;
	}

	public void setPumpQuantity1(long pumpQuantity1) {
		this.pumpQuantity1 = pumpQuantity1;
	}

	public double getPumpCost1() {
		return pumpCost1;
	}

	public void setPumpCost1(double pumpCost1) {
		this.pumpCost1 = pumpCost1;
	}

	public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	public String getVillageId() {
		return villageId;
	}

	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}

	public Date getDsrDate() {
		return dsrDate;
	}

	public void setDsrDate(Date dsrDate) {
		this.dsrDate = dsrDate;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getDisinfectionType() {
		return disinfectionType;
	}

	public void setDisinfectionType(String disinfectionType) {
		this.disinfectionType = disinfectionType;
	}

	public double getDisinfectionCost() {
		return disinfectionCost;
	}

	public void setDisinfectionCost(double disinfectionCost) {
		this.disinfectionCost = disinfectionCost;
	}

	public long getDisinfectionQuantity() {
		return disinfectionQuantity;
	}

	public void setDisinfectionQuantity(long disinfectionQuantity) {
		this.disinfectionQuantity = disinfectionQuantity;
	}

	public String getPumpSelect() {
		return pumpSelect;
	}

	public void setPumpSelect(String pumpSelect) {
		this.pumpSelect = pumpSelect;
	}

	public long getPumpQuantity() {
		return pumpQuantity;
	}

	public void setPumpQuantity(long pumpQuantity) {
		this.pumpQuantity = pumpQuantity;
	}

	public double getPumpCost() {
		return pumpCost;
	}

	public void setPumpCost(double pumpCost) {
		this.pumpCost = pumpCost;
	}

	public long getRccCapacity() {
		return rccCapacity;
	}

	public void setRccCapacity(long rccCapacity) {
		this.rccCapacity = rccCapacity;
	}

	public long getRccFsl() {
		return rccFsl;
	}

	public void setRccFsl(long rccFsl) {
		this.rccFsl = rccFsl;
	}

	public long getRccQuantity() {
		return rccQuantity;
	}

	public void setRccQuantity(long rccQuantity) {
		this.rccQuantity = rccQuantity;
	}

	public double getRccCost() {
		return rccCost;
	}

	public void setRccCost(double rccCost) {
		this.rccCost = rccCost;
	}

	public long getElectronicQuantity() {
		return electronicQuantity;
	}

	public void setElectronicQuantity(long electronicQuantity) {
		this.electronicQuantity = electronicQuantity;
	}

	public double getElectronicCost() {
		return electronicCost;
	}

	public void setElectronicCost(double electronicCost) {
		this.electronicCost = electronicCost;
	}

	public double getPlinthCost() {
		return plinthCost;
	}

	public void setPlinthCost(double plinthCost) {
		this.plinthCost = plinthCost;
	}

	public String getPumpingCentrifugal() {
		return pumpingCentrifugal;
	}

	public void setPumpingCentrifugal(String pumpingCentrifugal) {
		this.pumpingCentrifugal = pumpingCentrifugal;
	}

	public String getPumpingSubmersible() {
		return pumpingSubmersible;
	}

	public void setPumpingSubmersible(String pumpingSubmersible) {
		this.pumpingSubmersible = pumpingSubmersible;
	}

	public long getPumpingHead() {
		return pumpingHead;
	}

	public void setPumpingHead(long pumpingHead) {
		this.pumpingHead = pumpingHead;
	}

	public long getPumpingDischarge() {
		return pumpingDischarge;
	}

	public void setPumpingDischarge(long pumpingDischarge) {
		this.pumpingDischarge = pumpingDischarge;
	}

	public long getPumpingQuantity() {
		return pumpingQuantity;
	}

	public void setPumpingQuantity(long pumpingQuantity) {
		this.pumpingQuantity = pumpingQuantity;
	}

	public double getPumpingCost() {
		return pumpingCost;
	}

	public void setPumpingCost(double pumpingCost) {
		this.pumpingCost = pumpingCost;
	}

	public String getPiplineSize() {
		return piplineSize;
	}

	public void setPiplineSize(String piplineSize) {
		this.piplineSize = piplineSize;
	}

	public String getPiplineType() {
		return piplineType;
	}

	public void setPiplineType(String piplineType) {
		this.piplineType = piplineType;
	}

	public long getPiplineLength() {
		return piplineLength;
	}

	public void setPiplineLength(long piplineLength) {
		this.piplineLength = piplineLength;
	}

	public double getPiplineCost() {
		return piplineCost;
	}

	public void setPiplineCost(double piplineCost) {
		this.piplineCost = piplineCost;
	}

	public long getTotalCostSiteLaying() {
		return totalCostSiteLaying;
	}

	public void setTotalCostSiteLaying(long totalCostSiteLaying) {
		this.totalCostSiteLaying = totalCostSiteLaying;
	}

	public double getDistributionCost() {
		return distributionCost;
	}

	public void setDistributionCost(double distributionCost) {
		this.distributionCost = distributionCost;
	}

	public String getSlueiceSize() {
		return slueiceSize;
	}

	public void setSlueiceSize(String slueiceSize) {
		this.slueiceSize = slueiceSize;
	}

	public double getSlueiceCost() {
		return slueiceCost;
	}

	public void setSlueiceCost(double slueiceCost) {
		this.slueiceCost = slueiceCost;
	}

	public double getCostOfElectricConnectionProvision() {
		return costOfElectricConnectionProvision;
	}

	public void setCostOfElectricConnectionProvision(
			double costOfElectricConnectionProvision) {
		this.costOfElectricConnectionProvision = costOfElectricConnectionProvision;
	}

	public double getTotalCostOfTopographicalSurvey() {
		return totalCostOfTopographicalSurvey;
	}

	public void setTotalCostOfTopographicalSurvey(
			double totalCostOfTopographicalSurvey) {
		this.totalCostOfTopographicalSurvey = totalCostOfTopographicalSurvey;
	}

	public String getStorageSize() {
		return storageSize;
	}

	public void setStorageSize(String storageSize) {
		this.storageSize = storageSize;
	}

	public long getStorageDepth() {
		return storageDepth;
	}

	public void setStorageDepth(long storageDepth) {
		this.storageDepth = storageDepth;
	}

	public long getStorageQuantity() {
		return storageQuantity;
	}

	public void setStorageQuantity(long storageQuantity) {
		this.storageQuantity = storageQuantity;
	}

	public double getStorageCost() {
		return storageCost;
	}

	public void setStorageCost(double storageCost) {
		this.storageCost = storageCost;
	}

	public long getSuctionDimeter() {
		return suctionDimeter;
	}

	public void setSuctionDimeter(long suctionDimeter) {
		this.suctionDimeter = suctionDimeter;
	}

	public double getSuctionCost() {
		return suctionCost;
	}

	public void setSuctionCost(double suctionCost) {
		this.suctionCost = suctionCost;
	}

	public long getSuctionQuantity() {
		return suctionQuantity;
	}

	public void setSuctionQuantity(long suctionQuantity) {
		this.suctionQuantity = suctionQuantity;
	}

	public String getHighType() {
		return highType;
	}

	public void setHighType(String highType) {
		this.highType = highType;
	}

	public String getHighSize() {
		return highSize;
	}

	public void setHighSize(String highSize) {
		this.highSize = highSize;
	}

	public long getHighQuantity() {
		return highQuantity;
	}

	public void setHighQuantity(long highQuantity) {
		this.highQuantity = highQuantity;
	}

	public double getHighCost() {
		return highCost;
	}

	public void setHighCost(double highCost) {
		this.highCost = highCost;
	}

	public String getFilterType() {
		return filterType;
	}

	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}

	public String getFilterSize() {
		return filterSize;
	}

	public void setFilterSize(String filterSize) {
		this.filterSize = filterSize;
	}

	public long getFilterQuantity() {
		return filterQuantity;
	}

	public void setFilterQuantity(long filterQuantity) {
		this.filterQuantity = filterQuantity;
	}

	public double getFilterCost() {
		return filterCost;
	}

	public void setFilterCost(double filterCost) {
		this.filterCost = filterCost;
	}

	public String getClearType() {
		return clearType;
	}

	public void setClearType(String clearType) {
		this.clearType = clearType;
	}

	public long getClearQuantity() {
		return clearQuantity;
	}

	public void setClearQuantity(long clearQuantity) {
		this.clearQuantity = clearQuantity;
	}

	public double getClearCost() {
		return clearCost;
	}

	public void setClearCost(double clearCost) {
		this.clearCost = clearCost;
	}

	public String getBulkSize() {
		return bulkSize;
	}

	public void setBulkSize(String bulkSize) {
		this.bulkSize = bulkSize;
	}

	public long getBulkCount() {
		return bulkCount;
	}

	public void setBulkCount(long bulkCount) {
		this.bulkCount = bulkCount;
	}

	

	public double getBulkTotalCost() {
		return bulkTotalCost;
	}

	public void setBulkTotalCost(double bulkTotalCost) {
		this.bulkTotalCost = bulkTotalCost;
	}

	public double getInletTotalCost() {
		return inletTotalCost;
	}

	public void setInletTotalCost(double inletTotalCost) {
		this.inletTotalCost = inletTotalCost;
	}

	public boolean isPumpingMachineryApplicable() {
		return pumpingMachineryApplicable;
	}

	public void setPumpingMachineryApplicable(boolean pumpingMachineryApplicable) {
		this.pumpingMachineryApplicable = pumpingMachineryApplicable;
	}

	public double getPumpingMachineryCost() {
		return pumpingMachineryCost;
	}

	public void setPumpingMachineryCost(double pumpingMachineryCost) {
		this.pumpingMachineryCost = pumpingMachineryCost;
	}

	public void setPumpingMachineryCost(long pumpingMachineryCost) {
		this.pumpingMachineryCost = pumpingMachineryCost;
	}

	public String getPumpingMachinerySize() {
		return pumpingMachinerySize;
	}

	public void setPumpingMachinerySize(String pumpingMachinerySize) {
		this.pumpingMachinerySize = pumpingMachinerySize;
	}

	public String getPumpingPower() {
		return pumpingPower;
	}

	public void setPumpingPower(String pumpingPower) {
		this.pumpingPower = pumpingPower;
	}

	public String getPumpingMachineryDischarge() {
		return pumpingMachineryDischarge;
	}

	public void setPumpingMachineryDischarge(String pumpingMachineryDischarge) {
		this.pumpingMachineryDischarge = pumpingMachineryDischarge;
	}

	public BigDecimal getTotalCostOfStructure() {
		return totalCostOfStructure;
	}

	public void setTotalCostOfStructure(BigDecimal totalCostOfStructure) {
		this.totalCostOfStructure = totalCostOfStructure;
	}

	public BigDecimal getPercapitaCost() {
		return percapitaCost;
	}

	public void setPercapitaCost(BigDecimal percapitaCost) {
		this.percapitaCost = percapitaCost;
	}

	public BigDecimal getEnvironmentCost() {
		return environmentCost;
	}

	public void setEnvironmentCost(BigDecimal environmentCost) {
		this.environmentCost = environmentCost;
	}

	public BigDecimal getContigencyCharges() {
		return contigencyCharges;
	}

	public void setContigencyCharges(BigDecimal contigencyCharges) {
		this.contigencyCharges = contigencyCharges;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}

	public String getClearSize() {
		return clearSize;
	}

	public void setClearSize(String clearSize) {
		this.clearSize = clearSize;
	}

	public Set<DSRCanalInletBean> getDsrCanalInletBeans() {
		return dsrCanalInletBeans;
	}

	public void setDsrCanalInletBeans(Set<DSRCanalInletBean> dsrCanalInletBeans) {
		this.dsrCanalInletBeans = dsrCanalInletBeans;
	}

	public Set<DSRCanalDistributionBean> getDsrCanalDistributionBeans() {
		return dsrCanalDistributionBeans;
	}

	public void setDsrCanalDistributionBeans(
			Set<DSRCanalDistributionBean> dsrCanalDistributionBeans) {
		this.dsrCanalDistributionBeans = dsrCanalDistributionBeans;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public String toString() {
		return "DSRCanalBean [schemeId=" + schemeId + ", villageId="
				+ villageId + ", dsrDate=" + dsrDate + ", locationId="
				+ locationId + ", disinfectionType=" + disinfectionType
				+ ", disinfectionCost=" + disinfectionCost
				+ ", disinfectionQuantity=" + disinfectionQuantity
				+ ", pumpSelect=" + pumpSelect + ", pumpQuantity="
				+ pumpQuantity + ", pumpCost=" + pumpCost + ", rccCapacity="
				+ rccCapacity + ", rccFsl=" + rccFsl + ", rccQuantity="
				+ rccQuantity + ", rccCost=" + rccCost
				+ ", electronicQuantity=" + electronicQuantity
				+ ", electronicCost=" + electronicCost + ", plinthCost="
				+ plinthCost + ", pumpingCentrifugal=" + pumpingCentrifugal
				+ ", pumpingSubmersible=" + pumpingSubmersible
				+ ", pumpingHead=" + pumpingHead + ", pumpingDischarge="
				+ pumpingDischarge + ", pumpingQuantity=" + pumpingQuantity
				+ ", pumpingCost=" + pumpingCost + ", piplineSize="
				+ piplineSize + ", piplineType=" + piplineType
				+ ", piplineLength=" + piplineLength + ", piplineCost="
				+ piplineCost + ", totalCostSiteLaying=" + totalCostSiteLaying
				+ ", distributionCost=" + distributionCost + ", slueiceSize="
				+ slueiceSize + ", slueiceCost=" + slueiceCost
				+ ", costOfElectricConnectionProvision="
				+ costOfElectricConnectionProvision
				+ ", totalCostOfTopographicalSurvey="
				+ totalCostOfTopographicalSurvey + ", storageSize="
				+ storageSize + ", storageDepth=" + storageDepth
				+ ", storageQuantity=" + storageQuantity + ", storageCost="
				+ storageCost + ", suctionDimeter=" + suctionDimeter
				+ ", suctionCost=" + suctionCost + ", suctionQuantity="
				+ suctionQuantity + ", highType=" + highType + ", highSize="
				+ highSize + ", highQuantity=" + highQuantity + ", highCost="
				+ highCost + ", filterType=" + filterType + ", filterSize="
				+ filterSize + ", filterQuantity=" + filterQuantity
				+ ", filterCost=" + filterCost + ", clearType=" + clearType
				+ ", clearQuantity=" + clearQuantity + ", clearCost="
				+ clearCost + ", bulkSize=" + bulkSize + ", bulkCount="
				+ bulkCount + ", bulkTotalCost=" + bulkTotalCost
				+ ", inletTotalCost=" + inletTotalCost
				+ ", pumpingMachineryApplicable=" + pumpingMachineryApplicable
				+ ", pumpingMachineryCost=" + pumpingMachineryCost
				+ ", pumpingMachinerySize=" + pumpingMachinerySize
				+ ", pumpingPower=" + pumpingPower
				+ ", pumpingMachineryDischarge=" + pumpingMachineryDischarge
				+ ", totalCostOfStructure=" + totalCostOfStructure
				+ ", percapitaCost=" + percapitaCost + ", environmentCost="
				+ environmentCost + ", contigencyCharges=" + contigencyCharges
				+ ", grandTotal=" + grandTotal + ", clearSize=" + clearSize
				+ ", dsrCanalInletBeans=" + dsrCanalInletBeans
				+ ", dsrCanalDistributionBeans=" + dsrCanalDistributionBeans
				+ ", misAuditBean=" + misAuditBean + "]";
	}
	
	

	

	

}
