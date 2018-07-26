package com.prwss.mis.pmm.alert.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="t_alert_checklist", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class AlertChecklistBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5072977305398720648L;
	@Id
	@Column(name="location_id", nullable=false)
	private String locationId;
	
	@Id
	@Column(name="t_month", nullable=false)
	private String tMonth;
	
	@Id
	@Column(name="t_year")
	private long tYear;
	
	@Column(name="admin_approval")
	private boolean adminApproval;
	
	@Column(name="scheme_commissioning")
	private boolean schemeCommissioning;
	
	@Column(name="water_connection")
	private boolean waterConnection;
	
	@Column(name="household")
	private boolean household;
	
	@Column(name="operation_sustainability")
	private boolean operationSustainability;
	
	@Column(name="iec_training")
	private boolean iecTraining;
	
	@Column(name="beneficiay_share")
	private boolean beneficiayShare;
	
	@Column(name="spmc_payment_voucher")
	private boolean spmcPaymentVoucher;
	
	@Column(name="dpmc_payment_voucher")
	private boolean dpmcPaymentVoucher;
	
	@Column(name="gpwsc_register_entry")
	private boolean gpwscRegisterEntry;
	

	@Column(name="updation_procurement_plan")
	private boolean updationProcurementPlan;
	
	@Column(name="mail_status")
	private String mailStatus;
	
	@Embedded
	private MISAuditBean misAuditBean;

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String gettMonth() {
		return tMonth;
	}

	public void settMonth(String tMonth) {
		this.tMonth = tMonth;
	}

	public long gettYear() {
		return tYear;
	}

	public void settYear(long tYear) {
		this.tYear = tYear;
	}

	public boolean isAdminApproval() {
		return adminApproval;
	}

	public void setAdminApproval(boolean adminApproval) {
		this.adminApproval = adminApproval;
	}

	public boolean isSchemeCommissioning() {
		return schemeCommissioning;
	}

	public void setSchemeCommissioning(boolean schemeCommissioning) {
		this.schemeCommissioning = schemeCommissioning;
	}

	public boolean isWaterConnection() {
		return waterConnection;
	}

	public void setWaterConnection(boolean waterConnection) {
		this.waterConnection = waterConnection;
	}

	public boolean isHousehold() {
		return household;
	}

	public void setHousehold(boolean household) {
		this.household = household;
	}

	public boolean isOperationSustainability() {
		return operationSustainability;
	}

	public void setOperationSustainability(boolean operationSustainability) {
		this.operationSustainability = operationSustainability;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	public void setMailStatus(String mailStatus) {
		this.mailStatus = mailStatus;
	}

	public String getMailStatus() {
		return mailStatus;
	}

	public boolean isIecTraining() {
		return iecTraining;
	}

	public void setIecTraining(boolean iecTraining) {
		this.iecTraining = iecTraining;
	}

	public boolean isBeneficiayShare() {
		return beneficiayShare;
	}

	public void setBeneficiayShare(boolean beneficiayShare) {
		this.beneficiayShare = beneficiayShare;
	}

	public boolean isSpmcPaymentVoucher() {
		return spmcPaymentVoucher;
	}

	public void setSpmcPaymentVoucher(boolean spmcPaymentVoucher) {
		this.spmcPaymentVoucher = spmcPaymentVoucher;
	}

	public boolean isDpmcPaymentVoucher() {
		return dpmcPaymentVoucher;
	}

	public void setDpmcPaymentVoucher(boolean dpmcPaymentVoucher) {
		this.dpmcPaymentVoucher = dpmcPaymentVoucher;
	}

	public boolean isGpwscRegisterEntry() {
		return gpwscRegisterEntry;
	}

	public void setGpwscRegisterEntry(boolean gpwscRegisterEntry) {
		this.gpwscRegisterEntry = gpwscRegisterEntry;
	}

	public boolean isUpdationProcurementPlan() {
		return updationProcurementPlan;
	}

	public void setUpdationProcurementPlan(boolean updationProcurementPlan) {
		this.updationProcurementPlan = updationProcurementPlan;
	}


}
