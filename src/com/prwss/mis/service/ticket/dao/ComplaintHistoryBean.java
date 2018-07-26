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
import com.prwss.mis.masters.employee.dao.EmployeeBean;

@Entity
@Table(name = "t_complaint_history", schema = MISConstants.MIS_DB_SCHEMA_NAME)
public class ComplaintHistoryBean implements Serializable, Comparable<ComplaintHistoryBean> {

	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = -6151463953454394578L;

	@Id
	@GeneratedValue(generator = "seq_t_history_id", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "seq_t_history_id", sequenceName = "prwss_main.seq_t_history_id")
	@Column(name = "history_id", nullable = false)
	private long historyId;

	@Column(name = "ticket_id")
	private long ticketId;

	@ManyToOne(targetEntity = EmployeeBean.class)
	@JoinColumn(name = "assigned_to")
	private EmployeeBean assignedEmployeeBean;

	@Column(name = "comments")
	private String comments;

	@Column(name = "ent_by")
	private long entBy;

	@Column(name = "ent_date")
	private Timestamp entDate;

	@Column(name = "status")
	private String status;

	public long getHistoryId() {
		return historyId;
	}

	public void setHistoryId(long historyId) {
		this.historyId = historyId;
	}

	public long getTicketId() {
		return ticketId;
	}

	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}

	public EmployeeBean getAssignedEmployeeBean() {
		return assignedEmployeeBean;
	}

	public void setAssignedEmployeeBean(EmployeeBean assignedEmployeeBean) {
		this.assignedEmployeeBean = assignedEmployeeBean;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public long getEntBy() {
		return entBy;
	}

	public void setEntBy(long entBy) {
		this.entBy = entBy;
	}

	public Timestamp getEntDate() {
		return entDate;
	}

	public void setEntDate(Timestamp entDate) {
		this.entDate = entDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ComplaintHistoryBean [historyId=" + historyId + ", ticketId=" + ticketId + ", assignedEmployeeBean="
				+ assignedEmployeeBean + ", comments=" + comments + ", entBy=" + entBy + ", entDate=" + entDate
				+ ", status=" + status + "]";
	}

	@Override
	public int compareTo(ComplaintHistoryBean o) {

		return new Long(this.getHistoryId()).compareTo(new Long(o.getHistoryId()));
	}

}
