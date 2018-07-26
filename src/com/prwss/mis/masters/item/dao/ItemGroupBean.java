package com.prwss.mis.masters.item.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="mst_item_group", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class ItemGroupBean implements Serializable,Comparable<ItemGroupBean> {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = -8624530419920317957L;

	@Id
	@Column(name="item_group_id")
	private String itemGroupId;
	
	@Column(name="item_group_name")
	private String itemGroupName;
	
	@Embedded
	private MISAuditBean misAuditBean;

	public String getItemGroupId() {
		return itemGroupId;
	}

	public void setItemGroupId(String itemGroupId) {
		this.itemGroupId = itemGroupId;
	}

	public String getItemGroupName() {
		return itemGroupName;
	}

	public void setItemGroupName(String itemGroupName) {
		this.itemGroupName = itemGroupName;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public String toString() {
		return "ItemGroupBean [itemGroupId=" + itemGroupId + ", itemGroupName=" + itemGroupName + ", misAuditBean="
				+ misAuditBean + "]";
	}

	@Override
	public int compareTo(ItemGroupBean o) {
		return this.itemGroupId.compareTo(o.getItemGroupId());
	}
	

}
