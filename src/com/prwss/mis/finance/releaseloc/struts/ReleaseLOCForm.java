/**
 * 
 */
package com.prwss.mis.finance.releaseloc.struts;

import java.math.BigDecimal;

import org.apache.struts.validator.ValidatorForm;

import com.prwss.mis.common.util.MisUtility;

import fr.improve.struts.taglib.layout.datagrid.Datagrid;

/**
 * @author PJHA
 *
 */
public class ReleaseLOCForm extends ValidatorForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1747463419686915085L;
	
	private String programId;
	private String locationId;
	private String locationName;
	private String requestFromLocationId;
	private long locRequestNo;
	private String requestDate;
	private String locDate;
//	private BigDecimal componentA;
//	private BigDecimal releaseAmountA;
//	private BigDecimal componentB;
//	private BigDecimal releaseAmountB;
//	private BigDecimal componentC;
//	private BigDecimal releaseAmountC;
	private String schemeId;
	private long installmentNo;
	private String auditCompletedDate;
	private String releaseStatus;
	private BigDecimal amountFDRequest;
	private BigDecimal amountFDRelease;
	private Datagrid  releaselocDatagrid;
	private Datagrid locActivityDatagrid;
	
	
	public Datagrid getLocActivityDatagrid() {
		return locActivityDatagrid;
	}
	public void setLocActivityDatagrid(Datagrid locActivityDatagrid) {
		this.locActivityDatagrid = locActivityDatagrid;
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
	public String getLocDate() {
		return locDate;
	}
	public void setLocDate(String locDate) {
		this.locDate = locDate;
	}
	
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	public long getInstallmentNo() {
		return installmentNo;
	}
	public void setInstallmentNo(long installmentNo) {
		this.installmentNo = installmentNo;
	}
	public String getAuditCompletedDate() {
		return auditCompletedDate;
	}
	public void setAuditCompletedDate(String auditCompletedDate) {
		this.auditCompletedDate = auditCompletedDate;
	}
	public String getReleaseStatus() {
		return releaseStatus;
	}
	public void setReleaseStatus(String releaseStatus) {
		this.releaseStatus = releaseStatus;
	}
	public Datagrid getReleaselocDatagrid() {
		return releaselocDatagrid;
	}
	public void setReleaselocDatagrid(Datagrid releaselocDatagrid) {
		this.releaselocDatagrid = releaselocDatagrid;
	}
//	public BigDecimal getComponentA() {
//		return componentA;
//	}
//	public void setComponentA(BigDecimal componentA) {
//		this.componentA = componentA;
//	}
//	public BigDecimal getReleaseAmountA() {
//		return releaseAmountA;
//	}
//	public void setReleaseAmountA(BigDecimal releaseAmountA) {
//		this.releaseAmountA = releaseAmountA;
//	}
//	public BigDecimal getComponentB() {
//		return componentB;
//	}
//	public void setComponentB(BigDecimal componentB) {
//		this.componentB = componentB;
//	}
//	public BigDecimal getReleaseAmountB() {
//		return releaseAmountB;
//	}
//	public void setReleaseAmountB(BigDecimal releaseAmountB) {
//		this.releaseAmountB = releaseAmountB;
//	}
//	public BigDecimal getComponentC() {
//		return componentC;
//	}
//	public void setComponentC(BigDecimal componentC) {
//		this.componentC = componentC;
//	}
//	public BigDecimal getReleaseAmountC() {
//		return releaseAmountC;
//	}
//	public void setReleaseAmountC(BigDecimal releaseAmountC) {
//		this.releaseAmountC = releaseAmountC;
//	}
	
	
	@Override
	public String toString() {
		return "ReleaseLOCForm [programId=" + programId + ", locationId="
				+ locationId + ", locRequestNo=" + locRequestNo
				+ ", requestDate=" + requestDate + ", locDate=" + locDate
				+ ", schemeId=" + schemeId + ", installmentNo=" + installmentNo
				+ ", auditCompletedDate=" + auditCompletedDate
				+ ", releaseStatus=" + releaseStatus + ", releaselocDatagrid="
				+ releaselocDatagrid + ", locActivityDatagrid="
				+ locActivityDatagrid + "]";
	}
	public void setRequestFromLocationId(String requestFromLocationId) {
		this.requestFromLocationId = requestFromLocationId;
	}
	public String getRequestFromLocationId() {
		return requestFromLocationId;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setAmountFDRelease(BigDecimal amountFDRelease) {
		this.amountFDRelease = amountFDRelease;
	}
	public BigDecimal getAmountFDRelease() {
		return amountFDRelease;
	}
	public void setAmountFDRequest(BigDecimal amountFDRequest) {
		
			this.amountFDRequest = amountFDRequest;
		
	}
	public BigDecimal getAmountFDRequest() {
		return amountFDRequest;
	}	

}
