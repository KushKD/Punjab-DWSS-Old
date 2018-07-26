package com.prwss.mis.masters.store.dao;

import java.io.Serializable;

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
@Table(name="mst_store", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class StoreBean implements Serializable,Comparable<StoreBean> {

	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = 1129621040625497230L;

	@Id	
	@GeneratedValue(generator = "seq_store_id", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "seq_store_id", sequenceName = "prwss_main.seq_store_id")
	@Column(name="store_id")
	private long storeId;
	
	@Column(name="store_name")
	private String storeName;
	
	@Column(name="store_address")
	private String storeAddress;
	
	@Column(name="location_id")
	private String locationId;
	
	@Embedded
	private MISAuditBean misAuditBean;

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public String toString() {
		return "StoreBean [storeId=" + storeId + ", storeName=" + storeName + ", storeAddress=" + storeAddress
				+ ", locationId=" + locationId + ", misAuditBean=" + misAuditBean + "]";
	}

	@Override
	public int compareTo(StoreBean o) {
		return new Long(this.getStoreId()).compareTo(o.getStoreId());
	}
	
	
}
