package com.prwss.mis.pmm.dsrsewerage;

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
import com.prwss.mis.pmm.dsrsewerage.dao.DSRSewerageSewerBean;

@Entity
@Table(name="pmm_dsr_sewerage", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class DSRSewerageBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3463594292469204820L;
	@Id
	@Column(name = "scheme_id", nullable = false)
	private String schemeId;
	
	@Column(name = "village_id")
	private String villageId;
	
	@Column(name = "dsr_date")
	private Date dsrDate;
	
	@Column(name = "location_id")
	private String locationId;
	@Column(name = "total_cost_pipe_sewer")
	private double  totalCostPipeSewer;
	@Column(name = "manhole_size")
	private String manholeSize;
	@Column(name = "manhole_quantity")
	private long manholeQuantity;
	@Column(name = "manhole_depth_min")
	private long manholeDepthMin;
	@Column(name = "manhole_depth_max")
	private long manholeDepthMax;
	
	@Column(name = "manhole_cost")
	private double manholeCost;
	
	@Column(name = "environment_activities_cost")
	private double provisionEnvironmentActivitiesCost;
	
	@Column(name = "pumping_cost")
	private double pumpingCost;
	@Column(name = "pumping_discharge")
	private String pumpingDischarge;  
	@Column(name = "pumping_head")
	private String pumpingHead;  
	@Column(name = "collection_stp_diameter")
	private long collectionStpDiameter;
	@Column(name = "collection_stp_cost")
	private double collectionStpCost;
	@Column(name = "collection_ips_dimeter")
	private long collectionIpsDimeter;
	@Column(name = "collection_ips_cost")
	private double collectionIpsCost;
	@Column(name = "collection_ips_quantity")
	private long collectionIpsQuantity;
	@Column(name = "machinery_type")
	private String machineryType;
	
	@Column(name = "machinery_head")
	private long machineryHead;
	@Column(name = "machinery_discharge")
	private String machineryDischarge;
	@Column(name = "machinery_quantity")
	private long machineryQuantity;
	@Column(name = "machinery_cost")
	private double machineryCost;
	@Column(name = "genset_pannel_quantity")
	private long gensetPannelQuantity;
	@Column(name = "genset_pannel_cost")
	private double gensetPannelCost;
	@Column(name = "genset_capacity")
	private long gensetCapacity;
	@Column(name = "genset_quantity")
	private long gensetQuantity;
	@Column(name = "genset_electric_connection_cost")
	private double gensetElectricConnectionCost;
	@Column(name = "genset_electric_connection_load")
	private String gensetElectricConnectionLoad;
	@Column(name = "restroom_quantity")
	private long restroomQuantity;
	@Column(name = "restroom_cost")
	private double restroomCost;
	@Column(name = "sewerage_technology")
	private String sewerageTechnology;
	@Column(name = "sewerage_cost")
	private double sewerageCost;
	@Column(name = "site_development_cost")
	private double siteDevelopmentCost;
	@Column(name = "rising_type")
	private String risingType;
	@Column(name = "rising_size")
	private String risingSize;	
	@Column(name = "rising_cost")
	private double risingCost;
	@Column(name = "pumping_machinery_discharge")
	private String pumpingMachineryDischarge;
	@Column(name = "sludge_drying_cost")
	private double sludgeDryingCost;
	@Column(name = "sludge_curing_cost")
	private double sludgeCuringCost;
	@Column(name = "composition_cost")
	private double compositionCost;
	@Column(name = "total_cost_of_structure")
	private BigDecimal totalCostOfStructure;
	@Column(name = "OMcost_for_seven_years")
	private BigDecimal OMcostForSevenYears;
	@Column(name = "environment_cost")
	private BigDecimal environmentCost;
	@Column(name = "contigency_charges")
	private BigDecimal contigencyCharges;
	@Column(name = "grand_total")
	private BigDecimal grandTotal;
	
	@OneToMany(targetEntity=DSRSewerageSewerBean.class,fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="scheme_id", updatable = false , insertable = false)
	private Set<DSRSewerageSewerBean> dsrSewerageSewerBeans;
	
	@Embedded
	private MISAuditBean misAuditBean;
	
	

	

	public String getSchemeId() {
		return schemeId;
	}





	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}





	public String getLocationId() {
		return locationId;
	}





	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}





	public double getTotalCostPipeSewer() {
		return totalCostPipeSewer;
	}





	public void setTotalCostPipeSewer(double totalCostPipeSewer) {
		this.totalCostPipeSewer = totalCostPipeSewer;
	}





	public String getManholeSize() {
		return manholeSize;
	}





	public void setManholeSize(String manholeSize) {
		this.manholeSize = manholeSize;
	}





	public long getManholeQuantity() {
		return manholeQuantity;
	}





	public void setManholeQuantity(long manholeQuantity) {
		this.manholeQuantity = manholeQuantity;
	}





	public long getManholeDepthMin() {
		return manholeDepthMin;
	}





	public void setManholeDepthMin(long manholeDepthMin) {
		this.manholeDepthMin = manholeDepthMin;
	}





	public long getManholeDepthMax() {
		return manholeDepthMax;
	}





	public void setManholeDepthMax(long manholeDepthMax) {
		this.manholeDepthMax = manholeDepthMax;
	}





	public double getManholeCost() {
		return manholeCost;
	}





	public void setManholeCost(double manholeCost) {
		this.manholeCost = manholeCost;
	}





	public double getProvisionEnvironmentActivitiesCost() {
		return provisionEnvironmentActivitiesCost;
	}





	public void setProvisionEnvironmentActivitiesCost(
			double provisionEnvironmentActivitiesCost) {
		this.provisionEnvironmentActivitiesCost = provisionEnvironmentActivitiesCost;
	}





	public double getPumpingCost() {
		return pumpingCost;
	}





	public void setPumpingCost(double pumpingCost) {
		this.pumpingCost = pumpingCost;
	}





	public String getPumpingDischarge() {
		return pumpingDischarge;
	}





	public void setPumpingDischarge(String pumpingDischarge) {
		this.pumpingDischarge = pumpingDischarge;
	}





	public String getPumpingHead() {
		return pumpingHead;
	}





	public void setPumpingHead(String pumpingHead) {
		this.pumpingHead = pumpingHead;
	}





	public long getCollectionStpDiameter() {
		return collectionStpDiameter;
	}





	public void setCollectionStpDiameter(long collectionStpDiameter) {
		this.collectionStpDiameter = collectionStpDiameter;
	}





	public double getCollectionStpCost() {
		return collectionStpCost;
	}





	public void setCollectionStpCost(double collectionStpCost) {
		this.collectionStpCost = collectionStpCost;
	}





	public long getCollectionIpsDimeter() {
		return collectionIpsDimeter;
	}





	public void setCollectionIpsDimeter(long collectionIpsDimeter) {
		this.collectionIpsDimeter = collectionIpsDimeter;
	}





	public double getCollectionIpsCost() {
		return collectionIpsCost;
	}





	public void setCollectionIpsCost(double collectionIpsCost) {
		this.collectionIpsCost = collectionIpsCost;
	}





	public long getCollectionIpsQuantity() {
		return collectionIpsQuantity;
	}





	public void setCollectionIpsQuantity(long collectionIpsQuantity) {
		this.collectionIpsQuantity = collectionIpsQuantity;
	}





	public String getMachineryType() {
		return machineryType;
	}





	public void setMachineryType(String machineryType) {
		this.machineryType = machineryType;
	}





	public long getMachineryHead() {
		return machineryHead;
	}





	public void setMachineryHead(long machineryHead) {
		this.machineryHead = machineryHead;
	}





	public String getMachineryDischarge() {
		return machineryDischarge;
	}





	public void setMachineryDischarge(String machineryDischarge) {
		this.machineryDischarge = machineryDischarge;
	}





	public long getMachineryQuantity() {
		return machineryQuantity;
	}





	public void setMachineryQuantity(long machineryQuantity) {
		this.machineryQuantity = machineryQuantity;
	}





	public double getMachineryCost() {
		return machineryCost;
	}





	public void setMachineryCost(double machineryCost) {
		this.machineryCost = machineryCost;
	}





	public long getGensetPannelQuantity() {
		return gensetPannelQuantity;
	}





	public void setGensetPannelQuantity(long gensetPannelQuantity) {
		this.gensetPannelQuantity = gensetPannelQuantity;
	}





	public double getGensetPannelCost() {
		return gensetPannelCost;
	}





	public void setGensetPannelCost(double gensetPannelCost) {
		this.gensetPannelCost = gensetPannelCost;
	}





	public long getGensetCapacity() {
		return gensetCapacity;
	}





	public void setGensetCapacity(long gensetCapacity) {
		this.gensetCapacity = gensetCapacity;
	}





	public long getGensetQuantity() {
		return gensetQuantity;
	}





	public void setGensetQuantity(long gensetQuantity) {
		this.gensetQuantity = gensetQuantity;
	}





	public double getGensetElectricConnectionCost() {
		return gensetElectricConnectionCost;
	}





	public void setGensetElectricConnectionCost(double gensetElectricConnectionCost) {
		this.gensetElectricConnectionCost = gensetElectricConnectionCost;
	}





	public String getGensetElectricConnectionLoad() {
		return gensetElectricConnectionLoad;
	}





	public void setGensetElectricConnectionLoad(String gensetElectricConnectionLoad) {
		this.gensetElectricConnectionLoad = gensetElectricConnectionLoad;
	}





	public long getRestroomQuantity() {
		return restroomQuantity;
	}





	public void setRestroomQuantity(long restroomQuantity) {
		this.restroomQuantity = restroomQuantity;
	}





	public double getRestroomCost() {
		return restroomCost;
	}





	public void setRestroomCost(double restroomCost) {
		this.restroomCost = restroomCost;
	}





	public String getSewerageTechnology() {
		return sewerageTechnology;
	}





	public void setSewerageTechnology(String sewerageTechnology) {
		this.sewerageTechnology = sewerageTechnology;
	}





	public double getSewerageCost() {
		return sewerageCost;
	}





	public void setSewerageCost(double sewerageCost) {
		this.sewerageCost = sewerageCost;
	}





	public double getSiteDevelopmentCost() {
		return siteDevelopmentCost;
	}





	public void setSiteDevelopmentCost(double siteDevelopmentCost) {
		this.siteDevelopmentCost = siteDevelopmentCost;
	}





	public String getRisingType() {
		return risingType;
	}





	public void setRisingType(String risingType) {
		this.risingType = risingType;
	}





	public String getRisingSize() {
		return risingSize;
	}





	public void setRisingSize(String risingSize) {
		this.risingSize = risingSize;
	}





	public double getRisingCost() {
		return risingCost;
	}





	public void setRisingCost(double risingCost) {
		this.risingCost = risingCost;
	}





	public String getPumpingMachineryDischarge() {
		return pumpingMachineryDischarge;
	}





	public void setPumpingMachineryDischarge(String pumpingMachineryDischarge) {
		this.pumpingMachineryDischarge = pumpingMachineryDischarge;
	}





	public double getSludgeDryingCost() {
		return sludgeDryingCost;
	}





	public void setSludgeDryingCost(double sludgeDryingCost) {
		this.sludgeDryingCost = sludgeDryingCost;
	}





	public double getSludgeCuringCost() {
		return sludgeCuringCost;
	}





	public void setSludgeCuringCost(double sludgeCuringCost) {
		this.sludgeCuringCost = sludgeCuringCost;
	}





	public double getCompositionCost() {
		return compositionCost;
	}





	public void setCompositionCost(double compositionCost) {
		this.compositionCost = compositionCost;
	}





	public BigDecimal getTotalCostOfStructure() {
		return totalCostOfStructure;
	}





	public void setTotalCostOfStructure(BigDecimal totalCostOfStructure) {
		this.totalCostOfStructure = totalCostOfStructure;
	}





	public BigDecimal getOMcostForSevenYears() {
		return OMcostForSevenYears;
	}





	public void setOMcostForSevenYears(BigDecimal oMcostForSevenYears) {
		OMcostForSevenYears = oMcostForSevenYears;
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





	public Set<DSRSewerageSewerBean> getDsrSewerageSewerBeans() {
		return dsrSewerageSewerBeans;
	}





	public void setDsrSewerageSewerBeans(
			Set<DSRSewerageSewerBean> dsrSewerageSewerBeans) {
		this.dsrSewerageSewerBeans = dsrSewerageSewerBeans;
	}





	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}





	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}





	@Override
	public String toString() {
		return "DSRSewerageBean [schemeId=" + schemeId + ", locationId="
				+ locationId + ", totalCostPipeSewer=" + totalCostPipeSewer
				+ ", manholeSize=" + manholeSize + ", manholeQuantity="
				+ manholeQuantity + ", manholeDepthMin=" + manholeDepthMin
				+ ", manholeDepthMax=" + manholeDepthMax + ", pumpingCost="
				+ pumpingCost + ", pumpingDischarge=" + pumpingDischarge
				+ ", pumpingHead=" + pumpingHead + ", collectionStpDiameter="
				+ collectionStpDiameter + ", collectionStpCost="
				+ collectionStpCost + ", collectionIpsDimeter="
				+ collectionIpsDimeter + ", collectionIpsCost="
				+ collectionIpsCost + ", collectionIpsQuantity="
				+ collectionIpsQuantity + ", machineryType=" + machineryType
				+ ", machineryHead=" + machineryHead + ", machineryDischarge="
				+ machineryDischarge + ", machineryQuantity="
				+ machineryQuantity + ", machineryCost=" + machineryCost
				+ ", gensetPannelQuantity=" + gensetPannelQuantity
				+ ", gensetPannelCost=" + gensetPannelCost
				+ ", gensetCapacity=" + gensetCapacity + ", gensetQuantity="
				+ gensetQuantity + ", gensetElectricConnectionCost="
				+ gensetElectricConnectionCost
				+ ", gensetElectricConnectionLoad="
				+ gensetElectricConnectionLoad + ", restroomQuantity="
				+ restroomQuantity + ", restroomCost=" + restroomCost
				+ ", sewerageTechnology=" + sewerageTechnology
				+ ", sewerageCost=" + sewerageCost + ", siteDevelopmentCost="
				+ siteDevelopmentCost + ", risingType=" + risingType
				+ ", risingSize=" + risingSize + ", risingCost=" + risingCost
				+ ", pumpingMachineryDischarge=" + pumpingMachineryDischarge
				+ ", sludgeDryingCost=" + sludgeDryingCost
				+ ", sludgeCuringCost=" + sludgeCuringCost
				+ ", compositionCost=" + compositionCost
				+ ", totalCostOfStructure=" + totalCostOfStructure
				+ ", OMcostForSevenYears=" + OMcostForSevenYears
				+ ", environmentCost=" + environmentCost
				+ ", contigencyCharges=" + contigencyCharges + ", grandTotal="
				+ grandTotal + ", dsrSewerageSewerBeans="
				+ dsrSewerageSewerBeans + ", misAuditBean=" + misAuditBean
				+ "]";
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
	
	
	
}
