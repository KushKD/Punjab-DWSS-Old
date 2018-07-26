/**
 * 
 */
package com.prwss.mis.pmm.DSRBuilding.struts;

import java.math.BigDecimal;

import org.apache.struts.validator.ValidatorForm;

/**
 * @author pjha
 *
 */
public class DSRBuildingForm extends ValidatorForm  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6378262173253238113L;
	private String schemeId;
	private String  locationId;
	private String villageId;
	private String blockId;
	private String dsrDate;
	private long estatePipelineLength;
	private double estatePipelineCost;
	private double headworksPumpchamberCost;
	private String headworksHead;
	private String headworksDischarge;
	private double headworksCost;
	private String ohsrCapacity;
	private double ohsrCost;
	private String  internalPipelineLength;
	private double  internalPipelineCost;
	private long  sanitaryOwcEwcQuantity;
	private double  sanitaryOwcEwcCost;
	private long  sanitaryWashbasinQuantity;
	private double  sanitaryWashbasinCost;
	private long  sanitaryUrinalQuantity;
	private double  sanitaryUrinalCost;
	private String sewerageLength;
	private double sewerageCost;
	private String stormSewerageLength;
	private double stormSewerageCost;
	private double rainwaterCost;
	private double firesystemCost;
	private long windowacQuantity;
	private double windowacCost;
	private long splitacQuantity;
	private double splitacCost;
	private double acplantCost;
	private double departmentalCharges;
	private double contigencyCharges;
	private BigDecimal totalCost;
	
	
	public String getDsrDate() {
		return dsrDate;
	}
	public void setDsrDate(String dsrDate) {
		this.dsrDate = dsrDate;
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
	public long getEstatePipelineLength() {
		return estatePipelineLength;
	}
	public void setEstatePipelineLength(long estatePipelineLength) {
		this.estatePipelineLength = estatePipelineLength;
	}
	public double getEstatePipelineCost() {
		return estatePipelineCost;
	}
	public void setEstatePipelineCost(double estatePipelineCost) {
		this.estatePipelineCost = estatePipelineCost;
	}
	public double getHeadworksPumpchamberCost() {
		return headworksPumpchamberCost;
	}
	public void setHeadworksPumpchamberCost(double headworksPumpchamberCost) {
		this.headworksPumpchamberCost = headworksPumpchamberCost;
	}
	public String getHeadworksHead() {
		return headworksHead;
	}
	public void setHeadworksHead(String headworksHead) {
		this.headworksHead = headworksHead;
	}
	public String getHeadworksDischarge() {
		return headworksDischarge;
	}
	public void setHeadworksDischarge(String headworksDischarge) {
		this.headworksDischarge = headworksDischarge;
	}
	public double getHeadworksCost() {
		return headworksCost;
	}
	public void setHeadworksCost(double headworksCost) {
		this.headworksCost = headworksCost;
	}
	public String getOhsrCapacity() {
		return ohsrCapacity;
	}
	public void setOhsrCapacity(String ohsrCapacity) {
		this.ohsrCapacity = ohsrCapacity;
	}
	public double getOhsrCost() {
		return ohsrCost;
	}
	public void setOhsrCost(double ohsrCost) {
		this.ohsrCost = ohsrCost;
	}
	public String getInternalPipelineLength() {
		return internalPipelineLength;
	}
	public void setInternalPipelineLength(String internalPipelineLength) {
		this.internalPipelineLength = internalPipelineLength;
	}
	public double getInternalPipelineCost() {
		return internalPipelineCost;
	}
	public void setInternalPipelineCost(double internalPipelineCost) {
		this.internalPipelineCost = internalPipelineCost;
	}
	public long getSanitaryOwcEwcQuantity() {
		return sanitaryOwcEwcQuantity;
	}
	public void setSanitaryOwcEwcQuantity(long sanitaryOwcEwcQuantity) {
		this.sanitaryOwcEwcQuantity = sanitaryOwcEwcQuantity;
	}
	public double getSanitaryOwcEwcCost() {
		return sanitaryOwcEwcCost;
	}
	public void setSanitaryOwcEwcCost(double sanitaryOwcEwcCost) {
		this.sanitaryOwcEwcCost = sanitaryOwcEwcCost;
	}
	public long getSanitaryWashbasinQuantity() {
		return sanitaryWashbasinQuantity;
	}
	public void setSanitaryWashbasinQuantity(long sanitaryWashbasinQuantity) {
		this.sanitaryWashbasinQuantity = sanitaryWashbasinQuantity;
	}
	public double getSanitaryWashbasinCost() {
		return sanitaryWashbasinCost;
	}
	public void setSanitaryWashbasinCost(double sanitaryWashbasinCost) {
		this.sanitaryWashbasinCost = sanitaryWashbasinCost;
	}
	public long getSanitaryUrinalQuantity() {
		return sanitaryUrinalQuantity;
	}
	public void setSanitaryUrinalQuantity(long sanitaryUrinalQuantity) {
		this.sanitaryUrinalQuantity = sanitaryUrinalQuantity;
	}
	public double getSanitaryUrinalCost() {
		return sanitaryUrinalCost;
	}
	public void setSanitaryUrinalCost(double sanitaryUrinalCost) {
		this.sanitaryUrinalCost = sanitaryUrinalCost;
	}
	public String getSewerageLength() {
		return sewerageLength;
	}
	public void setSewerageLength(String sewerageLength) {
		this.sewerageLength = sewerageLength;
	}
	public double getSewerageCost() {
		return sewerageCost;
	}
	public void setSewerageCost(double sewerageCost) {
		this.sewerageCost = sewerageCost;
	}
	public String getStormSewerageLength() {
		return stormSewerageLength;
	}
	public void setStormSewerageLength(String stormSewerageLength) {
		this.stormSewerageLength = stormSewerageLength;
	}
	public double getStormSewerageCost() {
		return stormSewerageCost;
	}
	public void setStormSewerageCost(double stormSewerageCost) {
		this.stormSewerageCost = stormSewerageCost;
	}
	public double getRainwaterCost() {
		return rainwaterCost;
	}
	public void setRainwaterCost(double rainwaterCost) {
		this.rainwaterCost = rainwaterCost;
	}
	public double getFiresystemCost() {
		return firesystemCost;
	}
	public void setFiresystemCost(double firesystemCost) {
		this.firesystemCost = firesystemCost;
	}
	public long getWindowacQuantity() {
		return windowacQuantity;
	}
	public void setWindowacQuantity(long windowacQuantity) {
		this.windowacQuantity = windowacQuantity;
	}
	public double getWindowacCost() {
		return windowacCost;
	}
	public void setWindowacCost(double windowacCost) {
		this.windowacCost = windowacCost;
	}
	public long getSplitacQuantity() {
		return splitacQuantity;
	}
	public void setSplitacQuantity(long splitacQuantity) {
		this.splitacQuantity = splitacQuantity;
	}
	public double getSplitacCost() {
		return splitacCost;
	}
	public void setSplitacCost(double splitacCost) {
		this.splitacCost = splitacCost;
	}
	public double getAcplantCost() {
		return acplantCost;
	}
	public void setAcplantCost(double acplantCost) {
		this.acplantCost = acplantCost;
	}
	public double getDepartmentalCharges() {
		return departmentalCharges;
	}
	public void setDepartmentalCharges(double departmentalCharges) {
		this.departmentalCharges = departmentalCharges;
	}
	public double getContigencyCharges() {
		return contigencyCharges;
	}
	public void setContigencyCharges(double contigencyCharges) {
		this.contigencyCharges = contigencyCharges;
	}
	public BigDecimal getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}
	
	
	
	
	
	}

