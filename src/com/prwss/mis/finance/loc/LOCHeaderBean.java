/**
 * 
 */
package com.prwss.mis.finance.loc;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.finance.loc.dao.LOCActivityBean;
import com.prwss.mis.finance.loc.dao.LOCDetailBean;

/**
 * @author PJHA
 *
 */
@Entity
@Table(name="t_finance_loc_header", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class LOCHeaderBean implements Serializable, Comparable<LOCHeaderBean> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4998436524536693884L;
	
	@Id
	@SequenceGenerator(name="seq_t_loc_id", sequenceName="prwss_main.seq_t_loc_id")
	@GeneratedValue(generator="seq_t_loc_id", strategy=GenerationType.AUTO)
	@Column(name="loc_id", nullable=false)
	private long locId;
	
	@Column(name="program_id")
	private String programId;
	
	@Column(name="location_id")
	private String locationId;
	
	@Column(name="request_to_location_id")
	private String requestToLocationId;
	
	@Column(name="location_name")
	private String locationName;
	
	
	@Column(name="loc_request_date")
	private Date locRequestDate;
	
	@Column(name="loc_release_date")
	private Date locReleaseDate;
	
	@Column(name="component_a_req_amount")
	private BigDecimal componentAReqAmount;
	
	@Column(name="component_a_rels_amount")
	private BigDecimal componentARelsAmount;
	
	@Column(name="component_b_req_amount")
	private BigDecimal componentBReqAmount;
	
	@Column(name="component_b_rels_amount")
	private BigDecimal componentBRelsAmount;
	
	@Column(name="component_c_req_amount")
	private BigDecimal componentCReqAmount;
	
	@Column(name="component_c_rels_amount")
	private BigDecimal componentCRelsAmount;
	
	@Column(name="amount_fd_request")
	private BigDecimal amountFDRequest;
	
	@Column(name="amount_fd_release")
	private BigDecimal amountFDRelease;
	
	@Embedded
	private MISAuditBean misAuditBean;
	
	public BigDecimal getAmountFDRequest() {
		return amountFDRequest;
	}

	public void setAmountFDRequest(BigDecimal amountFDRequest) {
		this.amountFDRequest = amountFDRequest;
	}

	public BigDecimal getAmountFDRelease() {
		return amountFDRelease;
	}

	public void setAmountFDRelease(BigDecimal amountFDRelease) {
		this.amountFDRelease = amountFDRelease;
	}

	@OneToMany(targetEntity=LOCDetailBean.class,fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	@JoinColumn(name="loc_id",updatable = false , insertable = false)
	private Set<LOCDetailBean> locDetailBeans;
	
	@OneToMany(targetEntity=LOCActivityBean.class,fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	@JoinColumn(name="loc_id",updatable = false , insertable = false)
	private Set<LOCActivityBean> locActivtyBeans;
	
	

	public Set<LOCActivityBean> getLocActivtyBeans() {
		return locActivtyBeans;
	}

	public void setLocActivtyBeans(Set<LOCActivityBean> locActivtyBeans) {
		this.locActivtyBeans = locActivtyBeans;
	}

	public long getLocId() {
		return locId;
	}

	public void setLocId(long locId) {
		this.locId = locId;
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

	public Date getLocRequestDate() {
		return locRequestDate;
	}

	public void setLocRequestDate(Date locRequestDate) {
		this.locRequestDate = locRequestDate;
	}

	public Date getLocReleaseDate() {
		return locReleaseDate;
	}

	public void setLocReleaseDate(Date locReleaseDate) {
		this.locReleaseDate = locReleaseDate;
	}

	public BigDecimal getComponentAReqAmount() {
		return componentAReqAmount;
	}

	public void setComponentAReqAmount(BigDecimal componentAReqAmount) {
		this.componentAReqAmount = componentAReqAmount;
	}

	public BigDecimal getComponentARelsAmount() {
		return componentARelsAmount;
	}

	public void setComponentARelsAmount(BigDecimal componentARelsAmount) {
		this.componentARelsAmount = componentARelsAmount;
	}

	public BigDecimal getComponentBReqAmount() {
		return componentBReqAmount;
	}

	public void setComponentBReqAmount(BigDecimal componentBReqAmount) {
		this.componentBReqAmount = componentBReqAmount;
	}

	public BigDecimal getComponentBRelsAmount() {
		return componentBRelsAmount;
	}

	public void setComponentBRelsAmount(BigDecimal componentBRelsAmount) {
		this.componentBRelsAmount = componentBRelsAmount;
	}

	public BigDecimal getComponentCReqAmount() {
		return componentCReqAmount;
	}

	public void setComponentCReqAmount(BigDecimal componentCReqAmount) {
		this.componentCReqAmount = componentCReqAmount;
	}

	public BigDecimal getComponentCRelsAmount() {
		return componentCRelsAmount;
	}

	public void setComponentCRelsAmount(BigDecimal componentCRelsAmount) {
		this.componentCRelsAmount = componentCRelsAmount;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	
	public Set<LOCDetailBean> getLocDetailBeans() {
		return locDetailBeans;
	}

	public void setLocDetailBeans(Set<LOCDetailBean> locDetailBeans) {
		this.locDetailBeans = locDetailBeans;
	}

	@Override
	public String toString() {
		return "LOCHeaderBean [locId=" + locId + ", programId=" + programId
				+ ", locationId=" + locationId + ", locRequestDate="
				+ locRequestDate + ", locReleaseDate=" + locReleaseDate
				+ ",requestToLocationId="+requestToLocationId
				+ ", componentAReqAmount=" + componentAReqAmount
				+ ", componentARelsAmount=" + componentARelsAmount
				+ ", componentBReqAmount=" + componentBReqAmount
				+ ", componentBRelsAmount=" + componentBRelsAmount
				+ ", componentCReqAmount=" + componentCReqAmount
				+ ", componentCRelsAmount=" + componentCRelsAmount
				+ ", amountFDRequest="+amountFDRequest
				+ ", amountFDRelease="+amountFDRelease
				+ ", misAuditBean=" + misAuditBean + "]";
	}

	@Override
	public int compareTo(LOCHeaderBean o) {
		// TODO Auto-generated method stub
		return new Long(this.locId).compareTo(o.locId);
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
	
	

}
