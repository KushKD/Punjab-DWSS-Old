/**
 * 
 */
package com.prwss.mis.pmm.dsrtubewell.struts;

import java.math.BigDecimal;

import org.apache.struts.validator.ValidatorForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author pjha
 *
 */
public class DSRTubeWellForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2103173087987941795L;
	private String schemeId;
	private String locationId;
	private String villageId;
	private String blockId;
	private String dsrDate;
	private String tubewellSize;
	private long tubewellDepth;
	private long tubewellCost;
	private String pumpSelect;
	private long pumpQuantity;
	private long pumpTotalCost;
	private String pumpingCentrifugal;
	private long pumpingDischarge;
	private String pumpingSubmersible;
	private long pumpingQuantity;
	private long pumpingHead;
	private BigDecimal pumpingCost;
	private BigDecimal costOfElectricConnection;
	private BigDecimal costOfCIFitting;
	private long vnotchQuantity;
	private long vnotchCost;
	private String disinfectionType;
	private String disinfectionQuantity;
	private long disinfectionCost;
	private String rccCapacity;
	private long rccQuantity;
	private long rccFsl;
	private long rccCost;
	private long electronicQuantity;
	private long electronicCost;
	private long plinthCost;
	private String  risingSize;
	private String  risingType;
	private long  risingLength;
	private long  risingCost;
	private BigDecimal totalCostTopographical;
	private BigDecimal totalCostDevelopmentWater;
	private String  bulkSize;
	private long  bulkCount;
	private BigDecimal  bulkTotalCost;
	private BigDecimal  distributionTotalCost;
	private String  slueiceSize;
	private BigDecimal  slueiceCost;
	private BigDecimal  totalCostStructure;
	private BigDecimal  percapitaCost;
	private BigDecimal  environmentCost;
	private BigDecimal  grandTotal;
	private long contigencyCharges;//to be added at BL
	private Datagrid dsrTubeWellDistributionGrid;
	private String pumpingCentrifugal1;
	private long pumpingDischarge1;
	private long pumpingQuantity1;
	private String pumpingSubmersible1;
	private long pumpingHead1;
	private BigDecimal pumpingCost1;
	private BigDecimal costOfElectricConnection1;
	private BigDecimal costOfCIFitting1;
	private long vnotchQuantity1;
	private long vnotchCost1;
	private String tubewellSize1;
	private long tubewellDepth1;
	private long tubewellCost1;
	private String rccCapacity1;
	private long rccQuantity1;
	private long rccFsl1;
	private long rccCost1;
	private long electronicQuantity1;
	private long electronicCost1;
	private long plinthCost1;
	private String  risingSize1;
	private String  risingType1;
	private long  risingLength1;
	private long  risingCost1;
	private BigDecimal totalCostTopographical1;
	private BigDecimal totalCostDevelopmentWater1;
	
	
	
	public String getRccCapacity1() {
		return rccCapacity1;
	}
	public void setRccCapacity1(String rccCapacity1) {
		this.rccCapacity1 = rccCapacity1;
	}
	public long getRccQuantity1() {
		return rccQuantity1;
	}
	public void setRccQuantity1(long rccQuantity1) {
		this.rccQuantity1 = rccQuantity1;
	}
	public long getRccFsl1() {
		return rccFsl1;
	}
	public void setRccFsl1(long rccFsl1) {
		this.rccFsl1 = rccFsl1;
	}
	public long getRccCost1() {
		return rccCost1;
	}
	public void setRccCost1(long rccCost1) {
		this.rccCost1 = rccCost1;
	}
	public long getElectronicQuantity1() {
		return electronicQuantity1;
	}
	public void setElectronicQuantity1(long electronicQuantity1) {
		this.electronicQuantity1 = electronicQuantity1;
	}
	public long getElectronicCost1() {
		return electronicCost1;
	}
	public void setElectronicCost1(long electronicCost1) {
		this.electronicCost1 = electronicCost1;
	}
	public long getPlinthCost1() {
		return plinthCost1;
	}
	public void setPlinthCost1(long plinthCost1) {
		this.plinthCost1 = plinthCost1;
	}
	public String getRisingSize1() {
		return risingSize1;
	}
	public void setRisingSize1(String risingSize1) {
		this.risingSize1 = risingSize1;
	}
	public String getRisingType1() {
		return risingType1;
	}
	public void setRisingType1(String risingType1) {
		this.risingType1 = risingType1;
	}
	public long getRisingLength1() {
		return risingLength1;
	}
	public void setRisingLength1(long risingLength1) {
		this.risingLength1 = risingLength1;
	}
	public long getRisingCost1() {
		return risingCost1;
	}
	public void setRisingCost1(long risingCost1) {
		this.risingCost1 = risingCost1;
	}
	public BigDecimal getTotalCostTopographical1() {
		return totalCostTopographical1;
	}
	public void setTotalCostTopographical1(BigDecimal totalCostTopographical1) {
		this.totalCostTopographical1 = totalCostTopographical1;
	}
	public BigDecimal getTotalCostDevelopmentWater1() {
		return totalCostDevelopmentWater1;
	}
	public void setTotalCostDevelopmentWater1(BigDecimal totalCostDevelopmentWater1) {
		this.totalCostDevelopmentWater1 = totalCostDevelopmentWater1;
	}
	public String getTubewellSize1() {
		return tubewellSize1;
	}
	public void setTubewellSize1(String tubewellSize1) {
		this.tubewellSize1 = tubewellSize1;
	}
	public long getTubewellDepth1() {
		return tubewellDepth1;
	}
	public void setTubewellDepth1(long tubewellDepth1) {
		this.tubewellDepth1 = tubewellDepth1;
	}
	public long getTubewellCost1() {
		return tubewellCost1;
	}
	public void setTubewellCost1(long tubewellCost1) {
		this.tubewellCost1 = tubewellCost1;
	}
	public String getPumpingCentrifugal1() {
		return pumpingCentrifugal1;
	}
	public void setPumpingCentrifugal1(String pumpingCentrifugal1) {
		this.pumpingCentrifugal1 = pumpingCentrifugal1;
	}
	public long getPumpingDischarge1() {
		return pumpingDischarge1;
	}
	public void setPumpingDischarge1(long pumpingDischarge1) {
		this.pumpingDischarge1 = pumpingDischarge1;
	}
	public long getPumpingQuantity1() {
		return pumpingQuantity1;
	}
	public void setPumpingQuantity1(long pumpingQuantity1) {
		this.pumpingQuantity1 = pumpingQuantity1;
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
	public BigDecimal getPumpingCost1() {
		return pumpingCost1;
	}
	public void setPumpingCost1(BigDecimal pumpingCost1) {
		this.pumpingCost1 = pumpingCost1;
	}
	public BigDecimal getCostOfElectricConnection1() {
		return costOfElectricConnection1;
	}
	public void setCostOfElectricConnection1(BigDecimal costOfElectricConnection1) {
		this.costOfElectricConnection1 = costOfElectricConnection1;
	}
	public BigDecimal getCostOfCIFitting1() {
		return costOfCIFitting1;
	}
	public void setCostOfCIFitting1(BigDecimal costOfCIFitting1) {
		this.costOfCIFitting1 = costOfCIFitting1;
	}
	public long getVnotchQuantity1() {
		return vnotchQuantity1;
	}
	public void setVnotchQuantity1(long vnotchQuantity1) {
		this.vnotchQuantity1 = vnotchQuantity1;
	}
	public long getVnotchCost1() {
		return vnotchCost1;
	}
	public void setVnotchCost1(long vnotchCost1) {
		this.vnotchCost1 = vnotchCost1;
	}	
	public String getDsrDate() {
		return dsrDate;
	}
	public void setDsrDate(String dsrDate) {
		this.dsrDate = dsrDate;
	}
	public long getContigencyCharges() {
		return contigencyCharges;
	}
	public void setContigencyCharges(long contigencyCharges) {
		this.contigencyCharges = contigencyCharges;
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
	public String getTubewellSize() {
		return tubewellSize;
	}
	public void setTubewellSize(String tubewellSize) {
		this.tubewellSize = tubewellSize;
	}
	public long getTubewellDepth() {
		return tubewellDepth;
	}
	public void setTubewellDepth(long tubewellDepth) {
		this.tubewellDepth = tubewellDepth;
	}
	public long getTubewellCost() {
		return tubewellCost;
	}
	public void setTubewellCost(long tubewellCost) {
		this.tubewellCost = tubewellCost;
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
	public long getPumpTotalCost() {
		return pumpTotalCost;
	}
	public void setPumpTotalCost(long pumpTotalCost) {
		this.pumpTotalCost = pumpTotalCost;
	}
	public String getPumpingCentrifugal() {
		return pumpingCentrifugal;
	}
	public void setPumpingCentrifugal(String pumpingCentrifugal) {
		this.pumpingCentrifugal = pumpingCentrifugal;
	}
	public long getPumpingDischarge() {
		return pumpingDischarge;
	}
	public void setPumpingDischarge(long pumpingDischarge) {
		this.pumpingDischarge = pumpingDischarge;
	}
	public String getPumpingSubmersible() {
		return pumpingSubmersible;
	}
	public void setPumpingSubmersible(String pumpingSubmersible) {
		this.pumpingSubmersible = pumpingSubmersible;
	}
	public long getPumpingQuantity() {
		return pumpingQuantity;
	}
	public void setPumpingQuantity(long pumpingQuantity) {
		this.pumpingQuantity = pumpingQuantity;
	}
	public long getPumpingHead() {
		return pumpingHead;
	}
	public void setPumpingHead(long pumpingHead) {
		this.pumpingHead = pumpingHead;
	}
	public BigDecimal getPumpingCost() {
		return pumpingCost;
	}
	public void setPumpingCost(BigDecimal pumpingCost) {
		this.pumpingCost = pumpingCost;
	}
	public BigDecimal getCostOfElectricConnection() {
		return costOfElectricConnection;
	}
	public void setCostOfElectricConnection(BigDecimal costOfElectricConnection) {
		this.costOfElectricConnection = costOfElectricConnection;
	}
	public BigDecimal getCostOfCIFitting() {
		return costOfCIFitting;
	}
	public void setCostOfCIFitting(BigDecimal costOfCIFitting) {
		this.costOfCIFitting = costOfCIFitting;
	}
	public long getVnotchQuantity() {
		return vnotchQuantity;
	}
	public void setVnotchQuantity(long vnotchQuantity) {
		this.vnotchQuantity = vnotchQuantity;
	}
	public long getVnotchCost() {
		return vnotchCost;
	}
	public void setVnotchCost(long vnotchCost) {
		this.vnotchCost = vnotchCost;
	}
	public String getDisinfectionType() {
		return disinfectionType;
	}
	public void setDisinfectionType(String disinfectionType) {
		this.disinfectionType = disinfectionType;
	}
	public String getDisinfectionQuantity() {
		return disinfectionQuantity;
	}
	public void setDisinfectionQuantity(String disinfectionQuantity) {
		this.disinfectionQuantity = disinfectionQuantity;
	}
	public long getDisinfectionCost() {
		return disinfectionCost;
	}
	public void setDisinfectionCost(long disinfectionCost) {
		this.disinfectionCost = disinfectionCost;
	}
	public String getRccCapacity() {
		return rccCapacity;
	}
	public void setRccCapacity(String rccCapacity) {
		this.rccCapacity = rccCapacity;
	}
	public long getRccQuantity() {
		return rccQuantity;
	}
	public void setRccQuantity(long rccQuantity) {
		this.rccQuantity = rccQuantity;
	}
	public long getRccFsl() {
		return rccFsl;
	}
	public void setRccFsl(long rccFsl) {
		this.rccFsl = rccFsl;
	}
	public long getRccCost() {
		return rccCost;
	}
	public void setRccCost(long rccCost) {
		this.rccCost = rccCost;
	}
	public long getElectronicQuantity() {
		return electronicQuantity;
	}
	public void setElectronicQuantity(long electronicQuantity) {
		this.electronicQuantity = electronicQuantity;
	}
	public long getElectronicCost() {
		return electronicCost;
	}
	public void setElectronicCost(long electronicCost) {
		this.electronicCost = electronicCost;
	}
	public long getPlinthCost() {
		return plinthCost;
	}
	public void setPlinthCost(long plinthCost) {
		this.plinthCost = plinthCost;
	}
	public String getRisingSize() {
		return risingSize;
	}
	public void setRisingSize(String risingSize) {
		this.risingSize = risingSize;
	}
	public String getRisingType() {
		return risingType;
	}
	public void setRisingType(String risingType) {
		this.risingType = risingType;
	}
	public long getRisingLength() {
		return risingLength;
	}
	public void setRisingLength(long risingLength) {
		this.risingLength = risingLength;
	}
	public long getRisingCost() {
		return risingCost;
	}
	public void setRisingCost(long risingCost) {
		this.risingCost = risingCost;
	}
	public BigDecimal getTotalCostTopographical() {
		return totalCostTopographical;
	}
	public void setTotalCostTopographical(BigDecimal totalCostTopographical) {
		this.totalCostTopographical = totalCostTopographical;
	}
	public BigDecimal getTotalCostDevelopmentWater() {
		return totalCostDevelopmentWater;
	}
	public void setTotalCostDevelopmentWater(BigDecimal totalCostDevelopmentWater) {
		this.totalCostDevelopmentWater = totalCostDevelopmentWater;
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
	public BigDecimal getBulkTotalCost() {
		return bulkTotalCost;
	}
	public void setBulkTotalCost(BigDecimal bulkTotalCost) {
		this.bulkTotalCost = bulkTotalCost;
	}
	public BigDecimal getDistributionTotalCost() {
		return distributionTotalCost;
	}
	public void setDistributionTotalCost(BigDecimal distributionTotalCost) {
		this.distributionTotalCost = distributionTotalCost;
	}
	public String getSlueiceSize() {
		return slueiceSize;
	}
	public void setSlueiceSize(String slueiceSize) {
		this.slueiceSize = slueiceSize;
	}
	public BigDecimal getSlueiceCost() {
		return slueiceCost;
	}
	public void setSlueiceCost(BigDecimal slueiceCost) {
		this.slueiceCost = slueiceCost;
	}
	public BigDecimal getTotalCostStructure() {
		return totalCostStructure;
	}
	public void setTotalCostStructure(BigDecimal totalCostStructure) {
		this.totalCostStructure = totalCostStructure;
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
	public BigDecimal getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}
	public Datagrid getDsrTubeWellDistributionGrid() {
		return dsrTubeWellDistributionGrid;
	}
	public void setDsrTubeWellDistributionGrid(Datagrid dsrTubeWellDistributionGrid) {
		this.dsrTubeWellDistributionGrid = dsrTubeWellDistributionGrid;
	}
	

}
