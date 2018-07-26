package com.prwss.mis.ccdu.iecactivity.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.masters.item.dao.ItemBean;

@Entity
@Table(name="t_ccdu_iec_material_utilization", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class IECActivityMaterialUtilizationBean implements Serializable {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = -522938291909145346L;

	@Id
	@Column(name="iec_progress_id", nullable=false)
	private long iecProgressId;
	
	@Id
	@ManyToOne(targetEntity=ItemBean.class,fetch=FetchType.EAGER)
	@JoinColumn(name="item_id")
	private ItemBean itemBean;
	
	@Column(name="quantity")
	private long quantity;
	
	@Embedded
	private MISAuditBean misAuditBean;

	public long getIecProgressId() {
		return iecProgressId;
	}

	public void setIecProgressId(long iecProgressId) {
		this.iecProgressId = iecProgressId;
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
		return "IECActivityMaterialUtilizationBean [iecProgressId="
				+ iecProgressId + ", itemBean=" + itemBean + ", quantity="
				+ quantity + ", misAuditBean=" + misAuditBean + "]";
	}

	

}
