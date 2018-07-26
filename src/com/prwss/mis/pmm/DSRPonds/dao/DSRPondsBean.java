/**
 * 
 */
package com.prwss.mis.pmm.DSRPonds.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

/**
 * @author pjha
 *
 */
@Entity
@Table(name="pmm_dsr_ponds", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class DSRPondsBean implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6879673914955293105L;
	
	@Id	
	@Column(name="scheme_id", nullable=false)
	private String schemeId;
	@Column(name="location_id")
	private String  locationId;
	
	@Column(name="village_id")
	private String  villageId;
	
	@Column(name="dsr_date")
	private Date  dsrDate;;
	
	@Column(name="jcb_hours")
	private String  jcbHours;
	
	@Column(name="jcb_hours_cost")
	private double  jcbHoursCost;
	
	@Column(name="tractors_hours")
	private String  tractorsHours;
	  
	@Column(name="tractors_hours_cost")
	private double  tractorsHoursCost;
	
	@Column(name="labour_mandays")
	private String  labourMandays;
	
	@Column(name="labour_mandays_cost")
	private double  labourMandaysCost;
	 
	@Column(name="unforeseen_cost")
	private double  unforeseenCost;
	
	@Column(name="haudis_quantity")
	private long  haudisQuantity;
	
	@Column(name="haudis_cost")
	private double  haudisCost;
	
	@Column(name="pipeline_length")
	private String  pipelineLength;
	 
	@Column(name="pipeline_cost")
	private double  pipelineCost;
	 
	@Column(name="otherallied_cost")
	private double  otheralliedCost;
	
	@Column(name="drains_cost")
	private double  drainsCost;
	  
	@Column(name="total_cost")
	private BigDecimal  totalCost;
	
	@Embedded
	private MISAuditBean misAuditBean;
	

	public String getSchemeId() {
		return schemeId;
	}




	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}




	public String getLocationId() {
		return locationId;
	}




	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}




	public String getVillageId() {
		return villageId;
	}




	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}




	public Date getDsrDate() {
		return dsrDate;
	}




	public void setDsrDate(Date dsrDate) {
		this.dsrDate = dsrDate;
	}




	public String getJcbHours() {
		return jcbHours;
	}




	public void setJcbHours(String jcbHours) {
		this.jcbHours = jcbHours;
	}




	public double getJcbHoursCost() {
		return jcbHoursCost;
	}




	public void setJcbHoursCost(double jcbHoursCost) {
		this.jcbHoursCost = jcbHoursCost;
	}




	public String getTractorsHours() {
		return tractorsHours;
	}




	public void setTractorsHours(String tractorsHours) {
		this.tractorsHours = tractorsHours;
	}




	public double getTractorsHoursCost() {
		return tractorsHoursCost;
	}




	public void setTractorsHoursCost(double tractorsHoursCost) {
		this.tractorsHoursCost = tractorsHoursCost;
	}




	public String getLabourMandays() {
		return labourMandays;
	}




	public void setLabourMandays(String labourMandays) {
		this.labourMandays = labourMandays;
	}




	public double getLabourMandaysCost() {
		return labourMandaysCost;
	}




	public void setLabourMandaysCost(double labourMandaysCost) {
		this.labourMandaysCost = labourMandaysCost;
	}




	public double getUnforeseenCost() {
		return unforeseenCost;
	}




	public void setUnforeseenCost(double unforeseenCost) {
		this.unforeseenCost = unforeseenCost;
	}




	public long getHaudisQuantity() {
		return haudisQuantity;
	}




	public void setHaudisQuantity(long haudisQuantity) {
		this.haudisQuantity = haudisQuantity;
	}




	public double getHaudisCost() {
		return haudisCost;
	}




	public void setHaudisCost(double haudisCost) {
		this.haudisCost = haudisCost;
	}




	public String getPipelineLength() {
		return pipelineLength;
	}




	public void setPipelineLength(String pipelineLength) {
		this.pipelineLength = pipelineLength;
	}




	public double getPipelineCost() {
		return pipelineCost;
	}




	public void setPipelineCost(double pipelineCost) {
		this.pipelineCost = pipelineCost;
	}




	public double getOtheralliedCost() {
		return otheralliedCost;
	}




	public void setOtheralliedCost(double otheralliedCost) {
		this.otheralliedCost = otheralliedCost;
	}




	public double getDrainsCost() {
		return drainsCost;
	}




	public void setDrainsCost(double drainsCost) {
		this.drainsCost = drainsCost;
	}




	public BigDecimal getTotalCost() {
		return totalCost;
	}




	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}




	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}




	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}




	@Override
	public String toString() {
		return "DSRPondsBean [schemeId=" + schemeId + ", locationId="
				+ locationId + ", jcbHours=" + jcbHours + ", jcbHoursCost="
				+ jcbHoursCost + ", tractorsHours=" + tractorsHours
				+ ", tractorsHoursCost=" + tractorsHoursCost
				+ ", labourMandays=" + labourMandays + ", labourMandaysCost="
				+ labourMandaysCost + ", unforeseenCost=" + unforeseenCost
				+ ", haudisQuantity=" + haudisQuantity + ", haudisCost="
				+ haudisCost + ", pipelineLength=" + pipelineLength
				+ ", pipelineCost=" + pipelineCost + ", otheralliedCost="
				+ otheralliedCost + ", drainsCost=" + drainsCost
				+ ", totalCost=" + totalCost + "]";
	}
	  
	 
	  
	  
	
}
