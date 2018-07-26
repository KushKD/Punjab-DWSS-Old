/**
 * 
 */
package com.prwss.mis.finance.requestloc.struts;

import java.math.BigDecimal;

import org.apache.struts.validator.ValidatorForm;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author PJHA
 *
 */
public class RequestLOCForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7145900554617509536L;
	
	
	private String programId;
	private String locationId;
	private String locationName;
	private String requestToLocationId;
	private String schemeId;
	private String blockId;
	private String villageId;
	private Datagrid requestlocDatagrid;
	private long locRequestNo;
	private String requestDate;
	private String componentId;
	private BigDecimal componentA;
	private BigDecimal componentB;
	private BigDecimal componentC;
	private BigDecimal amountFD;
	private Datagrid locActivityDatagrid;
	private String subComponentId;
	private String activityId;
	
	
	public String getBlockId() {
		return blockId;
	}
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}
	public String getVillageId() {
		return villageId;
	}
	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}
	public String getComponentId() {
		return componentId;
	}
	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}
	public String getSubComponentId() {
		return subComponentId;
	}
	public void setSubComponentId(String subComponentId) {
		this.subComponentId = subComponentId;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public Datagrid getLocActivityDatagrid() {
		return locActivityDatagrid;
	}
	public void setLocActivityDatagrid(Datagrid locActivityDatagrid) {
		this.locActivityDatagrid = locActivityDatagrid;
	}
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	public String getProgramId() {
		return programId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public Datagrid getRequestlocDatagrid() {
		return requestlocDatagrid;
	}
	public void setRequestlocDatagrid(Datagrid requestlocDatagrid) {
		this.requestlocDatagrid = requestlocDatagrid;
	}
	public long getLocRequestNo() {
		return locRequestNo;
	}
	public void setLocRequestNo(long locRequestNo) {
		this.locRequestNo = locRequestNo;
	}
	public String getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	public BigDecimal getComponentA() {
		return componentA;
	}
	public void setComponentA(BigDecimal componentA) {
		this.componentA = componentA;
	}
	public BigDecimal getComponentB() {
		return componentB;
	}
	public void setComponentB(BigDecimal componentB) {
		this.componentB = componentB;
	}
	public BigDecimal getComponentC() {
		return componentC;
	}
	public void setComponentC(BigDecimal componentC) {
		this.componentC = componentC;
	}
	public void setRequestToLocationId(String requestToLocationId) {
		this.requestToLocationId = requestToLocationId;
	}
	public String getRequestToLocationId() {
		return requestToLocationId;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setAmountFD(BigDecimal amountFD) {
		this.amountFD = amountFD;
	}
	public BigDecimal getAmountFD() {
		return amountFD;
	}
	
	

}
