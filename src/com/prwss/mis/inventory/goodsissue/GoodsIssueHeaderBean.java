/**
 * 
 */
package com.prwss.mis.inventory.goodsissue;

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
import com.prwss.mis.inventory.goodsissue.GoodsIssueDetailsBean;

/**
 * @author PJHA
 *
 */
@Entity
@Table(name=MISConstants.GOODSISSUEHEADERTABLE, schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class GoodsIssueHeaderBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2032814620898999733L;
	

	@Id
	@Column(name="goods_issue_header_id")
	private long goodsIssueHeaderId ;
	
	@Column(name="project_id")
	private String projectId;
	
	@Column(name="location_id")
	private String locationId;
	
	@Column(name="store_id")
	private long storeId;
	
	@Column(name="indent_number")
	private String indentNumber;
	
	@Column(name="indent_date")
	private Date indentDate;
	
	@Column(name="issued_to_type")
	private String issuedType;
	
	@Column(name="issued_to")
	private String issuedTo;
	
	@Embedded
	private MISAuditBean misAuditBean;
	
	
	@OneToMany(targetEntity=GoodsIssueDetailsBean.class,fetch=FetchType.EAGER,cascade = {CascadeType.ALL})
	@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	@JoinColumn(name="goods_issue_header_id")
	private List<GoodsIssueDetailsBean> goodsIssueDetailsBeans;


	public long getGoodsIssueHeaderId() {
		return goodsIssueHeaderId;
	}


	public void setGoodsIssueHeaderId(long goodsIssueHeaderId) {
		this.goodsIssueHeaderId = goodsIssueHeaderId;
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


	

	public String getIndentNumber() {
		return indentNumber;
	}


	public void setIndentNumber(String indentNumber) {
		this.indentNumber = indentNumber;
	}


	public Date getIndentDate() {
		return indentDate;
	}


	public void setIndentDate(Date indentDate) {
		this.indentDate = indentDate;
	}


	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}


	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}


	public List<GoodsIssueDetailsBean> getGoodsIssueDetailsBeans() {
		return goodsIssueDetailsBeans;
	}


	public void setGoodsIssueDetailsBeans(
			List<GoodsIssueDetailsBean> goodsIssueDetailsBeans) {
		this.goodsIssueDetailsBeans = goodsIssueDetailsBeans;
	}

	

	public String getIssuedType() {
		return issuedType;
	}


	public void setIssuedType(String issuedType) {
		this.issuedType = issuedType;
	}


	public String getIssuedTo() {
		return issuedTo;
	}


	public void setIssuedTo(String issuedTo) {
		this.issuedTo = issuedTo;
	}


	@Override
	public String toString() {
		return "GoodsIssueHeaderBean [goodsIssueHeaderId="
				+ goodsIssueHeaderId + ", projectId=" + projectId
				+ ", locationId=" + locationId + ", storeId=" + storeId
				 + ", indentNumber="
				+ indentNumber + ", indentDate=" + indentDate
				+ ", misAuditBean=" + misAuditBean
				+ ", goodsIssueDetailsBeans=" + goodsIssueDetailsBeans + "]";
	}
	
	

}
