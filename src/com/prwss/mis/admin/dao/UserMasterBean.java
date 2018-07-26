/**
 * 
 */
package com.prwss.mis.admin.dao;

import java.io.Serializable;

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
@Table(name="mst_program", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class UserMasterBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1596380392737225696L;
	

	@Id	
	@Column(name="user_id", nullable=false)
	private String userId;

	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="user_password")
	private String userPassword;

	@Column(name="last_login_on")
	private String lastLoginOn;

	@Column(name=" gender")
	private String  gender;
	
	@Column(name="user_address1")
	private String userAddress1 ;
	
	@Column(name="user_address2")
	private String userAddress2 ;
	
	@Column(name="user_address3")
	private String userAddress3 ;
	
	@Column(name="user_telephone")
	private String userTelephone ;
	
	@Column(name="user_mobile")
	private String userMobile ;
	
	@Column(name="user_email")
	private String userEmail ;
	
	@Column(name="role_id")
	private String roleId ;
	
	@Embedded
	private MISAuditBean misAuditBean;
	
	
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getLastLoginOn() {
		return lastLoginOn;
	}

	public void setLastLoginOn(String lastLoginOn) {
		this.lastLoginOn = lastLoginOn;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUserAddress1() {
		return userAddress1;
	}

	public void setUserAddress1(String userAddress1) {
		this.userAddress1 = userAddress1;
	}

	public String getUserAddress2() {
		return userAddress2;
	}

	public void setUserAddress2(String userAddress2) {
		this.userAddress2 = userAddress2;
	}

	public String getUserAddress3() {
		return userAddress3;
	}

	public void setUserAddress3(String userAddress3) {
		this.userAddress3 = userAddress3;
	}

	public String getUserTelephone() {
		return userTelephone;
	}

	public void setUserTelephone(String userTelephone) {
		this.userTelephone = userTelephone;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	

	@Override
	public String toString() {
		return "UserMasterBean [userId=" + userId + ", userName=" + userName
				+ ", userPassword=" + userPassword + ", lastLoginOn="
				+ lastLoginOn + ", gender=" + gender + ", userAddress1="
				+ userAddress1 + ", userAddress2=" + userAddress2
				+ ", userAddress3=" + userAddress3 + ", userTelephone="
				+ userTelephone + ", userMobile=" + userMobile + ", userEmail="
				+ userEmail + ", misAuditBean=" + misAuditBean + "]";
	}
	
	 
}
