package com.prwss.mis.finance.voucher;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="t_finance_voucher_detail", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class VoucherDetailBean implements Serializable,Comparable<VoucherDetailBean> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6524926802379232758L;


	@Id
	@SequenceGenerator(name="seq_t_voucherdetail_id", sequenceName="prwss_main.seq_t_voucherdetail_id")
	@GeneratedValue(generator="seq_t_voucherdetail_id", strategy=GenerationType.AUTO)
	@Column(name="id", nullable=false)
	private long id;
	
	
	@Column(name="voc_id")
	private long vocId;
	
	@Column(name="voc_type")
	private String  vocType;
	
	@Column(name="code_head_id")
	private String codeHeadId;

	@Column(name="component_id")
	private String componentId;
	
	@Column(name="sub_component_id")
	private String subComponentId;
	
	@Column(name="activity_id")
	private String activityId;
	
	@Column(name="amount")
	private BigDecimal amount;
	  
	@Column(name="remarks")
	private String remarks;
	 
	  @Embedded
		private MISAuditBean misAuditBean;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getVocId() {
		return vocId;
	}

	public void setVocId(long vocId) {
		this.vocId = vocId;
	}

	public String getVocType() {
		return vocType;
	}

	public void setVocType(String vocType) {
		this.vocType = vocType;
	}

	public String getCodeHeadId() {
		return codeHeadId;
	}

	public void setCodeHeadId(String codeHeadId) {
		this.codeHeadId = codeHeadId;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public int compareTo(VoucherDetailBean o) {
		return new Long(this.vocId).compareTo(o.vocId);
	}

}
