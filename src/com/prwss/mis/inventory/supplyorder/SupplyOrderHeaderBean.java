/**
 * 
 */
package com.prwss.mis.inventory.supplyorder;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;


@Entity
@Table(name=MISConstants.SUPPLYORDERHEADERTABLE, schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class SupplyOrderHeaderBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3468126429880254286L;
	
	@Id
	@Column(name="supply_order_header_id")
	private long supplyOrderHeaderId ;
	
	@Column(name="project_id")
	private String projectId;
	
	@Column(name="location_id")
	private String locationId;
	
	@Column(name="store_id")
	private long storeId;
	
	@Column(name="supplier_id")
	private String supplierId;
	
	@Column(name="supply_order_number")
	private String supplyOrderNumber;
	
	@Column(name="supply_order_date")
	private Date supplyOrderDate;
	
	@Embedded
	private MISAuditBean misAuditBean;
	
	
	@OneToMany(targetEntity=SupplyOrderDetailsBean.class,fetch=FetchType.EAGER,cascade = {CascadeType.ALL})
	@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	@JoinColumn(name="supply_order_header_id")
	private List<SupplyOrderDetailsBean> supplyOrderDetailsBeans;
	
	

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	public long getSupplyOrderHeaderId() {
		return supplyOrderHeaderId;
	}

	public void setSupplyOrderHeaderId(long supplyOrderHeaderId) {
		this.supplyOrderHeaderId = supplyOrderHeaderId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplyOrderNumber() {
		return supplyOrderNumber;
	}

	public void setSupplyOrderNumber(String supplyOrderNumber) {
		this.supplyOrderNumber = supplyOrderNumber;
	}

	public Date getSupplyOrderDate() {
		return supplyOrderDate;
	}

	public void setSupplyOrderDate(Date supplyOrderDate) {
		this.supplyOrderDate = supplyOrderDate;
	}

	public List<SupplyOrderDetailsBean> getSupplyOrderDetailsBeans() {
		return supplyOrderDetailsBeans;
	}

	public void setSupplyOrderDetailsBeans(
			List<SupplyOrderDetailsBean> supplyOrderDetailsBeans) {
		this.supplyOrderDetailsBeans = supplyOrderDetailsBeans;
	}

	@Override
	public String toString() {
		return "SupplyOrderHeaderBean [supplyOrderHeaderId="
				+ supplyOrderHeaderId + ", projectId=" + projectId
				+ ", locationId=" + locationId + ", storeId=" + storeId
				+ ", supplierId=" + supplierId + ", supplyOrderNumber="
				+ supplyOrderNumber + ", supplyOrderDate=" + supplyOrderDate
				+ ", misAuditBean=" + misAuditBean
				+ ", supplyOrderDetailsBeans=" + supplyOrderDetailsBeans + "]";
	}

	
	
	

}
