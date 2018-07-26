package com.prwss.mis.service.ticket.dao;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;
import com.prwss.mis.masters.complaint.dao.ComplaintBean;
import com.prwss.mis.masters.employee.dao.EmployeeBean;

@Entity
@Table(name = "t_complaint_book", schema = MISConstants.MIS_DB_SCHEMA_NAME)
public class ComplaintBookBean implements Serializable, Comparable<ComplaintBookBean> {

	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = 8719959082768082822L;

	@Id
	@GeneratedValue(generator = "seq_t_ticket_id", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "seq_t_ticket_id", sequenceName = "prwss_main.seq_t_ticket_id")
	@Column(name = "ticket_id", nullable = false)
	private long ticketId;

	@ManyToOne(targetEntity = ComplaintBean.class)
	@JoinColumn(name = "complaint_id", nullable = false)
	private ComplaintBean complaintBean;

	@Column(name = "priority")
	private String priority;

	@Column(name = "description", length = 400)
	private String description;

	@Column(name = "subject", length = 50)
	private String subject;

	@Column(name = "public_name", length = 100)
	private String publicName;
		
	@Column(name = "public_email", length = 100)
	private String publicEmail;
	
	@Column(name = "public_address", length = 400)
	private String publicAddress;
	
	@Column(name = "public_feedback_type", length = 100)
	private String publicFeedbackType;
	
	@Column(name = "complaint_book_type", length = 100)
	private String complaintBookType;
	
	@Column(name = "status")
	private String status;

	@Column(name = "location_id")
	private String locationId;
	
	@Column(name = "public_mobile")
	private String publicMobile;
	
	
	@Column(name = "district_id")
	private String districtId;
	
	@ManyToOne(targetEntity = EmployeeBean.class)
	@JoinColumn(name = "pending_with")
	private EmployeeBean assignedEmployeeBean;

	@ManyToOne(targetEntity = EmployeeBean.class)
	@JoinColumn(name = "ent_by")
	private EmployeeBean ownedEmployeeBean;

	@Column(name = "ent_date")
	private Timestamp entDate;

	public long getTicketId() {
		return ticketId;
	}

	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}

	public ComplaintBean getComplaintBean() {
		return complaintBean;
	}

	public void setComplaintBean(ComplaintBean complaintBean) {
		this.complaintBean = complaintBean;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPublicName() {
		return publicName;
	}

	public void setPublicName(String publicName) {
		this.publicName = publicName;
	}

	public String getPublicEmail() {
		return publicEmail;
	}

	public void setPublicEmail(String publicEmail) {
		this.publicEmail = publicEmail;
	}

	public String getPublicAddress() {
		return publicAddress;
	}

	public void setPublicAddress(String publicAddress) {
		this.publicAddress = publicAddress;
	}

	public String getPublicFeedbackType() {
		return publicFeedbackType;
	}

	public void setPublicFeedbackType(String publicFeedbackType) {
		this.publicFeedbackType = publicFeedbackType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public EmployeeBean getAssignedEmployeeBean() {
		return assignedEmployeeBean;
	}

	public void setAssignedEmployeeBean(EmployeeBean assignedEmployeeBean) {
		this.assignedEmployeeBean = assignedEmployeeBean;
	}

	public EmployeeBean getOwnedEmployeeBean() {
		return ownedEmployeeBean;
	}

	public void setOwnedEmployeeBean(EmployeeBean ownedEmployeeBean) {
		this.ownedEmployeeBean = ownedEmployeeBean;
	}

	public Timestamp getEntDate() {
		return entDate;
	}

	public void setEntDate(Timestamp entDate) {
		this.entDate = entDate;
	}

	@Override
	public int compareTo(ComplaintBookBean o) {

		return new Long(this.getTicketId()).compareTo(new Long(o.getTicketId()));
	}

	@Override
	public String toString() {
		return "\nComplaintBookBean [ticketId=" + ticketId + ", complaintBean=" + complaintBean + ", priority="
				+ priority + ", description=" + description + ", subject=" + subject + ",publicName="+publicName+",publicEmail="+publicEmail
				+",publicAddress="+publicAddress+",publicFeedbackType="+publicFeedbackType+",complaintBookType="+complaintBookType
				+", status=" + status
				+ ", locationId=" + locationId + ", assignedEmployeeBean=" + assignedEmployeeBean
				+ ", ownedEmployeeBean=" + ownedEmployeeBean + ", entDate=" + entDate + ",publicMobile=" +publicMobile+",districtId="+ districtId +"]\n";
	}

	public void setComplaintBookType(String complaintBookType) {
		this.complaintBookType = complaintBookType;
	}

	public String getComplaintBookType() {
		return complaintBookType;
	}

	public void setPublicMobile(String publicMobile) {
		this.publicMobile = publicMobile;
	}

	public String getPublicMobile() {
		return publicMobile;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getDistrictId() {
		return districtId;
	}
	

}
