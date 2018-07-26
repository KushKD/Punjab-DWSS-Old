/**
 * 
 */
package com.prwss.mis.common.notification.dao;

/**
 * @author pjha
 *
 */

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="t_notification_routed_to", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class ActiveNotificationBean implements Serializable {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = 7497871196807275219L;

	@Id
	@Column(name="notification_id")
	private long notificationId;
	
	
	@Id
	@Column(name="employee_id")
	private long employeeId;
	
	
//	@
//	@ManyToOne(targetEntity=NotificationBean.class , fetch = FetchType.EAGER)
//	@JoinColumn(name="notification_id", updatable=false , insertable= false)
//	private Set<NotificationBean> notificationBean;

	
//	@Embedded
//	private MISAuditBean misAuditBean;

	public long getNotificationId() {
		return notificationId;
	}



	public void setNotificationId(long notificationId) {
		this.notificationId = notificationId;
	}



	public long getEmployeeId() {
		return employeeId;
	}



	



//	public Set<NotificationBean> getNotificationBean() {
//		return notificationBean;
//	}
//
//
//
//
//	public void setNotificationBean(Set<NotificationBean> notificationBean) {
//		this.notificationBean = notificationBean;
//	}







	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

//	public MISAuditBean getMisAuditBean() {
//		return misAuditBean;
//	}
//
//	public void setMisAuditBean(MISAuditBean misAuditBean) {
//		this.misAuditBean = misAuditBean;
//	}
	
	@Override
	public String toString() {
		return "ActiveNotificationBean [notificationId=" + notificationId
				+ ", employeeId=" + employeeId + "]";
	}

}
