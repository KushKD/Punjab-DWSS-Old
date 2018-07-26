/**
 * 
 */
package com.prwss.mis.pmm.DSRPonds.struts;

import java.math.BigDecimal;

import org.apache.struts.validator.ValidatorForm;

/**
 * @author pjha
 *
 */
public class DSRPondsForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7382864186016757557L;
	private String schemeId;
	private String  locationId;
	private String villageId;
	private String blockId;
	private String  jcbHours;
	private double  jcbHoursCost;
	private String  tractorsHours;
	private double  tractorsHoursCost;
	private String  labourMandays;
	private double  labourMandaysCost;
	private double  unforeseenCost;
	private long  haudisQuantity;
	private double  haudisCost;
	private String  pipelineLength;
	private double  pipelineCost;
	private double  otheralliedCost;
	private double  drainsCost;
	private String dsrDate;
	private BigDecimal  totalCost;
	
	
	
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



	public String getJcbHours() {
		return jcbHours;
	}



	public void setJcbHours(String jcbHours) {
		this.jcbHours = jcbHours;
	}



	public double getJcbHoursCost() {
		return jcbHoursCost;
	}



	public void setJcbHoursCost(double jcbHoursCost) {
		this.jcbHoursCost = jcbHoursCost;
	}



	public String getTractorsHours() {
		return tractorsHours;
	}



	public void setTractorsHours(String tractorsHours) {
		this.tractorsHours = tractorsHours;
	}



	public double getTractorsHoursCost() {
		return tractorsHoursCost;
	}



	public void setTractorsHoursCost(double tractorsHoursCost) {
		this.tractorsHoursCost = tractorsHoursCost;
	}



	public String getLabourMandays() {
		return labourMandays;
	}



	public void setLabourMandays(String labourMandays) {
		this.labourMandays = labourMandays;
	}



	public double getLabourMandaysCost() {
		return labourMandaysCost;
	}



	public void setLabourMandaysCost(double labourMandaysCost) {
		this.labourMandaysCost = labourMandaysCost;
	}



	public double getUnforeseenCost() {
		return unforeseenCost;
	}



	public void setUnforeseenCost(double unforeseenCost) {
		this.unforeseenCost = unforeseenCost;
	}



	public long getHaudisQuantity() {
		return haudisQuantity;
	}



	public void setHaudisQuantity(long haudisQuantity) {
		this.haudisQuantity = haudisQuantity;
	}



	public double getHaudisCost() {
		return haudisCost;
	}



	public void setHaudisCost(double haudisCost) {
		this.haudisCost = haudisCost;
	}



	public String getPipelineLength() {
		return pipelineLength;
	}



	public void setPipelineLength(String pipelineLength) {
		this.pipelineLength = pipelineLength;
	}



	public double getPipelineCost() {
		return pipelineCost;
	}



	public void setPipelineCost(double pipelineCost) {
		this.pipelineCost = pipelineCost;
	}



	public double getOtheralliedCost() {
		return otheralliedCost;
	}



	public void setOtheralliedCost(double otheralliedCost) {
		this.otheralliedCost = otheralliedCost;
	}



	public double getDrainsCost() {
		return drainsCost;
	}



	public void setDrainsCost(double drainsCost) {
		this.drainsCost = drainsCost;
	}



	public String getDsrDate() {
		return dsrDate;
	}



	public void setDsrDate(String dsrDate) {
		this.dsrDate = dsrDate;
	}



	public BigDecimal getTotalCost() {
		return totalCost;
	}



	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}



	@Override
	public String toString() {
		return "DSRPondsForm [schemeId=" + schemeId + ", locationId="
				+ locationId + ", villageId=" + villageId + ", blockId="
				+ blockId + ", jcbHours=" + jcbHours + ", jcbHoursCost="
				+ jcbHoursCost + ", tractorsHours=" + tractorsHours
				+ ", tractorsHoursCost=" + tractorsHoursCost
				+ ", labourMandays=" + labourMandays + ", labourMandaysCost="
				+ labourMandaysCost + ", unforeseenCost=" + unforeseenCost
				+ ", haudisQuantity=" + haudisQuantity + ", haudisCost="
				+ haudisCost + ", pipelineLength=" + pipelineLength
				+ ", pipelineCost=" + pipelineCost + ", otheralliedCost="
				+ otheralliedCost + ", drainsCost=" + drainsCost
				+ ", totalCost=" + totalCost + "]";
	}
	

	
	
}
