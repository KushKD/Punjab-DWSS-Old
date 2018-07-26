package com.prwss.mis.common.notification.dao;

import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="t_notification", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class NotificationBean implements Serializable , Comparable<NotificationBean>{

	private static final long serialVersionUID = 5567216145953645456L;
		  
	@Id
	@Column(name="notification_id")
	private long notificationId;
	
	@Column(name="document_type")
	private String documentType;
	
	@Column(name="location_id")
	private String locationId;
	
	@Column(name="exception_type")
	private String exceptionType;
	
	@Column(name="document_id")
	private String documentId;
	
	@Column(name="entry_mode")
	private String entryMode;
	
	@Column(name="opened_by")
	private long openedBy;
	
	@Column(name="opened_date")
	private Date openedDate;	  
	  
	@Column(name="current_status_by")
	private long currentStatusBy;	
	
	@Column(name="current_status_date")
	private Date currentStatusDate;
	
	@Column(name="notification_status")
	private String notificationStatus;
	
	@Column(name="short_message")
	private String shortMessage;
	
	
	@Column(name="message")
	private String message;
	
	@Column(name="routed_to")
	private String routedTo;
	/*
	@Column(name="routed_to_array")
	private int routedToArray[];
	 
	@Embedded
	private MISAuditBean misAuditBean; */
	
	public long getNotificationId() {
		return notificationId;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getRoutedTo() {
		return routedTo;
	}


	public void setRoutedTo(String routedTo) {
		this.routedTo = routedTo;
	}


	public long getCurrentStatusBy() {
		return currentStatusBy;
	}


	public void setCurrentStatusBy(long currentStatusBy) {
		this.currentStatusBy = currentStatusBy;
	}


	public Date getCurrentStatusDate() {
		return currentStatusDate;
	}


	public void setCurrentStatusDate(Date currentStatusDate) {
		this.currentStatusDate = currentStatusDate;
	}


	public String getNotificationStatus() {
		return notificationStatus;
	}


	public void setNotificationStatus(String notificationStatus) {
		this.notificationStatus = notificationStatus;
	}


	public String getShortMessage() {
		return shortMessage;
	}


	public void setShortMessage(String shortMessage) {
		this.shortMessage = shortMessage;
	}


	public String getExceptionType() {
		return exceptionType;
	}


	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}


	public String getDocumentId() {
		return documentId;
	}


	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}


	public String getEntryMode() {
		return entryMode;
	}


	public void setEntryMode(String entryMode) {
		this.entryMode = entryMode;
	}


	public long getOpenedBy() {
		return openedBy;
	}


	public void setOpenedBy(long openedBy) {
		this.openedBy = openedBy;
	}


	public Date getOpenedDate() {
		return openedDate;
	}


	public void setOpenedDate(Date openedDate) {
		this.openedDate = openedDate;
	}


	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}


	public void setNotificationId(long notificationId) {
		this.notificationId = notificationId;
	}


	public String getDocumentType() {
		return documentType;
	}


	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}


	public String getLocationId() {
		return locationId;
	}

@Override
public String toString() {
	return "NotificationBean [notificationId=" + notificationId
			+ ", documentType=" + documentType + ", locationId=" + locationId
			+ ", exceptionType=" + exceptionType + ", documentId=" + documentId
			+ ", entryMode=" + entryMode + ", openedBy=" + openedBy
			+ ", openedDate=" + openedDate + ", currentStatusBy="
			+ currentStatusBy + ", currentStatusDate=" + currentStatusDate
			+ ", notificationStatus=" + notificationStatus + ", shortMessage="
			+ shortMessage + ", message=" + message + ", routedTo=" + routedTo
			+ "]";
}


	@Override
	public int compareTo(NotificationBean o) {
		return new Long (this.notificationId).compareTo(o.getNotificationId());
	}

	

	
}
