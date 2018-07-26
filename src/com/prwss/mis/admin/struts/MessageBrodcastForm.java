package com.prwss.mis.admin.struts;

import org.apache.struts.validator.ValidatorForm;

public class MessageBrodcastForm extends ValidatorForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5256036510948652484L;
	
	private long messageId;
	private String messageDetail;
	private String expiryDate;
	
	
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
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	

}
