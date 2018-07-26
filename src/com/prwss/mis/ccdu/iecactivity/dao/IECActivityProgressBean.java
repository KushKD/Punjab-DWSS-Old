package com.prwss.mis.ccdu.iecactivity.dao;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.masters.iecactivity.dao.IECActivityBean;

@Entity
@Table(name="t_ccdu_iec_progress", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class IECActivityProgressBean implements Serializable,Comparable<IECActivityProgressBean> {
	
	/**
	 *  Initial Version
	 */
	private static final long serialVersionUID = -4798805775918498426L;

	@Id
	@GeneratedValue(generator="seq_iec_progress_id",strategy=GenerationType.AUTO)
	@SequenceGenerator(name="seq_iec_progress_id", sequenceName="prwss_main.seq_iec_progress_id")
	@Column(name="iec_progress_id", nullable=false)
	private long iecProgressId;
	
	@Column(name="location_id")
	private String locationId;
	
	@Column(name="block_id")
	private String blockId;
	
	@Column(name="village_id")
	private String villageId;
	
	@ManyToOne(targetEntity=IECActivityBean.class)
	@JoinColumn(name="activity_id", referencedColumnName="iec_activity_id")
	private IECActivityBean iecActivityBean;
	
	@Column(name="activity_date")
	private Date activityDate;
	
	@Column(name="venue")
	private String venue;
	
	@Column(name="no_of_participants")
	private long noOfParticipants;
	
	@Column(name="outcome")
	private String outcome;
	
	@Column(name="notes")
	private String notes;
	
	/*@Type(type="true_false")*/
	@Column(name="is_complete",nullable=false)
	private boolean isComplete;
	
	@Column(name="expenditure")
	private double expenditure;
	
	@Embedded
	private MISAuditBean misAuditBean;
	
	@OneToMany(targetEntity=IECActivityMaterialUtilizationBean.class, fetch=FetchType.EAGER)
	@JoinColumn(name="iec_progress_id", updatable=false, insertable=false)
	private Set<IECActivityMaterialUtilizationBean> iecActivityMaterialUtilizationBeans;

	public long getIecProgressId() {
		return iecProgressId;
	}

	public void setIecProgressId(long iecProgressId) {
		this.iecProgressId = iecProgressId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getBlockId() {
		return blockId;
	}

	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}

	public String getVillageId() {
		return villageId;
	}

	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}

	public IECActivityBean getIecActivityBean() {
		return iecActivityBean;
	}

	public void setIecActivityBean(IECActivityBean iecActivityBean) {
		this.iecActivityBean = iecActivityBean;
	}

	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public long getNoOfParticipants() {
		return noOfParticipants;
	}

	public void setNoOfParticipants(long noOfParticipants) {
		this.noOfParticipants = noOfParticipants;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
	

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}		

	@Override
	public String toString() {
		return "IECActivityProgressBean [iecProgressId=" + iecProgressId
				+ ", locationId=" + locationId + ", blockId=" + blockId
				+ ", villageId=" + villageId + ", iecActivityBean="
				+ iecActivityBean + ", activityDate=" + activityDate
				+ ", venue=" + venue + ", noOfParticipants=" + noOfParticipants
				+ ", outcome=" + outcome + ", notes=" + notes + ", isComplete="
				+ isComplete + ", misAuditBean=" + misAuditBean
				+ ", iecActivityMaterialUtilizationBeans="
				+ iecActivityMaterialUtilizationBeans + ",expenditure=" +expenditure +"]";
	}

	@Override
	public int compareTo(IECActivityProgressBean o) {
		return new Long(this.getIecProgressId()).compareTo(o.getIecProgressId());
	}

	public void setIecActivityMaterialUtilizationBeans(
			Set<IECActivityMaterialUtilizationBean> iecActivityMaterialUtilizationBeans) {
		this.iecActivityMaterialUtilizationBeans = iecActivityMaterialUtilizationBeans;
	}

	public Set<IECActivityMaterialUtilizationBean> getIecActivityMaterialUtilizationBeans() {
		return iecActivityMaterialUtilizationBeans;
	}

	public void setExpenditure(double expenditure) {
		this.expenditure = expenditure;
	}

	public double getExpenditure() {
		return expenditure;
	}

}
