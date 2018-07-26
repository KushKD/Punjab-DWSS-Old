package com.prwss.mis.admin;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;


@Entity
@Table(name="msg_brodcast", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class MessageBrodcastBean  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8724278558313069205L;
	
	
	@Id
	@GeneratedValue(generator ="seq_msg_brodcast", strategy = GenerationType.AUTO)
	@SequenceGenerator(name ="seq_msg_brodcast", sequenceName = "prwss_main.seq_msg_brodcast")
	@Column(name = "msg_id", nullable = false)
	private long messageId;
	
	@Column(name = "msg_desc")
	private String messageDetail;
	
	@Column(name = "expiry_date")
	private Date expiryDate;
	
	@Embedded
	private MISAuditBean misAuditBean;

	public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	public String getMessageDetail() {
		return messageDetail;
	}

	public void setMessageDetail(String messageDetail) {
		this.messageDetail = messageDetail;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}
	
}
