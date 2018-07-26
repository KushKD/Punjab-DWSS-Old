/**
 * 
 */
package com.prwss.mis.pmm.dsrcanal.struts;

import java.math.BigDecimal;

import org.apache.struts.validator.ValidatorForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author Akash Aggarwal
 *
 */
public class DSRCanalForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4532042800995858836L;

	private String schemeId;
	private String villageId;
	private String dsrDate;
	private String blockId;
	private String locationId;
	private String disinfectionType;
	private double disinfectionCost;
	private long disinfectionQuantity; 
	private String pumpSelect;
	private long pumpQuantity; 
	private double pumpCost; 
	private long rccCapacity;  
	private long rccFsl;  
	private long rccQuantity; 
	private double rccCost; 
	private long electronicQuantity; 
	private double electronicCost; 
	private double plinthCost;   
	private String pumpingCentrifugal;
	private String pumpingSubmersible;
	private long pumpingHead; 
	private long pumpingDischarge; 
	private long pumpingQuantity;  
	private double pumpingCost;  
	private String piplineSize;
	private String piplineType;
	private long piplineLength;   
	private double piplineCost; 
	private double totalCostSiteLaying; 
	private double distributionCost; 
	private String slueiceSize;
	private double slueiceCost; 
	private double costOfElectricConnectionProvision; 
	private double totalCostOfTopographicalSurvey; 
	private String storageSize ;
	private long storageDepth; 
	private long storageQuantity; 
	private double storageCost; 
	private long suctionDimeter; 
	private double suctionCost; 
	private long suctionQuantity;  
	private String highType;
	private String highSize;
	private long highQuantity;   
	private double highCost;  
	private String filterType;
	private String filterSize;
	private long filterQuantity;  
	private double filterCost;  
	private String clearType;
	private long clearQuantity;   
	private double clearCost;
	private String clearSize;
	private String bulkSize;
	private long bulkCount;  
	private double bulkTotalCost;  
	private double inletTotalCost;  
	private boolean pumpingMachineryApplicable;  
	private double pumpingMachineryCost; 	
	private String pumpingMachinerySize;
	private String pumpingPower;
	private String pumpingMachineryDischarge;
	private BigDecimal totalCostOfStructure; 	
	private BigDecimal percapitaCost; 
	private BigDecimal environmentCost;   
	private BigDecimal contigencyCharges;  
	private BigDecimal grandTotal;
	private Datagrid dsrCanalInletDatagrid;
	private Datagrid dsrCanalDistributionDatagrid;
	private long rccCapacity1;  
	private long rccFsl1;  
	private long rccQuantity1; 
	private double rccCost1; 
	private String pumpingCentrifugal1;
	private String pumpingSubmersible1;
	private long pumpingHead1; 
	private long pumpingDischarge1; 
	private long pumpQuantity1; 
	private double pumpCost1; 
	
	
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
	public String getDsrDate() {
		return dsrDate;
	}
	public void setDsrDate(String dsrDate) {
		this.dsrDate = dsrDate;
	}
	public String getBlockId() {
		return blockId;
	}
	public void setBlockId(String blockId) {
		this.blockId = blockId;
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
	public double getTotalCostSiteLaying() {
		return totalCostSiteLaying;
	}
	public void setTotalCostSiteLaying(double totalCostSiteLaying) {
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
	public String getClearSize() {
		return clearSize;
	}
	public void setClearSize(String clearSize) {
		this.clearSize = clearSize;
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
	public Datagrid getDsrCanalInletDatagrid() {
		return dsrCanalInletDatagrid;
	}
	public void setDsrCanalInletDatagrid(Datagrid dsrCanalInletDatagrid) {
		this.dsrCanalInletDatagrid = dsrCanalInletDatagrid;
	}
	public Datagrid getDsrCanalDistributionDatagrid() {
		return dsrCanalDistributionDatagrid;
	}
	public void setDsrCanalDistributionDatagrid(
			Datagrid dsrCanalDistributionDatagrid) {
		this.dsrCanalDistributionDatagrid = dsrCanalDistributionDatagrid;
	}
	
	
	
	
	
	
}
