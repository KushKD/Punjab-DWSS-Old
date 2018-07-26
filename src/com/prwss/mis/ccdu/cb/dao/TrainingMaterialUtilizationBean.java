package com.prwss.mis.ccdu.cb.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.masters.item.dao.ItemBean;

@Entity
@Table(name="t_ccdu_cb_training_material_utilization", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class TrainingMaterialUtilizationBean implements Serializable {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = 3595383910930873151L;

	@Id
	@Column(name="cb_progress_id", nullable=false)
	private long cbProgressId;
	
	@Id
	@ManyToOne(targetEntity=ItemBean.class)
	@JoinColumn(name="item_id")
	private ItemBean itemBean;
	
	@Column(name="quantity")
	private long quantity;
	
	@Embedded
	private MISAuditBean misAuditBean;

	public long getCbProgressId() {
		return cbProgressId;
	}

	public void setCbProgressId(long cbProgressId) {
		this.cbProgressId = cbProgressId;
	}

	public ItemBean getItemBean() {
		return itemBean;
	}

	public void setItemBean(ItemBean itemBean) {
		this.itemBean = itemBean;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public String toString() {
		return "TrainingMaterialUtilizationBean [cbProgressId=" + cbProgressId + ", itemBean=" + itemBean
				+ ", quantity=" + quantity + ", misAuditBean=" + misAuditBean + "]";
	}


}
