/**
 * 
 */
package com.prwss.mis.pmm.dsrsewerage.struts;

import java.math.BigDecimal;

import org.apache.struts.validator.ValidatorForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author Akash
 *
 */
public class DSRSewerageForm extends ValidatorForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3696979249984693636L;
	private String schemeId;
	private String locationId;
	private double  totalCostPipeSewer;
	private String manholeSize;
	private long manholeQuantity;
	private double manholeCost;//to be added in bean
	private long manholeDepthMin;
	private long manholeDepthMax;
	private double pumpingCost;
	private String pumpingDischarge;  
	private String pumpingHead;  
	private long collectionStpDiameter;
	private double collectionStpCost;
	private long collectionIpsDimeter;
	private double collectionIpsCost;
	private long collectionIpsQuantity;
	private String machineryType;
	private long machineryHead;
	private String machineryDischarge;
	private long machineryQuantity;
	private double machineryCost;
	private long gensetPannelQuantity;
	private double gensetPannelCost;
	private long gensetCapacity;
	private long gensetQuantity;
	private double gensetElectricConnectionCost;
	private String gensetElectricConnectionLoad;
	private long restroomQuantity;
	private double restroomCost;
	private String sewerageTechnology;
	private double sewerageCost;
	private double siteDevelopmentCost;
	private String risingType;
	private String risingSize;	
	private double risingCost;
	private String pumpingMachineryDischarge;
	private double sludgeDryingCost;
	private double sludgeCuringCost;
	private double compositionCost;
	private double provisionEnvironmentActivitiesCost;
	private BigDecimal totalCostOfStructure;
	private BigDecimal OMcostForSevenYears;
	private BigDecimal environmentCost;
	private BigDecimal contigencyCharges;
	private BigDecimal grandTotal;
	private Datagrid sewerageSystemDatagrid;
	private String villageId;
	private String blockId;
	private String dsrDate;
	
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
	public double getManholeCost() {
		return manholeCost;
	}
	public void setManholeCost(double manholeCost) {
		this.manholeCost = manholeCost;
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
	public double getProvisionEnvironmentActivitiesCost() {
		return provisionEnvironmentActivitiesCost;
	}
	public void setProvisionEnvironmentActivitiesCost(
			double provisionEnvironmentActivitiesCost) {
		this.provisionEnvironmentActivitiesCost = provisionEnvironmentActivitiesCost;
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
	public Datagrid getSewerageSystemDatagrid() {
		return sewerageSystemDatagrid;
	}
	public void setSewerageSystemDatagrid(Datagrid sewerageSystemDatagrid) {
		this.sewerageSystemDatagrid = sewerageSystemDatagrid;
	}
	public String getVillageId() {
		return villageId;
	}
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}
	public String getBlockId() {
		return blockId;
	}
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}
	public String getDsrDate() {
		return dsrDate;
	}
	public void setDsrDate(String dsrDate) {
		this.dsrDate = dsrDate;
	}
	
	

}
