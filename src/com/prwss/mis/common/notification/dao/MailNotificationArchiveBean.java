package com.prwss.mis.common.notification.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="notification_outgoing_archive", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class MailNotificationArchiveBean implements Serializable {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = -4169680520400897198L;

	@Id
	@Column(name="notification_id", nullable=false)
	private long notificationId;
	
	@Column(name="email_to")
	private String emailTo;
	
	@Column(name="email_cc")
	private String emailCC;
	
	@Column(name="email_from")
	private String emailFrom;
	
	@Column(name="email_subject")
	private String emailSubject;
	
	@Column(name="email_body")
	private String emailBody;
	
	@Column(name="email_attachments")
	private byte[] emailAttachments;
	
	@Column(name="email_status")
	private String emailStatus;
	
	@Column(name="ent_by")
	private String entBy;
	
	@Column(name="ent_date")
	private Timestamp entDate;

	public long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(long notificationId) {
		this.notificationId = notificationId;
	}

	public String getEmailTo() {
		return emailTo;
	}

	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	public String getEmailCC() {
		return emailCC;
	}

	public void setEmailCC(String emailCC) {
		this.emailCC = emailCC;
	}

	public String getEmailFrom() {
		return emailFrom;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailBody() {
		return emailBody;
	}

	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}

	public byte[] getEmailAttachments() {
		return emailAttachments;
	}

	public void setEmailAttachments(byte[] emailAttachments) {
		this.emailAttachments = emailAttachments;
	}

	public String getEmailStatus() {
		return emailStatus;
	}

	public void setEmailStatus(String emailStatus) {
		this.emailStatus = emailStatus;
	}

	public String getEntBy() {
		return entBy;
	}

	public void setEntBy(String entBy) {
		this.entBy = entBy;
	}

	public Timestamp getEntDate() {
		return entDate;
	}

	public void setEntDate(Timestamp entDate) {
		this.entDate = entDate;
	}

	@Override
	public String toString() {
		return "MailNotificationBean [notificationId=" + notificationId + ", emailTo=" + emailTo + ", emailCC="
				+ emailCC + ", emailFrom=" + emailFrom + ", emailSubject=" + emailSubject + ", emailBody=" + emailBody
				+ ", emailAttachments=" + Arrays.toString(emailAttachments) + ", emailStatus=" + emailStatus
				+ ", entBy=" + entBy + ", entDate=" + entDate + "]";
	}
	
}
